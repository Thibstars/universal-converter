package model.plugins.length;

import model.plugins.Plugin;

import java.util.HashMap;

/**
 * Created by thibault.helsmoortel on 07-Jun-17.
 */
public class LengthPlugin extends Plugin{

    private final HashMap<String, Double> conversions;

    public LengthPlugin() {
        super("Length", "Thibault Helsmoortel");

        conversions = new HashMap<>();
        conversions.put(MetricLength.Unit.KILOMETER.toString(), 1000d);
        conversions.put(MetricLength.Unit.METER.toString(), 1d);
        conversions.put(MetricLength.Unit.CENTIMETER.toString(), 0.01);
        conversions.put(MetricLength.Unit.MILLIMETER.toString(), 0.001);
        conversions.put(MetricLength.Unit.MICRON.toString(), 0.0001);
        conversions.put(MetricLength.Unit.NANOMETER.toString(), 0.00001);
        conversions.put(ImperialLength.Unit.INCH.toString(), 0.0254);
        conversions.put(ImperialLength.Unit.FOOT.toString(), 0.3048);
        conversions.put(ImperialLength.Unit.YARD.toString(), 0.9144);
        conversions.put(ImperialLength.Unit.MILE.toString(), 0.000621);
        conversions.put(ImperialLength.Unit.SEAMILE.toString(), 0.00054);
        setConversions(conversions);
    }
}
