package com.mcc.rifat.main;

import com.mcc.rifat.model.Conversation;
import com.mcc.rifat.utility.JsonService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        String conversationJsonFilePath = "";
        File jsonFile = new File("/home/rifat/Desktop/Data/test.json");
        Conversation conversation = new Conversation();
        conversation.dialogues.add("d1");
        conversation.dialogues.add("d2");

        List<Conversation>conversations = new ArrayList<Conversation>();
        conversations.add(conversation);
        conversations.add(conversation);
        JsonService jsonService = new JsonService();
        jsonService.writeJsonFile(jsonFile,conversations);



    }
}
