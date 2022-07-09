package service_layer;

import java.io.IOException;

public class FreeDictionary {

    private static char[] charArray;

    public static boolean findOnline(String w){
        boolean isWord;
        String requestURL = "https://api.dictionaryapi.dev/api/v2/entries/en/";
        requestURL = requestURL.concat(w);

        try {
            HttpUtil.sendGetRequest(requestURL);
            String result = HttpUtil.readSingleLineResponse();

            isWord =  true;
        } catch (IOException ex) {
            isWord =  false;

        }finally {
            HttpUtil.disconnect();
        }
        return isWord;
    }
    public static void printAllPermutation(String str) {
        int start = 0;
        int end = str.length() - 1;
        charArray = str.toCharArray();
        printAllPermutationRec( start, end);
    }

    private static void printAllPermutationRec( int start, int end){
        if (start == end) {
            if(findOnline(String.valueOf(charArray)))
                System.out.println(charArray);
            return;
        }

        for (int i = start; i <= end; ++i) {
            charArray = swap( start, i);
            printAllPermutationRec( start + 1, end);
            charArray = swap( start, i);
        }
    }

    private static char[] swap( int start, int end) {
        char tmp = charArray[start];
        charArray[start] = charArray[end];
        charArray[end] = tmp;

        return charArray;
    }


}
