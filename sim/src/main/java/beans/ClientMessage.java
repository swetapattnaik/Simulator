package beans;
public class ClientMessage {
	private String messageId;
	private String systemId;
	private String serviceType;
	private String sourceAddr;
	private String destAddr;
	private String shortMessage;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSourceAddr() {
		return sourceAddr;
	}

	public void setSourceAddr(String sourceAddr) {
		this.sourceAddr = sourceAddr;
	}

	public String getDestAddr() {
		return destAddr;
	}

	public void setDestAddr(String destAddr) {
		this.destAddr = destAddr;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ClientMessage))
			return false;
		ClientMessage msg = (ClientMessage) obj;
		return msg.getMessageId().trim().equals(this.getMessageId().trim() );
	}

	@Override
	public String toString() {
		return "ClientMessage [messageId=" + messageId + ", systemId=" + systemId + ", serviceType=" + serviceType
				+ ", sourceAddr=" + sourceAddr + ", destAddr=" + destAddr + ", shortMessage=" + shortMessage + "]";
	}

}
