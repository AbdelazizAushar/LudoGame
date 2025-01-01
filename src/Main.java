public class Main {
    public static void main(String[] args) {
        Game game = new Game(2);
        Cells[][] grid = game.states.get(0).grid;
        for (Cells[] row : grid) {
            for (Cells cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}