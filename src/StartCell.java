import java.util.List;

public class StartCell extends  Cells{
    public StartCell(char name, List<PlayStone>listStones) {
        super(name,listStones);
    }


    @Override
    void collide(PlayStone stone) {
        if(!listStones.isEmpty()&&stone.color!=listStones.get(0).color){
            listStones.forEach(stonein->stonein.i=-1);
        }
        listStones.add(stone);
    }
}
