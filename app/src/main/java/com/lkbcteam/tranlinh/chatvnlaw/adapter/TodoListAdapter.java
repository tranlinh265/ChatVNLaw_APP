package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.TodoItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 05/03/2018.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder>{
    private Context context;
    private List<TodoItem> todos;

    public TodoListAdapter(Context context, List<TodoItem> todos){
        this.context = context;
        this.todos = todos;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_list,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodoItem item = todos.get(position);
        switch (item.getType()){
            case 1:
                if(item.getStatus() == 0){
                    holder.civUndone.setVisibility(View.VISIBLE);
                }else{
                    holder.civDone.setVisibility(View.VISIBLE);
                }
                holder.llTimer.setVisibility(View.GONE);
                holder.prlItemContainer.setVisibility(View.VISIBLE);
                holder.tvTargetUser.setText(item.getTargetuserdisplayname());
                holder.tvContent.setText(item.getText());
                holder.tvHour.setText(String.format("%s:%s",item.getTime().getHour(),item.getTime().getMinute()));
                break;
            case 2:
                holder.llTimer.setVisibility(View.VISIBLE);
                holder.prlItemContainer.setVisibility(View.GONE);
                holder.tvTimeDay.setText(String.format("%s-%s-%s",item.getTime().getDay(),item.getTime().getMonth(),item.getTime().getYear()));
                break;
        }
    }


    @Override
    public int getItemCount() {
        return todos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llTimer;
        public PercentRelativeLayout prlItemContainer;
        public TextView tvTimeDay,tvHour,tvTargetUser,tvContent;
        public CircleImageView civStatus,civDone,civUndone;

        public ViewHolder(View itemView) {
            super(itemView);
            civDone = itemView.findViewById(R.id.civ_done);
            civUndone = itemView.findViewById(R.id.civ_undone);
            civStatus = itemView.findViewById(R.id.civ_status);
            llTimer = itemView.findViewById(R.id.ll_right_layout_timer);
            prlItemContainer = itemView.findViewById(R.id.prl_right_layout_item_container);
            tvTimeDay = itemView.findViewById(R.id.tv_time_day);
            tvHour = itemView.findViewById(R.id.tv_hour);
            tvTargetUser = itemView.findViewById(R.id.tv_target_user);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
