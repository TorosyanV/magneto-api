package com.magneto.data.dataobject;

public enum ConnectionStatus {

	PENDING(0), CONNECTED(1), REJECTED(2), DISCONNECTED(3),SENT(4), RECEIVED(5), UNKNOWN(6);

	private int state;

	private ConnectionStatus(final int state) {
		this.state = state;
	}

	private ConnectionStatus() {

	}

	public int getState() {
		return this.state;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public String getName() {
		return this.name();
	}

}
