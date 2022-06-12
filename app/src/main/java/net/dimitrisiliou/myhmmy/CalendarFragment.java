package net.dimitrisiliou.myhmmy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class CalendarFragment extends Fragment implements View.OnClickListener {
    TextView tv_curriculumButton;


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);



    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        tv_curriculumButton= view.findViewById(R.id.curriculumButton);
        tv_curriculumButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Uri uri = Uri.parse("https://www.ece.uop.gr/announcements/orologio-programma-eksetastikes/");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
        Log.d("CREATION", String.valueOf(uri));
    }
    }
