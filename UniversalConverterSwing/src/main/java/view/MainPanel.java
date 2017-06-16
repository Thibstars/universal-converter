package view;

import constants.AppConstants;
import controller.ConverterController;
import model.Convertible;
import model.Unit;
import model.plugins.Plugin;
import org.apache.commons.lang3.StringUtils;
import view.util.TextFieldValueChangeListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Class representing the main panel of the {@link MainFrame}.
 *
 * @author Thibault Helsmoortel
 */
public class MainPanel extends JPanel {

    private final ConverterController converterController;

    private JComboBox<Plugin> cbPlugin;
    private JComboBox<Unit> cbFrom;
    private JComboBox<Unit> cbTo;

    private JFormattedTextField tfFrom;
    private JLabel lblTo;

    private JButton btnInfo;

    public MainPanel() {
        this.converterController = new ConverterController();
        initLayout();
        addListeners();
    }

    private void initLayout() {
        setLayout(new BorderLayout());

        cbPlugin = new JComboBox<>();
        converterController.getPlugins().forEach(plugin -> cbPlugin.addItem(plugin));
        cbPlugin.setBounds(5, 0, 90, 20);
        cbFrom = new JComboBox<>();
        cbFrom.setBounds(5, 25, 90, 20);
        cbTo = new JComboBox<>();
        cbTo.setBounds(5, 75, 90, 20);
        setUnitComboBoxValues((Plugin) cbPlugin.getSelectedItem());

        NumberFormat format = NumberFormat.getNumberInstance();
        format.setGroupingUsed(false);
        format.setMaximumIntegerDigits(10);
        format.setRoundingMode(RoundingMode.HALF_UP);
        tfFrom = new JFormattedTextField(format);
        tfFrom.setToolTipText("From");
        lblTo = new JLabel();
        lblTo.setToolTipText("To");

        JPanel pnlFrom = new JPanel();
        pnlFrom.setLayout(new GridLayout(2, 1));
        pnlFrom.add(tfFrom);
        pnlFrom.add(cbFrom);
        JPanel pnlTo = new JPanel();
        pnlTo.setLayout(new GridLayout(2, 1));
        pnlTo.add(lblTo);
        pnlTo.add(cbTo);
        JPanel pnlConversion = new JPanel();
        pnlConversion.setLayout(new GridLayout(2, 1));
        pnlConversion.add(pnlFrom);
        pnlConversion.add(pnlTo);

        btnInfo = new JButton("About");

        add(cbPlugin, BorderLayout.PAGE_START);
        add(pnlConversion, BorderLayout.CENTER);
        add(btnInfo, BorderLayout.PAGE_END);

        setOpaque(true);
    }

    private void addListeners() {
        cbPlugin.addItemListener(e -> {
            Plugin plugin = (Plugin) e.getItem();
            setUnitComboBoxValues(plugin);
        });

        tfFrom.getDocument().addDocumentListener((TextFieldValueChangeListener) e -> tryPerformConversion());
        cbFrom.addActionListener(e -> tryPerformConversion());
        cbTo.addActionListener(e -> tryPerformConversion());

        btnInfo.addActionListener(e -> {
            StringBuilder about = new StringBuilder().
                    append(String.format("%s by %s", AppConstants.APP_NAME, AppConstants.APP_AUTHOR));
            for (Plugin plugin : converterController.getPlugins()) {
                about.append(String.format("\nPlugin \'%s\' by %s", plugin, plugin.getAuthor()));
            }
            about.append(String.format("\nApplication icon: %s", AppConstants.APP_ICON_CREDITS));
            JOptionPane.showMessageDialog(this, about);
        });
    }

    private boolean tryPerformConversion() {
        try {
            if (StringUtils.isNotEmpty(tfFrom.getText())) {
                Double.valueOf(tfFrom.getText());
                performConversion();
                return true;
            }
        } catch (Exception e1) {
            return false;
        }
        return false;
    }

    private void setUnitComboBoxValues(Plugin plugin) {
        cbFrom.removeAllItems();
        cbTo.removeAllItems();
        plugin.getConversions().forEach(unit -> {
            cbFrom.addItem(unit);
            cbTo.addItem(unit);
        });
    }

    private void performConversion() {
        double value = Double.valueOf(tfFrom.getText());
        Unit unitFrom = (Unit) cbFrom.getSelectedItem();
        Unit unitTo = (Unit) cbTo.getSelectedItem();

        Class sourceClass = unitFrom.getSystem();
        Object source = null;
        try {
            source = sourceClass.getDeclaredConstructors()[0].newInstance(value, unitFrom);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (source != null) {
            Convertible converted = converterController.convert(source, unitTo);
            lblTo.setText(converted.getValue().toString());
        }
    }
}
