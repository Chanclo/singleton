import java.rmi.RemoteException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class clsKeys {
    
    private Connection con = clsCon.getConnection();
    
    public ResultSet consulta(){
        ResultSet rs = null; 
        String consulta = "SELECT `tblteclado`.`intIdTeclado` AS 'IdTeclado' ,`tblteclado`.`vchNombre` , `tblswitch`.`vchNombre` AS 'Switch',`tblswitch`.`intId`  FROM tblswitch, tblTeclado WHERE `tblswitch`.`intId` = `tblteclado`.`intId`;";
        try{
            Statement stm;
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery(consulta);
            
            return rs;
            
        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }
    
    public int insertar(String Teclado, int idSw){
        int x = 0;
        String inserta = "INSERT INTO `dbkey`.`tblteclado` (`vchNombre`, `intId`) VALUES ('"+Teclado+"','"+idSw+"'); ";
        
        try{
            Statement stm = (Statement) con.createStatement();
            x = stm.executeUpdate(inserta);
        }catch(Exception e){
            System.out.print(e);
        }finally{
            return x;
        }
    }
    
    public int actualizar(int idTeclado,String Teclado, int idSw){
        int x = 0;
        String actualiza = 
        "UPDATE `dbkey`.`tblteclado` SET `vchNombre` = '"+Teclado+"' , `intId` = '"+idSw+"' "
        + "WHERE `intIdTeclado` = '"+idSw+"'; ";
        
        try{
            Statement stm = (Statement)con.createStatement();
            x = stm.executeUpdate(actualiza);
        }catch(Exception e){
            System.out.print(e);
        }finally{
            return x;
        }
    }
    
    public int eliminar(int IdTeclado){
        int x = 0;
        String elimina = 
        "DELETE FROM `dbkey`.`tblteclado` WHERE `intIdTeclado` = '"+IdTeclado+"'; ";
        
        try{
            Statement stm = (Statement)con.createStatement();
            x = stm.executeUpdate(elimina);
        }catch(SQLException e){
            System.out.print(e);
        }finally{
            return x;
        }
    }
    
    public void llenarTabla(JTable tabla){
        try{
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            
            while(model.getRowCount() > 0){
                model.removeRow(0);
            }
            
            ResultSet rs = consulta();
            
            while(rs.next()){
                String IdTeclado = rs.getString("IdTeclado");
                String Teclado = rs.getString("vchNombre");
                String Switch = rs.getString("Switch");
                String IdSwicth = rs.getString("intId");
                
                model.addRow(new Object[]{IdTeclado,Teclado, Switch, IdSwicth});
                
            }
        }catch(Exception e){
            System.out.print(e);
            System.out.println("Mostrar f");
        }
    }
    public void cmbSw(JComboBox cmb){
        ResultSet rs = null;
        String consulta ="SELECT * FROM tblswitch";
        
        try{
            Statement stm;
            stm = (Statement)con.createStatement();
            rs = stm.executeQuery(consulta);
            
            while(rs.next()){
                String IdSwitch =rs.getString("intId");
                String Nombre = rs.getString("vchNombre");
                
                
                int idSwitches = Integer.parseInt(IdSwitch);
                
                cmb.addItem(idSwitches + "-" + Nombre);
            }
            
        }catch(Exception e){
            System.out.print("Error");
        }
    }
}
