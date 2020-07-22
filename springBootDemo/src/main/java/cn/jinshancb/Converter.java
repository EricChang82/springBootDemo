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
        arrayList.add("26,-act，-ag，-ig");

        for (int i = 0; i < arrayList.size(); i++) {
            String name = arrayList.get(i);
            convert(name);
        }
    }

    /**Purpose:
     * @author changle
     * Create Time: 2020年7月21日 
     * @param word
     * @return
     * Version: 1.0
     */
    private static String processLine(String word) {
        word = StringUtils.replaceAll(word, "\"", "");
        word = StringUtils.replaceAll(word, ",", "");
        word = StringUtils.replaceAll(word, "▽", "");
        word = StringUtils.replaceAll(word, "★", "");
        word = StringUtils.replaceAll(word, "v ", "");
        word = StringUtils.replaceAll(word, "►", "");
        word = StringUtils.replaceAll(word, "7", "");
        word = StringUtils.replaceAll(word, "\\\\", "");
        word = StringUtils.replaceAll(word, "CET4", "");
        word = StringUtils.replaceAll(word, "NETM", "");
        word = StringUtils.replaceAll(word, "\\*", "");
        word = StringUtils.replaceAll(word, "余", "");
        word = StringUtils.replaceAll(word, "IELTS", "");
        word = StringUtils.replaceAll(word, "CET4", "");
        word = StringUtils.replaceAll(word, "CET6", "");
        word = StringUtils.replaceAll(word, " ", "");
        word = StringUtils.replaceAll(word, "俞", "");
        word = StringUtils.replaceAll(word, "食", "");

        return word;
    }

    /**Purpose:
     * @author changle
     * Create Time: 2020年6月2日 
     * @param name
     * Version: 1.0
     */
    private static void convert(String name) {
        String filePath = "D:\\工作\\单词兵法\\" + name + ".txt";
        String desFilepath = "D:\\工作\\单词兵法\\" + name + "_p1.txt";
        StringBuffer content = getConvertedContentOfFile(filePath, false);
        FileUtil.writeContentToFile(desFilepath, content);
        desFilepath = "D:\\工作\\单词兵法\\" + name + "_p2.txt";
        content = getConvertedContentOfFile(filePath, true);
        FileUtil.writeContentToFile(desFilepath, content);
        System.out.println("写入完成");
    }

    public static StringBuffer getConvertedContentOfFile(String filePath, boolean isFinal) {
        StringBuffer newContentBuffer = new StringBuffer();
        try {
            File propfile = new File(filePath);
            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                String word = temp.trim();
                word = processLine(word);
                if (word.trim().equals("") || word.contains("-")) {
                    continue;
                }
                if (isFinal) {
                    newContentBuffer.append("+").append(word).append("\n");
                    newContentBuffer.append("#").append("\n");
                    newContentBuffer.append("@").append("\n");
                    newContentBuffer.append("&").append("\n");
                    newContentBuffer.append("$1").append("\n");
                } else {
                    newContentBuffer.append(word).append("\n");
                }

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
