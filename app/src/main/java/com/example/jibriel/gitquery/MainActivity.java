package com.example.jibriel.gitquery;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jibriel.gitquery.models.RepoDetails;
import com.example.jibriel.gitquery.models.Repos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    HttpManager mHttpManager;

    @BindView(R.id.searchTxt)
    EditText searchTxt;

    @BindView(R.id.searchBtn)
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mHttpManager = HttpManager.Factory.create();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String repoName = searchTxt.getText().toString();
                if((!repoName.isEmpty())&&(repoName != null)){
                    searchAPI(repoName);
                    Log.e("Search", repoName);
                }else{
                    Log.e("Search", "Empty");
                }

                searchTxt.setText("");
            }
        });

    }


    Subscription subscription;
    public void searchAPI(String repoSearch){

        subscription = mHttpManager.searchRepo(repoSearch)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getSearchSubscriber());
    }


    private Subscriber<Repos> getSearchSubscriber() {
        Subscriber<Repos> mSubscribe = new Subscriber<Repos>() {
            @Override
            public void onCompleted() {
                Log.e("EXTRAS", "completed");
            }

            @Override
            public void onError(Throwable e) {
                // Show error
                e.printStackTrace();
                Log.e("EXTRAS", "error");

                Toast.makeText( getApplicationContext(), "Connection Failure!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(Repos response) {

                Log.e("EXTRAS", "DONE");

                //RepoSearchFragment.mRepoDetailsList = response.repos();
                viewSearchResults(response.repos());

                Toast.makeText( getApplicationContext(), "Success!",
                        Toast.LENGTH_LONG).show();
            }
        };

        return  mSubscribe;
    }


    private void viewSearchResults (List<RepoDetails> repoDetailsList) {

        RepoSearchFragment details = RepoSearchFragment.newInstance(mHttpManager);
        RepoSearchFragment.mRepoDetailsList = repoDetailsList;

        FragmentTransaction ft = getFragmentManager()
                .beginTransaction();
        ft.replace(R.id.fragFrame, details);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }

}
