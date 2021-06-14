package com.example.sharedpreferencestoringlastscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button increaseButton, decreaseButton;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.scoreId);
        increaseButton = findViewById(R.id.increaseId);
        decreaseButton = findViewById(R.id.decreaseId);

        if (loadScore() != 0){
            textView.setText("Score : " + loadScore());
            score = loadScore();
            //jkhn e app open kora hobe 2khn score ta load hobe. last j score ta loadScore() method a store hoyeche setai score a dekhabe
        }

        increaseButton.setOnClickListener(this);
        decreaseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.increaseId)
        {
            score = score + 10;
            textView.setText("Score : " + score);
            saveScore(score);
        }

        else if(v.getId() == R.id.decreaseId)
        {
            score = score - 10;
            textView.setText("Score : " + score);
            saveScore(score);
        }
    }

    private void saveScore(int score) {
        //data reading
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);//1st parameter is a name . and 2nd parameter is for security
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore", score);
        editor.commit();//must do it
    }

    private int loadScore()
    {
        //data writing
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt("lastScore", 0);//1st parameter is the key of saving data in sharedpreferences, and 2nd parameter is a defaultvalue
        return score;
    }
}