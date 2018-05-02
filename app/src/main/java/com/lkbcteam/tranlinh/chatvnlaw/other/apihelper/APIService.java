package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper;

import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.LawyerNameResponse;
import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.SearchLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.TopLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ArticleDetailResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.CreateTaskResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.LoginResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ProfileResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomFileListResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SearchLawResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SignupResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.TaskResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.UserInfoResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tranlinh on 22/02/2018.
 */

public interface APIService {
    @GET(Define.Api.SEARCH_LAWYER)
    Call<SearchLawyerResponse> searchLawyer( @Query("query") @Nullable String name, @Query("page") int page);
    @GET(Define.Api.LAWYER_NAME)
    Call<LawyerNameResponse> getAllNameOfLawyer();
    @GET(Define.Api.TOP_LAWYERS)
    Call<TopLawyerResponse> getTopLawyer();

    @FormUrlEncoded
    @POST(Define.RailServer.API_SIGNUP_URL)
    Call<SignupResponse> signup(
        @Field(Define.RailServer.SignupField.ID) String id,
        @Field(Define.RailServer.SignupField.EMAIL) String email,
        @Field(Define.RailServer.SignupField.PASSWORD) String password,
        @Field(Define.RailServer.SignupField.PASSWORD_CONFIRM) String passwordConfirm,
        @Field(Define.RailServer.SignupField.DISPLAYNAME) String displayName,
        @Field(Define.RailServer.SignupField.USERNAME) String username,
        @Field(Define.RailServer.SignupField.ROLE) String role
    );

    @FormUrlEncoded
    @POST(Define.RailServer.API_LOGIN_URL)
    Call<LoginResponse> login(
        @Field(Define.RailServer.LoginField.EMAIL) String email,
        @Field(Define.RailServer.LoginField.password) String password
    );

    @GET(Define.RailServer.API_GET_ROOM_URL)
    Call<RoomListResponse> getRoomList(
        @Header(Define.RailServer.X_USER_TOKEN) String userToken,
        @Header(Define.RailServer.X_USER_EMAIL)String userEmail
    );

    @GET(Define.RailServer.API_GET_USER_INFO)
    Call<UserInfoResponse> getUserInfo(
        @Path("username") String username
    );

    @GET(Define.RailServer.API_GET_ROOM_FILE_LIST)
    Call<RoomFileListResponse> getRoomFileList(
            @Header(Define.RailServer.X_USER_TOKEN) String userToken,
            @Header(Define.RailServer.X_USER_EMAIL) String userEmail,
            @Path("roomId") String roomId
    );

    @GET(Define.RailServer.API_GET_PROFILE_DATA)
    Call<ProfileResponse> getLawyerProfileData(
            @Path("username") String username
    );

    @FormUrlEncoded
    @PATCH(Define.RailServer.API_UPDATE_LAWYER_INFO)
    Call<ProfileResponse> updateLawyerInfo(
            @Path("username") String username,
            @Header(Define.RailServer.X_USER_TOKEN) String userToken,
            @Header(Define.RailServer.X_USER_EMAIL) String userEmail,
            @Field(Define.RailServer.LawyerProfileFiled.BIRTHDAY) String birthDay,
            @Field(Define.RailServer.LawyerProfileFiled.DISPLAY_NAME) String displayName,
            @Field(Define.RailServer.LawyerProfileFiled.ACHIEVEMENT) String achievement,
            @Field(Define.RailServer.LawyerProfileFiled.CARD_NUMBER) String cardNumber,
            @Field(Define.RailServer.LawyerProfileFiled.CERTIFICATE) String certificate,
            @Field(Define.RailServer.LawyerProfileFiled.EDUCATION) String education,
            @Field(Define.RailServer.LawyerProfileFiled.INTRO) String intro,
            @Field(Define.RailServer.LawyerProfileFiled.EXP) String exp,
            @Field(Define.RailServer.LawyerProfileFiled.WORK_PLACE) String workPlace
    );

    @FormUrlEncoded
    @PATCH(Define.RailServer.API_UPDATE_USER)
    Call<UserInfoResponse.UserInfo> checkToken(
            @Path("username") String username,
            @Header(Define.RailServer.X_USER_TOKEN) String userToken,
            @Header(Define.RailServer.X_USER_EMAIL) String email,
            @Field("users[status]") String status
    );

    @GET(Define.RailServer.API_GET_TASK)
    Call<TaskResponse> getTask(
            @Path("lawyer_id") String lawyerId,
            @Header(Define.RailServer.X_USER_TOKEN) String userToken,
            @Header(Define.RailServer.X_USER_EMAIL) String email
    );

    @FormUrlEncoded
    @POST(Define.RailServer.API_CREATE_NEW_TASK)
    Call<CreateTaskResponse> createNewTask(
            @Path("room_id")String roomId,
            @Header(Define.RailServer.X_USER_TOKEN) String userToken,
            @Header(Define.RailServer.X_USER_EMAIL) String userEmail,
            @Field(Define.RailServer.CreateTask.CONTENT) String content
    );

    @FormUrlEncoded
    @PATCH(Define.RailServer.API_EDIT_TASK)
    Call<CreateTaskResponse>editTask(
            @Path("room_id")String roomId,
            @Path("task_id")String taskId,
            @Header(Define.RailServer.X_USER_TOKEN) String userToken,
            @Header(Define.RailServer.X_USER_EMAIL) String userEmail,
            @FieldMap Map<String, String> map
            );
    @DELETE(Define.RailServer.API_DELETE_TASK)
    Call<CreateTaskResponse> deleteTask(
            @Path("room_id") String roomId,
            @Path("task_id")String taskId,
            @Header(Define.RailServer.X_USER_TOKEN) String userToken,
            @Header(Define.RailServer.X_USER_EMAIL) String userEmail
    );

    @GET(Define.RailServer.API_SEARCH_ARTICLES)
    Call<SearchLawResponse> searchLaw(
            @Query("query") String keyword
    );
    @GET(Define.RailServer.API_ARTICLE_DETAIL)
    Call<ArticleDetailResponse> getArticleDetail(
            @Path("articleId") String articleId
    );
}
