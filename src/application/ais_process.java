//package application;
//import java.awt.event.ActionEvent;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Scanner;
//
//import javax.swing.JFileChooser;
//
//
//import application.sql_test;
//import com.opencsv.*;
///**
// * @author Eric Wu
// *
// * @version 1.0 2021年8月8日上午8:33:05
// */
//public class ais_process {
//    static class DateItem {
//    	String mmsi_str;
//        String datetime;
//        String Longitude;
//        String Latitude;
//        DateItem(String[] mmsi_str1) {
//            this.mmsi_str = mmsi_str1[0];
//            this.Longitude = mmsi_str1[1];
//            this.Latitude = mmsi_str1[2];
//            this.datetime = mmsi_str1[3];
//        }
//    }
//    static class SortByDate implements Comparator<DateItem> {
//        public int compare(DateItem a, DateItem b) {
//            return a.datetime.compareTo(b.datetime);
//        }
//    }
//	File file;
//	Scanner filein;
//	int response;
//	int i5 = 0;
//	ArrayList<String> mmsi = new ArrayList<String>();
//	ArrayList<String[]> mmsi1 = new ArrayList<String[]>();
//	List<String[]> mmsi2 = new ArrayList<String[]>();
//	ArrayList newlist = new ArrayList();
//	public ais_process() {
//		new sql_test();
//		final JFileChooser fc = new JFileChooser(".");
//		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		response = fc.showOpenDialog(null);
//		if (response == JFileChooser.APPROVE_OPTION) {
//			file = fc.getSelectedFile();
//			try {
//				filein = new Scanner(file);
//				if (file.isFile()) {
//					while (filein.hasNextLine()) {
//						String line = filein.nextLine();
//						String[] list2 = new String[2];
//						if(i5==0) {
//							i5 += 1;
//						}else {
//							String a5 = "";
//							String[] a = line.split(",");
//							String[] a1 = new String[4];
//							a1[0] = a[4];
//							a1[1] = a[9];
//							a1[2] = a[10];
//							a1[3] = a[24];
//							sql_test.insert_data(a1);
//							
////							System.out.println(a1[0]);
////							System.out.println(a1[1]);
////							System.out.println(a1[2]);
//							mmsi1.add(a1);
//							mmsi.add(a[4]);
////							System.out.println(mmsi);
////							System.out.println(mmsi1);
//							newlist = getsingle(mmsi);
//							//System.out.println(newlist);
//						}
//						//System.out.println(line);
//					}
//				}
//			}catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}	
//			sql_test.read_sql();
//			System.out.println("資料庫已關閉");		
//			}
////	    JFrame f = new JFrame("JFrame 1");
////	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////	    f.setBounds(0,0,400,300);
////	    f.setVisible(true);
////	    JButton button = new JButton();
////	    button.setBounds(200,100,100,50);
//		mmsi2 = str_process(newlist, mmsi1);
//		try {
//			write_csv(mmsi2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	private static List<String[]> str_process(ArrayList newlist ,ArrayList<String[]> mmsi1 ) {
//		List<String[]> mmsi5 = new ArrayList<String[]>();
//		String a2 = "";
//		String[] list2 = new String[2];
//		list2[0] = "MMSI";
//		list2[1] = "POLYLINE(WKT)";
//		mmsi5.add(list2);
////		int i1 = 0;
////		int i2 = 0;
//		for (Iterator iterator = newlist.iterator(); iterator.hasNext();) {
//			int i = 0;
//			list2 = new String[2];
//			String object = (String) iterator.next();
//			list2[0] = object;
//			//System.out.println(object);
//			List<DateItem> dateList = new ArrayList<>();
//			for(Iterator<String[]> iterator2 = mmsi1.iterator(); iterator2.hasNext();) {
//				String[] object2 = (String[]) iterator2.next();
////				System.out.println(object2[0]);
//				if (object2[0].equals(object)) {
//					if(i == 0) {
//						dateList.add(new DateItem(object2));
//						
//						System.out.println(i);
//						a2 = a2+object2[1]+"\s"+object2[2];
//						i += 1;
//					}else if(i >= 1) {
//						dateList.add(new DateItem(object2));
////						System.out.println(i);
////						System.out.println(object2[0]+" "+object);
//						a2 =  a2+"," + object2[1]+"\s"+object2[2];
//					}
//				}
//			}
//			Collections.sort(dateList, new SortByDate());
//			String a3 = "POLYLINE ("+a2+")";
//			list2[1] = a3;
//			mmsi5.add(list2);
//			a2 = "";
////			System.out.println(list2[1]);
//		}
//		return mmsi5;
//	}
//
//
//
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//	
//	public static  ArrayList getsingle(ArrayList list) {
//		ArrayList tempList = new ArrayList();
//		Iterator it = list.iterator(); 
//		while (it.hasNext()) {
//			Object obj = it.next();
//			if(!tempList.contains(obj)) {
//				//System.out.println(obj);
//				tempList.add(obj);
//			}
//		}
//		return tempList;
//	}
//	private void write_csv(List<String[]> list1) throws IOException {
//		// TODO Auto-generated method stub
//		FileOutputStream os = new FileOutputStream("new.csv");
//		os.write(0xef);
//		os.write(0xbb);
//		os.write(0xbf);
//		CSVWriter writer = new CSVWriter(new OutputStreamWriter(os));
//		try {
//		    writer.writeAll(list1);
//		    writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void main(String[] args) {
//		try {
//			new ais_process();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//}
