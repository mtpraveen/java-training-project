package entryPoint;
/**
 * 
 */
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * @author Евгений
 * Задание по теме «Файлы. Потоки ввода-вывода»
 * 1.	Программе на вход подается имя каталога.
 * 2.	Необходимо найти все текстовые файлы в этом каталоге, считать их содержимое и записать в новый файл.
 * 3.	Полученный файл нужно упаковать в zip-архив.
 */
public class EntryPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start.");
		
		//using current directory
		//If "TempTextFileResult.txt" file is existing it is overwriting (without reading/writing itself)
		allTxtInOneTxt(".", "TempTextFileResult.txt");
		packTxt("TempTextFileResult.txt", "TempTextFileResult.zip");
		
		System.out.println("End.");
	}
	
	public static void allTxtInOneTxt(String dir, String result){
		File baseFile = new File(dir);
		File resultFile = new File(result);
		if (resultFile.exists())
			resultFile.delete();
		File[] files = baseFile.listFiles();
		List<File> listTextFiles = new ArrayList<File>();
		for (File i : files){
			if (i.isFile() && i.getName().substring(i.getName().length() - 4).equals(".txt")){
				if (i.getName().equals(resultFile.getName()))
					continue;
				listTextFiles.add(i);
			}
		}
		if (listTextFiles.isEmpty())
			return;
		BufferedReader br = null;
		BufferedWriter bw = null;
		String temp = "";
		try{
			resultFile.createNewFile();
			bw = new BufferedWriter(new FileWriter(resultFile));
			for (File i : listTextFiles){
				 br = new BufferedReader(new FileReader(i));
				 while ((temp = br.readLine()) != null)
					 bw.write(temp + "\r\n");
				 br.close();
			}
			bw.close();
		}
		catch(IOException exc){System.out.println("IOException during reading/writing text files" + exc.getMessage());}
	}

	public static void packTxt(String fileName, String packName){
		FileInputStream fis = null;
		ZipOutputStream zos = null;
		File resultZipFile = new File(packName);
		if (resultZipFile.exists())
			resultZipFile.delete();
		byte[] buffer = new byte[1024];
		try{
			 zos = new ZipOutputStream(new FileOutputStream(resultZipFile));
			 zos.putNextEntry(new ZipEntry(fileName));
			 fis = new FileInputStream(fileName);
			 int len;
			 while ((len = fis.read(buffer)) > 0)
				 zos.write(buffer, 0, len);
			 zos.closeEntry();
			 zos.close();
			 fis.close();
		}
		catch(FileNotFoundException exc){System.out.println("FileNotFoundException during packaging: " + exc.getMessage());}
		catch(IOException exc){System.out.println("IOException during packaging: " + exc.getMessage());}
	}
}
