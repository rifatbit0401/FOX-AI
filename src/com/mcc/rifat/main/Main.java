package com.mcc.rifat.main;

import com.mcc.rifat.ir.Document;
import com.mcc.rifat.ir.LuceneUtils;
import com.mcc.rifat.ir.SearchEngine;
import com.mcc.rifat.ir.SimilarityCalculator;
import com.mcc.rifat.model.Conversation;
import com.mcc.rifat.utility.JsonService;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import javax.print.Doc;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        //testJsonService();
        //testSimilarityCalculator();

        File jsonFile = new File("/home/rifat/Desktop/Data/conversation.json");
        JsonService jsonService = new JsonService();
        Conversation conversation = jsonService.readConversaionJsonFile(jsonFile);
        SearchEngine searchEngine = new SearchEngine(conversation);
        List<String>queryLines = new ArrayList<String>();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String queryLine = scanner.nextLine();
            queryLines.add(queryLine);
            Document resultDocument = searchEngine.search(new Document(queryLines), 0.8);
            String response = resultDocument.getRawSentences().get(queryLines.size());
            System.out.println(response);
            queryLines.add(response);
        }

    }

    private static void testSimilarityCalculator() {
        List<String> singleConevrsation = new ArrayList<String>();
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
