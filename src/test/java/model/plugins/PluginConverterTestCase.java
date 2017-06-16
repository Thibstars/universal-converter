package model.plugins;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

/**
 * Test case for {@link PluginConverter}.
 *
 * @author Thibault Helsmoortel
 */
public class PluginConverterTestCase {

    private PluginConverter pluginConverter;

    @Before
    public void setUp() throws Exception {
        this.pluginConverter = mock(PluginConverter.class);
    }

    @Test
    public void shouldReturnNullWhenNoExistingUnit() throws Exception {
        assertNull("getUnit should return null.", pluginConverter.getUnit("qsldfkjqm"));
    }
}
