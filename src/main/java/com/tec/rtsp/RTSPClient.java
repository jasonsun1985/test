package com.tec.rtsp;

import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class RTSPClient extends Thread implements IEvent {

    /**rtsp版本号，必须是1.0? 还不确定！*/
    private static final String VERSION = " RTSP1.0\r\n";
    private static final String RTSP_OK = "RTSP/1.0 200 OK";

    /** 远程地址 */
    private final InetSocketAddress remoteAddress;

    /** * 本地地址 */
    private final InetSocketAddress localAddress;

     /** * 连接通道 */
    private SocketChannel socketChannel;

     /** 发送缓冲区 */
    private final ByteBuffer sendBuf;

     /** 接收缓冲区 */
    private final ByteBuffer receiveBuf;

    private static final int BUFFER_SIZE = 8192;

     /** 端口选择器 */
    private Selector selector;

    private String address;

    private Status sysStatus;



     /** 线程是否结束的标志 */
    private AtomicBoolean shutdown;
    /**请求回应对的序列*/
    private int seq=1;
    /**会话 Session在setup请求后返回  */
    private String sessionid=null;
    /**trackID 在 请求Descrbe 之后返回*/
    private String trackInfo;
    /**转换账号和密码*/
    private String authorBase64;
    /**是否是tcp方式*/
    private boolean isTCPtranslate = true;
    /**客户端代理名称随意填*/
    private final static String UserAgent = "RtspWalkera/1.0";

    private boolean isSended;



    private enum Status {
        init, options, describe, setup, play, pause, teardown
    }

    public RTSPClient(InetSocketAddress remoteAddress,
                      InetSocketAddress localAddress, String address) {
        this.remoteAddress = remoteAddress;
        this.localAddress = localAddress;
        this.address = address;

        // 初始化缓冲区
        sendBuf = ByteBuffer.allocateDirect(BUFFER_SIZE);
        receiveBuf = ByteBuffer.allocateDirect(BUFFER_SIZE);
        if (selector == null) {
            // 创建新的Selector
            try {
                System.out.print("创建Selector");
                selector = Selector.open();
            } catch (final IOException e) {

                e.printStackTrace();
            }
        }

        startup();
        sysStatus = Status.init;
        shutdown=new AtomicBoolean(false);
        isSended=false;
    }

    public void startup() {
        try {
            // 打开通道
            socketChannel = SocketChannel.open();
            // 绑定到本地端口
            socketChannel.socket().setSoTimeout(30000);
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(localAddress);

            if (socketChannel.connect(remoteAddress)) {
                System.out.println("开始建立连接:" + remoteAddress.getAddress() +" "+remoteAddress.getPort());
            }else{
                System.out.println("建立连接 失败:  "+ remoteAddress.getAddress() +" "+remoteAddress.getPort());
            }
            socketChannel.register(selector, SelectionKey.OP_CONNECT
                    | SelectionKey.OP_READ | SelectionKey.OP_WRITE, this);

            System.out.println("客户端初始化完毕\r\n");

        } catch (final IOException e1) {
            e1.printStackTrace();
        }
    }

    public void send(byte[] out) {
        if (out == null || out.length < 1) {
            return;
        }
        synchronized (sendBuf) {
            sendBuf.clear();
            sendBuf.put(out);
            sendBuf.flip();
        }

        // 发送出去
        try {
            write();
            isSended=true;
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void write() throws IOException {
        if (isConnected()) {
            try {
                socketChannel.write(sendBuf);
            } catch (final IOException e) {
            }
        } else {
            System.out.println("通道为空或者没有连接上");
        }
    }

    public byte[] recieve() {
        if (isConnected()) {
            try {
                int len = 0;
                int readBytes = 0;

                synchronized (receiveBuf) {
                    receiveBuf.clear();
                    try {
                        while ((len = socketChannel.read(receiveBuf)) > 0) {
                            readBytes += len;
                        }
                    } finally {
                        receiveBuf.flip();
                    }
                    if (readBytes > 0) {
                        final byte[] tmp = new byte[readBytes];
                        receiveBuf.get(tmp);
                        return tmp;
                    } else {
                        System.out.println("接收到数据为空,重新启动连接");
                        return null;
                    }
                }
            } catch (final IOException e) {
                System.out.println("接收消息错误:");
            }
        } else {
            System.out.println("端口没有连接");
        }
        return null;
    }

    public boolean isConnected() {
        return socketChannel != null && socketChannel.isConnected();
    }

    private void select() {
        int n = 0;
        try {
            if (selector == null) {
                return;
            }
            n = selector.select(1000);

        } catch (final Exception e) {
            e.printStackTrace();
        }

        // 如果select返回大于0，处理事件
        if (n > 0) {
            for (final Iterator<SelectionKey> i = selector.selectedKeys()
                    .iterator(); i.hasNext();) {
                // 得到下一个Key
                final SelectionKey sk = i.next();
                i.remove();
                // 检查其是否还有效
                if (!sk.isValid()) {
                    continue;
                }

                // 处理事件
                final IEvent handler = (IEvent) sk.attachment();
                try {
                    if (sk.isConnectable()) {
                        handler.connect(sk);
                    } else if (sk.isReadable()) {
                        handler.read(sk);
                    } else {
                        // System.err.println("Ooops");
                    }
                } catch (final Exception e) {
                    handler.error(e);
                    sk.cancel();
                }
            }
        }
    }

    public void shutdown() {
        if (isConnected()) {
            try {
                socketChannel.close();
                System.out.println("端口关闭成功");
            } catch (final IOException e) {
                System.out.println("端口关闭错误:");
            } finally {
                socketChannel = null;
            }
        } else {
            System.out.println("通道为空或者没有连接");
        }
    }

    @Override
    public void run() {

        while (!shutdown.get())
        {
            try {
                if (isConnected()&&(!isSended)) {
                    switch (sysStatus) {
                        case init:
                            doOption();
                            break;
                        case options:
                            doDescribe();
                            break;
                        case describe:
                            doSetup(isTCPtranslate);
                            break;
                        case setup:
                            if(sessionid==null&&sessionid.length()>0){
                                System.out.println("setup还没有正常返回");
                            }else{
                                doPlay();
                            }
                            break;
                        case play:
                            //doPause();
                            break;

                        case pause:
                            //doTeardown();
                            break;
                        default:
                            break;
                    }
                }
                // do select
                select();
                try {
                    Thread.sleep(1000);
                } catch (final Exception e) {
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        shutdown();
    }

    public void connect(SelectionKey key) throws IOException {
        if (isConnected()) {
            return;
        }
        // 完成SocketChannel的连接
        socketChannel.finishConnect();
        while (!socketChannel.isConnected()) {
            try {
                Thread.sleep(300);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            socketChannel.finishConnect();
        }

    }

    public void error(Exception e) {
        e.printStackTrace();
    }

    public void read(SelectionKey key) throws IOException {
        // 接收消息
        final byte[] msg = recieve();
        if (msg != null) {
            handle(msg);
        } else {
            key.cancel();
        }
    }

    private void handle(byte[] msg) {
        String tmp = "初始化字符串!!";
        try {
            tmp = new String(msg ,"utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        System.out.println("返回内容：" );
        System.out.println(tmp);
        if (tmp.startsWith(RTSP_OK)) {
            switch (sysStatus) {
                case init:
                    sysStatus = Status.options;
                    break;
                case options:
                    sysStatus = Status.describe;
                    trackInfo=tmp.substring(tmp.indexOf("trackID"));
                    break;
                case describe:
                    sessionid = tmp.substring(tmp.indexOf("Session: ") + 9, tmp .indexOf("Date:"));
                    if(sessionid!=null&&sessionid.length()>0){
                        sysStatus = Status.setup;
                    }
                    break;
                case setup:
                    sysStatus = Status.play;
                    break;
                case play:
                    //sysStatus = Status.pause;
                    break;
                case pause:
                    //sysStatus = Status.teardown;
                    //shutdown.set(true);
                    break;
                case teardown:
                   // sysStatus = Status.init;
                    break;
                default:
                    break;
            }
            isSended=false;
        } else {
            System.out.println("返回错误：" + tmp);
        }

    }



    public void initConfig(String authorName  ,  String authorPassword ,boolean isTcp)
    {
        isTCPtranslate = isTcp;
        if(authorName == null && authorPassword == null) {
            authorBase64 = null;
        }  else {
            BASE64Encoder encoder = new BASE64Encoder();
            // 返回Base64编码过的字节数组字符串
            authorBase64= encoder.encode((authorName+":"+authorPassword).getBytes());
        }
    }
    private String addHeaders() {
        return "CSeq: " + (seq++) + "\r\n"
                + ((authorBase64 == null)?"":("Authorization: Basic " +authorBase64 +"\r\n"))
                + "User-Agent: " + UserAgent + "\r\n"
                + ((sessionid == null)?"":("Session: " + sessionid + "\r\n"))
                + "\r\n";
    }


    /**
     * option请求 <br>
     *     此步骤非必须
     */
    private void doOption()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("OPTIONS ");
        sb.append(this.address.substring(0, address.lastIndexOf("/")));
        sb.append(VERSION);
        sb.append(addHeaders());
        send(sb.toString().getBytes());
        System.out.print("请求Option: "+sb.toString());
    }

    /**
     *  Describe 请求<br>
     *  sps 和 pps 会在这个阶段返回
     *  sprop-parameter-sets= Z0IAKpY1QMgEvTcBAQEC,aM48gA==
     *  将  sprop-parameter-sets 对应的值用base64 解码即可 67 和68
     *  分别对应sps 和pps . <br>
     *      不过有的rtsp服务器如果没有按标准写的话 ，sprop-parameter-sets 返回的值可能为空。
     *      此时 如果想获取sps 和pps 只能通过rtsp服务器返回的 帧数据中去获取了。
     */
    private void doDescribe()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DESCRIBE ");
        sb.append(this.address);
        sb.append(VERSION);
        sb.append(addHeaders());
        send(sb.toString().getBytes());
        System.out.println("请求Descrbe：  "+sb.toString());
    }
    /**
     * 请求setup
     */
    private void doSetup(boolean isTcp)
    {
        StringBuilder sb = new StringBuilder();
        if ( isTcp ) {
            sb.append("SETUP ");
            sb.append(this.address);
            sb.append("/");
            sb.append(trackInfo);
            sb.append(VERSION);
            sb.append("Transport: RTP/AVP/TCP;unicast;client_port=55640-55641" + "\r\n");
            sb.append(addHeaders());
        } else {

            sb.append("SETUP ");
            sb.append(this.address);
            sb.append("/");
            sb.append(trackInfo);
            sb.append(VERSION);
            sb.append("Transport: RTP/AVP/UDP;unicast;client_port=55640-55641" + "\r\n");
            sb.append(addHeaders());
        }

        send(sb.toString().getBytes());
        System.out.println("setUp请求："+sb.toString());
    }

    /**
     * play 请求
     */
    private void doPlay()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("PLAY ");
        sb.append(this.address);
        sb.append(VERSION);
        //设置播放的时间范围
        sb.append("Range: npt=0.000-\r\n");
        sb.append(addHeaders()) ;

        send(sb.toString().getBytes());
        System.out.println("请求play "+sb.toString());

    }

    /**
     * Pause 暂停请求
     */
    private void doPause() {
        StringBuilder sb = new StringBuilder();
        sb.append("PAUSE ");
        sb.append(this.address);
        sb.append("/");
        sb.append(VERSION);
        sb.append(addHeaders()) ;
        send(sb.toString().getBytes());
        System.out.println("请求pause： "+sb.toString());
    }

    /**
     * 关闭连接
     */
    private void doTeardown()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("TEARDOWN ");
        sb.append(this.address);
        sb.append("/");
        sb.append(VERSION);
        sb.append(addHeaders()) ;
        send(sb.toString().getBytes());
        System.out.println("请求tearDown："+sb.toString());
    }


}
