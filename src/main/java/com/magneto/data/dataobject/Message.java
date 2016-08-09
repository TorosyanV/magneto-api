package com.magneto.data.dataobject;

import java.util.Date;

import com.magneto.data.entity.ConversationEntity;
import com.magneto.data.entity.ConversationReplyEntity;

public class Message {

	private String text;
	private Date date;
	private boolean seen;
	private int senderId;

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public Message(ConversationEntity conversation) {
		super();
		this.text = conversation.getConversationReply().getReplyText();
		this.date = conversation.getDate();
		this.senderId=conversation.getUserOne().getId();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	

	private boolean mine;
}
