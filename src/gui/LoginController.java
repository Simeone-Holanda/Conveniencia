package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import model.dao.DaoFactory;
import model.dao.LoginDao;
import model.entities.Login;

public class LoginController {

	@FXML
	private TextField txtNome;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btLogin;

	@FXML
	protected void onBtLoginAction() { // entender o pq n pode colocar um catch

		try {
			String txtlogin = txtNome.getText();
			String txtsenha = txtSenha.getText();

			LoginDao loginDao = DaoFactory.createLoginDao(); // conec��o criada aq

			Login login = loginDao.findByNameSenha(txtlogin, txtsenha); // se isso retorna null � pq o usuario n esta
																		// inserido
			if (login == null) {
				Alerts.showAlert("Error", "Usuario nao cadastrado", "Nome ou senha incorreto", AlertType.ERROR);
			} else {
				Parent parent2 = FXMLLoader.load(getClass().getResource("/gui/Sistema.fxml"));
				Main.sistema = new Scene(parent2);
				Main.MudarTela("sistemaVendas"); // chamando a tela de principal
				// DB.closeConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL url, ResourceBundle rb) {
		txtNome.setText(""); // Limpando os campos na instanciação
		txtSenha.setText("");
	}

}
