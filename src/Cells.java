import java.util.ArrayList;
import java.util.List;

public abstract class Cells {
    char name;
    List<PlayStone> listStones;

    public Cells(char name) {
        this.name = name;
        this.listStones= new ArrayList<>();
    }

    public Cells(char name, List<PlayStone> listStones) {
        this.name = name;
        this.listStones = listStones;
    }

    @Override
    public String toString() {
        return name + " ";
    }

    abstract public Cells copy();

    abstract void collide(PlayStone stone);

    protected ArrayList<PlayStone> deepCopyStones(ArrayList<PlayStone> stones) {
        ArrayList<PlayStone> newStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            newStones.add(new PlayStone(stone));
        }
        return newStones;
    }
}
