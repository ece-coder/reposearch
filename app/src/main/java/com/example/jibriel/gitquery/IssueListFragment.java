package com.example.jibriel.gitquery;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jibriel.gitquery.models.IssueDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jibriel on 06/03/2018.
 */

public class IssueListFragment extends Fragment {


    @BindView(R.id.issue_list_view)
    RecyclerView issueListView;

    private IssueListAdapter mIssueListAdapter;

    public static List<IssueDetails> mIssueDetailslist;

    public static IssueListFragment newInstance(){
        IssueListFragment fragment = new IssueListFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.issue_list_fragment, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIssueListAdapter = new IssueListAdapter(mIssueDetailslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        issueListView.setLayoutManager(mLayoutManager);
        issueListView.setAdapter(mIssueListAdapter);


    }
}
