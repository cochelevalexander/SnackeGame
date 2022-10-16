package Snake;
import java.awt.*;
/*import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter; */
import java.awt.event.*;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamesPlay extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int x[]  = new int[ALL_DOTS];
    private int y[]  = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public GamesPlay(){
        this.setBackground(Color.black);
        this.loadImage();
        this.initGame();
        this.addKeyListener(new FildKeyLisiner());
        this.setFocusable(true);
    }
    public void initGame(){
        dots = 3;
        for(int i = 0; i<dots; i++){
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(1000,this);
        timer.start();
        createApple();

    }
    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }
    public void loadImage (){
        ImageIcon iia = new ImageIcon("Snake/apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("Snake/dot.png");
        dot = iid.getImage();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        if(inGame == true){
            g.drawImage(apple,appleX,appleY, this);
            for(int i = 0; i<dots; i++){
                g.drawImage(dot,x[i],y[i],this);
            }
        }
    }

    public void move(){
        for(int i = dots; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        }
        if(up){
            y[0] -= DOT_SIZE;
        }
        if(down){
            y[0] += DOT_SIZE;
        }
    }
    public void Checkapple(){
        if(x[0]== appleX && y[0]==appleY){
            dots++;
            createApple();
        }

    }

    public void Checkcollisions(){
        for (int i = dots; i> 0; i--){
            if(i>4 && x[0]==x[i] && y[0]==y[i]){
                inGame = false;
            }
        }
        if(x[0]>SIZE || x[0]<SIZE || y[0]>SIZE || y[0]<SIZE){
            inGame = false;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame == true){
        move();
        Checkapple();
        Checkcollisions();
        }
        repaint();
    }
    class FildKeyLisiner extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                up = true;
                right = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                down = false;
                up = false;
                right = true;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }
        }
    }
}
