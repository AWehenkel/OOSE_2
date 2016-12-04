import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Created by pierre on 11/29/16.
 */
/*
GridJPanel is a class which is used to manage specific JPanel
having a grid form.
 */

public class GridJPanel extends JPanel implements MouseListener{
    private int row,col;
    private int[][] table;
    private Vector<Image> imgs;

    private Vector<GridJPanelListener> listeners;

    /*
    IN: col: int, the number of collumns in the grid
        row: int, the number of rows in the grid
        base: Image, the image representing a case of the grid.
     */
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

    /*
    Overrided to follow the resizing of the frame.
     */
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

    /*
    Replaces the Image of the cell (col, row) by the image. Throw an IllegalArgumentException if the cell coordinate are not consistent.
     */
    public void draw(int col, int row, Image image) throws IllegalArgumentException{
        if(imgs.indexOf(image) == -1)
            imgs.addElement(image);
        if(col >= table.length || col < 0 || row > table[col].length || row < 0)
            throw new IllegalArgumentException(col, row);
        table[col][row] = imgs.indexOf(image);
        //this.repaint();
    }

    /*
    Add a grid listener.
     */
    public void addGridListener(GridJPanelListener listener){
        listeners.addElement(listener);
    }

    /*
    Remove a grid listener.
     */
    public void removeGridListener(GridJPanelListener listener){
        listeners.remove(listener);
    }

    //Overrided. It computes the cell where the click has been done and call the grid listeners.
    public void mouseClicked(MouseEvent event){
        int x=event.getX();
        int y=event.getY();
        for (GridJPanelListener listening : listeners){
            listening.gridListener((int)Math.floor(x/(getWidth()/col)),(int)Math.floor(y/(getHeight()/row)));
        }
    }

    public void mouseEntered(MouseEvent event){
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void mouseExited(MouseEvent event){
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void mousePressed(MouseEvent event){}

    public void mouseReleased(MouseEvent event){}
}
