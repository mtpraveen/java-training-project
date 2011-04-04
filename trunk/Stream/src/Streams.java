import java.io.*;
import java.util.zip.*;

//�� ������������� ��������� :) ���� � ������ �������� "�� ���������". �������� ���� ������ ��� ���������� :) ��� ���� ������ ���� � ��������� �� ������ ��������....

public class Streams {
		
	public static void main(String[] args) {
		String path = "d:\\Politech\\hobby\\__JAVA\\workspace\\Stream\\1\\OutputFile1"; 
				//k:\\java\\_hier\\1\\1\\1\\OutputFile1.txt";
		File outputFile = new File(path);
		try {
		if (outputFile.createNewFile())
			System.out.println("���� "+outputFile.getName()+" ������");
		} catch (IOException e) {
			System.err.println(e);}
		
		File dir = new File("d:\\Politech\\hobby\\__JAVA\\workspace\\Stream\\1\\1");//"k:\\java\\_hier\\1\\1\\1\\1");
		//try { 
			if (dir.exists() && dir.isDirectory())
		 System.out.println("������� " + dir.getName() + " ����������");
			
		//File outputFile2 = new File("k:\\java\\_hier\\1\\1\\OutputFile.txt");
	try {
		 FileOutputStream dataOutFile = new FileOutputStream(outputFile);
			
		 File[] files = dir.listFiles();
		 for (int i=0; i < files.length; i++) {
		 System.out.print("\n" + i +" " + files[i].getPath());
		
		 if (files[i].getPath() != outputFile.getPath()){       //��� � �� �������, ��� ���������� ���� �� �����������, � ��� ����� "�� ��������", �� ������ �� "������������" � Debugger'�
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
			System.out.println("������: 		" + e.toString());
		}
		
		path=path.concat(".zip");   			// ������� ������� ���� - ������ ������������ :))
		 File zipFile = new File(path);			//� ������� �������� - ��� ��������� zip.�����
	 
		 File[] files = dir.listFiles();
	        try {
	        FileOutputStream fos = new FileOutputStream(zipFile);   // ��������� �������� �����
	        ZipOutputStream zos = new ZipOutputStream(fos);		//�������� ��� zip
	        int bytesRead;
	        byte[] buffer = new byte[1024];				//����� 
	        CRC32 crc = new CRC32();   				// ��������� ��������
	        for (int i=0, n=files.length; i < n; i++) {
	            String name = files[i].getPath();				// ������ ������� ������� - ������ ���� �� ��������
	            File file = new File(name);
	            
	            if (!file.exists()) {
	                System.err.println("Skipping: " + name);
	                continue;
	            }
	            BufferedInputStream bis = new BufferedInputStream(		//��������� ������� �����
	                new FileInputStream(file));
	            crc.reset();						//���������� CRC
	            while ((bytesRead = bis.read(buffer)) != -1) {
	                crc.update(buffer, 0, bytesRead);			//"������������" CRC - buffer - ������ � �������, 0 - ��������� �������� ������,
																// bytesread - the number of bytes to use for the update

	            }
	            bis.close();								//��������� ������� �����
	            											// Reset to beginning of input stream
	            bis = new BufferedInputStream(				//��������� ������� �����
	                new FileInputStream(file));
	            ZipEntry entry = new ZipEntry(name);
	            entry.setMethod(ZipEntry.STORED);				//������ ����������
	            entry.setCompressedSize(file.length());			//Sets the size of the compressed entry data.file.length() - the compressed size to set to
	            entry.setSize(file.length());				//Sets the uncompressed size of the entry data.
	            entry.setCrc(crc.getValue());				//Sets the CRC-32 checksum of the uncompressed entry data. crc.getValue() - Returns CRC-32 value.
	            zos.putNextEntry(entry);					// Begins writing a new ZIP file entry and positions the stream to the start of the entry data.
	            while ((bytesRead = bis.read(buffer)) != -1) {	
	                zos.write(buffer, 0, bytesRead);
	            }
	            bis.close();						//��������� ������� �����
	        }
	        zos.close();
	        }
	        catch (IOException e) {
				System.out.println("������: 		" + e.toString());
			}
	
	}

	}

