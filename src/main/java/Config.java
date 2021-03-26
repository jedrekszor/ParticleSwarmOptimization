import java.util.List;

public class Config {
    public static final int population = 20;
    public static final int dimensions = 20;
    public static final int domain = 100;
    public static final int iterations = 30;
    public static Long c1 = (long) 1;
    public static Long c2 = (long) 1;
    public static Long w = (long) 1;
    public static Long r1 = (long) 1;
    public static Long r2 = (long) 1;


    public static Long calculateValue(List<Long> coordinates) {
        return brown(coordinates);
    }

    private static Long sphere(List<Long> coordinates) {
        long sum = 0;
        for (Long coordinate : coordinates) {
            sum += (long) Math.pow(coordinate, 2);
        }
        return sum;
    }

    private static Long rastrigin(List<Long> coordinates) {
        long sum = 0;
        for (Long coordinate : coordinates) {
            sum += (long) (Math.pow(coordinate, 2.0) - 10.0 * Math.cos(2 * Math.PI * coordinate));
        }
        return sum;
    }

    private static Long rosenbrock(List<Long> coordinates) {
        long sum = 0;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            sum += 100 * Math.pow(coordinates.get(i + 1) - Math.pow(coordinates.get(i), 2), 2) + Math.pow(coordinates.get(i) - 1, 2);
        }
        return sum;
    }

    private static Long brown(List<Long> coordinates) {
        long sum = 0;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            sum += 100 * Math.pow(Math.pow(coordinates.get(i), 2), Math.pow(coordinates.get(i + 1), 2) + 1) + Math.pow(Math.pow(coordinates.get(i + 1), 2), Math.pow(coordinates.get(i), 2) + 1);
        }
        return sum;
    }



    


    private static Long square(List<Long> coordinates) {
        long sum = 0;
        for (Long coordinate : coordinates) {
            sum += (long) (Math.pow(coordinate, 2.0));
        }
        return sum;
    }
}


