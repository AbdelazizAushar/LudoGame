import java.util.List;

public class GoalCell extends ColoredCell{

    public GoalCell() {
        super('*');
    }

    public GoalCell(PlayerColor color){
        super('*', color);
    }

    public GoalCell(char name, List<PlayStone>listStones) {
        super(name,listStones);
    }

    @Override
    void collide(PlayStone stone) {
        listStones.add(stone);
        stone.isAWin=true;
    }


}
