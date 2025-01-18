import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings({ "ConvertToTryWithResources", "resource" })

public class Game {
    ArrayList<Player> players;
    ArrayList<State> states;

    public Game(int playersNumber) {
        this.players = getInitialPlayers(playersNumber);
        Cells[][] initialGrid = getInitialGrid(playersNumber);
        states = new ArrayList<>();
        states.add(new State(initialGrid, players));
    }

    private Cells[][] getInitialGrid(int playersNumber) {
        Cells[][] initialGrid = new Cells[playersNumber][PlayerRoadMaker.roadLength];
        for (int i = 0; i < playersNumber; i++) {
            Player currPlayer = players.get(i);
            PlayerRoadMaker currPlayerRoad = new PlayerRoadMaker(currPlayer.playerColor);
            initialGrid[i] = currPlayerRoad.getPlayerRoad();
        }
        return initialGrid;
    }

    private ArrayList<Player> getInitialPlayers(int playersNumber) {
        ArrayList<Player> initPlayers = new ArrayList<>();
        PlayerColor[] playerColors = PlayerColor.values();
        for (int i = 0; i < playersNumber; i++) {
            initPlayers.add(new Player(playerColors[i]));
        }
        return initPlayers;
    }

    int dice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    boolean win() {
        return false;
    }

    Player firstPlayer() {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "First throw :" + ConsoleColors.RESET);

        System.out.print("Player 1 : ");
        int dice1 = dice();
        System.out.println(dice1);

        System.out.print("Player 2 : ");
        int dice2 = dice();
        System.out.println(dice2);

        System.out.println();

        if (dice1 > dice2)
            return players.get(0);
        else if (dice1 < dice2)
            return players.get(1);
        return null;
    }

    void firstMove() {
        Player firstPlayer = firstPlayer();
        State firstState = new State(getInitialGrid(players.size()), players, firstPlayer);
        int dice = 0;
        while (dice != 6) {
            dice = dice();
            System.out.println(ConsoleColors.getColor(
                    firstPlayer.playerColor) + firstPlayer.playerColor + " : " + dice + ConsoleColors.RESET);
            if (dice == 6)
                break;
            else {
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                firstPlayer = switchPlayer(firstState);
                firstState.statePlayer = firstPlayer;
            }
        }
        PlayStone stone = chooseAStone(firstState, firstPlayer, dice);
        states.add(firstState.move(firstPlayer, stone, dice));
        LudoBoard board = new LudoBoard(firstState.players);
        System.out.println(board);
    }

    Player switchPlayer(State state) {
        for (int i = 0; i < state.players.size(); i++) {
            if (state.statePlayer.playerColor.equals(state.players.get(i).playerColor)) {
                if (i == state.players.size() - 1) {
                    return state.players.get(0);
                } else
                    return state.players.get(i + 1);
            }
        }
        return null;
    }

    public void play() {
        State lastState;
        LudoBoard board;
        Player currentPlayer;
        firstMove();
        while (!win()) {
            lastState = states.get(states.size() - 1);
            currentPlayer = switchPlayer(lastState);
            lastState.statePlayer = currentPlayer;
            int dice = dice();
            System.out.println(ConsoleColors.getColor(
                    currentPlayer.playerColor) + currentPlayer.playerColor + " : " + dice + ConsoleColors.RESET);
            PlayStone stone = chooseAStone(lastState, currentPlayer, dice);
            if (stone == null) {
            } else {
                states.add(lastState.move(currentPlayer, stone, dice));
            }
            board = new LudoBoard(states.get(states.size() - 1).players);
            System.out.println(board);
        }
    }

    private PlayStone chooseAStone(State currentState, Player player, int dice) {
        int playerIndex = State.getPlayerIndex(player);
        ArrayList<PlayStone> movableStones = currentState.players.get(playerIndex).getMovableStones(currentState, dice);
        if (movableStones.isEmpty()) {
            return null;
        } else {
            System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "choose a stone to move : " + ConsoleColors.RESET);
            String color = ConsoleColors.getColor(player.playerColor);
            for (PlayStone movableStone : movableStones) {
                System.out.print(color + movableStone.num + "  " + ConsoleColors.RESET);
            }
            System.out.println();
            Scanner input = new Scanner(System.in);
            int chosen = input.nextInt();
            for (PlayStone movableStone : movableStones) {
                if (movableStone.num == chosen) {
                    return movableStone;
                }
            }
            return null;
        }
    }
}

// switch (state.currentPlayer.playerColor) {
// case GREEN -> state.players.get(1);
// case YELLOW -> state.players.get(2);
// case RED -> state.players.get(3);
// case BLUE -> state.players.get(0);
// default -> null;
// }