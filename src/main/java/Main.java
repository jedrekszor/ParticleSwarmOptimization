import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Long> best = new ArrayList<>();
    static List<Player> swarm = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < Config.population; i++) {
            swarm.add(new Player());
        }
        best = List.copyOf(swarm.get(0).coordinates);
        setNewBest();

        for(int i = 0; i < Config.iterations; i++) {
            for(Player player: swarm) {
                player.move();
            }
            setNewBest();
            System.out.println("Iteration nr. " + (i + 1) + ", global best: " + Config.calculateValue(best));
        }
    }

    public static void setNewBest() {
        for(Player player: swarm) {
            if(Config.calculateValue(player.best) < Config.calculateValue(best)) {
                best = List.copyOf(player.best);
            }
        }
    }
}
