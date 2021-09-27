module hellofx2 {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	opens application to javafx.graphics, javafx.fxml;
}
