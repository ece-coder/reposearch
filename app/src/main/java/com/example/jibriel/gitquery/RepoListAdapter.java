package com.example.jibriel.gitquery;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jibriel.gitquery.models.IssueDetails;
import com.example.jibriel.gitquery.models.Issues;
import com.example.jibriel.gitquery.models.RepoDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jibriel on 06/03/2018.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.MyRepoListAdapterViewHolder> {

    private List<RepoDetails> repoDetails;
    private HttpManager mHttpmanager;
    private Activity mActivity;

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

        @BindView(R.id.issueCount)
        TextView clickIssue;

        public MyRepoListAdapterViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);

        }

    }

    public RepoListAdapter (List<RepoDetails> repoDetailsList, HttpManager httpManager, Activity activity){
        repoDetails =  repoDetailsList;
        mHttpmanager = httpManager;
        mActivity = activity;


    }

    @Override
    public void onBindViewHolder(MyRepoListAdapterViewHolder holder, final int position) {

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

        if(mRepoDetails.numOpenIssues() > 0){

            holder.clickIssue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("EXTRAS", "issues: " + repoDetails.get(position).numOpenIssues());
                    searchOpenIssues(repoDetails.get(position).fullName());
                }
            });

        }

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


    Subscription subscription;
    public void searchOpenIssues(String searchString){


        //repo:twbs/bootstrap+is:closed
        searchString = "repo:" + searchString;

        subscription = mHttpmanager.searchIssues(searchString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getSearchSubscriber());
    }


    private Subscriber<Issues> getSearchSubscriber() {
        Subscriber<Issues> mSubscribe = new Subscriber<Issues>() {
            @Override
            public void onCompleted() {
                Log.e("EXTRAS", "completed");
            }

            @Override
            public void onError(Throwable e) {
                // Show error
                e.printStackTrace();
                Log.e("EXTRAS", "error");

                Toast.makeText( mActivity.getApplicationContext(), "Connection Failure!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(Issues response) {

                Log.e("EXTRAS", "DONE");

                //RepoSearchFragment.mRepoDetailsList = response.repos();
                viewSearchResults(response.issues());

                Toast.makeText( mActivity.getApplicationContext(), "Success!",
                        Toast.LENGTH_LONG).show();
            }
        };

        return  mSubscribe;
    }


    private void viewSearchResults (List<IssueDetails> issueDetails) {

        IssueListFragment details = IssueListFragment.newInstance();
        IssueListFragment.mIssueDetailslist = issueDetails;

        // Execute a transaction, replacing any existing fragment
        // with this one inside the frame.
        FragmentTransaction ft = mActivity.getFragmentManager()
                .beginTransaction();
        ft.replace(R.id.fragFrame, details);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }


}
