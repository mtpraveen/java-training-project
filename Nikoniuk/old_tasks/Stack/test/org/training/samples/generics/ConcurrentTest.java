package org.training.samples.generics;

import static junit.framework.Assert.fail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.training.samples.generics.Stack.NullPointer;



public class ConcurrentTest {
	private Stack<Integer> s1 = new Stack<Integer>();
	
	@Test
	public void threadSafeTest() {

		ExecutorService threads = Executors.newFixedThreadPool(10);
		for (int i=0;i<5;i++){
			threads.execute(new Runnable(){

				@Override
				public void run() {
					try {
						s1.push(10);
						s1.push(20);
						s1.pop();
						s1.pop();
					} catch (NullPointer e) {
						fail("Stack is not thread safe");
					}
					
				}});
		}
		threads.shutdown();
	}

}
