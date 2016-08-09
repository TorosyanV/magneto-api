package com.magneto.data.repository;

import java.util.List;

import com.magneto.data.dataobject.ChaterDetails;
import com.magneto.data.dataobject.Message;
import com.magneto.util.Pagination;

/**
 * Created by vazgen on 2/23/16.
 */
public interface MessengerRepository {

    List<ChaterDetails> getChaters(int userId);
    
    List<Message> getMessages(int userOne, int userTwo, Pagination pagination);
    
}
