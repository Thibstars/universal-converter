package model.plugins.util;

import constants.AppConstants;
import model.Unit;
import model.plugins.DefaultPlugin;
import model.plugins.temperature.Celsius;
import model.plugins.temperature.Fahrenheit;
import model.plugins.temperature.Kelvin;
import model.plugins.temperature.TemperatureConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an external plugin, incorrectly inheriting from {@link DefaultPlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class ExternalDefaultPlugin extends DefaultPlugin {

    public ExternalDefaultPlugin() {
        super("incorrectAccessors", AppConstants.APP_AUTHOR, Fahrenheit.class, Celsius.class, Kelvin.class);

        List<Unit> conversions = new ArrayList<>();
        conversions.add(new Unit("celsius", Celsius.class, 1d, true));

        conversions.add(new Unit("fahrenheit", Fahrenheit.class, 32d, false));

        conversions.add(new Unit("kelvin", Kelvin.class, 274.15d, false));

        setConverter(new TemperatureConverter(conversions));
    }
}
