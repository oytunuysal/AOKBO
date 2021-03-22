package aokbo.genetic;

public class Population {

    private Chromosome[] chromosomes;

    public Population(int length) {
        chromosomes = new Chromosome[length];
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public Population initializePopulation(int targetSize, int maxEstimatedTime, int numberOfEliteChromosomes, int numberOfSemiEliteChromosomes) {
        for (int x = 0; x < chromosomes.length; x++) {
            do {
                chromosomes[x] = new Chromosome(targetSize).initializeChromosome();
            } while (chromosomes[x].getFitness() == maxEstimatedTime); // to make sure all the
                                                                                        // chromosomes are valid

        }
        sortChromosomeByFitness(numberOfEliteChromosomes, numberOfSemiEliteChromosomes);
        return this;
    }

    public void sortChromosomeByFitness(int numberOfEliteChromosomes, int numberOfSemiEliteChromosomes) {
        // tournamanet calls this aswell...
        Chromosome temp;

        for (int i = 0; i < numberOfEliteChromosomes
                + numberOfSemiEliteChromosomes; i++) {
            for (int j = i + 1; j < chromosomes.length; j++) {
                if (chromosomes[i].getFitness() > chromosomes[j].getFitness()) {
                    temp = chromosomes[i];
                    chromosomes[i] = chromosomes[j];
                    chromosomes[j] = temp;
                }
            }
        }

    }
}
