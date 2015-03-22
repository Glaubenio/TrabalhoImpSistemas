import java.io.Serializable;
import java.util.ArrayList;


public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String mensagem;
	ArrayList<String> mensagens;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(int id) {
		this.id = id;
		this.mensagem="";
	}
	private String nome;
	public Usuario(String nome,int id) {
		// TODO Auto-generated constructor stub
		this.setNome(nome);
		this.id=id;
		mensagens= new ArrayList<String>();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public String getMensagem() {
		
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
