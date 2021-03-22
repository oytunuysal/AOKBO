package aokbo.genetic;

import aokbo.simulation.GameRules;
import aokbo.simulation.TechTree;

public class GeneticAlgorithm {
/*    private int numberOfGenerations = 1000;
    private int populationSize = 100;
    private int targetsize = 120; // total length of chromosoms just to make sure it doesnt crash
    private int numberOfGenes = 90;
    private int currentGeneration;
    private double mutationRate = 0.05;
    // double MUTATION_RATE = 0;
    private double mutationGrowRate = 0.05;
    // private double MUTATION_RATE = 0.03;
    private double thirdOfMutationRate;
    private int numberOfEliteChromosomes = 2; // impact is smaller than tournament size.
    private int numberOfSemiEliteChromosomes = 15;
    private int tournamentSelectionSize = 2; // it has huge impact on stucking at local max.
    private int maxEstimatedTime = 3000;
    private GameRules gameRule;
    private TechTree techTree;

        public GeneticAlgorithm(int numberOfGenerations, int populationSize, int targetsize, int numberOfGenes,
            double mutationRate, double mutationGrowRate, int numberOfEliteChromosomes,
            int numberOfSemiEliteChromosomes, int tournamentSelectionSize, int maxEstimatedTime, GameRules gameRule,
            TechTree techTree) {
        this.numberOfGenerations = numberOfGenerations;
        this.populationSize = populationSize;
        this.targetsize = targetsize;
        this.numberOfGenes = numberOfGenes;
        this.currentGeneration = 0;
        this.mutationRate = mutationRate;
        this.mutationGrowRate = mutationGrowRate;
        this.thirdOfMutationRate = mutationRate / 3;
        this.numberOfEliteChromosomes = numberOfEliteChromosomes;
        this.numberOfSemiEliteChromosomes = numberOfSemiEliteChromosomes;
        this.tournamentSelectionSize = tournamentSelectionSize;
        this.maxEstimatedTime = maxEstimatedTime;
        this.gameRule = gameRule;
        this.techTree = techTree;
    }
*/
    private int currentGeneration;
    private Options generOptions;

    public GeneticAlgorithm(){
        this.currentGeneration = 0;
        this.generOptions = Options.getOptions();
    }


    public int getCurrentGeneration() {
        return this.currentGeneration;
    }

    public void setCurrentGeneration(int currentGeneration){
        this.currentGeneration = currentGeneration;
    }

