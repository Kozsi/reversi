package reversi.view;

import reversi.Core;
import reversi.Main;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateTableController {

	private Stage stage;

	public void setMain(Main main) {
	}

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private Text player1N;
	@FXML
	private Text player2N;
	@FXML
	private TextField player1Score;
	@FXML
	private TextField player2Score;
	@FXML
	private AnchorPane leftPane;
	@FXML
	private AnchorPane rightPane;
	@FXML
	private TextArea log;

	@FXML
	private void handlePut(MouseEvent event) {
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
		//Date date = new Date();
		//System.out.println(dateFormat.format(date)); 
		// Circle circle = new
		// Circle(MouseInfo.getPointerInfo().getLocation().getX(),MouseInfo.getPointerInfo().getLocation().getY(),30);
		// circle.setFill(Paint.valueOf("RED"));
		// proba.getChildren().add(circle);
		// System.out.println("asd1");
		double x = event.getSceneX();
		// MouseInfo.getPointerInfo().getLocation().getX();
		double y = event.getSceneY();
		// MouseInfo.getPointerInfo().getLocation().getY();
		System.out.println(x + " " + y);

		int elemx, elemy;
		elemx = (int) ((x - 89) / 60);
		elemy = (int) ((y - 146) / 60);
		int moved;

		System.out.println(elemx + " " + elemy);

		int player = this.c.getCurrentPlayer();

		if (c.isShearPossible(player)) {
			moved = c.processShears(player, elemx, elemy);
			if (moved > 0) {
				c.processShears(player, elemx, elemy);
				this.updateInfo("Player" + (player == 2 ? " Black" : " White") + ". is next!");
				this.c.setCurrentPlayer(3 - player);
				renderTable(this.c);
			} else {
				this.updateInfo("Wrong Move!" + "\n" + log.getText());

			}
		}
		if (this.c.isItEnd() == 1) {
			String winner;
			if (this.c.getBlack() > this.c.getWhite()) {
				winner = this.c.getBlackName();
			} else {
				winner = this.c.getWhiteName();
			}
			this.updateInfo("End of the Game! \nThe winner is Player: " + winner);
		}

	}

	Core c = new Core();

	public void updateInfo(String logStr) {
		log.setText(logStr);
		player1Score.setText("" + this.c.getBlack());
		player2Score.setText("" + this.c.getWhite());
	}

	public void renderTable(Core core) {
		leftPane.getChildren().clear();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Rectangle rectangle = new Rectangle(i * 60, j * 60, 60, 60);
				rectangle.setFill(Paint.valueOf("WHITE"));
				rectangle.setStroke(Paint.valueOf("BLACK"));
				leftPane.getChildren().add(rectangle);
				Circle circle = new Circle(i * 60 + 30, j * 60 + 30, 30);

				switch (core.getTable()[i][j]) {
				case 1:
					circle.setFill(Paint.valueOf("BLACK"));
					leftPane.getChildren().add(circle);
					break;
				case 2:
					circle.setFill(Paint.valueOf("WHITE"));
					circle.setStroke(Paint.valueOf("BLACK"));
					leftPane.getChildren().add(circle);
					break;

				default:
					break;
				}
			}
		}

	}

	public void initData(Core core) {
		this.c = core;
		player1N.setText(c.getBlackName());
		player2N.setText(c.getWhiteName());
		this.updateInfo("Player" + (this.c.getCurrentPlayer() == 1 ? " Black" : " White") + ". is next!");
		renderTable(this.c);

	}

}
