
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by antoinewehenkel on 29/11/16.
 */
public class Connect4GUI extends GUI{

    private Connect4Window window;

    @Override
    public void setGLHandler(GameLogic gl_handler) throws NullPointerException {
        this.gl_handler = gl_handler;
        window = new Connect4Window(gl_handler, 830, 440, 7, 6, "src/img/disk_black.png", "src/img/disk_yel.png", "src/img/disk_red.png");
    }

    @Override
    public void updateBoard(Disc[][] board) throws IllegalArgumentException {
        window.updateBoard(board);
        window.repaint();
    }

    @Override
    public void giveTurn(Disc playerColor) throws IndexOutOfBoundsException {

    }

    @Override
    public void setTimer(int seconds) throws IndexOutOfBoundsException {

    }

    @Override
    public void setMoveCounter(int moves) throws IndexOutOfBoundsException {

    }

    @Override
    public void setWinner(Disc playerColor) throws IndexOutOfBoundsException {

    }

    @Override
    public void giveHint(int column, Disc playerColor) throws IndexOutOfBoundsException {

    }



}
