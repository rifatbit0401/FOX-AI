package com.mcc.rifat.utility;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.util.DefaultPrettyPrinter;

import java.io.File;
import java.io.IOException;

public class JsonService {

    public void writeJsonFile(File jsonFile, Object object){

        ObjectWriter objectWriter = new ObjectMapper().writer(new DefaultPrettyPrinter());
        try {
            objectWriter.writeValue(jsonFile,object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
