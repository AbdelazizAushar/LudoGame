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

      private void initializePlayerStones() {
            for (int i = 0; i < 4; i++) {
                  stones.add(new PlayStone(playerColor));
            }
      }
}
