package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.TodoListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.TodoItem;
import com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.TodoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 09/04/2018.
 */

public class FragmentTodoList extends BaseFragment implements View.OnClickListener{

    private ImageButton mIbtnHomeMenu;
    private ProgressBar progressBar;
    private TodoList todoListLoader;
    private List<TodoItem> todos;
    private TodoListAdapter adapter;
    private RecyclerView rvTodoList;
    private TextView tvTitle;
    private TextView tvDataNotExist;
    private RecyclerView.LayoutManager mLayout;

    public static FragmentTodoList newInstance() {

        Bundle args = new Bundle();

        FragmentTodoList fragment = new FragmentTodoList();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_list, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mIbtnHomeMenu = view.findViewById(R.id.ibtn_home_menu);
        mIbtnHomeMenu.setOnClickListener(this);
        progressBar = view.findViewById(R.id.progress_bar);
        rvTodoList = view.findViewById(R.id.rv_todo_list);
        tvTitle = view.findViewById(R.id.tv_fragment_title);
        tvDataNotExist = view.findViewById(R.id.tv_data_not_exist);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        if (todos == null){
            todos = new ArrayList<>();
        }else{
            todos.clear();
        }
        mLayout = new GridLayoutManager(getContext(),1);
        rvTodoList.setLayoutManager(mLayout);
        adapter = new TodoListAdapter(getContext(), todos);
        rvTodoList.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(3),true,false);
                break;
        }
    }
}
