package logic;

import java.util.ArrayList;

public abstract class Controller {

	protected ArrayList<String> input = new ArrayList<String>();

	public ArrayList<String> getInput() {
		return input;
	}

	public abstract void setUpController();

	public abstract void handleInput();

}
