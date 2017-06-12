package model.plugins;

import constants.AppConstants;

/**
 * Class representing default plugins, that is, a plugin that is supported by default.
 * Subclasses will be automatically registered in the {@link model.Converter}.
 * <p>
 * NOTE: Do not use this class for external plugins. Extend directly from {@link Plugin} instead.
 *
 * @author Thibault Helsmoortel
 */
public abstract class DefaultPlugin extends Plugin {

    protected DefaultPlugin(String name, String author, Class... convertables) {
        super(AppConstants.PLUGIN_DEFAULT + name, author, convertables);
    }
}
