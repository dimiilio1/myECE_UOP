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

public class ExtraStudiesFragment extends Fragment implements View.OnClickListener {
    TextView tv_phdLink,tv_smart_ict_link;


    public ExtraStudiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_extrastudies, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_smart_ict_link= view.findViewById(R.id.smart_ict_link);
        tv_smart_ict_link.setOnClickListener(this);
        tv_phdLink= view.findViewById(R.id.phd_link);
        tv_phdLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String clickedView = null;
        String intentType = null;
        switch(view.getId()) {
            case R.id.phd_link:
                clickedView = "https://www.ece.uop.gr/didaktorikes/";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.smart_ict_link:
                clickedView = "https://www.ece.uop.gr/metaptychiakes/";
                intentType = Intent.ACTION_VIEW;
                break;
        }
        Uri uri = Uri.parse(clickedView);
        Intent intent = new Intent(intentType,uri);
        startActivity(intent);
        Log.d("CREATION", String.valueOf(uri));
    }
}
