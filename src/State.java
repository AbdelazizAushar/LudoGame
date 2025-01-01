import java.util.ArrayList;

public class State {
    Cells[][] grid; // Cells[57][4] -- [56 route + 1 goal][4 players]
    ArrayList<Player> players;
    boolean isFinished = false;

    public State(Cells[][] grid, ArrayList<Player> players) {
        this.grid = grid;
        this.players = players;
        this.isFinished = false;
    }

    public State(State state) {
        this.grid = deepCopyGrid(state.grid);
        this.players = deepCopyPlayers(state.players);
        this.isFinished = state.isFinished;
    }

    private ArrayList<Player> deepCopyPlayers(ArrayList<Player> players) {
        ArrayList<Player> newPlayers = new ArrayList<>();
        for (Player player : players) {
            newPlayers.add(player);
        }
        return newPlayers;
    }

    private Cells[][] deepCopyGrid(Cells[][] grid) {
        Cells[][] newGrid = new Cells[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                newGrid[i][j] = grid[i][j].copy();
            }
        }
        return newGrid;
    }
}
