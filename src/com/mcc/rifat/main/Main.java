package com.mcc.rifat.main;

import com.mcc.rifat.ir.Document;
import com.mcc.rifat.ir.SearchEngine;
import com.mcc.rifat.ir.SimilarityCalculator;
import com.mcc.rifat.model.ConversationJsonModel;
import com.mcc.rifat.triplenine.TripleNineDataMiner;
import com.mcc.rifat.triplenine.TripleNineDataModel;
import com.mcc.rifat.utility.JsonService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        //testJsonService();
        //testSimilarityCalculator();

        testSearchEngine();


       // testTripleNineMiner();
    }

    private static void testTripleNineMiner() throws IOException {
        TripleNineDataMiner tripleNineDataMiner = new TripleNineDataMiner();
        List<TripleNineDataModel> tripleNineDataList = tripleNineDataMiner.parseFile("/home/rifat/Desktop/FOX-AI/sample data/sample-999-raw-data.text");
        ConversationJsonModel conversationJsonModel = tripleNineDataMiner.convertToConversationJsonModel(tripleNineDataList);
        JsonService jsonService = new JsonService();
        jsonService.writeJsonFile(new File("/home/rifat/Desktop/Data/my-999.json"), conversationJsonModel);
    }

    private static void testSearchEngine() {
        File jsonFile = new File("/home/rifat/Desktop/Data/my-999.json");
        JsonService jsonService = new JsonService();
        ConversationJsonModel conversationJsonModel = jsonService.readConversaionJsonFile(jsonFile);
        SearchEngine searchEngine = new SearchEngine(conversationJsonModel);
        List<String> queryLines = new ArrayList<String>();
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
        ConversationJsonModel conversationJsonModel = new ConversationJsonModel();
        conversationJsonModel.conversations.add(list);
        conversationJsonModel.conversations.add(list);

        JsonService jsonService = new JsonService();
        jsonService.writeJsonFile(jsonFile, conversationJsonModel);

        System.out.println(jsonService.readConversaionJsonFile(jsonFile).conversations.get(0).get(0));
    }
}
