package com.tec.rtsp;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Aivin on 2017/4/5.
 */

public class MyStringUtils {


    /**
     * bytes转换成十六进制字符串
     *
     * @param b byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b) {
        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }
    public static String byte2HexStrLikeIos(byte[] b) {
        String stmp = "";
        StringBuilder sb = new StringBuilder("");

        int i= 0;
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);

            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            if(n%2!=0)
            {
                sb.append(" ");
                i++ ;

                if(i%8==0)
                {
                    sb.append("\r\n");
                }
            }


        }
        return sb.toString().toLowerCase().trim();
    }


    public static String byteOne2HexStr(byte  b)
    {
        String  stmp = Integer.toHexString( b & 0xFF);


        return  (stmp.length() == 1) ? "0" + stmp : stmp ;
    }


    public static String byte1ToHexStr(byte mByte) {
        String stmp   = Integer.toHexString(mByte & 0xFF);
        return stmp.toUpperCase().trim();
    }

/**************************************************************************************************
 *  int 转为 4 个字节的 byte 数组
 ************************************************************************************************/

    /***
     * int 类型 转为 1个字节长度的 byte
     */
    public static byte intTo1ByteHex(int intVaue /*, byte[]  byteVauesHex*/) {
//	        for (int i = 0   ;i <byteVauesHex.length  ;  i ++) {
        return new Integer(intVaue & 0xff).byteValue();
//	            intVaue = intVaue >> 8;
//	        }

    }

    /***
     * int 类型 转为 1个字节长度的 byte数组、
     * ？？
     */
    public static byte[] intTo1ByteHexArray(int intVaue )
    {
        byte[] array = new byte[1] ;
        array[0] = new  Integer(intVaue & 0xff).byteValue();
        return array ;

    }

    /***
     * int 类型 转为 4个字节长度的 byte数组。
     * 大端小端翻转 。
     * 邹聪提供。
     *
     * @param intVaue
     * @param byteVauesHex
     */
    public static void intTo4ByteArrayHex(int intVaue, byte[] byteVauesHex) {
        for (int i = 0; i < byteVauesHex.length; i++) {
            byteVauesHex[i] = new Integer(intVaue & 0xff).byteValue();
            intVaue = intVaue >> 8;
        }

    }






    /**
     * 将一个byte转为一个 byte数组
     * @param mByte
     * @return
     */
    public static byte []  byte1ToByteArray( byte mByte)
    {
        byte [] bytes= {mByte} ;
        return  bytes;

    }


    /***
     * int 类型 转为 4个字节长度的 byte数组。
     * 正常顺序 。不考虑大端小端翻转 。
     * 邹聪提供。
     *
     * @param intVaue
     * @param byteVauesHex
     */
    public static void intTo4ByteArrayHexNormal(int intVaue, byte[] byteVauesHex) {
        for (int i = byteVauesHex.length - 1; i >= 0; i--) {
            byteVauesHex[i] = new Integer(intVaue & 0xff).byteValue();
            intVaue = intVaue >> 8;
        }

    }


