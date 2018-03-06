package com.example.jibriel.gitquery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jibriel.gitquery.models.IssueDetails;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jibriel on 06/03/2018.
 */

public class IssueDetailActivity extends AppCompatActivity {

    public static IssueDetails issueDetails;

    @Nullable
    @BindView(R.id.issue_title)
    TextView issueTitle;

    @BindView(R.id.issue_url)
    TextView issueUrl;

    @BindView(R.id.issue_created_at)
    TextView issueCreated;

    @BindView(R.id.issue_updated_at)
    TextView issueUpdated;

    @BindView(R.id.issue_body)
    TextView issueBody;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue_details);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if(!bundle.isEmpty()){

            issueTitle.setText(bundle.get("title").toString());

            issueUrl.setText(bundle.get("url").toString());

            issueCreated.setText(bundle.get("created_at").toString());

            issueUpdated.setText(bundle.get("updated_at").toString());

            issueBody.setText(bundle.get("body").toString());
        }

    }
}
