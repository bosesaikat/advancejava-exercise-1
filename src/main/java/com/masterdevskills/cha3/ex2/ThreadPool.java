package com.masterdevskills.cha3.ex2;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO: Implement this thread pool using BlockingQueue
public class ThreadPool {

  private final BlockingQueue<Runnable> taskList = new LinkedBlockingQueue<>();
  private final ThreadGroup threadGroup = new ThreadGroup("group 1");
  private final List<Job> workers;

  public ThreadPool(int poolSize) {
    workers = IntStream.range(0, poolSize)
        .mapToObj(value -> new Job(threadGroup, "Worker " + value))
        .collect(Collectors.toList());
    workers.forEach(Thread::start);
  }

  private Runnable take() throws InterruptedException {
    return taskList.poll();
  }

  public void submit(Runnable job) {
    taskList.add(job);
  }

  public int getRunQueueLength() {
    return taskList.size();
  }

  public void shutdown() {
    threadGroup.interrupt();
  }

  private class Job extends Thread {

    public Job(ThreadGroup group, String name) {
      super(group, name);
    }

    public void run() {
      //TODO
      // we run in an infinite loop:
      // remove the next job from the linked list using take()
      // we then call the run() method on the job
      while (true) {
        Runnable task;
        try {
          take().run();
        } catch (InterruptedException e) {
          currentThread().interrupt();
        }
      }
    }
  }
}


