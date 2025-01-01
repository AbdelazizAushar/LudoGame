import java.util.List;

public class SafetyCell extends Cells{

    public SafetyCell(){
        super('+');
    }

    public SafetyCell(char name, List<PlayStone>listStones) {
        super(name,listStones);
    }

    @Override
    void collide(PlayStone stone) {
       listStones.add(stone);

    }
}
