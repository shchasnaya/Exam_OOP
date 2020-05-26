package exem.file;

import javax.swing.*;
import java.io.*;

import exem.store.ProductStore;
import exem.store.FarbaDirectory;


    public class OpenAndSave {
        public static void save(FarbaDirectory fd, ProductStore ps) throws Exception{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFileChooser dialog = new JFileChooser();
            dialog.setDialogTitle("Виберіть файл для збереження");
            dialog.setApproveButtonText("Збетегти");
            dialog.showOpenDialog(null);
            File f = dialog.getSelectedFile();
            if(f != null){
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(fd);
                    oos.writeObject(ps);
                    oos.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        public static Object[] open() throws Exception{
            JFileChooser dialog = new JFileChooser();
            dialog.setDialogTitle("Виберіть файл для відкриття");
            dialog.setApproveButtonText("Відкрити");
            dialog.showOpenDialog(null);
            File f = dialog.getSelectedFile();
            if(f != null){
                Object[] obj = new Object[2];
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                obj[0] = ois.readObject();
                obj[1] = ois.readObject();
                ois.close();
                return obj;
            }else return null;

        }


        public static String readLog(){
            StringBuilder text = new StringBuilder();
            try{
                BufferedReader reader = new BufferedReader(new FileReader("Log.txt"));
                String s;
                while ((s = reader.readLine()) != null){
                    text.append(s);
                    text.append("\n");
                }
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            return text.toString();
        }
    }

