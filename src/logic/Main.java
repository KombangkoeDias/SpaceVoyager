package logic;
import gui.GameOverScreen;
import gui.InGameScreen;

import gui.MainMenu;
import gui.SelectMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static Loader loader = new Loader();
	public static InGameScreen inGameScreen;
	public static Scene scene;
	public static Stage permanentStage = new Stage();
	public static Main main = null;
	public static void setMain(Main main) {
		Main.main = main;
	}
	public Main() {
		setMain(this);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainMenu mainmenu = new MainMenu();
		permanentStage = primaryStage;
		scene = new Scene(mainmenu, WIDTH-15, HEIGHT-15);
		permanentStage.setScene(scene);
		permanentStage.setResizable(false);
		permanentStage.setTitle("Space Voyage");
		permanentStage.show();
	}
	

	public static void goToGame() throws Exception
	{
		inGameScreen = new InGameScreen();
		InGameScreen.inGameTimer.spaceShipController.setUpController();
		scene.setRoot(inGameScreen);
		permanentStage.setScene(scene);
		permanentStage.show();
	}

	public static void gotoSelectMenu()
	{
		SelectMenu selectmenu = new SelectMenu();
		scene.setRoot(selectmenu);
		permanentStage.setScene(scene);
		permanentStage.show();
	}
	public static void gotoScoreBoard() {
		GameOverScreen gameover = new GameOverScreen();
		scene.setRoot(gameover);
		permanentStage.setScene(scene);
		permanentStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
