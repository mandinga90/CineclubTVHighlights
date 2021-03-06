package cineclub.tvhighlights.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cineclub.tvhighlights.R;
import cineclub.tvhighlights.entities.TvHighlight;
import cineclub.tvhighlights.functional.Consumer;
import cineclub.tvhighlights.functional.RecycleViewAdapter;
import cineclub.tvhighlights.ui.fragments.RetainFragment;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNetworkFragment();
    }

    private void setupNetworkFragment() {

        getRetainFragment().getTVHighlights(new Consumer<List<TvHighlight>>() {
            @Override
            public void apply(List<TvHighlight> tvHighlights) {
                setupRecyclerView(tvHighlights);
            }
        });
    }

    private RetainFragment getRetainFragment() {
        RetainFragment retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.NETWORK_FRAGMENT_TAG));
        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, getString(R.string.NETWORK_FRAGMENT_TAG)).commit();
        }
        return retainFragment;
    }

    private void setupRecyclerView(List<TvHighlight> tvHighlights){

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecycleViewAdapter(tvHighlights);
        mRecyclerView.setAdapter(mAdapter);

        if (mAdapter.getItemCount() > 0){
            MainActivity.this.findViewById(R.id.img_no_entries).setVisibility(View.GONE);
            MainActivity.this.findViewById(R.id.txt_no_entries).setVisibility(View.GONE);
        }

    }

}
