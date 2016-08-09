package com.magneto.service.messenger.dto;

/**
 * Created by Vazgen on 08-Apr-16.
 */
public class MessagRecived extends MessageDto {
    public int getFromUserId() {
        return fromUserId;
    }

    public MessagRecived setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    private int fromUserId;
}
