package com.collect.any;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestFile2 {
    //使用字节流读取并写入文件，将一个文件复制到另一个文件中
    public static void main(String[] args) throws IOException {
        //要复制的源文件
        File f=new File("D:\\temp\\1.jpg");
        //目标文件
        File f2=new File("C:\\Users\\R07400\\Desktop\\1.jpg");
        //定义一个byte类型的数组，用于存储读取到的内容
        byte [] b=new byte[1024];
        
        int length;
        try {
            //定义读取的流
            InputStream in=new FileInputStream(f);
            //定义输出到文件的流
            OutputStream out=new FileOutputStream(f2);
            //将文件内容输出到另一个文件中
            while((length=in.read(b))!=-1){
                out.write(b, 0, length);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
