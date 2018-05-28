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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.adapter.SearchLawAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SearchLawResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.SearchLawPresenter;

import java.util.ArrayList;
import java.util.List;


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
    private TextView tvCurrentPosition;
    private Button btnLoadMore;

    private SearchLawPresenter presenter;

    private boolean initView = false;
    private int currentPage = 0;

    private Bundle bundle;
    private TextView tvNodata;
    private View vContent;

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
        if(articles == null){
            articles = new ArrayList<>();
            bundle = new Bundle();
            initView = true;
        }

        ibtnSearch = (ImageButton)view.findViewById(R.id.ibtn_search);
        ibtnBack = (ImageButton)view.findViewById(R.id.ibtn_home_menu);
        ibtnSearch.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
        layoutManager = new GridLayoutManager(getContext(), 1){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvLawResult = (RecyclerView)view.findViewById(R.id.rv_law_result);
        adapter = new SearchLawAdapter();
        adapter.setArticles(articles);
        adapter.setCallback(this);
        rvLawResult.setLayoutManager(layoutManager);
        rvLawResult.setAdapter(adapter);
        tvCurrentPosition = (TextView)view.findViewById(R.id.tv_current_position);
        btnLoadMore = (Button)view.findViewById(R.id.btn_load_more);
        btnLoadMore.setOnClickListener(this);
        tvNodata = (TextView)view.findViewById(R.id.tv_no_data);
        vContent = (View)view.findViewById(R.id.nsv_content);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        presenter = new SearchLawPresenter();
        presenter.setArticles(articles);
        presenter.setCallback(this);
        if (articles.isEmpty() && initView){
            presenter.searchLaw(bundle);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_search:
                // show dialog
                DialogSearchLaw dialogSearchLaw = new DialogSearchLaw();
//                Bundle bundle = new Bundle();
                dialogSearchLaw.setArguments(bundle);
                dialogSearchLaw.setListener(this);
                dialogSearchLaw.show(getFragmentManager(), "dialogSearchLaw");
                break;
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(4),true,false);
                break;
            case R.id.btn_load_more:
                bundle.putString("page", String.valueOf(currentPage + 1));
                presenter.loadMore(bundle);
                break;
        }
    }

    @Override
    public void onClickPositiveButton(DialogFragment dialogFragment) {
        bundle = dialogFragment.getArguments();
        presenter.searchLaw(bundle);
    }

    @Override
    public void onSearchFirstPageSuccess(String currentPosition) {
        tvNodata.setVisibility(View.GONE);
        vContent.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        currentPage = 1;
        tvCurrentPosition.setText(currentPosition);
        vContent.scrollTo(0,0);
    }

    @Override
    public void onSearchMoreSuccess(String currentPosition) {
        tvNodata.setVisibility(View.GONE);
        vContent.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        currentPage++;
        tvCurrentPosition.setText(currentPosition);
    }

    @Override
    public void onNoDataFound() {
        adapter.notifyDataSetChanged();
        tvNodata.setVisibility(View.VISIBLE);
        vContent.setVisibility(View.GONE);
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
