package reversi.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorController {
	
	private Stage stage;

	public ErrorController() {

	}

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private Text text;



	public void setText(String uzenet) {
		text.setText(uzenet);
		
	}
	
	@FXML
	private void handleOk() {
		stage.close();
	}

}
