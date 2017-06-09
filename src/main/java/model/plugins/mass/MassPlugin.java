package model.plugins.mass;

import constants.AppConstants;
import model.plugins.DefaultPlugin;

import java.util.HashMap;

/**
 * Default mass plugin class. Automatically registered in the {@link model.Converter}.
 *
 * @author Thibault Helsmoortel
 */
public class MassPlugin extends DefaultPlugin {

    public MassPlugin() {
        super(AppConstants.PLUGIN_MASS, AppConstants.APP_AUTHOR, ImperialMass.class, MetricMass.class);

        HashMap<String, Double> conversions = new HashMap<>();
        conversions.put(MetricMass.Unit.NANOGRAM.toString(), 0.000001d);
        conversions.put(MetricMass.Unit.MICROGRAM.toString(), 0.00001d);
        conversions.put(MetricMass.Unit.MILLIGRAM.toString(), 0.0001d);
        conversions.put(MetricMass.Unit.GRAM.toString(), 0.001d);
        conversions.put(MetricMass.Unit.KILOGRAM.toString(), 1d);
        conversions.put(MetricMass.Unit.TONNE.toString(), 1000d);

        conversions.put(ImperialMass.Unit.OUNCE.toString(), 0.02835d);
        conversions.put(ImperialMass.Unit.POUND.toString(), 0.45359237d);
        conversions.put(ImperialMass.Unit.STONE.toString(), 6.35029318d);
        conversions.put(ImperialMass.Unit.TON.toString(), 1016.0469088d);
        setConverter(new MassConverter(conversions));
    }
}
