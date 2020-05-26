package exem.wiev;

import exem.model.Disc;
import exem.model.Farba;
import exem.store.FarbaDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DlgDisc extends JDialog implements IDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldName;
    private JTextField textFieldRadius;
    private JComboBox<Farba> comboBox1;
    private Disc disc;

    public DlgDisc() {
        setContentPane(contentPane);
        setModal(true);
        setBounds(200, 200, 525, 170);
        setMaximumSize(new Dimension(252,170));
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Диск");

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
        String name = textFieldName.getText();
        float radius = Float.parseFloat(textFieldRadius.getText());

        Farba farba = (Farba) comboBox1.getSelectedItem();
        boolean flag = true;
        try {
            disc = new Disc(name,farba,radius);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
            flag = false;
        }
        if(flag) {
            DlgDisc.this.setVisible(false);
            dispose();
        }
    }

    private void onCancel() {
        disc = null;
        DlgDisc.this.setVisible(false);
        dispose();
    }

    @Override
    public Object getObject() {
        return disc;
    }

    @Override
    public void setFarbDirectory(FarbaDirectory fd) {
        ComboBoxModel<Farba> model = new DefaultComboBoxModel<>(fd.getArr());
        comboBox1.setModel(model);
    }

    @Override
    public String toString() {
        return "Disc";
    }
}