    public Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population) {
        Population crossoverPopulation = keepEliteChromosomes(population);
        for (int x = generOptions.numberOfEliteChromosomes + generOptions.numberOfSemiEliteChromosomes; x < population
                .getChromosomes().length; x++) {
            Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
            Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
            crossoverPopulation.getChromosomes()[x] = crossoverChromosome(chromosome1, chromosome2);
        }
        crossoverPopulation.sortChromosomeByFitness(generOptions.numberOfEliteChromosomes, generOptions.numberOfSemiEliteChromosomes);
        return crossoverPopulation;
    }

    private Population mutatePopulation(Population population) {
        for (int x = generOptions.numberOfEliteChromosomes; x < population.getChromosomes().length; x++) {
            mutateChromosome(population.getChromosomes()[x]);
        }
        population.sortChromosomeByFitness(generOptions.numberOfEliteChromosomes, generOptions.numberOfSemiEliteChromosomes);
        return population;
    }

    private Population keepEliteChromosomes(Population population) {
        Population elitePopulation = new Population(population.getChromosomes().length);
        for (int x = 0; x < generOptions.numberOfEliteChromosomes + generOptions.numberOfSemiEliteChromosomes; x++) {
            elitePopulation.getChromosomes()[x] = population.getChromosomes()[x];
        }
        return elitePopulation;
    }

    private void mutateChromosome(Chromosome chromosome) {
        // there will be 3 types of mutation. 1- changes the deletes, 2- adds a gene, 3-
        // changes the gene.
        // generate random number between 0 and chromosome length.
        for (int x = 0; x < chromosome.getGenes().size(); x++) {
            if (!chromosome.targetIndexes.contains(x)) { // && !chromosome.optionalIndexes.contains(x)
                if (Math.random() < generOptions.mutationRate) { // need to change how its going to be mutate
                    // System.out.println("MUTATION!");
                    double i = Math.random();

                    // int index = Chromosome.repeatableCommands.length - 1;
                    int index = (int) (Math.random() * 100) % Chromosome.repeatableCommands.length;
                    int repeatableCommand = Chromosome.repeatableCommands[index];
                    if (i < 0.3) {// delete
                        chromosome.genes.remove(x);
                        for (int a = 0; a < chromosome.targetIndexes.size(); a++) {
                            if (chromosome.targetIndexes.get(a) > x) {
                                chromosome.targetIndexes.set(a, chromosome.targetIndexes.get(a) - 1);
                            }
                        }
                    } else if (i > 0.6) {// insert
                        chromosome.genes.add(x, repeatableCommand);
                        for (int a = 0; a < chromosome.targetIndexes.size(); a++) {
                            if (chromosome.targetIndexes.get(a) > x) {
                                chromosome.targetIndexes.set(a, chromosome.targetIndexes.get(a) + 1);
                            }
                        }
                    } else {// change
                        chromosome.genes.set(x, repeatableCommand);
                    }
                }

            } else {
                if (Math.random() < generOptions.thirdOfMutationRate) {
                    // int index = Chromosome.repeatableCommands.length - 1;
                    int index = (int) (Math.random() * 100) % Chromosome.repeatableCommands.length;
                    int repeatableCommand = Chromosome.repeatableCommands[index];
                    chromosome.genes.add(x, repeatableCommand);
                    for (int a = 0; a < chromosome.targetIndexes.size(); a++) {
                        if (chromosome.targetIndexes.get(a) >= x) {
                            chromosome.targetIndexes.set(a, chromosome.targetIndexes.get(a) + 1);
                        }
                    }

                    // for (int a = 0; a < chromosome.optionalIndexes.size(); a++) {
                    // if (chromosome.optionalIndexes.get(a) >= x) {
                    // chromosome.optionalIndexes.set(a, chromosome.optionalIndexes.get(a) + 1);
                    // }
                    // }
                }
            }

        }
        chromosome.setChromosomeChanged();
        chromosome.getFitness();
    }

    private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
        Chromosome crossoverChromosome = new Chromosome(generOptions.targetsize);

        if (Math.random() < 0.5) {
            crossoverParts(crossoverChromosome, chromosome1, chromosome2);

        } else {
            crossoverParts(crossoverChromosome, chromosome2, chromosome1);
        }
        // crossoverChromosome.setGenes();
        crossoverChromosome.setChromosomeChanged();
        crossoverChromosome.getFitness();
        return crossoverChromosome;
    }

    private void crossoverParts(Chromosome crossoverChromosome, Chromosome chromosome1, Chromosome chromosome2) {
        int firstHalfIndex;
        int i = 0;
        int index = ((int) (Math.random() * 100)) % chromosome1.targetIndexes.size(); // selects a random command inside
                                                                                      // the randomNumbers
        firstHalfIndex = chromosome1.targetIndexes.get(index); // selects the locaiton of that command inside the
                                                               // chromosome1

        for (int x = 0; x < firstHalfIndex; x++) {// prints until that location comes.
            crossoverChromosome.getGenes().add(chromosome1.getGenes().get(x));
            i++;
        }
        for (int x = chromosome2.targetIndexes.get(index); x < chromosome2.genes.size(); x++) {// prints other half
            crossoverChromosome.getGenes().add(chromosome2.getGenes().get(x));
        }
        for (int x = 0; x < index; x++) { // copying randomNumbers
            crossoverChromosome.targetIndexes.add(chromosome1.targetIndexes.get(x));
        }
        for (int x = index; x < chromosome2.targetIndexes.size(); x++) {
            crossoverChromosome.targetIndexes
                    .add(i + chromosome2.targetIndexes.get(x) - chromosome2.targetIndexes.get(index));
            // crossoverChromosome.randomNumbers.add(Math.abs(i + 1 -
            // chromosome2.randomNumbers.get(x)));
        }
    }

    private Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(generOptions.tournamentSelectionSize);
        for (int x = 0; x < generOptions.tournamentSelectionSize; x++) {
            tournamentPopulation.getChromosomes()[x] = population
                    .getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
        }
        tournamentPopulation.sortChromosomeByFitness(generOptions.numberOfEliteChromosomes, generOptions.numberOfSemiEliteChromosomes);
        return tournamentPopulation;
    }

}
