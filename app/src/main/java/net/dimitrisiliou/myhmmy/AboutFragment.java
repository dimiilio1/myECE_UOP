package net.dimitrisiliou.myhmmy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment implements View.OnClickListener {

   ImageView linkedin,website;


    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    linkedin = view.findViewById(R.id.mylinkedin);
    website = view.findViewById(R.id.mywebsite);
    linkedin.setOnClickListener(this);
    website.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (linkedin.equals(v)) {
            Uri uri = Uri.parse("https://www.linkedin.com/in/dimitrisiliou/");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        } else if (website.equals(v)) {
            Uri uri = Uri.parse("https://www.dimitrisiliou.net");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    }
}
