import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by antoinewehenkel on 30/11/16.
 */
public class ControlJPanel extends JPanel implements ActionListener {
    private Button hint, new_game, undo;

    public ControlJPanel(){
        GridLayout layout = new GridLayout(4,1);
        setLayout(layout);

        hint = new Button("src/img/hint.png");
        add(hint);
        hint.addActionListener(this);

        new_game = new Button("src/img/newGame.png");
        add(new_game);
        new_game.addActionListener(this);

        undo = new Button("src/img/undo.png");
        add(undo);
        undo.addActionListener(this);

        setBackground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(hint))
            System.out.println("hint");
        else if(e.getSource().equals(new_game))
            System.out.println("New game");
        else if(e.getSource().equals(undo))
            System.out.println("undo");
    }
}
