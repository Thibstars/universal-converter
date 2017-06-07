package model;

import model.plugins.Plugin;
import model.plugins.length.LengthPlugin;
import model.plugins.length.ImperialLength;
import model.plugins.length.LengthObject;
import model.plugins.length.MetricLength;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by thibault.helsmoortel on 07-Jun-17.
 */
public class Converter {

    private static final List<Plugin> plugins;
    private static final HashMap<String, Double> conversions;

    static {
        plugins = new ArrayList<>();
        conversions = new HashMap<>();

        // Register all default plugins
        registerPlugin(new LengthPlugin());
    }

    public static void registerPlugin(Plugin plugin) {
        plugins.add(plugin);
        conversions.putAll(plugin.getConversions());
    }

    public static void unregisterPlugin(Plugin plugin) {
        plugins.remove(plugin);
        conversions.forEach((key, value) -> {
            if (plugin.getConversions().containsKey(key)) {
                conversions.remove(key);
            }
        });
    }

    public static Object convert(Object source, Class target, Object... args) {
        if (source instanceof LengthObject) {
            if (source instanceof ImperialLength) {
                if (target.equals(MetricLength.class)) {
                    return imperialToMetricLength((ImperialLength) source);
                } else if (target.equals(ImperialLength.class)) {
                    return imperialToImperialLength((ImperialLength) source, (ImperialLength.Unit) args[0]);
                }

            } else if (source instanceof MetricLength && target.equals(MetricLength.class)) {
                return metricToMetricLength((MetricLength) source, (MetricLength.Unit) args[0]);
            }
        }

        throw new IllegalArgumentException("Could not convert " + source.getClass().getSimpleName() + " to " + target);
    }

    private static ImperialLength imperialToImperialLength(ImperialLength imperialLength, ImperialLength.Unit target) {
        double length = imperialLength.getLength();
        ImperialLength.Unit impUnit = imperialLength.getUnit();

        Double conversionRate = conversions.get(target.toString()) / conversions.get(impUnit.toString());
        length = length / conversionRate;

        return new ImperialLength(length, target);
    }

    private static MetricLength metricToMetricLength(MetricLength metricLength, MetricLength.Unit target) {
        double length = metricLength.getLength();
        MetricLength.Unit metUnit = metricLength.getUnit();

        Double conversionRate = conversions.get(target.toString()) / conversions.get(metUnit.toString());
        length = length / conversionRate;

        return new MetricLength(length, target);
    }

    private static MetricLength imperialToMetricLength(ImperialLength imperialLength) {
        MetricLength metricLength = null;
        double length = imperialLength.getLength();
        ImperialLength.Unit impUnit = imperialLength.getUnit();
        Double conversionRate;

        switch (impUnit) {
            case INCH:
                conversionRate = conversions.get(ImperialLength.Unit.INCH.toString());
                length = length * conversionRate;
                metricLength = new MetricLength(length, MetricLength.Unit.METER);
                break;
            case FOOT:
                conversionRate = conversions.get(ImperialLength.Unit.FOOT.toString());
                length = length * conversionRate;
                metricLength = new MetricLength(length, MetricLength.Unit.METER);
                break;
            case YARD:
                conversionRate = conversions.get(ImperialLength.Unit.YARD.toString());
                length = length * conversionRate;
                metricLength = new MetricLength(length, MetricLength.Unit.METER);
                break;
            case MILE:
                conversionRate = conversions.get(ImperialLength.Unit.MILE.toString());
                length = length * conversionRate;
                metricLength = new MetricLength(length, MetricLength.Unit. METER);
                break;
            case SEAMILE:
                conversionRate = conversions.get(ImperialLength.Unit.SEAMILE.toString());
                length = length * conversionRate;
                metricLength = new MetricLength(length, MetricLength.Unit.METER);
                break;
        }

        return metricLength;
    }
}
