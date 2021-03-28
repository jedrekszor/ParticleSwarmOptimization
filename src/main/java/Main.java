import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Float> plot = new ArrayList<>();
    public static List<Float> best = new ArrayList<>();
    static List<Player> swarm = new ArrayList<>();

//    private static int eval(final Genotype<BitGene> gt) {
//        return gt.chromosome()
//                .as(BitChromosome.class)
//                .bitCount();
//    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < Config.population; i++) {
            swarm.add(new Player());
        }
        best = List.copyOf(swarm.get(0).coordinates);
        setNewBest();

//        Config.w = Config.wMax;
//        Config.c1 = Config.c1i;
//        Config.c2 = Config.c2i;

        int i = 0;
        while (true) {
            for (Player player : swarm) {
                player.move();
            }
            setNewBest();
//            adjustAcceleration(i);
            float error = Math.abs(Config.calculateValue(best) - Config.result);
            System.out.println("Iteration nr. " + (i + 1) + ", global best: " + Config.calculateValue(best));// + ", error: " + error);
//            System.out.println(best);

            plot.add(Config.calculateValue(best));
            if (Config.isIterations) {
                if (i >= Config.iterations - 1)
                    break;
            } else {
                if (error <= Config.precision || i >= 9999)
                    break;
            }
            i++;
        }
        FileWriter writer = new FileWriter("raport/plots/" + Config.path + ".csv");
        String top = "Step,Value\n";
        writer.write(top);
        for (int j = 0; j < plot.size(); j++) {
            String temp = j + "," + plot.get(j) + "\n";
            writer.write(temp);
        }

        writer.close();


//        final Factory<Genotype<BitGene>> gtf =
//                Genotype.of(BitChromosome.of(10, 0.5));
//        final Engine<BitGene, Integer> engine = Engine
//                .builder(Main::eval, gtf)
//                .build();
//
//        final Genotype<BitGene> result = engine.stream()
//                .limit(100)
//                .collect(EvolutionResult.toBestGenotype());
//        System.out.println("Hello World: \n\t " + result);
    }

    public static void setNewBest() {
        for (Player player : swarm) {
            if (Config.calculateValue(player.best) < Config.calculateValue(best)) {
                best = List.copyOf(player.best);
            }
        }
    }

    private static void adjustAcceleration(int iteration) {
        Config.w = Config.wMin + (Config.wMax - Config.wMin) * (Config.iterations - iteration) / Config.iterations;
        Config.c1 = (Config.c1f - Config.c1i) * (iteration / (float) Config.iterations) + Config.c1i;
        Config.c2 = (Config.c2f - Config.c2i) * (iteration / (float) Config.iterations) + Config.c2i;
    }
}
