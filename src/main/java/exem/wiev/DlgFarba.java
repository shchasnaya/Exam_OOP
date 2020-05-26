package exem.wiev;

import exem.model.Farba;
import exem.store.FarbaDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DlgFarba extends JDialog implements IDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    private JTextField textFieldMark;
    private JTextField textFieldColor;
    private JTextField textFieldNorm;
    private JTextField textFieldPrice;
    private JSpinner spinner1;
    private Farba farba;
    private FarbaDirectory fd;

    public DlgFarba() {
        setContentPane(contentPane);
        setModal(true);
        setBounds(200, 200, 500, 300);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Фарба");
        spinner1.setValue(0.05);
        SpinnerModel numbers = new SpinnerNumberModel(0.5, 0.01, 5, 0.02);
        spinner1.setModel(numbers);
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
        String mark = textFieldMark.getText();
        String color = textFieldColor.getText();
        float consumption = Float.parseFloat(spinner1.getValue().toString());
        float price = Float.parseFloat(textFieldPrice.getText());
        try {
            fd.add(new Farba(mark,color,consumption,price));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        DlgFarba.this.setVisible(false);
        dispose();
    }

    private void onCancel() {
       // farba = null;
        DlgFarba.this.setVisible(false);
        dispose();
    }

    @Override
    public Object getObject() {
        return farba;
    }

    @Override
    public void setFarbDirectory(FarbaDirectory wd) {
        this.fd = wd;
        textArea1.setText(wd.toString());
    }

    @Override
    public String toString() {
        return "Farba";
    }
}
