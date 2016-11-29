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

public class Button extends JButton implements MouseListener {
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
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        g.drawImage(currentImg,this.getWidth(),this.getHeight(),this.getWidth(),this.getHeight(), this);
    }
    //Méthode appelée lors du clic de souris
    public void mouseClicked(MouseEvent event){
        currentImg = img.elementAt(1);
    }

    //Méthode appelée lors du survol de la souris
    public void mouseEntered(MouseEvent event){
        currentImg = img.elementAt(2);
    }

    //Méthode appelée lorsque la souris sort de la zone du bouton
    public void mouseExited(MouseEvent event){
        currentImg = img.elementAt(0);
    }

    //Méthode appelée lorsque l'on presse le bouton gauche de la souris
    public void mousePressed(MouseEvent event){
        currentImg = img.elementAt(1);
    }

    //Méthode appelée lorsque l'on relâche le clic de souris
    public void mouseReleased(MouseEvent event){
        if((event.getY() > 0 && event.getY() < this.getHeight()) && (event.getX() > 0 && event.getX() < this.getWidth()))
            currentImg = img.elementAt(2);
        else
            currentImg = img.elementAt(0);
    }

}
