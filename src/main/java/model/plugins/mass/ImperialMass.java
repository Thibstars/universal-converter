package model.plugins.mass;

import model.Unit;

/**
 * Class representing imperial mass.
 *
 * @author Thibault Helsmoortel
 */
public class ImperialMass extends MassObject {

    private final Unit unit;

    public ImperialMass(double mass, Unit unit) {
        super(mass);
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImperialMass that = (ImperialMass) o;

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
