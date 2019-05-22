package com.alibaba.excel.util;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.Enumeration;

/**
 *
 * @author jipengfei
 * @date 2017/06/22
 */
public class POITempFile {

    private static final String JAVA_IO_TMPDIR = "java.io.tmpdir";

    private static final String POIFILES = "poifiles";

    private static final String EASY_EXCEL_FILES = "easyexcel";

    /**
     */
    public static void createPOIFilesDirectory() {

        String tmpDir = System.getProperty(JAVA_IO_TMPDIR);
        if (tmpDir == null) {
            throw new RuntimeException(
                "Systems temporary directory not defined - set the -D" + JAVA_IO_TMPDIR + " jvm property!");
        }
        File directory = new File(tmpDir, POIFILES);
        if (!directory.exists()) {
            syncCreatePOIFilesDirectory(directory);
        }

    }

    /**
     * 获取环境变量的配置
     * @return easyexcel临时目录
     */
    public static String getEasyExcelTmpDir() {
        String tmpDir = System.getProperty(JAVA_IO_TMPDIR);
        if (tmpDir == null) {
            throw new RuntimeException(
                    "Systems temporary directory not defined - set the -D" + JAVA_IO_TMPDIR + " jvm property!");
        }
        File directory = new File(tmpDir, EASY_EXCEL_FILES);
        if (!directory.exists()) {
            syncCreatePOIFilesDirectory(directory);
        }
        return tmpDir + File.separator + EASY_EXCEL_FILES;
    }


    /**
     *
     * @param directory
     */
    private static synchronized void syncCreatePOIFilesDirectory(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private static final int BUF = 4096;

    public static boolean writeFile(File file, InputStream stream) throws FileNotFoundException {
        OutputStream o = null;
        try {
            makeDirs(file.getAbsolutePath());
            if (!file.exists()) {
                file.createNewFile();
                file.deleteOnExit();
            }

            o = new FileOutputStream(file);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            try {
                o.close();
            } catch (IOException e) {

            }
        }
    }

    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (folderName == null || "".equals(folderName)) {
            return false;
        }
        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    public static String getFolderName(String filePath) {

        if (filePath == null || "".equals(filePath)) {
            return filePath;
        }
        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     * 文件解压
     * @param path
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean doUnZip(String path, File file) throws IOException {
        ZipFile zipFile = new ZipFile(file, "utf-8");
        Enumeration<ZipArchiveEntry> en = zipFile.getEntries();
        ZipArchiveEntry ze;
        while (en.hasMoreElements()) {
            ze = en.nextElement();
            if(ze.getName().contains("../")){
                //防止目录穿越
                throw new IllegalStateException("unsecurity zipfile!");
            }
            File f = new File(path, ze.getName());
            if (ze.isDirectory()) {
                f.mkdirs();
                continue;
            } else { f.getParentFile().mkdirs(); }

            InputStream is = zipFile.getInputStream(ze);
            OutputStream os = new FileOutputStream(f);
            IOUtils.copy(is, os, BUF);
            is.close();
            os.close();
        }
        zipFile.close();
        return true;
    }

    public static void deletefile(String delpath) {
        File file = new File(delpath);
        // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
        if (!file.isDirectory()) {
            file.delete();
        } else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File delfile = new File(delpath + File.separator + filelist[i]);
                if (!delfile.isDirectory()) {
                    delfile.delete();
                } else if (delfile.isDirectory()) {
                    deletefile(delpath + File.separator + filelist[i]);
                }
            }
            file.delete();
        }
    }

    /**
     * xml解析
     * @param inputStream
     * @param contentHandler
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void parseXML(InputStream inputStream, ContentHandler contentHandler)
            throws ParserConfigurationException, SAXException, IOException {
        InputSource sheetSource = new InputSource(inputStream);
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        //防止XML实体注注入
        saxFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        saxFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        saxFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(contentHandler);
        xmlReader.parse(sheetSource);
    }
}
