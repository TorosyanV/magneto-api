package com.magneto.data.entity;

/**
 * Created by JW on 2/18/16.
 */

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CONVERSATION")
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;


    @OneToOne
    @JoinColumn(name = "USER_ONE_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_ONE_USER_ID")
    )
    private UserEntity userOne;

    @OneToOne(optional=true)
    @JoinColumn( name = "USER_TWO_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_TWO_USER_ID"))
    private UserEntity userTwo;

    @Column(name = "DATE")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "conversation")
    private ConversationReplyEntity conversationReply;

    public ConversationReplyEntity getConversationReply() {
        return conversationReply;
    }

    public void setConversationReply(ConversationReplyEntity conversationReply) {
        this.conversationReply = conversationReply;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUserOne() {
        return userOne;
    }

    public void setUserOne(UserEntity userOne) {
        this.userOne = userOne;
    }

    public UserEntity getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(UserEntity userTwo) {
        this.userTwo = userTwo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
