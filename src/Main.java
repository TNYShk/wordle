import service_layer.MiriamWebThesaurus;

import java.awt.*;
import java.io.IOException;

import static service_layer.Container.getContainer;
import static service_layer.FreeDictionary.printAllPermutation;
import static service_layer.LocalDictionary.permuteLocalDictionary;
import static service_layer.MiriamWebThesaurus.*;

public class Main extends Container {

    public static void main(String[] args) throws IOException {
        String word = "poamley";
        permuteLocalDictionary(word);
        System.out.println(getContainer());
        System.out.println("done with local file");
        printAllPermutation(word);
        System.out.println("done with free API\n");



        System.out.println(getContainer());
        System.out.println("\n Thesaurus API");


   /*   Spelling Bee NYT *****
        spellingBMW("formula","o");
        System.out.println(thesaurus);
      */


       //thesaurusMW(word);


    }





}