import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ComputerDecision {
    private State state;
    private Player player;
    private int dice;
    private PlayStone decisionStone;

    public ComputerDecision(State state, Player player, int dice){
        this.state = state;
        this.player = player;
        this.dice = dice;
        decisionStone = chooseAStone();
    }
    private PlayStone chooseAStone(){
        int playerIndex = State.getPlayerIndex(player);
        ArrayList<PlayStone> movableStones = state.players.get(playerIndex).getMovableStones(state, dice);
        if(!movableStones.isEmpty()){
            TreeMap<Integer, PlayStone> scores = new TreeMap<>();
            for (PlayStone movableStone : movableStones) {
                State newState = state.move(player, movableStone, dice);
                int score = calculateStoneScore(movableStone, state, newState);
                scores.put(score, movableStone);
            }
            return scores.firstEntry().getValue();
        }
        return null;
    }
    private int calculateStoneScore(PlayStone stone, State oldState, State newState) {
        int playerIndex = State.getPlayerIndex(player);
        PlayStone oldStone = oldState.players.get(playerIndex).stones.get(stone.num-1);
        PlayStone newStone = newState.players.get(playerIndex).stones.get(stone.num-1);
        int score = oldStone.i;
        if (!oldStone.isAWin && newStone.isAWin) score += 100;
        else if (!oldStone.isOut && newStone.isOut) score += 60;
        else if(oldState.stonesIntersectedWith(oldStone, newStone.i - oldStone.i).containsValue(true)) score += 50;
        return score;
    }

    public PlayStone getDecisionStone() {return decisionStone; }




}
