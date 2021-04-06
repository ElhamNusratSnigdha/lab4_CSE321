public class Assignment3Task2 {
    public static int theNumber = 0;
    public static int itHas = 0;
    public static void main(String[]args){
        int numThreads = 10;
        int threadNo = 0;
        Runner[] threads = new Runner[numThreads];

        //creating threads
        for (int i = 0; i < threads.length; i++){
            threadNo = i;
            threads[i] = new Runner("Thread-"+ i, threadNo);
        }

        //starting threads
        long ss = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++){
            threads[i].run();
            if(itHas<threads[i].itHas){
                theNumber=threads[i].theNumber;
                itHas=threads[i].itHas;
            }

        }
        long se = System.currentTimeMillis();
        long singleThreadedPerformance = (se-ss)/1000;

        long ms = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++){
            //threads[i].run();
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(itHas<threads[i].itHas){
                theNumber=threads[i].theNumber;
                itHas=threads[i].itHas;
            }

        }
        long me = System.currentTimeMillis();
        long multiThreadedPerformance = (me-ms)/1000;

        System.out.println("The Integer that has the largest number of divisors: "+ theNumber);
        System.out.println("The number of divisors it has: "+ itHas);
        System.out.println();
        System.out.println("The execution time of Single threaded:  "+singleThreadedPerformance+" s");
        System.out.println("The execution time of Multi-threaded performance :  "+multiThreadedPerformance+" s");
    }
}
class Runner extends Thread {
    public String threadName;
    public int threadNo;
    public int theNumber;
    public int itHas;

    public Runner(String name, int threadNo){
        this.threadName = name;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        int start = 1;
        int end = 10000;
        if (!threadName.equals("Thread-0")){
            start = threadNo*10000+start;
            end = threadNo*end+10000;
        }
        //System.out.println(start+" "+end);
        for (int i = start; i <= end; i++){
            int c=0;
            for (int j=1; j<=start; j++){
                if (i%j==0){
                    c++;
                }
            }
            if (c>itHas){
                this.theNumber=i;
                this.itHas=c;
            }
        }
    }

}