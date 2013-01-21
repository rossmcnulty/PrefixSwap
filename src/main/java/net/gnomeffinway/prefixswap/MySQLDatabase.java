package net.gnomeffinway.prefixswap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLDatabase
{
  private final String host;
  private final String port;
  private final String username;
  private final String password;
  private final String database;
  private final String url;
  public Connection connection;
  
  public final String getHost()
  {
    return this.host;
  } 
  
  public final String getPort() {
    return this.port;
  } 
  
  public final String getUsername() {
    return this.username;
  } 
  
  public final String getPassword() {
    return this.password;
  } 
  
  public final String getDatabase() {
    return this.database;
  } 
  
  public final String getUrl() {
    return this.url;
  } 
  
  public final Connection getConnection() {
    return this.connection;
  } 
  
  public MySQLDatabase(String host, String port, String username, String password, String database)
  {
    this.host = host;
    this.port = port;
    this.username = username;
    this.password = password;
    this.database = database;
    this.url = ("jdbc:mysql://" + host + ":" + port + "/" + database);
  } 
  
  public Connection open() throws Exception {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      this.connection = DriverManager.getConnection(this.url, this.username, this.password);
      return this.connection;
    } catch (Exception e) {
      throw e;
    } 
  } 
  
  public void close() throws Exception {
    try {
      if (this.connection != null)
        this.connection.close(); 
    } catch (Exception e) {
      throw e;
    } 
  } 
  
  public ResultSet query(String sql) throws Exception
  {
    try
    {
      Statement statement = this.connection.createStatement();
      return statement.executeQuery(sql);
    }
    catch (Exception e) {
      throw e;
    } 
  } 
}
