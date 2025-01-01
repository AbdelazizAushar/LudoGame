import java.util.ArrayList;

public class State {
    Cells[][] grid; // Cells[57][4] -- [56 route + 1 goal][4 players]
    ArrayList<Player> players;
    boolean isFinished = false;

    public State(Cells[][] grid, ArrayList<Player> players){
        this.grid = grid;
        this.players = players;
        this.isFinished = false;
    }

    public State(Cells[][] grid, ArrayList<Player> players, boolean isFinished) {
        this.grid = grid;
        this.players = players;
    }
}
