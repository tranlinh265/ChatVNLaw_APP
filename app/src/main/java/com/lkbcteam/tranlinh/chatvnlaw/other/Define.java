package com.lkbcteam.tranlinh.chatvnlaw.other;

/**
 * Created by tranlinh on 08/02/2018.
 */

public class Define {

    public static final String DEFAULT_AVATAR = "https://image.ibb.co/i23jUF/default_ava.png";

    public interface RailServer{
        String TEST_URL = "http://vnlawapi.datalab.vn/";
        String RELEASE_URL = "";
        boolean USE_TEST = true;
        String BASE_API_URL = USE_TEST ? TEST_URL : RELEASE_URL;
        String X_API_KEY = "b1c7f840acdee887f402236e82736eba";

        String API_LOGIN_URL = "api/login";
        String API_SIGNUP_URL = "api/signup";
        String API_LOGOUT_URL = "api/logout";
        String API_GET_ROOM_URL = "api/rooms";
        String API_GET_USER_INFO = "api/users/{username}";
        String API_GET_ROOM_FILE_LIST = "/api/rooms/{roomId}/room_files/";
        String API_GET_PROFILE_DATA = "/api/lawyers/{username}";
        String API_UPDATE_LAWYER_INFO = "/api/lawyers/{username}";
        String API_UPDATE_USER = "/api/users/{username}";
        String API_GET_TASK = "/api/lawyers/{lawyer_id}/tasks";
        String API_CREATE_NEW_TASK = "/api/rooms/{room_id}/tasks";
        String API_EDIT_TASK = "/api/rooms/{room_id}/tasks/{task_id}";
        String API_DELETE_TASK = "/api/rooms/{room_id}/tasks/{task_id}";
        String API_SEARCH_ARTICLES = "/api/search/articles";
        String API_ARTICLE_DETAIL = "/api/articles/{articleId}";

        String X_API_TOKEN_KEY = "x-api-token";
        String X_USER_TOKEN = "x-user-token";
        String X_USER_EMAIL = "x-user-email";

        interface LoginField{
            String LOGIN = "login";
            String EMAIL = LOGIN+"[email]";
            String password = LOGIN+"[password]";
        }

        interface SignupField{
            String SIGNUP = "signup";
            String ID = SIGNUP + "[id]";
            String EMAIL = SIGNUP + "[email]";
            String PASSWORD = SIGNUP + "[password]";
            String PASSWORD_CONFIRM = SIGNUP + "[password_confirmation]";
            String DISPLAYNAME = SIGNUP + "[profile_attributes][displayName]";
            String USERNAME = SIGNUP + "[profile_attributes][userName]";
            String ROLE = SIGNUP + "[user_role_attributes][role_id]";
        }

        interface LawyerProfileFiled{
            String BIRTHDAY ="lawyers[profile_attributes][birthday]";
            String DISPLAY_NAME = "lawyers[profile_attributes][displayName]";
            String ACHIEVEMENT = "lawyers[achievement]";
            String CARD_NUMBER = "lawyers[cardNumber]";
            String CERTIFICATE = "lawyers[certificate]";
            String EDUCATION = "lawyers[education]";
            String INTRO = "lawyers[intro]";
            String EXP = "lawyers[exp]";
            String WORK_PLACE = "lawyers[workPlace]";
        }
        interface CreateTask{
            String CONTENT = "tasks[content]";
        }
        interface EditTask{
            String CONTENT = "tasks[content]";
            String STATUS = "tasks[status]";
        }
    }
    public interface Pubnub{
        String SHARED_PREFS = "com.lkbcteam.tranlinh.chatvnlaw.SHARED_PREFS";
        String USER_NAME    = "com.lkbcteam.tranlinh.chatvnlaw.USERNAME";
        String CALL_USER    = "com.lkbcteam.tranlinh.chatvnlaw.CALLUSER";

        String STDBY_SUFFIX = "-stdby";
//      String PUB_KEY = "pub-c-41480199-8449-4c3a-a44a-d29a395ba540"; // Use Your Pub Key
//      String SUB_KEY = "sub-c-22984fba-0c86-11e8-bb84-266dd58d78d1"; // Use Your Sub Key

//        String PUB_KEY = "pub-c-daeb24b3-d3b6-459e-b376-e217c45078f4"; // Use Your Pub Key
//        String SUB_KEY = "sub-c-696c546a-16b1-11e8-bb84-266dd58d78d1"; // Use Your Sub Key
        String PUB_KEY = "pub-c-9d0d75a5-38db-404f-ac2a-884e18b041d8"; // Use Your Pub Key
        String SUB_KEY = "sub-c-4e25fb64-37c7-11e5-a477-0619f8945a4f"; // Use Your Sub Key
        String JSON_CALL_USER = "call_user";
        String CAMERA_MODE = "camera_mode";
        String CAMERA_MODE_FRONT = "camera_front";
        String CAMERA_MODE_BACK = "camera_back";
        String LOG_TAG = "idk";
        String DIALED = "dialed";
    }
    public interface Api{
        String BASE_URL = "http://vnlawapi.datalab.vn";
        String SEARCH_LAWYER = "/api/search/lawyers";
        String ALL_LAWYERS = "api/lawyers";
        String TOP_LAWYERS = "api/lawyers/top";
        String LAWYER_NAME = "api/lawyers/names";
    }
    public interface  Table{
         String TABLE_ROOMS = "rooms";
         String TABLE_REFERENCE = "reference";
         String TABLE_NOTIFICATIONS = "notifications";
         String TABLE_MONEY_ACCOUNT = "moneyAccount";
         String TABLE_LAWYERS = "lawyers";
         String TABLE_USERS = "users";
         String TABLE_TASKS = "tasks";
         String TABLE_ROLES = "roles";
         String TABLE_NOTES = "notes";

    }
    public interface  Balance {
         String AMOUNT= "amount";
    }
    public interface  Role {
         String USER = "user";
         String lawyer = "lawyer";
    }
    public interface  Notifications{
         String ID = "id";
         String SENDER = "sender";
         String SENDER_DISPLAYNAME = " displayName";
         String SENDER_ROLE = "role";
         String SENDER_ID = "uid";
         String TYPE = "type";
        //  value=
        //    "requestRoom"= when ask lawyer;
        //    "acceptRoom"= when lawyer accepted and create new room
         String INFO = "information";
         String TIMESTAMP = "timeStamp";
    }
    public interface  NotificationType{
         String REQUEST_ROOM = "requestRoom";
         String ACCEPT_ROOM_REQUEST = "acceptRoomRequest";
         String REFUSE_ROOM_REQUEST = "refuseRoomRequest";
    }
    public interface  User{
         String USER_ID = "uid";
         String DISPLAY_NAME = "displayName";
         String EMAIL = "email";
         String IS_DELETED = "isDeleted";
         String PHOTOURL = "photoUrl";
         String ROLE = "role";
         String STATUS = "status";
         String USERNAME = "username";
         String TOKEN = "deviceToken";
    }
    public interface  Room{
        String MESSAGES = "messages";
         String ROOM_ID = "rid";
         String UN_READ_MESSAGE = "unReadMessage";
         String DESCRIPTION = "description";
         String SHARED_IMAGES = "sharedImages";
         String SHARED_FILES = "sharedFiles";
         String VIDEO_CALL = "videoCall";
         String CHAT_SESSION = "chatSession";
    }

