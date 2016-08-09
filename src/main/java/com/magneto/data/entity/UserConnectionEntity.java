package com.magneto.data.entity;

import com.magneto.data.dataobject.ConnectionStatus;

import javax.persistence.*;

@Entity
@Table(name = "USERCONNECTION", uniqueConstraints = @UniqueConstraint(columnNames = { "USER_ONE_ID", "USER_TWO_ID" }) )
public class UserConnectionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

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

	public ConnectionStatus getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(ConnectionStatus connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	@OneToOne
	@JoinColumn(name = "USER_ONE_ID", nullable = false, referencedColumnName = "ID",  foreignKey = @ForeignKey(name = "FK_USER_ONET_APP_USER_ID") )
	private UserEntity userOne;

	@OneToOne
	@JoinColumn(name = "USER_TWO_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_TWOT_APP_USER_ID") )
	private UserEntity userTwo;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "CONNECTION_STATUS")
	private ConnectionStatus connectionStatus;

}
