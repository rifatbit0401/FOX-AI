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

    public Document search(Document query, double similarityThreshold){
        Document mostRelevantDocument = null;
        double currentMaxSimilarityScore = 0.0;
        for (Document document: documents) {
            if(document.getRawSentences().size()<query.getRawSentences().size())
                continue;

            List<String> currentDialogues = new ArrayList<String>();
            for (int i=0; i <query.getRawSentences().size(); i++){
                String line = document.getRawSentences().get(i);
                currentDialogues.add(line);
            }
            Document slicedDocument = new Document(currentDialogues);
            double similarityScore = similarityCalculator.getCosineSimilarityScore(query,slicedDocument);
            if(similarityScore>=similarityThreshold && currentMaxSimilarityScore < similarityScore){
                mostRelevantDocument = document;
                currentMaxSimilarityScore = similarityScore;
            }
        }

        return mostRelevantDocument;
    }


}
