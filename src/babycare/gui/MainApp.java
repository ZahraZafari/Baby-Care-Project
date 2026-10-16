package babycare.gui;

import babycare.model.Infant;
import babycare.service.CareManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MainApp extends JFrame {
    private JTextField txtName, txtAge, txtWeight;
    private JTextArea txtOutput;
    private CareManager manager;

    public MainApp() {
        manager = new CareManager();
        
        setTitle("سامانه هوشمند مراقبت از نوزاد");
        setSize(550, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        Color lightBlueBg = new Color(230, 240, 255);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(lightBlueBg);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        inputPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        inputPanel.setBackground(lightBlueBg);

        Font labelFont = new Font("Tahoma", Font.BOLD, 14);
        Font inputFont = new Font("Tahoma", Font.PLAIN, 16);

        JLabel lblName = new JLabel("نام نوزاد:");
        lblName.setFont(labelFont);
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        txtName = new JTextField();
        txtName.setFont(inputFont);
        txtName.setHorizontalAlignment(JTextField.RIGHT);

        JLabel lblAge = new JLabel("سن (به ماه):");
        lblAge.setFont(labelFont);
        lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
        txtAge = new JTextField();
        txtAge.setFont(inputFont);
        txtAge.setHorizontalAlignment(JTextField.RIGHT);

        JLabel lblWeight = new JLabel("وزن (کیلوگرم):");
        lblWeight.setFont(labelFont);
        lblWeight.setHorizontalAlignment(SwingConstants.RIGHT);
        txtWeight = new JTextField();
        txtWeight.setFont(inputFont);
        txtWeight.setHorizontalAlignment(JTextField.RIGHT);

        JButton btnCalculate = new JButton("بررسی و نمایش وضعیت");
        btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCalculate.setBackground(new Color(150, 200, 255));
        btnCalculate.setForeground(Color.BLACK);

        JButton btnSave = new JButton("ذخیره گزارش در فایل");
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSave.setBackground(new Color(150, 200, 255));
        btnSave.setForeground(Color.BLACK);

        inputPanel.add(lblName);   inputPanel.add(txtName);
        inputPanel.add(lblAge);    inputPanel.add(txtAge);
        inputPanel.add(lblWeight); inputPanel.add(txtWeight);
        inputPanel.add(btnCalculate); inputPanel.add(btnSave);

        txtOutput = new JTextArea();
        txtOutput.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtOutput.setMargin(new Insets(15, 15, 15, 15));
        txtOutput.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txtOutput.setEditable(false);
        txtOutput.setLineWrap(true);
        txtOutput.setWrapStyleWord(true); 
        
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        TitledBorder border = BorderFactory.createTitledBorder("نتیجه بررسی");
        border.setTitleFont(new Font("Tahoma", Font.BOLD, 12));
        border.setTitleJustification(TitledBorder.CENTER);
        scrollPane.setBorder(border);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        btnCalculate.addActionListener(e -> {
            try {
                String name = txtName.getText().trim();
                String ageStr = txtAge.getText().trim();
                String weightStr = txtWeight.getText().trim();

                if (name.isEmpty() || ageStr.isEmpty() || weightStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "لطفاً تمام فیلدها (نام، سن و وزن) را پر کنید.", "خطا", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int age = Integer.parseInt(ageStr);
                double weight = Double.parseDouble(weightStr);

                Infant baby = new Infant(name, age, weight);
                txtOutput.setText(manager.generateReport(baby));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "لطفاً برای سن و وزن فقط عدد وارد کنید.", "خطا در فرمت اعداد", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSave.addActionListener(e -> {
            String content = txtOutput.getText().trim();
            
            if(content.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "گزارشی برای ذخیره وجود ندارد!\nابتدا اطلاعات را وارد کرده و دکمه 'بررسی' را بزنید.", 
                    "خطا در ذخیره", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try (FileWriter writer = new FileWriter("Baby_Report.txt")) {
                writer.write(content);
                JOptionPane.showMessageDialog(this, "گزارش با موفقیت در فایل Baby_Report.txt ذخیره شد.", "عملیات موفق", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "خطا در دسترسی به فایل برای ذخیره گزارش!", "خطای سیستم", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}
