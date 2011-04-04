import java.io.*;
import java.util.zip.*;

//не дружественный интерфейс :) пути к файлам задаются "по умолчанию". Выходной файл задаем без расширения :) Его путь должен быть в отдельном от файлов каталоге....

public class Streams {
		
	public static void main(String[] args) {
		String path = "d:\\Politech\\hobby\\__JAVA\\workspace\\Stream\\1\\OutputFile1"; 
				//k:\\java\\_hier\\1\\1\\1\\OutputFile1.txt";
		File outputFile = new File(path);
		try {
		if (outputFile.createNewFile())
			System.out.println("Файл "+outputFile.getName()+" создан");
		} catch (IOException e) {
			System.err.println(e);}
		
		File dir = new File("d:\\Politech\\hobby\\__JAVA\\workspace\\Stream\\1\\1");//"k:\\java\\_hier\\1\\1\\1\\1");
		//try { 
			if (dir.exists() && dir.isDirectory())
		 System.out.println("каталог " + dir.getName() + " существует");
			
		//File outputFile2 = new File("k:\\java\\_hier\\1\\1\\OutputFile.txt");
	try {
		 FileOutputStream dataOutFile = new FileOutputStream(outputFile);
			
		 File[] files = dir.listFiles();
		 for (int i=0; i < files.length; i++) {
		 System.out.print("\n" + i +" " + files[i].getPath());
		
		 if (files[i].getPath() != outputFile.getPath()){       //так и не понятно, чем отличается путь от абсолютного, и все равно "не работает", не смотря на "правильность" в Debugger'е
			FileInputStream dataInFile = new FileInputStream(files[i]);
			int byteValue = 0;
			while ((byteValue = dataInFile.read()) != -1) {
				dataOutFile.write(byteValue);
			}
			dataInFile.close();
			
		} }
		 dataOutFile.close();
			} 
		 catch (IOException e) {
			System.out.println("Ошибка: 		" + e.toString());
		}
		
		path=path.concat(".zip");   			// признак плохого тона - прямое присваивание :))
		 File zipFile = new File(path);			//в нулевом элементе - имя выходного zip.файла
	 
		 File[] files = dir.listFiles();
	        try {
	        FileOutputStream fos = new FileOutputStream(zipFile);   // открываем выходной поток
	        ZipOutputStream zos = new ZipOutputStream(fos);		//обявляем его zip
	        int bytesRead;
	        byte[] buffer = new byte[1024];				//буфер 
	        CRC32 crc = new CRC32();   				// дополнить проверку
	        for (int i=0, n=files.length; i < n; i++) {
	            String name = files[i].getPath();				// второй элемент массива - первый файл на упаковку
	            File file = new File(name);
	            
	            if (!file.exists()) {
	                System.err.println("Skipping: " + name);
	                continue;
	            }
	            BufferedInputStream bis = new BufferedInputStream(		//открываем входной поток
	                new FileInputStream(file));
	            crc.reset();						//сбрасываем CRC
	            while ((bytesRead = bis.read(buffer)) != -1) {
	                crc.update(buffer, 0, bytesRead);			//"подсчитываем" CRC - buffer - массив с байтами, 0 - начальное значение данных,
																// bytesread - the number of bytes to use for the update

	            }
	            bis.close();								//закрываем входной поток
	            											// Reset to beginning of input stream
	            bis = new BufferedInputStream(				//открываем входной поток
	                new FileInputStream(file));
	            ZipEntry entry = new ZipEntry(name);
	            entry.setMethod(ZipEntry.STORED);				//собсна архивируем
	            entry.setCompressedSize(file.length());			//Sets the size of the compressed entry data.file.length() - the compressed size to set to
	            entry.setSize(file.length());				//Sets the uncompressed size of the entry data.
	            entry.setCrc(crc.getValue());				//Sets the CRC-32 checksum of the uncompressed entry data. crc.getValue() - Returns CRC-32 value.
	            zos.putNextEntry(entry);					// Begins writing a new ZIP file entry and positions the stream to the start of the entry data.
	            while ((bytesRead = bis.read(buffer)) != -1) {	
	                zos.write(buffer, 0, bytesRead);
	            }
	            bis.close();						//закрываем входной поток
	        }
	        zos.close();
	        }
	        catch (IOException e) {
				System.out.println("Ошибка: 		" + e.toString());
			}
	
	}

	}

