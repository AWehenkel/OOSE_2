import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Created by pierre on 11/29/16.
 */

public class GridJPanel extends JPanel implements MouseListener{
    private int height,width,row,col;
    private int[][] table;
    private Vector<Image> imgs;
    private Vector<GridJPanelListener> listeners;

    public GridJPanel(int width,int height,int col,int row,Image img){
        this.height = height;
        this.width = width;
        this.row = row;
        this.col = col;
        this.imgs = new Vector<>(1);
        this.imgs.addElement(img);
        this.listeners = new Vector<>();
        this.table = new int[col][row];
        for (int i=0; i < col ; i++){
            for (int j=0; j < row ; j++) {
                table[i][j] = 0;
            }
        }
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (int i=0; i < col ; i++){
            for (int j=0; j < row ; j++){
                g.drawImage(imgs.elementAt(table[i][j]),i*width/col,j*height/row,width/col,height/row,this);
            }
        }
    }

    public void draw(int col,int row,Image image){
        imgs.addElement(image);
        table[col][row] = imgs.indexOf(image);
        this.repaint();
    }

    public void addGridListener(GridJPanelListener listener){
        listeners.addElement(listener);
    }
    //Méthode appelée lors du clic de souris
    public void mouseClicked(MouseEvent event){
        int x=event.getX();
        int y=event.getY();
        for (GridJPanelListener listening : listeners){
            listening.gridListener((int)Math.floor(x/(width/col)),(int)Math.floor(y/(height/row)));
        }
        //calcule colonne et row et appelle tout les listener.
    }

    //Méthode appelée lors du survol de la souris pour changer l'image d'une colone
    public void mouseEntered(MouseEvent event){

    }

    //Méthode appelée lorsque la souris sort de la zone d'une colonne
    public void mouseExited(MouseEvent event){

    }

    //Méthode appelée lorsque l'on presse le bouton gauche de la souris
    public void mousePressed(MouseEvent event){

    }

    //Méthode appelée lorsque l'on relâche le clic de souris
    public void mouseReleased(MouseEvent event){
    }
}
