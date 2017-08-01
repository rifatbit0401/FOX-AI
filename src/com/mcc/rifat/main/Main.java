package com.mcc.rifat.main;

import com.mcc.rifat.ir.Document;
import com.mcc.rifat.ir.LuceneUtils;
import com.mcc.rifat.ir.SimilarityCalculator;
import com.mcc.rifat.model.Conversation;
import com.mcc.rifat.utility.JsonService;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        //testJsonService();
        List<String>singleConevrsation = new ArrayList<String>();
        singleConevrsation.add("Hi hello lol!!!");
        Document document1 = new Document(singleConevrsation);
        Document document2 = new Document(singleConevrsation);

        SimilarityCalculator similarityCalculator = new SimilarityCalculator();
        System.out.println(similarityCalculator.getCosineSimilarityScore(document1,document2));
    }

    private static void testJsonService() {
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
