import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Task {
	
	
	
	private static String q="";
	public static void main(String[] args) throws IOException  {
		File dir  = new File("D:\\Files\\");
	
	
	if (dir.exists() && dir.isDirectory()){
			System.out.println("каталог " + dir.getName() + " существует");
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			System.out.println(file+"\nfile.getName() "+file.getName()+"\nfile.getAbsolutePath() "+file.getAbsolutePath()+"\nfile.getPath() "+file.getPath());
			try {
				BufferedReader br = 
				new BufferedReader(new FileReader(file));
							String tmp = "";
							while ((tmp = br.readLine()) != null) {
								
								String[] s = tmp.split("\\n");
								
								for (String res : s){
									System.out.println(res);
									
									q+=res;
									
								}
									
							}	
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				
			}
			}
	System.out.println(q);
	
	

	String filenames[] = { "test.txt" };
	FileOutputStream fout = new FileOutputStream("D:\\Files\\test.txt"); 
		
	FileWriter fw = new FileWriter("test.txt",true); 

	 fw.write(q); 


	ZipOutputStream zout = new ZipOutputStream(fout);
	for (String filename : filenames) {
		ZipEntry ze = new ZipEntry(filename);
		zout.putNextEntry(ze);
	
		zout.closeEntry();
	}
	zout.close();
	
	
}
	
}



