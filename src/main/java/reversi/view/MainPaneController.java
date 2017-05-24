package reversi.view;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import reversi.IO.IOHandler;
import reversi.Main;
import reversi.Core;

import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MainPaneController {
	private Main main;

	public MainPaneController() {

	}

	public MainPaneController(Main main) {
		this.main = main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	Core c = new Core();

	@FXML
	private void handleNewGame() {
		this.main.createPlayerNamesPane(c);
	}

	@FXML
	private void handleLoadGame() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Game");
		File eleresiUt = fileChooser.showOpenDialog(main.getPrimaryStage());

		try {
			this.c = IOHandler.LoadGame(eleresiUt);
			this.main.createTable(this.c);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void handleSaveGame() {
		try {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Save Game");
			File eleresiUt = directoryChooser.showDialog(main.getPrimaryStage());
			IOHandler.SaveGame(eleresiUt, c);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	public void initData(Core core) {
		this.c = core;

	}

}
