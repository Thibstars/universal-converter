package model.plugins.length;

import constants.AppConstants;
import model.Unit;
import model.plugins.DefaultPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Default length plugin class. Automatically registered in the {@link model.Converter}.
 *
 * @author Thibault Helsmoortel
 */
public class LengthPlugin extends DefaultPlugin {

    public LengthPlugin() {
        super(AppConstants.PLUGIN_LENGTH, AppConstants.APP_AUTHOR, ImperialLength.class, MetricLength.class);

        List<Unit> conversions = new ArrayList<>();
        conversions.add(new Unit("kilometer", MetricLength.class, 1000d, false));
        conversions.add(new Unit("meter", MetricLength.class, 1d, true));
        conversions.add(new Unit("centimeter", MetricLength.class, 0.01, false));
        conversions.add(new Unit("millimeter", MetricLength.class, 0.001, false));
        conversions.add(new Unit("micron", MetricLength.class, 0.0001, false));
        conversions.add(new Unit("nanometer", MetricLength.class, 0.00001, false));

        conversions.add(new Unit("inch", ImperialLength.class, 0.0254, false));
        conversions.add(new Unit("foot", ImperialLength.class, 0.3048, false));
        conversions.add(new Unit("yard", ImperialLength.class, 0.9144, false));
        conversions.add(new Unit("mile", ImperialLength.class, 0.000621, false));
        conversions.add(new Unit("seamile", ImperialLength.class, 0.00054, false));
        setConverter(new LengthConverter(conversions));
    }
}
