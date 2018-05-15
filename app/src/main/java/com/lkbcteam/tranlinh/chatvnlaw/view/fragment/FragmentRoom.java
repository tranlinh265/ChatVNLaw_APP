package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ChatContentAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.RoomPresenter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class FragmentRoom extends BaseFragment implements RoomPresenter.RoomView,View.OnClickListener  {

    public final static int PICK_IMAGE_REQUEST = 1;
    public final static int READ_EXTERNAL_REQUEST = 2;


    private RecyclerView rvChatContentContainer;
    private List<Message> mMessageList;
    private RoomListResponse.Room room;
    private EditText mEdtChatInput;
    private ImageButton mBtnSend;
    private RoomPresenter roomPresenter;
    private ChatContentAdapter adapter;
    private RecyclerView.LayoutManager mLayout;
    private ImageButton mIbtnBack,mIbtnInfo;
    private boolean isloading = true;
    private ProgressBar pbLoading;
    private CircleImageView civTargetUserAvatar;
    private RoomListResponse.User targetUser;
    private TextView tvTargetUserDisplayName;
    private ImageButton ibtnPickImage;
    private int position = 0;
    private ImageView ivTopImage;

    private View.OnClickListener mHideSoftKey = view -> {
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    };

    public static FragmentRoom newInstance(RoomListResponse.Room room) {
        FragmentRoom fragment = new FragmentRoom();
        fragment.setRoom(room);
        return fragment;
    }

    public static FragmentRoom newInstance(RoomListResponse.Room room, int position) {
        
        Bundle args = new Bundle();
        
        FragmentRoom fragment = new FragmentRoom();
        fragment.room = room;
        fragment.position = position;
//        args.putString("position", String.valueOf(position));
//        args.putSerializable("room", room);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room,container,false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        civTargetUserAvatar = view.findViewById(R.id.iv_target_user_avatar);
        tvTargetUserDisplayName = view.findViewById(R.id.tv_sender_displayname);
//        Bundle bundle = getArguments();
//        if(bundle!=null){
//            String position = bundle.getString("position");
            civTargetUserAvatar.setTransitionName(getContext().getString(R.string.target_user_avatar_transiton) + this.position);
//            RoomListResponse.Room room = (RoomListResponse.Room)bundle.getSerializable("room");
//            setRoom(room);
            setTargetUser();
            Picasso.with(getContext()).load(targetUser.getAvatar().getThumbSmall().getUrl()).into(civTargetUserAvatar);
            tvTargetUserDisplayName.setText(targetUser.getDisplayName());
//        }
        mMessageList = new ArrayList<>();
        rvChatContentContainer = view.findViewById(R.id.rv_chat_content_container);
        mLayout = new GridLayoutManager(getContext(),1);
        rvChatContentContainer.setLayoutManager(mLayout);
        adapter = new ChatContentAdapter(getContext(),mMessageList, mHideSoftKey);
        adapter.setTargetUserPhotourl(targetUser.getAvatar().getThumbSmall().getUrl());
        rvChatContentContainer.setAdapter(adapter);
        mIbtnBack = view.findViewById(R.id.ibtn_back);
        mEdtChatInput = view.findViewById(R.id.edt_chat_input);
        mBtnSend = view.findViewById(R.id.btn_send);
        mIbtnInfo = view.findViewById(R.id.ibtn_info);
        mIbtnInfo.setOnClickListener(this);
        mIbtnBack.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        pbLoading = view.findViewById(R.id.pb_loading);
        ibtnPickImage = view.findViewById(R.id.ibtn_pick_image);
        ibtnPickImage.setOnClickListener(this);
        ibtnPickImage.setVisibility(View.GONE);
        ivTopImage = (ImageView)view.findViewById(R.id.iv_top_image);

        rvChatContentContainer.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mMessageList.size() == 0) return;

                int positionView = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if(positionView == 0){
                    if(!isloading){
                        isloading = true;
                        long time = Long.parseLong(mMessageList.get(0).getmMessageInfo().getMsgTimeStamp()) - 1;
                        roomPresenter.loadHistoryMessage(String.valueOf(room.getId()), String.valueOf(time));
                    }
                }
            }
        });
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        roomPresenter = new RoomPresenter(this,mMessageList);
        (new Handler()).postDelayed(() -> {
            pbLoading.setVisibility(View.GONE);
            rvChatContentContainer.setVisibility(View.VISIBLE);
            roomPresenter.loadHistoryMessage(String.valueOf(room.getId()));
            roomPresenter.loadIncomingMessage(String.valueOf(room.getId()));
        },500);

    }

    @Override
    public void notifyMessageAdded(int position) {
        adapter.notifyItemInserted(position);
    }

    @Override
    public void notifyMessageRemoved(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyListMessage(boolean scrollTolast) {
        adapter.notifyDataSetChanged();
        if(scrollTolast){
            mLayout.scrollToPosition(mMessageList.size() - 1);
        }
        isloading = false;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error, Toast.LENGTH_LONG).show();
    }

    public void setRoom(RoomListResponse.Room room) {
        this.room = room;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_back:
                goBackFragment();
                break;
            case R.id.btn_send:
                String chatContent = mEdtChatInput.getText().toString();
                if(!TextUtils.isEmpty(chatContent)){
                    roomPresenter.sendMessage(String.valueOf(room.getId()), chatContent);
                    mEdtChatInput.setText(null);
                }
                break;
            case R.id.ibtn_info:

                Fragment nextFragment = FragmentRoomInfo.newInstance(room);
                Fragment previousFragment = getFragmentManager().findFragmentById(R.id.container_framelayout);

                Transition sharedElementEnterTransition = TransitionInflater.from(getContext()).inflateTransition(R.transition.default_transition);
                sharedElementEnterTransition.setDuration(FADE_DEFAULT_TIME);

                previousFragment.setSharedElementReturnTransition(sharedElementEnterTransition);
                nextFragment.setSharedElementEnterTransition(sharedElementEnterTransition);

                goNextFragment(nextFragment,true,true, ivTopImage);
                break;
            case R.id.ibtn_pick_image:
                requestPermission();
                break;
        }
    }

    public RoomListResponse.User getTargetUser() {
        return targetUser;
    }
    private void setTargetUser(){
        if(this.room.isCurrentUserIsLawyer()){
            setTargetUser(room.getUser());
        }else{
            setTargetUser(room.getLawyer());
        }
    }
    public void setTargetUser(RoomListResponse.User targetUser) {
        this.targetUser = targetUser;
    }
    private void requestPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            pickImage();
            return;
        }

        int result = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            pickImage();
        }else{
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, READ_EXTERNAL_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode != READ_EXTERNAL_REQUEST) return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            pickImage();
        }else{

        }
    }

    private void pickImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"),
                PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null &&
                data.getData() != null) {
            Uri uri = data.getData();
            Log.e("debug", "onActivityResult: " + roomPresenter.getRealPathFromUriPath(uri,getActivity()));
            String filePath = roomPresenter.getRealPathFromUriPath(uri, getActivity());
            File file = new File(filePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        }
    }
}
