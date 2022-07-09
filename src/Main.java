import java.io.IOException;

import static service_layer.FreeDictionary.printAllPermutation;
import static service_layer.LocalDictionary.permuteLocalDictionary;
import static service_layer.MiriamWebThesaurus.thesaurusMW;

public class Main {

    public static void main(String[] args) throws IOException {
        String word = "lab";

        permuteLocalDictionary(word);
        System.out.println();
        printAllPermutation(word);
        System.out.println();

        thesaurusMW(word);

    }
}