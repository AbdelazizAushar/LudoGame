import java.util.List;

public class SafetyCell extends Cells{
    public SafetyCell(char name, List<PlayStone>listStones) {
        super(name,listStones);
    }

    @Override
    void collide(PlayStone stone) {
       listStones.add(stone);
       return;
    }
}
