import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

public class Button extends JButton {
    private Vector<Image> img;
    private Image currentImg;

    public Button(String file1,String file2,String file3){
        img = new Vector<>(3);
        try{
            img.addElement(ImageIO.read(new File(file1)));
            img.addElement(ImageIO.read(new File(file2)));
            img.addElement(ImageIO.read(new File(file3)));
        }catch (IOException e){
            e.printStackTrace();
        }
        currentImg = img.elementAt(0);
    }

    public void paintComponent(Graphics g){
        g.drawImage(currentImg,0,0,this.getWidth(),this.getHeight(), this);
    }

}
