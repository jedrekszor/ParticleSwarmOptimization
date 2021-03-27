import java.util.List;

public class Config {
    public static final int population = 20;
    public static final int dimensions = 2;
    public static final int domain = 100;

    public static float w = .5f;
    public static float c1 = 1;
    public static float c2 = 1;

    public static float r1 = .5f;
    public static float r2 = .5f;

    public static final boolean isIterations = false;
    public static final int iterations = 30;

    public static final float precision = .0001f;
    public static final float result = 0;



    public static float calculateValue(List<Float> coordinates) {
        return sphere(coordinates);
    }

    private static float sphere(List<Float> coordinates) {
        float sum = 0;
        for (double coordinate : coordinates) {
            sum += (double) Math.pow(coordinate, 2);
        }
        return sum;
    }

    private static float rastrigin(List<Float> coordinates) {
        float sum = 0;
        for (double coordinate : coordinates) {
            sum += (double) (Math.pow(coordinate, 2.0) - 10.0 * Math.cos(2 * Math.PI * coordinate));
        }
        return sum;
    }

    private static float rosenbrock(List<Float> coordinates) {
        float sum = 0;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            sum += 100 * Math.pow(coordinates.get(i + 1) - Math.pow(coordinates.get(i), 2), 2) + Math.pow(coordinates.get(i) - 1, 2);
        }
        return sum;
    }

    private static float brown(List<Float> coordinates) {
        float sum = 0;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            sum += 100 * Math.pow(Math.pow(coordinates.get(i), 2), Math.pow(coordinates.get(i + 1), 2) + 1) + Math.pow(Math.pow(coordinates.get(i + 1), 2), Math.pow(coordinates.get(i), 2) + 1);
        }
        return sum;
    }



    


    private static float square(List<Float> coordinates) {
        float sum = 0;
        for (float coordinate : coordinates) {
            sum += (long) (Math.pow(coordinate, 2.0));
        }
        return sum;
    }
}


