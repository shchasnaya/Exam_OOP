package exem.wiev;

import exem.model.IFarba;
import exem.store.FarbaDirectory;
import exem.store.ProductStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGui {
    public JFrame frame;
    private JPanel MainPanel;
    private JTextArea textArea1;
    private JList list1;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Операції");
    private JMenuItem menuItem_calc = new JMenuItem("Підрахунок");

    private FarbaDirectory fd = new FarbaDirectory();
    private ProductStore ps = new ProductStore();
    private DlgDisc dlgDisc = new DlgDisc();
    private DlgPlate dlgPlate = new DlgPlate();
    private DlgRealization dlgRealization = new DlgRealization();
    private DlgFarba dlgFarba = new DlgFarba();

    /**
     * Create the application
     */
    public MainGui() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 600, 400);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Exem, Щасна А.П., ПІ-181");
        frame.setContentPane(MainPanel);

        menuBar.add(menu);
        menu.add(menuItem_calc);
        frame.setJMenuBar(menuBar);

        DefaultListModel <IDialog> model = new DefaultListModel<>();
        model.addElement(dlgDisc);
        model.addElement(dlgPlate);
        model.addElement(dlgRealization);
        model.addElement(dlgFarba);
        list1.setModel(model);

        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                IDialog dlg = (IDialog) list1.getSelectedValue();
                dlg.setFarbDirectory(fd);
                dlg.setVisible(true);
                Object obj = dlg.getObject();
                if (obj != null) {
                    ps.add((IFarba) obj);
                }
                textArea1.setText(ps.toString());
            }
        });

        menuItem_calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float norm = 0.0f;
                float costs = 0.0f;

                for(IFarba hh : ps.getArr()) {
                    costs += hh.calcCost();
                    norm += hh.calcConsumption();
                }
                float sum = costs + norm;
                String result = "Витрати на фарбування деталей: " + sum +
                        "\nВитрати на реалізацію " + costs +
                        "\nЗагальна сума " + norm;
                JOptionPane.showMessageDialog(null, result, "Помилка", JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }

}
