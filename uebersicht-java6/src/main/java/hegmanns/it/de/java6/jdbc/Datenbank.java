package hegmanns.it.de.java6.jdbc;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import org.apache.derby.jdbc.EmbeddedDriver;

public class Datenbank {
	
	private static final String dbUrl = "jdbc:derby:./derbydb;create=true";
	private static final String dbUsername = "app";
	private static final String dbPassword = "app";
	
	private String tabellenname;
	public Datenbank(String tabellenname){
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
		RowSetFactory rowsetFactory = RowSetProvider.newFactory();
		
		JdbcRowSet rowset = configureRowset(rowsetFactory);
		rowset.setCommand("create table " + tabellenname  + " (id int not null, daten varchar(200))");


		rowset.execute();
	}
	
	public void datenEinfuegen(String  daten) throws Exception{
		RowSetFactory rowsetFactory = RowSetProvider.newFactory();
		JdbcRowSet rowset = configureRowset(rowsetFactory);
		rowset.setCommand("insert into " + tabellenname + " (id, daten) values (1, '" + daten + "')");
		rowset.execute();
		
		if (!rowset.rowInserted()){
			throw new RuntimeException("");
		}
	}
	
	public String leseDaten() throws Exception
	{
		RowSetFactory rowsetFactory = RowSetProvider.newFactory();
		JdbcRowSet rowset = configureRowset(rowsetFactory);
		rowset.setCommand("select daten from " + tabellenname);
		rowset.execute();
		
		if (rowset.next()){
			return rowset.getString(2);
		}
		return null;
	}
}
