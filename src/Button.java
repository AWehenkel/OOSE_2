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
    private Vector<Image> img;
    private Image currentImg;

    public Button(String file1){
        img = new Vector<>(3);
        try{
            img.addElement(ImageIO.read(new File(file1)));
        }catch (IOException e){
            e.printStackTrace();
        }
        currentImg = img.elementAt(0);
        this.addMouseListener(this);

    }

    public void paintComponent(Graphics g){
        super.setBackground(Color.BLACK);
        //setBackground(Color.BLACK);
        g.drawImage(currentImg,this.getWidth()/4,this.getHeight()/4,this.getWidth()/2,this.getHeight()/2, this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        setBackground(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
