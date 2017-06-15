package model.plugins.length;

import model.Convertible;
import model.Unit;

/**
 * Class representing any type of length.
 *
 * @author Thibault Helsmoortel
 */
class LengthObject extends Convertible {

    LengthObject(double length, Unit unit) {
        super(length, unit);
    }

    public double getLength() {
        return (Double) value;
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

        LengthObject that = (LengthObject) o;

        return unit == that.unit && getLength() == that.getLength();
    }

    @Override
    public int hashCode() {
        return unit.hashCode() + ((int) (getLength() * Math.PI));
    }
}
