public class Game {
    public static final int resolution = 500;

    public static void main(String[] args){

        Frame frame = new Frame(resolution);
        frame.initialize();

        while (!StdDraw.isKeyPressed(27)) {
            frame.draw();
            StdDraw.pause(100);

            // draw with mouse and pause the game
            if(StdDraw.isMousePressed() || StdDraw.isKeyPressed(32)){
                // resume game with enter
                while(!StdDraw.isKeyPressed(10)){
                    displayPauseText(frame);
                    // set cell as alive with left mouse button
                    if(StdDraw.isMousePressed())
                        frame.setPixel();
                    // set canvas to white with backspace button
                    if(StdDraw.isKeyPressed(8))
                        frame.clear();
                }
            }

            frame.calculateNextFrame();
        }
        System.exit(0);
    }

    private static void displayPauseText(Frame f){
        int center = f.getCoordinateCenter();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(center, center, "press enter to resume");
    }

}

