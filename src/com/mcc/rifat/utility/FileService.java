package com.mcc.rifat.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {


    public List<String> readFile(File file){
        List<String>lines = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine())!=null){
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
