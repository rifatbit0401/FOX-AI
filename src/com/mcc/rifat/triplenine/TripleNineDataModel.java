package com.mcc.rifat.triplenine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TripleNineDataModel {

    public String timeStamp;
    public String unread;
    public String visitorId;
    public String visitorName;
    public String visitorEmail;
    public String visitorNotes;
    public String ipAddress;
    public String countryCode;
    public String countryName;
    public String region;
    public String city;
    public String userAgent;
    public String platform;
    public String browser;
    public List<String> rawData;
    public List<TripleNineDialogue> dialogues = new ArrayList<TripleNineDialogue>();
    private final String DATE_TIME_REGEX = "[(]\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}[)]";
    private final String VISITOR_MESSAGE_REGEX = "\\w*\\s\\d{8,10}:";
    private final String AGENT_MESSAGE_REGEX = "National Help\\s*[D|d]esk[-|\\s]999:";

    public TripleNineDataModel(List<String> singleConversation) {
        timeStamp = singleConversation.get(0);
        unread = singleConversation.get(1);
        visitorId = singleConversation.get(2);
        visitorName = singleConversation.get(3);
        visitorEmail = singleConversation.get(4);
        visitorNotes = singleConversation.get(5);
        ipAddress = singleConversation.get(6);
        countryCode = singleConversation.get(7);
        countryName = singleConversation.get(8);
        region = singleConversation.get(9);
        city = singleConversation.get(10);
        userAgent = singleConversation.get(11);
        platform = singleConversation.get(12);
        browser = singleConversation.get(13);

        rawData = singleConversation;
        setDialogues(singleConversation);

    }


    private void setDialogues(List<String> singleConversation) {

        List<String> mergedMessages = mergeMessages(singleConversation);
        boolean isFirst = true;
        TripleNineDialogue dialogue = new TripleNineDialogue();
        String message = "";
        for (String line : mergedMessages) {
            message += line + " ";
            if (isMessageFromAgent(message)) {
                dialogue.agent = message;
                dialogues.add(dialogue);
                dialogue = new TripleNineDialogue();
            } else {
                dialogue.client = message;
            }
            message = "";
        }
        cleanDialogues(dialogues);
    }

    private boolean doesContainDateTime(String str) {
        Matcher matcher = Pattern.compile(DATE_TIME_REGEX).matcher(str);
        return matcher.find();
    }

    private boolean isMessageFromVisitor(String str) {
        Matcher matcher = Pattern.compile(DATE_TIME_REGEX + " " + VISITOR_MESSAGE_REGEX).matcher(str);
        boolean found = matcher.find();
        return found;
    }

    private boolean isMessageFromAgent(String str) {
        Matcher matcher = Pattern.compile(DATE_TIME_REGEX + " " + AGENT_MESSAGE_REGEX).matcher(str);
        boolean found = matcher.find();
        return found;
    }


    private List<String> mergeMessages(List<String> singleConversation) {
        List<String> mergedMessages = new ArrayList<String>();

        for (int i = 15; i < singleConversation.size(); i++) {
            String line = singleConversation.get(i);
            if (line.equals(""))
                continue;
            int lastConversationIndex = mergedMessages.size() - 1;
            if (doesContainDateTime(line)) {

                if (mergedMessages.size() == 0 ||
                        (isMessageFromAgent(line) && !isMessageFromAgent(mergedMessages.get(lastConversationIndex))) ||
                        (!isMessageFromAgent(line) && isMessageFromAgent(mergedMessages.get(lastConversationIndex)))) {
                    mergedMessages.add(line);
                } else {
                    String mergedMessage = mergedMessages.get(lastConversationIndex) + line;
                    mergedMessages.set(lastConversationIndex, mergedMessage);
                }
            } else {
                // System.out.println(line);
                String mergedMessage = mergedMessages.get(lastConversationIndex) + line;
                mergedMessages.set(lastConversationIndex, mergedMessage);
            }
        }

        return mergedMessages;
    }

    private void cleanDialogues(List<TripleNineDialogue> tripleNineDialogues) {

        String clientRegex = DATE_TIME_REGEX + " " + VISITOR_MESSAGE_REGEX;
        String agentRegex = DATE_TIME_REGEX + " " + AGENT_MESSAGE_REGEX;
        for (int i = 0; i < dialogues.size(); i++) {
            TripleNineDialogue dialogue = dialogues.get(i);
            if (dialogue.client == null)
                System.out.println("okk");
            dialogue.client = dialogue.client.replaceAll(clientRegex, "").trim();
            dialogue.agent = dialogue.agent.replaceAll(agentRegex, "").trim();
            dialogues.set(i, dialogue);
        }

    }


}




