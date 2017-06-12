package model.plugins.mass;

import constants.AppConstants;
import model.Unit;
import model.plugins.DefaultPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Default mass plugin class. Automatically registered in the {@link model.Converter}.
 *
 * @author Thibault Helsmoortel
 */
public class MassPlugin extends DefaultPlugin {

    public MassPlugin() {
        super(AppConstants.PLUGIN_MASS, AppConstants.APP_AUTHOR, ImperialMass.class, MetricMass.class);

        List<Unit> conversions = new ArrayList<>();
        conversions.add(new Unit("nanogram", MetricMass.class, 0.000001d, false));
        conversions.add(new Unit("microgram", MetricMass.class, 0.00001d, false));
        conversions.add(new Unit("milligram", MetricMass.class, 0.0001d, false));
        conversions.add(new Unit("gram", MetricMass.class, 0.001d, false));
        conversions.add(new Unit("kilogram", MetricMass.class, 1d, true));
        conversions.add(new Unit("tonne", MetricMass.class, 1000d, false));

        conversions.add(new Unit("ounce", ImperialMass.class, 0.02835d, false));
        conversions.add(new Unit("pound", ImperialMass.class, 0.45359237d, false));
        conversions.add(new Unit("stone", ImperialMass.class, 6.35029318d, false));
        conversions.add(new Unit("ton", ImperialMass.class, 1016.0469088d, false));
        setConverter(new MassConverter(conversions));
    }
}
