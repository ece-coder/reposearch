package com.example.jibriel.gitquery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jibriel.gitquery.models.IssueDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jibriel on 06/03/2018.
 */

public class IssueListAdapter extends RecyclerView.Adapter<IssueListAdapter.MyIssueListAdapterViewHolder> {

    private List<IssueDetails> issueDetails;
    private Activity activity;


    public class MyIssueListAdapterViewHolder extends  RecyclerView.ViewHolder{


        @BindView(R.id.issueTitle)
        TextView issueTitle;

        @BindView(R.id.issue_list_row)
        LinearLayout issueListRow;


        public MyIssueListAdapterViewHolder (View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public IssueListAdapter (List<IssueDetails> issueDetails, Activity activity){
        this.issueDetails = issueDetails;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        int ret = 0;

        if(issueDetails != null){
            ret = issueDetails.size();
        }

        return ret;
    }

    @Override
    public MyIssueListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.issue_list_row, parent, false);
        return new MyIssueListAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyIssueListAdapterViewHolder holder, final int position) {

        IssueDetails mIssueDetails = issueDetails.get(position);

        if(mIssueDetails.title() != null) {
            holder.issueTitle.setText(mIssueDetails.title());
        }else{
            holder.issueTitle.setText("[No Title]");
        }

        holder.issueListRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle extras = new Bundle();
                extras.putString("title", issueDetails.get(position).title());
                extras.putString("url", issueDetails.get(position).url());
                extras.putString("created_at", issueDetails.get(position).created());
                extras.putString("updated_at", issueDetails.get(position).updated());
                extras.putString("body", issueDetails.get(position).body());


                openIssueDetailsPage(extras);

                Log.e("EXTRAS", issueDetails.get(position).title());
            }
        });
    }

    private void openIssueDetailsPage (Bundle bundle){

        Intent intent = new Intent(activity,IssueDetailActivity.class);
        intent.putExtras(bundle);

        activity.startActivity(intent);

    }
}
