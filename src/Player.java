import java.util.ArrayList;
import java.util.Comparator;

public class Player {
    boolean isWin;
    String playerName;
    PlayerColor playerColor;
    ArrayList<PlayStone> stones = new ArrayList<>();

    public Player(PlayerColor playerColor) {
        this.playerName = "";
        this.playerColor = playerColor;
        initializePlayerStones();
    }

    public Player(String playerName, PlayerColor playerColor) {
        this.playerName = playerName;
        this.playerColor = playerColor;
    }

    // deep copy constructor
    public Player(Player player) {
        this.isWin = player.isWin;
        this.playerName = player.playerName;
        this.playerColor = player.playerColor;
        this.stones = deepCopyStones(player.stones);
    }

    private ArrayList<PlayStone> deepCopyStones(ArrayList<PlayStone> stones) {
        ArrayList<PlayStone> newStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            newStones.add(new PlayStone(stone));
        }
        return newStones;
    }

    private void initializePlayerStones() {
        for (int i = 0; i < 4; i++) {
            stones.add(new PlayStone(playerColor));
        }
    }

    public ArrayList<PlayStone> getMovableStones(State state, int dice) {
        ArrayList<PlayStone> movableStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            if (stone.isAWin) continue;
            if (stone.isOut && dice != 6) continue;
            if (state.BlockFounded(dice, stone)) continue;
            if (getWinningTileIndex() != stone.i + dice) continue;
            movableStones.add(stone);
        }
        return movableStones;
    }

    private int getWinningTileIndex() {
        ArrayList<PlayStone> winningStones = getStonesWinningInOrder();
        PlayStone lastStoneToWin = winningStones.getFirst(); // the most outside stone
        return lastStoneToWin.i - 1; // winning tile index
    }

    private ArrayList<PlayStone> getStonesWinningInOrder(){
        ArrayList<PlayStone> winningStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            if (stone.isAWin) winningStones.add(stone);
        }
        winningStones.sort(Comparator.comparingInt(stone -> stone.i));
        return winningStones;
    }
}
