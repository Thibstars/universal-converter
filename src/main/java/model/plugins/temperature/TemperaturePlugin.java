package model.plugins.temperature;

import constants.AppConstants;
import model.Unit;
import model.plugins.DefaultPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Default temperature plugin class. Automatically registered in the {@link model.Converter}.
 *
 * @author Thibault Helsmoortel
 */
public class TemperaturePlugin extends DefaultPlugin {

    public TemperaturePlugin() {
        super(AppConstants.PLUGIN_TEMPERATURE, AppConstants.APP_AUTHOR, Fahrenheit.class, Celsius.class, Kelvin.class);

        List<Unit> conversions = new ArrayList<>();
        conversions.add(new Unit("celsius", Celsius.class, 1d, true));

        conversions.add(new Unit("fahrenheit", Fahrenheit.class, 32d, false));

        conversions.add(new Unit("kelvin", Kelvin.class, 274.15d, false));

        setConverter(new TemperatureConverter(conversions));
    }
}
