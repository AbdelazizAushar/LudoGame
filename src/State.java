import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class State {
    Cells[][] grid; // Cells[57][4] -- [56 route + 1 goal][4 players]
    ArrayList<Player> players;
    boolean isFinished = false;
    Map<String,Integer> intersection =new HashMap<>();
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




    Map<String, Integer> intersection(PlayStone stone) {
        Map<String, Integer> intersection = new HashMap<>();

        for (PlayerColor color : PlayerColor.values()) {
            if (stone.color == color) intersection.put(color.name(), stone.i);

            int colorIndex = color.getStartingPosition();
            int offset = stone.color.getStartingPosition() - colorIndex;

            int notKnownColorIndex = (stone.i + 12 * (offset < 0 ? (offset + 4) : offset))%48;
            intersection.put(color.name(), notKnownColorIndex);
        }

        return intersection;
    }
}
