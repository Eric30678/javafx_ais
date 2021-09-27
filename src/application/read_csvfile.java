package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import application.sqlite_test;
import application.sceneController;
public class read_csvfile {
	
	String a = sceneController.return_a();
	public read_csvfile() throws IOException {
		try {
//			sqlite_test.delete_sql();
			new sqlite_test();
			InputStreamReader isr = new InputStreamReader(new FileInputStream(a));//檔案讀取路徑
			BufferedReader reader = new BufferedReader(isr);
			String line = null;
			int a1 = 0;
			while((line=reader.readLine())!=null){
				if(a1 == 0) {
					a1 = 1;
				}else {
				String item[] = line.split(",");
			      /** 讀取 **/
				String[] a = new String[4];
				String  data1= item[4].trim();
				String  data2= item[9].trim();
				String  data3= item[10].trim();
				String  data4= item[24].trim();
				a[0] = data1;
				a[1] = data2;
				a[2] = data3;
				a[3] = data4;
				sqlite_test.insert_sql(a);
//				sqlite_test.read_sql();
//				System.out.print(data1+"\t"+ data2+"\t"+ data3+"\t"+data4+"\n"); 
				}
			}
			sqlite_test.read_sql();
//			sqlite_test.delete_sql();

			
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		
	}
//	public static void main(String[] args) {
//		try {
//			new read_csvfile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
