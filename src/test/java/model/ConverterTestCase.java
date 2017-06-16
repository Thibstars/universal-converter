package model;

import constants.AppConstants;
import controller.ConverterController;
import model.plugins.Plugin;
import model.plugins.temperature.Celsius;
import model.plugins.temperature.TemperatureConverter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test case for {@link model.Converter}.
 *
 * @author Thibault Helsmoortel
 */
public class ConverterTestCase {

    private Plugin plugin;
    private ConverterController controller;
    private TemperatureConverter converter;

    @Before
    public void setUp() throws Exception {
        this.plugin = mock(Plugin.class);
        when(plugin.getName()).thenReturn("testPlugin");
        when(plugin.getAuthor()).thenReturn("testPlugin_Author");
        this.controller = new ConverterController();
        for (Plugin plugin : controller.getPlugins()) {
            if (plugin.getName().contains(AppConstants.PLUGIN_TEMPERATURE)) {
                converter = (TemperatureConverter) plugin.getConverter();
            }
        }
    }

    @Test
    public void shouldRegisterPlugin() throws Exception {
        Converter.registerPlugin(plugin);
        assertTrue(Converter.getPlugins().contains(plugin));
        Converter.deregisterPlugin(plugin); // Deregister for next tests
    }

    @Test
    public void shouldDeregisterPlugin() throws Exception {
        Converter.registerPlugin(plugin);
        Converter.deregisterPlugin(plugin);
        assertFalse(Converter.getPlugins().contains(plugin));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConvertWithInvalidConvertibleSource() throws Exception {
        Object invalidClassObject = new Object();
        controller.convert(invalidClassObject, converter.getUnit("celsius"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConvertWithInvalidUnit() throws Exception {
        Celsius celsius = new Celsius(19.3, converter.getUnit("celsius"));
        controller.convert(celsius, new Unit("thisDoesNotExist", Object.class, 87d, false));
    }
}
