import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by antoinewehenkel on 29/11/16.
 */
public class Connect4Window extends javax.swing.JFrame implements GridJPanelListener {

    private GameLogic gl;
    int col, row;
    private GridJPanel board_pan;
    private ControlJPanel control_pan;
    private HashMap<Disc, Image> discs;

    public Connect4Window(GameLogic gl, int width, int height, int col, int row, String empty, String yellow, String red){
        this.gl = gl;
        this.col = col;
        this.row = row;
        getContentPane().setBackground(Color.black);

        try{
            discs = new HashMap<>();
            discs.put(Disc.None, ImageIO.read(new File(empty)));
            discs.put(Disc.Red, ImageIO.read(new File(red)));
            discs.put(Disc.Yellow, ImageIO.read(new File(yellow)));
        }
        catch (IOException |NullPointerException e) {
            e.printStackTrace();
        }

        board_pan = new GridJPanel(col, row, discs.get(Disc.None));
        board_pan.addGridListener(this);
        ImageIcon yel_icon = new ImageIcon(yellow);
        ImageIcon red_icon = new ImageIcon(red);
        Vector<ImageIcon> icons = new Vector<>();
        icons.add(yel_icon);
        icons.add(red_icon);
        control_pan = new ControlJPanel(icons);
        this.setTitle("Button interaction");
        this.setSize(width, height);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(1,2);
        setLayout(layout);
        this.add(board_pan);
        this.add(control_pan);
        this.setVisible(true);


    }

    @Override
    public void paint(Graphics g){
        Dimension real_dim = getContentPane().getSize();
        int height = (int) real_dim.getHeight();
        int width = (int) real_dim.getWidth();
        int dim = height < width/2 ? height : width/2;
        board_pan.setSize(dim, dim);
        control_pan.setSize(width/2, height);
        super.paint(g);
    }


    public void updateBoard(Disc[][] board) throws IllegalArgumentException{
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++) {
                if (i > row || i < 0 || j > col || j < 0)
                    throw new IllegalArgumentException(i, j);
                else
                    board_pan.draw(j, i, discs.get(board[i][j]));
            }
    }

    public void setTimer(int seconds){
        control_pan.setTimer(seconds);
    }

    public void setMoveCounter(int nb_moves){
        control_pan.setMoves(nb_moves);
    }

    public void giveTurn(Disc playerColor){
        if(playerColor == Disc.Red)
            control_pan.setPlayer(1);
        else if(playerColor == Disc.Yellow)
            control_pan.setPlayer(0);
        else
            System.out.println("throw exception");
    }

    @Override
    public void gridListener(int col, int row) {
        gl.play(col);
    }

}