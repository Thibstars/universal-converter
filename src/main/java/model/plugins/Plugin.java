package model.plugins;

import model.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class representing an actual plugin.
 *
 * @author Thibault Helsmoortel
 */
public abstract class Plugin {

    private final String name;
    private final String author;
    private List<Unit> conversions;
    private PluginConverter converter;
    private List<Class> convertibleClasses;

    /**
     * Constructor initializing name, author and all the convertible classes.
     *
     * @param name         the plugin name
     * @param author       the main plugin author
     * @param convertibles the convertible classes in this plugin
     */
    public Plugin(String name, String author, Class... convertibles) {
        this.name = name;
        this.author = author;
        this.conversions = new ArrayList<>(0);
        this.convertibleClasses = new ArrayList<>();
        Collections.addAll(this.convertibleClasses, convertibles);
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public List<Unit> getConversions() {
        return conversions;
    }

    public PluginConverter getConverter() {
        return converter;
    }

    public void setConverter(PluginConverter converter) {
        this.converter = converter;
        this.conversions = converter.getConversions();
    }

    public List<Class> getConvertibleClasses() {
        return convertibleClasses;
    }

    @Override
    public String toString() {
        return name;
    }
}
