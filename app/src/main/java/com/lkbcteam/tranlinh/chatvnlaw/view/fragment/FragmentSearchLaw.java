package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.SearchLawAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SearchLawResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.SearchLawPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw.KEYWORD;
import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw.RG_MODE;
import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw.RG_MODE_VALUE;
import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw.RG_SORT_MODE;

/**
 * Created by tranlinh on 26/04/2018.
 */

public class FragmentSearchLaw  extends BaseFragment implements SearchLawAdapter.SearchLawListener,SearchLawPresenter.SearchLawListener,View.OnClickListener, DialogSearchLaw.DialogSearchLawListener{

    private ImageButton ibtnSearch;
    private ImageButton ibtnBack;

    private RecyclerView rvLawResult;
    private SearchLawAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<SearchLawResponse.Article> articles;

    private SearchLawPresenter presenter;

    public static FragmentSearchLaw newInstance() {

        Bundle args = new Bundle();

        FragmentSearchLaw fragment = new FragmentSearchLaw();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_law,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        articles = new ArrayList<>();

        ibtnSearch = (ImageButton)view.findViewById(R.id.ibtn_search);
        ibtnBack = (ImageButton)view.findViewById(R.id.ibtn_home_menu);
        ibtnSearch.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
        layoutManager = new GridLayoutManager(getContext(), 1);
        rvLawResult = (RecyclerView)view.findViewById(R.id.rv_law_result);
        adapter = new SearchLawAdapter();
        adapter.setArticles(articles);
        adapter.setCallback(this);
        rvLawResult.setLayoutManager(layoutManager);
        rvLawResult.setAdapter(adapter);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        presenter = new SearchLawPresenter();
        presenter.setArticles(articles);
        presenter.setCallback(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_search:
                // show dialog
                DialogSearchLaw dialogSearchLaw = new DialogSearchLaw();
                Bundle bundle = new Bundle();
                dialogSearchLaw.setArguments(bundle);
                dialogSearchLaw.setListener(this);
                dialogSearchLaw.show(getFragmentManager(), "dialogSearchLaw");
                break;
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(4),true,false);
                break;
        }
    }

    @Override
    public void onClickPositiveButton(DialogFragment dialogFragment) {
        Bundle bundle = dialogFragment.getArguments();
        int rgModeChecked = bundle.getInt(RG_MODE,0);
        int rgSoftModeChecked = bundle.getInt(RG_SORT_MODE, 0);
        int rgSoftValueChecked = bundle.getInt(RG_MODE_VALUE, 0);
        String keyword = bundle.getString(KEYWORD, "");

        presenter.searchLaw(keyword);
    }

    @Override
    public void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyDataInserted() {
    }

    @Override
    public void onClickArticleItem(int position) {
        SearchLawResponse.Article article = articles.get(position);
        FragmentArticleDetail articleDetail = new FragmentArticleDetail();
        Bundle bundle = new Bundle();
        bundle.putString("articleId", article.getId());
        articleDetail.setArguments(bundle);
        goNextFragment(articleDetail,true,true);
    }
}
