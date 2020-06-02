package cn.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cn.Util;

public class FileUtil {

    /**
     * Purpose:创建文件
     * @author changl
     * Create Time: 2015年11月20日 上午11:15:40
     * @param filePathAndName
     * @param fileContent
     * Version: 1.0
     */
    public static void createNewFile(String filePathAndName, String fileContent) {
        try {
            // 创建File对象，参数为String类型，表示目录名
            File myFile = new File(filePathAndName);
            // 判断文件是否存在，如果不存在则调用createNewFile()方法创建新目录，否则跳至异常处理代码
            if (!myFile.exists()) {
                myFile.createNewFile();
            } else {
                throw new Exception("The new file already exists!");
            }
            FileWriter resultFile = new FileWriter(myFile);
            PrintWriter myNewFile = new PrintWriter(resultFile);
            myNewFile.println(fileContent);
            resultFile.close(); // 关闭文件写入�?
        } catch (Exception ex) {
            System.out.println("无法创建新文件！");
            ex.printStackTrace();
        }
    }

    /**
     * Purpose: 例如：aabbcc123 以aa卡头，cc结尾，追加456。 调用后结果:aabbcc456
     * @author changl
     * Create Time: 2015年11月20日 上午11:29:50
     * @param filePath
     * @param replaceMark_Start 开始位置
     * @param replaceMark_End 结束位置
     * @param appentNewContent 结束位置后追加为新的内容
     * @param isPrintToConsole
     * Version: 1.0
     */
    public static void ModifyContentOfLine(String filePath, String replaceMark_Start, String replaceMark_End, String appentNewContent, boolean isPrintToConsole) {
        try {
            File propfile = new File(filePath);

            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (temp.trim().startsWith(replaceMark_Start)) {
                    //
                    String newTemp = temp.substring(temp.indexOf(replaceMark_Start), temp.indexOf(replaceMark_End) + replaceMark_End.length()) + appentNewContent;
                    //
                    buf.append(newTemp).append("\r\n");
                } else {
                    buf.append(temp).append("\r\n");
                }
            }
            fis.close();
            isr.close();
            br.close();
            if (isPrintToConsole) {
                Util.print(buf.toString());
            }
            File propfile_result = new File(filePath);
            FileOutputStream fos = new FileOutputStream(propfile_result);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:将内容根据起始，终止位置写入文件�?
     * @author changl
     * Create Time: 2015�?�?7�?下午4:52:52
     * @param classNameFilePath
     * @param writeValueBuf
     * Version: 1.0
     * @param endMark 
     * @param beginMark 
     * @throws Exception 
     */
    public static void replaceContentOfFile_FullLine(String filePath, String replaceMark, String replaceValue, boolean isPrintToConsole) {
        try {
            File propfile = new File(filePath);

            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (temp.trim().startsWith(replaceMark)) {
                    buf.append(replaceValue).append("\r\n");
                } else {
                    buf.append(temp).append("\r\n");
                }
            }
            fis.close();
            isr.close();
            br.close();
            if (isPrintToConsole) {
                Util.print(buf.toString());
            }
            File propfile_result = new File(filePath);
            FileOutputStream fos = new FileOutputStream(propfile_result);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:替换文件中的指定内容
     * @author changl
     * Create Time: 2016年6月17日 下午4:05:23
     * @param filePath
     * @param replaceMark
     * @param replaceValue
     * @param isPrintToConsole
     * Version: 1.0
     */
    public static void replaceContentOfFile(String filePath, String formerStr, String toStr, boolean isPrintToConsole) {
        try {
            File propfile = new File(filePath);

            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (temp.contains(formerStr)) {
                    String toString = temp.replaceAll(formerStr, toStr);
                    buf.append(toString).append("\r\n");
                } else {
                    buf.append(temp).append("\r\n");
                }
            }
            fis.close();
            isr.close();
            br.close();
            if (isPrintToConsole) {
                Util.print(buf.toString());
            }
            File propfile_result = new File(filePath);
            FileOutputStream fos = new FileOutputStream(propfile_result);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:根据指定prefix字符串，获得特定的行
     * @author changl
     * Create Time: 2016年6月17日 下午2:35:11
     * @param filePath
     * @param replaceMark
     * @param replaceValue
     * @param isPrintToConsole
     * Version: 1.0
     */
    public static String getContentSpecStart(String filePath, String prefix) {
        try {
            File propfile = new File(filePath);

            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (temp.trim().startsWith(prefix)) {
                    return temp.trim();
                }
            }
            fis.close();
            isr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Purpose:根据指定prefix字符串，获得特定的行
     * @author changl
     * Create Time: 2016年6月17日 下午2:35:11
     * @param filePath
     * @param replaceMark
     * @param replaceValue
     * @param isPrintToConsole
     * Version: 1.0
     */
    public static String getContentOfFile(String filePath) {
        StringBuffer contentBuffer = new StringBuffer();
        try {
            File propfile = new File(filePath);
            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                contentBuffer.append(temp);
                contentBuffer.append("\n");
            }
            fis.close();
            isr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentBuffer.toString();
    }

    /**
     * Purpose:只替换第一次出现的
     * @author changl
     * Create Time: 2015年12月29日 上午10:14:26
     * @param filePath
     * @param replaceMark
     * @param replaceValue
     * @param isPrintToConsole
     * Version: 1.0
     */
    public static void replaceContentOfFile_FullLine_First(String filePath, String replaceMark, String replaceValue, boolean isPrintToConsole) {
        try {
            File propfile = new File(filePath);
            Boolean flag = true;
            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (flag && temp.trim().startsWith(replaceMark)) {
                    buf.append(replaceValue).append("\r\n");
                    flag = false;
                } else {
                    buf.append(temp).append("\r\n");
                }
            }
            fis.close();
            isr.close();
            br.close();
            if (isPrintToConsole) {
                Util.print(buf.toString());
            }
            File propfile_result = new File(filePath);
            FileOutputStream fos = new FileOutputStream(propfile_result);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean cleanFile(String filePath) {
        try {
            File propfile_result = new File(filePath);
            FileOutputStream fos = new FileOutputStream(propfile_result);
            PrintWriter pw = new PrintWriter(fos);
            pw.write("");
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Purpose:获得包含指定内容的整个行
     * @author changl
     * Create Time: 2015年11月20日 上午11:53:57
     * @param filePath
     * @param conten
     * @param isPrintToConsole
     * Version: 1.0
     */
    public static String getFullLineWithContent(String filePath, String content, boolean isPrintToConsole) {
        String temp = "";
        try {
            File propfile = new File(filePath);

            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            while ((temp = br.readLine()) != null) {
                if (temp.trim().indexOf(content) >= 0) {
                    break;
                }
            }
            fis.close();
            isr.close();
            br.close();
            if (isPrintToConsole) {
                Util.print(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Util.isNull(temp)) {
            System.out.println("getFullLinewithContent方法未找到指定内容：" + content);
        }
        return temp;
    }

    /**
     * Purpose:将内容根据起始，终止位置写入文件�?
     * @author changl
     * Create Time: 2015�?�?7�?下午4:52:52
     * @param classNameFilePath
     * @param writeValueBuf
     * Version: 1.0
     * @param endMark 
     * @param beginMark 
     * @throws Exception 
     */
    public static void writeContentToFile(String filePath, StringBuffer writeValueBuf, String beginMark, String endMark) {
        try {
            boolean startFlag = false;
            boolean completeFlag = false;
            File propfile = new File(filePath);

            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (!completeFlag && temp.startsWith(beginMark)) {
                    startFlag = true;
                }
                if (startFlag && temp.startsWith(endMark)) {
                    //			buf.append("\r\n");
                    buf.append(writeValueBuf).append("\r\n");
                    startFlag = false;
                    completeFlag = true;
                }

                buf.append(temp).append("\r\n");
            }
            fis.close();
            isr.close();
            br.close();
            //		Util.print(buf.toString());
            File propfile_result = new File(filePath);
            FileOutputStream fos = new FileOutputStream(propfile_result);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:将内容写入文件
     * @author changle
     * Create Time: 2020年6月2日 
     * @param filePath
     * @param buf
     * Version: 1.0
     */
    public static void writeContentToFile(String filePath, StringBuffer buf) {
        try {
            File propfile_result = new File(filePath);
            FileOutputStream fos = new FileOutputStream(propfile_result);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:将内容加大文件的最前面
     * @author changl
     * Create Time: 2015年11月20日 下午3:18:31
     * @param filePath
     * @param writeValueBuf
     * @param beginMark
     * @param endMark
     * Version: 1.0
     */
    public static void writeContentToFileHeader(String filePath, StringBuffer writeValueBuf) {
        try {
            boolean startFlag = true;
            File propfile = new File(filePath);

            //read file 
            FileInputStream fis = new FileInputStream(propfile);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (startFlag) {
                    buf.append(writeValueBuf).append("\r\n");
                    startFlag = false;
                }
                buf.append(temp).append("\r\n");
            }
            fis.close();
            isr.close();
            br.close();
            //		Util.print(buf.toString());
            File propfile_result = new File(filePath);
            FileOutputStream fos = new FileOutputStream(propfile_result);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:列出目录下的所有文件夹、文件名称
     * @author changl
     * Create Time: 2015年11月24日 下午5:06:13
     * @param path
     * @return
     * Version: 1.0
     */
    public static String[] listDirs(String path) {
        File file = new File(path);
        String[] dirs = file.list();
        return dirs;
    }

    //	  public static void deleteFold(String path) { 
    //		    File dir = new File(path);  
    //          String[] file = dir.list();  
    //          for (int i = 0; i < file.length; i++) {  
    //          	String path1= file[i];
    //        
    //          }
    //       
    //	  }
    public static void moveFolderWithOutSelf(String oldPath, String newPath) {
        File dir = new File(oldPath);
        String[] file = dir.list();
        for (int i = 0; i < file.length; i++) {
            String path = file[i];
            copyFolderWithSelf(oldPath + path, newPath);
        }
        deleteDir(new File(oldPath));//删除原文件
    }

    public static void copyFolderWithOutSelf(String oldPath, String newPath) {
        File dir = new File(oldPath);
        String[] file = dir.list();
        for (int i = 0; i < file.length; i++) {
            String path = file[i];
            copyFolderWithSelf(oldPath + "\\" + path, newPath);
        }
    }

    /** 
     * 复制整个文件夹的内容(含自身) 
     * @param oldPath 准备拷贝的目录 
     * @param newPath 指定绝对路径的新目录 
     * @return 
     */
    public static void copyFolderWithSelf(String oldPath, String newPath) {
        try {
            File dir = new File(oldPath);
            if (!dir.exists()) {
                System.out.println(oldPath + " 文件不存在");
                return;
            }
            File newPathFile = new File(newPath);

            //修复bug 2016年8月19日
            if (!newPathFile.exists()) {
                new File(newPath).mkdirs(); //如果文件夹不存在 则建立新文件夹  
            }

            if (dir.isDirectory()) {
                if (!dir.exists()) {
                    new File(newPath).mkdirs(); //如果文件夹不存在 则建立新文件夹   
                }

            } else {

            }

            // 目标   
            String originalNewFolder = newPath;
            newPath += File.separator + dir.getName();
            File moveDir = new File(newPath);
            if (dir.isDirectory()) {
                if (!moveDir.exists()) {
                    moveDir.mkdirs();
                }

                String[] file = dir.list();
                File temp = null;
                for (int i = 0; i < file.length; i++) {
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + file[i]);
                    } else {
                        temp = new File(oldPath + File.separator + file[i]);
                    }
                    //                if("lib".equals(file[i])){
                    //                    continue;
                    //                }
                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                        byte[] b = new byte[1024 * 5];
                        int len;
                        while ((len = input.read(b)) != -1) {
                            output.write(b, 0, len);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                    if (temp.isDirectory()) { //如果是子文件夹   
                        copyFolderWithSelf(oldPath + "/" + file[i], newPath);
                    }
                }
            } else {

                /*
                 * FileInputStream input = new FileInputStream(oldPath);
                 * FileOutputStream output = new FileOutputStream(newPath);
                 * byte[] b = new byte[1024 * 5]; int len; while ((len =
                 * input.read(b)) != -1) { output.write(b, 0, len); }
                 * output.flush(); output.close(); input.close();
                 */
                FileUtil.copyFileToNewPath(oldPath, originalNewFolder);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:复制文件到新的文件
     * @author changl
     * Create Time: 2016年6月17日 上午11:43:56
     * @param oldFile
     * @param newPath
     * Version: 1.0
     */
    public static void copyFileToNewPath(String oldFile, String newPath) {
        try {
            File temp = new File(oldFile);

            if (temp.isFile()) {
                FileInputStream input = new FileInputStream(temp);
                FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = input.read(b)) != -1) {
                    output.write(b, 0, len);
                }
                output.flush();
                output.close();
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:复制一个文件的内容到另一个文件中
     * @author changl
     * Create Time: 2016年3月17日 下午4:38:15
     * @param oldPath
     * @param newPath
     * Version: 1.0
     */
    public static void copyFileOverWriteOther(String oldPath, String newPath) {
        try {
            File temp = new File(oldPath);

            if (temp.isFile()) {
                FileInputStream input = new FileInputStream(temp);
                FileOutputStream output = new FileOutputStream(newPath);
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = input.read(b)) != -1) {
                    output.write(b, 0, len);
                }
                output.flush();
                output.close();
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteDir(String path, boolean withSelf) {
        File dir = new File(path);
        if (withSelf) {
            deleteDir(dir);
        } else {
            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    String subPath = children[i];
                    deleteDir(new File(path + File.separator + subPath));
                }
            }
        }

    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {

        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            //            System.out.println("删除1："+dir);
            return dir.delete();
        }
        // 目录此时为空，可以删除
        //        System.out.println("删除："+dir);
        return dir.delete();
    }

    // 得到该文件夹，及其所有子文件夹下的所有目标文�?
    public static List<File> getAllFiles(File file, boolean isContentSubDirFiles, boolean isContentSubDirPath) {
        List<File> valueFiles = new ArrayList<File>();
        File[] fs = file.listFiles();
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].isDirectory() && isContentSubDirFiles) {
                // 递归得到每个子文件夹下的目标文件
                valueFiles.addAll(getAllFiles(fs[i], true, false));
            }
        }
        // 把file文件夹下的目标文件放进去
        valueFiles.addAll(Arrays.asList(getFiles(file, isContentSubDirPath)));
        return valueFiles;
    }

    /**
     * Purpose:获得目录下所有文件的路径
     * @author changl
     * Create Time: 2016年8月17日 下午1:53:55
     * @param file
     * @param isContentSubDirFiles
     * @param isContentSubDirPath
     * @return
     * Version: 1.0
     */
    public static String getAllFilesPath(String pathname) {
        File file = new File(pathname);
        List<File> valueFiles = new ArrayList<File>();
        File[] fs = file.listFiles();
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].isDirectory()) {
                // 递归得到每个子文件夹下的目标文件
                valueFiles.addAll(getAllFiles(fs[i], true, false));
            }
        }
        // 把file文件夹下的目标文件放进去
        valueFiles.addAll(Arrays.asList(getFiles(file, true)));
        StringBuffer retBuffer = new StringBuffer();
        for (int i = 0; i < valueFiles.size(); i++) {
            if (valueFiles.get(i).isDirectory()) {
                continue;
            }
            retBuffer.append(valueFiles.get(i).getPath());
            retBuffer.append("\n");
        }
        return retBuffer.toString();
    }

    // 得到�?��文件夹下的目标文件，不包括子文件�?
    public static File[] getFiles(File file, final boolean isContentSubDirPath) {
        // 图个方便，用匿名类了
        FileFilter filter = new FileFilter() {
            //			String regex = "\\w*\\.jsp";

            public boolean accept(File pathname) {
                if (isContentSubDirPath) {
                    return isContentSubDirPath;
                }
                if (pathname.isDirectory()) {
                    return false;
                } else {
                    return true;
                }
                //				return pathname.getName().matches(regex);// 文件后缀�?jsp
            }
        };
        File[] fs = file.listFiles(filter);
        //		File[] fs = file.listFiles();
        return fs;
    }

    /**
     * Purpose:比较两个文件是否相同
     * @author changl
     * Create Time: 2016年3月17日 下午4:41:01
     * @param localdbconfigfile
     * @param usingconfigfile
     * @return
     * Version: 1.0
     */
    public static boolean compareFiles(String flie1, String flie2) {
        boolean flag = true;
        FileInputStream input1 = null;
        FileInputStream input2 = null;
        try {
            File temp1 = new File(flie1);
            input1 = new FileInputStream(temp1);
            File temp2 = new File(flie2);
            input2 = new FileInputStream(temp2);

            if (temp1.isFile() && temp2.isFile()) {
                int len1 = input1.available();
                int len2 = input2.available();
                if (len1 != len2) {
                    return false;
                } else {
                    byte[] data1 = new byte[len1];
                    byte[] data2 = new byte[len2];
                    input1.read(data1);
                    input2.read(data2);
                    for (int i = 0; i < len1; i++) {
                        if (data1[i] != data2[i]) {
                            //                    			     System.out.println("文件内容不一样");     
                            //                    			     return false;     
                            flag = false;

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //
            try {
                if (input1 != null) {
                    input1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //
            try {
                if (input2 != null) {
                    input2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return flag;

    }

    /**
     * Purpose:获得文件名称
     * @author changl
     * Create Time: 2016年6月17日 上午10:37:28
     * @param apiFile
     * Version: 1.0
     */
    public static String getFileName(String path) {
        File file = new File(path);
        return getFileName(file);
    }

    public static String getFileName(File file) {
        if (file.exists()) {
            return file.getName();
        } else {
            return "ERROR文件不存在";
        }

    }

    /**
     * Purpose:替换文件名中的内容,包含子文件夹(只改文件名)
     * @author changl
     * Create Time: 2016年6月17日 上午10:45:19
     * Version: 1.0
     * @param toChar 
     * @param fmChar 
     * @param objectFilePath 
     */
    public static void replaceCharacterOfFileName(String objectFilePath, String fmChar, String toChar) {
        try {
            File file = new File(objectFilePath);
            String fileName = FileUtil.getFileName(file);

            if (!file.exists()) {
                System.out.println(objectFilePath + " 文件不存在");
                return;
            }
            // 目标   
            if (file.isDirectory()) {
                File[] fileNames = file.listFiles();
                for (int i = 0; i < fileNames.length; i++) {
                    String path = fileNames[i].getAbsolutePath();
                    replaceCharacterOfFileName(path, fmChar, toChar);
                }
            } else {
                String newFileNameString = fileName.replace(fmChar, toChar);
                String filePath = file.getAbsolutePath();
                String newFilePath = filePath.replace(fileName, newFileNameString);
                File newFile = new File(newFilePath);
                file.renameTo(newFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Purpose:遍历文件夹下所有文件。根据map中的K,V进行替换
     * @author changl
     * Create Time: 2016年6月17日 下午3:07:11
     * @param objectFilePath
     * @param replaceKVMap
     * Version: 1.0
     */
    public static void replaceContentOfFolder(String objectFilePath, HashMap<String, String> replaceKVMap) {

        try {
            File file = new File(objectFilePath);
            if (!file.exists()) {
                System.out.println(objectFilePath + " 文件不存在");
                return;
            }
            // 目标   
            if (file.isDirectory()) {
                File[] fileNames = file.listFiles();
                for (int i = 0; i < fileNames.length; i++) {
                    String path = fileNames[i].getAbsolutePath();
                    replaceContentOfFolder(path, replaceKVMap);
                }
            } else {
                Iterator<String> iterator = replaceKVMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String classNameString_key = (String) iterator.next();
                    String fileName_value = replaceKVMap.get(classNameString_key);
                    replaceContentOfFile(file.getAbsolutePath(), classNameString_key, fileName_value, false);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
