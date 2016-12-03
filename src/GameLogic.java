/**
 * Created by pierre on 11/28/16.
 */
// Template class for the GameLogic implementation
public abstract class GameLogic {
    // Board dimensions
    public static final int HEIGHT = 6, WIDTH = 8;
    // Cross-reference with the GUI
    protected GUI guiEndPoint;

    /* Sets or replaces the reference to the GUI end point.
     * Throws a NullPointerException if the parameter is null. */
    public abstract void setGUIEndPoint(final GUI guiEndPoint) throws NullPointerException;

    /* Clears the grid, gives the turn to Red and starts a new internal timer. Updates the GUI
     * grid, player turn and move counter.
     * Throws a NullPointerException if <guiEndPoint> is not set. */
    public abstract void newGame() throws NullPointerException;

    /* Updates the GUI with the time elapsed in seconds since a new game was started.
     * Throws a NullPointerException if <guiEndPoint> is not set. */
    public abstract void refreshTimer() throws NullPointerException;

    /* Drops a disc in <column> for the current player. Updates the GUI grid, player turn and
     * move counter.
     * Throws a NullPointerException if <guiEndPoint> is not set.
     * Throws an IndexOutOfBoundsException if <column> is outside [0, WIDTH-1]. */
    public abstract void play(int column) throws NullPointerException, IndexOutOfBoundsException;

    /* Undoes the last made move. Does nothing if there's no move to undo. Updates the GUI grid,
     * player turn and move counter.
     * Throws a NullPointerException if <guiEndPoint> is not set. */
    public abstract void undo() throws NullPointerException;

    /* Updates the GUI with a move suggestion for the current player.
     * Throws a NullPointerException if <guiEndPoint> is not set. */
    public abstract void hint() throws NullPointerException;
}
