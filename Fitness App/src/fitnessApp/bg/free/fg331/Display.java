package fitnessApp.bg.free.fg331;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Display extends JFrame {

    //    private final Commander commander;
    //    int width = 900;
//    int height = 1060;
    JTable jTable;
    String[] columnNames = {"Дата", "Ден", "Коремни", "Нужни", "Клекове", "Нужни", "Лицеви", "Нужни", "Колело", "Минути"};
    int temp = 0;

    Display(Commander commander) {
//        this.commander = commander;

        //настройки на JFrame
        setTitle("Fitness");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(width, height));
//        setMinimumSize(new Dimension(width, height));
        setVisible(true);
        setLocationRelativeTo(null);
        this.setLocation(0, 0);

        //
        JButton button = new JButton("Enter");
        button.setMinimumSize(new Dimension(100, 100));
        button.addActionListener(e -> Deposit.deposit(jTable));

        JLabel label = new JLabel("2 Октомври - 9 Януари");

        int today = LocalDate.now().getDayOfYear();
//        int year = Calendar.getInstance().get(Calendar.YEAR);
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.DAY_OF_MONTH, 9);
        gc.set(Calendar.MONTH, Calendar.JANUARY);
        gc.set(Calendar.YEAR, Calendar.YEAR + 1);
        int endDay = gc.get(Calendar.DAY_OF_YEAR);

        Calendar c = Calendar.getInstance();
//        c.setTime(new Date(2021, Calendar.OCTOBER,5));
        c.set(2021, Calendar.OCTOBER, 2);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM");


        label.setText(label.getText() + " Оставащи дни " + (endDay + 365 - today));

        jTable = new JTable(new DefaultTableModel(columnNames, 100)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column % 2 != 1;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);

                // Rows alternate in colour for readability.
                if (row == temp) {
                    c.setBackground(Color.LIGHT_GRAY);
                } else {
                    c.setBackground(Color.WHITE);
                }

                if (isRowSelected(row)) {
                    c.setBackground(new Color(106, 185, 229));
                }

                return c;
            }
        };

        for (int i = 0; i < jTable.getRowCount(); i++) {
            if (LocalDate.now().format(formatter1).equals(formatter.format(c.getTime())))
                temp = i;
            jTable.setValueAt(formatter.format(c.getTime()), i, 0);
            c.add(Calendar.DATE, 1);
        }

        for (int i = 0; i < jTable.getRowCount(); i++) {
            jTable.setValueAt("Ден " + (i + 1), i, 1);
        }

        for (int i = 0; i < jTable.getRowCount(); i++) {
            for (int j = 3; j <= 7; j += 2) {
                jTable.setValueAt(i <= 30 ? "50" : "100", i, j);
            }
            jTable.setValueAt(i <= 30 ? "60" : "90", i, 9);
        }

        Deposit.undeposit(jTable);

        JScrollPane scrollPane = new JScrollPane(jTable);

        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipady = 30;
        constraints.ipadx = getWidth();
        jPanel.add(button, constraints);

        constraints.gridx = 1;
        jPanel.add(label);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        jPanel.add(scrollPane, constraints);

//        System.out.println(getWidth());

        add(jPanel);

        pack();
    }
//    public boolean isNumeric(String str) {
//        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
//    }
}
