package recipe;

import system.data.Data;

public class Step extends Data<Step> {
	
	private int time;
	private String description;
	
	public Step(int time, String description) {
		this.time = time;
		this.description = description;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Step) && (((Step) o).description == description) && (((Step) o).time == time);
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
