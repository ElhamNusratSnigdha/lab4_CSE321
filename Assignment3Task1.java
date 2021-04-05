class ThreadA extends Thread{
    private int start=0;
    private int end=0;
    public ThreadA(int a,int b){
        this.start=a;
        this.end=b;
    }
    @Override
    public void run(){
        for (int i = start; i <= end; i++){
            System.out.println(i);
        }
    }
}
public class Assignment3Task1 {
    public static void main(String[] args){
        ThreadA thread1 = new ThreadA(1,10);
        ThreadA thread2 = new ThreadA(11,20);
        thread1.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
    }
}
