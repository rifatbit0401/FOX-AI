package com.mcc.rifat.ir;

public class SimilarityCalculator {

    public double getCosineSimilarityScore(Document doc1, Document doc2){
        double valueOfDoc1 = getVectorValue(doc1);
        double valueOfDoc2 = getVectorValue(doc2);
        double dotProduct = 0.0;

        for (String term : doc1.getVector().keySet()) {
            if(doc2.getVector().keySet().contains(term)){
                dotProduct+=doc1.getVector().get(term)*doc2.getVector().get(term);
            }
        }
        return dotProduct/(valueOfDoc1*valueOfDoc2);
    }

    private double getVectorValue(Document document){
        double value = 0.0;
        for (String term: document.getVector().keySet()) {
            int termWieght = document.getVector().get(term);
            value+= termWieght*termWieght;
        }
        return Math.sqrt(value);
    }
}
