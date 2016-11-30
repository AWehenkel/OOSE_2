import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Created by pierre on 11/29/16.
 */

public class GridJPanel extends JPanel implements MouseListener{
    private int row,col;
    private int[][] table;
    private Vector<Image> imgs;

    private Vector<GridJPanelListener> listeners;

    public GridJPanel(int col,int row,Image base){
        this.row = row;
        this.col = col;
        this.imgs = new Vector<>(1);
        this.imgs.addElement(base);
        this.listeners = new Vector<>();
        this.table = new int[col][row];

        for (int i=0; i < col ; i++){
            for (int j=0; j < row ; j++) {
                table[i][j] = 0;
            }
        }
        this.addMouseListener(this);
        setVisible(true);
    }

    public void paintComponent(Graphics g){
        g.fillRect(0, 0, getWidth(), getHeight());
        int width = (int)Math.floor(getWidth()/col);
        int height = (int)Math.floor(getHeight()/row);
        for (int i=0; i < col ; i++){
            for (int j=0; j < row ; j++){
                g.drawImage(imgs.elementAt(table[i][j]),i*width,j*height,width,height,this);
            }
        }

    }

    public void draw(int col,int row,Image image){
        if(imgs.indexOf(image) == -1)
            imgs.addElement(image);
        table[col][row] = imgs.indexOf(image);
        //this.repaint();
    }

    public void addGridListener(GridJPanelListener listener){
        listeners.addElement(listener);
    }

    //Méthode appelée lors du clic de souris
    public void mouseClicked(MouseEvent event){
        int x=event.getX();
        int y=event.getY();
        for (GridJPanelListener listening : listeners){
            listening.gridListener((int)Math.floor(x/(getWidth()/col)),(int)Math.floor(y/(getHeight()/row)));
        }
        //calcule colonne et row et appelle tout les listener.
    }

    //Méthode appelée lors du survol de la souris pour changer l'image d'une colone
    public void mouseEntered(MouseEvent event){
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    //Méthode appelée lorsque la souris sort de la zone d'une colonne
    public void mouseExited(MouseEvent event){
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    //Méthode appelée lorsque l'on presse le bouton gauche de la souris
    public void mousePressed(MouseEvent event){

    }

    //Méthode appelée lorsque l'on relâche le clic de souris
    public void mouseReleased(MouseEvent event){
    }
}
