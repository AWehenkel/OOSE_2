import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by antoinewehenkel on 30/11/16.
 */
public class ControlJPanel extends JPanel implements ActionListener {
    private Button hint, new_game, undo;
    private JLabel timer, moves, player;
    private Vector<ImageIcon> players_icon;
    private JPanel infos;
    int cur_width, cur_height;

    public ControlJPanel(Vector<ImageIcon> players_icon){
        this.players_icon = players_icon;

        GridLayout layout = new GridLayout(4,1);
        setLayout(layout);

        infos = new JPanel();
        timer = new JLabel("", JLabel.CENTER);
        timer.setForeground(Color.white);
        timer.setSize(getWidth()/3, getHeight()/4);

        moves = new JLabel("", JLabel.CENTER);
        moves.setForeground(Color.white);
        moves.setSize(getWidth()/3, getHeight()/4);

        player = new JLabel("", JLabel.CENTER);
        player.setSize(getWidth()/4, getHeight()/4);

        infos.setLayout(new GridLayout(1, 3));
        infos.add(timer);
        infos.add(moves);
        infos.add(player);
        infos.setBackground(Color.BLACK);
        add(infos);

        hint = new Button("src/img/hint.png");
        add(hint);
        hint.addActionListener(this);

        new_game = new Button("src/img/newGame.png");
        add(new_game);
        new_game.addActionListener(this);

        undo = new Button("src/img/undo.png");
        add(undo);
        undo.addActionListener(this);
        setBackground(Color.BLACK);
        setVisible(true);

        cur_height = getHeight();
        cur_width = getWidth();

    }

    public void setTimer(int seconds){
        timer.setText("Seconds " + seconds);
    }

    public void setMoves(int nb_move){
        moves.setText("Number of move: " + nb_move);
    }

    public void setPlayer(int id_player){
        this.player.setIcon(players_icon.get(id_player));
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

    public void paintComponent(Graphics g){
        if(getHeight() != cur_height || getWidth() != cur_width){
            cur_height = getHeight();
            cur_width = getWidth();
            System.out.println("aze");
            for(int i = 0; i < players_icon.size(); i++){
                Image image = players_icon.get(i).getImage(); // transform it
                Image newimg = image.getScaledInstance(cur_width/4, cur_height/4,  Image.SCALE_AREA_AVERAGING); // scale it the smooth way
                players_icon.set(i, new ImageIcon(newimg));
            }
        }
        hint.setSize(getWidth(), getHeight()/4);
        new_game.setSize(getWidth(), getHeight()/4);
        undo.setSize(getWidth(), getHeight()/4);
        timer.setSize(getWidth()/3, getHeight()/4);
        moves.setSize(getWidth()/3, getHeight()/4);
        player.setSize(getWidth()/3, getHeight()/4);
        super.paintComponent(g);
    }
}
