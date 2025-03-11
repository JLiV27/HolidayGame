//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.tools.Tool;

/***
 * Step 0: import
 */
import java.awt.event.*;

/***
 * Step 1: implement KeyListener
 */

//*******************************************************************************

public class BasicGameApp implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too
    Character santa;
    Character krampus;
    Character[] manyKrampus;

    Image backGroundPic;

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();
        /***
         * Step 2: addKeyListener(this)
         */
        canvas.addKeyListener(this);

        backGroundPic = Toolkit.getDefaultToolkit().getImage("Background.png");

        //variable and objects
        //create (construct) the objects needed for the game
        santa = new Character(100,100,0,0,50,50);
        santa.name = "Santa";
        santa.pic = Toolkit.getDefaultToolkit().getImage("Santa.png");

        krampus = new Character(100, 50,0,0,50,50);
        krampus.name = "krampus";
        krampus.pic = Toolkit.getDefaultToolkit().getImage("Krampus.png");

        manyKrampus = new Character[5];

        for (int i = 0; i < manyKrampus.length; i++) {
            manyKrampus[i] = new Character((int)(500 * Math.random() + 1),(int)(500 * Math.random() + 1),2,2,50,50);
            manyKrampus[i].pic = Toolkit.getDefaultToolkit().getImage("Krampus.png");
        }
    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            checkKey();
            moveThings();  //move all the game objects
            for (int i = 0; i < manyKrampus.length; i++) {
                manyKrampus[i].wrap();
            }
            krampus.wrap();
            santa.wrap();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    public void checkKey(){
        if(santa.right){
            santa.dx = 2;
        } else if (santa.left) {
            santa.dx = -2;
        }
        else{
            santa.dx = 0;
        }

        if(santa.down){
            santa.dy = 2;
        }else if(santa.up){
            santa.dy = -2;
        }
        else{
            santa.dy = 0;
        }

    }

    public void moveThings() {
        krampus.wrap();
        //krampus.printInfo();
        santa.wrap();
        //santa.printInfo();
        }
        //call the move() code for each object

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the images
        g.drawImage(backGroundPic,0,0,1000,800,null);
        g.drawImage(krampus.pic, krampus.xpos, krampus.ypos, krampus.width, krampus.height, null);
        g.drawImage(santa.pic, santa.xpos, santa.ypos, santa.width, santa.height, null);
        g.setColor(Color.red);
        BasicStroke myStroke = new BasicStroke(2.5f);
        g.setStroke(myStroke);
        g.drawRect(santa.hitbox.x,santa.hitbox.y,santa.hitbox.width,santa.hitbox.height);
        g.drawRect(krampus.hitbox.x,krampus.hitbox.y,krampus.hitbox.width,krampus.hitbox.height);
        for (int i = 0; i < manyKrampus.length; i++) {
            g.drawImage(manyKrampus[i].pic,manyKrampus[i].xpos,manyKrampus[i].ypos,manyKrampus[i].width,manyKrampus[i].height, null);
        }

        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
        JFrame jf = new JFrame();
        jf.setSize(1920,1080);
        //frame.setUndecorated(true);

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if(keyCode == 68){
                santa.right = true;
            }
            if(keyCode == 65){
                santa.left = true;
            }
            if(keyCode == 87){
                santa.up = true;
            }
            if(keyCode == 83){
                santa.down = true;
            }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == 68){
            santa.right = false;
        }
        if(keyCode == 65){
            santa.left = false;
        }
        if(keyCode == 87){
            santa.up = false;
        }
        if(keyCode == 83){
            santa.down = false;
        }
    }
}