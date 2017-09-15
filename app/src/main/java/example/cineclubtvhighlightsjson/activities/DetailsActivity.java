package example.cineclubtvhighlightsjson.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;

import example.cineclubtvhighlightsjson.R;
import example.cineclubtvhighlightsjson.databinding.TvHighlightDetailsBinding;
import example.cineclubtvhighlightsjson.entities.TvHighlight;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TvHighlight tvHighlightForDetails = getIntent().getParcelableExtra(getResources().getString(R.string.parcelableExtra));
        TvHighlightDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.tv_highlight_details);
        binding.setTvHighlight(tvHighlightForDetails);

        // cover image
        tvHighlightForDetails.setCoverImage(binding.tvHighlightDetailsCover);

        // tv channel
        if (tvHighlightForDetails.getTvChannelIcon() > 0){
            binding.tvHighlightDetailsChannelIcon.setImageResource(tvHighlightForDetails.getTvChannelIcon());
            binding.tvHighlightDetailsChannelName.setVisibility(View.GONE);
        }
        else{
            binding.tvHighlightDetailsChannelName.setText(tvHighlightForDetails.getTvChannelName());
        }

        // original title
        String originalTitleToBeDisplayed = tvHighlightForDetails.getOriginalTitleToBeDisplayed();
        if( ! originalTitleToBeDisplayed.isEmpty() ){
            binding.tvHighlightDetailsOriginalTitle.setText(originalTitleToBeDisplayed);
            binding.tvHighlightDetailsOriginalTitle.setVisibility(View.VISIBLE);
        }
        else{
            binding.tvHighlightDetailsOriginalTitle.setVisibility(View.GONE);
        }

        // dateTime
        binding.tvHighlightDetailsTime.setText( tvHighlightForDetails.getDateTimeString() );

        // advertising in minutes
        binding.tvHighlightDetailsAdvertisingInMinutes.setText(tvHighlightForDetails.getAdvertisingInMinutesText());

        // description
        binding.tvHighlightDetailsDescription.setMovementMethod(new ScrollingMovementMethod());

        // link
        binding.tvHighlightDetailsLink.setLinksClickable(true);
        binding.tvHighlightDetailsLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
