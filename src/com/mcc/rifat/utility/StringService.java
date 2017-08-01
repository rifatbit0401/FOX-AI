package com.mcc.rifat.utility;

import java.util.List;

public class StringService {
    public String toSingleLine(List<String>list){
        String str = "";
        for (String s :
                list) {
            str+=" "+s;
        }
        return str;
    }
}
