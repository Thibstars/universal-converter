package model.plugins.util;

import org.junit.Test;

/**
 * Test case for {@link PluginUtils}.
 *
 * @author Thibault Helsmoortel
 */
public class PluginUtilsTestCase {

    @Test(expected = IllegalAccessException.class)
    public void shouldNotInstantiateInaccessiblePlugin() throws Exception {
        PluginUtils.createPluginInstance(IncorrectAccessorsPlugin.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateDefaultPluginExternally() throws Exception {
        PluginUtils.createPluginInstance(ExternalDefaultPlugin.class);
    }
}
