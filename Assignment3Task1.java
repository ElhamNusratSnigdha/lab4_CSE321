class ThreadA extends Thread{
    @Override
    public void run(){
        for (int i = 1; i <= 10; i++){
            System.out.print(i+" ");
        }
        System.out.println();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        yield();
        for (int i = 21; i <= 30; i++){
            System.out.print(i+" ");
        }
    }
}
class ThreadB extends Thread{
    @Override
    public void run(){
        for (int i = 11; i <= 20; i++){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}

public class Assignment3Task1 {
    public static void main(String[] args){
        ThreadA thread1 = new ThreadA();
        ThreadB thread2 = new ThreadB();
        thread1.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();

        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}