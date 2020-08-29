package com.masterdevskills.cha3.ex1;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO: Create a thread group field
// Create a LinkedList field containing Runnable
// Hint: Since LinkedList is not thread-safe, we need to synchronize it
public class ThreadPool {

  private final ThreadGroup threadGroup = new ThreadGroup("group 1");
  private final LinkedList<Runnable> taskList = new LinkedList<>();
  private int poolSize;
  private final List<Worker> workers;

  public ThreadPool(int poolSize) {
    this.poolSize = poolSize;
    workers = IntStream.range(0, poolSize)
        .mapToObj(value -> new Worker(threadGroup, "Worker " + value))
        .collect(Collectors.toList());
    workers.forEach(Thread::start);
  }

  private Runnable take() throws InterruptedException {
    synchronized (taskList) {
      while (getRunQueueLength() == 0) {
        taskList.wait(1000);
      }
      Runnable job = taskList.poll();
      taskList.notifyAll();
      return job;
    }
  }

  public void submit(Runnable job) {
    synchronized (taskList) {
      taskList.add(job);
      taskList.notifyAll();
    }
  }

  public int getRunQueueLength() {
    //TODO return the length of the LinkedList
    // remember to also synchronize!
    synchronized (taskList) {
      return taskList.size();
    }
  }

  public void shutdown() {
    threadGroup.interrupt();
  }

  private class Worker extends Thread {

    public Worker(ThreadGroup group, String name) {
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
          task = take();
          task.run();
        } catch (InterruptedException e) {
          currentThread().interrupt();
        }
      }
    }
  }
}


