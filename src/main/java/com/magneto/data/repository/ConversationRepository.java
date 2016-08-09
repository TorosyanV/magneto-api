package com.magneto.data.repository;

/**
 * Created by JW on 2/18/16.
 */
public interface ConversationRepository {
    void sendMessage(int user1, int user2, String text);
}
