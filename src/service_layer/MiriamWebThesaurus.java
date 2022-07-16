package service_layer;

import java.io.IOException;
import java.util.*;

public class MiriamWebThesaurus  {

    public static Set<String> thesaurus = new HashSet<>();

    public static void thesaurusMW(String str) {
        Permutations("", str);
    }

    private static void Permutations(String prefix, String str) {
        if(0 == str.length()){

            if(isInMiriamWebsterDict(prefix) )
                    System.out.print(prefix + " ");
            return;
        }

        int length = str.length();
        for (int i = 0; i < length; ++i) {
            Permutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, length));
        }
    }


    private static boolean isInMiriamWebsterDict(String word) {
        boolean isFound = false;
        String requestURL = "https://dictionaryapi.com/api/v3/references/ithesaurus/json/";
        String myAPI = "?key=<...........>";
        requestURL = requestURL.concat(word);
        requestURL = requestURL.concat(myAPI);

        try {
            HttpUtil.sendGetRequest(requestURL);
            String result = HttpUtil.readSingleLineResponse();
            //parseResult(result);
            System.out.println(result);
            if (!result.isEmpty()){
                isFound = true;
            }
        } catch (IOException e) {
            return false;
        }finally {
            HttpUtil.disconnect();
        }
        return isFound;
    }

    public static void parseResult(String result){

        LinkedHashSet<String> localThesau = new LinkedHashSet<>(Arrays.asList(result.split(",")));

        for(String s: localThesau) {
            s = s.replace("[", "");
            s = s.replace("]", "");
            thesaurus.add(s);
        }

        System.out.println(localThesau);

    }

    public static void main(String[] args) {
        thesaurusMW("state");
        //System.out.println(thesaurus);

       /* int counter = 0;
        for(String s: thesaurus){
            System.out.print(s+", ");
            ++counter;
            if(counter == 10){
                System.out.println();
                counter = 0;
            }
        }*/

    }
}
