package com.lkbcteam.tranlinh.chatvnlaw.other.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Lawyer;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 26/02/2018.
 */

public class ListLawyerAdapter extends RecyclerView.Adapter<ListLawyerAdapter.ViewHolder> {
    private List<Lawyer> lawyers;
    private Context context;
    private BaseFragment baseFragment;

    public ListLawyerAdapter(Context context, BaseFragment baseFragment, List<Lawyer> lawyers){
        this.lawyers = lawyers;
        this.context = context;
        this.baseFragment = baseFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_lawyer,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lawyer lawyer = lawyers.get(position);
        holder.tvLawyerName.setText(lawyer.getName());
        holder.tvLawyerCost.setText(String.valueOf(lawyer.getCost()));
        holder.rbLawyerRating.setRating(lawyer.getRate());
        holder.tvLawyerIntro.setText(lawyer.getIntro());
        Picasso.with(context).load(lawyer.getPhotoUrl()).into(holder.civLawyerAvatar);
    }

    @Override
    public int getItemCount() {
        return lawyers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView civLawyerAvatar, civStatus;
        public TextView tvLawyerName, tvLawyerCost,tvMoneyUnit,tvLawyerIntro;
        public RatingBar rbLawyerRating;

        public ViewHolder(View itemView) {
            super(itemView);
            civLawyerAvatar = itemView.findViewById(R.id.civ_user_image);
            civStatus = itemView.findViewById(R.id.civ_status);
            tvLawyerName = itemView.findViewById(R.id.tv_lawyer_name);
            tvLawyerCost = itemView.findViewById(R.id.tv_cost);
            tvMoneyUnit = itemView.findViewById(R.id.tv_money_unit);
            tvLawyerIntro = itemView.findViewById(R.id.tv_intro_content);
            rbLawyerRating = itemView.findViewById(R.id.rb_rating);
        }
    }
}
