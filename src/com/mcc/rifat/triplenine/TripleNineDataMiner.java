package com.mcc.rifat.triplenine;

import com.mcc.rifat.model.ConversationJsonModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TripleNineDataMiner {

    private String CONVERSATION_SPLITTER = "========";

    public List<TripleNineDataModel> parseFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<TripleNineDataModel> tripleNineConversations = new ArrayList<TripleNineDataModel>();

        //TripleNineDataModel tripleNineConversation = new TripleNineDataModel();
        List<String> conversation = new ArrayList<String>();
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith(CONVERSATION_SPLITTER)) {
                TripleNineDataModel tripleNineData = new TripleNineDataModel(conversation);
                tripleNineConversations.add(tripleNineData);
                conversation = new ArrayList<String>();
                continue;
            }
            conversation.add(line);
        }
        return tripleNineConversations;
    }

    public ConversationJsonModel convertToConversationJsonModel(List<TripleNineDataModel> tripleNineDataList) {

        ConversationJsonModel conversationJsonModel = new ConversationJsonModel();
        for (TripleNineDataModel tripleNineData : tripleNineDataList) {
            List<String> speechList = new ArrayList<String>();
            for (TripleNineDialogue dialogue : tripleNineData.dialogues) {
                speechList.add(dialogue.client);
                speechList.add(dialogue.agent);
            }
            conversationJsonModel.conversations.add(speechList);
        }
        return conversationJsonModel;
    }


}
