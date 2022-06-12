package net.dimitrisiliou.myhmmy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import net.dimitrisiliou.myhmmy.BackroundUpdate.AppJobService;
import net.dimitrisiliou.myhmmy.community.communityFragment;
import net.dimitrisiliou.myhmmy.courses.CourseMatchingFragment;
import net.dimitrisiliou.myhmmy.database.DataBaseHelper;
import net.dimitrisiliou.myhmmy.map.MapFragment;
import net.dimitrisiliou.myhmmy.news.NewsFragment;
import net.dimitrisiliou.myhmmy.settings.SettingsFragment;
import net.dimitrisiliou.myhmmy.sidemenu.Callback;
import net.dimitrisiliou.myhmmy.sidemenu.MenuAdapter;
import net.dimitrisiliou.myhmmy.sidemenu.MenuItem;
import net.dimitrisiliou.myhmmy.sidemenu.MenuUtil;
import net.dimitrisiliou.myhmmy.professors.ProfessorsFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback {
    private static final String TAG = "MainActivity";
    RecyclerView menuRv;
    List<MenuItem> menuItems;
    MenuAdapter adapter;
    int selectedMenuPos = 0 ;
    JobScheduler jobScheduler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        String notifications = dataBaseHelper.getSettings("NEWS_NOTIFICATION");

        if (notifications.equals("yes")) {
            scheduleJob();
        } else if (notifications.equals("no")) {
            cancelJob();
        }
        // setup side menu
        setupSideMenu();
        // set the default fragment when activity launch
        setNewsFragment();

    }

    private void setupSideMenu() {
      DataBaseHelper dbHelper = new DataBaseHelper(this);
        String old_studies = dbHelper.getSettings("STUDIES") ;
        menuRv = findViewById(R.id.rv_side_menu);

        // get menu list item  will get data from a static data class

        menuItems = MenuUtil.getMenuList();
        if(old_studies.equals("hmmy_choice")) {
            int remove_courses_tab = 4;
            menuItems.remove(remove_courses_tab);  //index = 4 is the course (tab) item from MenuUtil
        }
        adapter = new MenuAdapter(menuItems,this);
        menuRv.setLayoutManager(new LinearLayoutManager(this));
        menuRv.setAdapter(adapter);

    }

    void setCommunityfragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new communityFragment()).commit();

    }

    void setProfessorsFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ProfessorsFragment()).commit();
    }


    void setNewsFragment() {

       getSupportFragmentManager().beginTransaction().replace(R.id.container,new NewsFragment()).commit();

    }
    void setCoursesFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CourseMatchingFragment()).commit();


    }
    void setMapfragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MapFragment()).commit();

    }
    void setCalendarfragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new CalendarFragment()).commit();

    }
    void setSecretaryfragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new SecretaryFragment()).commit();

    }
    private void setExtraStudiesfragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ExtraStudiesFragment()).commit();

    }

    void setAboutfragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new AboutFragment()).commit();

    }
    void setSettingsfragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new SettingsFragment()).commit();

    }

     void openQrPage() {
         Uri uri = Uri.parse("https://myseat.uop.gr/");
         Intent mySeat = new Intent(Intent.ACTION_VIEW,uri);
         startActivity(mySeat);
    }




    @Override
    public void onSideMenuItemClick(int i) {

        switch (menuItems.get(i).getCode()) {

            case MenuUtil.RSS_FRAGMENT_CODE: setNewsFragment();
                break;
            case MenuUtil.PROFESSORS_FRAGMENT_CODE: setProfessorsFragment();
                break;
            case MenuUtil.BOOK_FRAGMENT_CODE: setCoursesFragment();
                break;
            case MenuUtil.COMMUNITY_FRAGMENT_CODE: setCommunityfragment();
                break;
            case MenuUtil.MAP_FRAGMENT_CODE: setMapfragment();
                break;
            case MenuUtil.CALENDAR_FRAGMENT_CODE: setCalendarfragment();
                break;
            case MenuUtil.SECRETARY_FRAGMENT_CODE: setSecretaryfragment();
                break;
            case MenuUtil.EXTRASTUDIES_FRAGMENT_CODE: setExtraStudiesfragment();
                break;
            case MenuUtil.ABOUT_FRAGMENT_CODE: setAboutfragment();
                break;
            case MenuUtil.SETTINGS_FRAGMENT_CODE: setSettingsfragment();
                break;
            case MenuUtil.QR_SEAT: openQrPage();
                break;

            default:setNewsFragment();
        }// hightligh the selected menu item

        menuItems.get(selectedMenuPos).setSelected(false);
        menuItems.get(i).setSelected(true);
        selectedMenuPos = i ;
        adapter.notifyDataSetChanged();

    }




    public void scheduleJob() {
        ComponentName componentName = new ComponentName((this), AppJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setPeriodic(15*60*1000)
                .setRequiresCharging(false)
                .setPersisted(true)
                .build();
        JobScheduler scheduler = (JobScheduler) this.getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }
    public void cancelJob() {
        JobScheduler scheduler = (JobScheduler)  this.getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }
}