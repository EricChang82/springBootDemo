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
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import cn.utils.FileUtil;


public class Converter {
    public static void main(String[] args) {
        
        ArrayList<String> arrayList = new ArrayList<String>();
//        arrayList.add("-ver，-vers，-vert，-vol，-var-vor,-wer，-ver表示转拧");
        arrayList.add("-cern，-crim，-cert，-cris，-crit，-cree，-cret");
        
        for (int i = 0; i < arrayList.size(); i++) {
            String name =arrayList.get(i);
            convert(name);
        }
    }
    /**Purpose:
     * @author changle
     * Create Time: 2020年6月2日 
     * @param name
     * Version: 1.0
     */
    private static void convert(String name) {
        String filePath="D:\\工作\\单词兵法\\"+name+".csv";
        String desFilepath="D:\\工作\\单词兵法\\"+name+".txt";
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
            String word =temp.trim();
            if (word.contains("-")) {
                continue;
            }
            word =StringUtils.replaceAll(word, "\"", "");
            word =StringUtils.replaceAll(word, ",", "");
            newContentBuffer.append("+").append(word).append("\n");
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
