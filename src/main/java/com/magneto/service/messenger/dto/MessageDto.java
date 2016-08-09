package com.magneto.service.messenger.dto;

import java.util.Date;

import com.magneto.data.entity.ConversationEntity;

public class MessageDto {

	private String text;
	private Date date;
	private boolean seen;

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

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	private boolean mine;
}
