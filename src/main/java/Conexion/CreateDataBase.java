/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import dao.ExerciseDAO;
import dao.ResultDAO;
import dao.StudentDAO;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian Giraldo
 */
public class CreateDataBase {
     public static void run(){
        String sqlstudent = "CREATE TABLE STUDENT(SID integer, FIRST varchar(50), LAST varchar(50), EMAIL varchar(40), PRIMARY KEY(SID));";
          Connection connection_student = null;
            try {
                connection_student = Conexion.getConnection();
                Statement stmt = connection_student.createStatement();
                stmt.executeUpdate(sqlstudent);
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
             Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sqlexercise = "CREATE TABLE EXERCISE(CAT varchar(50), END integer, TOPIC varchar(50), MAXPT integer, PRIMARY KEY(CAT, END));";
          Connection connection_exercise = null;
            try {
                connection_exercise = Conexion.getConnection();
                Statement stmt = connection_exercise.createStatement();
                stmt.executeUpdate(sqlexercise);
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
             Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sqlresult = "CREATE TABLE RESULT(SID integer, CAT varchar(50), END integer, POINTS integer, FOREIGN KEY(SID) REFERENCES STUDENT(SID),"
                + " FOREIGN KEY(CAT) REFERENCES EXERCISE(CAT), FOREIGN KEY(END) REFERENCES EXERCISE(END));";
          Connection connection_result = null;
            try {
                connection_result = Conexion.getConnection();
                Statement stmt = connection_result.createStatement();
                stmt.executeUpdate(sqlresult);
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(ResultDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
             Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }    
     }
     
}
