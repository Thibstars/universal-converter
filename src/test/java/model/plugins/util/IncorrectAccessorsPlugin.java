package model.plugins.util;

/**
 * Created by thibault.helsmoortel on 16-Jun-17.
 */

import constants.AppConstants;
import model.Unit;
import model.plugins.Plugin;
import model.plugins.temperature.Celsius;
import model.plugins.temperature.Fahrenheit;
import model.plugins.temperature.Kelvin;
import model.plugins.temperature.TemperatureConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Plugin using incorrect accessors.
 *
 * @author Thibault Helsmoortel
 */
class IncorrectAccessorsPlugin extends Plugin {

     private IncorrectAccessorsPlugin() {
        super("incorrectAccessors", AppConstants.APP_AUTHOR, Fahrenheit.class, Celsius.class, Kelvin.class);

        List<Unit> conversions = new ArrayList<>();
        conversions.add(new Unit("celsius", Celsius.class, 1d, true));

        conversions.add(new Unit("fahrenheit", Fahrenheit.class, 32d, false));

        conversions.add(new Unit("kelvin", Kelvin.class, 274.15d, false));

        setConverter(new TemperatureConverter(conversions));
    }
}
