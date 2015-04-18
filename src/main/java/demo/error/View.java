package demo.error;

public class View {
	private String type;
	private String status;
	
	public View(String type, String status) {
		this.type = type;
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
