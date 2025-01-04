import java.util.ArrayList;

public class Game {

    ArrayList<Player> players;
    ArrayList<State> states;

    public Game(int playersNumber) {
        this.players = getInitialPlayers(playersNumber);
        Cells[][] initialGrid = getInitialGrid(playersNumber);
        states = new ArrayList<>();
        states.add(new State(initialGrid, players));
    }

    private Cells[][] getInitialGrid(int playersNumber) {
        Cells[][] initialGrid = new Cells[playersNumber][PlayerRoadMaker.roadLength];
        for (int i = 0; i < playersNumber; i++) {
            Player currPlayer = players.get(i);
            PlayerRoadMaker currPlayerRoad = new PlayerRoadMaker(currPlayer.playerColor);
            initialGrid[i] = currPlayerRoad.getPlayerRoad();
        }
        return initialGrid;
    }

    private ArrayList<Player> getInitialPlayers(int playersNumber) {
        ArrayList<Player> players = new ArrayList<>();
        PlayerColor[] playerColors = PlayerColor.values();
        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player(playerColors[i]));
        }
        return players;
    }
}
