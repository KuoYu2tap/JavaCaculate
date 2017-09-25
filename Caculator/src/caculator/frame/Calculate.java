package caculator.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculate extends JPanel {

        private JButton display;
        private JPanel panel;
        private Font font = new Font(Font.MONOSPACED,Font.BOLD,20);

        private double result;
        private String lastCmd;
        private boolean start;

        public Calculate(){
            setLayout(new BorderLayout());

            result = 0;
            lastCmd = "=";
            start = true;

            display = new JButton("0");
            display.setEnabled(false);
            display.setFont(font);
            add(display, BorderLayout.NORTH);

            ActionListener insert = new InsertAction();
            ActionListener cmd = new CmdAction();
            ActionListener clr = new ClrAction();

            panel = new JPanel();
            panel.setLayout(new GridLayout(4,4));

            addButton("7",insert);
            addButton("8",insert);
            addButton("9",insert);
            addButton("/",cmd);
            addButton("C",clr);

            addButton("4",insert);
            addButton("5",insert);
            addButton("6",insert);
            addButton("*",cmd);
            addButton("CE",clr);

            addButton("1",insert);
            addButton("2",insert);
            addButton("3",insert);
            addButton("-",cmd);
            addButton("",cmd);

            addButton("0",insert);
            addButton(".",insert);
            addButton("=",cmd);
            addButton("+",cmd);
            addButton("",cmd);

            add(panel, BorderLayout.CENTER);
        }

        public void caculate(double x) {
            if(lastCmd.equals("+"))      result += x;
            else if(lastCmd.equals("-")) result -= x;
            else if(lastCmd.equals("*")) result *= x;
            else if(lastCmd.equals("=")) result = x;
            else if(lastCmd.equals("/")){
                if(Double.doubleToLongBits(x) !=Double.doubleToLongBits(0.0))
                    result /= x;
                else {
                    result = 0.0;
                }
            }

            display.setText("" + result);

        }

        private void addButton(String label, ActionListener listener)
        {
            JButton button = new JButton(label);
            button.setFont(font);
            button.addActionListener(listener);
            panel.add(button);
        }

        private class InsertAction implements ActionListener
        {
            public void actionPerformed(ActionEvent event){
                String input = event.getActionCommand();
                if(start){
                    display.setText("");
                    start = false;
                }
                display.setText(display.getText() + input);
            }
        }

        private class CmdAction implements ActionListener
        {
            public void actionPerformed(ActionEvent event){
                String cmd = event.getActionCommand();

                if(start)
                {
                    if (cmd.equals("-"))
                    {
                        display.setText(cmd);
                        start = false;
                    }
                    else lastCmd = cmd;
                }
                else {
                    caculate(Double.parseDouble(display.getText()));
                    lastCmd = cmd;
                    start = true;
                }
            }
        }

        private class ClrAction implements ActionListener
        {
            public void actionPerformed(ActionEvent event){
                String clr = event.getActionCommand();
                String show = display.getText();

                if(clr.equals("C")){
                    int len = show.length();
                    if(len==1){
                        display.setText("0");
                    }
                    else {
                        show = show.substring(len-1);
                        result = Double.parseDouble(show);
                        display.setText(show);
                    }
                }
                if(clr.equals("CE")){
                    display.setText("0");
                    result=0;
                }
            }
        }
}

