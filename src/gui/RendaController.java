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
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import model.dao.DaoFactory;
import model.dao.VendasDao;
import model.entities.Vendas;

public class RendaController implements Initializable{

	@FXML
	private Label valortxt;
	
	@FXML
	private BarChart<String , Integer> tabela;
	
	@FXML 
	private Button btSair;

	@FXML
	private Button btAtualizar;
	
	@FXML 
	private Button btZera;
	
	public double calcSaldo() {
		VendasDao vendasDao = DaoFactory.createVendasDao();
		
		List<Vendas> tdsVendas = vendasDao.findAll();
		
		double cont = 0;
		for(Vendas item: tdsVendas) {
			if(tdsVendas.get(0) == null) {
				return 0.0;
			}
			cont += item.getValor();
		}
	    return cont;
	}
	
	@FXML
	public void onBtSair() {
		Parent parent2 = null;
		try {
			parent2 = FXMLLoader.load(getClass().getResource("/gui/Sistema.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    Main.sistema = new Scene(parent2);
		Main.MudarTela("sistemaVendas");
	}
	
	@FXML
	public void onBtAtualizarAction() {
		double valor = calcSaldo();
		String txtValor = Double.toString(valor);
		valortxt.setText(txtValor + " R$");
		// confirmaçãopara o usuario
		Alerts.showAlert("Atualizado", "Pagina atualizada", "", AlertType.INFORMATION);
				
	}
	
	@FXML
	public void onBtZeraVendas() {
		VendasDao vendasDao = DaoFactory.createVendasDao();
		List<Vendas> vendas = vendasDao.findAll();
		for(Vendas item: vendas) {	
			vendasDao.deleteByNome(item.getName());
		//	vendasDao.zeraVendas(item.getName());
			//cont += item.getValor();
		}
		
		double valor = calcSaldo();
		//System.out.println(valor);
		String txtValor = Double.toString(valor);
		valortxt.setText(txtValor + " R$");	
		// confirmação para o usuario
		Alerts.showAlert("Meradorias zeradas","Operação concluida com sucesso", "", AlertType.INFORMATION);
				
	}
	
	public void initialize(URL uri, ResourceBundle rb) {
		
		double valor = calcSaldo();
		String txtValor = Double.toString(valor);
		valortxt.setText(txtValor + " R$");
		
	}
}
	

