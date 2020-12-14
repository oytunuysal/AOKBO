package aokbo;

class GeneticAlgorithm {

    static final int NUMBER_OF_GENERATIONS = 1000;
    static final int POPULATION_SIZE = 100;
    static final int targetsize = 120; //total length of chromosoms just to make sure it doesnt crash
    static final int numberOfGenes = 90;
    static int currentGeneration = 0;
    static double MUTATION_RATE = 0.05;
    //static double MUTATION_RATE = 0;
    static final double MUTATION_GROW_RATE = 0.05;
    //private static double MUTATION_RATE = 0.03;
    static final double THIRD_OF_MR = MUTATION_RATE / 3;
    static final int NUMB_OF_ELITE_CHROMOSOMES = 2; //impact is smaller than tournament size.
    static final int NUMB_OF_SEMIELITE_CHROMOSOMES = 15;
    private static final int TOURNAMENT_SELECTION_SIZE = 2; //it has huge impact on stucking at local max.
    static final int maxEstimatedTime = 3000;
    static gameRules gameRule;
    static TechTree techTree;

    Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population) {
        Population crossoverPopulation = keepEliteChromosomes(population);
        for (int x = NUMB_OF_ELITE_CHROMOSOMES + NUMB_OF_SEMIELITE_CHROMOSOMES; x < population.getChromosomes().length; x++) {
            Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
            Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
            crossoverPopulation.getChromosomes()[x] = crossoverChromosome(chromosome1, chromosome2);
        }
        crossoverPopulation.sortChromosomeByFitness();
        return crossoverPopulation;
    }

    private Population mutatePopulation(Population population) {
        for (int x = NUMB_OF_ELITE_CHROMOSOMES; x < population.getChromosomes().length; x++) {
            mutateChromosome(population.getChromosomes()[x]);
        }
        population.sortChromosomeByFitness();
        return population;
    }

    private Population keepEliteChromosomes(Population population) {
        Population elitePopulation = new Population(population.getChromosomes().length);
        for (int x = 0; x < NUMB_OF_ELITE_CHROMOSOMES + NUMB_OF_SEMIELITE_CHROMOSOMES; x++) {
            elitePopulation.getChromosomes()[x] = population.getChromosomes()[x];
        }
        return elitePopulation;
    }

    private void mutateChromosome(Chromosome chromosome) {
        //there will be 3 types of mutation. 1- changes the deletes, 2- adds a gene, 3- changes the gene.
        //generate random number between 0 and chromosome length.
        for (int x = 0; x < chromosome.getGenes().size(); x++) {
            if (!chromosome.targetIndexes.contains(x)) { // && !chromosome.optionalIndexes.contains(x)
                if (Math.random() < MUTATION_RATE) { //need to change how its going to be mutate
                    //System.out.println("MUTATION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    double i = Math.random();

                    //int index = Chromosome.repeatableCommands.length - 1;
                    int index = (int) (Math.random() * 100) % Chromosome.repeatableCommands.length;
                    int repeatableCommand = Chromosome.repeatableCommands[index];
                    if (i < 0.3) {//delete
                        chromosome.genes.remove(x);
                        for (int a = 0; a < chromosome.targetIndexes.size(); a++) {
                            if (chromosome.targetIndexes.get(a) > x) {
                                chromosome.targetIndexes.set(a, chromosome.targetIndexes.get(a) - 1);
                            }
                        }
                    } else if (i > 0.6) {//insert
                        chromosome.genes.add(x, repeatableCommand);
                        for (int a = 0; a < chromosome.targetIndexes.size(); a++) {
                            if (chromosome.targetIndexes.get(a) > x) {
                                chromosome.targetIndexes.set(a, chromosome.targetIndexes.get(a) + 1);
                            }
                        }
                    } else {//change
                        chromosome.genes.set(x, repeatableCommand);
                    }
                }

            } else {
                if (Math.random() < THIRD_OF_MR) {
                    //int index = Chromosome.repeatableCommands.length - 1;
                    int index = (int) (Math.random() * 100) % Chromosome.repeatableCommands.length;
                    int repeatableCommand = Chromosome.repeatableCommands[index];
                    chromosome.genes.add(x, repeatableCommand);
                    for (int a = 0; a < chromosome.targetIndexes.size(); a++) {
                        if (chromosome.targetIndexes.get(a) >= x) {
                            chromosome.targetIndexes.set(a, chromosome.targetIndexes.get(a) + 1);
                        }
                    }

//                    for (int a = 0; a < chromosome.optionalIndexes.size(); a++) {
//                        if (chromosome.optionalIndexes.get(a) >= x) {
//                            chromosome.optionalIndexes.set(a, chromosome.optionalIndexes.get(a) + 1);
//                        }
//                    }
                }
            }

        }
        chromosome.setChromosomeChanged();
        chromosome.getFitness();
    }

    /*
            private void mutateChromosome(Chromosome chromosome) {
        //there will be 3 types of mutation. 1- changes the deletes, 2- adds a gene, 3- changes the gene.
        //generate random number between 0 and chromosome length.
        int x;
        if (Math.random() < MUTATION_RATE) { //need to change how its going to be mutate
            do {
                x = (int) ((Math.random() * 100) % chromosome.genes.size());
            } while (chromosome.targetIndexes.contains(x));

            //System.out.println("MUTATION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            double i = Math.random();
            int index = Chromosome.repeatableCommands.length - 1;
            int repeatableCommand = Chromosome.repeatableCommands[index];
            if (i < 0.3) {//delete
                chromosome.genes.remove(x);
                for (int a = 0; a < chromosome.targetIndexes.size(); a++) {
                    if (chromosome.targetIndexes.get(a) > x) {
                        chromosome.targetIndexes.set(a, chromosome.targetIndexes.get(a) - 1);
                    }
                }
            } else if (i > 0.6) {//insert
                chromosome.genes.add(x, repeatableCommand);
                for (int a = 0; a < chromosome.targetIndexes.size(); a++) {
                    if (chromosome.targetIndexes.get(a) > x) {
                        chromosome.targetIndexes.set(a, chromosome.targetIndexes.get(a) + 1);
                    }
                }
            } else {//change
                chromosome.genes.set(x, repeatableCommand);
            }
        }

        chromosome.getFitness();
    }
     */
