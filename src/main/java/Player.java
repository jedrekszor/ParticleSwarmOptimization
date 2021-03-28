import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {
    List<Float> coordinates = new ArrayList<>();
    List<Float> velocity = new ArrayList<>();
    List<Float> best;

    public Player() {
        Random rand = new Random();
        for (int i = 0; i < Config.dimensions; i++) {
            coordinates.add((rand.nextFloat() * Config.domain * 2) - Config.domain);
            velocity.add((rand.nextFloat() * 2) - 1);
        }
        best = List.copyOf(coordinates);
    }

    public void move() {
        updateVel();
        updatePos();
        setNewBest();
    }

    private void updateVel() {
        for (int i = 0; i < coordinates.size(); i++) {
            Random rand = new Random();
            float r1 = rand.nextFloat();
            float r2 = rand.nextFloat();
            float cognitiveVelocity = Config.c1 * r1 * (best.get(i) - coordinates.get(i));
            float socialVelocity = Config.c2 * r2 * (Main.best.get(i) - coordinates.get(i));
            velocity.set(i, Config.w * velocity.get(i) + cognitiveVelocity + socialVelocity);
        }
    }

    private void updatePos() {
        for (int i = 0; i < coordinates.size(); i++) {
            coordinates.set(i, coordinates.get(i) + velocity.get(i));
        }
    }

    private void setNewBest() {
        if ((Config.calculateValue(coordinates) < Config.calculateValue(best)) && Math.abs(Collections.max(coordinates)) <= Config.domain) {
            best = List.copyOf(coordinates);
        }
    }
}
