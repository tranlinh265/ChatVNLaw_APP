package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.TaskResponse;

import java.util.List;

/**
 * Created by tranlinh on 16/04/2018.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder>{
    private List<TaskResponse.Task> tasks;
    private  onClick callback;
    private Context context;

    public TodoListAdapter(Context context, List<TaskResponse.Task> tasks){
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        TaskResponse.Task task = tasks.get(position);
        if(TextUtils.isEmpty(task.getTargetUser())){
            // normal task
            return 1;
        }
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewHolder vh = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_list_room_info,parent, false);
                vh = new ViewHolder(view);
                vh.setViewType(viewType);
                return vh;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_list_task_content, parent, false);
                vh = new ViewHolder(view);
                vh.setViewType(viewType);
                return vh;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = holder.getViewType();
        TaskResponse.Task task = tasks.get(position);

        if (viewType == 0){
            holder.tvTargetUser.setText(String.format(context.getString(R.string.job_with_format), task.getTargetUser()));
            holder.ibtnAddTask.setOnClickListener(v -> callback.onClickAddNewTaskIcon(task, position));
        }else{
            holder.cbTaskStatus.setChecked(!task.getStatus().equals("Doing"));
            holder.tvTaskContent.setText(task.getContent());
            String updateAt = task.getUpdatedAt().split("T")[0];
            holder.tvTaskTime.setText(updateAt);
            holder.ibtnEditTask.setOnClickListener(v -> callback.onClickEditTaskIcon(task, position));
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public onClick getCallback() {
        return callback;
    }

    public void setCallback(onClick callback) {
        this.callback = callback;
    }

    public interface onClick{
        void onClickAddNewTaskIcon(Object o, int position);
        void onClickEditTaskIcon(Object o, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private int viewType;
        private TextView tvTargetUser;
        private CheckBox cbTaskStatus;
        private TextView tvTaskContent;
        private TextView tvTaskTime;
        private ImageButton ibtnAddTask, ibtnEditTask;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTargetUser = (TextView) itemView.findViewById(R.id.tv_room_info);
            cbTaskStatus = (CheckBox) itemView.findViewById(R.id.cb_task_status);
            tvTaskContent = (TextView) itemView.findViewById(R.id.tv_task_content);
            tvTaskTime = (TextView) itemView.findViewById(R.id.tv_task_time);
            ibtnAddTask = (ImageButton) itemView.findViewById(R.id.ibtn_add_new_task);
            ibtnEditTask = (ImageButton) itemView.findViewById(R.id.ibtn_edit_task);
        }

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }
    }
}
