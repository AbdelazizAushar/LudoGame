import java.util.List;

public abstract class Cells {
    char name;
    List<PlayStone>listStones;

    public Cells(char name, List<PlayStone> listStones) {
        this.name = name;
        this.listStones = listStones;
    }



   abstract void collide(PlayStone stone) ;


}
