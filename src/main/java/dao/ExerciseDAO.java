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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Exercise;

/**
 *
 * @author Labing
 */
public class ExerciseDAO implements IBaseDatos<Exercise>{

    @Override
    public List<Exercise> findAll() {
        List<Exercise> exercises = null;
	    String query = "SELECT * FROM EXERCISE";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int end = 0;
            int maxpt = 0;
	    String cat = null;
            String topic = null;
	    while (rs.next()){
	    	if(exercises == null){
	    		exercises = new ArrayList<Exercise>();
	    	}
	      
	        Exercise registro = new Exercise();
	        end = rs.getInt("end");
	        registro.setEND(end);
                
	        maxpt = rs.getInt("maxpt");
	        registro.setMAXPT(maxpt);
                
	        cat = rs.getString("cat");
	        registro.setCAT(cat);
	        
                topic = rs.getString("topic");
                registro.setTOPIC(topic);
                
	        exercises.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Exercises");
			e.printStackTrace();
		}
	    
	    return exercises;
    }

    @Override
    public boolean insert(Exercise exercise) {
        boolean result = false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into EXERCISE (CAT, END, TOPIC, MAXPT)" + " values (?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString (1, exercise.getCAT());
                        preparedStmt.setInt (2, exercise.getEND());
                        preparedStmt.setString (3, exercise.getTOPIC());
			preparedStmt.setInt (4, exercise.getMAXPT());
                	result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    @Override
    public boolean update(Exercise exercise) {
        boolean result = false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update EXERCISE set TOPIC = ?, MAXPT = ? where CAT = ?, END = ?";
		PreparedStatement preparedStmt = null;
		try {
		    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setString (1, exercise.getTOPIC());
                    preparedStmt.setInt (2, exercise.getMAXPT());
                    preparedStmt.setString (3, exercise.getCAT());
                    preparedStmt.setInt (4, exercise.getEND());		    
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
    }

    @Override
    public boolean delete(Exercise exercise) {
        boolean result = false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from EXERCISE where CAT = ?, END = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		    preparedStmt.setString (1, exercise.getTOPIC());
                    preparedStmt.setInt (2, exercise.getMAXPT());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
    }
    
}
