package model;

/**
 * Class represenitng a measurement's unit.
 *
 * @author Thibault Helsmoortel
 */
public class Unit {

    private final String name;
    private final Class system;
    private final double conversionRate;
    private final boolean base;

    public Unit(String name, Class system, double conversionRate, boolean base) {
        this.name = name;
        this.system = system;
        this.conversionRate = conversionRate;
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public Class getSystem() {
        return system;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public boolean isBase() {
        return base;
    }
}
