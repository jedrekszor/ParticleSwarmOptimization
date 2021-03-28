import java.util.ArrayList;
import java.util.List;

public class Config {
    public static final int population = 40;
    public static final int dimensions = 30;
    public static final float domain = 5.12f;

    public static float w = .7f;
    public static float c1 = 2f;
    public static float c2 = 2f;

    public static final float wMin = 0.4f;
    public static final float wMax = 0.9f;
    public static final float c1i = 2f;
    public static final float c1f = 1f;
    public static final float c2i = 1f;
    public static final float c2f = 2f;

    public static final boolean isIterations = true;
    public static final int iterations = 2000;

    public static final float precision = 0.0001f;
    public static final float result = 0;

    public static final String path = "ex21";


    public static float calculateValue(List<Float> coordinates) {
        return rastrigin(coordinates);
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
        return sum + (10 * dimensions);
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

    private static float corana(List<Float> coordinates) {
        float sum = 0;
        List<Integer> d = new ArrayList<>();
        d.add(1);
        d.add(1000);
        d.add(10);
        d.add(100);
        for (int i = 0; i < coordinates.size() - 1; i++) {
            float z = (float) (Math.floor(Math.abs(coordinates.get(i) / 0.2) + 0.49999) * Math.signum(coordinates.get(i)) * 0.2);
            if (Math.abs(coordinates.get(i) - z) < 0.05) {
                sum += 0.15 * Math.pow(z - 0.05 * Math.signum(z), 2) * d.get(i);
            } else {
                sum += d.get(i) * Math.pow(coordinates.get(i), 2);
            }
        }
        return sum;
    }
}


