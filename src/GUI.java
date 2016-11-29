/**
 * Created by pierre on 11/28/16.
 */

public abstract class GUI {
    protected GameLogic glHandler;

    /* Sets or replaces the reference to the GameLogic handler.
     * Throws a NullPointerException if the parameter is null. */
    public abstract void setGLHandler(final GameLogic glHandler) throws NullPointerException;

    /* Displays the board passed as argument.
     * Throws an IllegalArgumentException if <board> if not of dimensions HEIGHT x WIDTH */
    public abstract void updateBoard(final Disc[][] board) throws IllegalArgumentException;

    /* Displays the player with color <playerColor> as the current player.
     * Throws an IndexOutOfBoundsException if <turnColor> is not either of the color constants. */
    public abstract void giveTurn(Disc playerColor) throws IndexOutOfBoundsException;

    /* Sets the displayed value of the timer.
     * Throws an IndexOutOfBoundsException if <seconds> is negative. */
    public abstract void setTimer(int seconds) throws IndexOutOfBoundsException;

    /* Sets the displayed value of the move counter.
     * Throws an IndexOutOfBoundsException if <moves> is negative. */
    public abstract void setMoveCounter(int moves) throws IndexOutOfBoundsException;

    /* Sets the player with color <playerColor> as the winner of the game.
     * Throws an IndexOutOfBoundsException if <turnColor> is not either of the color constants. */
    public abstract void setWinner(Disc playerColor) throws IndexOutOfBoundsException;

    /* Displays a hint for the player with color <playerColor> to play in the column <column>.
     * Throws an IndexOutOfBoundsException if <column> is outside [0, WIDTH-1].
     * Throws an IndexOutOfBoundsException if <turnColor> is not either of the color constants. */
    public abstract void giveHint(int column, Disc playerColor) throws IndexOutOfBoundsException;
}
