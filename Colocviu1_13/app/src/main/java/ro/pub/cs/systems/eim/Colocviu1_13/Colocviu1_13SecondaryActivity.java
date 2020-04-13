package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {

    private Button registerButton, cancelButton;
    private TextView commandsView;

    private Colocviu1_13SecondaryActivity.ButtonClickListener buttonClickListener = new Colocviu1_13SecondaryActivity.ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.register_button:
                    Intent resultIntentRegister = new Intent();
                    resultIntentRegister.putExtra(Constants.RETURN_BUTTON, "REGISTER");
                    setResult(RESULT_OK, resultIntentRegister);
                    finish();
                    break;

                case R.id.cancel_button:
                    Intent resultIntentCancel = new Intent();
                    resultIntentCancel.putExtra(Constants.RETURN_BUTTON, "CANCEL");
                    setResult(RESULT_OK, resultIntentCancel);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        commandsView = (TextView)findViewById(R.id.commands_view);
        registerButton = (Button)findViewById(R.id.register_button);
        registerButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.COMMANDS)) {
            String commands = intent.getStringExtra(Constants.COMMANDS);
            commandsView.setText(commands);
        }
    }
}
