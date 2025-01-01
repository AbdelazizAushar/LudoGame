import java.util.ArrayList;

public class Player {

      boolean isWin;
      String playerName;
      PlayerColor playerColor;
      ArrayList<PlayStone> stones = new ArrayList<>(4);

      public Player(PlayerColor playerColor) {
            this.playerName = "";
            this.playerColor = playerColor;
      }

      public Player(String playerName, PlayerColor playerColor) {
            this.playerName = playerName;
            this.playerColor = playerColor;
      }
}
