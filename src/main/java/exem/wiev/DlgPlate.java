package exem.wiev;

import exem.model.Disc;
import exem.model.Farba;
import exem.model.Plate;
import exem.store.FarbaDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class
DlgPlate extends JDialog implements IDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JTextField textFieldName;
    private JTextField textFieldLength;
    private JTextField textFieldWidth;
    private Plate plate;

    public DlgPlate() {
        setContentPane(contentPane);
        setModal(true);
        setMaximumSize(new Dimension(252,200));
        setBounds(200, 200, 525, 200);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Пластина");

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
        float length = Float.parseFloat(textFieldLength.getText());
        float width = Float.parseFloat(textFieldWidth.getText());
        Farba farba = (Farba) comboBox1.getSelectedItem();
        boolean flag = true;
        try {
            plate = new Plate(name,farba,length,width);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
            flag = false;
        }
        if(flag) {
            DlgPlate.this.setVisible(false);
            dispose();
        }
        dispose();
    }

    private void onCancel() {
        plate = null;
        DlgPlate.this.setVisible(false);
        dispose();
    }

    @Override
    public Object getObject() {
        return plate;
    }

    @Override
    public void setFarbDirectory(FarbaDirectory fd) {
        ComboBoxModel<Farba> model = new DefaultComboBoxModel<>(fd.getArr());
        comboBox1.setModel(model);
    }

    @Override
    public String toString() {
        return "Plate";
    }
}
