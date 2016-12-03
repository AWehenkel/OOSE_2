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
// This class is used to create a window containing a connect 4.
public class Connect4Window extends javax.swing.JFrame implements GridJPanelListener, ControlJPanelListener {

    private GameLogic gl;
    private final int col, row;
    private GridJPanel board_pan;
    private ControlJPanel control_pan;
    private HashMap<Disc, Image> discs;
    private boolean grid_added;
    private HashMap<Disc, Image> win_images;
    private JLabel win_message;
    private Disc curr_winner;

    public Connect4Window(GameLogic gl, int col, int row) throws IllegalArgumentException{
        if(col < 0 || row < 0)
            throw new IllegalArgumentException(col, row);
        this.gl = gl;
        this.col = col;
        this.row = row;

        getContentPane().setBackground(Color.black);

        try{
            discs = new HashMap<>();
            discs.put(Disc.None, ImageIO.read(new File("src/img/disk_black.png")));
            discs.put(Disc.Red, ImageIO.read(new File("src/img/disk_red.png")));
            discs.put(Disc.Yellow, ImageIO.read(new File("src/img/disk_yel.png")));

            win_images = new HashMap<>();
            win_images.put(Disc.Red, ImageIO.read(new File("src/img/red_win.png")));
            win_images.put(Disc.Yellow, ImageIO.read(new File("src/img/yellow_win.png")));
        }
        catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        //The label used to print win message
        win_message = new JLabel();

        //The board is displayed using the GridJPannel
        board_pan = new GridJPanel(col, row, discs.get(Disc.None));
        board_pan.addGridListener(this);

        //The control pannel:
        Vector<Image> icons = new Vector<>();
        icons.add(discs.get(Disc.Yellow));
        icons.add(discs.get(Disc.Red));
        control_pan = new ControlJPanel(icons);
        control_pan.addListener(this);

        //Setting parameters relative to the window.
        setTitle("Connect 4");
        setSize(830, 440);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Placement of the two part on the window.
        GridLayout layout = new GridLayout(1,2);
        setLayout(layout);
        add(board_pan);
        grid_added = true;
        add(control_pan);
        setVisible(true);
    }

    @Override
    //Override to resize correctly the components of the layout grid.
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

    /* Displays the board passed as argument.
     * Throws an IllegalArgumentException if <board> if not of dimensions HEIGHT x WIDTH */
    public void updateBoard(Disc[][] board) throws IllegalArgumentException{
        if(curr_winner != null) {
            remove(win_message);
            curr_winner = null;
            add(board_pan, 0);
        }
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                board_pan.draw(j, i, discs.get(board[i][j]));
        repaint();
    }

    /* Sets the player with color <playerColor> as the winner of the game.
     * Throws an IndexOutOfBoundsException if <turnColor> is not either of the color constants. */
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

    /*
        Changes the image of the win_message JLabel and resizes it as a square which is contained in an half window.
     */
    private void drawImageWin(Image img){
        Dimension real_dim = getContentPane().getSize();
        int height = (int) real_dim.getHeight();
        int width = (int) real_dim.getWidth();
        int dim = height < width/2 ? height : width/2;
        win_message.setSize(dim, dim);
        Image newimg = img.getScaledInstance(dim, dim,  Image.SCALE_FAST); // scale it the smooth way
        win_message.setIcon(new ImageIcon(newimg));
    }

    /* Sets the displayed value of the timer.
     * Throws an IndexOutOfBoundsException if <seconds> is negative. */
    public void setTimer(int seconds) throws IndexOutOfBoundsException{
        control_pan.setTimer(seconds);
    }

    /* Sets the displayed value of the move counter.
     * Throws an IndexOutOfBoundsException if <moves> is negative. */
    public void setMoveCounter(int nb_moves) throws IndexOutOfBoundsException{
        control_pan.setMoves(nb_moves);
    }

    /* Displays a hint for the player with color <player> to play in the column <column>.
     * Throws an IndexOutOfBoundsException if <col> is outside [0, WIDTH-1].
     * Throws an IndexOutOfBoundsException if <player> is not either of the color constants. */
    public void hint(int col, Disc player) throws IndexOutOfBoundsException{
        try {
            board_pan.draw(col, 0, discs.get(player));
        } catch (IllegalArgumentException e) {
            throw new IndexOutOfBoundsException();
        }
        board_pan.repaint();
    }

    /* Displays the player with color <playerColor> as the current player.
     * Throws an IndexOutOfBoundsException if <turnColor> is not either of the color constants. */
    public void giveTurn(Disc playerColor) throws IndexOutOfBoundsException{
        if(playerColor == Disc.Red)
            control_pan.setPlayer(1);
        else
            control_pan.setPlayer(0);
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
