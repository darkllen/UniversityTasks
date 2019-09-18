import org.springframework.util.StopWatch;

public class Main {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        for (int i = 1000 ;i<=8000; i+=1000){
            stopWatch.start("N="+i);
            Timing.main(new String[]{String.valueOf(i),"777280"});
            stopWatch.stop();
        }

       System.out.println(stopWatch.prettyPrint());


    }
}

// складність 2log(2,n)*n
//1240 байт
//8*10^-9*N^2