    /**
     * 将一个short转为2个字节的数组
     * 算法来源 网络
     * http://blog.csdn.net/thanklife/article/details/17002641
     * 如: pitch = 1900 = 0x076c => 发送顺序：先发0x07，再发0x6c
     * 高位在前，低位在后
     */
    public static byte[] shortTobytes2_height_low(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = b.length - 1; i >= 0; i--) {
            b[i] = new Integer(temp & 0xff).byteValue();//
            temp = temp >> 8;
        }
        return b;
    }

    public static byte[] shortTobytes2_low_height(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {

            b[i] = new Integer(temp & 0xff).byteValue();//
            temp = temp >> 8;
        }
        return b;
    }


    public static  byte[]  intTo4ByteArrayHex_height_low (int intVaue)
    {
        byte[] byteVauesHex = new byte[4] ;
        for (int i = byteVauesHex.length -1; i >=0  ; i--) {
            byteVauesHex[i] = new Integer(intVaue & 0xff).byteValue();//
            intVaue = intVaue >> 8;
        }

        return  byteVauesHex ;
    }

    public static  byte[]  intTo4ByteArrayHex_low_height(int intVaue)
    {
        byte[] byteVauesHex = new byte[4] ;
        for (int i = 0; i < byteVauesHex.length; i++) {
            byteVauesHex[i] = new Integer(intVaue & 0xff).byteValue();
            intVaue = intVaue >> 8;
        }

        return  byteVauesHex ;
    }



    public static  byte[]  floatTo4ByteArrayHex_height_low (float intVaue)
    {
        byte[] byteVauesHex = new byte[4] ;
        int fbit = Float.floatToIntBits(intVaue);
        for (int pos = 3; pos >= 0 ; pos -- ) {
            byteVauesHex[pos] = new Integer(fbit).byteValue();
            fbit = fbit >> 8;
        }
        return  byteVauesHex ;
    }


    /**
     *  注： float转byte[] 跟 int转byte[] 是不一样的。
     * @param intVaue
     * @return
     */
    public static  byte[]  floatTo4ByteArrayHex_low_height(float intVaue)
    {
        byte[] byteVauesHex = new byte[4] ;
        int fbit = Float.floatToIntBits(intVaue);
        for (int pos = 0; pos <  4; pos++) {
            byteVauesHex[pos] = new Integer(fbit).byteValue();
            fbit = fbit >> 8;
        }
        return  byteVauesHex ;
    }



    /***
     * 将2个字节的 16进制数组 转化为 一个short
     * 计算算法来源 邹聪。
     * 高位在前，低位在后  的bytes数组
     */
    public static short bytes2ToShort(byte[] srcBytes) {
        short result;
        short v1 = (short) (srcBytes[0] & 0xFF);
        short v2 = (short) (srcBytes[1] & 0xFF);
        result = (short) (v1 * 256 + v2);
        return result;
    }

    /***
     * 将2个字节的 16进制数组 转化为 一个short <br>
     * 计算算法来源 邹聪。<br>
     * 低位在前，高位在后  的bytes数组
     */
    public static int bytes2ToShort_low_hight(byte[] src) {
        return ((src[0] & 0xFF)
                | ((src[ 1] & 0xFF) << 8)
        );
    }

    /***
     * 将2个字节的 16进制数组 转化为 一个short
     * 计算算法来源 邹聪。
     * 高位在前，低位在后  的bytes数组
     */
    public static double bytes2ToDouble(byte[] srcBytes) {
        double result;
        short v1 = (short) (srcBytes[0] & 0xFF);
        short v2 = (short) (srcBytes[1] & 0xFF);
        result = (double) (v1 * 256 + v2);
        return result;
    }

    /**
     * 两个byte转整形，add zc
     * @param res
     * @return
     */
    public static int byte2int(byte[] res) {
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00);
        return targets;
    }


    /***
     * int 类型 转为 2个字节长度的 byte数组。
     * 大端小端翻转 。
     * 邹聪提供。
     * @param intVaue
     * @param byteVauesHex
     */
    public static void intTo2ByteArrayHex(int intVaue, byte[] byteVauesHex)
    {
        for (int i = 0; i < byteVauesHex.length; i++) {
            byteVauesHex[i] = new Integer(intVaue & 0xff).byteValue();
            intVaue = intVaue >> 8;
        }

    }


    /***
     * int 类型 转为 2个字节长度的 byte数组。
     * 正常顺序。
     * @param intVaue
     */
    public static byte[]  intTo2ByteArrayHexNormal(int intVaue ) {
        byte[] byteVauesHex = new byte[2] ;
        for (int i = byteVauesHex.length - 1; i >= 0; i--) {
            byteVauesHex[i] = new Integer(intVaue & 0xff).byteValue();
            intVaue = intVaue >> 8;
        }
        return byteVauesHex;
    }

/**************************************************************************************************
 *   4 个字节的 byte 数组 转为 int
 ************************************************************************************************/
    /**
     * 将4个字节长度的 byte数组转为int。
     * 本方法适用于(低位在后，高位在前)的顺序
     */
    public static int bytes4HeightAndLowToInt(byte[] src, int offset) {
        return (((src[offset] & 0xFF) << 24)
                | ((src[offset + 1] & 0xFF) << 16)
                | ((src[offset + 2] & 0xFF) << 8)
                | (src[offset + 3] & 0xFF));
    }


    /**
     * 将4个字节长度的 byte数组转为int。<br>
     * 本方法适用于(低位在前，高位在后)的顺序<br>
     * 320项目中用到
     */
    public static int bytes4LowAndHeightToInt(byte[] src, int offset) {

        return ((src[offset] & 0xFF)
                | ((src[offset + 1] & 0xFF) << 8)
                | ((src[offset + 2] & 0xFF) << 16)
                | ((src[offset + 3] & 0xFF) << 24));
    }

    /**
     *  {(byte)0x00 ,(byte)0x0A}  ---> 10
     */
    public static short bytes2ToInt_low_height(byte[] srcBytes)
    {
        short result;
        short v1 = (short) (srcBytes[0] & 0xFF);
        short v2 = (short) (srcBytes[1] & 0xFF);
        result = (short) (v1 * 256 + v2);
        return result;
    }

    /**
     * 将1个字节长度的 byte数组转为int。
     */
    public static int bytes1ToInt(byte[] src) {
        return src[0] & 0xFF;
    }

    /**
     * 将一个字节长度的byte 转为 int
     *
     * @param src
     * @return
     */
    public static int byte1ToInt(byte src) {
        return src & 0xFF;
    }

    /***
     * 单个的十六进制 Str转为 int
     *
     * @param hexStr
     * @return
     */
    public static int hexOneToInt(String hexStr) {
        return Integer.parseInt(hexStr, 16);
    }

    /**
     * 数组截取
     *
     * @param srcByte 源数组
     * @param begin   源数组要复制的起始位置
     * @param count   要复制的长度
     * @return
     */
    public static byte[] subBytes(byte[] srcByte, int begin, int count) {
        byte[] result = new byte[count];
        System.arraycopy(srcByte, begin, result, 0, count);
        return result;
    }


