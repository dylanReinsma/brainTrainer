package com.dylanreinsma.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView timerTextView;
    TextView sumTextView;
    TextView progressTextView;
    int totalProgress = 0;
    int progress = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView messageTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfAnswer;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        timerTextView = findViewById(R.id.timerTextView);
        sumTextView = findViewById(R.id.sumTextView);
        progressTextView = findViewById(R.id.progressTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        messageTextView = findViewById(R.id.messageTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        gameLayout.setVisibility(View.INVISIBLE);
    }

    public void goClick(View view) {
        goButton.setVisibility (View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        newQuestion();
        timer();
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfAnswer).equals(view.getTag().toString())){
            messageTextView.setText("Correct!");
            progress++;
        } else {
            messageTextView.setText("Wrong :(");
        }
        totalProgress++;
        progressTextView.setText(progress + "/" + totalProgress);
        newQuestion();
    }

    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfAnswer = random.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void timer() {
        new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished / 1000 >= 10) {
                    timerTextView.setText((millisUntilFinished / 1000 + "s"));
                } else {
                    timerTextView.setText(("0" + millisUntilFinished / 1000 + "s"));
                }
            }

            @Override
            public void onFinish() {
                messageTextView.setText("Finished!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    public void playAgain(View view) {
        progress = 0;
        totalProgress = 0;
        timer();
        newQuestion();
        progressTextView.setText(progress + "/" + totalProgress);
        playAgainButton.setVisibility(View.INVISIBLE);
        messageTextView.setText("");
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }
}
