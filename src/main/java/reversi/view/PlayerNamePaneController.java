package reversi.view;

import reversi.Core;
import reversi.Main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlayerNamePaneController {

	private Stage stage;

	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	public PlayerNamePaneController() {

	}

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	Core c = new Core();

	@FXML
	private TextField player1Field;
	@FXML
	private TextField player2Field;

	@FXML
	private void handleReady() {
		c.setBlackName(player1Field.getText());
		c.setWhiteName(player2Field.getText());

		// System.out.println(t.getBlackName());
		// System.out.println(t.getWhiteName());
		this.main.createTable(c);

	}

	public void initData(Core core) {
		this.c = core;

	}

}
