package es.deusto.bspq21e1.serialization;

public class DirectedMessage {

    private UserData userData;
    private MessageData messageData;

    public DirectedMessage() {

    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public void setMessageData(MessageData messageData) {
        this.messageData = messageData;
    }

    public MessageData getMessageData() {
        return this.messageData;
    }
}