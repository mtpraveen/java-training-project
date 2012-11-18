package com.epam.training.zipworkertest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.epam.training.data.FileFinder;
import com.epam.training.data.ZipWorker;

public class ZipWorkerTest {

//	@Test
//	public void testAddFilesToZip() {
//		File f1 = new File("d:\\1.txt");
//		File f2 = new File("d:\\2.txt");
//		File f3 = new File("d:\\3.txt");
//		
//		File[] files = new File[] { f1, f2, f3 };
//		
//		ZipWorker.addFilesToZip(files, "d:\\MyZip.zip");
//	}
	
//	@Test
//	public void testUnzipFile() {
//		File zipFile = new File("d:\\MyZip.zip");
//		ZipWorker.unzipFile(zipFile);
//	}
	
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
	
	@Test
	public void testDeleteFolder() {
		ZipWorker.deleteFolder("d:\\fordeleating");
	}

}
