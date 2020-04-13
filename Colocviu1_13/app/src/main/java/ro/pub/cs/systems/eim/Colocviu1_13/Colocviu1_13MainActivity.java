package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
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

    private int serviceStatus = Constants.SERVICE_STOPPED;

    private IntentFilter intentFilter = new IntentFilter();

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

                case R.id.navigate_button:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_13SecondaryActivity.class);
                    intent.putExtra(Constants.COMMANDS, pressedView.getText().toString());
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    total = 0;
                    pressedView.setText("");
                    break;
            }

            if (total >= 4) {
                Intent serviceIntent = new Intent(getApplicationContext(), Colocviu1_13Service.class);
                serviceIntent.putExtra(Constants.SERVICE_COMMANDS, pressedView.getText().toString());
                getApplication().startService(serviceIntent);
                serviceStatus = Constants.SERVICE_STARTED;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if ((requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) && (intent != null)) {
            String but = intent.getStringExtra(Constants.RETURN_BUTTON);
            Toast.makeText(this, "Pressed button: " + but, Toast.LENGTH_LONG).show();
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

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
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

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("SERVICE_MESSAGE", intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_13Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
