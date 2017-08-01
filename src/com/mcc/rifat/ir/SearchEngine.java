package com.mcc.rifat.ir;

import com.mcc.rifat.model.Conversation;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    private List<Document>documents = new ArrayList<Document>();
    private SimilarityCalculator similarityCalculator = new SimilarityCalculator();

    public SearchEngine(Conversation conversation){

        generateDocuments(conversation);
    }

    private void generateDocuments(Conversation conversation){
        for (List<String> dialogue : conversation.conversations) {
            Document document = new Document(dialogue);
            documents.add(document);
        }
    }

    public void search(Document query, double similarityThreshold){
        Document mostRelevant = null;
        double currentMaxSimilarityScore = 0.0;
        for (Document document: documents) {
            double similarityScore = similarityCalculator.getCosineSimilarityScore(query,document);
            if(similarityScore>=similarityThreshold && currentMaxSimilarityScore < similarityScore){
                mostRelevant = document;
                currentMaxSimilarityScore = similarityScore;
            }
        }
    }


}
