package net.dimitrisiliou.myhmmy.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.dimitrisiliou.myhmmy.R;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {


    RecyclerView RvMap;
    MapAdapter adapter;
    List<MapItem> mdata;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        Toolbar myToolbar = rootView.findViewById(R.id.toolbarMap);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Αίθουσες, Εργαστήρια κ.α");
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RvMap = view.findViewById(R.id.rv_map);

        MapItem item = new MapItem("Κτήριο Η'","1ος Όροφος:  Εργαστήρια Η1.01, Η1.02, Η1.04 και Γραφεία Η1.03, Η1.05 - Η1.12 \n2ος Όροφος: Αίθουσα Πολλαπλών Χρήσεων Η2.02, Εργαστήριο Η2.03 και Γραφεία Η2.01, Η2.04 - Η2.08","https://goo.gl/maps/FVHdgLBoF2hEDTkW8");
        MapItem item1 = new MapItem("Κεντρικό Κτήριο Σχολής Μηχανικών","Ισόγειο: Γραμματεία, Εργαστήρια ΗΛΜ - ΣΧΓ, Αίθουσα Α19 \n1ος Όροφος:  Εργαστήρια ΗΛΝ, ΨΗΦ, ΚΥΚ, ΣΗΕ, ΜΙΚ, ΙΣΧ, ΚΙΝ, ΜΕΤ, PLC και Φ/Β","https://goo.gl/maps/9BpgXhKB7FHF7yFe7");
        MapItem item2 = new MapItem("Πτέρυγα Ζ'","Ισόγειο: Εργαστήριο H/Y Z1 και Αίθουσες Ζ4, Ζ5 \n1ος Όροφος: Αίθουσες Ζ6 - Ζ12 \n2ος Όροφος: Εργαστήριο ΣΑΕ και Αίθουσες Ζ17, Ζ18","https://goo.gl/maps/C73Rp8Tp1BTXTbBR9");
        MapItem item3 = new MapItem("Κτήριο Ε'","Ισόγειο: Εργαστήριο Ε1","https://goo.gl/maps/SB9rsL35JFc8oSS7A");
        MapItem item4 = new MapItem("Κτήριο Κ'","Ισόγειο: Αίθουσες Κ0.01 - Κ0.04 και Γραφεία  Κ0.05 - Κ0.15 \n1ος Όροφος: Αίθουσες Κ1.07 και Κ1.12 \n2ος Όροφος: Γραφεία και Εργαστήρια Κ2.01 - Κ2.14","https://goo.gl/maps/m12iPzwinbJksfmdA" );
        MapItem item5 = new MapItem("Μεγάλο Αμφιθέατρο","ΑΜΦ1","https://goo.gl/maps/BVEBJS7gRy2iVgdB6");
        MapItem item6 = new MapItem("Μικρό Αμφιθέατρο ","ΑΜΦ2","https://goo.gl/maps/soGfyue3vh2CZQkj6");
        MapItem item7 = new MapItem("Βιβλιοθήκη","","https://goo.gl/maps/A7zN65PJVXjxuzKG6");
        MapItem item8 = new MapItem("Φοιτητική Εστία",null,"https://goo.gl/maps/iy2ZFxcPD8cUSgY57");
        MapItem item9 = new MapItem("Εστιατόριο","","https://www.google.com/maps/place/38%C2%B013'10.1%22N+21%C2%B044'49.2%22E/@38.2194729,21.74701,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d38.2194729!4d21.74701");
        MapItem item10 = new MapItem("Κυλικείο","","https://goo.gl/maps/ZqsBnd2oD75xekCt6");
        MapItem item11 = new MapItem("Συνεδριακό Κέντρο","","https://goo.gl/maps/gAQeTEHmofcurh3SA");
        MapItem item12 = new MapItem("Ερευνητικό Εργαστήριο 'Έξυπνο Σπίτι'","","https://goo.gl/maps/ayV9sHVyYqgN1PHY9");

        mdata = new ArrayList<>();
        mdata.add(item);
        mdata.add(item1);
        mdata.add(item2);
        mdata.add(item3);
        mdata.add(item4);
        mdata.add(item5);
        mdata.add(item6);
        mdata.add(item7);
        mdata.add(item8);
        mdata.add(item9);
        mdata.add(item10);
        mdata.add(item11);
        mdata.add(item12);

        // setup adapter and recyclerview
        RvMap.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MapAdapter(this,mdata);
        RvMap.setAdapter(adapter);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.course_matching_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        searchView.setQueryHint("Αναζήτηση");
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
