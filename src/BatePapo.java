import java.rmi.Remote;
import java.rmi.RemoteException;


public interface BatePapo extends Remote {
	void criaSala() throws RemoteException;
	void removerDaSala(int salaid) throws RemoteException;
	String listarSalas() throws RemoteException;
	int addUsuario(String nome,int salaid) throws RemoteException;
	Sala getSala(int id)	throws RemoteException;
	void encaminharMensagem(String mensagem,int salaid,int remetenteid,int destinatarioid) throws RemoteException;
}

