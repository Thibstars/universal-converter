package model.plugins.util;

import model.Converter;
import model.plugins.DefaultPlugin;
import model.plugins.Plugin;

/**
 * Utils for {@link Plugin}.
 *
 * @author Thibault Helsmoortel
 */
public final class PluginUtils {

    /**
     * Returns a {@link Plugin} instance of a given class.
     *
     * NOTE: External plugins should not extend {@link DefaultPlugin}, but {@link Plugin} instead.
     *
     * @param pluginClass the class of the plugin to create
     * @return the created plugin
     * @throws IllegalAccessException if the plugin uses incorrect accessors
     * @throws InstantiationException if the plugin couldn't be instantiated
     * @throws IllegalArgumentException if an external plugin extends {@link DefaultPlugin}
     */
    public static Plugin createPluginInstance(Class<? extends Plugin> pluginClass) throws IllegalAccessException, InstantiationException, IllegalArgumentException {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (!(pluginClass.newInstance() instanceof DefaultPlugin)) {
            return pluginClass.newInstance();
        } else if (stackTraceElements[2].getClassName().equals(Converter.class.getName())) {
            return pluginClass.newInstance();
        } else {
            throw new IllegalArgumentException("Default plugins should only be instantiated internally. Use the regular Plugin class instead.");
        }
    }
}