//    /**
//     * 数组拷贝
//     */
//    public static byte[] subBytes2(byte[] srcByte, int srcPos , int destPos  , int count) {
//        byte[] dest = new byte[count];
//        System.arraycopy(srcByte, srcPos, dest,  destPos ,count);
//        return dest;
//    }

    /**
     * 查找列表中与目标值的绝对值最小的 数
     *
     * @param list   列表
     * @param tarNum 目标
     * @return 符合条件的数字在数组中的下标
     */
    public static int getNearestValueIndex(ArrayList<Integer> list, int tarNum) {
        //假设第一个最小
        int result = Math.abs(list.get(0) - tarNum);
        int index = 0;

        for (int i = 0; i < list.size(); i++) {

            int abVlue = Math.abs(list.get(i) - tarNum);
            if (result > abVlue) {
                result = abVlue;//将较小的保存
                index = i;
            }

        }//

        return index;
    }

    /***
     *   英制转公制
     *   1ft/s= 0.3048 m / s（米/秒）
     */
//    public double feetToMetric(double src)
//    {
//        return  src * 0.3048  ;
//    }

    /**
     * 公制转英制
     *
     * @return
     */
    public static float metricToFeet(float src) {
        return (float) (src / 0.3048);
    }


//    /**
//     * 字符串大小比较
//     * 相等： 0
//     * str1 > str2 :  正数
//     * str1 《 str2 :负数
//     * 、
//     */
//    public static int compareStr1ToStr2(String str1, String str2) {
//        return str1.compareTo(str2);
//
//    }

    /**
     版本比较
     * 1.3.7 格式的 <br>
     *     @param  newVersion 服务器新版本
     *     @param  oldVersion 本地机器旧版本
     */
    public static boolean  haveNewVersionFlag(String newVersion, String oldVersion)
    {
        //在没有读取过本地飞机的版本情况下，先联网获取到服务器版本
        if("N/A".equals(oldVersion))
        {
            return  false ;
        }
        if(newVersion .equals(oldVersion))
        {
            return  false ;
        }
        //1.3.7 格式
        String[] arrayNewer= newVersion.split("[.]") ;
        String[] arrayodler= oldVersion.split("[.]") ;

        for(int i=0 ;i< arrayNewer.length ;i++)
        {
            int newer= Integer.parseInt(arrayNewer[i].trim()) ;
            int older= Integer.parseInt(arrayodler[i].trim()) ;
            if( newer >  older  )
            {
                return  true ;
            }else if( newer < older){
                return  false ;
            }
        }
        return  false;
    }//



    /***
     * 指定数组中复制内容到新的数组
     *   arraycopy(Object src,byte srcPos,Object dest, byte destPos,byte length);
     src:源数组
     srcPos:源数组要复制的起始位置
     dest:目的数组
     destPos:目的数组放置的起始位置
     length:要复制的长度
     */
    public static void  copyDataToNewByteteArray(byte[] src, int srcPos , byte[] dest, int destPos , int length)
    {
        System.arraycopy(src, srcPos , dest, destPos, length);
    }



    public static int [] getNewArrayWithMoreSpaceInt( int [] oldArray , int [] increceData  )
    {
        int[] lastArray=new int[oldArray.length+increceData.length];//新数组
        System.arraycopy(oldArray, 0, lastArray, 0, oldArray.length);//将a数组内容复制新数组b

        for(int i=0 ;i<increceData.length ;i++)
        {
            lastArray[ oldArray.length + i] = increceData[i] ;
        }
        oldArray=lastArray;//改变引用
        return   oldArray ;
    }

    /**
     * 数组动态扩充容量 <br>
     * 代码并没有真正增加原数组a的容量，只是将a内容复制到新的大数组，<br>
     * 然后让a重新引用大数组。由于Java数组存贮在堆中，原a数组无需释放，会被自动回收。<br>
     */
    public static String [] getNewArrayWithMoreSpaceString( String [] oldArray , String [] increceData  )
    {
        String [] lastArray=new String[oldArray.length+increceData.length];//新数组
        System.arraycopy(oldArray, 0, lastArray, 0, oldArray.length);//将a数组内容复制新数组b

        for(int i=0 ;i<increceData.length ;i++)
        {
            lastArray[ oldArray.length + i] = increceData[i] ;
        }
        oldArray=lastArray;//改变引用
        return   oldArray ;
    }

    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */
    public static byte[] get8bitsOfOneByte(byte b)
    {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte)(b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    /**
     * 根据 时间戳 获取格式化的时间 。
     */
    public static String getFormatedTiimeByTimeStamp(long timeLong)
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); //常用的日期格式
        String dateStr=sdf.format(new Date(timeLong)); //日期类型--->String
        return  dateStr;
    }

    /**
     * 获取格式化的时间格式
     * @return 2017_05_31_16_23_31
     */
    public static String getCurrentDataStr()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss"); //常用的日期格式
        String dateStr=sdf.format(new Date()); //日期类型--->String
       return  dateStr;
    }

    /**
     * 获取格式化的时间格式
     * @return 2017-07-07 10:24
     */
    public static String getCurrentDataStrWithYear_day_hore_min()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); //常用的日期格式
        String dateStr=sdf.format(new Date()); //日期类型--->String
        return  dateStr;
    }


    /**
     * 获得校验位。
     * 计算规则：
     * SUM等于从该数据帧第一字节开始，也就是帧头开始，至该帧数据的最后一字节所有字节的和，
     * 只保留低八位，高位舍去。
     *   P1=s/256; //整除高8位s
     P0=s%256; //取余是低八
     */
    public static  byte[] getVerifyValue(byte [] lastBytes)
    {
        byte checksum =  0;

        for (int i = 0; i <= lastBytes.length-2; i++) {
            checksum += lastBytes[i];
        }

        checksum = (byte) (checksum % 256) ;
        lastBytes[lastBytes.length-1] = checksum;
        return  lastBytes ;
    }

    /**
     * 获取整个byte[]  -1 的校验位
     * @param lastBytes
     * @return
     */
    private static  byte  getVerifyValueOfAllBytes_1(byte [] lastBytes)
    {
        byte checksum =  0;

        for (int i = 0; i <= lastBytes.length-2; i++) {
            checksum += lastBytes[i];
        }

        checksum = (byte) (checksum % 256) ;
        return  checksum ;
    }
    /**
     * bytes转换成十六进制字符串
     */
    public static String byte2HexStrWithOutSpace(byte[] b) {
        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
//            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }


    /**
     * 16进制字符串转换为字符串
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "ASCII");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s.trim();
    }

    /**
     * ASCII码转为 十六进制
     */
    public static String asciiToHex(String str)
    {
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }


    /**
     * 十六进制转为ASCII码
     */
    public static String hexToAsicill(String hex)
    {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for( int i=0; i<hex.length()-1; i+=2 ){
            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }




    /**
     * 将字符串中的 逗号 转为 小数点
     * @return
     */
    public static String partDou2Point(String sorceStr)
    {
        sorceStr=sorceStr.replaceAll (",", ".");
        return  sorceStr ;
    }//


    /**
     * 校验飞机的合法性。
     * 收到数据后需要重新计算校验值 然后跟自带的校验
     */
    public static boolean checkTheDataWithverifyValue(byte[] bytes)
    {
        byte reslut = getVerifyValueOfAllBytes_1(bytes) ;
        byte reslut2 = bytes[bytes.length-1] ;
        return  reslut==reslut2 ? true : false ;
    }


    /**
     * 保留两位小数点
     * @param srcValue
     * @return
     */
    public static  String getDistanceFormedStr2(double srcValue)
    {
        DecimalFormat deformat2 = new DecimalFormat("#.##") ;
        String str =  deformat2.format(srcValue) ;
        str = MyStringUtils.partDou2Point(str) ;
//        MyLogUtils.mLog_iNormal("str===="+str +"  srcValue="+srcValue);
        return str   ;
    }

    public static  String getDistanceFormedStr(float srcValue)
    {
        DecimalFormat deformat2 = new DecimalFormat("#.###") ;
        String str =  deformat2.format(srcValue) ;
        str = MyStringUtils.partDou2Point(str) ;
        return str +" m" ;
    }


    public static String getExcepionStr(StackTraceElement[] stackTraceElements)
    {
        StringBuffer sb = new StringBuffer( ) ;
        for (int i = 0; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }



    /**
     * 获取byte的高四位
     */
    public static int gethe4OfByte(byte mByte)
    {
        int height;
        height = ((mByte & 0xf0) >> 4);
        return height;
    }

    /**
     * 获取byte的低四位
     */
    public static int getLow4OfByte(byte data)
    {
        int low;
        low = (data & 0x0f);
        return low;
    }
}
