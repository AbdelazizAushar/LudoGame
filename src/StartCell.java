import java.util.List;

public class StartCell extends  Cells{
    public StartCell(char name, List<PlayStone>listStones) {
        super(name,listStones);
    }


    @Override
    void collide(PlayStone stone) {
        listStones.add(stone);

    }
}
