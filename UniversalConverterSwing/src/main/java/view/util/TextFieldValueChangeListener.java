package view.util;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Interface representing a value change listener for {@link javax.swing.JTextField}.
 * This interface gives you the option to only override the update method, instead of 3 methods separately.
 * <p>
 * Eg.:
 * <pre>
 *     {@code
 *     textField.getDocument().addDocumentListener((TextFieldValueChangeListener) e -> {
 * // Your update code here
 * });
 * </pre>
 *
 * @author Thibault Helsmoortel
 */
@FunctionalInterface
public interface TextFieldValueChangeListener extends DocumentListener {

    @Override
    default void insertUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    default void removeUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    default void changedUpdate(DocumentEvent e) {
        update(e);
    }

    void update(DocumentEvent e);
}
