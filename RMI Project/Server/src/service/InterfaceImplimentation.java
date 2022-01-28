package service;

import java.sql.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InterfaceImplimentation extends UnicastRemoteObject implements JavaRemote{
    
    public Connection cnx;
    public PreparedStatement pst;
    
    int qsts = 5;
    String q[][] = new String[qsts][4];
    String ra[][] = new String[qsts][2];
    
    public InterfaceImplimentation() throws RemoteException{
        super();
        
        
        q[0][0] = "Which is used to find and fix bugs in the Java programs.?";
        q[0][1] = "JVM";
        q[0][2] = "JDB";
        q[0][3] = "JDK";

        q[1][0] = "What is the return type of the hashCode() method in the Object class?";
        q[1][1] = "int";
        q[1][2] = "Object";
        q[1][3] = "long";

        q[2][0] = "Which package contains the Random class?";
        q[2][1] = "java.util package";
        q[2][2] = "java.lang package";
        q[2][3] = "java.awt package";
        
        q[3][0] = "An interface with no fields or methods is known as?";
        q[3][1] = "Runnable Interface";
        q[3][2] = "Abstract Interface";
        q[3][3] = "Marker Interface";

        q[4][0] = "In which memory a String is stored, when we create a string using new operator?";
        q[4][1] = "Stack";
        q[4][2] = "String memory";
        q[4][3] = "Random storage space";
        
        
        ra[0][0] = q[0][0];
        ra[1][0] = q[1][0];
        ra[2][0] = q[2][0];
        ra[3][0] = q[3][0];
        ra[4][0] = q[4][0];
        
        ra[0][1] = "JDB";
        ra[1][1] = "int";
        ra[2][1] = "java.util package";
        ra[3][1] = "Marker Interface";
        ra[4][1] = "Heap memory";
        
    }

    @Override
    public boolean connect(String usr, String pwd) throws RemoteException {
        cnx = null;
        try {
            if(cnx == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "nasreddine");
                String req = "SELECT * FROM loginDB WHERE username=? AND password=?";
                pst = cnx.prepareStatement(req);
                pst.setString(1, usr);
                pst.setString(2, pwd);
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()) {
                    return true;
                }
                else {
                    return false;
                    
                }
            }
            
            cnx.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void register(String usr, String pwd) throws RemoteException {
        
        
        try {
            String req = "INSERT INTO loginDB (username, password) VALUES (?, ?)";
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "nasreddine");
            pst = cnx.prepareStatement(req);
            pst.setString(1, usr);
            pst.setString(2, pwd);            
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "registered !");
            
            cnx.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceImplimentation.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        
        
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String edit_num(int num) throws RemoteException {
                
        try {
            return "" +(num+1)+ " ->";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String edit_qst(int num) throws RemoteException {
        
        try {
            return ""+q[num][0];
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String edit_op1(int num) throws RemoteException {
        
        try {
            return ""+q[num][1];
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String edit_op2(int num) throws RemoteException {
        
        try {
            return ""+q[num][2];
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String edit_op3(int num) throws RemoteException {
        
        try {
            return ""+q[num][3];
        } catch (Exception e) {
        }
                
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int score(String[][] a) throws RemoteException {
        
        try {
            int s = 0;
            for(int i = 0; i < qsts; i++){
                if(a[i][0].equals(ra[i][1]))
                    s+=10;
                else
                    s+=0;
            }
            return s;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}