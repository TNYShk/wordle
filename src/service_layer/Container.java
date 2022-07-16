package service_layer;

import java.util.HashSet;
import java.util.Set;

public abstract class Container {
    private static Set<String> results = new HashSet<>();


    public static Set<String> getContainer(){
        return results;
    }
}
