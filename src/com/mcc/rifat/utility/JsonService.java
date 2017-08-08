package com.mcc.rifat.utility;


import com.mcc.rifat.model.ConversationJsonModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.util.DefaultPrettyPrinter;

import java.io.File;
import java.io.IOException;

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

    public ConversationJsonModel readConversaionJsonFile(File conversationJsonFile){
        byte[] contents = fileService.readAllBytes(conversationJsonFile);
        ConversationJsonModel conversationJsonModel = new ConversationJsonModel();
        try {
            conversationJsonModel = new ObjectMapper().readValue(contents,ConversationJsonModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conversationJsonModel;
    }
}
