package model;

import model.plugins.DefaultPlugin;
import model.plugins.Plugin;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for all the conversions.
 *
 * @author Thibault Helsmoortel
 */
public class Converter {

    private static final Logger LOGGER = Logger.getLogger(Converter.class.getName());

    private static final List<Plugin> plugins;

    static {
        plugins = new ArrayList<>();

        // Register all default plugins using reflection
        Reflections reflections = new Reflections("model.plugins");
        Set<Class<? extends DefaultPlugin>> subTypes = reflections.getSubTypesOf(DefaultPlugin.class);

        subTypes.forEach(aClass -> {
            try {
                registerPlugin(aClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.log(Level.WARNING, String.format("Could not register plugin %s. \n%s", aClass.getSimpleName(), e));
                e.printStackTrace();
            }
        });
    }

    /**
     * Registers the given plugin, which is a requirement to perform the plugin's conversions.
     *
     * @param plugin the plugin to register
     */
    public static void registerPlugin(Plugin plugin) {
        LOGGER.log(Level.INFO, String.format("Registering plugin %s by %s", plugin.getName(), plugin.getAuthor()));
        plugins.add(plugin);
    }

    /**
     * Deregisters the given plugin. Conversions of this plugin will no longer work.
     *
     * @param plugin the plugin to deregister
     */
    public static void deregisterPlugin(Plugin plugin) {
        LOGGER.log(Level.INFO, String.format("Deregistering plugin %s by %s", plugin.getName(), plugin.getAuthor()));
        plugins.remove(plugin);
    }

    public static List<Plugin> getPlugins() {
        return new ArrayList<>(plugins);
    }

    /**
     * The main conversion method that will return a registered plugin converter conversion value,
     * but only if source and target are convertable classes in the plugin.
     *
     * @param source the source object to convert
     * @param target the target unit
     * @param args   optional arguments necessary in the conversion
     * @return the conversion result of the plugin
     */
    public static Object convert(Object source, Unit target, Object... args) {
        for (Plugin plugin : plugins) {
            List<Class> convertableClasses = plugin.getConvertableClasses();
            List<Unit> conversionUnits = plugin.getConverter().getConversions();
            if (convertableClasses.contains(source.getClass()) && conversionUnits.contains(target)) {
                return plugin.getConverter().convert(source, target, args);
            }
        }

        throw new IllegalArgumentException("Could not convert " + source.getClass().getSimpleName() + " to " + target.getName());
    }
}
