public class Main {

    public static void main(String[] args){
        GUI gui = new Connect4GUI();
        GameLogic gl = new Connect4GL();
        gl.setGUIEndPoint(gui);
        gui.setGLHandler(gl);
        gl.newGame();
    }
}
