
package Logica;

import CapaPersistencia.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RegistroProductos {
    private int id;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precioCompra;
    private double precioUnitario;
    private double precioVenta;
    
    public RegistroProductos(){
        
    }
    public RegistroProductos getRegistroProductos(int id) throws SQLException{
      this.id = id;
      return this.getRegistroProductos();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    public void registrarProducto(int id, String nombre,String categoria, int cantidad, 
            double precioCompra, double precioUnitario, double precioVenta){
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioUnitario = precioUnitario;
        this.precioVenta = precioVenta;
    }
    
    public boolean guardarProducto() {
        System.out.println("Exitoso");
        ConexionBD conexion = new ConexionBD();
        String sentencia = "INSERT INTO caracteristicasProductos(id, nombre, categoria, cantidad, precioCompra, precioUnitario, precioVenta)"
                +"VALUES ('" + this.id + "','" + this.nombre + "','" + this.categoria + "'" 
                + "'" +this.cantidad + "'" +this.precioCompra + "'" + this.precioUnitario + "'" +this.precioVenta + "');";
        if (conexion.setAutocommitBD(false)){
            if (conexion.CrearBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
                
            }
        }else{
            conexion.cerrarConexion();
            return false;
        }       
    }
    public boolean eliminarProducto(int id) {
        
        ConexionBD conexion = new ConexionBD();
        String sentencia = "DELETE FROM `caracteristicasProductos`WHERE`id`='" + id + "'";
        if (conexion.setAutocommitBD(false)){
            if (conexion.ActualizarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;   
            }
        }else{
            conexion.cerrarConexion();
            return false;
        }       
    }
     public boolean actualizarProducto() {
        ConexionBD conexion = new ConexionBD();
        String sentencia = "UPDATE `caracteristicasProductos `SET nombre='" + this.nombre + "',categoria='" + this.categoria + "',cantidad'" 
                + "'" +this.cantidad + "',precioCompra='" +this.precioCompra + "',precioUnitario'" + this.precioUnitario + "',precioVenta'" +this.precioVenta 
                + "'WHERE id=" +this.id + ";";
        if (conexion.setAutocommitBD(false)){
            if (conexion.ActualizarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;   
            }
        }else{
            conexion.cerrarConexion();
            return false;
        }       
    }
    public List<RegistroProductos> ListarProductos() throws SQLException{
        ConexionBD conexion = new ConexionBD();
        List<RegistroProductos> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM caracteristicasProductos order by id asc";
        ResultSet rs = conexion.ConsultarBD(sql);
        RegistroProductos rp;
        while (rs.next()){
            rp = new RegistroProductos();
            rp.setId(rp.getId("id"));
            rp.setNombre(rp.getNombre("nombre"));
            rp.setCategoria(rp.getCategoria("categoria"));
            rp.setCantidad(rp.getCantidad("cantidad"));
            rp.setPrecioCompra(rp.getPrecioCompra("precio Compra"));
            rp.setPrecioUnitario(rp.getPrecioUnitario("precio Unitario"));
            rp.setPrecioVenta(rp.getPrecioVenta("precio Venta"));
            listaProductos.add(rp);
        }
        conexion.cerrarConexion();
        return listaProductos;
    }
    
    public RegistroProductos getRegistroProductos()throws SQLException {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM caracteristicasProductos WHERE id ='" + this.id + "'";
        ResultSet rs = conexion.ConsultarBD(sql);
        if (rs.next()){
            this.id = rs.getInt("id");
            this.nombre = rs.getString("nombre");
            this.categoria = rs.getString("categoria");
            this.cantidad = rs.getInt("cantidad");
            this.precioCompra = rs.getDouble("precio compra");
            this.precioUnitario = rs.getDouble("precio unitario");
            this.precioVenta = rs.getDouble("precio venta");
            conexion.cerrarConexion();
            return this;
        }else{
            conexion.cerrarConexion();
            return null;
        }
        
        
    }
    @Override
    public String toString(){
    return "Productos{" + "id=" + id + ", nombre=" + categoria + ", categoria=" + cantidad + ", cantidad="
    + precioCompra + ", precio compra=" + precioUnitario + ", precio unitario=" + precioVenta + ", precio venta=" + "}";
}

    private int getId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getCategoria(String categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getCantidad(String cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getPrecioCompra(String precio_Compra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getPrecioUnitario(String precio_Unitario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getPrecioVenta(String precio_Venta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}



    



