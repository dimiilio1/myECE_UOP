package net.dimitrisiliou.myhmmy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecretaryFragment extends Fragment implements View.OnClickListener {
    TextView tv_e_merimnaButton,tv_e_classButton,tv_emailSecretary,tv_emailAktipis,tv_emailLikoudi,tv_emailMallopoulou,tv_emailPatsogianni,tv_phoneAktipis,tv_phoneLikoudi,tv_phonePatsogianni,tv_phoneMallopoulou,tv_e_secretaryButton;


    public SecretaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_secretary, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_emailSecretary= view.findViewById(R.id.sec_email);
        tv_emailSecretary.setOnClickListener(this);
        tv_emailAktipis= view.findViewById(R.id.aktipis_email);
        tv_emailAktipis.setOnClickListener(this);
        tv_emailLikoudi= view.findViewById(R.id.likoudi_email);
        tv_emailLikoudi.setOnClickListener(this);
        tv_emailMallopoulou= view.findViewById(R.id.mallopoulou_mail);
        tv_emailMallopoulou.setOnClickListener(this);
        tv_emailPatsogianni= view.findViewById(R.id.patsogianni__mail);
        tv_emailPatsogianni.setOnClickListener(this);
        tv_phoneAktipis= view.findViewById(R.id.aktipis_phone);
        tv_phoneAktipis.setOnClickListener(this);
        tv_phoneLikoudi= view.findViewById(R.id.likoudi_phone);
        tv_phoneLikoudi.setOnClickListener(this);
        tv_phonePatsogianni= view.findViewById(R.id.patsogianni_phone);
        tv_phonePatsogianni.setOnClickListener(this);
        tv_phoneMallopoulou= view.findViewById(R.id.mallopoulou_phone);
        tv_phoneMallopoulou.setOnClickListener(this);
        tv_e_secretaryButton= view.findViewById(R.id.e_secretaryButton);
        tv_e_secretaryButton.setOnClickListener(this);
        tv_e_classButton= view.findViewById(R.id.e_classButton);
        tv_e_classButton.setOnClickListener(this);
        tv_e_merimnaButton= view.findViewById(R.id.email);
        tv_e_merimnaButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String clickedView = null;
        String intentType = null;
        switch(view.getId()) {
            case R.id.sec_email:
                clickedView ="mailto:secretary.ece@uop.gr";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.likoudi_email:
                clickedView = "mailto:likoudi@uop.gr";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.likoudi_phone:
                clickedView ="tel:+302610369263";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.patsogianni__mail:
                clickedView ="mailto:apatso@uop.gr";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.patsogianni_phone:
                clickedView ="tel:+302610369193";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.mallopoulou_mail:
                clickedView ="mailto:mariamal@uop.gr";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.mallopoulou_phone:
                clickedView ="tel:+302610369237";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.aktipis_email:
                clickedView ="mailto:aktipis@uop.gr";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.aktipis_phone:
                clickedView ="tel:+302610369236";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.e_secretaryButton:
                clickedView ="https://e-students.teiwest.gr/";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.e_classButton:
                clickedView ="https://eclass.uop.gr/";
                intentType = Intent.ACTION_VIEW;
                break;
            case R.id.email:
                clickedView ="http://mail.go.uop.gr/";
                intentType = Intent.ACTION_VIEW;
                break;
        }
        Uri uri = Uri.parse(clickedView);
        Intent intent = new Intent(intentType,uri);
        startActivity(intent);
    }
}
