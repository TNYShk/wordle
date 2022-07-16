package service_layer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class LocalDictionary extends Container{
    private static HashSet<String> dictionary = new HashSet<>();

    public static void permuteLocalDictionary(String str) throws IOException {
        if(dictionary.isEmpty())
            loadDictionary();
        Permutations("", str);
    }
    private static void Permutations(String prefix, String str) {
        if(0 == str.length()){
            if(isInDictionary(prefix)){
                //System.out.println(prefix + " local");
                Container.getContainer().add(prefix);
            }

            return;
        }
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            Permutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, length));
        }
    }
    private static boolean isInDictionary(String word){
        return dictionary.contains(word);
    }
    private static void loadDictionary() throws IOException {
        try( BufferedReader rd = new BufferedReader( new FileReader("/Users/tanyashkolnik/Documents/wordsDictionary.txt"))) {
            String line = "";
            while ((line = rd.readLine()) != null) {
                dictionary.add(line);
            }
        }
    }


}
