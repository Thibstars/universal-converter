package model.plugins;

import java.util.HashMap;

/**
 * Interface representing a PluginConverter.
 *
 * @author Thibault Helsmoortel
 */
public interface PluginConverter {

    Object convert(Object source, Class target, Object... args);

    HashMap<String, Double> getConversions();
}
