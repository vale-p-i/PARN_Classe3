package utils;

import java.util.ArrayList;
import java.util.List;

public class KeywordUtils {

    public static List<String> getKeywordListFromString(String dbContent){
        List<String> keywords = new ArrayList<String>();

        String[] dividedKeywords = dbContent.split("#");
        for (String keyword : dividedKeywords)
            keywords.add(keyword);
        return keywords;
    }
}
