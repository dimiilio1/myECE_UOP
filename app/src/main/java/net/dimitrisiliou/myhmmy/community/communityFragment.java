package net.dimitrisiliou.myhmmy.community;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.dimitrisiliou.myhmmy.R;
import net.dimitrisiliou.myhmmy.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class communityFragment extends Fragment implements communityCallback {

    List<communityItem> mdata;
    RecyclerView rv_community;
    communityAdapter communityAdapter;

    public communityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataBaseHelper dbHelper = new DataBaseHelper(getContext());
        String old_studies = dbHelper.getSettings("STUDIES");
        rv_community = view.findViewById(R.id.rv_community);
        // create a list of items
        mdata = new ArrayList<>();

        if(old_studies.equals("hmmy_choice") || old_studies.equals("ele_choice")) {
            mdata.add(new communityItem(R.drawable.chaticon));
        }else {
            mdata.add(new communityItem(R.drawable.chaticon));
            mdata.add(new communityItem(R.drawable.chaticon_cied));
        }
        mdata.add(new communityItem(R.drawable.notesicon));
        mdata.add(new communityItem(R.drawable.wecare));
        mdata.add(new communityItem(R.drawable.hmmyfbbanner));
        mdata.add(new communityItem(R.drawable.hmmytwitterbanner));
        mdata.add(new communityItem(R.drawable.hmmylinkedinbanner));
        communityAdapter = new communityAdapter(mdata,this);
        // setup grid recyclerview
        rv_community.setLayoutManager(new GridLayoutManager(getActivity(),1));
        rv_community.setAdapter(communityAdapter);

    }

    @Override
    public void onCommunityItemClick(int pos) {
        DataBaseHelper dbHelper = new DataBaseHelper(getContext());
        String old_studies = dbHelper.getSettings("STUDIES");
        if(old_studies.equals("hmmy_choice") || old_studies.equals("ele_choice")) {
        if (pos == 0) {
                Uri uri = Uri.parse("https://www.facebook.com/groups/elec.teipat/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } else if (pos == 1) {
            Uri uri = Uri.parse("https://drive.google.com/drive/folders/12-fJAINrwViiht1YXPh5-dendcuGDmqX?usp=sharing");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            }else if (pos == 2) {
                Uri uri = Uri.parse("https://wecare.uop.gr/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }else if (pos == 3) {
            Uri uri = Uri.parse("https://www.facebook.com/%CE%A4%CE%BC%CE%AE%CE%BC%CE%B1-%CE%97%CE%BB%CE%B5%CE%BA%CF%84%CF%81%CE%BF%CE%BB%CF%8C%CE%B3%CF%89%CE%BD-%CE%9C%CE%B7%CF%87%CE%B1%CE%BD%CE%B9%CE%BA%CF%8E%CE%BD-%CE%9C%CE%B7%CF%87%CE%B1%CE%BD%CE%B9%CE%BA%CF%8E%CE%BD-%CE%A5%CF%80%CE%BF%CE%BB%CE%BF%CE%B3%CE%B9%CF%83%CF%84%CF%8E%CE%BD-%CE%A0%CE%B1%CE%BD-%CE%A0%CE%B5%CE%BB%CE%BF%CF%80%CE%BF%CE%BD%CE%BD%CE%AE%CF%83%CE%BF%CF%85-526093927472950/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

            }else {
            if (pos == 0) {
                Uri uri = Uri.parse("https://www.facebook.com/groups/elec.teipat/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } else if (pos == 1) {
                Uri uri = Uri.parse("https://www.facebook.com/groups/tesyd");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }else if (pos == 2) {
                Uri uri = Uri.parse("https://drive.google.com/drive/folders/12-fJAINrwViiht1YXPh5-dendcuGDmqX?usp=sharing");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }else if (pos == 3) {
                Uri uri = Uri.parse("https://wecare.uop.gr/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            else if (pos == 4) {
                Uri uri = Uri.parse("https://www.facebook.com/ece.uop.gr/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            else if (pos == 5) {
                Uri uri = Uri.parse("https://twitter.com/ECE_UoP");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            else if (pos == 6) {
                Uri uri = Uri.parse("https://www.linkedin.com/company/eceuop/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }
    }}