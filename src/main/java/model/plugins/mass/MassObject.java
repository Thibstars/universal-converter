package model.plugins.mass;

/**
 * Class representing any type of mass.
 *
 * @author Thibault Helsmoortel
 */
public class MassObject {

    private final double mass;

    MassObject(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }
}
