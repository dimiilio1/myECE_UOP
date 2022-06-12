package net.dimitrisiliou.myhmmy.courses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.dimitrisiliou.myhmmy.R;

import java.util.ArrayList;
import java.util.List;

public class CourseMatchingAdapter extends RecyclerView.Adapter<CourseMatchingAdapter.CourseMatchingViewHolder> implements Filterable {
    private List<CourseTeiMatchingModel> courseList;
    private List<CourseTeiMatchingModel> courseListFull;
    private OnNoteListener mOnNoteListener;


    public class CourseMatchingViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView textView1;
        TextView textView2;
        OnNoteListener onNoteListener;

        CourseMatchingViewHolder(View itemView, OnNoteListener onNoteListener) {

            super(itemView);
            textView1 = itemView.findViewById(R.id.text_view1);
            textView2 = itemView.findViewById(R.id.text_view2);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public CourseMatchingAdapter(List<CourseTeiMatchingModel> courseList, OnNoteListener onNoteListener) {
        this.courseList = courseList;
        courseListFull = new ArrayList<>(courseList);
        this.mOnNoteListener = onNoteListener;
    }
    @NonNull
    @Override
    public CourseMatchingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_matching_item,
                parent, false);
        return new CourseMatchingViewHolder(v,mOnNoteListener);
    }
    @Override
    public void onBindViewHolder(@NonNull CourseMatchingViewHolder holder, int position) {
        CourseTeiMatchingModel currentItem = courseList.get(position);
        holder.textView1.setText(currentItem.getId());
        holder.textView2.setText(currentItem.getTitle());

    }
    @Override
    public int getItemCount() {
        return courseList.size();
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CourseTeiMatchingModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(courseListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CourseTeiMatchingModel item : courseListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            courseList.clear();
            courseList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public interface OnNoteListener {
        void onNoteClick(int position);

    }
}
