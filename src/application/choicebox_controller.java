package application;

import application.sqlite_test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
//import javafx.scene.control.Label;

public class choicebox_controller implements Initializable{
	
//	private Label scenePane;
	
	@FXML
	private ChoiceBox<String> myChoiceBox;
//	@FXML
//	private Button btn2;
	@FXML
	private DatePicker datepicker;
//	private String[] food = {"sdjak","sdjkfh","fdijsf"};
	public String choicebox_val;
	public String n_mmsi_date;
	public String n_mmsi_date1;
	private static List<String> mmsi1;
	private static String[] a;
	@Override
	public void initialize(URL arg0,ResourceBundle arg1) {
		get_sqldata();
		myChoiceBox.getItems().addAll(a);
		myChoiceBox.setOnAction(this::get_choicebox_val);
		
	}
	public void get_choicebox_val(ActionEvent e) {
		choicebox_val = myChoiceBox.getValue();
		System.out.println(choicebox_val);
		sqlite_test.order_sql(choicebox_val);
	}
	public static void get_sqldata() {
		try {
			mmsi1 = sqlite_test.distinct_sql();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a = mmsi1.toArray(new String[mmsi1.size()]);
	}


	public void get_date(ActionEvent e) {
		LocalDate mmsi_date = datepicker.getValue(); 
		n_mmsi_date = mmsi_date.toString();
		n_mmsi_date = n_mmsi_date.replace("-0", "/");
		LocalDate mmsi_date1 = mmsi_date.plusDays(1L);
		n_mmsi_date1 = mmsi_date1.toString();
		n_mmsi_date1= n_mmsi_date1.replace("-0", "/");
		System.out.println(mmsi_date.toString());
		String a5 = sqlite_test.selectdate_sql(choicebox_val,n_mmsi_date,n_mmsi_date1);
		try {
			write_csvfile(a5);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public void write_csvfile(String a5) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.csv"));//檔案輸出路徑
		bw.write(a5);
		bw.flush();
		bw.close();
	}
}