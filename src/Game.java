import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings({"ConvertToTryWithResources", "resource"})

public class Game {
    ArrayList<Player> players;
    ArrayList<State> states;
    static int repeatedTurns = 0;
    static boolean hasNewTurn = false;


    public Game(int playersNumber) {
        this.players = getInitialPlayers(playersNumber);
        Cells[][] initialGrid = getInitialGrid(playersNumber);
        states = new ArrayList<>();
        states.add(new State(initialGrid, players));
    }

    public Game(int playersNumber, Integer... computerIndexes) {
        this.players = getInitialPlayers(playersNumber);
        createComputerPlayers(computerIndexes);
        Cells[][] initialGrid = getInitialGrid(playersNumber);
        states = new ArrayList<>();
        states.add(new State(initialGrid, players));
    }

    private Cells[][] getInitialGrid(int playersNumber) {
        Cells[][] initialGrid = new Cells[4][PlayerRoadMaker.roadLength];
        PlayerColor[] playerColors = PlayerColor.values();
        for (int i = 0; i < playerColors.length; i++) {
            PlayerRoadMaker currPlayerRoad = new PlayerRoadMaker(playerColors[i]);
            initialGrid[i] = currPlayerRoad.getPlayerRoad();
        }
        return initialGrid;
    }

    private ArrayList<Player> getInitialPlayers(int playersNumber) {
        ArrayList<Player> initPlayers = new ArrayList<>();
        PlayerColor[] playerColors = PlayerColor.values();
        for (int i = 0; i < playersNumber; i++) {
            initPlayers.add(new Player(playerColors[i], false));
        }
        return initPlayers;
    }

    private void createComputerPlayers(Integer... computerIndexes) {
        for (Integer index : computerIndexes) {
            players.get(index).isComputer = true;
        }
    }




    int dice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    boolean win() {
        return false;
    }

    List<Integer> throwing() {
        List<Integer> dices = new ArrayList<>();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "First throw :" + ConsoleColors.RESET);
        for (int i = 0; i < players.size(); i++) {
            int dice = dice();
            System.out.println("Player " + players.get(i).playerColor + " : " + dice);
            if (!dices.contains(dice))
                dices.add(dice);
        }
        System.out.println();
        return dices;
    }

    Player firstPlayer() {
//        List<Integer> dices = throwing();
//        while (dices.size() < players.size()) {
//            dices = throwing();
//        }
//        for (int i = 0; i < dices.size(); i++) {
//            if (Objects.equals(dices.get(i), Collections.max(dices)))
//                return players.get(i);
//        }
        return players.get(1);
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
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                firstPlayer = switchPlayer(firstState);
                firstState.statePlayer = firstPlayer;
            }
        }
        PlayStone chosenStone = chooseAStone(firstState, firstPlayer, dice);
        states.add(firstState.move(firstPlayer, chosenStone, dice));
        LudoBoard board = new LudoBoard(firstState.players);
        System.out.println(board);
    }

    Player switchPlayer(State state) {
        if(hasNewTurn) return state.statePlayer;
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
            Scanner input = new Scanner(System.in);
            System.out.println(ConsoleColors.getColor(
                    currentPlayer.playerColor) + "Enter Dice Number: " + ConsoleColors.RESET);
            int dice = input.nextInt();
            addTurn(dice);
            if(repeatedTurns == 3) {
                System.out.println("You cannot play 3 consecutive turns");
                hasNewTurn = false;
                repeatedTurns = 0;
                continue;
            }
            System.out.println(ConsoleColors.getColor(
                    currentPlayer.playerColor) + currentPlayer.playerColor + " : " + dice + ConsoleColors.RESET);
            PlayStone chosenStone = chooseAStone(lastState, currentPlayer, dice);
            if (chosenStone == null) {
            } else {
                State newState = lastState.move(currentPlayer, chosenStone, dice);
                addTurn(State.getPlayerIndex(currentPlayer), chosenStone.i, lastState, newState);
                states.add(newState);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            board = new LudoBoard(states.get(states.size() - 1).players);
            System.out.println(board);
        }
    }

    private PlayStone chooseAStone(State currentState, Player player, int dice) {
        if (player.isComputer) return new ComputerDecision(currentState, player, dice).getDecisionStone();
        else return chooseAStoneByPlayer(currentState, player, dice);
    }

    private PlayStone chooseAStoneByPlayer(State currentState, Player player, int dice) {
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

    private void addTurn(int player, int cell, State oldState, State newState){
        if(cell == -1) return;
        if(oldState.grid[player][cell].listStones.size() == 0) return;
        hasNewTurn = oldState.grid[player][cell].listStones.size() >= newState.grid[player][cell].listStones.size();
    }

    private void addTurn(int dice){
        if(dice != 6) {
            repeatedTurns = 0;
            hasNewTurn = false;
            return;
        };
        repeatedTurns++;
        hasNewTurn = true;
    }

}

// switch (state.currentPlayer.playerColor) {
// case GREEN -> state.players.get(1);
// case YELLOW -> state.players.get(2);
// case RED -> state.players.get(3);
// case BLUE -> state.players.get(0);
// default -> null;
// }