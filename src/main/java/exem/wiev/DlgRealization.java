package exem.wiev;

import exem.model.Farba;
import exem.model.Plate;
import exem.model.Realization;
import exem.store.FarbaDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DlgRealization extends JDialog implements IDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JSlider slider1;
    private JTextField textFieldCount;
    private Realization realization;

    public DlgRealization() {
        setContentPane(contentPane);
        setModal(true);
        setMinimumSize(new Dimension(400,220));
        setBounds(200, 200, 400, 220);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Реалізація");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        int count = slider1.getValue();
        Farba farba = (Farba) comboBox1.getSelectedItem();
        boolean flag = true;
        try {
            realization = new Realization(count,farba);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
            flag = false;
        }
        if(flag) {
            DlgRealization.this.setVisible(false);
            dispose();
        }
        dispose();
    }

    private void onCancel() {
        realization = null;
        DlgRealization.this.setVisible(false);
        dispose();
    }

    @Override
    public Object getObject() {
        return realization;
    }

    @Override
    public void setFarbDirectory(FarbaDirectory fd) {
        ComboBoxModel<Farba> model = new DefaultComboBoxModel<>(fd.getArr());
        comboBox1.setModel(model);
    }

    @Override
    public String toString() {
        return "Realization";
    }
}
