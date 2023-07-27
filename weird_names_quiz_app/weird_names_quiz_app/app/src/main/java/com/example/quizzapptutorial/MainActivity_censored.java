package com.example.quizzapptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    String selectedAnswer = "";

    TextView tvScore;

    int randomIndex;


    ArrayList<String> realNames = new ArrayList<>(Arrays.asList("RealName1", "RealName2", "RealName3"));
    ArrayList<String> fakeNames = new ArrayList<>(Arrays.asList("FakeName1", "FakeName2", "FakeName3"));
    int selectedPosition;

    int fakePosition;

    int resultInt;

    int fakeIndex;

    List<String> selectedNames = new ArrayList<>();

    String resultText;

    String fakeName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        // colocar o botao/textview de resultado aqui tambem
        tvScore = findViewById(R.id.result_tv);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        loadNewQuestion();
    }

    public static void main(String[] args) {

    }

    public void loadNewQuestion() {

        selectedNames.clear();
        Random random = new Random();


        // Select three real names at random
        for (int i = 0; i < 3; i++) {
            randomIndex = random.nextInt(realNames.size());
            selectedNames.add(realNames.get(randomIndex));
            realNames.remove(randomIndex);
        }

        // Select one fake name at random
        fakeIndex = random.nextInt(fakeNames.size());
        fakeName = fakeNames.get(fakeIndex);
        selectedNames.add(fakeName);


        // Determine the position of the fake name in the new list
        fakePosition = selectedNames.indexOf(fakeNames.get(fakeIndex));
        fakeNames.remove(fakeIndex);

        System.out.println("Selected names b4 shuffle: " + selectedNames);
        System.out.println("Position of fake name b4 shuffle: " + fakePosition);

        // Shuffle the selected names list
        Collections.shuffle(selectedNames);

        // Determine the position of the fake name in the new list
        fakePosition = selectedNames.indexOf(fakeName);

        System.out.println("Selected names after shuffle: " + selectedNames);
        System.out.println("Position of fake name after shuffle: " + fakePosition);


        ansA.setText(selectedNames.get(0));
        ansB.setText(selectedNames.get(1));
        ansC.setText(selectedNames.get(2));
        ansD.setText(selectedNames.get(3));

    }


    @Override
    public void onClick(View view) {


        ansA.setBackgroundColor(Color.LTGRAY);
        ansB.setBackgroundColor(Color.LTGRAY);
        ansC.setBackgroundColor(Color.LTGRAY);
        ansD.setBackgroundColor(Color.LTGRAY);

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.submit_btn) {

            if (selectedPosition == fakePosition) {
                // What happens when the answer is right
                resultText = "CORRETO, era " + fakeName;
                resultInt = 1;
            } else {
                // What happens when the answer is wrong
                resultText = "ERRADO, era " + fakeName;
                resultInt = 0;
            }

            System.out.println("selected position: "+ selectedPosition);
            System.out.println("fake position: "+ fakePosition);

            showResult(resultInt, resultText);
            loadNewQuestion();

        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.BLUE);

            selectedPosition = selectedNames.indexOf(selectedAnswer);

        }

    }


//    public void finishQuiz() {
//        new AlertDialog.Builder(this)
//                .setTitle("Proxima")
//                .setPositiveButton("Reiniciar", (dialogInterface, i) -> restartQuiz() )
//                .setCancelable(false)
//                .show();
//    }
//
//    public void restartQuiz() {
//
//        loadNewQuestion();
//    }


    public void showResult(int resultInt, String resultText) {


        tvScore.setText(resultText);

        if (resultInt == 1) {
            tvScore.setTextColor(Color.parseColor("#008000"));
        } else {
            tvScore.setTextColor(Color.parseColor("#FF0A00"));
        }
    }
}



//        Button restartBtn = findViewById(R.id.idBtnRestart);
//        LinearLayout = findViewById(R.id.resultsLayout);

//        restartBtn.setOnClickListener(this);
//
//        restartBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
//                startActivity(intent);
//            }
//        });

//        ResultDialog resultDialog = new ResultDialog(MainActivity.this);
//        View resultView = LayInflator.from(getApplicationContext()).inflate(R.layout.Results,(LinearLayout));
//        TextView scoreTV = resultDialog.findViewById(R.id.idTVScore);
//        Button restartQuizBtn = resultDialog.findViewById(R.id.idBtnRestart);
//        scoreTV.setText(currentResult);
//
//        if(resultInt.equals(1)){
//            scoreTV.setBackgroundColor(Color.GREEN);
//        } else {
//            scoreTV.setBackgroundColor(Color.RED);
//        }



