package com.mcc.rifat.utility;


import com.mcc.rifat.model.Conversation;
import com.sun.xml.internal.bind.v2.model.core.TypeRef;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.util.DefaultPrettyPrinter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class JsonService {

    private FileService fileService = new FileService();
    public void writeJsonFile(File jsonFile, Object object){

        ObjectWriter objectWriter = new ObjectMapper().writer(new DefaultPrettyPrinter());
        try {
            objectWriter.writeValue(jsonFile,object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Conversation readConversaionJsonFile(File conversationJsonFile){
        byte[] contents = fileService.readAllBytes(conversationJsonFile);
        Conversation conversation  = new Conversation();
        try {
            conversation = new ObjectMapper().readValue(contents,Conversation.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conversation;
    }
}
