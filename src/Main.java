import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        Player blue = new Player(PlayerColor.BLUE);
        PlayStone blueStone = blue.stones.get(2);
        blueStone.i = 10;
        blueStone.isOut = false;
        players.add(blue);
        LudoBoard board = new LudoBoard(players);
        System.out.println(board);
    }
}