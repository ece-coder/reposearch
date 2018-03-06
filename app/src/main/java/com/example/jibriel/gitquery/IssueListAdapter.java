package com.example.jibriel.gitquery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    public class MyIssueListAdapterViewHolder extends  RecyclerView.ViewHolder{


        @BindView(R.id.issueTitle)
        TextView issueTitle;


        public MyIssueListAdapterViewHolder (View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public IssueListAdapter (List<IssueDetails> issueDetails){
        this.issueDetails = issueDetails;
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
    public void onBindViewHolder(MyIssueListAdapterViewHolder holder, int position) {

        IssueDetails mIssueDetails = issueDetails.get(position);

        if(mIssueDetails.title() != null) {
            holder.issueTitle.setText(mIssueDetails.title());
        }else{
            holder.issueTitle.setText("[No Title]");
        }

    }
}
