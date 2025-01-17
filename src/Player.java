import java.util.*;

public class Player {
    boolean winner;
    String playerName;
    PlayerColor playerColor;
    ArrayList<PlayStone> stones = new ArrayList<>();

    public Player(PlayerColor playerColor) {
        this.winner = false;
        this.playerName = " ";
        this.playerColor = playerColor;
        initializePlayerStones();
    }

    public Player(String playerName, PlayerColor playerColor) {
        this.winner = false;
        this.playerName = playerName;
        this.playerColor = playerColor;
        initializePlayerStones();
    }

    // deep copy constructor
    public Player(Player player) {
        this.winner = player.winner;
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
            stones.add(new PlayStone(playerColor, i+1));
        }
    }

    public ArrayList<PlayStone> getMovableStones(State state, int dice) {
        ArrayList<PlayStone> movableStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            if (stone.isAWin) continue;
            if (stone.isOut && dice != 6) continue;
            if (getWinningTileIndex() - stone.i <= 6 && getWinningTileIndex() != stone.i + dice) continue;
            if (state.BlockFounded(dice, stone) == 0) continue;
            movableStones.add(stone);
        }
        return movableStones;
    }

    public int getWinningTileIndex() {
        ArrayList<PlayStone> winningStones = getStonesWinningInOrder();
        PlayStone lastStoneToWin = winningStones.get(0); // the most outside stone
        return lastStoneToWin.i - 1; // winning tile index
    }

    private ArrayList<PlayStone> getStonesWinningInOrder() {
        ArrayList<PlayStone> winningStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            if (stone.isAWin) winningStones.add(stone);
        }
        winningStones.sort(Comparator.comparingInt(stone -> stone.i));
        return winningStones;
    }
}
