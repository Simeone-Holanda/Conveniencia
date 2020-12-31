package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage stage; 
	
	//Telas 
	public static Scene mainScene; 
	public static Scene sistema;
	public static Scene cadastro;
	public static Scene renda;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage; 
			primaryStage.setTitle("Login");
			
			// tela login
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/Login.fxml")); //Esse Parent é uma super classe de AnchorPane(classe criada inicial View), os outros elementos sao elementos necessario para a captura da tela
		    mainScene = new Scene(parent); 
		    primaryStage.setScene(mainScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void MudarTela(String tela) {
		switch(tela) {
			case "login":
				stage.setTitle("Login");
				stage.setScene(mainScene);
				break;
			case "sistemaVendas":
				stage.setTitle("Setor de vendas");
				stage.setScene(sistema); 
				break;
			case "cadastro":
				stage.setTitle("Cadastro de mercadorias");
				stage.setScene(cadastro);
				break;
			case "renda":
				stage.setTitle("Calculo da renda");
				stage.setScene(renda);
				break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
