package caculator.frame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

        EventQueue.invokeLater(()->{
            JFrame frame = new SizedFrame();
            JPanel c = new Calculate();
            frame.setContentPane(c);
            frame.setTitle("Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}


class SizedFrame extends JFrame
{
    public SizedFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenH = screenSize.height;
        int screenW = screenSize.width;

        setSize(screenW / 2, screenH / 2);
        setLocationByPlatform(true);
    }
}
