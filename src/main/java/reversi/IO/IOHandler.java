package reversi.IO;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import reversi.Core;

public class IOHandler {

	public IOHandler() {

	}

	public static void SaveGame(File kimenetEleres, Core core)
			throws ParserConfigurationException, TransformerException {
		System.out.println("ki:" + kimenetEleres.getAbsolutePath());
		DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();

		Document doc = dBuilder.newDocument();

		Element root = doc.createElement("Game");
		doc.appendChild(root);

		Element Game = doc.createElement("Board");
		root.appendChild(Game);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Element coordinate = doc.createElement("coor-" + i + "-" + j);
				coordinate.appendChild(doc.createTextNode("" + core.getTable()[i][j]));
				Game.appendChild(coordinate);
			}
		}
		Element Black = doc.createElement("Black");
		Element BlackName = doc.createElement("Black-name");
		Element Blackpoint = doc.createElement("Black-point");
		BlackName.appendChild(doc.createTextNode("" + core.getBlackName()));
		Blackpoint.appendChild(doc.createTextNode("" + core.getBlack()));
		Black.appendChild(BlackName);
		Black.appendChild(Blackpoint);
		root.appendChild(Black);

		Element White = doc.createElement("White");
		Element WhiteName = doc.createElement("White-name");
		Element Whitepoint = doc.createElement("White-point");
		WhiteName.appendChild(doc.createTextNode("" + core.getWhiteName()));
		Whitepoint.appendChild(doc.createTextNode("" + core.getWhite()));
		White.appendChild(WhiteName);
		White.appendChild(Whitepoint);
		root.appendChild(White);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String nameoffile = dateFormat.format(date);
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(kimenetEleres, nameoffile+".xml"));

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(3));
		transformer.transform(source, result);
	}

	public static Core LoadGame(File kimenetEleres) throws SAXException, IOException, ParserConfigurationException {
		System.out.println("be:" + kimenetEleres.getAbsolutePath());
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(kimenetEleres);

		Core core = new Core();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				core.getTable()[i][j] = Integer
						.parseInt(document.getElementsByTagName("coor-" + i + "-" + j).item(0).getTextContent());
			}
		}
		core.setBlack(Integer.parseInt(document.getElementsByTagName("Black-point").item(0).getTextContent()));
		core.setWhite(Integer.parseInt(document.getElementsByTagName("White-point").item(0).getTextContent()));
		core.setBlackName(document.getElementsByTagName("Black-name").item(0).getTextContent());
		core.setWhiteName(document.getElementsByTagName("White-name").item(0).getTextContent());
		return core;

	}
}
