package exem.wiev;

import exem.event.IProductListener;
import exem.event.ProductEvent;
import exem.file.OpenAndSave;
import exem.model.IFarba;
import exem.store.FarbaDirectory;
import exem.store.ProductStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainGui {
    public JFrame frame;
    private JPanel MainPanel;
    private JTextArea textArea1;
    private JList list1;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu_operations = new JMenu("Операції");
    private JMenuItem menuItem_calc = new JMenuItem("Підрахунок");
    private JMenu menu = new JMenu("Файл");
    private JMenu menu_event = new JMenu("Події");
    private JMenuItem menuItem_save = new JMenuItem("Зберегти");
    private JMenuItem menuItem_open = new JMenuItem("Відкрити");
    private JMenuItem menuItem_addlistener = new JMenuItem("Додати listener");
    private JMenuItem menuItem_removelistener = new JMenuItem("Видалити listener");
    private JMenuItem menuItem_showLog = new JMenuItem("Показати log");

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
        menuBar.add(menu_event);
        menu.add(menuItem_open);
        menu.add(menuItem_save);
        menu_event.add(menuItem_addlistener);
        menu_event.add(menuItem_removelistener);
        menu_event.add(menuItem_showLog);
        menuBar.add(menu_operations);
        menu_operations.add(menuItem_calc);
        frame.setJMenuBar(menuBar);

        DefaultListModel <IDialog> model = new DefaultListModel<>();
        model.addElement(dlgDisc);
        model.addElement(dlgPlate);
        model.addElement(dlgRealization);
        model.addElement(dlgFarba);
        list1.setModel(model);

        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter("Log.txt", false));
        } catch (IOException e) {
            e.printStackTrace();
        }

        IProductListener pLis = new IProductListener() {
            @Override
            public void onProductEvent(ProductEvent e) {
                System.err.println(e);
                try {
                    BufferedWriter bf = new BufferedWriter(new FileWriter("Log.txt", true));
                    bf.write(e.toString());
                    bf.newLine();
                    bf.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

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

        menuItem_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OpenAndSave.save(fd, ps);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "File save error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        menuItem_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Object[] obj = OpenAndSave.open();
                    if(obj != null){
                        fd = (FarbaDirectory) obj[0];
                        ps = (ProductStore) obj[1];
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "File open error", "Error", JOptionPane.ERROR_MESSAGE);
                    fd = new FarbaDirectory();
                    ps = new ProductStore();
                }
                textArea1.setText(ps.toString());
            }

        });

        menuItem_addlistener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ps.addProductListener(pLis);
            }
        });

        menuItem_removelistener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ps.removeProductListener(pLis);
            }
        });

        menuItem_showLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, OpenAndSave.readLog(), "Log", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

}
