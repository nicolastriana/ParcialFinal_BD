/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Conexion.Conexion;
import Conexion.IBaseDatos;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Result;

/**
 *
 * @author Labing
 */
public class ResultDAO implements IBaseDatos<Result> {

    @Override
    public List<Result> findAll() {
        List<Result> results = null;
	    String query = "SELECT * FROM RESULT";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ResultDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int points = 0;
	
	    while (rs.next()){
	    	if(results == null){
	    		results = new ArrayList<Result>();
	    	}
	      
	        Result registro= new Result();
	        points = rs.getInt("points");
	        registro.setPoints(points);	        	        
	        
	        results.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Results");
			e.printStackTrace();
		}
	    
	    return results;
    }

    @Override
    public boolean insert(Result t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Result t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Result t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
