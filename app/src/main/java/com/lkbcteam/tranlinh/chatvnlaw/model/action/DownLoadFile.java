package com.lkbcteam.tranlinh.chatvnlaw.model.action;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 01/02/2018.
 */

public class DownLoadFile {
    public static void DownLoadFileViaUrl(final Context context, String fileName, String url){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference httpsReference = storage.getReferenceFromUrl(url);
        Log.d("in", "onChatMessageItemClicked: ");
        File outputDir = context.getCacheDir();
        try {
            final File localFile = File.createTempFile(getPrefixAndSuffix(fileName).get(0), getPrefixAndSuffix(fileName).get(1));
            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Log.d(localFile.getAbsolutePath(), "onChatMessageItemClicked: ");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Log.d("fail", "onChatMessageItemClicked: ");

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){

        }
    }

    public static List<String> getPrefixAndSuffix(String input){
        String reverse = new StringBuilder(input).reverse().toString();
        List<String> result = new ArrayList<>();

        String[] split = String.valueOf(reverse).split("[.]");

        result.add(new StringBuilder(split[1]).reverse().toString());
        result.add(new StringBuilder(split[0]).reverse().toString());

        return result;
    }
}
