package view.util;

import javax.swing.*;

/**
 * Utils class for fonts.
 *
 * @author Thibault Helsmoortel
 */
public final class FontUtils {

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
}
