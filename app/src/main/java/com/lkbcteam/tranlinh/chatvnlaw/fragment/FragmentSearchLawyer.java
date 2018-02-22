package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.SearchLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.APIService;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;

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
    public static FragmentSearchLawyer newInstance() {
        return new FragmentSearchLawyer();
    }

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
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        apiService = ApiUtils.getService();
    }

    private void searchLawyer(String lawyerName){
        apiService.searchLawyer(lawyerName).enqueue(new Callback<SearchLawyerResponse>() {
            @Override
            public void onResponse(Call<SearchLawyerResponse> call, Response<SearchLawyerResponse> response) {
                if (response.isSuccessful()){

                }else {

                }
            }

            @Override
            public void onFailure(Call<SearchLawyerResponse> call, Throwable t) {

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
