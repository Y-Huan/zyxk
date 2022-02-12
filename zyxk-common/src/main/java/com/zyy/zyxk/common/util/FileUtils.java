package com.zyy.zyxk.common.util;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    private static String [] imgTypes = {"jpg","png","jpeg","bmp","gif","webp"};

    private static String [] excelTypes = {"xls","xlsx"};

    public static String getFileTypeByFileName(String fileName) {
        if (fileName == null || fileName.length() == 0) {
            return null;
        }
        String suffix = getFileSuffixByFileName(fileName).toLowerCase();
        for (String str:imgTypes) {
            if (suffix.contains(str)) {
                return "image";
            }
        }
        //TODO
        return "TODO";

    }

    public static String getFileSuffixByFileName(String fileName) {
        return getFileSuffixByFileName(fileName,".jpg");
    }

    public static String getFileSuffixByFileName(String fileName,String defaultSuffix) {
        if (!StringUtils.isEmpty(fileName)) {
            int start = fileName.lastIndexOf(".");
            return fileName.substring(start);
        } else {
            return defaultSuffix;
        }
    }

    public static File createFile(String folder, String suffix) {
        return createFile(folder,null,suffix);
    }

    public static File createFile(String folder,String fileName,String surffix) {
        if (StringUtils.isEmpty(fileName)) {
            fileName = UUIDUtils.getUUID();
        }
        fileName = fileName + surffix;
        File file = new File(buildFilePath(folder,fileName));
        File parent = file.getParentFile();
        if (file.exists()) {
            return createFile(folder,surffix);
        } else {
            if (!parent.exists()){
                parent.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;
        }
    }

    public static String getCreateFileName(String suffix) {
        return UUIDUtils.getUUID() + suffix;
    }

    public static String buildFilePath(String folder,String fileName) {
        String md5 = EncryptUtil.md5(fileName);
        StringBuffer filePath = new StringBuffer(100);
        filePath.append(folder);
        filePath.append(File.separator);
        filePath.append(md5.substring(0,2));
        filePath.append(File.separator);
        filePath.append(md5.substring(2,4));
        filePath.append(File.separator);
        filePath.append(md5.substring(4,6));
        filePath.append(File.separator);
        filePath.append(md5.substring(6,8));
        filePath.append(File.separator);
        filePath.append(fileName);
        return filePath.toString();
    }

    public static boolean isExcel(String suffix) {
        if (StringUtils.isEmpty(suffix)) {
            return false;
        }
        suffix = suffix.toLowerCase();
        for (String t:excelTypes) {
            if (suffix.contains(t)) {
                return true;
            }
        }
        return false;
    }
}
