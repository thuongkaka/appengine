package com.gcp.bot.model;


/**
 * Created by lunar on 12/12/2016.
 */
public class Event {
    private String replyToken;
    private String type;
    private long timestamp;
    private Source source;
    private Message message;
    private Postback postback;
    private Beacon beacon;

    public String getReplyToken() {
        return replyToken;
    }

    public void setReplyToken(String replyToken) {
        this.replyToken = replyToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Postback getPostback() {
        return postback;
    }

    public void setPostback(Postback postback) {
        this.postback = postback;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public Event() {

    }

    private class Source {
        private String type;
        private String userId;
        private String groupId;
        private String roomId;

        public Source() {

        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }
    }

    private class Message {
        /*
        * Image / Video / Audio
        */
        private String id;
        private String type;

        /*
        * Text
        */
        private String text;

        /*
        * Location
        */
        private String title;
        private String address;
        private double latitude;
        private double longitude;

        /*
        * Sticker
        */
        private String packageId;
        private String stickerId;

        public Message() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getPackageId() {
            return packageId;
        }

        public void setPackageId(String packageId) {
            this.packageId = packageId;
        }

        public String getStickerId() {
            return stickerId;
        }

        public void setStickerId(String stickerId) {
            this.stickerId = stickerId;
        }
    }

    private class Postback {
        private String data;

        public Postback() {

        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    private class Beacon {
        private String hwid;
        private String type;

        public Beacon() {

        }

        public String getHwid() {
            return hwid;
        }

        public void setHwid(String hwid) {
            this.hwid = hwid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
