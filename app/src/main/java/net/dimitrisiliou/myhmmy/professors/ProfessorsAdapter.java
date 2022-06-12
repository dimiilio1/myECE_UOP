package net.dimitrisiliou.myhmmy.professors;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.dimitrisiliou.myhmmy.R;

import java.util.ArrayList;
import java.util.List;

public class ProfessorsAdapter extends RecyclerView.Adapter<ProfessorsAdapter.ProfessorsViewHolder>  implements Filterable {

    List<ProfessorsItem> profList;
    ProfessorsFragment mContext;
    private List<ProfessorsItem> profListFull;


    public ProfessorsAdapter(ProfessorsFragment context, List<ProfessorsItem> mdata) {
        this.profList = mdata;
        mContext = context;
        profListFull = new ArrayList<>(profList);
    }
    private int lastPosition = -1;

    @Override
    public ProfessorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_professors,parent,false);

        return new ProfessorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorsViewHolder holder, int position) {
        lastPosition = position;

        holder.tv_name.setText(profList.get(position).getName());
        holder.tv_desc.setText(profList.get(position).getDesc());
        holder.img.setImageResource(profList.get(position).getImg());
        holder.tv_email.setOnClickListener(new onEmailClick(holder.getAdapterPosition()));
        holder.tv_website.setOnClickListener(new onWebsiteClick(holder.getAdapterPosition()));

    }

    class onWebsiteClick implements View.OnClickListener {
        int position;

        public onWebsiteClick(int adapterPosition) {
            this.position = adapterPosition;
        }

        @Override
        public void onClick(View view) {
            ProfessorsItem map = profList.get(position);
            Uri uri = Uri.parse(map.getwebsite());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            mContext.startActivity(intent);
            Log.d("CREATION", String.valueOf(uri));
        }
    }

    class onEmailClick implements View.OnClickListener {
        int position;

        public onEmailClick(int adapterPosition) {
            this.position = adapterPosition;
        }

        @Override
        public void onClick(View view) {
            ProfessorsItem map = profList.get(position);
            Uri uri = Uri.parse(map.getEmail());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            mContext.startActivity(intent);
            Log.d("CREATION", String.valueOf(uri));

        }
    }

    public class ProfessorsViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_desc;
        ImageView img;
        Button tv_email, tv_website;

        public ProfessorsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.professors_item_name) ;
            tv_desc = itemView.findViewById(R.id.professors_item_desc);
            img = itemView.findViewById(R.id.professors_item_img);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_website = itemView.findViewById(R.id.tv_website);
        }
    }
    @Override
    public int getItemCount() {
        return profList.size();
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProfessorsItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(profListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ProfessorsItem item : profListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
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
            profList.clear();
            profList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
