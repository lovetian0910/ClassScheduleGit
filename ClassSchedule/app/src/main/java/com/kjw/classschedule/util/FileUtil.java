package com.kjw.classschedule.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by jwkuang on 2016/9/14.
 */
public class FileUtil {
    public static String readFileContent(File file){
        FileReader fileReader = null;
        BufferedReader bf = null;
        try {
            fileReader = new FileReader(file);
            bf = new BufferedReader(fileReader);
            String content = "";
            StringBuilder sb = new StringBuilder();
            while(content != null){
                sb.append(content);
                content = bf.readLine();
            }
            bf.close();
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if(bf != null){
                    bf.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void writeString2File(String filename, String content){
        BufferedWriter bw = null;
        try {
            File file = new File(filename);
            File dir = new File(file.getParent());
            if(!dir.exists()){
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(bw != null){
                try{
                    bw.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
