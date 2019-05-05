package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class MediumStar extends Star {
	
	public static final int SCORE = 1000;
	private static ArrayList<MediumStar> mediumStarList = new ArrayList<MediumStar>();
	
	public MediumStar(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		this.image = Main.loader.mediumStarImage;
	}
	
	@Override
	public void disappear() {
		MediumStar mediumStar = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mediumStarList.remove(mediumStarList.indexOf(mediumStar));
			}
		});
	}

	public static int getScore() {
		return SCORE;
	}

	public static ArrayList<MediumStar> getMediumStarList() {
		return mediumStarList;
	}

}
