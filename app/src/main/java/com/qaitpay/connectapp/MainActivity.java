package com.qaitpay.connectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //    Button play;
    int activePlayer = 0; //0=Blue & 1=Red
    boolean gameIsActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};//2 means unplayed
    int[][] WinnerPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};//2 means unplayed

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);


            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.od);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.xd);
                activePlayer = 0;

            }
            counter.animate()
                    .translationYBy(1000f)
                    .rotation(3600)
                    .setDuration(300);
            for (int[] WinningPosition : WinnerPosition) {

                if (gameState[WinningPosition[0]] == gameState[WinningPosition[1]] &&
                        gameState[WinningPosition[1]] == gameState[WinningPosition[2]] &&
                        gameState[WinningPosition[0]] != 2) {

                    // Someone has won!

                    gameIsActive = false;

                    String winner = "Red";

                    if (gameState[WinningPosition[0]] == 0) {


                        winner = "Blue";


                    }

                    TextView winnerMessage = findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = findViewById(R.id.playAgainButton);

                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;

                    for (int counterState : gameState) {

                        if (counterState == 2) gameIsOver = false;

                    }

                    if (gameIsOver) {

                        TextView winnerMessage = findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = findViewById(R.id.playAgainButton);

                        layout.setVisibility(View.VISIBLE);

                    }
                }

            }
        }

    }
        public void playAgain (View view){
            gameIsActive = true;
            LinearLayout layout = findViewById(R.id.playAgainButton);
            layout.setVisibility(View.INVISIBLE);


            activePlayer = 0;

            for (int i = 0; i < gameState.length; i++) {

                gameState[i] = 2;


            }

            GridLayout gridLayout = findViewById(R.id.gridLayout);

            for (int i = 0; i < gridLayout.getChildCount(); i++) {

                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

            }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout =findViewById(R.id.playAgainButton);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

