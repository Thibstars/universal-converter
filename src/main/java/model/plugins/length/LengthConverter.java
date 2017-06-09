package model.plugins.length;

import model.plugins.PluginConverter;

import java.util.HashMap;

/**
 * Converter class for the {@link LengthPlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class LengthConverter implements PluginConverter {

    private final HashMap<String, Double> conversions;

    LengthConverter(HashMap<String, Double> conversions) {
        this.conversions = conversions;
    }

    @Override
    public Object convert(Object source, Class target, Object... args) {
        if (source instanceof LengthObject) {
            if (source instanceof ImperialLength) {
                if (target.equals(MetricLength.class)) {
                    return imperialToMetricLength((ImperialLength) source);
                } else if (target.equals(ImperialLength.class)) {
                    return imperialToImperialLength((ImperialLength) source, (ImperialLength.Unit) args[0]);
                }

            } else if (source instanceof MetricLength) {
                if (target.equals(MetricLength.class)) {
                    return metricToMetricLength((MetricLength) source, (MetricLength.Unit) args[0]);
                } else if (target.equals(ImperialLength.class)) {
                    return metricToImperialLength((MetricLength) source);
                }
            }
        }

        throw new IllegalArgumentException("Could not convert " + source.getClass().getSimpleName() + " to " + target);
    }

    @Override
    public HashMap<String, Double> getConversions() {
        return conversions;
    }

    private ImperialLength imperialToImperialLength(ImperialLength imperialLength, ImperialLength.Unit target) {
        double length = imperialLength.getLength();
        ImperialLength.Unit impUnit = imperialLength.getUnit();

        Double conversionRate = conversions.get(target.toString()) / conversions.get(impUnit.toString());
        length = length / conversionRate;

        return new ImperialLength(length, target);
    }

    private MetricLength metricToMetricLength(MetricLength metricLength, MetricLength.Unit target) {
        double length = metricLength.getLength();
        MetricLength.Unit metUnit = metricLength.getUnit();

        Double conversionRate = conversions.get(target.toString()) / conversions.get(metUnit.toString());
        length = length / conversionRate;

        return new MetricLength(length, target);
    }

    private ImperialLength metricToImperialLength(MetricLength metricLength) {
        ImperialLength imperialLength;

        double length = metricLength.getLength();
        MetricLength.Unit metUnit = metricLength.getUnit();
        Double conversionRate;

        conversionRate = (conversions.get(metUnit.toString()) / conversions.get(MetricLength.Unit.METER.toString())) / conversions.get(ImperialLength.Unit.FOOT.toString());
        length = length * conversionRate;
        imperialLength = new ImperialLength(length, ImperialLength.Unit.FOOT);

        return imperialLength;
    }

    private MetricLength imperialToMetricLength(ImperialLength imperialLength) {
        MetricLength metricLength;
        double length = imperialLength.getLength();
        ImperialLength.Unit impUnit = imperialLength.getUnit();
        Double conversionRate;

        conversionRate = conversions.get(impUnit.toString());
        length = length * conversionRate;
        metricLength = new MetricLength(length, MetricLength.Unit.METER);

        return metricLength;
    }
}
