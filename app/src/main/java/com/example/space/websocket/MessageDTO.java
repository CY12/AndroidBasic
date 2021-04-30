package com.example.space.websocket;

public class MessageDTO {
    private String fromUserName;
    private String targetUserName;
    private String message;
    private String messageType;

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public static enum Type {
        TYPE_NEW("0000"), TYPE_TEXT("1000"), TYPE_BYTE("1001");
        private String messageType;

        Type(String messageType) {
            this.messageType = messageType;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

    }
}