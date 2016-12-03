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
public class Connect4Window extends javax.swing.JFrame implements GridJPanelListener, ControlJPanelListener {

    private GameLogic gl;
    private int col, row;
    private GridJPanel board_pan;
    private ControlJPanel control_pan;
    private HashMap<Disc, Image> discs;
    private boolean grid_added;
    private HashMap<Disc, Image> win_images;
    private JLabel win_message;
    private Disc curr_winner;

    public Connect4Window(GameLogic gl, int width, int height, int col, int row, String empty, String yellow, String red, String y_win, String r_win){
        this.gl = gl;
        this.col = col;
        this.row = row;

        getContentPane().setBackground(Color.black);

        try{
            discs = new HashMap<>();
            discs.put(Disc.None, ImageIO.read(new File(empty)));
            discs.put(Disc.Red, ImageIO.read(new File(red)));
            discs.put(Disc.Yellow, ImageIO.read(new File(yellow)));

            win_images = new HashMap<>();
            win_images.put(Disc.Red, ImageIO.read(new File(r_win)));
            win_images.put(Disc.Yellow, ImageIO.read(new File(y_win)));
        }
        catch (IOException |NullPointerException e) {
            e.printStackTrace();
        }
        win_message = new JLabel();
        board_pan = new GridJPanel(col, row, discs.get(Disc.None));
        board_pan.addGridListener(this);
        Vector<Image> icons = new Vector<>();
        icons.add(discs.get(Disc.Yellow));
        icons.add(discs.get(Disc.Red));
        control_pan = new ControlJPanel(icons, this);
        this.setTitle("Connect 4");
        this.setSize(width, height);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(1,2);
        setLayout(layout);
        this.add(board_pan);
        grid_added = true;
        this.add(control_pan);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        Dimension real_dim = getContentPane().getSize();
        int height = (int) real_dim.getHeight();
        int width = (int) real_dim.getWidth();
        int dim = height < width/2 ? height : width/2;
        control_pan.setSize(width/2, height);
        if(curr_winner == null)
            board_pan.setSize(dim, dim);
        else {
            drawImageWin(win_images.get(curr_winner));
        }

        super.paint(g);
    }


    public void updateBoard(Disc[][] board) throws IllegalArgumentException{
        if(curr_winner != null) {
            remove(win_message);
            curr_winner = null;
            add(board_pan, 0);
        }
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++) {
                if (i > row || i < 0 || j > col || j < 0)
                    throw new IllegalArgumentException(i, j);
                else
                    board_pan.draw(j, i, discs.get(board[i][j]));
            }
    }

    public void setWinner(Disc playerColor) throws IndexOutOfBoundsException{

        Image image = win_images.get(playerColor);
        if(image == null)
            throw new IndexOutOfBoundsException();
        drawImageWin(image);
        if(curr_winner == null){
            remove(board_pan);
            add(win_message, 0);
        }
        curr_winner = playerColor;
        repaint();
    }

    private void drawImageWin(Image img){
        Dimension real_dim = getContentPane().getSize();
        int height = (int) real_dim.getHeight();
        int width = (int) real_dim.getWidth();
        int dim = height < width/2 ? height : width/2;
        win_message.setSize(dim, dim);
        Image newimg = img.getScaledInstance(dim, dim,  Image.SCALE_FAST); // scale it the smooth way
        win_message.setIcon(new ImageIcon(newimg));
    }

    public void setTimer(int seconds){
        control_pan.setTimer(seconds);
    }

    public void setMoveCounter(int nb_moves){
        control_pan.setMoves(nb_moves);
    }

    public void hint(int col, Disc player) throws IndexOutOfBoundsException{
        try {
            board_pan.draw(col, 0, discs.get(player));
        } catch (IllegalArgumentException e) {
            throw new IndexOutOfBoundsException();
        }
        board_pan.repaint();
    }

    public void giveTurn(Disc playerColor) throws IndexOutOfBoundsException{
        if(playerColor == Disc.Red)
            control_pan.setPlayer(1);
        else if(playerColor == Disc.Yellow)
            control_pan.setPlayer(0);
        else
            throw new IndexOutOfBoundsException();
    }

    @Override
    public void gridListener(int col, int row) {
        gl.play(col);
    }

    @Override
    public void hintButtonClicked() {
        gl.hint();
    }

    @Override
    public void newGameButtonClicked() {
        gl.newGame();
    }

    @Override
    public void undoButtonClicked() {
        gl.undo();
    }
}
