package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.app.SearchManager;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.LawyerCardAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.Lawyer;
import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.LawyerNameResponse;
import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.SearchLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.APIService;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 31/01/2018.
 */

public class FragmentSearchLawyer extends BaseFragment implements View.OnClickListener ,CardStackView.CardEventListener{
    private ImageButton mIbtnHomeMenu;
    private APIService apiService;
    private TextView tvTitle;
    private final String TITLE = "Tìm kiếm luật sư";
    private CardStackView csvSearchResult;
    private List<Lawyer> lawyers;
    private LawyerCardAdapter adapter;
    private Button btnReload;
    private ProgressBar progressBar;
    private String keyword = "";
    private int currentPage = 0, limitPage=0;
    private int totalResult = 0;
    private final int OFFSET = 6;
    private ArrayList<String> suggestion = new ArrayList<>();

    public static FragmentSearchLawyer newInstance() {
        return new FragmentSearchLawyer();
    }
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_search_lawyer,container,false);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mIbtnHomeMenu = view.findViewById(R.id.ibtn_home_menu);
        mIbtnHomeMenu.setOnClickListener(this);
        tvTitle = view.findViewById(R.id.tv_fragment_title);
        tvTitle.setText(TITLE);
        csvSearchResult = view.findViewById(R.id.csv_lawyers);
        csvSearchResult.setCardEventListener(this);
        progressBar = view.findViewById(R.id.activity_main_progress_bar);
        btnReload = view.findViewById(R.id.btn_reload);
        btnReload.setOnClickListener(this);
        toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }

    private void reload(){
        currentPage = 0;
        btnReload.setVisibility(View.INVISIBLE);
        csvSearchResult.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                searchLawyer(keyword);
            }
        }, 1000);
    }
    @Override
    protected void initData(View view) {
        super.initData(view);
        apiService = ApiUtils.getService();
        lawyers = new ArrayList<>();
        adapter = new LawyerCardAdapter(getContext());
        adapter.addAll(lawyers);
        csvSearchResult.setAdapter(adapter);
        getLawyerName();
        reload();
    }

    private void getLawyerName(){
        suggestion = new ArrayList<>();
        apiService.getAllNameOfLawyer().enqueue(new Callback<LawyerNameResponse>() {
            @Override
            public void onResponse(Call<LawyerNameResponse> call, Response<LawyerNameResponse> response) {
                if(response.isSuccessful()){
                    List<LawyerNameResponse.Name> names = response.body().getNames();
                    int i;
                    for (i = 0; i < names.size(); i++){
                        suggestion.add(names.get(i).getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<LawyerNameResponse> call, Throwable t) {

            }
        });
    }
    private void searchLawyer(final String lawyerName){
        apiService.searchLawyer(lawyerName,currentPage).enqueue(new Callback<SearchLawyerResponse>() {
            @Override
            public void onResponse(Call<SearchLawyerResponse> call, Response<SearchLawyerResponse> response) {
                if (response.isSuccessful()){
                    limitPage = response.body().getLimitPage();
                    currentPage = response.body().getCurrentPage();
                    totalResult = response.body().getNumberLawyers();
                    adapter.setCurrentPage(currentPage);
                    adapter.setTotalResult(totalResult);
                    lawyers.clear();
                    adapter.clear();
                    if(response.body().getLawyers().size() > 0){
                        lawyers.addAll(response.body().getLawyers());
                        adapter.addAll(lawyers);
                    }else{
                        btnReload.setVisibility(View.VISIBLE);
                        keyword = "";
                    }
                    adapter.notifyDataSetChanged();
                    csvSearchResult.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }else {
                    lawyers.clear();
                    adapter.clear();
                    keyword = "";
                    csvSearchResult.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<SearchLawyerResponse> call, Throwable t) {
                t.printStackTrace();
                btnReload.setVisibility(View.VISIBLE);
                csvSearchResult.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(5),true,false);
                break;
            case R.id.btn_reload:
                reload();
                break;
        }
    }

    @Override
    public void onCardDragging(float percentX, float percentY) {

    }

    @Override
    public void onCardSwiped(SwipeDirection direction) {
        if(csvSearchResult.getTopIndex() == adapter.getCount()){
//            btnReload.setVisibility(View.VISIBLE);
            if(currentPage < limitPage){
                currentPage++;
                progressBar.setVisibility(View.VISIBLE);
                searchLawyer(keyword);
            }else{
                btnReload.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onCardReversed() {

    }

    @Override
    public void onCardMovedToOrigin() {

    }

    @Override
    public void onCardClicked(int index) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        final SearchView searchView = (SearchView) item.getActionView();
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        SearchManager searchManager =
                (SearchManager) getContext().getSystemService(getContext().SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_1, suggestion);

        // jsonarraylist is static string array

        searchAutoComplete.setAdapter(adapter);

        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  // Your code for onitemclick
                  if(view instanceof TextView){
                      TextView textView = (TextView) view;
                      searchView.setQuery(textView.getText(),true);
                  }
              }
          });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keyword = query;
                reload();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
