package sample;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class IOFuncTest {

	public class OnlyExt implements FilenameFilter {
		private String ext;

		public OnlyExt(String ext) {
			this.ext = "." + ext;
		}

		public boolean accept(File dir, String name) {
			return name.endsWith(ext);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dirname = args[0];
		File f1 = new File(dirname);
		File[] textFiles;
		IOFuncTest test = new IOFuncTest();
		FilenameFilter onlyTxt = test.new OnlyExt("txt");

		if (f1.isDirectory()) {
			System.out.println("Directory " + dirname);
			System.out.println("full size -> " + f1.getTotalSpace() / 1024
					+ " kb");
			System.out.println("free space -> " + f1.getFreeSpace() / 1024
					+ " kb");
			System.out.println("------------------------------");
			String s[] = f1.list();

			for (int i = 0; i < s.length; i++) {
				File f = new File(dirname + "/" + s[i]);
				if (f.isDirectory()) {
					System.out.println(s[i] + " --> directory [" + f.length()
							+ "bytes]");
				} else {
					System.out.println(s[i] + " --> file [" + f.length()
							+ "bytes]");
				}
			}
		} else {
			System.out.println(dirname + " is not a directory");
		}

		textFiles = f1.listFiles(onlyTxt);

		System.out.println("------------------------------");
		System.out.println("All text files in current directory:");
		for (File f : textFiles) {
			System.out.println(f.getName());
		}

		String temp;
		String destFile = "/contents/contents.txt";
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		for (int i = 0; i < textFiles.length; i++) {
			try {
				fr = new FileReader(textFiles[i]);
				br = new BufferedReader(fr);
				fw = new FileWriter(dirname + destFile, true);
				bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw, true);
				while ((temp = br.readLine()) != null) {
					pw.println(temp);
				}
				System.out.println("File " + textFiles[i].getName() + " copied into /contents/" + destFile);
			} catch (IOException e) {
				System.out.println("File Error! -> " + e);
			}
		}
		
		try {
			fr.close();
			br.close();
			fw.close();
			bw.close();
			pw.close();
		} catch (IOException e) {
			System.out.println("Writer\\Reader closing error! -> " + e);
		}
		
		try {
			FileInputStream fis = new FileInputStream(dirname + destFile);
			FileOutputStream fos = new FileOutputStream(dirname + "/contents.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry("contents.txt"); //this is where I should have entered only the filename at first
			BufferedInputStream bis = new BufferedInputStream(fis, 1024);
			zos.putNextEntry(ze);
			
			byte[] buffer = new byte[1024];
			while (bis.read(buffer) > 0) {
				zos.write(buffer);
			}
			
			fis.close();
			bis.close();
			zos.closeEntry();
			zos.finish();
			zos.close();
			
			System.out.println("------------------------------");
			System.out.println("Text file with the contents successfully packed into ZIP.");
		} catch (FileNotFoundException f) {
			System.out.println("File not found! -> " + f);
		} catch (IOException e) {
			System.out.println("File error! -> " + e);
		}
	}

}
