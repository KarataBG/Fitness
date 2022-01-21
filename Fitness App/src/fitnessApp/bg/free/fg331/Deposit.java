package fitnessApp.bg.free.fg331;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class Deposit {


    public Deposit() {


    }

    public static void deposit(JTable jTable) {
        PrintWriter writer;
        String path;

        int startIndex = 2;
        int endIndex = 9;
        int lastIndex = 8;
        int skip = 2;
        try {
            path = new File("").getCanonicalPath();
            writer = new PrintWriter(new BufferedWriter(new FileWriter(path + "\\depo.txt", false)));

            for (int j = 0; j < jTable.getRowCount(); j++) {
                for (int i = startIndex; i < endIndex; i += skip) {
                    Object o;
                    if ((o = jTable.getValueAt(j, i)) == null) {
                        writer.print("0");
                    } else writer.print(o);
                    if (i != lastIndex)
                        writer.print(" ");
                }
                writer.println("");
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void undeposit(JTable jTable) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();

        hashMap.put(0,2);
        hashMap.put(1,4);
        hashMap.put(2,6);
        hashMap.put(3,8);

        String path;

        int rowsAmount = 4;
        try {
            path = new File("").getCanonicalPath();

            String file = Utils.loadFileAsString(path + "\\depo.txt");
            String[] cells = file.split("\\s");

            if (cells.length == 1) {
                deposit(jTable);
                undeposit(jTable);
            } else {
                for (int j = 0; j < jTable.getRowCount(); j++) {
                    for (int i = 0; i < rowsAmount; i++) {
                        if (!cells[j * rowsAmount + i].equals("0")) {
                            jTable.setValueAt(cells[j * 4 + i], j, hashMap.get(i));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
