/**
 * Created by antoinewehenkel on 29/11/16.
 */
public class Connect4GL extends GameLogic{
    private Disc[][] board;
    @Override
    public void setGUIEndPoint(GUI guiEndPoint) throws NullPointerException {
        this.guiEndPoint = guiEndPoint;
        board = new Disc[HEIGHT][WIDTH];
    }

    @Override
    public void newGame() throws NullPointerException {
        for(int i = 0; i < HEIGHT; i++)
            for(int j = 0; j < WIDTH; j++)
                board[i][j] = Disc.None;
        try {
            guiEndPoint.updateBoard(board);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshTimer() throws NullPointerException {

    }

    @Override
    public void play(int column) {
        board[HEIGHT-1][column] = Disc.Red;
        try {
            guiEndPoint.updateBoard(board);
            guiEndPoint.setTimer(10);
            guiEndPoint.setMoveCounter(5);
            guiEndPoint.giveTurn(Disc.Red);
            //throw new IllegalArgumentException(8,8);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo() throws NullPointerException {
        guiEndPoint.setWinner(Disc.None);
    }

    @Override
    public void hint() throws NullPointerException {
        guiEndPoint.giveHint(2, Disc.Yellow);
    }
}
