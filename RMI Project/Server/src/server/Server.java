package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.InterfaceImplimentation;
import java.rmi.registry.LocateRegistry;


public class Server {
    public static void main(String[] args) throws MalformedURLException {
        
        try {
            LocateRegistry.createRegistry(1099);
            InterfaceImplimentation imp = new InterfaceImplimentation();
                        
            Naming.rebind("rmi://localhost:1099/Server", imp);
            
            System.out.println("Server is running...");
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
