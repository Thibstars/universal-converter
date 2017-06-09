package model.plugins.mass;

import model.plugins.PluginConverter;

import java.util.HashMap;

/**
 * Converter class for the {@link MassPlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class MassConverter implements PluginConverter {

    private final HashMap<String, Double> conversions;

    MassConverter(HashMap<String, Double> conversions) {
        this.conversions = conversions;
    }

    @Override
    public Object convert(Object source, Class target, Object... args) {
        if (source instanceof MassObject) {
            if (source instanceof MetricMass) {
                if (target.equals(MetricMass.class)) {
                    return metricToMetricMass((MetricMass) source, (MetricMass.Unit) args[0]);
                } else if (target.equals(ImperialMass.class)) {
                    return metricToImperialMass((MetricMass) source);
                }
            } else if (source instanceof ImperialMass) {
                if (target.equals(ImperialMass.class)) {
                    return imperialToImperialMass((ImperialMass) source, (ImperialMass.Unit) args[0]);
                } else if (target.equals(MetricMass.class)) {
                    return imperialToMetricMass((ImperialMass) source);
                }
            }
        }
        throw new IllegalArgumentException("Could not convert " + source.getClass().getSimpleName() + " to " + target);
    }

    @Override
    public HashMap<String, Double> getConversions() {
        return conversions;
    }

    private MetricMass metricToMetricMass(MetricMass metricMass, MetricMass.Unit target) {
        double mass = metricMass.getMass();
        MetricMass.Unit metUnit = metricMass.getUnit();

        Double conversionRate = conversions.get(target.toString()) / conversions.get(metUnit.toString());
        mass = mass / conversionRate;

        return new MetricMass(mass, target);
    }

    private ImperialMass imperialToImperialMass(ImperialMass imperialMass, ImperialMass.Unit target) {
        double mass = imperialMass.getMass();
        ImperialMass.Unit impUnit = imperialMass.getUnit();

        Double conversionRate = conversions.get(target.toString()) / conversions.get(impUnit.toString());
        mass = mass / conversionRate;

        return new ImperialMass(mass, target);
    }

    private ImperialMass metricToImperialMass(MetricMass metricMass) {
        ImperialMass imperialMass;
        double mass = metricMass.getMass();
        MetricMass.Unit metUnit = metricMass.getUnit();
        Double conversionRate;

        conversionRate = (conversions.get(metUnit.toString()) / conversions.get(MetricMass.Unit.KILOGRAM.toString())) / conversions.get(ImperialMass.Unit.POUND.toString());
        mass = mass * conversionRate;

        imperialMass = new ImperialMass(mass, ImperialMass.Unit.POUND);

        return imperialMass;
    }

    private MetricMass imperialToMetricMass(ImperialMass imperialMass) {
        MetricMass metricMass;
        double mass = imperialMass.getMass();
        ImperialMass.Unit impUnit = imperialMass.getUnit();
        Double conversionRate;

        conversionRate = conversions.get(impUnit.toString());
        mass = mass * conversionRate;
        metricMass = new MetricMass(mass, MetricMass.Unit.KILOGRAM);

        return metricMass;
    }
}
