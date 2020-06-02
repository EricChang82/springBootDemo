/**
 * springBootDemo
 * @author changle
 * Create Time: 2020年6月2日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.jinshancb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import cn.utils.FileUtil;


public class Converter {
    public static void main(String[] args) {
        String filePath="E:\\1工作区\\ver2.txt";
        String desFilepath="E:\\\\1工作区\\\\ver2_converted.txt";
        StringBuffer content=getConvertedContentOfFile(filePath);
        FileUtil.writeContentToFile(desFilepath,content);
        System.out.println("写入完成");
    }
    public static StringBuffer getConvertedContentOfFile(String filePath) {
        StringBuffer newContentBuffer = new StringBuffer();
        try {
        File propfile = new File(filePath);
        //read file 
        FileInputStream fis = new FileInputStream(propfile);
        
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String temp="";
        while((temp = br.readLine()) != null ) {
            newContentBuffer.append("+").append(temp.trim()).append("\n");
            newContentBuffer.append("#").append("\n");
            newContentBuffer.append("@").append("\n");
            newContentBuffer.append("&").append("\n");
            newContentBuffer.append("$1").append("\n");
        }
        fis.close();
        isr.close();
        br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newContentBuffer;
    } 
}
