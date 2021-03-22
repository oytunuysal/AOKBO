package aokbo.genetic;

import java.util.ArrayList;
import java.util.Collections;

import aokbo.AOKBO;
import aokbo.simulation.GameRules;
import aokbo.simulation.SimulationMaintained;
import aokbo.simulation.TechTree;

public class Chromosome {

    private boolean isFitnessChanged = true;
    private int fitness = 0;
    public static int[] repeatableCommands = new int[]{11, 21, 31, 41};
    public static ArrayList<Integer> targetPrerequisites;
    public ArrayList<Integer> genes = new ArrayList<>();
    int length = 0;
    public ArrayList<Integer> targetIndexes = new ArrayList<>();
//    public static int[] optionalCommands = new int[]{16};
//    public ArrayList<Integer> optionalIndexes = new ArrayList<>();

    private Options geneticOptions;

    Chromosome(int length) {
        //genes = new int[length];
        this.length = length;
        this.genes.ensureCapacity(this.length);
        this.isFitnessChanged = true;
        this.geneticOptions = Options.getOptions();
    }

    void setGenes() {
    }

    public ArrayList<Integer> getGenes() {
        isFitnessChanged = true;
        return genes;
    }

    public int getFitness() {
        if (isFitnessChanged) {
            this.fitness = recalculateFitness();
            this.isFitnessChanged = false;
        }
        return fitness;
    }

    void setChromosomeChanged() {
        this.isFitnessChanged = true;
    }

    @Override
    public String toString() {
        return "Chromosome{"
                + "genes=" + genes.toString()
                + '}';
    }

    private int recalculateFitness() { //this may call simulation
        int chromosomeFitness = 0;
        //Printing all genes inside the chromosome
        //System.out.println(genes.toString());
        SimulationMaintained calc = new SimulationMaintained(geneticOptions.gameRule, geneticOptions.techTree, AOKBO.createNewResources());
        chromosomeFitness = calc.Run(genes, geneticOptions.maxEstimatedTime);
        if (chromosomeFitness == 0) {
            chromosomeFitness = geneticOptions.maxEstimatedTime;
        }
        return chromosomeFitness;
    }

    Chromosome initializeChromosome() { // change here
        setChromosomeChanged();
        //create random build orders here
        int prerequisiteSize = targetPrerequisites.size();
        genes.ensureCapacity(this.length);
        for (int i = 0; i < geneticOptions.numberOfGenes; i++) { //creates random repeatable commands
            genes.add(repeatableCommands[((int) (Math.random() * 100)) % repeatableCommands.length]);
        }

        int randomNumber;
//        for (int i = 0; i < optionalCommands.length; i++) {
//            randomNumber = (int) (Math.random() * 100) % genes.size();
//            genes.add(randomNumber, optionalCommands[i]);
//            optionalIndexes.add(randomNumber);
//        }
//        // Collections.sort(optionalIndexes);

        for (int i = 0; i < prerequisiteSize; i++) { //should not create the same index value
            do {
                randomNumber = (int) (Math.random() * 100) % genes.size();
                //} while (targetIndexes.contains(randomNumber) || optionalIndexes.contains(randomNumber));
            } while (targetIndexes.contains(randomNumber));
            targetIndexes.add(randomNumber);
        }
        Collections.sort(targetIndexes);

        for (int i = 0; i < prerequisiteSize; i++) {//inserts prerequisites
            genes.add(targetIndexes.get(i), (int) targetPrerequisites.get(i));
        }
        int deletionIndex = targetIndexes.get(targetIndexes.size() - 1) + 1;
        while (deletionIndex < genes.size()) {//deletes after target reaches
            genes.remove(deletionIndex);
        }

        return this;
    }
}
