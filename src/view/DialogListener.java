package src.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JDialog;

/**
 * Generic Dialog ActionListener meant only for custom JDialogs.
 */
public class DialogListener implements ActionListener {
    private JDialog owner;
    private DialogStatus status;

    public DialogListener(JDialog owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = ((JComponent) e.getSource()).getName();
        switch (name) {
            case "AddItem.Add":
                status = DialogStatus.PROCEED;
                owner.dispose();
                break;
            case "AddItem.Cancel":
                status = DialogStatus.CANCEL;
                owner.dispose();
                break;
        }
    }

    public DialogStatus getStatus() {
        return status;
    }
}
