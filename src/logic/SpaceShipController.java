package logic;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import sprite.PlayerBullet;
import sprite.PlayerSpaceShip;

public class SpaceShipController extends Controller {

	private PlayerSpaceShip player;

	public SpaceShipController(PlayerSpaceShip spaceship) {
		super();
		this.player = spaceship;
	}

	@Override
	public void setUpController() {

		Main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!input.contains(code)) {
					input.add(code);
				}
			}

		});

		Main.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (input.contains(code)) {
					input.remove(code);
				}
			}
		});

	}

	@Override
	public void handleInput() {
		player.velocityX = 0;
		player.velocityY = 0;
		if (player.isControllable()) {
			if (input.contains("UP")) {
				player.addVelocityY(-player.getSpeed());
			}
			if (input.contains("DOWN")) {
				player.addVelocityY(player.getSpeed());
			}
			if (input.contains("LEFT")) {
				player.addVelocityX(-player.getSpeed());
			}
			if (input.contains("RIGHT")) {
				player.addVelocityX(player.getSpeed());
			}
			if (input.contains("SPACE")) {
				player.bulletTimeCount++;
				if (player.bulletTimeCount >= 100 / player.getFireRate()) {
					PlayerBullet bullet = new PlayerBullet(player.positionX + (player.getWidth() / 2),
							player.positionY + (player.getHeight() / 2), 1000, 0, player);
					bullet.positionY -= bullet.getHeight() / 2;
					PlayerBullet.getBulletList().add(bullet);
					player.bulletTimeCount = 0;
				}
			}
		}
		
	}

	public PlayerSpaceShip getSpaceship() {
		return player;
	}

}
