package Recipe;

public class Step {
	
	int time;
	String description;
	
	public Step(int time, String description) {
		this.time = time;
		this.description = description;
	}
	
	public String toString() {
		return time + " min: " + description;
	}
}
