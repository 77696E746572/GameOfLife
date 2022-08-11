
public class Frame {
    private boolean[][] matrix;
    private final int res;
    final static int scale = 10;

    public Frame(int res) {
        this.res = res;
        int mSize = Math.floorDiv(res,scale);
        this.matrix = new boolean[mSize][mSize];
    }

    public void initialize(){
        StdDraw.setCanvasSize(res,res);
        StdDraw.setScale(0, matrix.length);
        StdDraw.enableDoubleBuffering();
        setRandomConfig();
    }

    public void setRandomConfig(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = Math.random() >= 0.5;
            }
        }
    }
    public void calculateNextFrame() {
        Frame nextFrame = new Frame(res);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                nextFrame.matrix[i][j] = isAlive(i, j);
            }
        }
        this.matrix = nextFrame.matrix;
    }

    private boolean isAlive(int i, int j) {
        int liveNeighbours = calculateLiveNeighbours(i, j);

        // case: cell is alive
        if (matrix[i][j]) {
            // Any live cell with two or three live neighbours survives.
            // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
            return liveNeighbours > 1 && liveNeighbours < 4;
        // case: cell is dead
        } else {
            // Any dead cell with three live neighbours becomes a live cell.
            return liveNeighbours == 3;
        }
    }


    private int calculateLiveNeighbours(int i, int j) {
        int liveNeighbourCount = 0;

        for (int k = i - 1; k < i + 2; k++) {
            for (int l = j - 1; l < j + 2; l++) {
                // check matrix bounds and ignore index (i,j)
                if (k >= 0 && l >= 0 && k < matrix.length && l < matrix.length && !(k == i && j == l)) {
                    if (matrix[k][l]) // == true
                        liveNeighbourCount++;
                }
            }
        }
        return liveNeighbourCount;
    }


    public void draw(){
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                if(matrix[i][j]){
                    double posX = i+0.5;
                    double posY = j+0.5;
                    StdDraw.filledSquare(posX, posY,0.5);
                }

            }
        }
        StdDraw.show();
    }

    public void setPixel(){
        int posX = Math.abs((int)StdDraw.mouseX() % matrix.length);
        int posY = Math.abs((int)StdDraw.mouseY() % matrix.length);

        this.matrix[posX][posY] = true;
        draw();
    }

    public void clear(){
        int mSize = Math.floorDiv(res,scale);
        this.matrix = new boolean[mSize][mSize];
        draw();
    }

    public int getCoordinateCenter(){
        return matrix.length / 2;
    }

}
