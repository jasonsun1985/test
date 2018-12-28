package com.collect.any;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteAppendFile {
	public static void main(String[] args) {
			WriteAppendFile writeAppendFile = new WriteAppendFile();
			writeAppendFile.writeLog("追加测试\r\n", "d:\\cmd.txt");
	}
	public void writeLog(String info,String filePath){
        BufferedWriter out = null;
        try {  
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));  
            out.write(info);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        }
	}
}
