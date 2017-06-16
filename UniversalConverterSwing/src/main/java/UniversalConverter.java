import view.MainFrame;
import view.util.FontUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Main entry point of the application
 *
 * @author Thibault Helsmoortel
 */
public final class UniversalConverter {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
        FontUtils.setUIFont(new javax.swing.plaf.FontUIResource("SansSerif", Font.PLAIN, 20));
    }
}
