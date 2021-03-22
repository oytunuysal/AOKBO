package aokbo.genetic;

import aokbo.simulation.GameRules;
import aokbo.simulation.TechTree;

public class Options {
    public int numberOfGenerations = 1000;
    public int populationSize = 100;
    public int targetsize = 120; // total length of chromosoms just to make sure it doesnt crash
    public int numberOfGenes = 90;
    public double mutationRate = 0.05;
    // double MUTATION_RATE = 0;
    public double mutationGrowRate = 0.05;
    // private double MUTATION_RATE = 0.03;
    public double thirdOfMutationRate;
    public int numberOfEliteChromosomes = 2; // impact is smaller than tournament size.
    public int numberOfSemiEliteChromosomes = 15;
    public int tournamentSelectionSize = 2; // it has huge impact on stucking at local max.
    public int maxEstimatedTime = 3000;
    public GameRules gameRule;
    public TechTree techTree;

    private static Options singleton;

    private Options() {

    }

    public static Options getOptions() {
        if (singleton != null) {
            return singleton;
        } else {
            singleton = new Options();
            return singleton;
        }
    }

    public void setOptions(int numberOfGenerations, int populationSize, int targetsize, int numberOfGenes,
            double mutationRate, double mutationGrowRate, int numberOfEliteChromosomes,
            int numberOfSemiEliteChromosomes, int tournamentSelectionSize, int maxEstimatedTime, GameRules gameRule,
            TechTree techTree) {
        singleton.numberOfGenerations = numberOfGenerations;
        singleton.populationSize = populationSize;
        singleton.targetsize = targetsize;
        singleton.numberOfGenes = numberOfGenes;
        singleton.mutationRate = mutationRate;
        singleton.mutationGrowRate = mutationGrowRate;
        singleton.thirdOfMutationRate = mutationRate / 3;
        singleton.numberOfEliteChromosomes = numberOfEliteChromosomes;
        singleton.numberOfSemiEliteChromosomes = numberOfSemiEliteChromosomes;
        singleton.tournamentSelectionSize = tournamentSelectionSize;
        singleton.maxEstimatedTime = maxEstimatedTime;
        singleton.gameRule = gameRule;
        singleton.techTree = techTree;
    }

    /*
     * public static int numberOfGenerations = 1000; public static int
     * populationSize = 100; public static int targetsize = 120; // total length of
     * chromosoms just to make sure it doesnt crash public static int numberOfGenes
     * = 90; public static double mutationRate = 0.05; // double MUTATION_RATE = 0;
     * public static double mutationGrowRate = 0.05; // private double MUTATION_RATE
     * = 0.03; public static double thirdOfMutationRate; public static int
     * numberOfEliteChromosomes = 2; // impact is smaller than tournament size.
     * public static int numberOfSemiEliteChromosomes = 15; public static int
     * tournamentSelectionSize = 2; // it has huge impact on stucking at local max.
     * public static int maxEstimatedTime = 3000; public static GameRules gameRule;
     * public static TechTree techTree;
     * 
     * public static void setState(int numberOfGenerations, int populationSize, int
     * targetsize, int numberOfGenes, double mutationRate, double mutationGrowRate,
     * int numberOfEliteChromosomes, int numberOfSemiEliteChromosomes, int
     * tournamentSelectionSize, int maxEstimatedTime, GameRules gameRule, TechTree
     * techTree){ Options.numberOfGenerations = numberOfGenerations;
     * Options.populationSize = populationSize; Options.targetsize = targetsize;
     * Options.numberOfGenes = numberOfGenes; Options.mutationRate = mutationRate;
     * Options.mutationGrowRate = mutationGrowRate; Options.thirdOfMutationRate =
     * mutationRate / 3; Options.numberOfEliteChromosomes =
     * numberOfEliteChromosomes; Options.numberOfSemiEliteChromosomes =
     * numberOfSemiEliteChromosomes; Options.tournamentSelectionSize =
     * tournamentSelectionSize; Options.maxEstimatedTime = maxEstimatedTime;
     * Options.gameRule = gameRule; Options.techTree = techTree; }
     */

}
