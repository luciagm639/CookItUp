package system.data;

import recipe.Step;
import system.MySystem;

public class StepsList extends DataSet<Step> {
	
	public StepsList() {
		super("Steps.txt");
	}

	@Override
	public void readData(MySystem system, String text) {
		String[] sp = split(text);
		
		try {
			int id = Integer.parseInt(sp[0]);
			int time = Integer.parseInt(sp[1]);
			String description = sp[2];
			
			Step step = new Step(time, description);
			add(step, id);
		} catch(NumberFormatException e) {/*We don't add to the system*/}
	}

	@Override
	public String writeData(Step step) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(step.getId());
		sj.add(step.getTime());
		sj.add(step.getDescription());
		
		return sj.toString();
	}
}
