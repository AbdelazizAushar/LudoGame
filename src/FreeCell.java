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
        if (!listStones.isEmpty() && stone.color != listStones.get(0).color) {
            listStones.forEach(stonein -> stonein.i = -1);
            listStones.forEach(stonein -> stonein.isOut = true);
            listStones.clear();
        }
        listStones.add(stone);
    }
}