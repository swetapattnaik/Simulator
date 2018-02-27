package euiccsim;

public class Message {
   
    private String eid;
	private String url;
	private String method; 

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Message))
			return false;
		Message msg = (Message) obj;
		return msg.getEid().trim().equals(this.getEid().trim() );
	}

	@Override
	public String toString() {
		return "Message [eid=" + eid + ", url=" + url + ", method=" + method + "]";
	}
	
	

}