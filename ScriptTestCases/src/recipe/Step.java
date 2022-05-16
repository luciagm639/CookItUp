package recipe;

public class Step {
	
	private int time;
	private String description;
	
	public Step(int time, String description) {
		this.time = time;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return time + " min: " + description;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
