public class PlayStone {
    PlayerColor color;
    int i;
    boolean isOut;
    boolean isAWin;

    public PlayStone(PlayerColor color) {
        this.color = color;
        this.i = -1;
        this.isOut = true;
        this.isAWin = false;
    }

    public PlayStone(PlayerColor color, int i, boolean isOut, boolean isAWin) {
        this.color = color;
        this.i = i;
        this.isOut = isOut;
        this.isAWin = isAWin;
    }

    // deep copy constructor
    public PlayStone(PlayStone playStone) {
        this.color = playStone.color;
        this.i = playStone.i;
        this.isOut = playStone.isOut;
        this.isAWin = playStone.isAWin;
    }

    public String getStoneOnBoard(int number) {
        char result = 'g';
        switch (color.ordinal()){
            case 1 -> result = 'y';
            case 2 -> result = 'r';
            case 3 -> result = 'b';
        }
        return String.valueOf(number) + String.valueOf(result);
    }

}
