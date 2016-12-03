/**
 * Created by antoinewehenkel on 3/12/16.
 */
/*
Interface which allows to the ControlJPannel
to tell listener when a button is clicked.
 */
public interface ControlJPanelListener {
    //Method called when the hint button is clicked
    void hintButtonClicked();
    //Method called when the new game button is clicked
    void newGameButtonClicked();
    //Method called when the undo button is clicked
    void undoButtonClicked();
}
