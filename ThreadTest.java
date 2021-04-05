class MyThread extends Thread{

    //Creating thread process 1
    public MyThread(String name){
        super(name);
    }
    //end

    //default constructor
    public MyThread(){
    }

    @Override
    public void run(){
        //System.out.println("2. Hello world! from "+Thread.currentThread().getName());
        //System.out.println(Thread.currentThread().getName());

        for (int i = 0; i < 5; i++){
            //to pause long task for short tasks- yield();
            System.out.println("This output is from "+ getName() + ": "+ i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//Creating thread process 2: implementing the runnable interface
class MyThreadFromRunnable implements Runnable{
    @Override
    public void run() {
        //System.out.println("3. Hello world! from "+Thread.currentThread().getName());
    }
}

//another way of creating asynchronous task execution by implementing the executor interface

public class ThreadTest {
    public static void main(String[] args){
        //System.out.println("1. Hello world! from Main method");
        //executed using current thread

        //System.out.println(Thread.currentThread().getName());
        //printing the thread name by getting the name of the currently running thread
        //result: "main" - it is basically functioning in it's own thread called "main method"

        /* If we instantiate a normal class or invoke another method- all these things will
        be executed in this "main" thread */

        /* To make a multi-threaded program, we need to have some special classes.
        So, we need to create our own thread classes- that can be run asynchronously/concurrently. */

        System.out.println("Approach 1");
        //MyThread myThread = new MyThread();
        MyThread myThread0 = new MyThread("My Thread 0");
        MyThread myThread1 = new MyThread("My Thread 1");
        MyThread myThread2 = new MyThread("My Thread 2");

        //We can set priority
        //myThread2.setPriority(Thread.MAX_PRIORITY);
        //myThread0.setPriority(Thread.MIN_PRIORITY);

        //checking which thread is in which state
        System.out.println("~$$ Thread 1 is in state "+myThread1.getState()+" $$~");

        //"start()"-creates multiple threads
        //sequence depends on thread scheduler
        System.out.println("Running in multiple threads");
        //myThread.start();
        myThread0.start();
        myThread1.start();
        System.out.println("~$$ Thread 1 is in state "+myThread1.getState()+" $$~");
        myThread2.start();

        /* manually suspend a thread-from running to blocked state
        or  manually kill the thread from another thread*/
        //myThread1.suspend();
        //myThread0.stop();
        //myThread1.resume();
        //these methods will be "Deprecated"/deadlocks


        //wait for this job to be done
        try {
            myThread1.join();
            myThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //"run()"-works in single thread
        //System.out.println("Running in one thread");
        //myThread.run();
        //myThread0.run();
        //myThread1.run();
        //myThread2.run();
        System.out.println("~$$ Thread 1 is in state "+myThread1.getState()+" $$~");

        if (!myThread2.isAlive()){
            System.out.println(myThread2.getName() + " is DEAD!!!!");
        }else System.out.println("Hello from My Thread 2");




        //System.out.println("Approach 2");
        //Thread myThreadRunnable0 = new Thread(new MyThreadFromRunnable());
        //myThreadRunnable0.start();
        //Thread myThreadRunnable1 = new Thread(new MyThreadFromRunnable(), "My Thread 1");
        //myThreadRunnable1.start();

        //myThreadRunnable0.run();
        //myThreadRunnable1.run();
    }
}
