package com.example.kuis;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class LamanKuis extends AppCompatActivity implements View.OnClickListener {

    private TextView question_textView, result_textView;
    private Button button_true, button_false;
    private ImageButton prev_button, next_button;
    private int correct = 0;
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            // array of objects of class Question
            // providing questions from string
            // resource and the correct ans
            new Question(R.string.pertanyaan1, true),
            new Question(R.string.pertanyaan2, false),
            new Question(R.string.pertanyaan3, true),
            new Question(R.string.pertanyaan4, true),
            new Question(R.string.pertanyaan5, false),
            new Question(R.string.pertanyaan6, false),
            new Question(R.string.pertanyaan7, true),
            new Question(R.string.pertanyaan8, true),
            new Question(R.string.pertanyaan9, false),
            new Question(R.string.pertanyaan10, true),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laman_kuis);

        question_textView = findViewById(R.id.textView2);
        button_true = findViewById(R.id.button_true);
        button_false = findViewById(R.id.button_false);
        next_button = findViewById(R.id.next_button);
        prev_button = findViewById(R.id.prev_button);

        button_false.setOnClickListener(this);
        button_true.setOnClickListener(this);
        next_button.setOnClickListener(this);
        prev_button.setOnClickListener(this);

        question_textView.setText(R.string.pertanyaan1);

    }

    @SuppressLint({"SetTextI18n", "StringFormatInvalid"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        // checking which button is
        // clicked by user
        // in this case user choose false
        switch (v.getId()) {
            case R.id.button_false:
                checkAnswer(false);
                break;

            case R.id.button_true:
                checkAnswer(true);
                break;

            case R.id.next_button:
                // go to next question
                // limiting question bank range
                if (currentQuestionIndex < 11) {
                    currentQuestionIndex
                            = currentQuestionIndex + 1;
                    // we are safe now!
                    // last question reached
                    // making buttons
                    // invisible
                    if (currentQuestionIndex == 10) {
                        question_textView.setText(getString(
                                R.string.correct_answer, correct));
                        next_button.setVisibility(
                                View.INVISIBLE);
                        prev_button.setVisibility(
                                View.INVISIBLE);
                        button_true.setVisibility(
                                View.INVISIBLE);
                        button_false.setVisibility(
                                View.INVISIBLE);
                        if (correct > 6)
                            question_textView.setText(
                                    "Poin anda " + correct
                                            + " "
                                            + " " + " " + " " + " " + "   " +
                                            "  " +
                                            "  " +
                                            "  " +
                                            "            SELAMAT ANDA LULUS MENJADI CALON IBU!");
                            // showing correctness
                        else
                            question_textView.setText("ANDA GAGAL MENJADI CALON IBU");
                    }
                    else {
                        updateQuestion();
                    }
                }

                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex
                            = (currentQuestionIndex - 1)
                            % questionBank.length;
                    updateQuestion();
                }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void updateQuestion()
    {
        Log.d("Current",
                "onClick: " + currentQuestionIndex);

        question_textView.setText(
                questionBank[currentQuestionIndex]
                        .getAnswerResId());
        // setting the textview with new question
    }
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue
                = questionBank[currentQuestionIndex]
                .isAnswerTrue();
        // getting correct ans of current question
        int toastMessageId;
        // if ans matches with the
        // button clicked

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {
            // showing toast
            // message correct
            toastMessageId = R.string.wrong_answer;
        }

        Toast
                .makeText(LamanKuis.this, toastMessageId,
                        Toast.LENGTH_SHORT)
                .show();
    }
}