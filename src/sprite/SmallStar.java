package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class SmallStar extends Star {
	
	public static final int SCORE = 100;
	private static ArrayList<SmallStar> smallStarList = new ArrayList<SmallStar>();
	
	public SmallStar(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		setImage(Main.loader.smallStarImage);
	}
	
	@Override
	public void disappear() {
		SmallStar smallStar = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smallStarList.remove(smallStarList.indexOf(smallStar));
			}
		});
	}

	public static int getScore() {
		return SCORE;
	}

	public static ArrayList<SmallStar> getSmallStarList() {
		return smallStarList;
	}

}
