package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {
    
    /**
     * This ctor creates a new game state
     */
    private PigGameState state;

    public PigLocalGame() {
        state=new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx==state.getTurn()){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof PigHoldAction){
            if (state.getTurn()==0){
                state.setP1Score(state.getP1Score()+state.getRunningScore());
            }
            else if (state.getTurn()==1){
                state.setP2Score(state.getP2Score()+state.getRunningScore());
            }
            state.setRunningScore(0);
            int t=(state.getTurn()+1)%players.length;
            state.setTurn(t);
            return true;
        }
        else if (action instanceof PigRollAction){
            int dieNum=(int) (Math.random()*6+1);
            state.setDieValue(dieNum);
            if (dieNum==1){
                state.setRunningScore(0);
                int t=(state.getTurn()+1)%players.length;
                state.setTurn(t);
            }
            else{
                state.setRunningScore(state.getRunningScore()+dieNum);
            }
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState s=new PigGameState(state);
        p.sendInfo(s);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if (state.getP1Score()>=50){
            return playerNames[0]+" wins with a score of "+state.getP1Score();
        }
        else if (state.getP2Score()>=50){
            return playerNames[1]+" wins with a score of "+state.getP2Score();
        }
        return null;
    }

}// class PigLocalGame
