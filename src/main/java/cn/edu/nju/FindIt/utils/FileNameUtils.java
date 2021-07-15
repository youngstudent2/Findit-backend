package cn.edu.nju.FindIt.utils;

import java.util.UUID;

public class FileNameUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取文件后缀
     * 
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * 
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName) {
        return getUUID() + FileNameUtils.getSuffix(fileOriginName);
    }
}
