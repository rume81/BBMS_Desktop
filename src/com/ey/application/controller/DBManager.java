package com.ey.application.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ey.application.model.CommonProperties;

public class DBManager {

	// --- Globals
	Statement stmt;
	Connection conn;

	public DBManager() throws Exception {
		String sDriverName = "org.sqlite.JDBC";
		// now we set up a set of fairly basic string variables to use in the
		// body of the code proper
		CommonProperties props = new CommonProperties();
		String workingDir = props.getWorkingDir();
		//System.out.println("=======================  "+workingDir);
		String sTempDb = workingDir+"\\db\\snsdata.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sTempDb;
		//System.out.println("=======================  "+sDbUrl);
		int iTimeout = 30;
		

		try {
			// register the driver
			Class.forName(sDriverName);
			// create a database connection
			conn = DriverManager.getConnection(sDbUrl);
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Driver Not Found: " + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("Error Connecting database: " + sqle.toString());
		}
		
	}

	// ***********************
	// Public Methods
	// ***********************

	/*
	 ** Returns ResultSet Object obtianed from executing query
	 **
	 ** @param strSql Query String to execute
	 ** 
	 * @return ResultSet
	 */
	public ResultSet getRecord(String strSql) throws SQLException {
		return stmt.executeQuery(strSql);
	}

	/*
	 ** Execute modification query and returns true if success, false if failure
	 **
	 ** @param strSql Query String to upadate/insert/delete
	 ** 
	 * @return boolean, state of success
	 */
	public boolean doQuery(String strSql) throws SQLException {
		//System.out.println(strSql);
		return stmt.executeUpdate(strSql) > 0;
	}

	/*
	 ** close Sataement & Connection cleanup code
	 **
	 */
	public void close() throws SQLException {
		stmt.close();
		conn.close();
		conn = null;
		stmt = null;
		
	}
	
	public Connection getConnection() {
		return conn;
	}
}
