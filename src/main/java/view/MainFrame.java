package view;

import constants.AppConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Class representing the main frame of the application.
 *
 * @author Thibault Helsmoortel
 */
public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super(AppConstants.APP_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 400);
        add(new MainPanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
