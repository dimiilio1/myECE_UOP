
package net.dimitrisiliou.myhmmy.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import net.dimitrisiliou.myhmmy.R;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<HashMap<String, String>> mDataset = new ArrayList<>();
    private Context mContext;
    private int lastPosition = -1;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title, tv_date, tv_newannounce ;
        public ImageView iv_share;
        public CardView cardView;


        public ViewHolder(View v) {
            super(v);
            tv_title = (TextView) v.findViewById(R.id.title);
            tv_date = (TextView) v.findViewById(R.id.date);
            iv_share = (ImageView) v.findViewById(R.id.share);
            cardView = (CardView) v.findViewById(R.id.card_view);
            tv_newannounce = (TextView) v.findViewById(R.id.newannounce);

        }
    }

    public NewsAdapter(Context context, ArrayList<HashMap<String, String>> dataset) {
        mContext = context;
        mDataset = dataset;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // position should never be final
        lastPosition = position;

        HashMap<String, String> map = mDataset.get(position);
        holder.tv_title.setText(map.get("title"));
        String fromHtml = Html.fromHtml(map.get("content:encoded")).toString();
        String lastStr = fromHtml.substring(2, fromHtml.length() - 1);
        holder.tv_date.setText(map.get("pubDate"));
        holder.cardView.setOnClickListener(new onCardClick(holder.getAdapterPosition()));
        holder.iv_share.setOnClickListener(new onPostShare(holder.getAdapterPosition()));

//shows the "NEW" TAG
        String dateString = (String) holder.tv_date.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy ", Locale.UK);
        Date date;
        try {
            date = sdf.parse(dateString);
            assert date != null;
            int dayDiff = daysBetween(date, new Date()) - 1;
            if ( dayDiff <=4 ) { // show new tag for articles that were published up to 4 days ago.
                holder.tv_newannounce.setText("ΝΕΟ!");
            } else {
                holder.tv_newannounce.setText("");
            }

        } catch (ParseException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String errors = sw.toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@dimitrisiliou.atlassian.net"});
                email.putExtra(Intent.EXTRA_SUBJECT, "[myhmmy] ParseException Error Code 004");
                email.putExtra(Intent.EXTRA_TEXT, errors);
                email.setType("message/rfc822");
                mContext.startActivity(email);
            e.printStackTrace();
        }
    }

    private static int daysBetween(Date startDate, Date endDate) {
        int daysBetween = 0;
        while (startDate.before(endDate)) {
            startDate.setTime(startDate.getTime() + 86400000);
            daysBetween++;
        }
        return daysBetween;
    }

    class onCardClick implements View.OnClickListener {
        int position;

        public onCardClick(int adapterPosition) {
            this.position = adapterPosition;
        }

        @Override
        public void onClick(View view) {
            HashMap<String, String> map = mDataset.get(position);
            Log.d("Position", Objects.requireNonNull(map.get("link")));
            Uri uri = Uri.parse(map.get("link"));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);
        }
    }

    class onPostShare implements View.OnClickListener {
        int position;

        public onPostShare(int adapterPosition) {
            this.position = adapterPosition;
        }

        @Override
        public void onClick(View view) {
            HashMap<String, String> map = mDataset.get(position);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    map.get("title") + " \n" + map.get("link"));
            sendIntent.setType("text/plain");
            mContext.startActivity(Intent.createChooser(sendIntent, "Share this post"));
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}