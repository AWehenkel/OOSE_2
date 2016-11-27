import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by pierre on 11/27/16.
 */
public class Button extends JPanel {
    private String file;
    public Button(String file){
        this.file = file;
    }
    public void paintComponent(Graphics g){
        try{
            Image img = ImageIO.read(new File(file));
            g.drawImage(img, 0, 0, this);
        }catch (IOException e){

        }
    }

}