    public interface  Members{
         String LAYER = "lawyer";
         String CUSTOMER = "customer";
    }

    public interface  Messages {
         String MESSAGE_ID = "mid";
         String TIMESTAMP = "msgTimeStamp";
         String SENDERID = "senderUid";
         String CONTENT = "content";
         String TAGS = "tags";
         String INFO = "info";
    }

    public interface  UnreadMessage {
        String CONTENT = "content";
         String COUNT = "count";
         String LASTMESSAGE = "lastMessage";
         String TIMESTAMP = "msgTimeStamp";
         String RECEIVER_ID = "receiverUid";
         String SENDER_ID = "senderUid";
    }

    public interface  SharedFiles{
         String CONTENT_TYPE = "contentType";
         String DOWNLOAD_URL = "downloadURL";
         String HEIGHT = "height";
         String WIDTH = "width";
         String NAME = "name";
         String SENDER_ID = "senderUid";
         String SIZE = "size";
         String TIMESTAMP = "filesTimeStamp";
    }
    public interface  LawyerInfo{
         String ACHIEVEMENT = "achievement";
         String ADDRESS = "address";
         String AREA = "area";
         String BIRTHDAY = "birthday";
         String CARDNUMBER = "cardNumber";
         String CATEGORY = "category";
         String CERTIFICATE= "certificate";
         String EDUCATION= "education";
         String EXP = "exp";
         String FULLNAME = "fullname";
         String INTRO= "intro";
         String PHONENUMBER= "phoneNumber";
         String PRICE= "price";
         String RATE = "rate";
         String RATESUM= "rateSum";
         String WORKPLACE= "workPlace";
    }
    public interface  VideoCall{
         String REQUEST= "request";
         String STREAM= "stream";
         String END= "end";
         String CANCELREQUEST= "cancel";
    }

    public interface Notice{
        String UPDATE_SUCCESS = "Cập nhật thành công.";
        String ERROR_INVALID_TOKEN = "Đăng nhập quá hạn, xin vui lòng đăng nhập lại.";
        String UPDATE_FALURE = "Cập nhật không thành công, vui lòng thử lại sau.";
        String LAWYER_ONLY = "Chức năng chỉ dành riêng cho luật sư.";
        String WELCOME = "Chào mừng ";
    }
    public enum Specializes{
        HINH_SU("Hình sự"),
        SO_HUU_TRI_TUE("Sở hữu trí tuệ"),
        HON_NHAN_GIA_DINH("Hôn nhân & gia đình"),
        NHA_DAT_XAY_DUNG("Nhà đất - Xây dựng"),
        TAI_CHINH_NGAN_HANG("Tài chính - Ngân hàng"),
        DAN_SU("Dân sự"),
        LAO_DONG_BAO_HIEM("Lao động - Bảo hiểm xã hội"),
        DOANH_NGHIEP("Doanh nghiệp");

        private String value;

        Specializes(String s) {
            this.value = s;
        }

        public String getValue() {
            return value;
        }

        public static int convertToIntValue(String s){
            if(s.toLowerCase().equals(HINH_SU.getValue().toLowerCase()))
                return 1;
            if(s.toLowerCase().equals(SO_HUU_TRI_TUE.getValue().toLowerCase()))
                return 2;
            if(s.toLowerCase().equals(HON_NHAN_GIA_DINH.getValue().toLowerCase()))
                return 3;
            if(s.toLowerCase().equals(NHA_DAT_XAY_DUNG.getValue().toLowerCase()))
                return 4;
            if(s.toLowerCase().equals(TAI_CHINH_NGAN_HANG.getValue().toLowerCase()))
                return 5;
            if(s.toLowerCase().equals(DAN_SU.getValue().toLowerCase()))
                return 6;
            if(s.toLowerCase().equals(LAO_DONG_BAO_HIEM.getValue().toLowerCase()))
                return 7;
            if(s.toLowerCase().equals(DOANH_NGHIEP.getValue().toLowerCase()))
                return 8;
            return -1;
        }
    }
}
