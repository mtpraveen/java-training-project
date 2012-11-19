package com.epam.training.zipworkertest;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.epam.training.data.ZipWorker;

public class ZipWorkerTest {

	@Test
	public void testAddFilesToZip() {
		System.out.println(System.getProperty("user.dir") + "\\data\\" + "users.txt");
		String parentFolder = System.getProperty("user.dir") + "\\data\\";
		File f1 = new File(parentFolder + "users.txt");
		File f2 = new File(parentFolder + "rooms.txt");
		File f3 = new File(parentFolder + "applications.txt");
		
		File[] files = new File[] { f1, f2, f3 };
		
		ZipWorker.addFilesToZip(files, parentFolder + "data.zip");
	}
	
//	@Test
//	public void testUnzipFile() {
//		File zipFile = new File("d:\\MyZip.zip");
//		ZipWorker.unzipFile(zipFile);
//	}
//	
//	@Test
//	public void testDeleteFile() throws InterruptedException {
//		Thread.sleep(3000);		
//		System.out.println(ZipWorker.deleteFile(new File("d:\\1.txt")));
//		Thread.sleep(1000);
//		System.out.println(ZipWorker.deleteFile(new File("d:\\2.txt")));
//		Thread.sleep(1000);
//		System.out.println(ZipWorker.deleteFile(new File("d:\\3.txt")));
//		Thread.sleep(1000);
//		System.out.println(ZipWorker.deleteFile(new File("d:\\MyZip.zip")));
//		
//	}
//	
//	@Test
//	public void testDeleteFolder() {
//		ZipWorker.deleteFolder("d:\\fordeleating");
//	}
//	
//	@Test
//	public void testChechFiles() {
//		File[] files = new File[] { 
//				new File("d:\\1.txt"),
//				new File("d:\\2.txt"),
//				new File("d:\\3.txt")
//		};
//		
//		File zipFile = new File("d:\\MyZip.zip");
//		
//		assertTrue(ZipWorker.checkFiles(zipFile, files));
//	}

}
