import java.util.List;

public class StartCell extends ColoredCell {
    public StartCell() {
        super('#');
    }

    public StartCell(PlayerColor color) {
        super('#', color);
    }

    public StartCell(char name, List<PlayStone> listStones) {
        super(name, listStones);
    }

    @Override
    public Cells copy() {
        return new StartCell(this.name, this.listStones);
    }

    @Override
    void collide(PlayStone stone) {
        listStones.add(stone);
    }
}
