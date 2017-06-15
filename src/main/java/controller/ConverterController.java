package controller;

import model.Converter;
import model.Convertible;
import model.Unit;
import model.plugins.Plugin;

import java.util.List;

/**
 * Controller class for {@link Converter}.
 *
 * @author Thibault Helsmoortel
 */
public final class ConverterController {

    public Convertible convert(Object source, Unit target, Object... args) {
        return Converter.convert(source, target, args);
    }

    public List<Plugin> getPlugins() {
        return Converter.getPlugins();
    }
}
