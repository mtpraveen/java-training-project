/**
 * 
 */
package by.brsu.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author epam0003
 *
 */
public class IOClass {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String dirName="alex";
		File f=new File(dirName);
		Pattern pat=Pattern.compile(".txt");
		Matcher matcher;
		File fInput=new File("sss.txt");
		FileWriter fw=new FileWriter(fInput,true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		if (f.isDirectory()) 
		{
			String s[]=f.list();
			for(int i=0;i<s.length;i++)
			{
				File fSearch=new File(dirName+"\\"+s[i]);
				if (fSearch.isFile())
				{
					matcher = pat.matcher(fSearch.getName());
					if (matcher.find())
					{
						BufferedReader br = new BufferedReader(new FileReader(fSearch.getPath()));
						String tmp = "";
						while ((tmp = br.readLine()) != null) {
							String[] s1 = tmp.split("\\s");
							for (String res : s1){
								pw.println(res);
							}
						}
						
					}
				}
			}
			pw.close();
			FileOutputStream fos = new FileOutputStream("zipFile.zip");
		    ZipOutputStream zos = new ZipOutputStream(fos);
		    int bytesRead;
	        byte[] buffer = new byte[1024];
		    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fInput));
            ZipEntry entry = new ZipEntry(fInput.getPath());
            entry.setCompressedSize(fInput.length());
            entry.setSize(fInput.length());;
            zos.putNextEntry(entry);
            while ((bytesRead = bis.read(buffer)) != -1) {

                zos.write(buffer, 0, bytesRead);
            }
            bis.close();
            zos.close();
		}
	}
}
