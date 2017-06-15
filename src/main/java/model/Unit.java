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

    /**
     * Class constructor specifying name, system, conversionRate and base.
     *
     * @param name           the unit's name
     * @param system         the unit's system (applicable scope class)
     * @param conversionRate the unit's conversionRate in relation to another base unit, if this isn't the base unit
     * @param base           true to mark this unit as base unit (generally a conversionRate of 1d)
     */
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

    @Override
    public String toString() {
        return name;
    }
}
