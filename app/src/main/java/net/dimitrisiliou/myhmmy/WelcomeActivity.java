package net.dimitrisiliou.myhmmy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import net.dimitrisiliou.myhmmy.database.DataBaseHelper;

import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity {
 private  static int SPLASH_TIME_OUT = 1500;
    TextView welcome,hiden,feedback,feedbacktxt;
    RadioGroup radioGroup;
    Button button;
    SharedPreferences pref;
    String choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());

            try {
                dataBaseHelper.createDatabase();
            } catch (IOException e) {
                e.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //set fragment for first use
                Boolean isFirstRun = getSharedPreferences("mystudies", MODE_PRIVATE)
                        .getBoolean("isFirstRun", true);
                if (isFirstRun) {
                    FirstScreen();
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
                    dataBaseHelper.changeSetting("STUDIES", "hmmy_choice");
                } else {
                    // set the default fragment when activity launch
                    Intent homeIntent = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
                getSharedPreferences("mystudies", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", false).apply();

            }
        },SPLASH_TIME_OUT);
    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        RadioButton button = (RadioButton) view;
        boolean checked = button.isChecked();
        Intent homeIntent = new Intent(WelcomeActivity.this,MainActivity.class);
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.hmmy_choice:
                if (checked)
                    dataBaseHelper.changeSetting("STUDIES", "hmmy_choice");
                    startActivity(homeIntent);
                finish();
                break;
            case R.id.cied_choice:
                if (checked)
                    dataBaseHelper.changeSetting("STUDIES", "cied_choice");
                    startActivity(homeIntent);
                    finish();
                break;
            case R.id.ele_choice:
                if (checked)
                    dataBaseHelper.changeSetting("STUDIES", "ele_choice");
                    startActivity(homeIntent);
                    finish();
                break;
        }
    }
    public void FirstScreen(){
        setContentView(R.layout.fragment_settings);

        welcome = (TextView) findViewById(R.id.welcome_settings);
        hiden = (TextView) findViewById(R.id.textView15);
        feedback = (TextView) findViewById(R.id.textView16);
        feedbacktxt = (TextView) findViewById(R.id.textView7);
        radioGroup = (RadioGroup) findViewById(R.id.radiobutton2);
        hiden.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        feedback.setVisibility(View.INVISIBLE);
        feedbacktxt.setVisibility(View.INVISIBLE);
        welcome.setText("Διάλεξε ένα από τα παρακάτω\n για να συνεχίσεις");
    }
}