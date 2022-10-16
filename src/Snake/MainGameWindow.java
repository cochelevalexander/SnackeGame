package Snake;

import javax.swing.*;
import java.awt.*;


class MainGameWindow extends JFrame {
    public  MainGameWindow(){
        setTitle("Snacke");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new GamesPlay());
        setVisible(true);
        Button button  = new Button("jjj");
    }

    public static void main(String[] args) {
        MainGameWindow nw = new MainGameWindow();
        nw.setVisible(true);
        GamesPlay sk = new GamesPlay();
        sk.setVisible(true);
    }
}
