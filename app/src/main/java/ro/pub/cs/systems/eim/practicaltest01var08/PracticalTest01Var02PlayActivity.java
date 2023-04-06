package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01var08.Constants.Constants;
import ro.pub.cs.systems.eim.practicaltest01var08.Services.PracticalTest01Var08Service;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    private Button checkButton;
    private Button backButton;

    private EditText riddleEditText;
    private EditText guessEditText;

    private String correctAnswer;

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast toast = Toast.makeText(context, intent.getExtras().getString(Constants.HINT), Toast.LENGTH_SHORT);
            toast.show();
            System.out.println(intent.getExtras().getString(Constants.HINT));
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.back_button) {
                finish();
            } else if (view.getId() == R.id.check_button) {
                if (!guessEditText.getText().toString().equals("")) {
                    if (guessEditText.getText().toString().equals(correctAnswer)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        checkButton = findViewById(R.id.check_button);
        backButton = findViewById(R.id.back_button);

        riddleEditText = findViewById(R.id.riddle_play_edit_text);
        guessEditText = findViewById(R.id.guess_edit_text);

        backButton.setOnClickListener(buttonClickListener);
        checkButton.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras().containsKey(Constants.ANSWER_TEXT)) {
                correctAnswer = intent.getExtras().getString(Constants.ANSWER_TEXT);
            }
            if (intent.getExtras().containsKey(Constants.RIDDLE_TEXT)) {
                riddleEditText.setText(intent.getExtras().getString(Constants.RIDDLE_TEXT));
            }
        }
        setResult(RESULT_OK, intent);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.RIDDLE_TEXT)) {
                riddleEditText.setText(savedInstanceState.getString(Constants.RIDDLE_TEXT));
            } else {
                riddleEditText.setText("");
            }
            if (savedInstanceState.containsKey(Constants.GUESS_TEXT)) {
                guessEditText.setText(savedInstanceState.getString(Constants.GUESS_TEXT));
            } else {
                guessEditText.setText("");
            }
        }

        Intent serviceIntent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
        serviceIntent.putExtra(Constants.ANSWER_TEXT, correctAnswer);
        getApplicationContext().startService(serviceIntent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.RIDDLE_TEXT, riddleEditText.getText().toString());
        outState.putString(Constants.GUESS_TEXT, guessEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.RIDDLE_TEXT)) {
            riddleEditText.setText(savedInstanceState.getString(Constants.RIDDLE_TEXT));
        } else {
            riddleEditText.setText("");
        }
        if (savedInstanceState.containsKey(Constants.GUESS_TEXT)) {
            guessEditText.setText(savedInstanceState.getString(Constants.GUESS_TEXT));
        } else {
            guessEditText.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }
}