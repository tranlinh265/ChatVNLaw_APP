package com.lkbcteam.tranlinh.chatvnlaw.other.custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.lkbcteam.tranlinh.chatvnlaw.R;

/**
 * Created by tranlinh on 23/04/2018.
 */

public class DialogTaskContent extends DialogFragment {

    public static final String TAG_NEW_TASK_DIALOG = "new_task_dialog";
    public static final String TAG_EDIT_TASK_DIALOG = "edit_task_dialog";
    public static final String DIALOG_TITLE = "dialog_title";
    public static final String DIALOG_NEGATIVE_BUTTON_TEXT = "negative_text";
    public static final String DIALOG_POSITIVE_BUTTON_TEXT = "positive_text";
    public static final String DIALOG_NEUTRA_BUTTON_TEXT = "neutral_text";

    public static final String DIALOG_CONTENT = "dialog_content";
    public static final String DIALOG_TASK_ID = "task_id";
    public static final String DIALOG_TASK_POSITION = "position";
    public static final String DIAOG_TASK_STATUS = "task_status";

    private DialogTaskContentListener listener;
    private Dialog dialog;

    private EditText edtContent;
    private CheckBox cbStatus;

    public static DialogTaskContent newInstance(Bundle bundle) {
        DialogTaskContent fragment = new DialogTaskContent();
        fragment.setArguments(bundle);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(DIALOG_TITLE, "");
        String negativeBtnText = getArguments().getString(DIALOG_NEGATIVE_BUTTON_TEXT, "cancel");
        String positiveBtnText = getArguments().getString(DIALOG_POSITIVE_BUTTON_TEXT,"ok");
        String neutralBtnText = getArguments().getString(DIALOG_NEUTRA_BUTTON_TEXT, "");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle(title);
        builder.setView(inflater.inflate(R.layout.dialog_task_content, null))
                .setNegativeButton(negativeBtnText, (dialog, which) -> {
                    listener.onClickNegativeButton(DialogTaskContent.this);
                })
                .setPositiveButton(positiveBtnText, (dialog, which) -> {
                    if(edtContent == null){
                        edtContent = (EditText)this.dialog.findViewById(R.id.edt_task_content);
                    }
                    if(cbStatus == null){
                        cbStatus = (CheckBox)this.dialog.findViewById(R.id.cb_task_status);
                    }
                    String content = edtContent.getText().toString();
                    String status = cbStatus.isChecked() ? "Done" : "Doing";

                    DialogTaskContent dialogFragment = DialogTaskContent.this;
                    dialogFragment.getArguments().putString(DIALOG_CONTENT, content);
                    dialogFragment.getArguments().putString(DIAOG_TASK_STATUS, status);

                    listener.onClickPositiveButton(dialogFragment);
                });
        if (!TextUtils.isEmpty(neutralBtnText)){
            builder.setNeutralButton(neutralBtnText, (dialog,which) ->{
                listener.onClickNeutralButton(DialogTaskContent.this);
            });
        }
        this.dialog = builder.create();

        this.dialog.setOnShowListener(dialogInterface -> {
            String content = getArguments().getString(DIALOG_CONTENT, "");
            String status = getArguments().getString(DIAOG_TASK_STATUS, "Doing");

            cbStatus = (CheckBox) dialog.findViewById(R.id.cb_task_status);
            edtContent = (EditText) dialog.findViewById(R.id.edt_task_content);
            if(edtContent != null){
                edtContent.setText(content);
            }
            if(cbStatus != null){
                cbStatus.setChecked(!status.equals("Doing"));
            }
        });
        return dialog;
    }

    public void setListener(DialogTaskContentListener listener) {
        this.listener = listener;
    }

    public interface DialogTaskContentListener{
        void onClickNegativeButton(DialogFragment dialogFragment);
        void onClickPositiveButton(DialogFragment dialogFragment);
        void onClickNeutralButton(DialogFragment dialogFragment);
    }
}
