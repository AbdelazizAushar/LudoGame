import java.util.List;

public class FreeCell extends Cells {
    public FreeCell() {
        super('-');
    }

    public FreeCell(char name, List<PlayStone> listStones) {
        super(name, listStones);

    }

    @Override
    public Cells copy() {
        return new FreeCell(this.name, this.listStones);
    }

    @Override
    void collide(PlayStone stone) {
        if (!listStones.isEmpty() && !stone.color.equals(listStones.get(0).color)) {
            resetStones();
        }
        listStones.add(stone);


    }
    private void resetStones() {
        for (PlayStone stone : listStones) {
            stone.i = -1;
            stone.isOut = true;
        }
        listStones.clear();
    }
}