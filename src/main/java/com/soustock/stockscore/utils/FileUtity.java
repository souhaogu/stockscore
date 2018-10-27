package com.soustock.stockscore.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtity {

    /**
     * check file exists
     * @param filePathStr
     * @return
     */
    public static boolean isExists(String filePathStr){
        File file = new File(filePathStr);
        return file.exists();
    }

    /**
     * get child file or dir name of dir
     * @param dirStr
     * @return
     */
    public static List<String> getChildFileNames(String dirStr){
        List<String> retList = new ArrayList<>();
        File dir = new File(dirStr);
        if (dir.exists()) {
            File[] childFileNames = dir.listFiles();
            for (File file: childFileNames){
                retList.add(file.getName());
            }
        }
        return retList;
    }
}
