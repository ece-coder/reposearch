package com.example.jibriel.gitquery;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jibriel.gitquery.models.RepoDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jibriel on 06/03/2018.
 */

public class RepoSearchFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private RepoListAdapter mRepoListAdapter;

    public static List<RepoDetails> mRepoDetailsList;

    private static HttpManager mHttpManager;


    public static RepoSearchFragment newInstance(HttpManager httpManager) {
        RepoSearchFragment myFragment = new RepoSearchFragment();

        mHttpManager = httpManager;

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.repo_search_fragment, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRepoListAdapter = new RepoListAdapter(mRepoDetailsList, mHttpManager, getActivity());
        RecyclerView.LayoutManager mLayoutMnager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutMnager);
        recyclerView.setAdapter(mRepoListAdapter);
    }
}
