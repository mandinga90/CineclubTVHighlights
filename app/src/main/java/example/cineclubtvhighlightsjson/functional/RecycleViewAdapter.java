package example.cineclubtvhighlightsjson.functional;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import example.cineclubtvhighlightsjson.R;
import example.cineclubtvhighlightsjson.activities.DetailsActivity;
import example.cineclubtvhighlightsjson.activities.MainActivity;
import example.cineclubtvhighlightsjson.entities.TvHighlight;

/**
 * Created by mlu on 06.05.2017.
 * sources:
 * https://developer.android.com/guide/topics/ui/layout/recyclerview.html
 * http://www.androidhive.info/2016/01/android-working-with-recycler-view/
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleViewAdapter(List<TvHighlight> tvHighlights) {
        for(int i = 0; i < tvHighlights.size(); i++){
            TvHighlight currentTvHighlight = tvHighlights.get(i);
            if(DateTimeHelper.isToday(currentTvHighlight.getDateTime())){
                this.tvHighlights.add(currentTvHighlight);
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tvHighlights.size();
    }

    private List<TvHighlight> tvHighlights = new LinkedList<>();
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView originalTitle;
        public TextView time;
        public ImageView tvChannelIcon;
        public TextView advertisingInMinutes;
        public ImageView cover;

        public TextView tvChannelName;

        private TvHighlight tvHighlight;

        public ViewHolder(View v) {
            super(v);
            setupViews(v);
            v.setOnClickListener(this);
        }

        private void setupViews(View v) {
            title = (TextView) v.findViewById(R.id.tv_highlight_title);
            originalTitle = (TextView) v.findViewById(R.id.tv_highlight_original_title);
            time = (TextView) v.findViewById(R.id.tv_highlight_time);
            tvChannelIcon = (ImageView) v.findViewById(R.id.tv_highlight_channel_icon);
            advertisingInMinutes = (TextView) v.findViewById(R.id.tv_highlight_advertising_in_minutes);
            cover = (ImageView) v.findViewById(R.id.tv_highlight_cover);
            tvChannelName = (TextView) v.findViewById(R.id.tv_highlight_channel_name);
        }
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            intent.putExtra(v.getContext().getString(R.string.parcelableExtra), tvHighlight);
            ContextCompat.startActivity(v.getContext(), intent, null);

        }

        public void setTvHighlight(TvHighlight tvHighlight) {
            this.tvHighlight = tvHighlight;
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View listItemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.tv_highlight_list_item, parent, false);

        ViewHolder vh = new ViewHolder(listItemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TvHighlight currentTvHighlight = tvHighlights.get(position);
        holder.setTvHighlight( currentTvHighlight );

        holder.title.setText( currentTvHighlight.getTitle() );

        // original title
        String originalTitleToBeDisplayed = currentTvHighlight.getOriginalTitleToBeDisplayed();
        if( ! originalTitleToBeDisplayed.isEmpty() ){
            holder.originalTitle.setText(originalTitleToBeDisplayed);
            holder.originalTitle.setVisibility(View.VISIBLE);
        }
        else{
            holder.originalTitle.setVisibility(View.GONE);
        }

        // dateTime
        holder.time.setText( currentTvHighlight.getDateTimeString() );

        // tv channel icon
        int tvChannelIcon = currentTvHighlight.getTvChannelIcon();
        if( tvChannelIcon > 0 ){
            holder.tvChannelIcon.setImageResource(tvChannelIcon);
            holder.tvChannelName.setVisibility(View.GONE);
        }
        else{
            holder.tvChannelName.setText(currentTvHighlight.getTvChannelName());
        }

        // advertising in minutes
        holder.advertisingInMinutes.setText(currentTvHighlight.getAdvertisingInMinutesText());

        // cover
        currentTvHighlight.setCoverImage(holder.cover);

    }
}
