import service_layer.MiriamWebThesaurus;

import java.awt.*;
import java.io.IOException;

import static service_layer.Container.getContainer;
import static service_layer.FreeDictionary.printAllPermutation;
import static service_layer.LocalDictionary.permuteLocalDictionary;
import static service_layer.MiriamWebThesaurus.thesaurusMW;

public class Main extends Container {

    public static void main(String[] args) throws IOException {
        String word = "state";
        permuteLocalDictionary(word);
        System.out.println("done with local file");
        printAllPermutation(word);
        System.out.println("done with free API\n");

        System.out.println(getContainer());
        System.out.println("\n Thesaurus API");

        thesaurusMW(word);


    }





}