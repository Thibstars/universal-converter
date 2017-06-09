package model.plugins.mass;

/**
 * Class representing metric mass.
 *
 * @author Thibault Helsmoortel
 */
public class MetricMass extends MassObject {

    private final Unit unit;

    public MetricMass(double mass, Unit unit) {
        super(mass);
        this.unit = unit;
    }

    public MetricMass.Unit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetricMass that = (MetricMass) o;

        return unit == that.unit && getMass() == that.getMass();
    }

    @Override
    public int hashCode() {
        return unit.hashCode() + ((int) (getMass() * Math.PI));
    }

    @Override
    public String toString() {
        return String.format("%s %s", getMass(), unit.toString().toLowerCase());
    }

    public enum Unit {
        NANOGRAM, MICROGRAM, MILLIGRAM, GRAM, KILOGRAM, TONNE
    }
}
