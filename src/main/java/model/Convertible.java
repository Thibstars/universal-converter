package model;

/**
 * Class representing an object that can be converted.
 *
 * @author Thibault Helsmoortel
 */
public abstract class Convertible {

    protected final Object value;
    protected final Unit unit;

    public Convertible(Object value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Object getValue() {
        return value;
    }
}
