package hegmanns.it.de.java6.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import org.apache.commons.io.FileUtils;
import org.apache.derby.jdbc.EmbeddedDriver;

public class DatenbankLifecycle {
	
	private static final String dbUrl = "jdbc:derby:./derbydb;create=true";
	private static final String dbUsername = "app";
	private static final String dbPassword = "app";
	
	private String tabellenname;
	public DatenbankLifecycle(String tabellenname){
		this.tabellenname = tabellenname;
		
		EmbeddedDriver driver = new EmbeddedDriver();
	}
	
	private JdbcRowSet configureRowset(RowSetFactory rowsetFactory) throws SQLException
	{
		JdbcRowSet rowset = rowsetFactory.createJdbcRowSet();

		rowset.setUrl(dbUrl);
		rowset.setUsername(dbUsername);
		rowset.setPassword(dbPassword);
		
		return rowset;
	}

	public void tabelleErstellen() throws Exception
	{
		loeschen();
		try(Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword) ; 
		    Statement statement = connection.createStatement()){
			
			statement.execute("create table " + tabellenname  + " (id int not null generated always as identity (start with 1, increment by 1), daten varchar(200))");
			
		}
		
	}
	
	/**
	 * Fuegt einne String in die verwaltete Datenbank/Tabelle ein.
	 * 
	 * @param daten
	 * @throws Exception
	 */
	public void datenEinfuegen(String  daten) throws Exception{
		
		try(Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword) ; 
			    Statement statement = connection.createStatement()){
				
				statement.execute("insert into " + tabellenname + " ( daten) values ('" + daten + "')");
				
			}

	}
	
	/**
	 * Liest die erste Zeile.
	 * @return
	 * @throws Exception
	 */
	public String leseDaten() throws Exception
	{
		RowSetFactory rowsetFactory = RowSetProvider.newFactory();
		JdbcRowSet rowset = configureRowset(rowsetFactory);
		rowset.setCommand("select daten from " + tabellenname);
		rowset.execute();
		
		String daten = null;
		if (rowset.next())
		{
			daten = rowset.getString(1);
		}
		rowset.close();
		return daten;
	}
	
	public void loeschen() throws IOException
	{
		File file = new File("./derbydb");
		if (file.exists())
		{
		FileUtils.deleteDirectory(file);
		}
	}
}
