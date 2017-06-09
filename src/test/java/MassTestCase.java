import model.Converter;
import model.plugins.mass.ImperialMass;
import model.plugins.mass.MetricMass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by thibault.helsmoortel on 08-Jun-17.
 */
public class MassTestCase {

    @Test
    public void testMetricKGToG() {
        MetricMass metricMass = new MetricMass(1, MetricMass.Unit.KILOGRAM);
        Object actual = Converter.convert(metricMass, MetricMass.class, MetricMass.Unit.GRAM);

        MetricMass expected = new MetricMass(1000, MetricMass.Unit.GRAM);
        assertEquals("KG should be 1000g.", expected, actual);
    }

    @Test
    public void testMetricGToKG() {
        MetricMass metricMass = new MetricMass(1, MetricMass.Unit.GRAM);
        Object actual = Converter.convert(metricMass, MetricMass.class, MetricMass.Unit.KILOGRAM);

        MetricMass expected = new MetricMass(0.001, MetricMass.Unit.KILOGRAM);
        assertEquals("G should be 0.001kg.", expected, actual);
    }

    @Test
    public void testImperialStoneToPound() {
        ImperialMass imperialMass = new ImperialMass(1, ImperialMass.Unit.STONE);
        Object actual = Converter.convert(imperialMass, ImperialMass.class, ImperialMass.Unit.POUND);

        ImperialMass expected = new ImperialMass(14, ImperialMass.Unit.POUND);
        assertEquals("Stone should be 14 pound.", expected, actual);
    }

    @Test
    public void testImperialPoundToStone() {
        ImperialMass imperialMass = new ImperialMass(1, ImperialMass.Unit.POUND);
        Object actual = Converter.convert(imperialMass, ImperialMass.class, ImperialMass.Unit.STONE);

        ImperialMass expected = new ImperialMass(0.07142857142857142, ImperialMass.Unit.STONE);
        assertEquals("Pound should be 0.07142857142857142 stone.", expected, actual);
    }

    @Test
    public void testImperialOunceToMetric() {
        ImperialMass imperialMass = new ImperialMass(1, ImperialMass.Unit.OUNCE);
        Object actual = Converter.convert(imperialMass, MetricMass.class);

        MetricMass expected = new MetricMass(0.02835, MetricMass.Unit.KILOGRAM);
        assertTrue("Ounce should be 0.02835 kg.", expected.equals(actual) || expected.equals(Converter.convert(actual, MetricMass.class, expected.getUnit())));
    }

    @Test
    public void testImperialStoneToMetric() {
        ImperialMass imperialMass = new ImperialMass(1, ImperialMass.Unit.STONE);
        Object actual = Converter.convert(imperialMass, MetricMass.class);

        MetricMass expected = new MetricMass(6.35029318, MetricMass.Unit.KILOGRAM);
        assertTrue("Stone should be 0.6.35029318 kg.", expected.equals(actual) || expected.equals(Converter.convert(actual, MetricMass.class, expected.getUnit())));

    }

    @Test
    public void testMetricGramToImperial() {
        MetricMass metricMass = new MetricMass(1, MetricMass.Unit.GRAM);
        Object actual = Converter.convert(metricMass, ImperialMass.class);

        ImperialMass expected = new ImperialMass(0.035273368606701945, ImperialMass.Unit.OUNCE);
        assertTrue("Gram should be 0.035273368606701945 ounce.", expected.equals(actual) || expected.equals(Converter.convert(actual, ImperialMass.class, expected.getUnit())));
    }

    @Test
    public void testMetricTonneToImperial() {
        MetricMass metricMass = new MetricMass(1, MetricMass.Unit.TONNE);
        Object actual = Converter.convert(metricMass, ImperialMass.class);

        ImperialMass expected = new ImperialMass(157.4730444177697, ImperialMass.Unit.STONE);
        assertTrue("Tonne should be 157.4730444177697 stone.", expected.equals(actual) || expected.equals(Converter.convert(actual, ImperialMass.class, expected.getUnit())));
    }
}
