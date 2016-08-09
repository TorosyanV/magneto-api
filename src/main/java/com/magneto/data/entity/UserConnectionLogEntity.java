package com.magneto.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERCONNECTION_LOG")
public class UserConnectionLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	 @OneToOne
	    @JoinColumn(name = "ACTOR_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ACTOR_ID_APP_USER_ID")
	    )

	private UserEntity actor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public UserEntity getActor() {
		return actor;
	}

	public void setActor(UserEntity actor) {
		this.actor = actor;
	}

	public UserEntity getActed() {
		return acted;
	}

	public void setActed(UserEntity acted) {
		this.acted = acted;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	 @OneToOne
	    @JoinColumn(name = "ACTED_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ACTED_ID_APP_USER_ID")
	    )
	private UserEntity acted;
	@Column(name = "ACTION_DATE")
	private Date actionDate;

}
