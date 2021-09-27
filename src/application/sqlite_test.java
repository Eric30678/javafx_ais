package application;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sqlite_test{
	static Connection c = null;
    static Statement stmt = null;
    static List<String> mmsi1 = new ArrayList<String>();
    String url = "jdbc:sqlite:test.db";
//    String[] a= {"13213213","1231213213","1321321321","2020/01/15 12:00"};
	public sqlite_test() {
		// TODO Auto-generated constructor stub
	    try{
	    	if(c==null) {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
			}
	    	stmt = c.createStatement();
	    	System.out.println("Opened database successfully");
	    	String sql = "CREATE TABLE IF NOT EXISTS test "+
					 "(mmsi_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					 "mmsi varchar(30),"+
					 "Longitude varchar(30),"+ 
					 "Latitude varchar(30),"+ 
					 "Record_Time DATETIME);";
	    	DatabaseMetaData dbm = c.getMetaData();
	    	ResultSet tables = dbm.getTables(null, null, "test", null);
			if (tables.next()) {
			  // Table exists
				System.out.println("已有此資料庫");
				System.out.println(tables.next());
			}
			else {
			  // Table does not exist
				System.out.println(tables.next());
				System.out.println("無資料庫，正在創建資料庫");
				stmt.executeUpdate(sql);
			}
	      
//	      stmt.close();
//	      c.close();
//	      insert_sql();
//	      read_sql();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}
	
	
	public static void insert_sql(String[] a) throws SQLException {
		
		try {
			
			if(c==null) {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
			}
			stmt = c.createStatement();
		    c.setAutoCommit(false);
		    System.out.println("Opened database successfully");
			String sql = "INSERT INTO test (mmsi,Longitude,Latitude,Record_Time)"+
						 "VALUES ('" + a[0] + "','" + a[1] + "','" + a[2] + "','" + a[3] + "');"; 
			stmt.executeUpdate(sql);
//		    stmt.close();
//		    c.commit();
//		    c.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void read_sql() throws SQLException {
		try {
			if                                                  (c==null) {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
			}
		    c.setAutoCommit(false);
		    System.out.println("Opened database successfully");

		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM test;" );
		    while ( rs.next() ) {
		    String id = rs.getString("mmsi_id");
		    String mmsi = rs.getString("mmsi");
		    String Longitude  = rs.getString("Longitude");
		    String Latitude = rs.getString("Latitude");
		    String datetime = rs.getString("Record_Time");
		    System.out.println(  id + "\t" + mmsi +"\t" + Longitude +"\t" + Latitude +"\t" + datetime);
		    }
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
}
	public static void delete_sql() throws SQLException {
		try {
			if(c==null) {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
				
			}
		    c.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    stmt = c.createStatement();
		    stmt.executeUpdate( "drop table test;" );
		    stmt.close();
		    c.commit();
		    c.close();
//		    while ( rs.next() ) {
//		    String id = rs.getString("mmsi_id");
//		    String mmsi = rs.getString("mmsi");
//		    String Longitude  = rs.getString("Longitude");
//		    String Latitude = rs.getString("Latitude");
//		    String datetime = rs.getString("Record_Time");
//		    System.out.println( "ID = " + id + "\t" + "mmsi = " + mmsi +"\t"+"Longitude = " + Longitude +"\t"+"ADDRESS = " + Latitude +"\t"+"SALARY = " + datetime);
//		}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static List<String> distinct_sql() throws ClassNotFoundException {
		String sql = "select distinct mmsi from test;";
		try {
			if(c==null) {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
			}
		    c.setAutoCommit(false);
//		    System.out.println("Opened database successfully");
		    stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
			    String mmsi = rs.getString("mmsi");
			    System.out.println(mmsi);
			    try {
					mmsi1.add(mmsi);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mmsi1;
	}
	
	
	public static void order_sql(String str1) {
		String sql = "SELECT Record_Time FROM test where mmsi='"+str1+"' AND (Longitude BETWEEN 121.750 AND 121.875) AND (Latitude BETWEEN 24.375 AND 24.500) ORDER BY Record_Time ASC";
		ArrayList<String[]> mmsi2 = new ArrayList<String[]>();
//		String[] a1;
		String[] a1 = new String[5];
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData meta = rs.getMetaData();
//			a1 = new String[5];
			for (int i = 0; i < meta.getColumnCount(); i++) {
				System.out.print(meta.getColumnName(i + 1) + "\t");
				
				a1[i] = meta.getColumnName(i + 1);
				}
			System.out.println();
			mmsi2.add(a1);
			a1 = new String[5];
			while (rs.next()) {
				for (int i = 0; i < meta.getColumnCount(); i++) {
					a1[i] = rs.getString(i + 1);
					System.out.print(rs.getObject(i + 1) + "\t");
				}
					mmsi2.add(a1);
					a1 = new String[5];
					System.out.println();
				}	
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return mmsi2;
	}
//	public static List<String> return_mmsi1() {
//		try {
//			new sqlite_test().distinct_sql();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//		return mmsi1;
//	}
//	
	public static String selectdate_sql(String str1,String str2,String str3) {
		String sql = "SELECT * FROM test where mmsi='"+str1+"' AND (Longitude BETWEEN 121.750 AND 121.875) AND (Latitude BETWEEN 24.375 AND 24.500) AND Record_Time BETWEEN '"+str2+"' AND '"+str3+"' ORDER BY Record_Time ASC";
		ArrayList<String[]> mmsi2 = new ArrayList<String[]>();
//		String[] a1;
		String a2 = new String();
		String[] a1 = new String[5];
		try {
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData meta = rs.getMetaData();
//			a1 = new String[5];
			for (int i = 0; i < meta.getColumnCount(); i++) {
//				if() {
//				System.out.print(meta.getColumnName(i + 1) + "\t");
				
				a1[i] = meta.getColumnName(i + 1);
				}
			System.out.println();
			mmsi2.add(a1);
			a1 = new String[5];
			a2 = "LINESTRING("; 
			while (rs.next()) {
				for (int i = 0; i < meta.getColumnCount(); i++) {
					int i2 = 0; 
					a1[i] = rs.getString(i + 1);
					
					System.out.print(rs.getObject(i + 1) + "\t");
					if(i == 2 || i == 3) {
						
						String i1 = rs.getString(i + 1);
						
						a2 = a2+i1+" ";
						i2 =+ 1;
					}
					System.out.println("\n");
				}
				
					mmsi2.add(a1);
					a1 = new String[5];
				}	
			a2 += ")";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a2;
	}
}