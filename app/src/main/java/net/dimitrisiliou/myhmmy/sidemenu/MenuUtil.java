package net.dimitrisiliou.myhmmy.sidemenu;

import net.dimitrisiliou.myhmmy.R;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil  {

    public static final int RSS_FRAGMENT_CODE = 0;
    public static final int BOOK_FRAGMENT_CODE = 1;
    public static final int COMMUNITY_FRAGMENT_CODE = 2;
    public static final int PROFESSORS_FRAGMENT_CODE = 3;
    public static final int MAP_FRAGMENT_CODE = 4;
    public static final int CALENDAR_FRAGMENT_CODE = 5;
    public static final int SECRETARY_FRAGMENT_CODE = 6;
    public static final int EXTRASTUDIES_FRAGMENT_CODE =7;
    public static final int ABOUT_FRAGMENT_CODE = 8;
    public static final int SETTINGS_FRAGMENT_CODE = 9;
    public static final int QR_SEAT = 10;


    public static List<MenuItem> getMenuList() {
        List<MenuItem> list = new ArrayList<>();
        // first menu item is selected
        list.add(new MenuItem(R.drawable.ic_rss, RSS_FRAGMENT_CODE,true));
        list.add(new MenuItem(R.drawable.ic_baseline_qr_code_scanner_24,QR_SEAT,false));
        list.add(new MenuItem(R.drawable.ic_professors,PROFESSORS_FRAGMENT_CODE,false));
        list.add(new MenuItem(R.drawable.ic_community, COMMUNITY_FRAGMENT_CODE,false));
        list.add(new MenuItem(R.drawable.ic_book, BOOK_FRAGMENT_CODE,false));
        list.add(new MenuItem(R.drawable.ic_map, MAP_FRAGMENT_CODE,false));
        list.add(new MenuItem(R.drawable.ic_calendar, CALENDAR_FRAGMENT_CODE,false));
        list.add(new MenuItem(R.drawable.ic_secretary, SECRETARY_FRAGMENT_CODE,false));
        list.add(new MenuItem(R.drawable.ic_extrastudies,EXTRASTUDIES_FRAGMENT_CODE,false));
        list.add(new MenuItem(R.drawable.ic_info, ABOUT_FRAGMENT_CODE,false));
        list.add(new MenuItem(R.drawable.ic_settings, SETTINGS_FRAGMENT_CODE,false));

        return list;
    }
}
