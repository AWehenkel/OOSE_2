import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Created by pierre on 11/27/16.
 */
/*This class is used to design button with image*/

public class Button extends JButton implements MouseListener{
    private Image currentImg;
    private Image clicked_image;
    private Image non_clicked_image;

    /*
    IN: non_clicked_image: String, the path to an image which will be used when the button is not clicked, neither hover.
        clicked_images: String, the path to an image which will be used when the button is clicked, or hover.
     */
    public Button(String non_clicked_image, String clicked_image){
        try{
            this.clicked_image = ImageIO.read(new File(clicked_image));
            this.non_clicked_image = ImageIO.read(new File(non_clicked_image));
            currentImg = this.non_clicked_image;
        }catch (IOException e){
            e.printStackTrace();
        }
        setBorderPainted(false);

        //The button needs to react to its mouse events.
        addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        g.drawImage(currentImg, getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2, this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        currentImg = clicked_image;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentImg = non_clicked_image;
        repaint();
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
