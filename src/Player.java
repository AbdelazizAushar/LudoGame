import java.util.ArrayList;

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


}
