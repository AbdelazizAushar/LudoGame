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
}
