import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends javax.swing.JFrame implements GridJPanelListener{
    private GridJPanel my_grid;
    Image img;


    public Fenetre() {
        try{
            img = ImageIO.read(new File("src/img/disk_black.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        my_grid= new GridJPanel(400,400,4,4,img);
        my_grid.addGridListener(this);
        this.setTitle("Button interaction");
        this.setSize(800, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(my_grid);

        this.setVisible(true);
    }

    @Override
    public void gridListener(int row, int col) {
        System.out.println("here");
        System.out.println(row+','+col);
    }
}