package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01var08.Constants.Constants;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    private Button playButton;

    private EditText riddleEditText;
    private EditText answerEditText;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.play_button) {
                if (!riddleEditText.getText().toString().equals("") &&
                    !answerEditText.getText().toString().equals(""))
                {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                    intent.putExtra(Constants.RIDDLE_TEXT, riddleEditText.getText().toString());
                    intent.putExtra(Constants.ANSWER_TEXT, answerEditText.getText().toString());
                    startActivityForResult(intent, Constants.PLAY_RESULT_CODE);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        playButton = findViewById(R.id.play_button);

        playButton.setOnClickListener(buttonClickListener);

        riddleEditText = findViewById(R.id.riddle_edit_text);
        answerEditText = findViewById(R.id.answer_edit_text);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.RIDDLE_TEXT)) {
                riddleEditText.setText(savedInstanceState.getString(Constants.RIDDLE_TEXT));
            } else {
                riddleEditText.setText("");
            }
            if (savedInstanceState.containsKey(Constants.ANSWER_TEXT)) {
                answerEditText.setText(savedInstanceState.getString(Constants.ANSWER_TEXT));
            } else {
                answerEditText.setText("");
            }
        } else {
            riddleEditText.setText("");
            answerEditText.setText("");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.RIDDLE_TEXT, riddleEditText.getText().toString());
        outState.putString(Constants.ANSWER_TEXT, answerEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.RIDDLE_TEXT)) {
            riddleEditText.setText(savedInstanceState.getString(Constants.RIDDLE_TEXT));
        } else {
            riddleEditText.setText("");
        }
        if (savedInstanceState.containsKey(Constants.ANSWER_TEXT)) {
            answerEditText.setText(savedInstanceState.getString(Constants.ANSWER_TEXT));
        } else {
            answerEditText.setText("");
        }
    }
}