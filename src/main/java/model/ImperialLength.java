package model;

/**
 * Created by thibault.helsmoortel on 07-Jun-17.
 */
public class ImperialLength extends LengthObject {

    private final Unit unit;

    public ImperialLength(double length, Unit unit) {
        super(length);
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public enum Unit {
        INCH, FOOT, YARD, MILE, SEAMILE
    }

    @Override
    public String toString() {
        return String.format("%s %s", getLength(), unit.toString().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImperialLength that = (ImperialLength) o;

        return unit == that.unit && getLength() == that.getLength();
    }

    @Override
    public int hashCode() {
        return unit.hashCode() + ((int) (getLength() * Math.PI));
    }
}
