import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Created by pierre on 11/27/16.
 */

public class Button extends JButton implements MouseListener{
    private Image currentImg;
    private Image clicked_image;
    private Image non_clicked_image;

    public Button(String non_clicked_image, String clicked_image){
        try{
            this.clicked_image = ImageIO.read(new File(clicked_image));
            this.non_clicked_image = ImageIO.read(new File(non_clicked_image));
            currentImg = this.non_clicked_image;
        }catch (IOException e){
            e.printStackTrace();
        }
        setBorderPainted(false);
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        super.setBackground(Color.BLACK);
        //setBackground(Color.BLACK);
        g.drawImage(currentImg,this.getWidth()/4,this.getHeight()/4,this.getWidth()/2,this.getHeight()/2, this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentImg = clicked_image;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentImg = non_clicked_image;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        currentImg = clicked_image;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        currentImg = non_clicked_image;
        repaint();
    }
}
