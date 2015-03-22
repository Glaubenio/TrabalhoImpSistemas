import java.io.Serializable;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class Sala implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private HashMap<Integer, Usuario> usuarios;
	public Sala(int id) {
		// TODO Auto-generated constructor stub
		setUsuarios(new HashMap<Integer, Usuario>());
	
		this.id=id;
	}
	public int getId() {
		return id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HashMap<Integer, Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(HashMap<Integer, Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	void removerUsuario(int userId){
		if(usuarios.containsKey(userId)){
			usuarios.remove(userId);
		}
	}
	
	String listarUsuarios(){
		if(usuarios.isEmpty()){
			JOptionPane.showMessageDialog(null, "Sala Vazia");
			return null;
		}
		String str = "";
		for (int i = 1; i < usuarios.size()+1; i++) {
			str+="User id: "+usuarios.get(i).getId()+"\nNome: "+usuarios.get(i).getNome()+"\n\n";
		}
		return str;
	}
	
	void encaminharMensagem(int remetenteId,int destinatarioId,String mensagem){
		String str=usuarios.get(remetenteId).getNome()+" Lhe enviou a mensagem:\n"+mensagem;
		System.out.println(str);
		usuarios.get(destinatarioId).setMensagem(str);
	}
}
