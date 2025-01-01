import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private ArrayList<State> states;

    public Game(int playersNumber) {
        this.players = getInitialPlayers(playersNumber);
        Cells[][] initialGrid = getInitialGrid(playersNumber);
    }

    private Cells[][] getInitialGrid(int playersNumber) {
        int roadLength = 54;
        Cells[][] initialGrid = new Cells[playersNumber][roadLength];
        for(int i = 0; i < playersNumber ; i++){
            Player currPlayer = players.get(i);
            initialGrid[i][0] = new StartCell(currPlayer.playerColor);
            initialGrid[i][1] =
        }
        return initialGrid;
    }

    private ArrayList<Player> getInitialPlayers(int playersNumber) {
        ArrayList<Player> players = new ArrayList<>();
        PlayerColor[] playerColors = PlayerColor.values();
        for(int i = 0; i < playersNumber ; i++){
            players.add(new Player(playerColors[i]));
        }
        return players;
    }
}
