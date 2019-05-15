package sprite;

import logic.Main;

public abstract class Star extends Sprite implements Item {

	public Star(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
	}
	
	public static void generateStar(Sprite source) {
		int quantity = (int) (Math.random() * 10);
		for (int i = 0; i < quantity; i++) {
			double positionX = source.positionX + (Math.random() * source.getWidth());
			double positionY = source.positionY + (Math.random() * source.getHeight());
			double randomNumber = Math.random();
			if (randomNumber < 0.01) {
				BigStar bigStar = new BigStar(positionX, positionY, -200, 0);
				BigStar.getBigStarList().add(bigStar);
			}
			else if (randomNumber < 0.1) {
				MediumStar mediumStar = new MediumStar(positionX, positionY, -200, 0);
				MediumStar.getMediumStarList().add(mediumStar);
			}
			else {
				SmallStar smallStar = new SmallStar(positionX, positionY, -200, 0);
				SmallStar.getSmallStarList().add(smallStar);
			}
		}
	}
	
	@Override
	public Sprite checkCollide() {
		if (this.intersects(Main.inGameScreen.inGameTimer.player) && Main.inGameScreen.inGameTimer.player.isVisible()) {
			return Main.inGameScreen.inGameTimer.player;
		}
		return null;
	}
	
}
