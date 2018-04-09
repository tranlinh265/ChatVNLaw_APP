package com.lkbcteam.tranlinh.chatvnlaw.fragment;

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

import com.google.firebase.auth.FirebaseAuth;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.TodoListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.TodoItem;
import com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.TodoList;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataLoadingFinish;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.FragmentMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tranlinh on 31/01/2018.
 */

public class FragmentTodos extends BaseFragment implements View.OnClickListener{
    private final String TITLE = "Danh sách công việc";

    private ImageButton mIbtnHomeMenu;
    private ProgressBar progressBar;
    private TodoList todoListLoader;
    private List<TodoItem> todos;
    private TodoListAdapter adapter;
    private RecyclerView rvTodoList;
    private TextView tvTitle;
    private TextView tvDataNotExist;

    public static FragmentTodos newInstance() {
        return new FragmentTodos();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_list,container,false);
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
        progressBar = view.findViewById(R.id.progress_bar);
        rvTodoList = view.findViewById(R.id.rv_todo_list);
        tvTitle = view.findViewById(R.id.tv_fragment_title);
        tvTitle.setText(TITLE);
        tvDataNotExist = view.findViewById(R.id.tv_data_not_exist);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        if(todos == null){
            todos = new ArrayList<>();
        }else{
            todos.clear();
        }
        RecyclerView.LayoutManager mLayout = new GridLayoutManager(getContext(),1);
        rvTodoList.setLayoutManager(mLayout);

        adapter = new TodoListAdapter(getContext(), todos);
        rvTodoList.setAdapter(adapter);

        todoListLoader = new TodoList(this,getContext(), FirebaseAuth.getInstance().getCurrentUser());
        todoListLoader.loadTodoList(new OnDataLoadingFinish() {
            @Override
            public void onSuccess(Object o) {
                progressBar.setVisibility(View.GONE);
                tvDataNotExist.setVisibility(View.GONE);
                rvTodoList.setVisibility(View.VISIBLE);
                TodoItem item = (TodoItem)o;
                if(todos.size() == 0){
                    TodoItem timer = new TodoItem();
                    timer.setTime(item.getTime());
                    timer.setType(2);
                    todos.add(timer);
                    todos.add(item);
                }else{
                    int i;
                    for(i = todos.size() -1; i >= 0; i-- ){
                        if(i == 0)
                            break;
                        TodoItem tmpItem = todos.get(i);

                        if(tmpItem.getType() == 2)
                            continue;

                        if (TimeUnit.MILLISECONDS.toDays(tmpItem.getTimecreate()) > TimeUnit.MILLISECONDS.toDays(item.getTimecreate())) {
                            continue;
                        }
                        if (TimeUnit.MILLISECONDS.toDays(tmpItem.getTimecreate()) < TimeUnit.MILLISECONDS.toDays(item.getTimecreate())){
                            break;
                        }

                        if (tmpItem.getTimecreate() > item.getTimecreate()){
                            continue;
                        }
                        break;
                    }

                    if(i == 0){
                        TodoItem timer = new TodoItem();
                        timer.setTime(item.getTime());
                        timer.setType(2);
                        todos.add(0,timer);
                        todos.add(1,item);
                    }else{
                        TodoItem tmpItem = todos.get(i);
                        if(TimeUnit.MILLISECONDS.toDays(tmpItem.getTimecreate()) != TimeUnit.MILLISECONDS.toDays(item.getTimecreate())){
                            TodoItem timer = new TodoItem();
                            timer.setTime(item.getTime());
                            timer.setType(2);
                            todos.add(i+1,timer);
                            todos.add(i+2,item);
                        }else{
                            todos.add(i+1,item);
                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {
                progressBar.setVisibility(View.GONE);
                tvDataNotExist.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDataNotExist() {
                progressBar.setVisibility(View.GONE);
                tvDataNotExist.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(3),true,false);
                break;
        }
    }
}
