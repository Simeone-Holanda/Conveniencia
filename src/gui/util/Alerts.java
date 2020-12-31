package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type); // instanciando nosso objeto alert
		alert.setTitle(title); // pegando o tituto dele
		alert.setHeaderText(header); // cabeçalho
		alert.setContentText(content); // conteudo
		alert.setAlertType(type); // tipo
		alert.show();
	}	
}