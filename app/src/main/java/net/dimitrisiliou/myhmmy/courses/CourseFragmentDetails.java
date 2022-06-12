package net.dimitrisiliou.myhmmy.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import net.dimitrisiliou.myhmmy.R;

public class CourseFragmentDetails extends BottomSheetDialogFragment {
    TextView teiTitle,hmmyid,semester,hours,lab,tutoring,teiID,hmmyTitle;
    String titleTEI,titleHMMY,idHMMY,idTEI,sem,tutor,hour,labs;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();

        if (bundle != null) {
            titleTEI = bundle.getString("teiTitle");
            titleHMMY = bundle.getString("hmmyTitle");
            idHMMY = bundle.getString("hmmyid");
            idTEI = bundle.getString("teiId");
            sem = bundle.getString("semester");
            tutor = bundle.getString("tutoring");
            hour = bundle.getString("hours");
            labs = bundle.getString("lab");
        }

        return inflater.inflate(R.layout.fragment_courses_details, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        teiID = getView().findViewById(R.id.tei_idview);
        teiTitle =getView().findViewById(R.id.teicourceview);
        hmmyid =getView().findViewById(R.id.hmmy_idview);
        semester = getView().findViewById(R.id.hmmy_semesterview);
        hours = getView().findViewById(R.id.hmmy_hoursview);
        lab = getView().findViewById(R.id.hmmy_labview);
        tutoring = getView().findViewById(R.id.hmmy_tutoringview);
        hmmyTitle = getView().findViewById(R.id.hmmytitleview);

        teiTitle.setText(titleTEI);
        teiID.setText(idTEI);
        teiTitle.setText(titleTEI);
        hmmyid.setText(idHMMY);
        semester.setText(sem);
        hours.setText(hour);
        lab.setText(labs);
        tutoring.setText(tutor);
        hmmyTitle.setText(titleHMMY);
        }

    public CourseFragmentDetails() {
        // Required empty public constructor
    }
}
