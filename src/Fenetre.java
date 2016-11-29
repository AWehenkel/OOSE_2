
import javax.swing.*;

public class Fenetre extends javax.swing.JFrame{
    Button myBut;

    public Fenetre(){
        this.setTitle("Ma première fenêtre Java");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.add(myBut);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        }
}