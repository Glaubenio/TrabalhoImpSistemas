

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class BatePapoCliente extends Application implements Initializable{
	int userId;
	BatePapo obj=null;
	int currentRoom;
	
	@FXML
	private Label label;
	
	
	@FXML
	private Text label2,label3,label4,label5;
	
	@FXML
	private Button refresh,back,chooseRoom,enviar;
	
	@FXML
	private TextArea texto,salas,users;

	
	@FXML
	private TextField fieldSala,fieldMensagem,fieldId,fieldNome;

	@FXML
	private void escolherSala(ActionEvent event){
		try{
			try{

			int salaid= Integer.parseInt(fieldSala.getText());
			String nome=fieldNome.getText();
		
			this.currentRoom=obj.getSala(salaid).getId();
			this.userId=obj.addUsuario(nome, salaid);
			
			this.label2.setVisible(true);
			this.label3.setVisible(true);
			this.fieldMensagem.setVisible(true);
			this.fieldId.setVisible(true);
			this.texto.setVisible(true);
			this.refresh.setVisible(true);
			this.enviar.setVisible(true);
			this.back.setVisible(true);
			this.users.setVisible(true);
			this.users.setText(obj.getSala(currentRoom).listarUsuarios());
			
			this.label4.setVisible(false);
			this.label5.setVisible(false);
			this.fieldNome.setVisible(false);
			
			this.fieldSala.setVisible(false);
			this.chooseRoom.setVisible(false);
			this.salas.setVisible(false);
			this.label.setText("Sala "+this.currentRoom);
			
			
			}catch(NullPointerException e1){
				System.out.println("sala nao existe");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch(NullPointerException e){
			
		}
		

		
	}
	@FXML
	private void enviarMensagem(ActionEvent event) throws RemoteException{
		int destinatarioid= Integer.parseInt(fieldId.getText());
		obj.encaminharMensagem(fieldMensagem.getText(), currentRoom, userId, destinatarioid);
	}
	
	@FXML
	private void refresh(ActionEvent event) throws RemoteException{
		String mensagem = obj.getSala(currentRoom).getUsuarios().get(userId).getMensagem();
		this.texto.setText(mensagem);	
	}
	
	@FXML
	private void voltar(ActionEvent event) throws RemoteException{
			this.label2.setVisible(false);
			this.label3.setVisible(false);
			this.fieldMensagem.setVisible(false);
			this.fieldId.setVisible(false);
			this.texto.setVisible(false);
			this.refresh.setVisible(false);
			this.enviar.setVisible(false);
			this.back.setVisible(false);
			this.label4.setVisible(true);
			this.label5.setVisible(true);
			this.fieldNome.setVisible(true);
			this.users.setVisible(false);
			this.fieldSala.setVisible(true);
			this.chooseRoom.setVisible(true);
			this.salas.setVisible(true);
			this.label.setText("Menu");
			obj.getSala(currentRoom).removerUsuario(userId);
		

	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			obj=(BatePapo)Naming.lookup("rmi://localhost/BatePapoServer");
			this.salas.setText(obj.listarSalas());
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}