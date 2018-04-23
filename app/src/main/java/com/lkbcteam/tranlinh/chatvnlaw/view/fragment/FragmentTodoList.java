package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.TodoListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.TodoListAdapter1;
import com.lkbcteam.tranlinh.chatvnlaw.model.TodoItem;
import com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.TodoList;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.TaskResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogTaskContent;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.TodoListPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogTaskContent.DIALOG_CONTENT;

/**
 * Created by tranlinh on 09/04/2018.
 */

public class FragmentTodoList extends BaseFragment implements View.OnClickListener
        , TodoListPresenter.onLoadData
        , TodoListAdapter.onClick
        , DialogTaskContent.DialogTaskContentListener{
    private final String ROOM_ID = "room_id";

    private ImageButton mIbtnHomeMenu;
    private ProgressBar progressBar;
    private List<TaskResponse.Task> tasks;
    private TodoListAdapter adapter;
    private RecyclerView rvTodoList;
    private TextView tvTitle;
    private TextView tvDataNotExist;
    private RecyclerView.LayoutManager mLayout;
    private TodoListPresenter presenter;

    private boolean isChanged = false;

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
        if (tasks == null){
            tasks = new ArrayList<>();
        }else{
            tasks.clear();
        }
        mLayout = new GridLayoutManager(getContext(),1);
        rvTodoList.setLayoutManager(mLayout);
        adapter = new TodoListAdapter(getContext(),tasks);
        adapter.setCallback(this);
        rvTodoList.setAdapter(adapter);
        presenter = new TodoListPresenter(tasks);
        presenter.setCallback(this);
        loadTaskList();
    }

    private void loadTaskList(){
        Integer lawyerId = SharePreference.getInstance(getActivity()).getLawyerId();
        String lawyerToken = SharePreference.getInstance(getActivity()).getUserToken();
        String lawyerEmail = SharePreference.getInstance(getActivity()).getEmail();
        presenter.loadTaskFromRail(String.valueOf(lawyerId), lawyerToken, lawyerEmail);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(3),true,false);
                break;
        }
    }

    @Override
    public void notifyDataChanged() {
        progressBar.setVisibility(View.GONE);
        rvTodoList.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onClickAddNewTaskIcon(Object o, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(DialogTaskContent.DIALOG_TITLE, "Tạo công việc mới với " + ((TaskResponse.Task)o).getTargetUser());
        bundle.putString(DialogTaskContent.DIALOG_NEGATIVE_BUTTON_TEXT,"Hủy");
        bundle.putString(DialogTaskContent.DIALOG_POSITIVE_BUTTON_TEXT, "Tạo mới");
        bundle.putInt(ROOM_ID, ((TaskResponse.Task)o).getRoomId());

        DialogFragment dialogFragment = DialogTaskContent.newInstance(bundle);
        ((DialogTaskContent)dialogFragment).setListener(this);
        dialogFragment.show(getFragmentManager(), DialogTaskContent.TAG_NEW_TASK_DIALOG);
    }

    @Override
    public void onClickEditTaskIcon(Object o, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(DialogTaskContent.DIALOG_TITLE, "Chỉnh sửa công việc");
        bundle.putString(DialogTaskContent.DIALOG_NEGATIVE_BUTTON_TEXT,"Hủy");
        bundle.putString(DialogTaskContent.DIALOG_POSITIVE_BUTTON_TEXT,"Chỉnh sửa");

        bundle.putString(DIALOG_CONTENT,((TaskResponse.Task)o).getContent());
        bundle.putString(DialogTaskContent.DIALOG_NEUTRA_BUTTON_TEXT, "Xóa");

        DialogFragment dialogFragment = DialogTaskContent.newInstance(bundle);
        ((DialogTaskContent)dialogFragment).setListener(this);
        dialogFragment.show(getFragmentManager(), DialogTaskContent.TAG_EDIT_TASK_DIALOG);
    }

    @Override
    public void onClickNegativeButton(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }

    @Override
    public void onClickPositiveButton(DialogFragment dialogFragment) {
        String userToken = SharePreference.getInstance(getActivity()).getUserToken();
        String userEmail = SharePreference.getInstance(getActivity()).getEmail();
        String content = dialogFragment.getArguments().getString(DIALOG_CONTENT,"");

        String roomId = String.valueOf(dialogFragment.getArguments().getInt(ROOM_ID,0));

        if (dialogFragment.getTag().equals(DialogTaskContent.TAG_NEW_TASK_DIALOG)){
            // create new task
            if(!TextUtils.isEmpty(content)){
                presenter.createNewTask(userToken, userEmail, roomId,content);
                dialogFragment.dismiss();
            }else{
                // show dialog notice

            }
        }else{
            // edit tag
            if(!TextUtils.isEmpty(content)){

            }else{

            }

            dialogFragment.dismiss();
        }
    }

    @Override
    public void onClickNeutralButton(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }
}
