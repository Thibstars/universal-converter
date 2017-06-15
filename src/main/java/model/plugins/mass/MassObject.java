package model.plugins.mass;

import model.Convertible;
import model.Unit;

/**
 * Class representing any type of mass.
 *
 * @author Thibault Helsmoortel
 */
public class MassObject extends Convertible {

    MassObject(double mass, Unit unit) {
        super(mass, unit);
    }

    public double getMass() {
        return (Double) value;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MassObject that = (MassObject) o;

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
}
