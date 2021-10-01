
package CapaPersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionBD {
    private String DB_driver = "";
    private String url = "";
    private String db = "";
    private String host = "";
    private String username = "";
    private String password = "";
    public Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private boolean local;
    
    public ConexionBD(){
        DB_driver = "com.mysql.jdbc.Driver";
        
        host = "localhost: 3306";
        db = "misiontic";
        url = "jdbc:mysql://"+host+"/"+db;
        username = "root";
        password = "SaraEscobar1143_";
        
        try {
            Class.forName(DB_driver);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            con = DriverManager.getConnection(url, username, password);
            con.setTransactionIsolation(8);
            System.out.println("Conectado exitosamente");
        }catch (SQLException ex){
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getConnection(){
        return con;
    }
    public void closeConnection(Connection con){
        if (con != null){
            try {
                con.close();
            }catch(SQLException ex){
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public ResultSet ConsultarBD (String sentencia){
        try{
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sentencia);
        }catch(SQLException sqlex){
        }catch(RuntimeException rex){
        }catch(Exception ex){
        }
        return rs;
    }
    public boolean CrearBD(String sentencia){
        System.out.println("Exitoso");
        try{
            stmt = con.createStatement();
            stmt.execute(sentencia);
        }catch(SQLException | RuntimeException sqlex){
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }
    public boolean EliminarBD(String sentencia){
        try{
            stmt = con.createStatement();
            stmt.execute(sentencia);
        }catch(SQLException | RuntimeException sqlex){
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }
    public boolean ActualizarBD(String sentencia){
        try{
            stmt = con.createStatement();
            stmt.execute(sentencia);
        }catch(SQLException | RuntimeException sqlex){
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }
    public boolean setAutocommitBD(boolean parametro){
        try{
            con.setAutoCommit(parametro);
        }catch(SQLException sqlex){
            System.out.println("Error al configurar el autoCommit: " + sqlex.getMessage());
            return false;
        }
        return true;
    }
    public void cerrarConexion(){
        closeConnection(con);
    }
    public boolean commitBD(){
        try{
            con.commit();
            return true;
        }catch(SQLException sqlex){
            System.out.println("Error al hacer commit: " + sqlex.getMessage());
            return false;
        }
    }
    public boolean rollbackBD(){
        try{
            con.rollback();
            return true;
        }catch(SQLException sqlex){
            System.out.println("Error al hacer rollback: " + sqlex.getMessage());
            return false;
        }
    }
    public static void main (String[] args){
        ConexionBD b = new ConexionBD();
        b.cerrarConexion();
    }

    
}

    
