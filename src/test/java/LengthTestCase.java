import model.Converter;
import model.plugins.length.ImperialLength;
import model.plugins.length.MetricLength;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by thibault.helsmoortel on 07-Jun-17.
 */
public class LengthTestCase {

    @Test
    public void testMetricKMToM() {
        MetricLength metricLength = new MetricLength(1, MetricLength.Unit.KILOMETER);
        Object actual = Converter.convert(metricLength, MetricLength.class, MetricLength.Unit.METER);

        MetricLength expected = new MetricLength(1000, MetricLength.Unit.METER);
        assertEquals("KM should be 1000m.", expected, actual);
    }

    @Test
    public void testMetricMToKM() {
        MetricLength metricLength = new MetricLength(1000, MetricLength.Unit.METER);
        Object actual = Converter.convert(metricLength, MetricLength.class, MetricLength.Unit.KILOMETER);

        MetricLength expected = new MetricLength(1, MetricLength.Unit.KILOMETER);
        assertEquals("KM should be 1000m.", expected, actual);
    }

    @Test
    public void testImperialInchToFoot() {
        ImperialLength imperialLength = new ImperialLength(1, ImperialLength.Unit.INCH);
        Object actual = Converter.convert(imperialLength, ImperialLength.class, ImperialLength.Unit.FOOT);

        ImperialLength expected = new ImperialLength(0.08333333333333331, ImperialLength.Unit.FOOT);
        assertEquals("Inch should be 0.083333 foot.", expected, actual);
    }

    @Test
    public void testImperialFootToInches() {
        ImperialLength imperialLength = new ImperialLength(1, ImperialLength.Unit.FOOT);
        Object actual = Converter.convert(imperialLength, ImperialLength.class, ImperialLength.Unit.INCH);

        ImperialLength expected = new ImperialLength(12, ImperialLength.Unit.INCH);
        assertEquals("Inch should be 0.083333 foot.", expected, actual);
    }

    @Test
    public void testImperialInchToMetric() {
        ImperialLength imperialLength = new ImperialLength(1, ImperialLength.Unit.INCH);
        Object actual = Converter.convert(imperialLength, MetricLength.class);

        MetricLength expected = new MetricLength(2.54, MetricLength.Unit.CENTIMETER);
        assertTrue("Inch should be 2.54 cm.", expected.equals(actual) || expected.equals(Converter.convert(actual, MetricLength.class, expected.getUnit())));
    }

    @Test
    public void testImperialFootToMetric() {
        ImperialLength imperialLength = new ImperialLength(1, ImperialLength.Unit.FOOT);
        Object actual = Converter.convert(imperialLength, MetricLength.class);

        MetricLength expected = new MetricLength(30.48, MetricLength.Unit.CENTIMETER);
        assertTrue("Foot should be 30.48 cm.", expected.equals(actual) || expected.equals(Converter.convert(actual, MetricLength.class, expected.getUnit())));
    }

    @Test
    public void testMetricCentimeterToImperial() {
        MetricLength metricLength = new MetricLength(1, MetricLength.Unit.CENTIMETER);
        Object actual = Converter.convert(metricLength, ImperialLength.class);

        ImperialLength expected = new ImperialLength(0.3937007874015748, ImperialLength.Unit.INCH);
        assertTrue("Cm should be 0.3937007874015748 inch.", expected.equals(actual) || expected.equals(Converter.convert(actual, ImperialLength.class, expected.getUnit())));
    }

    @Test
    public void testMetricMeterToImperial() {
        MetricLength metricLength = new MetricLength(1, MetricLength.Unit.METER);
        Object actual = Converter.convert(metricLength, ImperialLength.class);

        ImperialLength expected = new ImperialLength(3.280839895013123, ImperialLength.Unit.FOOT);
        assertTrue("M should be 3.280839895013123 foot.", expected.equals(actual) || expected.equals(Converter.convert(actual, ImperialLength.class, expected.getUnit())));
    }
}
