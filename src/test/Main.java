package reversi;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;


import reversi.view.PlayerNamePaneController;
import reversi.view.CreateTableController;
import reversi.view.MainPaneController;
import reversi.Table;
import reversi.Core;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	
	public Main(){

	}
	
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Game");
		createMainPane();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void createMainPane(Table table){		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/MainPane.fxml"));
			mainPane = (BorderPane) loader.load();
			
			Scene scene = new Scene(mainPane);
			
			MainPaneController controller = loader.getController();
			controller.setMain(this);
			primaryStage.setScene(scene);
			primaryStage.show();	
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createPlayerNamesPane(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/PlayerNamesPane.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();
			
			mainPane.setCenter(pane);

			PlayerNamePaneController controller = loader.getController();
			
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void createTable(){
	try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/TablePane.fxml"));
		AnchorPane pane;
		pane = (AnchorPane) loader.load();
		
		mainPane.setCenter(pane);

		CreateTableController controller = loader.getController();
		
		controller.setMain(this);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
	
	
	/*public void createTable(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/TablePane.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Tábla kölyök");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(getPrimaryStage());
			
			Scene scene = new Scene(pane);
			stage.setScene(scene);
		
			System.out.println("Itt vagyok!");
			PlayerNamePaneController controller = loader.getController();
			controller.setStage(stage);
			controller.setMain(this);
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/


}
