package com.magneto.data.entity;

import javax.persistence.*;
import javax.swing.text.StringContent;
import javax.xml.soap.Text;

/**
 * Created by JW on 2/18/16.
 */
@Entity
@Table(name = "CONVERSATION_REPLY")
public class ConversationReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;


    @Column(name = "REPLY_TEXT", nullable = false)
    private String replyText;

    @OneToOne
    @JoinColumn(name="USER_ID",  nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey (name = "FK_USER_ID"))
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "CONVERSATION_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey (name = "FK_CONVERSATION_ID"))
    private ConversationEntity conversation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ConversationEntity getConversation() {
        return conversation;
    }

    public void setConversation(ConversationEntity conversation) {
        this.conversation = conversation;
    }
}
