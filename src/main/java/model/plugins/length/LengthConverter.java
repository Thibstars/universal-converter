package model.plugins.length;

import model.Convertible;
import model.Unit;
import model.plugins.PluginConverter;

import java.util.List;

/**
 * Converter class for the {@link LengthPlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class LengthConverter extends PluginConverter {

    private Unit footUnit;

    LengthConverter(List<Unit> conversions) {
        super(conversions);
        this.footUnit = getUnit("foot");
    }

    @Override
    public Convertible convert(Object source, Unit target, Object... args) {
        if (source instanceof LengthObject) {
            if (source instanceof MetricLength) {
                if (target.getSystem().equals(MetricLength.class)) {
                    return metricToMetricLength((MetricLength) source, target);
                } else if (target.getSystem().equals(ImperialLength.class)) {
                    return metricToImperialLength((MetricLength) source);
                }
            } else if (source instanceof ImperialLength) {
                if (target.getSystem().equals(ImperialLength.class)) {
                    return imperialToImperialLength((ImperialLength) source, target);
                } else if (target.getSystem().equals(MetricLength.class)) {
                    return imperialToMetricLength((ImperialLength) source);
                }
            }
        }

        throw new IllegalArgumentException("Could not convert " + source.getClass().getSimpleName() + " to " + target.getName());
    }

    private ImperialLength imperialToImperialLength(ImperialLength imperialLength, Unit target) {
        double length = imperialLength.getLength();
        Unit impUnit = imperialLength.getUnit();

        Double conversionRate = target.getConversionRate() / impUnit.getConversionRate();
        length = length / conversionRate;

        return new ImperialLength(length, target);
    }

    private MetricLength metricToMetricLength(MetricLength metricLength, Unit target) {
        double length = metricLength.getLength();
        Unit metUnit = metricLength.getUnit();

        Double conversionRate = target.getConversionRate() / metUnit.getConversionRate();
        length = length / conversionRate;

        return new MetricLength(length, target);
    }

    private ImperialLength metricToImperialLength(MetricLength metricLength) {
        ImperialLength imperialLength;

        double length = metricLength.getLength();
        Unit metUnit = metricLength.getUnit();
        Double conversionRate;

        conversionRate = (metUnit.getConversionRate() / 1) / footUnit.getConversionRate();
        length = length * conversionRate;
        imperialLength = new ImperialLength(length, footUnit);

        return imperialLength;
    }

    private MetricLength imperialToMetricLength(ImperialLength imperialLength) {
        MetricLength metricLength;
        double length = imperialLength.getLength();
        Unit impUnit = imperialLength.getUnit();
        Double conversionRate;

        conversionRate = impUnit.getConversionRate();
        length = length * conversionRate;
        metricLength = new MetricLength(length, baseUnit);

        return metricLength;
    }
}
