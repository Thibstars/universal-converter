package model.plugins.length;

/**
 * Class representing any type of length.
 *
 * @author Thibault Helsmoortel
 */
class LengthObject {

    private final double length;

    LengthObject(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }
}
