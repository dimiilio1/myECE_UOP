package net.dimitrisiliou.myhmmy.map;

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

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MapViewHolder> implements Filterable {

    List<MapItem> mapList;
    MapFragment mContext;
    private List<MapItem> mapListFull;

    public MapAdapter(MapFragment context, List<MapItem> mdata) {
        this.mapList = mdata;
        mContext = context;
        mapListFull = new ArrayList<>(mapList);
    }
    private int lastPosition = -1;
    @Override
    public MapAdapter.MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_map,parent,false);

        return new MapAdapter.MapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapAdapter.MapViewHolder holder, int position) {
        lastPosition = position;

        holder.tv_name.setText(mapList.get(position).getName());
        holder.tv_desc.setText(mapList.get(position).getDesc());
        holder.tv_website.setOnClickListener(new MapAdapter.onWebsiteClick(holder.getAdapterPosition()));

    }

    class onWebsiteClick implements View.OnClickListener {
        int position;

        public onWebsiteClick(int adapterPosition) {
            this.position = adapterPosition;
        }

        @Override
        public void onClick(View view) {
            MapItem map = mapList.get(position);
            Uri uri = Uri.parse(map.getMap());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            mContext.startActivity(intent);
            Log.d("CREATION", String.valueOf(uri));
        }
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    public class MapViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_desc;
        ImageView img;
        Button tv_email, tv_website;

        public MapViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.map_item_name) ;
            tv_desc = itemView.findViewById(R.id.map_item_desc);
            tv_website = itemView.findViewById(R.id.map_website);
        }
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MapItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mapListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MapItem item : mapListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern) ) {
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
            mapList.clear();
            mapList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

