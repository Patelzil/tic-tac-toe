package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0: x, 1: o
    int player = 0;

    static int player1 = 0;
    static int player2 = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2} , {3,4,5} , {6,7,8}, {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};

    Boolean gameActive = true;

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive)
        {
            gameState[tappedCounter] = player;

            counter.setTranslationY(-1500);

            if (player == 0)
            {
                counter.setImageResource(R.drawable.x_image);

                player = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.o_image);

                player = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(400);

            for (int[] i : winningPositions)
            {
                if (gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[0]] != 2)
                {
                    gameActive = false;

                    String winner = "";
                    if (player == 1)
                    {
                        winner = "X has won";
                        player1++;
                    }
                    else if(player == 0)
                    {
                        winner = "O has won";
                        player2++;
                    }


                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView)findViewById(R.id.winnerTextView);

                    winnerText.setText(winner);

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView)findViewById(R.id.winnerTextView);


        playAgainButton.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i <gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        player = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
