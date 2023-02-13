package utils;

import java.util.ArrayList;
import java.util.List;

public class StringListUtils {
    public static List<String> getSplittedString(String dbContent){
        List<String> items = new ArrayList<>();

        String[] dividedStrings = dbContent.split("#");
        for(String item:dividedStrings)
            items.add(item);
        return items;
    }

    public static String getStringFromList(List<String> list){
        String dbContet = "";
        for(String item:list)
            dbContet += item+"#";
        return dbContet;
    }
}
