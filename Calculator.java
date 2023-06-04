package hello;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private double firstNumber;
    private String operator;
    private boolean isOperatorClicked;

    public Calculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.getContentPane().add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "CE"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String buttonText = ((JButton) event.getSource()).getText();
            
            if (buttonText.matches("[0-9]")) {
                if (isOperatorClicked) {
                    textField.setText("");
                    isOperatorClicked = false;
                }
                textField.setText(textField.getText() + buttonText);
            } else if (buttonText.matches("[+\\-*/]")) {
                if (!isOperatorClicked) {
                    firstNumber = Double.parseDouble(textField.getText());
                    operator = buttonText;
                    isOperatorClicked = true;
                }
            } else if (buttonText.equals(".")) {
                if (!textField.getText().contains(".")) {
                    textField.setText(textField.getText() + buttonText);
                }
            } else if (buttonText.equals("=")) {
                calculate();
            } else if (buttonText.equals("CE")) {
            	clear();
            }
        }
        
        private void calculate() {
            double secondNumber = Double.parseDouble(textField.getText());
            double result = 0.0;
            
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
            }
            
            textField.setText(String.valueOf(result));
            firstNumber = result;
            isOperatorClicked = false;
        }
    }
    
    private void clear() {
    	textField.setText("");
    	firstNumber = 0.0;
    	operator = "";
    	isOperatorClicked = false;
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Calculator window = new Calculator();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

