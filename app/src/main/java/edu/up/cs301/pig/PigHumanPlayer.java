package edu.up.cs301.pig;

import edu.up.cs301.game.GameFramework.GameHumanPlayer;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameState;
import edu.up.cs301.game.R;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    playerScoreTextView = null;
    private TextView    oppScoreTextView    = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton dieImageButton      = null;
    private Button      holdButton          = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public PigHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        //TODO You will implement this method to receive state objects from the game
        if (info instanceof PigGameState){

            PigGameState pigGameState = (PigGameState) info;
            if (playerNum == 0){

                playerScoreTextView.setText(pigGameState.getPlayer0Score() +"");
                oppScoreTextView.setText(pigGameState.getPlayer1Score() + "");
            } else {
                playerScoreTextView.setText(pigGameState.getPlayer1Score() +"");
                oppScoreTextView.setText(pigGameState.getPlayer0Score() + "");
            }
            if (pigGameState.getTurnId() == playerNum)
            {
                holdButton.setBackgroundColor(Color.BLUE);
            }
            else
            {
                holdButton.setBackgroundColor(Color.GRAY);
            }
            turnTotalTextView.setText(pigGameState.getRunningTotal() + "");


            switch (pigGameState.getDieValue()){
                case 1:
                    dieImageButton.setImageResource(R.drawable.face1);
                    if (pigGameState.getTurnId() == playerNum)
                    {
                        Context context = myActivity.getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        //myActivity.findViewById(R.)
                    }
                    break;
                case 2:
                    dieImageButton.setImageResource(R.drawable.face2);
                    break;
                case 3:
                    dieImageButton.setImageResource(R.drawable.face3);
                    break;
                case 4:
                    dieImageButton.setImageResource(R.drawable.face4);
                    break;
                case 5:
                    dieImageButton.setImageResource(R.drawable.face5);
                    break;
                case 6:
                    dieImageButton.setImageResource(R.drawable.face6);
                    break;
            }

        } else {
            flash(Color.BLACK, 10);
            return;
        }
    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        //TODO  You will implement this method to send appropriate action objects to the game
        if (button.equals(holdButton)){

            PigHoldAction a = new PigHoldAction(this);
            game.sendAction(a);

        } else {

            PigRollAction a = new PigRollAction(this);
            game.sendAction(a);
        }
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);
        if (playerNum == 0)
        {
            TextView t = (TextView)activity.findViewById(R.id.yourScoreText);
            t.setText("PLAYER BABY");
            TextView t2 = (TextView)activity.findViewById(R.id.oppScoreText);
            t2.setText("Opponent");
        }
        else
        {
            TextView t = (TextView)activity.findViewById(R.id.oppScoreText);
            t.setText("PLAYER BABY");
            TextView t2 = (TextView)activity.findViewById(R.id.yourScoreText);
            t2.setText("Opponent");
        }

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui

}// class PigHumanPlayer
