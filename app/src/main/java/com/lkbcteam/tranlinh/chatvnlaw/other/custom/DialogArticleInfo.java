package com.lkbcteam.tranlinh.chatvnlaw.other.custom;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class DialogArticleInfo extends DialogFragment {
    private final String TITLE = "Thông tin";
    private final String NEGATIVE_BTN = "Đóng";

    private Dialog dialog;

    public DialogArticleInfo(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(TITLE)
                .setView(R.layout.dialog_attributes)
                .setNegativeButton(NEGATIVE_BTN, (dialog, which) -> {
                    dialog.dismiss();
                });
        dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> {
            Bundle bundle = getArguments();
            if (bundle == null)
                return;
            TextView tvNumericalSymbol = (TextView) dialog.findViewById(R.id.tv_numerical_symbol);
            TextView tvArticleType = (TextView) dialog.findViewById(R.id.tv_article_type);
            TextView tvSource = (TextView)dialog.findViewById(R.id.tv_source);
            TextView tvAgencyIssued = (TextView)dialog.findViewById(R.id.tv_agency_issued);
            TextView tvTheSigner = (TextView)dialog.findViewById(R.id.tv_the_signer);
            TextView tvSignerTitle = (TextView)dialog.findViewById(R.id.tv_signer_title);
            TextView tvScope = (TextView)dialog.findViewById(R.id.tv_scope);
            TextView tvPublicDay = (TextView)dialog.findViewById(R.id.tv_public_day);
            TextView tvEffectDay = (TextView)dialog.findViewById(R.id.tv_effect_day);
            TextView tvDayReport = (TextView)dialog.findViewById(R.id.tv_day_report);
            TextView tvEffectStatus = (TextView)dialog.findViewById(R.id.tv_effect_status);

            tvNumericalSymbol.setText(bundle.getString("numerical_symbol", ""));
            tvArticleType.setText(bundle.getString("article_type", ""));
            tvSource.setText(bundle.getString("source", ""));
            tvAgencyIssued.setText(bundle.getString("agency_issued", ""));
            tvTheSigner.setText(bundle.getString("the_signer", ""));
            tvSignerTitle.setText(bundle.getString("signer_title", ""));
            tvScope.setText(bundle.getString("scope", ""));
            tvPublicDay.setText(bundle.getString("public_day", ""));
            tvEffectDay.setText(bundle.getString("effect_day", ""));
            tvDayReport.setText(bundle.getString("day_report", ""));
            tvEffectStatus.setText(bundle.getString("effect_status", ""));
        });
        return dialog;
    }
}
