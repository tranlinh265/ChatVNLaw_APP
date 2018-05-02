package com.lkbcteam.tranlinh.chatvnlaw.other.custom;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

/**
 * Created by tranlinh on 02/05/2018.
 */

public class DialogSearchLaw extends DialogFragment {
    private final String title = "Tìm kiếm luật";
    private final String negativeBtnText = "Hủy";
    private final String positiveBtnText = "Tìm kiếm";
    private final String neutralBtnText = "Mặc định";

    public static final String RG_MODE = "rgMode";
    public static final String RG_SORT_MODE = "rgSoftMode";
    public static final String RG_MODE_VALUE = "rgSoftValue";
    public static final String KEYWORD = "keyword";

    private DialogSearchLawListener listener;
    private Dialog dialog;

    private RadioGroup rgMode, rgSoftMode, rgSoftValue;
    private RadioButton rbMatchAll, rbContainAll, rbReleaseDay, rbEffectDay,
            rbNewToOld, rbOldToNew;
    private EditText edtKeyword;

    public DialogSearchLaw(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle(title);
        builder.setView(R.layout.diaglo_search_law)
                .setNegativeButton(negativeBtnText, (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton(positiveBtnText, (dialog, which) -> {
                    DialogFragment dialogFragment = DialogSearchLaw.this;
                    dialogFragment.getArguments().putInt(RG_MODE,rbMatchAll.isChecked() ? 0 : 1);
                    dialogFragment.getArguments().putInt(RG_SORT_MODE, rbReleaseDay.isChecked()? 0: 1);
                    dialogFragment.getArguments().putInt(RG_MODE_VALUE, rbNewToOld.isChecked()? 0: 1);
                    dialogFragment.getArguments().putString(KEYWORD, edtKeyword.getText().toString());
                    listener.onClickPositiveButton(dialogFragment);
                })
                .setNeutralButton(neutralBtnText, (dialog, which) -> {
                    edtKeyword.setText(null);
                    rbMatchAll.setChecked(true);
                    rbContainAll.setChecked(false);
                    rbReleaseDay.setChecked(true);
                    rbEffectDay.setChecked(false);
                    rbNewToOld.setChecked(true);
                    rbOldToNew.setChecked(false);
                });

        dialog = builder.create();

        dialog.setOnShowListener(dialogInterface -> {
            rgMode = (RadioGroup)dialog.findViewById(R.id.rg_mode);
            rgSoftMode = (RadioGroup)dialog.findViewById(R.id.rg_sort_mode);
            rgSoftValue = (RadioGroup)dialog.findViewById(R.id.rg_sort_value);
            rbMatchAll = (RadioButton)dialog.findViewById(R.id.rb_match_all);
            rbContainAll = (RadioButton)dialog.findViewById(R.id.rb_contain_all);
            rbEffectDay = (RadioButton)dialog.findViewById(R.id.rb_effect_day);
            rbReleaseDay = (RadioButton)dialog.findViewById(R.id.rb_release_day);
            rbOldToNew = (RadioButton)dialog.findViewById(R.id.rb_old_to_new);
            rbNewToOld = (RadioButton)dialog.findViewById(R.id.rb_new_to_old);
            edtKeyword = (EditText)dialog.findViewById(R.id.edt_keyword);

            Bundle bundle = getArguments();

            int rgModeChecked = bundle.getInt(RG_MODE,0);
            int rgSoftModeChecked = bundle.getInt(RG_SORT_MODE, 0);
            int rgSoftValueChecked = bundle.getInt(RG_MODE_VALUE, 0);
            String keyword = bundle.getString(KEYWORD, "");

            edtKeyword.setText(keyword);
            rbMatchAll.setChecked(rgModeChecked == 0);
            rbReleaseDay.setChecked(rgSoftModeChecked == 0);
            rbNewToOld.setChecked(rgSoftValueChecked == 0);

        });
        return dialog;
    }

    public void setListener(DialogSearchLawListener listener) {
        this.listener = listener;
    }

    public interface DialogSearchLawListener{
        void onClickPositiveButton(DialogFragment dialogFragment);
    }

}
