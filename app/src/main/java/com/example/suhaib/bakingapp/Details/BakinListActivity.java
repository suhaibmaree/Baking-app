package com.example.suhaib.bakingapp.Details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suhaib.bakingapp.JsonFiles.Baking;
import com.example.suhaib.bakingapp.JsonFiles.Step;
import com.example.suhaib.bakingapp.R;

import com.example.suhaib.bakingapp.Details.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BakinDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BakinListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    Baking baking;
    RecyclerView recyclerView;
    SimpleItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakin_list);


        TextView bakingName = findViewById(R.id.baking_name);
        TextView ingredient = findViewById(R.id.ingredient);
        String ingredientText="";
        Intent intent = getIntent();
        if(intent.hasExtra("baking")){
            baking = intent.getParcelableExtra("baking");
            //Log.d("Steps size"+baking.getSteps().size(),"Check");
            bakingName.setText(baking.getName());
            for (int i = 0; i<baking.getIngredients().size();i++){
                ingredientText = ingredientText+baking.getIngredients().get(i).getIngredient()+"\n";
            }
            ingredient.setText(ingredientText);

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.bakin_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.bakin_list);
        adapter = new SimpleItemRecyclerViewAdapter(this,baking.getSteps(),mTwoPane);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);


    }// end on Creat

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final BakinListActivity mParentActivity;
        private final boolean mTwoPane;
        private List<Step> mValues;


        SimpleItemRecyclerViewAdapter(BakinListActivity parent, List<Step> items, boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bakin_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mStep.setText(mValues.get(position).getShortDescription());
        }

        @Override
        public int getItemCount() {
            if (mValues!=null){
                Log.d("Steps size"+mValues.size(),"Check");
            return mValues.size();
            }
            else {
               // Log.d("Steps size"+mValues.size(),"Check");
                return 0;
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            final TextView mStep;

            ViewHolder(View view) {
                super(view);
                mStep = view.findViewById(R.id.Step);
            }

            @Override
            public void onClick(View v) {
                int index = getAdapterPosition();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable("step",mValues.get(index));
                    BakinDetailFragment fragment = new BakinDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bakin_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, BakinDetailActivity.class);
                    intent.putExtra("step",mValues.get(index));
                    context.startActivity(intent);
                }
            }
        }
    }
}
