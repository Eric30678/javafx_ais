//package application;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//
//
//
//
//
///**
// * @author Eric Wu
// *
// * @version 1.0 2021年8月9日下午6:36:01
// */
//public class sql_test {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	static Connection conn = null;
//	static Statement st = null;
//	ResultSet rs = null;
//	static String url = "jdbc:sqlite://localhost:3306/sql_tutorial";
//	String user = "root";
//	String password = "admin123";
//	static List<String[]> mmsi2 = new ArrayList<String[]>();
//	public sql_test() {
//		// TODO Auto-generated constructor stub
//		try {
//			String url = "jdbc:mysql://localhost:3306/sql_tutorial";
//			String user = "root";
//			String password = "admin123";
//			String sql = "CREATE TABLE test"+
//					 "(mmsi_id int AUTO_INCREMENT,"+
//					 "mmsi varchar(30),"+
//					 "Longitude varchar(30),"+ 
//					 "Latitude varchar(30),"+ 
//					 "Record_Time DATETIME,"+
//					 "PRIMARY KEY (mmsi_id))";
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url,user,password);
//			System.out.println("成功連結資料庫");
//			
//			st = conn.createStatement();
//			DatabaseMetaData dbm = conn.getMetaData();
//			// check if "employee" table is there
//			ResultSet tables = dbm.getTables(null, null, "test", null);
//			if (tables.next()) {
//			  // Table exists
//				System.out.println("已有此資料庫");
//				System.out.println(tables.next());
//			}
//			else {
//			  // Table does not exist
//				System.out.println(tables.next());
//				System.out.println("無資料庫，正在創建資料庫");
//				st.executeUpdate(sql);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("錯誤");
//		}
// 	}
//	public static void insert_data(String[] mmsi2) {
//		try {
////          Statement st = conn.createStatement(); 
////			st = conn.createStatement();			
//			st.executeUpdate("INSERT INTO `test` (mmsi,Longitude,Latitude,Record_Time) " + "VALUES ('"+mmsi2[0]+"',"+mmsi2[1]+","+mmsi2[2]+",'"+mmsi2[3]+"');");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void read_sql() {
//		try {
//			ResultSet rst = st.executeQuery("select * FROM test;");
//			ResultSetMetaData meta = rst.getMetaData();
//			for (int i = 0; i < meta.getColumnCount(); i++) {
//
//				System.out.print(meta.getColumnName(i + 1) + "\t");
//				}
//			System.out.println();
//			while (rst.next()) {
//				for (int i = 0; i < meta.getColumnCount(); i++) {
//					System.out.print(rst.getObject(i + 1) + "\t");
//				}
//					System.out.println();
//				}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static ArrayList distinct_sql() {
//		ArrayList mmsi1 = new ArrayList();
//		String sqlString = "select distinct mmsi from test;";
//		try {
//			ResultSet rst = st.executeQuery(sqlString);
//			ResultSetMetaData meta = rst.getMetaData();
//			for (int i = 0; i < meta.getColumnCount(); i++) {
//				System.out.print(meta.getColumnName(i + 1) + "\t");
//				}
//			System.out.println();
//			while (rst.next()) {
//				for (int i = 0; i < meta.getColumnCount(); i++) {
//					String a1 = rst.getString(i + 1);
//					mmsi1.add(a1);
////					System.out.print(rst.getObject(i + 1) + "\t");
//				}
////					System.out.println();
//				}			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return mmsi1;
//	}
//
//	public static void close_sql() {
//		try {
//			st.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void order_sql(String str1) {
//		String sql = "SELECT * FROM test where mmsi='"+str1+"' AND (Longitude BETWEEN 121.750 AND 121.875) AND (Latitude BETWEEN 24.375 AND 24.500) ORDER BY Record_Time ASC";
//		
////		String[] a1;
//		String[] a1 = new String[5];
//		
//		try {
//			ResultSet rst = st.executeQuery(sql);
//			ResultSetMetaData meta = rst.getMetaData();
////			a1 = new String[5];
//			for (int i = 0; i < meta.getColumnCount(); i++) {
//				System.out.print(meta.getColumnName(i + 1) + "\t");
//				
//				a1[i] = meta.getColumnName(i + 1);
//				}
//			System.out.println();
//			mmsi2.add(a1);
//			a1 = new String[5];
//			while (rst.next()) {
//				for (int i = 0; i < meta.getColumnCount(); i++) {
//					a1[i] = rst.getString(i + 1);
//					System.out.print(rst.getObject(i + 1) + "\t");
//				}
//					mmsi2.add(a1);
//					a1 = new String[5];
//					System.out.println();
//				}	
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		return mmsi2;
//	}
//	private static void write_csv(List<String[]> list1) throws IOException {
//		// TODO Auto-generated method stub
//		FileOutputStream os = new FileOutputStream("new.csv");
//		os.write(0xef);
//		os.write(0xbb);
//		os.write(0xbf);
//		CSVWriter writer = new CSVWriter(new OutputStreamWriter(os));
//		
//		try {
//		    writer.writeAll(list1);
//		    writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	public static void main(String[] args) {
//		new sql_test();
//		List<String[]> a11 = new ArrayList<String[]>();
//		read_sql();
//		ArrayList<String> a1 = distinct_sql();
//		for (Iterator iterator = a1.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			order_sql(string);
////			System.out.println(string);
//			}
//		try {
//			write_csv(mmsi2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		close_sql();
//	}
//}