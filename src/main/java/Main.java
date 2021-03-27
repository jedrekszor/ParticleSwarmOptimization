import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<Float> plot = new ArrayList<>();
    public static List<Float> best = new ArrayList<>();
    static List<Player> swarm = new ArrayList<>();

    private static int eval(final Genotype<BitGene> gt) {
        return gt.chromosome()
                .as(BitChromosome.class)
                .bitCount();
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < Config.population; i++) {
            swarm.add(new Player());
        }
        best = List.copyOf(swarm.get(0).coordinates);
        setNewBest();

        int i = 1;
        while (true) {
            for (Player player : swarm) {
                player.move();
            }
            setNewBest();
            float error = Math.abs(Config.calculateValue(best) - Config.result);
            System.out.println("Iteration nr. " + (i) + ", global best: " + Config.calculateValue(best));// + ", error: " + error);

            if (Config.isIterations) {
                if (i >= Config.iterations)
                    break;
            } else {

                if (error <= Config.precision)
                    break;
            }

            Config.w -= 0.005;

            i++;
        }
        FileWriter writer = new FileWriter("plots/plot.csv");

        for (int j = 0; j < plot.size(); j++) {
            String temp = j + "," + plot.get(j) + "\n";
            writer.write(temp);
        }

        writer.close();


        final Factory<Genotype<BitGene>> gtf =
                Genotype.of(BitChromosome.of(10, 0.5));
        final Engine<BitGene, Integer> engine = Engine
                .builder(Main::eval, gtf)
                .build();

        final Genotype<BitGene> result = engine.stream()
                .limit(100)
                .collect(EvolutionResult.toBestGenotype());
        System.out.println("Hello World: \n\t " + result);
    }

    public static void setNewBest() {
        for (Player player : swarm) {
            if (Config.calculateValue(player.best) < Config.calculateValue(best)) {
                best = List.copyOf(player.best);
                plot.add(Config.calculateValue(best));
            }
        }
    }
}
