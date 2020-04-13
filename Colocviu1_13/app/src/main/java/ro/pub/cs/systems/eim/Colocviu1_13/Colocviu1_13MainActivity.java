package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private Button northButton, southButton, eastButton, westButton;
    private TextView pressedView;
    private Button navigateButton;
    private int total = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String pressed;

            switch(view.getId()) {
                case R.id.north_button:
                    pressed = pressedView.getText().toString();
                    if (pressed.equals("")) {
                        pressed += northButton.getText().toString();
                    } else {
                        pressed += ", ";
                        pressed += northButton.getText().toString();
                    }
                    total++;
                    pressedView.setText(pressed);
                    break;

                case R.id.south_button:
                    pressed = pressedView.getText().toString();
                    if (pressed.equals("")) {
                        pressed += southButton.getText().toString();
                    } else {
                        pressed += ", ";
                        pressed += southButton.getText().toString();
                    }
                    total++;
                    pressedView.setText(pressed);
                    break;

                case R.id.east_button:
                    pressed = pressedView.getText().toString();
                    if (pressed.equals("")) {
                        pressed += eastButton.getText().toString();
                    } else {
                        pressed += ", ";
                        pressed += eastButton.getText().toString();
                    }
                    total++;
                    pressedView.setText(pressed);
                    break;

                case R.id.west_button:
                    pressed = pressedView.getText().toString();
                    if (pressed.equals("")) {
                        pressed += westButton.getText().toString();
                    } else {
                        pressed += ", ";
                        pressed += westButton.getText().toString();
                    }
                    total++;
                    pressedView.setText(pressed);
                    break;

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        pressedView = (TextView)findViewById(R.id.buttons_view);
        northButton = (Button)findViewById(R.id.north_button);
        northButton.setOnClickListener(buttonClickListener);
        southButton = (Button)findViewById(R.id.south_button);
        southButton.setOnClickListener(buttonClickListener);
        eastButton = (Button)findViewById(R.id.east_button);
        eastButton.setOnClickListener(buttonClickListener);
        westButton = (Button)findViewById(R.id.west_button);
        westButton.setOnClickListener(buttonClickListener);
        navigateButton = (Button)findViewById(R.id.navigate_button);
        navigateButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.TOTAL_PRESSED)) {
                total = savedInstanceState.getInt(Constants.TOTAL_PRESSED);
                Toast.makeText(getApplicationContext(), "Total pressed: " + Integer.toString(total), Toast.LENGTH_LONG).show();
            } else {
                total = 0;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(Constants.TOTAL_PRESSED, total);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.TOTAL_PRESSED)) {
                total = savedInstanceState.getInt(Constants.TOTAL_PRESSED);
                Toast.makeText(getApplicationContext(), "Total pressed: " + Integer.toString(total), Toast.LENGTH_LONG).show();
            } else {
                total = 0;
            }
        }
    }
}