//    private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
//        Chromosome crossoverChromosome = new Chromosome(targetsize);
//        int firstHalfIndex = 0;
//        int i = 0;
//        int index = ((int) (Math.random() * 100)) % chromosome1.targetIndexes.size(); //selects a random command inside the randomNumbers
//        if (Math.random() < 0.5) {
//            firstHalfIndex = chromosome1.targetIndexes.get(index); //selects the locaiton of that command inside the chromosome1
//            for (int x = 0; x < firstHalfIndex; x++) {//prints until that location comes.
//                crossoverChromosome.getGenes().add(chromosome1.getGenes().get(x));
//                i++;
//            }
//            for (int x = chromosome2.targetIndexes.get(index); x < chromosome2.genes.size(); x++) {//prints other half
//                crossoverChromosome.getGenes().add(chromosome2.getGenes().get(x));
//            }
//            for (int x = 0; x < index; x++) { //copying randomNumbers
//                crossoverChromosome.targetIndexes.add(chromosome1.targetIndexes.get(x));
//            }
//            for (int x = index; x < chromosome2.targetIndexes.size(); x++) {
//                crossoverChromosome.targetIndexes.add(i + chromosome2.targetIndexes.get(x) - chromosome2.targetIndexes.get(index));
//                //crossoverChromosome.randomNumbers.add(Math.abs(i + 1 - chromosome2.randomNumbers.get(x)));
//            }
//
//        } else {
//            firstHalfIndex = chromosome2.targetIndexes.get(index); //selects the locaiton of that command inside the chromosome2
//            for (int x = 0; x < firstHalfIndex; x++) {//prints until that location comes.
//                crossoverChromosome.getGenes().add(chromosome2.getGenes().get(x));
//                i++;
//            }
//            for (int x = chromosome1.targetIndexes.get(index); x < chromosome1.genes.size(); x++) {//prints other half
//
//                crossoverChromosome.getGenes().add(chromosome1.getGenes().get(x));
//
//            }
//            for (int x = 0; x < index; x++) { //copying randomNumbers
//                crossoverChromosome.targetIndexes.add(chromosome2.targetIndexes.get(x));
//            }
//            for (int x = index; x < chromosome2.targetIndexes.size(); x++) {
//                //crossoverChromosome.randomNumbers.add(Math.abs(i + 1 - chromosome1.randomNumbers.get(x)));
//                crossoverChromosome.targetIndexes.add(i + chromosome1.targetIndexes.get(x) - chromosome1.targetIndexes.get(index));
//            }
//        }
//        //crossoverChromosome.setGenes();
//        crossoverChromosome.setChromosomeChanged();
//        crossoverChromosome.getFitness();
//        return crossoverChromosome;
//    }
    private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
        Chromosome crossoverChromosome = new Chromosome(targetsize);

        if (Math.random() < 0.5) {
            crossoverParts(crossoverChromosome, chromosome1, chromosome2);

        } else {
            crossoverParts(crossoverChromosome, chromosome2, chromosome1);
        }
        //crossoverChromosome.setGenes();
        crossoverChromosome.setChromosomeChanged();
        crossoverChromosome.getFitness();
        return crossoverChromosome;
    }

    private void crossoverParts(Chromosome crossoverChromosome, Chromosome chromosome1, Chromosome chromosome2) {
        int firstHalfIndex;
        int i = 0;
        int index = ((int) (Math.random() * 100)) % chromosome1.targetIndexes.size(); //selects a random command inside the randomNumbers
        firstHalfIndex = chromosome1.targetIndexes.get(index); //selects the locaiton of that command inside the chromosome1

        for (int x = 0; x < firstHalfIndex; x++) {//prints until that location comes.
            crossoverChromosome.getGenes().add(chromosome1.getGenes().get(x));
            i++;
        }
        for (int x = chromosome2.targetIndexes.get(index); x < chromosome2.genes.size(); x++) {//prints other half
            crossoverChromosome.getGenes().add(chromosome2.getGenes().get(x));
        }
        for (int x = 0; x < index; x++) { //copying randomNumbers
            crossoverChromosome.targetIndexes.add(chromosome1.targetIndexes.get(x));
        }
        for (int x = index; x < chromosome2.targetIndexes.size(); x++) {
            crossoverChromosome.targetIndexes.add(i + chromosome2.targetIndexes.get(x) - chromosome2.targetIndexes.get(index));
            //crossoverChromosome.randomNumbers.add(Math.abs(i + 1 - chromosome2.randomNumbers.get(x)));
        }
    }

    private Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
        for (int x = 0; x < TOURNAMENT_SELECTION_SIZE; x++) {
            tournamentPopulation.getChromosomes()[x] = population.getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
        }
        tournamentPopulation.sortChromosomeByFitness();
        return tournamentPopulation;
    }
}
