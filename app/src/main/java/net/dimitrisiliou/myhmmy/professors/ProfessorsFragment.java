package net.dimitrisiliou.myhmmy.professors;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessorsFragment extends Fragment {


    RecyclerView RvProfessors;
    ProfessorsAdapter adapter;
    List<ProfessorsItem> mdata;

    public ProfessorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_professors, container, false);
        Toolbar myToolbar = rootView.findViewById(R.id.toolbarProf);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Email και Πληροφορίες Καθηγητών");
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RvProfessors = view.findViewById(R.id.rv_professors);

        // create list of professors items

        ProfessorsItem item = new ProfessorsItem("Παναγιώτης Αλεφραγκής",getString(R.string.assistance_professor),R.drawable.alefrag,"mailto:alefrag@uop.gr","https://www.ece.uop.gr/staff/alefragkis-panagiotis/");
        ProfessorsItem item2 = new ProfessorsItem("Γεώργιος Αντωνόπουλος",getString(R.string.lecturer),R.drawable.user,"mailto:gantonopoulos@uop.gr","https://www.ece.uop.gr/staff/antonopoulos-georgios/");
        ProfessorsItem item3 = new ProfessorsItem("Χρήστος Αντωνόπουλος",getString(R.string.assistance_professor),R.drawable.anton_ch,"mailto:gantonopoulos@uop.gr","https://www.ece.uop.gr/staff/christos-antonopoulos/");
        ProfessorsItem item4 = new ProfessorsItem("Γιώργος Ασημακόπουλος",getString(R.string.lecturer),R.drawable.asimakopoulo,"mailto:asim@uop.gr","https://www.ece.uop.gr/staff/asimakopoulos-giorgos/");
        ProfessorsItem item5 = new ProfessorsItem("Πέτρος Βλαχόπουλος",getString(R.string.professor),R.drawable.vlachopoulos,"mailto:petrosv@uop.gr","https://www.ece.uop.gr/staff/vlachopoulos-petros/");
        ProfessorsItem item6 = new ProfessorsItem("Νικόλαος Βώρος",getString(R.string.professor),R.drawable.voros,"mailto:voros@uop.gr","https://www.ece.uop.gr/staff/voros-nikolaos/");
        ProfessorsItem item7 = new ProfessorsItem("Κωνσταντίνος Γεωργάκας",getString(R.string.assistance_professor),R.drawable.georgakas,"mailto:kgeorgakas@uop.gr","https://www.ece.uop.gr/staff/konstantinos-georgakas/");
        ProfessorsItem item8 = new ProfessorsItem("Αναστάσιος Δροσόπουλος",getString(R.string.professor),R.drawable.drosopoulos,"mailto:drosop@uop.gr","https://www.ece.uop.gr/staff/drosopoulos-anastasios/");
        ProfessorsItem item9 = new ProfessorsItem("Λάμπρος Δρόσος",getString(R.string.professor),R.drawable.user,"mailto:ldrossos@uop.gr","https://www.ece.uop.gr/staff/drosos-lampros/");
        ProfessorsItem item10 = new ProfessorsItem("Ιωάννης Δ. Ζαχαράκης",getString(R.string.professor),R.drawable.zaharakis,"mailto:zaharakis@uop.gr","https://www.ece.uop.gr/staff/zaharakis-ioannis/");
        ProfessorsItem item11 = new ProfessorsItem("Παναγιώτης Ζέρβας",getString(R.string.assistance_professor),R.drawable.zervas,"mailto:pzervas@uop.gr","https://www.ece.uop.gr/staff/panagiotis-zervas/");
        ProfessorsItem item12 = new ProfessorsItem("Δημήτρης Καρέλης",getString(R.string.assistance_professor),R.drawable.karelis,"mailto:dkarelis@uop.gr","https://www.ece.uop.gr/staff/karelis-dimitris/");
        ProfessorsItem item13 = new ProfessorsItem("Βασίλης Καψάλης",getString(R.string.professor),R.drawable.kapsalis,"mailto:kapsalis@uop.gr","https://www.ece.uop.gr/staff/kapsalis-vasilis/");
        ProfessorsItem item14 = new ProfessorsItem("Παρασκευάς Κίτσος",getString(R.string.associate_professor),R.drawable.kitsos,"mailto:kitsos@uop.gr","https://www.ece.uop.gr/staff/kitsos-paraskevas/");
        ProfessorsItem item15 = new ProfessorsItem("Ιωάννης Κούγιας",getString(R.string.professor),R.drawable.kougias,"mailto:kougias@uop.gr","https://www.ece.uop.gr/staff/kougias-ioannis/");
        ProfessorsItem item16 = new ProfessorsItem("Αθανάσιος Κούτρας",getString(R.string.assistance_professor),R.drawable.koutras,"mailto:koutras@uop.gr","https://www.ece.uop.gr/staff/koutras-athanasios/");
        ProfessorsItem item17 = new ProfessorsItem("Λάμπρος Μπισδούνης",getString(R.string.professor),R.drawable.bisdounis,"mailto:bisdounis@uop.gr","https://www.ece.uop.gr/staff/mpisdounis-lampros/");
        ProfessorsItem item18 = new ProfessorsItem("Μιχάλης Παρασκευάς",getString(R.string.associate_professor),R.drawable.paraskevas,"mailto:mparask@uop.gr","https://www.ece.uop.gr/staff/paraskevas-michalis/");
        ProfessorsItem item19 = new ProfessorsItem("Νίκος Πετρέλλης",getString(R.string.associate_professor),R.drawable.petrellis,"mailto:nPetrellis@uop.gr","https://www.ece.uop.gr/staff/petrellis-nikos/");
        ProfessorsItem item20 = new ProfessorsItem("Πολίτη Χριστίνα",getString(R.string.assistance_professor),R.drawable.politi,"mailto:tpoliti@uop.gr","https://www.ece.uop.gr/staff/christina-tania-politi/");
        ProfessorsItem item21 = new ProfessorsItem("Γιώργος Σουλιώτης",getString(R.string.assistance_professor),R.drawable.souliotis,"mailto:gsoul@uop.gr","https://www.ece.uop.gr/staff/souliotis-giorgos/");
        ProfessorsItem item22 = new ProfessorsItem("Ηλίας Σταθάτος",getString(R.string.professor),R.drawable.stathatos,"mailto:estathatos@uop.gr","https://www.ece.uop.gr/staff/stathatos-ilias/");
        ProfessorsItem item23 = new ProfessorsItem("Σπύρος Συρμακέσης",getString(R.string.professor),R.drawable.sirmakessis,"mailto:syrma@uop.gr","https://www.ece.uop.gr/staff/syrmakesis-spyros/");
        ProfessorsItem item24 = new ProfessorsItem("Νικόλαος Σχοινάς",getString(R.string.assistance_professor),R.drawable.user,"mailto:nschinas@uop.gr","https://www.ece.uop.gr/staff/schoinas-nikolaos/");
        ProfessorsItem item25 = new ProfessorsItem("Σωτηρόπουλος Δημήτρης",getString(R.string.assistance_professor),R.drawable.sotiropoulos,"mailto:dg.sotiropoulos@uop.gr","https://www.ece.uop.gr/staff/sotiropoulos-dimitris/");
        ProfessorsItem item26 = new ProfessorsItem("Βασίλειος Ταμπακάς",getString(R.string.professor),R.drawable.tampakas,"mailto:tampakas@uop.gr","https://www.ece.uop.gr/staff/vasileios-tampakas/");
        ProfessorsItem item27 = new ProfessorsItem("Ιωάννης Τζήμας",getString(R.string.associate_professor),R.drawable.tzimas,"mailto:tzimas@uop.gr","https://www.ece.uop.gr/staff/tzimas-ioannis/");
        ProfessorsItem item28 = new ProfessorsItem("Βασίλειος Τριανταφύλλου",getString(R.string.professor),R.drawable.triantafyllou,"mailto:vtriantaf@uop.gr","https://www.ece.uop.gr/staff/triantafyllou-vasileios/");
        ProfessorsItem item29 = new ProfessorsItem("Ιωάννης Τσακνάκης",getString(R.string.associate_professor),R.drawable.tsaknakis,"mailto:jtsaknakis@uop.gr","https://www.ece.uop.gr/staff/ioannis-tsaknakis/");
        ProfessorsItem item30 = new ProfessorsItem("Λουκάς Χαδέλλης",getString(R.string.professor),R.drawable.user,"mailto:loukas@uop.gr","https://www.ece.uop.gr/staff/chadellis-loukas/");
        ProfessorsItem item31 = new ProfessorsItem("Βασίλειος Χαραλαμπάκος",getString(R.string.assistance_professor),R.drawable.charalampakos,"mailto:charalambakos@uop.gr","https://www.ece.uop.gr/staff/charalampakos-vasileios/");
        ProfessorsItem item32 = new ProfessorsItem("Σωτήρης Π. Χριστοδούλου",getString(R.string.assistance_professor),R.drawable.christodoulou,"mailto:sxristod@uop.gr","https://www.ece.uop.gr/staff/sotiris-p-christodoulou/");


        mdata = new ArrayList<>();
        mdata.add(item);
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
        mdata.add(item13);
        mdata.add(item14);
        mdata.add(item15);
        mdata.add(item16);
        mdata.add(item17);
        mdata.add(item18);
        mdata.add(item19);
        mdata.add(item20);
        mdata.add(item21);
        mdata.add(item22);
        mdata.add(item23);
        mdata.add(item24);
        mdata.add(item25);
        mdata.add(item26);
        mdata.add(item27);
        mdata.add(item28);
        mdata.add(item29);
        mdata.add(item30);
        mdata.add(item31);
        mdata.add(item32);



        // setup adapter and recyclerview

        RvProfessors.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProfessorsAdapter(this,mdata);
        RvProfessors.setAdapter(adapter);


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
