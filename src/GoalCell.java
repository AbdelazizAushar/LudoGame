import java.util.List;

public class GoalCell extends Cells{
    public GoalCell(char name, List<PlayStone>listStones) {
        super(name,listStones);
    }

    @Override
    void collide(PlayStone stone) {
        listStones.add(stone);
        stone.isAWin=true;
    }


}
