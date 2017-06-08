package model.plugins.length;

/**
 * Class representing metric length.
 *
 * @author Thibault Helsmoortel
 */
public class MetricLength extends LengthObject {

    private final Unit unit;

    public MetricLength(double length, Unit unit) {
        super(length);
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getLength(), unit.toString().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetricLength that = (MetricLength) o;

        return unit == that.unit && getLength() == that.getLength();
    }

    @Override
    public int hashCode() {
        return unit.hashCode() + ((int) (getLength() * Math.PI));
    }

    public enum Unit {
        NANOMETER, MICRON, MILLIMETER, CENTIMETER, METER, KILOMETER
    }
}
