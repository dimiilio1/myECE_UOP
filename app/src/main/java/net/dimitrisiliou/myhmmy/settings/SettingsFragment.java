package net.dimitrisiliou.myhmmy.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.dimitrisiliou.myhmmy.R;
import net.dimitrisiliou.myhmmy.WelcomeActivity;
import net.dimitrisiliou.myhmmy.database.DataBaseHelper;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    RadioButton choice1,choice2,choice3,newsyes,newsno;
    TextView feedbackbutton1;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        choice1 = (RadioButton) view.findViewById(R.id.hmmy_choice);
        choice2 = (RadioButton) view.findViewById(R.id.cied_choice);
        choice3 = (RadioButton) view.findViewById(R.id.ele_choice);
        newsyes = (RadioButton) view.findViewById(R.id.yes_news_choice);
        newsno = (RadioButton) view.findViewById(R.id.no_news_choice);
        feedbackbutton1 =  view.findViewById(R.id.feedbackButton1);

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        newsyes.setOnClickListener(this);
        newsno.setOnClickListener(this);
        feedbackbutton1.setOnClickListener(this);

        DataBaseHelper dbHelper = new DataBaseHelper(getContext());
        String notif = dbHelper.getSettings("NEWS_NOTIFICATION");
        String studies = dbHelper.getSettings("STUDIES");

        switch (studies) {
            case "hmmy_choice":
                choice1.setChecked(true);
                break;
            case "cied_choice":
                choice2.setChecked(true);
                break;
            case "ele_choice":
                choice3.setChecked(true);
                break;
        }
        switch (notif) {
            case "yes":
                newsyes.setChecked(true);
                break;
            case "no":
                newsno.setChecked(true);
                break;
        }
    }


    public void onClick(View view) {

        // Is the button now checked?
        boolean checked;
        Intent restart = new Intent (getActivity(), WelcomeActivity.class);
        DataBaseHelper dbHelper = new DataBaseHelper(getContext());
        // Check which radio button was clicked
        Intent sendFeedback = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:support@dimitrisiliou.atlassian.net"));
        switch(view.getId()) {

            case R.id.hmmy_choice:
                checked = ((RadioButton) view).isChecked();
                if (checked)
                    dbHelper.changeSetting("STUDIES","hmmy_choice");
                startActivity(restart);
                    break;
            case R.id.cied_choice:
                checked = ((RadioButton) view).isChecked();
                if (checked)
                    dbHelper.changeSetting("STUDIES","cied_choice");
                startActivity(restart);
                    break;
            case R.id.ele_choice:
                checked = ((RadioButton) view).isChecked();
                if (checked)
                    dbHelper.changeSetting("STUDIES","ele_choice");
                startActivity(restart);
                    break;
            case R.id.yes_news_choice:
                checked = ((RadioButton) view).isChecked();
                if (checked)
                    dbHelper.changeSetting("NEWS_NOTIFICATION","yes");
                    break;
            case R.id.no_news_choice:
                checked = ((RadioButton) view).isChecked();
                if (checked)
                    dbHelper.changeSetting("NEWS_NOTIFICATION","no");
                    break;

        }
        if (feedbackbutton1.equals(view)) {
            startActivity(sendFeedback);
        }
    }
}