import constants.AppConstants;
import controller.ConverterController;
import model.plugins.Plugin;
import model.plugins.length.ImperialLength;
import model.plugins.length.LengthConverter;
import model.plugins.length.MetricLength;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test case for the {@link model.plugins.length.LengthPlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class LengthTestCase {

    private ConverterController controller;
    private LengthConverter lengthConverter;

    @Before
    public void setUp() throws Exception {
        this.controller = new ConverterController();
        for (Plugin plugin : controller.getPlugins()) {
            if (plugin.getName().contains(AppConstants.PLUGIN_LENGTH)) {
                lengthConverter = (LengthConverter) plugin.getConverter();
            }
        }
    }

    @Test
    public void testMetricKMToM() {
        MetricLength metricLength = new MetricLength(1, lengthConverter.getUnit("kilometer"));
        Object actual = controller.convert(metricLength, lengthConverter.getUnit("meter"));

        MetricLength expected = new MetricLength(1000, lengthConverter.getUnit("meter"));
        assertEquals("KM should be 1000m.", expected, actual);
    }

    @Test
    public void testMetricMToKM() {
        MetricLength metricLength = new MetricLength(1000, lengthConverter.getUnit("meter"));
        Object actual = controller.convert(metricLength, lengthConverter.getUnit("kilometer"));

        MetricLength expected = new MetricLength(1, lengthConverter.getUnit("kilometer"));
        assertEquals("KM should be 1000m.", expected, actual);
    }

    @Test
    public void testImperialInchToFoot() {
        ImperialLength imperialLength = new ImperialLength(1, lengthConverter.getUnit("inch"));
        Object actual = controller.convert(imperialLength, lengthConverter.getUnit("foot"));

        ImperialLength expected = new ImperialLength(0.08333333333333331, lengthConverter.getUnit("foot"));
        assertEquals("Inch should be 0.083333 foot.", expected, actual);
    }

    @Test
    public void testImperialFootToInches() {
        ImperialLength imperialLength = new ImperialLength(1, lengthConverter.getUnit("foot"));
        Object actual = controller.convert(imperialLength, lengthConverter.getUnit("inch"));

        ImperialLength expected = new ImperialLength(12, lengthConverter.getUnit("inch"));
        assertEquals("Inch should be 0.083333 foot.", expected, actual);
    }

    @Test
    public void testImperialInchToMetric() {
        ImperialLength imperialLength = new ImperialLength(1, lengthConverter.getUnit("inch"));
        Object actual = controller.convert(imperialLength, lengthConverter.getUnit("centimeter"));

        MetricLength expected = new MetricLength(2.54, lengthConverter.getUnit("centimeter"));
        assertTrue("Inch should be 2.54 cm.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }

    @Test
    public void testImperialFootToMetric() {
        ImperialLength imperialLength = new ImperialLength(1, lengthConverter.getUnit("foot"));
        Object actual = controller.convert(imperialLength, lengthConverter.getUnit("centimeter"));

        MetricLength expected = new MetricLength(30.48, lengthConverter.getUnit("centimeter"));
        assertTrue("Foot should be 30.48 cm.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }

    @Test
    public void testMetricCentimeterToImperial() {
        MetricLength metricLength = new MetricLength(1, lengthConverter.getUnit("centimeter"));
        Object actual = controller.convert(metricLength, lengthConverter.getUnit("inch"));

        ImperialLength expected = new ImperialLength(0.3937007874015748, lengthConverter.getUnit("inch"));
        assertTrue("Cm should be 0.3937007874015748 inch.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }

    @Test
    public void testMetricMeterToImperial() {
        MetricLength metricLength = new MetricLength(1, lengthConverter.getUnit("meter"));
        Object actual = controller.convert(metricLength, lengthConverter.getUnit("foot"));

        ImperialLength expected = new ImperialLength(3.280839895013123, lengthConverter.getUnit("foot"));
        assertTrue("M should be 3.280839895013123 foot.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }
}
