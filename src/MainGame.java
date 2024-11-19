public class MainGame {

    Character santa;
    Character krampus;
    // eventually window size variables

    public static void main(String[] args) {
        MainGame myGame = new MainGame();
    }

    public MainGame() {

       /* santa = new Character();
        santa.name = "Santa";
        santa.xpos = 100;
        santa.ypos = 100;
        santa.printInfo();
        santa.move();
        santa.printInfo();
        run();

        krampus = new Character();
        krampus.name = "Krampus";
        krampus.xpos = 100;
        krampus.ypos = 100;*/
    }

    public void setUpGraphics() {
        // brings up game window and allows us to draw on it
    }

    public void run() {
        while (true) {
            moveThings();
            render();
            pause();
        }
    }

    public void moveThings() {
        santa.move();
        santa.printInfo();
    }

    public void render() {

    }

    public void pause(){

    }
}