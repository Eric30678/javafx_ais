package application;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
public class sceneController{
	private Stage stage;
	private Scene scene;
	private Parent parent;
	static String a;
	
//	public void switchtoscene1(ActionEvent e) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
//		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//	}
	public void switchtoscene2(ActionEvent e) throws IOException {
		new read_csvfile();
		Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private Button btn1;
	@FXML
	private Label listview;
	@FXML
	private Button btn2;
	@FXML
	private AnchorPane scenePane;
	
	public void Button1Action(ActionEvent e) {
		FileChooser fc = new FileChooser();
		File f = fc.showOpenDialog(null);
		if (f != null) {
			listview.setText(f.getAbsolutePath());
			a = f.getAbsolutePath();
		}else {
			System.out.println("file is not valid");
		}
	}
	public static String return_a() {
		return a;
	}
	public void ok(ActionEvent e) {
		//			Alert alert = new Alert(AlertType.CONFIRMATION);
		//			alert.setTitle("Logout");
					stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//					System.out.println("you successfully logged out!");
					stage.close();
	}
}
