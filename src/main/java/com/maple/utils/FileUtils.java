package com.maple.utils;

import com.maple.freemarker.CodeFile;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class FileUtils {
    /*
     * 写文件到硬盘
     */
    public static void writeCodeFile(String rootPath, List<CodeFile> filelist) throws Exception {
        File root = new File(rootPath);
        if (!root.exists()) {
            root.mkdir();
        } else {
            org.apache.commons.io.FileUtils.deleteDirectory(root);
            root.mkdir();
        }
        String separator = File.separator;
        for ( CodeFile file : filelist ) {
            String path = file.getPath().replace(".", separator);
            path = rootPath + separator + path;
            File pathFile = new File(path);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            path += separator + file.getName();
            OutputStream os = new FileOutputStream(new File(path));
            IOUtils.write(file.getContent(), os);
            IOUtils.closeQuietly(os);
        }
    }
    
    /*
     * 输出到控制台
     */
    public static void printCodeFile(List<CodeFile> filelist) {
        for ( CodeFile file : filelist ) {
            System.out.println("---------------------------------------------------------");
            System.out.println(file.getPath() + "///" + file.getName());
            System.out.println(file.getContent());
        }
    }
}
