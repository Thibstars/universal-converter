package model.plugins;

import model.Unit;

import java.util.List;

/**
 * Interface representing a PluginConverter.
 *
 * @author Thibault Helsmoortel
 */
public abstract class PluginConverter {

    private final List<Unit> conversions;
    protected Unit baseUnit;

    public PluginConverter(List<Unit> conversions) {
        this.conversions = conversions;
        for (Unit conversion : conversions) {
            if (conversion.isBase()) {
                this.baseUnit = conversion;
                break;
            }
        }
    }

    public abstract Object convert(Object source, Unit target, Object... args);

    public List<Unit> getConversions() {
        return conversions;
    }

    public Unit getUnit(String name) {
        for (Unit conversion : conversions) {
            if (name.equals(conversion.getName())) {
                return conversion;
            }
        }
        return null;
    }
}
