package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JavaRemote extends Remote {
    
    public boolean connect(String usr, String pwd) throws RemoteException;
    
    public void register(String usr, String pwd) throws RemoteException;
    
    public String edit_num(int num) throws RemoteException;
    
    public String edit_qst(int num) throws RemoteException;
    
    public String edit_op1(int num) throws RemoteException;
    
    public String edit_op2(int num) throws RemoteException;
    
    public String edit_op3(int num) throws RemoteException;
    
    public int score(String[][] a) throws RemoteException;
    
}
