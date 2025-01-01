public class PlayerRoad {

    private final int roadLength = 54;
    private PlayerColor playerRoadColor;
    private Cells[] playerRoad;

    public PlayerRoad(PlayerColor playerRoadColor){
        this.playerRoadColor = playerRoadColor;
        playerRoad = new Cells[roadLength];
    }

    private void setStartCell() {
        playerRoad[0] = new StartCell(playerRoadColor);
    }

    private void setEmptyCells() {

    }

    private void setSafeCells() {

    }

    private void setGoalCells() {

    }
}
