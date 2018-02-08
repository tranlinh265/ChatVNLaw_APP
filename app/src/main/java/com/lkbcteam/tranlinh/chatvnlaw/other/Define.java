package com.lkbcteam.tranlinh.chatvnlaw.other;

/**
 * Created by tranlinh on 08/02/2018.
 */

public class Define {
    public static class Table{
        public static String TABLE_ROOMS = "rooms";
        public static String TABLE_REFERENCE = "reference";
        public static String TABLE_NOTIFICATIONS = "notifications";
        public static String TABLE_MONEY_ACCOUNT = "moneyAccount";
        public static String TABLE_LAWYERS = "lawyers";
        public static String TABLE_USERS = "users";
        public static String TABLE_TASKS = "tasks";
        public static String TABLE_ROLES = "roles";
        public static String TABLE_NOTES = "notes";
    }
    public static class Balance {
        public static String AMOUNT= "amount";
    }
    public static class Role {
        public static String USER = "user";
        public static String lawyer = "lawyer";
    }
    public static class Notifications{
        public static String ID = "id";
        public static String SENDER = "sender";
        public static String SENDER_DISPLAYNAME = " displayName";
        public static String SENDER_ROLE = "role";
        public static String SENDER_ID = "uid";
        public static String TYPE = "type";
        //  value=
        //    "requestRoom"= when ask lawyer;
        //    "acceptRoom"= when lawyer accepted and create new room
        public static String INFO = "information";
        public static String TIMESTAMP = "timeStamp";
    }
    public static class NotificationType{
        public static String REQUEST_ROOM = "requestRoom";
        public static String ACCEPT_ROOM_REQUEST = "acceptRoomRequest";
        public static String REFUSE_ROOM_REQUEST = "refuseRoomRequest";
    }
    public static class User{
        public static String USER_ID = "uid";
        public static String DISPLAY_NAME = "displayName";
        public static String EMAIL = "email";
        public static String IS_DELETED = "isDeleted";
        public static String PHOTOURL = "photoUrl";
        public static String ROLE = "role";
        public static String STATUS = "status";
        public static String USERNAME = "username";
    }
    public static class Room{
        public static String ROOM_ID = "rid";
        public static String UN_READ_MESSAGE = "unReadMessage";
        public static String DESCRIPTION = "description";
        public static String SHARED_IMAGES = "sharedImages";
        public static String SHARED_FILES = "sharedFiles";
        public static String VIDEO_CALL = "videoCall";
        public static String CHAT_SESSION = "chatSession";
    }

    public static class Members{
        public static String LAYER = "lawyer";
        public static String CUSTOMER = "customer";
    }

    public static class Messages {
        public static String MESSAGE_ID = "mid";
        public static String TIMESTAMP = "msgTimeStamp";
        public static String SENDERID = "senderUid";
        public static String CONTENT = "content";
        public static String TAGS = "tags";
    }

    public static class UnreadMessage {
        public static String COUNT = "count";
        public static String LASTMESSAGE = "lastMessage";
        public static String TIMESTAMP = "msgTimeStamp";
        public static String RECEIVER_ID = "receiverUid";
        public static String SENDER_ID = "senderUid";
    }

    public static class SharedFiles{
        public static String CONTENT_TYPE = "contentType";
        public static String DOWNLOAD_URL = "downloadURL";
        public static String HEIGHT = "height";
        public static String WIDTH = "width";
        public static String NAME = "name";
        public static String SENDER_ID = "senderUid";
        public static String SIZE = "size";
        public static String TIMESTAMP = "filesTimeStamp";
    }
    public static class LawyerInfo{
        public static String ACHIEVEMENT = "achievement";
        public static String ADDRESS = "address";
        public static String AREA = "area";
        public static String BIRTHDAY = "birthday";
        public static String CARDNUMBER = "cardNumber";
        public static String CATEGORY = "category";
        public static String CERTIFICATE= "certificate";
        public static String EDUCATION= "education";
        public static String EXP = "exp";
        public static String FULLNAME = "fullname";
        public static String INTRO= "intro";
        public static String PHONENUMBER= "phoneNumber";
        public static String PRICE= "price";
        public static String RATE = "rate";
        public static String RATESUM= "rateSum";
        public static String WORKPLACE= "workPlace";
    }
    public static class VideoCall{
        public static String REQUEST= "request";
        public static String STREAM= "stream";
        public static String END= "end";
        public static String CANCELREQUEST= "cancel";
    }
}
