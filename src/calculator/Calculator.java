package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Calculator extends JFrame implements ActionListener {

    private DecimalFormat df = new DecimalFormat("#.000");
    private String[] symbols = new String[]{
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "Adv", "="
    };

    private int operator = 0; // 1 = addition, 2 = subtraction, 3 = multiplication, 4 = division
    private JPanel panel = new JPanel(new BorderLayout(5, 5));
    private JPanel btnPanel = new JPanel(new GridLayout(5, 5, 2, 2));
    private JButton[] btns = new JButton[symbols.length];
    private JTextArea screen = new JTextArea(5, 40);
    private double firstNum = 0, secondNum = 0;
    private JTextField calculatingTf = new JTextField(40);

    public Calculator() {
        init();
    }

    private void init() {
        screen.setFont(new Font("Times New Roman", Font.BOLD, 18));
        setTitle("Calculator");
        screen.setBackground(Color.BLACK);
        panel.setBackground(Color.BLACK);
        btnPanel.setBackground(Color.BLACK);
        screen.setForeground(Color.WHITE);
        screen.setEditable(false);

        // Create and add buttons
        for (int i = 0; i < btns.length; i++) {
            btns[i] = new JButton(symbols[i]);
            btns[i].setOpaque(true);
            btns[i].setBorderPainted(false);
            btns[i].setBackground(Color.BLACK);
            btns[i].setForeground(Color.WHITE);
            btns[i].addActionListener(this);
            btnPanel.add(btns[i]);
        }

        // Set up the panel
        calculatingTf.setForeground(Color.WHITE);
        calculatingTf.setBackground(Color.BLACK);
        calculatingTf.setEditable(false);

        panel.add(calculatingTf, BorderLayout.SOUTH);
        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(screen, BorderLayout.NORTH);
        add(panel);
        setSize(340, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case ".":
                if (!screen.getText().contains(".")) {
                    screen.setText(screen.getText() + ".");
                }
                break;
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                screen.setText(screen.getText() + cmd);
                break;
            case "+":
                if (!screen.getText().isEmpty()) {
                    firstNum = Double.parseDouble(screen.getText());
                    operator = 1; // Addition
                    screen.setText("");
                }
                break;
            case "-":
                if (!screen.getText().isEmpty()) {
                    firstNum = Double.parseDouble(screen.getText());
                    operator = 2; // Subtraction
                    screen.setText("");
                }
                break;
            case "×":
                if (!screen.getText().isEmpty()) {
                    firstNum = Double.parseDouble(screen.getText());
                    operator = 3; // Multiplication
                    screen.setText("");
                }
                break;
            case "÷":
                if (!screen.getText().isEmpty()) {
                    firstNum = Double.parseDouble(screen.getText());
                    operator = 4; // Division
                    screen.setText("");
                }
                break;
            case "%":
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    screen.setText(df.format(num / 100));
                }
                break;
            case "+/-":
                if (!screen.getText().isEmpty()) {
                    double neg = Double.parseDouble(screen.getText());
                    screen.setText(df.format(neg * -1));
                }
                break;
            case "AC":
                screen.setText("");
                calculatingTf.setText(""); // Clear additional text field
                firstNum = 0;
                secondNum = 0;
                operator = 0;
                break;
            case "=":
                if (!screen.getText().isEmpty()) {
                    secondNum = Double.parseDouble(screen.getText());
                    switch (operator) {
                        case 1: // Addition
                            screen.setText(df.format(firstNum + secondNum));
                            calculatingTf.setText(firstNum + " + " + secondNum + " = " + df.format(firstNum + secondNum));
                            break;
                        case 2: // Subtraction
                            screen.setText(df.format(firstNum - secondNum));
                            calculatingTf.setText(firstNum + " - " + secondNum + " = " + df.format(firstNum - secondNum));
                            break;
                        case 3: // Multiplication
                            screen.setText(df.format(firstNum * secondNum));
                            calculatingTf.setText(firstNum + " × " + secondNum + " = " + df.format(firstNum * secondNum));
                            break;
                        case 4: // Division
                            if (secondNum != 0) {
                                screen.setText(df.format(firstNum / secondNum));
                                calculatingTf.setText(firstNum + " ÷ " + secondNum + " = " + df.format(firstNum / secondNum));
                            } else {
                                screen.setText("Error"); // Handle division by zero
                                calculatingTf.setText("Error");
                            }
                            break;
                        default:
                            screen.setText("Error");
                    }
                }
                break;
            default:
                // Handle unexpected cases
                break;
        }
    }
}
