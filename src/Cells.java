import java.util.List;

public abstract class Cells {
    char name;
    List<PlayStone>listStones;

    public Cells(char name){
        this.name = name;
    }

    public Cells(char name, List<PlayStone> listStones) {
        this.name = name;
        this.listStones = listStones;
    }

    @Override
    public String toString() {
        return name+" ";
    }

    abstract void collide(PlayStone stone) ;


}
