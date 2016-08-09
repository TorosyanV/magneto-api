package com.magneto.web.viewmodel.messenger;

/**
 * Created by vazgen on 2/20/16.
 */
public class UserMessage {


    private int toUserId;
    private String text;

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "toUserId=" + toUserId +
                ", text='" + text + '\'' +
                '}';
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
