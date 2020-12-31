package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.dao.DaoFactory;
import model.dao.MercadoriasDao;
import model.entities.Mercadorias;

public class CasdastroController implements Initializable {

	@FXML
	private Button btAtualizar;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btVoltar;

	@FXML
	private TextField txtNomeCa;

	@FXML
	private TextField txtNomeAtu;
	
	@FXML
	private TextField txtPrecoCa;

	@FXML
	private TextField txtPrecoAtu;

	@FXML
	private ComboBox comboAtu;

	@FXML
	public void elementoCombobox2() {
		// adicionando todas as nossas mercadorias ao combobox
		MercadoriasDao mercadoriaDao = DaoFactory.createMercadoriasDao();
		List<Mercadorias> list = mercadoriaDao.findAll();
		for (Mercadorias obj : list) {
			comboAtu.getItems().add(obj.getNomeProduto());
		}
	}
	
	@FXML
	public void onBtSalvarAction() {
		MercadoriasDao mercadoriaDao = DaoFactory.createMercadoriasDao();
		int cod = mercadoriaDao.count(); 
		String nome = txtNomeCa.getText();
		double preco = Double.parseDouble(txtPrecoCa.getText());
		Mercadorias newProduto = new Mercadorias(cod,nome, preco);
		mercadoriaDao.insert(newProduto);
		
		// confirmação do cadastro para o usuario
		Alerts.showAlert("Cadastro", "Mercadoria cadastrada", "Operação concluida com sucesso", AlertType.INFORMATION);
		
	}
	
	@FXML
	public void onBtAtualizarAction() {
		MercadoriasDao mercadoriaDao = DaoFactory.createMercadoriasDao();
		
		String oldNome = (String) comboAtu.getValue();
		
		String newNome = txtNomeAtu.getText();	
		
		double preco = Double.parseDouble(txtPrecoAtu.getText());
		
		mercadoriaDao.update(newNome, preco, oldNome);
		
		// confirmação do cadastro para o usuario
		Alerts.showAlert("Atualizada", "Mercadoria atualizada", "Operação concluida com sucesso", AlertType.INFORMATION);
				
	}
	
	@FXML
	public void onBtVoltar() {
		Parent parent2 = null;
		try {
			parent2 = FXMLLoader.load(getClass().getResource("/gui/Sistema.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    Main.sistema = new Scene(parent2);
		Main.MudarTela("sistemaVendas");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		elementoCombobox2();

	}

}
