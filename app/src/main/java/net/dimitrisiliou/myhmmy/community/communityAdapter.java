package net.dimitrisiliou.myhmmy.community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.dimitrisiliou.myhmmy.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class communityAdapter extends RecyclerView.Adapter<communityAdapter.CommunityViewHolder> {

    List<communityItem> mdata;
    communityCallback listener;

    public communityAdapter(List<communityItem> mdata, communityCallback listener) {
        this.mdata = mdata;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_community,parent,false) ;

        return new CommunityViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext()).load(mdata.get(position).getImage()).into(holder.imgport);



    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class CommunityViewHolder extends RecyclerView.ViewHolder {

        ImageView imgport;


        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);

            imgport = itemView.findViewById(R.id.item_community_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCommunityItemClick(getAdapterPosition());
                }
            });

        }
    }
}
