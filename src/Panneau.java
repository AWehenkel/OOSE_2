import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    String text;
    public Panneau(String text){
        this.text = text;
    }

    public void paintComponent(Graphics g){
        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString(text, 10, 20);
    }
}