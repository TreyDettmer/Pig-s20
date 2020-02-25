package edu.up.cs301.pig;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.infoMessage.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState state;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        state = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        if (playerIdx == state.getTurnId())
        {
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
        //TODO  You will implement this method
        if (action instanceof PigRollAction)
        {
            Random rand = new Random();
            int i = rand.nextInt(6) + 1;
            state.setDieValue(i);
            if (i != 1)
            {
                state.setRunningTotal(state.getRunningTotal() + i);
            }
            else
            {
                state.setRunningTotal(0);
                if (state.getPlayerCount() > 1)
                {
                    state.setTurnId(state.getTurnId() % 1);
                }

            }
            return true;
        }
        else if (action instanceof PigHoldAction)
        {
            if (state.getTurnId() == 1)
            {
                state.setPlayer1Score(state.getPlayer1Score() + state.getRunningTotal());
            }
            else
            {
                state.setPlayer0Score(state.getPlayer0Score() + state.getRunningTotal());
            }
            state.setRunningTotal(0);
            if (state.getPlayerCount() > 1)
            {
                state.setTurnId(state.getTurnId() % 1);
            }
            return true;
        }
        else {
            return false;
        }


    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState temp = new PigGameState(state);

        p.sendInfo(temp);
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
        //TODO  You will implement this method
        if (state.getPlayer0Score() >= 50)
        {
            return ("Player 1 won with a score of " + state.getPlayer0Score());
        }
        else if (state.getPlayerCount() > 1)
        {
            if (state.getPlayer1Score() >= 50)
            {
                return ("Player 2 won with a score of " + state.getPlayer1Score());
            }
        }
        return null;
    }

}// class PigLocalGame
