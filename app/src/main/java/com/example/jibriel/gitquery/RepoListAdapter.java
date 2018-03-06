package com.example.jibriel.gitquery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jibriel.gitquery.models.RepoDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jibriel on 06/03/2018.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.MyRepoListAdapterViewHolder> {

    private List<RepoDetails> repoDetails;

    public class MyRepoListAdapterViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.urlVal)
        TextView urlVal;

        @BindView(R.id.starVal)
        TextView starVal;

        @BindView(R.id.forkVal)
        TextView forkVal;

        @BindView(R.id.issueVal)
        TextView issueVal;

        public MyRepoListAdapterViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);

        }

    }

    public RepoListAdapter (List<RepoDetails> repoDetailsList){
        repoDetails =  repoDetailsList;

    }

    @Override
    public void onBindViewHolder(MyRepoListAdapterViewHolder holder, int position) {

        RepoDetails mRepoDetails = repoDetails.get(position);
        if(mRepoDetails.description() != null) {
            holder.description.setText(mRepoDetails.description());
        }else{
            holder.description.setText("[No Description]");
        }
        holder.urlVal.setText(mRepoDetails.url());
        holder.starVal.setText(Integer.toString(mRepoDetails.numStars()));
        holder.forkVal.setText(Integer.toString(mRepoDetails.numForks()));
        holder.issueVal.setText(Integer.toString(mRepoDetails.numOpenIssues()));


    }

    @Override
    public MyRepoListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_row, parent, false);

        return new MyRepoListAdapterViewHolder(itemView);
    }

    @Override
    public int getItemCount() {

        int ret = 0;

        if(repoDetails != null){
            ret = repoDetails.size();
        }

        return ret;
    }


}
