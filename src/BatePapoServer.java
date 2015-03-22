import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class BatePapoServer extends UnicastRemoteObject implements BatePapo{
	HashMap<Integer, Sala> salas;
	static int salaid=1;
	static int usuarioid=1;

	protected BatePapoServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		salas=new HashMap<Integer, Sala>();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void criaSala() throws RemoteException {
		// TODO Auto-generated method stub
		Sala sala= new Sala(salaid++);
		salas.put(sala.getId(), sala);
	}

	@Override
	public void removerDaSala(int salaid) throws RemoteException {
		// TODO Auto-generated method stub
		salas.remove(salaid);
	}

	@Override
	public String listarSalas() throws RemoteException {
		// TODO Auto-generated method stub
		String str="";
		for (int i = 1; i <= salas.size(); i++) {
			str+="Sala "+salas.get(i).getId()+"\n";
			
		}
		return str;
	}

	@Override
	public int addUsuario(String nome,int salaid) throws RemoteException {
		Usuario usuario=new Usuario(nome,usuarioid++);
		salas.get(salaid).getUsuarios().put(usuario.getId(), usuario);
		return usuario.getId();
		
	}


	public static void main(String[] args) {
		try{
		BatePapoServer obj=new BatePapoServer();
		Naming.rebind("rmi://localhost/BatePapoServer", obj);
		System.out.println("deu certo");
		}catch(Exception e){
			e.printStackTrace();
	}
	}

	@Override
	public Sala getSala(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return salas.get(id);
	}

	@Override
	public void encaminharMensagem(String mensagem, int salaid, int remetenteid,int destinatarioid)
			throws RemoteException {
		// TODO Auto-generated method stub
		String str=getSala(salaid).getUsuarios().get(remetenteid).getNome()+" Lhe enviou a mensagem:\n"+mensagem;
		if(destinatarioid==0){
			for(int i=1;i<=getSala(salaid).getUsuarios().size();i++){
				getSala(salaid).getUsuarios().get(i).setMensagem(str);
			}
		}else{
			getSala(salaid).getUsuarios().get(destinatarioid).setMensagem(str);
		}
		
		
	}
}