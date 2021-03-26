import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    List<Long> coordinates = new ArrayList<>();
    List<Long> velocity = new ArrayList<>();
    List<Long> best;

    public Player() {
        for (int i = 0; i < Config.dimensions; i++) {
            coordinates.add((long) ((Math.random() * Config.domain * 2) - Config.domain));
            velocity.add((long) ((Math.random()) * 2 - 1));
        }
        best = List.copyOf(coordinates);
    }

    public void move() {
        List<Long> newCoordinates = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            long cognitiveVelocity = Config.c1 * Config.r1 * (best.get(i) - coordinates.get(i));
            long socialVelocity = Config.c2 * Config.r2 * (Main.best.get(i) - coordinates.get(i));
            newCoordinates.add(Config.w * velocity.get(i) + cognitiveVelocity + socialVelocity);
        }
        coordinates = newCoordinates;
        setNewBest(coordinates);
    }

    private void setNewBest(List<Long> coordinates) {
        if ((Config.calculateValue(coordinates) < Config.calculateValue(best)) && Math.abs(Collections.max(coordinates)) <= Config.domain) {
            best = List.copyOf(coordinates);
        }
    }
}
