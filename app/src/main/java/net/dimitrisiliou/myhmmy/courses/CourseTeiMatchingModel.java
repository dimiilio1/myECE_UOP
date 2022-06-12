package net.dimitrisiliou.myhmmy.courses;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CourseTeiMatchingModel extends ArrayList<Parcelable> {
    private String id;
    private String title;
    private String courseMatch;

    public CourseTeiMatchingModel(String id, String title, String courseMatch) {
        this.id = id;
        this.title = title;
        this.courseMatch = courseMatch;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseMatch() {
        return courseMatch;
    }

    public void setCourseMatch(String courseMatch) {
        this.courseMatch = courseMatch;
    }

    @NonNull
    @Override
    public Stream<Parcelable> stream() {
        return null;
    }

    @NonNull
    @Override
    public Stream<Parcelable> parallelStream() {
        return null;
    }
}

