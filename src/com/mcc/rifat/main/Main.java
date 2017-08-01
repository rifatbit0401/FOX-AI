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
        List<String> list = new ArrayList<String>();
        list.add("d1");
        list.add("d2");
        Conversation conversation = new Conversation();
        conversation.conversations.add(list);
        conversation.conversations.add(list);

        JsonService jsonService = new JsonService();
        jsonService.writeJsonFile(jsonFile,conversation);

        System.out.println(jsonService.readConversaionJsonFile(jsonFile).conversations.get(0).get(0));

    }
}
