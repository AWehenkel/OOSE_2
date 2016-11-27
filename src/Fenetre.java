import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Fenetre extends javax.swing.JFrame{

    // these are the components we need.
    private JSplitPane splitPane;  // split the window in top and bottom
    private JPanel leftPanel;       // container panel for the top
    private JPanel rightPanel;    // container panel for the bottom
    private JButton newGame;
    private JButton undo;
    private JButton hint;
    public Fenetre(){

        // first, lets create the containers:
        // the splitPane devides the window in two components (here: top and bottom)
        // users can then move the devider and decide how much of the top component
        // and how much of the bottom component they want to see.
        splitPane = new JSplitPane();

        leftPanel = new JPanel();         // our top component
        rightPanel = new JPanel();      // our bottom component

        // in our bottom panel we want the text area and the input components
        newGame = new JButton();    // and a button at the right, to send the text
        undo = new JButton();
        hint = new JButton();
        JButton button = new JButton();
        try {
            Image img = ImageIO.read(new File("src/img/hint.png"));
            hint.setIcon(new ImageIcon(img));
            img = ImageIO.read(new File("src/img/newGame.png"));
            newGame.setIcon(new ImageIcon(img));
            img = ImageIO.read(new File("src/img/undo.png"));
            undo.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
        }
        // now lets define the default size of our window and its layout:
        setPreferredSize(new Dimension(400, 400));     // let's open the window with a default size of 400x400 pixels
        // the contentPane is the container that holds all our component
        // we only add one element to the window itself
        getContentPane().add(splitPane);// due to the GridLayout, our splitPane will now fill the whole windo
        // let's configure our splitPane:
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);  // we want it to split the window verticaly
        splitPane.setDividerLocation(200);                    // the initial position of the divider is 200 (our window is 400 pixels high)
        splitPane.setLeftComponent(leftPanel);              // at the top we want our "topPanel"
        splitPane.setRightComponent(rightPanel);

        // our topPanel doesn't need anymore for this example. Whatever you want it to contain, you can add it here
        leftPanel.setLayout(new GridLayout(6,7,0,0)); // BoxLayout.Y_AXIS will arrange the content vertically
        for (int i=0;i < 6*7 ; i++){
            leftPanel.add(new Button("src/img/disk_black,png"));
        }
        rightPanel.setLayout(new GridLayout(6,1));
        rightPanel.add(new Panneau("player"));
        rightPanel.add(new Panneau("time"));
        rightPanel.add(new Panneau("nb of moves"));
        rightPanel.add(newGame);                // first we add the scrollPane to the bottomPanel, so it is at the top
        rightPanel.add(undo);// the scrollPane should make the textArea scrollable, so we define the viewport
        rightPanel.add(hint);
        pack();   // calling pack() at the end, will ensure that every layout and size we just defined gets applied before the stuff becomes visible
        this.setVisible(true);
    }
}