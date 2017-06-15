package model.plugins.temperature;

import model.Convertible;
import model.Unit;

/**
 * Class representing any type of temperature.
 *
 * @author Thibault Helsmoortel
 */
public class TemperatureObject extends Convertible {

    TemperatureObject(Object value, Unit unit) {
        super(value, unit);
    }

    public double getTemperature() {
        return (Double) value;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemperatureObject that = (TemperatureObject) o;

        return unit == that.unit && getTemperature() == that.getTemperature();
    }

    @Override
    public int hashCode() {
        return unit.hashCode() + ((int) (getTemperature() * Math.PI));
    }

    @Override
    public String toString() {
        return String.format("%s %s", getTemperature(), unit.toString().toLowerCase());
    }
}
