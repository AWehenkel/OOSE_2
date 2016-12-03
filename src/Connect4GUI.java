/**
 * Created by antoinewehenkel on 29/11/16.
 */
public class Connect4GUI extends GUI{

    private Connect4Window window;

    @Override
    public void setGLHandler(GameLogic gl_handler) throws NullPointerException {
        if(gl_handler == null)
            throw new NullPointerException();

        this.gl_handler = gl_handler;
        try {
            window = new Connect4Window(gl_handler, gl_handler.WIDTH, gl_handler.HEIGHT);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBoard(Disc[][] board) throws IllegalArgumentException {
        window.updateBoard(board);
    }

    @Override
    public void giveTurn(Disc playerColor) throws IndexOutOfBoundsException {
        window.giveTurn(playerColor);
    }

    @Override
    public void setTimer(int seconds) throws IndexOutOfBoundsException {
        window.setTimer(seconds);
    }

    @Override
    public void setMoveCounter(int moves) throws IndexOutOfBoundsException {
        window.setMoveCounter(moves);
    }

    @Override
    public void setWinner(Disc playerColor) throws IndexOutOfBoundsException {
        window.setWinner(playerColor);
    }

    @Override
    public void giveHint(int column, Disc playerColor) throws IndexOutOfBoundsException {
        window.hint(column, playerColor);
    }



}
