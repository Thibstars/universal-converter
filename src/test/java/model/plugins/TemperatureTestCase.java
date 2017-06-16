package model.plugins;

import constants.AppConstants;
import controller.ConverterController;
import model.plugins.temperature.Celsius;
import model.plugins.temperature.Fahrenheit;
import model.plugins.temperature.Kelvin;
import model.plugins.temperature.TemperatureConverter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test case for the {@link model.plugins.temperature.TemperaturePlugin}.
 *
 * @author Thibault Helsmoortel
 */
public class TemperatureTestCase {

    private ConverterController controller;
    private TemperatureConverter converter;

    @Before
    public void setUp() throws Exception {
        this.controller = new ConverterController();
        for (Plugin plugin : controller.getPlugins()) {
            if (plugin.getName().contains(AppConstants.PLUGIN_TEMPERATURE)) {
                converter = (TemperatureConverter) plugin.getConverter();
            }
        }
    }

    @Test
    public void testCelsiusToCelsius() throws Exception {
        Celsius celsius = new Celsius(19.3, converter.getUnit("celsius"));
        Object actual = controller.convert(celsius, converter.getUnit("celsius"));

        Celsius expected = new Celsius(19.3, converter.getUnit("celsius"));
        assertEquals("19.3 Celsius must be 19.3 Celsius.", expected, actual);
    }

    @Test
    public void testCelsiusToFahrenheit() throws Exception {
        Celsius celsius = new Celsius(1d, converter.getUnit("celsius"));
        Object actual = controller.convert(celsius, converter.getUnit("fahrenheit"));

        Fahrenheit expected = new Fahrenheit(33.8, converter.getUnit("fahrenheit"));
        assertTrue("1 Celsius should be 33.8 Fahrenheit.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }

    @Test
    public void testCelsiusToKelvin() throws Exception {
        Celsius celsius = new Celsius(1d, converter.getUnit("celsius"));
        Object actual = controller.convert(celsius, converter.getUnit("kelvin"));

        Kelvin expected = new Kelvin(274.15, converter.getUnit("kelvin"));
        assertTrue("1 Celsius should be 274.15 Kelvin.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }

    @Test
    public void testFahrenheitToFahrenheit() throws Exception {
        Fahrenheit fahrenheit = new Fahrenheit(57.7, converter.getUnit("fahrenheit"));
        Object actual = controller.convert(fahrenheit, converter.getUnit("fahrenheit"));

        Fahrenheit expected = new Fahrenheit(57.7, converter.getUnit("fahrenheit"));
        assertEquals("57.7 Fahrenheit must be 57.7 Fahrenheit.", expected, actual);
    }

    @Test
    public void testFahrenheitToCelsius() throws Exception {
        Fahrenheit fahrenheit = new Fahrenheit(1d, converter.getUnit("fahrenheit"));
        Object actual = controller.convert(fahrenheit, converter.getUnit("fahrenheit"));

        Celsius expected = new Celsius(-17.22222222222222, converter.getUnit("celsius"));
        assertTrue("1 Fahrenheit should be -17.22222222222222 Celsius.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }

    @Test
    public void testFahrenheitToKelvin() throws Exception {
        Fahrenheit fahrenheit = new Fahrenheit(1d, converter.getUnit("fahrenheit"));
        Object actual = controller.convert(fahrenheit, converter.getUnit("kelvin"));

        Kelvin expected = new Kelvin(255.92777777777775, converter.getUnit("kelvin"));
        assertTrue("1 Fahrenheit should be 255.92777777777775 Kelvin.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }

    @Test
    public void testKelvinToKelvin() throws Exception {
        Kelvin kelvin = new Kelvin(288.15d, converter.getUnit("kelvin"));
        Object actual = controller.convert(kelvin, converter.getUnit("kelvin"));

        Kelvin expected = new Kelvin(288.15d, converter.getUnit("kelvin"));
        assertEquals("288.15 Kelvin must be 288.15 Kelvin.", expected, actual);
    }

    @Test
    public void testKelvinToCelsius() throws Exception {
        Kelvin kelvin = new Kelvin(1d, converter.getUnit("kelvin"));
        Object actual = controller.convert(kelvin, converter.getUnit("celsius"));

        Celsius expected = new Celsius(-272.15, converter.getUnit("celsius"));
        assertTrue("1 Kelvin should be -272.15 Celsius.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }

    @Test
    public void testKelvinToFahrenHeit() throws Exception {
        Kelvin kelvin = new Kelvin(1d, converter.getUnit("kelvin"));
        Object actual = controller.convert(kelvin, converter.getUnit("fahrenheit"));

        Fahrenheit expected = new Fahrenheit(-457.87, converter.getUnit("fahrenheit"));
        assertTrue("1 Kelvin should be -457.87 Fahrenheit.", expected.equals(actual) || expected.equals(controller.convert(actual, expected.getUnit())));
    }
}
