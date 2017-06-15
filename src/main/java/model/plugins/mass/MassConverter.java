package model.plugins.mass;

import model.Convertible;
import model.Unit;
import model.plugins.PluginConverter;

import java.util.List;

/**
 * Converter class for the {@link MassPlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class MassConverter extends PluginConverter {

    private Unit poundUnit;

    MassConverter(List<Unit> conversions) {
        super(conversions);
        this.poundUnit = getUnit("pound");
    }

    @Override
    public Convertible convert(Object source, Unit target, Object... args) {
        if (source instanceof MassObject) {
            if (source instanceof MetricMass) {
                if (target.getSystem().equals(MetricMass.class)) {
                    return metricToMetricMass((MetricMass) source, target);
                } else if (target.getSystem().equals(ImperialMass.class)) {
                    return metricToImperialMass((MetricMass) source);
                }
            } else if (source instanceof ImperialMass) {
                if (target.getSystem().equals(ImperialMass.class)) {
                    return imperialToImperialMass((ImperialMass) source, target);
                } else if (target.getSystem().equals(MetricMass.class)) {
                    return imperialToMetricMass((ImperialMass) source);
                }
            }
        }

        throw new IllegalArgumentException("Could not convert " + source.getClass().getSimpleName() + " to " + target.getName());
    }

    private MetricMass metricToMetricMass(MetricMass metricMass, Unit target) {
        double mass = metricMass.getMass();
        Unit metUnit = metricMass.getUnit();

        Double conversionRate = target.getConversionRate() / metUnit.getConversionRate();
        mass = mass / conversionRate;

        return new MetricMass(mass, target);
    }

    private ImperialMass imperialToImperialMass(ImperialMass imperialMass, Unit target) {
        double mass = imperialMass.getMass();
        Unit impUnit = imperialMass.getUnit();

        Double conversionRate = target.getConversionRate() / impUnit.getConversionRate();
        mass = mass / conversionRate;

        return new ImperialMass(mass, target);
    }

    private ImperialMass metricToImperialMass(MetricMass metricMass) {
        ImperialMass imperialMass;
        double mass = metricMass.getMass();
        Unit metUnit = metricMass.getUnit();
        Double conversionRate;

        conversionRate = (metUnit.getConversionRate() / 1) / poundUnit.getConversionRate();
        mass = mass * conversionRate;

        imperialMass = new ImperialMass(mass, poundUnit);

        return imperialMass;
    }

    private MetricMass imperialToMetricMass(ImperialMass imperialMass) {
        MetricMass metricMass;
        double mass = imperialMass.getMass();
        Unit impUnit = imperialMass.getUnit();
        Double conversionRate;

        conversionRate = impUnit.getConversionRate();
        mass = mass * conversionRate;
        metricMass = new MetricMass(mass, baseUnit);

        return metricMass;
    }
}
