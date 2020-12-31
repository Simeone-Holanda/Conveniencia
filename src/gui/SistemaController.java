package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.dao.DaoFactory;
import model.dao.MercadoriasDao;
import model.dao.VendasDao;
import model.entities.Mercadorias;
import model.entities.Vendas;

public class SistemaController implements Initializable {
	@FXML
	private Button btAdd;

	@FXML
	private Button btSair;

	@FXML
	private Button btRenda;

	@FXML
	private ComboBox<String> combo;

	@FXML
	private TextField txtNomeCa;

	@FXML
	private TextField txtNomeAtu;

	@FXML
	private TextField qtd = null;

	
	@FXML
	private TableView<Vendas> tvVendas;

	@FXML
	public void onBtAdd() {
		if (combo.getValue() == null || qtd.getText() == "0") {
			System.out.println(qtd.getText());
			Alerts.showAlert("Error", "Erro na entrada de dados", "Prenchar todos os dados para adicionar",
					AlertType.INFORMATION);
			

		} else {
			// pegar elementos 
			int quanti = Integer.parseInt(qtd.getText()); // valor da quantidade
			String produto = (String) combo.getValue(); // pegando o valor do combobox
			double preco = 0;
			MercadoriasDao mercadoriaDao = DaoFactory.createMercadoriasDao();
			List<Mercadorias> list = mercadoriaDao.findAll();
			
			// inserir no bd
			Vendas newVenda = null;

			for (Mercadorias item : list) {
				if (item.getNomeProduto().equals(produto)) {//buscando o produto no nosso bd de mercadorias para extrair o codigo e o preço dele
					newVenda = new Vendas(item.getCodProduct(), item.getNomeProduto(), item.getPrecoProduto());
					// e aq preco = item.getPrecoProduto(); // salvei o pre�o separado tbm pq preciso dele para manipula o valor total
				}
			}
		
			double valor = newVenda.getPreco() * quanti; // valor total da compra
			System.out.println(newVenda.getPreco());
			
			VendasDao vendasDao = DaoFactory.createVendasDao(); // vms agr criar nossa venda

			vendasDao.insert(newVenda, quanti, valor); //adicionando em nosso bd tds os dados necessarios da venda
			
			// pegando os dados para inserir na tableview 
			Vendas novaVenda = vendasDao.findByNome(produto); // isso aq � um objeto q vai ser add na nossa tabela

			novaVenda.setQuantidade(quanti); // verificar se isso � necessario, pq nosso objeto venda tem a quantidade--------------------------------------------
			novaVenda.setPreco(newVenda.getPreco());
			novaVenda.setValor(valor);
			
			// inserindo a venda na tableview
			tvVendas.getItems().add(new Vendas(novaVenda.getCodProduto(), novaVenda.getName(), novaVenda.getPreco(),
					novaVenda.getQuantidade(), novaVenda.getValor())); // adicionando a venda a nossa table view
			// msg de confirmação 
			Alerts.showAlert("Venda", "Venda realizada", "Operação concluida com sucesso", AlertType.INFORMATION);
		}

	}

	@FXML
	public void onBtremove() {

		if (tvVendas.getSelectionModel().getSelectedItem() == null) {
			Alerts.showAlert("Error", "Selecione uma mercadoria", "Selecione uma mercadoria para remover",
					AlertType.INFORMATION);
		} else {
			VendasDao vendasDao = DaoFactory.createVendasDao();
			vendasDao.deleteByNome(tvVendas.getSelectionModel().getSelectedItem().getName());
			tvVendas.getItems().remove(tvVendas.getSelectionModel().getSelectedItem());
			// msg de confirmação 
			Alerts.showAlert("Remoção", "Mercadoria removida", "Operação concluida com sucesso", AlertType.INFORMATION);
			
		}

	}

	@FXML
	public void onBtSair() {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		Main.mainScene = new Scene(parent); 
		Main.MudarTela("login");
	}
	
	@FXML
	public void onBtCadastro() {
		Parent parent3 = null;
		try {
			parent3 = FXMLLoader.load(getClass().getResource("/gui/Cadastro.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.cadastro = new Scene(parent3);
		Main.MudarTela("cadastro");
	}

	public void onBtCalcRenda() { 
	    Parent parent4 = null;
		try {
			parent4 = FXMLLoader.load(getClass().getResource("/gui/Renda.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    Main.renda = new Scene(parent4);
		Main.MudarTela("renda");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		elementoCombobox();
		tabelaVendas();
		elementosTabela();
	}
	
	public void elementosTabela() {
		VendasDao vendasAntigas = DaoFactory.createVendasDao();
		List<Vendas> vendas = vendasAntigas.findAll();
		for(Vendas element: vendas) {
			tvVendas.getItems().add(element);
		}
		
	}
	public void elementoCombobox() {
		// adicionando todas as nossas mercadorias ao combobox
		MercadoriasDao mercadoriaDao = DaoFactory.createMercadoriasDao();
		List<Mercadorias> list = mercadoriaDao.findAll();
		for (Mercadorias obj : list) {
			combo.getItems().add(obj.getNomeProduto());
		}
	}

	public void tabelaVendas() {
		// nossas colunas estao criadas
		TableColumn<Vendas, Integer> colCodProduto = new TableColumn("Codigo");
		colCodProduto
				.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCodProduto()).asObject());

		TableColumn<Vendas, String> colnome = new TableColumn("Descricao");
		colnome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

		TableColumn<Vendas, Double> colPreco = new TableColumn("Preco");
		colPreco.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPreco()).asObject());

		TableColumn<Vendas, Integer> colquantity = new TableColumn("Quantidade");
		colquantity.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantidade()).asObject());

		TableColumn<Vendas, Double> colValor = new TableColumn("Valor");
		colValor.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getValor()).asObject());

		tvVendas.getColumns().addAll(colCodProduto, colnome, colPreco, colquantity, colValor);

	}

}