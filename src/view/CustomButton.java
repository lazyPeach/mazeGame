package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lorenzop on 12/18/16.
 */
public class CustomButton extends JButton {
    public CustomButton(String content, int width, int height) {
        super(content);

        setSize(width, height);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        setForeground(Color.white);
        setFont(new Font("", Font.BOLD, 30));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
