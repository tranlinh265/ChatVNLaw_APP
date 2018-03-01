package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.Lawyer;
import com.lkbcteam.tranlinh.chatvnlaw.model.Specialization;
import com.squareup.picasso.Picasso;

/**
 * Created by tranlinh on 01/03/2018.
 */

public class LawyerCardAdapter extends ArrayAdapter<Lawyer> {
    public LawyerCardAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.item_card_lawyer,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Lawyer lawyer = getItem(position);
        viewHolder.tvLawyerName.setText(lawyer.getName());
        viewHolder.tvLawyerIntro.setText(lawyer.getIntro());
        viewHolder.tvCost.setText(String.valueOf(lawyer.getCost()));
        viewHolder.rbLawyerRating.setRating(lawyer.getRate());
        Picasso.with(getContext()).load(lawyer.getPhotoUrl()).into(viewHolder.ivLawyerAva);

        for(int i = 0; i < lawyer.getSpecializations().size(); i++){
            Specialization specialization = lawyer.getSpecializations().get(i);
            switch (specialization.getId()){
                case 1:
                    convertView.findViewById(R.id.tv_hinh_su).setVisibility(View.VISIBLE);
                    break;
                case 2:
                    convertView.findViewById(R.id.tv_so_huu_tri_tue).setVisibility(View.VISIBLE);
                    break;
                case 3:
                    convertView.findViewById(R.id.tv_hon_nhan_gia_dinh).setVisibility(View.VISIBLE);
                    break;
                case 4:
                    convertView.findViewById(R.id.tv_nha_dat_xay_dung).setVisibility(View.VISIBLE);
                    break;
                case 5:
                    convertView.findViewById(R.id.tv_tai_chinh_ngan_hang).setVisibility(View.VISIBLE);
                    break;
                case 6:
                    convertView.findViewById(R.id.tv_dan_su).setVisibility(View.VISIBLE);
                    break;
                case 7:
                    convertView.findViewById(R.id.tv_lao_dong_bao_hiem).setVisibility(View.VISIBLE);
                    break;
                case 8:
                    convertView.findViewById(R.id.tv_doanh_nghiep).setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
        return  convertView;
    }

    private static class ViewHolder{
        public ImageView ivLawyerAva;
        public TextView tvLawyerName, tvCost, tvMoneyUnit, tvLawyerIntro;
        public RatingBar rbLawyerRating;

        public ViewHolder(View view){
            ivLawyerAva = view.findViewById(R.id.iv_lawyer_avatar);
            tvLawyerName = view.findViewById(R.id.tv_lawyer_name);
            tvCost = view.findViewById(R.id.tv_cost);
            tvMoneyUnit = view.findViewById(R.id.tv_money_unit);
            rbLawyerRating = view.findViewById(R.id.rb_rating);
            tvLawyerIntro = view.findViewById(R.id.tv_intro_content);
        }
    }
}
