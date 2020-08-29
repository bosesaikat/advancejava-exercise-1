package com.masterdevskills.cha3.ex3;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

//TODO: Implement this thread pool using ExecutorService
public class ThreadPool {

	private final ExecutorService service;
	private final BlockingQueue<Runnable> taskList = new LinkedBlockingQueue<>();

	public ThreadPool(int poolSize) {
		service = Executors.newFixedThreadPool(poolSize);
	}

	private Runnable take() throws InterruptedException {
		return taskList.poll();
	}

	public void submit(Runnable job) {
		taskList.add(job);
		try {
			service.submit(take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getRunQueueLength() {
		return taskList.size();
	}

	public void shutdown() {
		service.shutdown();
	}

}


