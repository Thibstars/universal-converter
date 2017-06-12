import constants.AppConstants;
import model.Converter;
import model.plugins.Plugin;
import model.plugins.mass.ImperialMass;
import model.plugins.mass.MassConverter;
import model.plugins.mass.MetricMass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test case for the {@link model.plugins.mass.MassPlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class MassTestCase {

    private MassConverter massConverter;

    @Before
    public void setUp() throws Exception {
        for (Plugin plugin : Converter.getPlugins()) {
            if (plugin.getName().contains(AppConstants.PLUGIN_MASS)) {
                massConverter = (MassConverter) plugin.getConverter();
            }
        }
    }

    @Test
    public void testMetricKGToG() {
        MetricMass metricMass = new MetricMass(1, massConverter.getUnit("kilogram"));
        Object actual = Converter.convert(metricMass, massConverter.getUnit("gram"));

        MetricMass expected = new MetricMass(1000, massConverter.getUnit("gram"));
        assertEquals("KG should be 1000g.", expected, actual);
    }

    @Test
    public void testMetricGToKG() {
        MetricMass metricMass = new MetricMass(1, massConverter.getUnit("gram"));
        Object actual = Converter.convert(metricMass, massConverter.getUnit("kilogram"));

        MetricMass expected = new MetricMass(0.001, massConverter.getUnit("kilogram"));
        assertEquals("G should be 0.001kg.", expected, actual);
    }

    @Test
    public void testImperialStoneToPound() {
        ImperialMass imperialMass = new ImperialMass(1, massConverter.getUnit("stone"));
        Object actual = Converter.convert(imperialMass, massConverter.getUnit("pound"));

        ImperialMass expected = new ImperialMass(14, massConverter.getUnit("pound"));
        assertEquals("Stone should be 14 pound.", expected, actual);
    }

    @Test
    public void testImperialPoundToStone() {
        ImperialMass imperialMass = new ImperialMass(1, massConverter.getUnit("pound"));
        Object actual = Converter.convert(imperialMass, massConverter.getUnit("stone"));

        ImperialMass expected = new ImperialMass(0.07142857142857142, massConverter.getUnit("stone"));
        assertEquals("Pound should be 0.07142857142857142 stone.", expected, actual);
    }

    @Test
    public void testImperialOunceToMetric() {
        ImperialMass imperialMass = new ImperialMass(1, massConverter.getUnit("ounce"));
        Object actual = Converter.convert(imperialMass, massConverter.getUnit("kilogram"));

        MetricMass expected = new MetricMass(0.02835, massConverter.getUnit("kilogram"));
        assertTrue("Ounce should be 0.02835 kg.", expected.equals(actual) || expected.equals(Converter.convert(actual, expected.getUnit())));
    }

    @Test
    public void testImperialStoneToMetric() {
        ImperialMass imperialMass = new ImperialMass(1, massConverter.getUnit("stone"));
        Object actual = Converter.convert(imperialMass, massConverter.getUnit("kilogram"));

        MetricMass expected = new MetricMass(6.35029318, massConverter.getUnit("kilogram"));
        assertTrue("Stone should be 0.6.35029318 kg.", expected.equals(actual) || expected.equals(Converter.convert(actual, expected.getUnit())));

    }

    @Test
    public void testMetricGramToImperial() {
        MetricMass metricMass = new MetricMass(1, massConverter.getUnit("gram"));
        Object actual = Converter.convert(metricMass, massConverter.getUnit("ounce"));

        ImperialMass expected = new ImperialMass(0.035273368606701945, massConverter.getUnit("ounce"));
        assertTrue("Gram should be 0.035273368606701945 ounce.", expected.equals(actual) || expected.equals(Converter.convert(actual, expected.getUnit())));
    }

    @Test
    public void testMetricTonneToImperial() {
        MetricMass metricMass = new MetricMass(1, massConverter.getUnit("tonne"));
        Object actual = Converter.convert(metricMass, massConverter.getUnit("stone"));

        ImperialMass expected = new ImperialMass(157.4730444177697, massConverter.getUnit("stone"));
        assertTrue("Tonne should be 157.4730444177697 stone.", expected.equals(actual) || expected.equals(Converter.convert(actual, expected.getUnit())));
    }
}
