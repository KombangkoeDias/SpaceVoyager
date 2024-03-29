package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class MediumStar extends Star {
	
	public static final int SCORE = 500;
	private static ArrayList<MediumStar> mediumStarList = new ArrayList<MediumStar>();
	
	public MediumStar(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		setImage(Main.loader.mediumStarImage);
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
	
	public static ArrayList<MediumStar> getMediumStarList() {
		return mediumStarList;
	}

}
