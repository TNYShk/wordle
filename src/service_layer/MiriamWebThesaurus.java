package service_layer;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MiriamWebThesaurus  {

    public static Set<String> thesaurus = new HashSet<>();
    public static String BEEWORD;
    private static Set<Character> beeWordSet;



    public static void spellingBMW(String str, String letter){
        BEEWORD = str;
        beeWordSet = IntStream.range(0,BEEWORD.length()).mapToObj(BEEWORD::charAt).collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(beeWordSet);
        PermutationsBee("",str,letter);
    }
    private static void PermutationsBee(String prefix, String str, String letter) {

      /*  if(FreeDictionary.findOnline(str) && str.length()>3){

            if(!thesaurus.contains(str))
                System.out.print(str+ " ,");

            thesaurus.add(str);
        }*/

        if(0 == str.length()){

            isInMiriamWebsterDict(prefix,letter);

            return;
        }

        int length = str.length();
        for (int i = 0; i < length; ++i) {
            PermutationsBee(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, length),letter);
        }
    }



    private static boolean isInMiriamWebsterDict(String word, String mustLetter) {
        boolean isFound = false;
        String requestURL = "https://dictionaryapi.com/api/v3/references/ithesaurus/json/";
        String myAPI = "?key=e3b0322b-c7f8-401c-ac77-ca4c84aa62a5";
        requestURL = requestURL.concat(word);
        requestURL = requestURL.concat(myAPI);

        try {
            HttpUtil.sendGetRequest(requestURL);
            String result = HttpUtil.readSingleLineResponse();

            spellingBee(result,mustLetter);
            //System.out.println(result);
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

    public static void spellingBee(String result,String mustHaveLetter){
        HashSet<String> localThesau = new HashSet<>(Arrays.asList(result.split(",")));


        for(String s: localThesau) {
            s = s.replace("[", "");
            s = s.replace("]", "");
            s= s.replace("\"","");
            s = s.trim();

            if(s.contains(mustHaveLetter) && hasAllLetters(s)){
               if(!thesaurus.contains(s))
                    System.out.print(s+ " ,");

                thesaurus.add(s);


            }


        }



    }

    public static boolean hasAllLetters(String str){


      for(int i=0;i<str.length();++i){
          if(!beeWordSet.contains(str.charAt(i)))
              return false;
      }

      return true;
    }


//************ Start of Wordle MW Thesaurus
    public static void thesaurusMW(String str) {
        Permutations("", str);
    }

    private static void Permutations(String prefix, String str) {
        if(0 == str.length()){

            if(isInMiriamWebsterDict(prefix) )
                System.out.print(prefix + " --> ");
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
        String myAPI = "?key=e3b0322b-c7f8-401c-ac77-ca4c84aa62a5";
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
    // ******************* end of Wordle MW Thesaurus ***** //

    public static void main(String[] args) {
        //isInMiriamWebsterDict("knave");
        //thesaurusMW("formula");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        spellingBMW("formal","o");

        System.out.println("\n"+thesaurus);
        System.out.println(new Timestamp(System.currentTimeMillis()));


    }



}
