package com.soustock.stockscore.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CsvFileReader {
    private TxtFileReader txtFileReader;

    private Map<String, Integer> fieldPosMap = new HashMap();
    public CsvFileReader(String filePathStr) throws IOException {
        txtFileReader = new TxtFileReader(filePathStr);
        String lineStr = txtFileReader.readLine();
        if (lineStr != null){
            String[] field_arr = lineStr.split(",");
            for (int i = 0; i < field_arr.length; i ++){
                fieldPosMap.put(field_arr[i], i);
            }
        }
    }

    private Map<String, String> rowCache = new HashMap<>();
    public boolean nextRow() throws IOException {
        rowCache.clear();
        String lineStr = txtFileReader.readLine();
        while (lineStr != null){
            String[] var_arr = lineStr.split(",");
            if (var_arr.length == fieldPosMap.size()){
                Iterator varIter = this.fieldPosMap.entrySet().iterator();
                while(varIter.hasNext()) {
                    Map.Entry<String, Integer> entry = (Map.Entry)varIter.next();
                    rowCache.put(entry.getKey(), var_arr[entry.getValue()]);
                }
                return true;
            }
            lineStr = txtFileReader.readLine();
        }
        return false;
    }

    public String readField(String fieldName) {
        return rowCache.get(fieldName);
    }

    public void close() throws IOException {
        txtFileReader.close();
    }
}
