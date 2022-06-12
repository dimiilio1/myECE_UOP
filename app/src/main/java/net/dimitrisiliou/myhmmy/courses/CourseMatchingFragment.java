package net.dimitrisiliou.myhmmy.courses;

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
import net.dimitrisiliou.myhmmy.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CourseMatchingFragment extends Fragment implements CourseMatchingAdapter.OnNoteListener {
    private CourseMatchingAdapter adapter;
    private List<CourseTeiMatchingModel> courseList = new ArrayList<>();
    RecyclerView RvCourses;
    String hmmyTitle, semester, hours, lab, tutoring;

    public CourseMatchingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);
        Toolbar myToolbar = rootView.findViewById(R.id.toolbarCourses);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Αναζήτηση");

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillCourseList();
        setUpRecyclerView();

    }

    private void fillCourseList() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        String old_studies = dataBaseHelper.getSettings("STUDIES");

        if (old_studies.equals("cied_choice")) {
            courseList.addAll(dataBaseHelper.getAllCiedCourses());

        } else if (old_studies.equals("ele_choice")) {
            courseList.addAll(dataBaseHelper.getAllFelCourses());
        }
    }

    private void setUpRecyclerView() {
        RvCourses = getView().findViewById(R.id.rv_courses);
        RvCourses.setHasFixedSize(true);
        RvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CourseMatchingAdapter(courseList, this);
        RvCourses.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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


    @Override
    public void onNoteClick(int position) {

        CourseTeiMatchingModel mapTei = courseList.get(position);
        String hmmyid = mapTei.getCourseMatch();
        String teiId = mapTei.getId();
        String teiTitle = mapTei.getTitle();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        List<CourseHmmyMatchingModel> course = dataBaseHelper.getHmmyCourse(hmmyid);
        List<CourseHmmyMatchingModel> course2 = new ArrayList<>(course);
        course2.addAll(course);
        CourseHmmyMatchingModel map = course2.get(0);

        lab = String.valueOf(map.getLab());
        hours = String.valueOf(map.getHours());
        tutoring = String.valueOf(map.getTutor());
        semester = String.valueOf(map.getSemester());
        hmmyTitle = String.valueOf(map.getTitle());


        Bundle buddle = new Bundle();
        buddle.putString("lab", lab);
        buddle.putString("hours", hours);
        buddle.putString("tutoring", tutoring);
        buddle.putString("semester", semester);
        buddle.putString("hmmyTitle", hmmyTitle);
        buddle.putString("hmmyid", hmmyid);
        buddle.putString("teiId", teiId);
        buddle.putString("teiTitle", teiTitle);

        CourseFragmentDetails courseFragmentDetails = new CourseFragmentDetails();
        courseFragmentDetails.setArguments(buddle);
        courseFragmentDetails.show(getActivity().getSupportFragmentManager(), "tagname");
    }
}