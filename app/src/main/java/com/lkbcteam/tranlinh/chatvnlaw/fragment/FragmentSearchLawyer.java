package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ListLawyerAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.Lawyer;
import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.SearchLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.APIService;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 31/01/2018.
 */

public class FragmentSearchLawyer extends BaseFragment implements View.OnClickListener {
    private ImageButton mIbtnHomeMenu;
    private APIService apiService;
    private TextView tvTitle;
    private final String TITLE = "Tìm kiếm luật sư";
    private RecyclerView rvLawyerList;
    private ListLawyerAdapter listLawyerAdapter;
    private List<Lawyer> lawyers;
    public static FragmentSearchLawyer newInstance() {
        return new FragmentSearchLawyer();
    }
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_lawyer,container,false);
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
        rvLawyerList = view.findViewById(R.id.rv_list_lawyer);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rvLawyerList.setLayoutManager(layoutManager);
        toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        apiService = ApiUtils.getService();
        lawyers = new ArrayList<>();
        listLawyerAdapter = new ListLawyerAdapter(getContext(),this,lawyers);
        rvLawyerList.setAdapter(listLawyerAdapter);
        getAllLawysers();
    }

    private void getAllLawysers(){
        searchLawyer("");
    }

    private void searchLawyer(String lawyerName){
        apiService.searchLawyer(lawyerName).enqueue(new Callback<SearchLawyerResponse>() {
            @Override
            public void onResponse(Call<SearchLawyerResponse> call, Response<SearchLawyerResponse> response) {
                if (response.isSuccessful()){
                    lawyers.clear();
                    lawyers.addAll(response.body().getLawyers());
                    listLawyerAdapter.notifyDataSetChanged();
                }else {

                }
            }

            @Override
            public void onFailure(Call<SearchLawyerResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(5),true,false);
                break;
        }
    }

}
