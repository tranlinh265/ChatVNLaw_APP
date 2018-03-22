package com.lkbcteam.tranlinh.chatvnlaw.other;

/**
 * Created by tranlinh on 08/02/2018.
 */

public class Define {

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
}
