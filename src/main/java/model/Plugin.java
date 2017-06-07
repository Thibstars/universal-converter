package model;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;

/**
 * Created by thibault.helsmoortel on 07-Jun-17.
 */
public abstract class Plugin {

    private final String name;
    private final String author;
    private @NotNull HashMap<String, Double> conversions;

    protected Plugin(String name, String author) {
        this.name = name;
        this.author = author;
        this.conversions = new HashMap<>(0);
    }

    public HashMap<String, Double> getConversions() {
        return conversions;
    }

    public void setConversions(@NotNull HashMap<String, Double> conversions) {
        this.conversions = conversions;
    }
}
