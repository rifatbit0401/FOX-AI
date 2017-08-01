package com.mcc.rifat.ir;

import com.mcc.rifat.utility.StringService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {
    private List<String>rawSentences = new ArrayList<>();
    private List<String> terms = new ArrayList<>();
    private Map<String, Integer> vector = new HashMap<String, Integer>();
    private StringService stringService = new StringService();
    private LuceneUtils luceneUtils = new LuceneUtils();

    public Document(List<String> sentences) {
        rawSentences = sentences;
        String line = stringService.toSingleLine(sentences);
        terms.addAll(luceneUtils.tokenizeString(line));
        constructVector();
    }

    public List<String> getRawSentences() {
        return rawSentences;
    }

    public List<String> getTerms() {
        return terms;
    }

    public Map<String, Integer>getVector(){
        return vector;
    }

    private void constructVector() {
        for (String term : terms) {
            if (!vector.containsKey(term)) {
                vector.put(term, 0);
            }
            vector.put(term, vector.get(term) + 1);
        }
    }

}
