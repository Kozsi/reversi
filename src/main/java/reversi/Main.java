package reversi;

import java.io.IOException;

import reversi.view.PlayerNamePaneController;
import reversi.view.CreateTableController;
import reversi.view.ErrorController;
import reversi.view.MainPaneController;
import reversi.Core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane mainPane;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Main() {

	}

	Core c = new Core();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Game");
		createMainPane(c);

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void createMainPane(Core core) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/MainPane.fxml"));
			mainPane = (BorderPane) loader.load();

			Scene scene = new Scene(mainPane);

			MainPaneController controller = loader.getController();
			controller.initData(core);
			controller.setMain(this);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createPlayerNamesPane(Core core) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/PlayerNamesPane.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();

			mainPane.setCenter(pane);

			PlayerNamePaneController controller = loader.getController();
			controller.initData(core);
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void createTable(Core core) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/TablePane.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();

			mainPane.setCenter(pane);

			CreateTableController controller = loader.getController();
			controller.initData(core);

			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Error(String uzenet){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/Error.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Hiba");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(getPrimaryStage());
			
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			
			ErrorController controller = loader.getController();
			controller.setStage(stage);
			controller.setText(uzenet);
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
