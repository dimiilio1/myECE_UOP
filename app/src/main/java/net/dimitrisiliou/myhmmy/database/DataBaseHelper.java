package net.dimitrisiliou.myhmmy.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.dimitrisiliou.myhmmy.courses.CourseHmmyMatchingModel;
import net.dimitrisiliou.myhmmy.courses.CourseTeiMatchingModel;
import net.dimitrisiliou.myhmmy.news.NewsModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String RSS_ID_LINK = "RSS_ID_LINK";
    public static final String RSS_TABLE = "RSS_TABLE";
    public static final String RSS_TITLE = "RSS_TITLE";
    public static final String RSS_CONTEXT = "RSS_CONTEXT";
    public static final String RSS_DATE = "RSS_DATE";
    public static final String RSS_COUNTER = "RSS_COUNTER";
    public static final String HMMY_COURSES_TABLE = "HMMY_COURSES_TABLE";
    public static final String ΤΕΙ_COURSE_TITLE = "COURSE_TITLE";
    public static final String FEL_TEI_COURSES_TABLE = "FEL_TEI_COURSES_TABLE";
    public static final String CIED_TEI_COURSES_TABLE = "CIED_TEI_COURSES_TABLE";

    public static final String SETTINGS_TABLE = "SETTINGS_TABLE";
    public static final String SETTINGS_ID = "SETTINGS_ID";
    public static final String SETTINGS_CHOICE = "SETTINGS_CHOICE";


    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "ymmh.db";
    public final static String DATABASE_PATH = "/data/data/net.dimitrisiliou.myhmmy/databases/";
    public static final int DATABASE_VERSION = 26;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;

    }
    //Create a empty database on the system
    public void createDatabase() throws IOException
    {

        boolean dbExist = checkDataBase();

        if(dbExist)
        {
            Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }
        else
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }

    }
    //Check database already exist or not
    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }
    //Copies the database from the local assets-folder to the just created empty database in the system folder
    private void copyDataBase() throws IOException
    {

        InputStream mInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    //delete database
    public void db_delete()
    {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }
    //Open database
    public void openDatabase() throws SQLException
    {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }



    //this is called the first time a database is accessed.There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createRssTable = " create table " + RSS_TABLE
                + " (" + RSS_ID_LINK + " text primary key, "
                + RSS_TITLE + " text not null, " + RSS_DATE
                + " date, " + RSS_CONTEXT + " text not null, "
                + RSS_COUNTER + " int not null "
                +");";

        db.execSQL(createRssTable);

    }

    // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ RSS_TABLE);
        onCreate(db);

    }

    public String getSettings(String id) {

        String queryString = "SELECT SETTINGS_CHOICE FROM SETTINGS_TABLE WHERE " + SETTINGS_ID  +  " = '" + id + "'" ;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        String settingChoice = null;

        if(cursor.moveToFirst()) {
            settingChoice = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return  settingChoice;
    }

    public List<CourseTeiMatchingModel> getAllCiedCourses() {

        List<CourseTeiMatchingModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CIED_TEI_COURSES_TABLE;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                String courseID = cursor.getString(0);
                String courseTitle = cursor.getString(1);
                String courseMatch = cursor.getString(2);

                CourseTeiMatchingModel newCourse = new CourseTeiMatchingModel(courseID,courseTitle,courseMatch);
                returnList.add(newCourse);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CourseTeiMatchingModel> getAllFelCourses() {

        List<CourseTeiMatchingModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + FEL_TEI_COURSES_TABLE;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery(queryString, null);

        if(cursor1.moveToFirst()) {
            do {
                String courseID = cursor1.getString(0);
                String courseTitle = cursor1.getString(1);
                String courseMatch = cursor1.getString(2);
                CourseTeiMatchingModel newCourse = new CourseTeiMatchingModel(courseID,courseTitle,courseMatch);
                returnList.add(newCourse);
            }while(cursor1.moveToNext());
        }

        cursor1.close();
        db.close();
        return returnList;
    }

    public List<CourseHmmyMatchingModel> getHmmyCourse(String hmmyid) {

        List<CourseHmmyMatchingModel> returnList = new ArrayList<>();

        if(hmmyid == null || hmmyid == " " || hmmyid == "" || hmmyid.isEmpty()) {

            String courseID = "-----";
            String courseTitle = " Χωρίς αντιστοίχιση";
            String courseSemester = "-----";
            String courseHours = "-----";
            String courseLab = "-----";
            String courseTutor ="-----";

            CourseHmmyMatchingModel newCourse = new CourseHmmyMatchingModel(courseID,courseTitle,courseSemester,courseHours,courseLab,courseTutor);
            returnList.add(newCourse);

        }
        else{
            String queryString = "SELECT * FROM " + HMMY_COURSES_TABLE +" WHERE ID= " +"\"" + hmmyid+"\"" ;
            SQLiteDatabase db =this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);

            if(cursor.moveToFirst()) {
                do {
                    String courseID = cursor.getString(0);
                    String courseTitle = cursor.getString(1);
                    String courseSemester = cursor.getString(2);
                    String courseHours = cursor.getString(3);
                    String courseLab = cursor.getString(4);
                    String courseTutor = cursor.getString(5);

                    CourseHmmyMatchingModel newCourse = new CourseHmmyMatchingModel(courseID,courseTitle,courseSemester,courseHours,courseLab,courseTutor);
                    returnList.add(newCourse);
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return returnList;

        }return returnList;
    }

    public boolean changeSetting(String id, String choice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SETTINGS_CHOICE, choice);
        db.update(SETTINGS_TABLE, cv,"SETTINGS_ID = '" + id + "'" ,null);
        db.close();
        return true;
    }

    public boolean addOne(NewsModel rssModel) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(RSS_ID_LINK, rssModel.getLink_id());
            cv.put(RSS_TITLE, rssModel.getTitle());
            cv.put(RSS_DATE, String.valueOf(rssModel.getPubDate()));
            cv.put(RSS_CONTEXT, rssModel.getContext());
            cv.put(RSS_COUNTER, rssModel.getCounter());

            db.insert(RSS_TABLE, null, cv);
            db.close();
            return true;
        }catch (Exception x){ return true;}

    }

    public ArrayList<HashMap<String, String>> getAllRss() {
        //get data from the database

        ArrayList<HashMap<String, String>> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + RSS_TABLE + " ORDER BY "+ RSS_COUNTER+ " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new rss objrcts and put them into the return list
            do {
                HashMap<String, String> currentMap = new HashMap<>();
                currentMap.put("title", cursor.getString(1));
                currentMap.put("link", cursor.getString(0));
                currentMap.put("pubDate", cursor.getString(2));
                currentMap.put("content:encoded", cursor.getString(3));
                currentMap.put("counter", cursor.getString(4));

                returnList.add(currentMap);


            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;

    }

}