package aokbo;

class Population {

    private Chromosome[] chromosomes;

    Population(int length) {
        chromosomes = new Chromosome[length];
    }

    Chromosome[] getChromosomes() {
        return chromosomes;
    }

    Population initializePopulation() {
        for (int x = 0; x < chromosomes.length; x++) {
            do {
                chromosomes[x]
                        = new Chromosome(GeneticAlgorithm.targetsize).initializeChromosome();
            } while (chromosomes[x].getFitness() == GeneticAlgorithm.maxEstimatedTime); //to make sure all the chromosomes are valid

        }
        sortChromosomeByFitness();
        //for (int i = 0; i < chromosomes.length; i++) {
        //    System.out.println(i + "=            " + chromosomes[i].getFitness());
        //}
        return this;
    }

    void sortChromosomeByFitness() {
        //tournamanet calls this aswell...
        Chromosome temp;
//        for(Chromosome chromosome: chromosomes){
//            chromosome.getFitness();
//        }

        for (int i = 0; i < GeneticAlgorithm.NUMB_OF_ELITE_CHROMOSOMES + GeneticAlgorithm.NUMB_OF_SEMIELITE_CHROMOSOMES; i++) {
            for (int j = i + 1; j < chromosomes.length; j++) {
//                while (chromosomes[j].getFitness() == GeneticAlgorithm.maxEstimatedTime) {
//                    chromosomes[j]
//                            = new Chromosome(GeneticAlgorithm.targetsize).initializeChromosome();
//                };
                if (chromosomes[i].getFitness() > chromosomes[j].getFitness()) {
                    temp = chromosomes[i];
                    chromosomes[i] = chromosomes[j];
                    chromosomes[j] = temp;
                }
            }
        }

    }

    /*
        void sortChromosomeByFitness() {

        Chromosome temp;

        for (int i = 0; i < chromosomes.length; i++) {
            for (int j = i + 1; j < chromosomes.length; j++) {
                if (chromosomes[i].getFitness() > chromosomes[j].getFitness()) {
                    temp = chromosomes[i];
                    chromosomes[i] = chromosomes[j];
                    chromosomes[j] = temp;
                }
            }
        }

    }
     */
 /*
        void sortChromosomeByFitness() {
        Chromosome temp;
        int is_swapped;
        for (int i = 0; i < chromosomes.length; i++) {
            is_swapped = 0;
            for (int j = 0; j < chromosomes.length - 1 - i; j++) {
                if (chromosomes[j].getFitness() > chromosomes[j + 1].getFitness()) {
                    temp = chromosomes[j];
                    chromosomes[j] = chromosomes[j + 1];
                    chromosomes[j + 1] = temp;
                    is_swapped = 1;
                }
            }
            if (is_swapped != 0) {
                break;
            }
        }
    }

     */
}
