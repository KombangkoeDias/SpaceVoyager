package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class BigStar extends Star {
	
	public static final int SCORE = 1000;
	private static ArrayList<BigStar> bigStarList = new ArrayList<BigStar>();
	
	public BigStar(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		setImage(Main.loader.bigStarImage);
	}
	
	@Override
	public void disappear() {
		BigStar bigStar = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				bigStarList.remove(bigStarList.indexOf(bigStar));
			}
		});
	}

	public static ArrayList<BigStar> getBigStarList() {
		return bigStarList;
	}

}
