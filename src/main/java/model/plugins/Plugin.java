package model.plugins;

import com.sun.istack.internal.NotNull;
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
    private @NotNull
    List<Unit> conversions;
    private PluginConverter converter;
    private List<Class> convertableClasses;

    /**
     * Constructor initializing name, author and all the convertable classes.
     *
     * @param name         the plugin name
     * @param author       the main plugin author
     * @param convertables the convertable classes in this plugin
     */
    public Plugin(String name, String author, Class... convertables) {
        this.name = name;
        this.author = author;
        this.conversions = new ArrayList<>(0);
        this.convertableClasses = new ArrayList<>();
        Collections.addAll(this.convertableClasses, convertables);
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

    public List<Class> getConvertableClasses() {
        return convertableClasses;
    }
}
