package com.lkbcteam.tranlinh.chatvnlaw.other.custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;

import com.lkbcteam.tranlinh.chatvnlaw.R;

/**
 * Created by tranlinh on 23/04/2018.
 */

public class DialogTaskContent extends DialogFragment {

    private DialogTaskContentListener listener;
    private Dialog dialog;

    public static DialogTaskContent newInstance(Bundle bundle) {
        DialogTaskContent fragment = new DialogTaskContent();
        fragment.setArguments(bundle);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title", "");
        String negativeBtnText = getArguments().getString("negativeBtn", "cancel");
        String positiveBtnText = getArguments().getString("positiveBtn","ok");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle(title);
        builder.setView(inflater.inflate(R.layout.dialog_task_content, null))
                .setNegativeButton(negativeBtnText, (dialog, which) -> {
                    listener.onClickNegativeButton(DialogTaskContent.this);
                })
                .setPositiveButton(positiveBtnText, (dialog, which) -> {
                    listener.onClickPositiveButton(DialogTaskContent.this);
                });
        this.dialog = builder.create();
        return dialog;
    }

    public void setListener(DialogTaskContentListener listener) {
        this.listener = listener;
    }

    public interface DialogTaskContentListener{
        void onClickNegativeButton(DialogFragment dialogFragment);
        void onClickPositiveButton(DialogFragment dialogFragment);
    }
}
