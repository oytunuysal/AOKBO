/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import aokbo.genetic.Chromosome;
import aokbo.genetic.GeneticAlgorithm;
import aokbo.genetic.Options;
import aokbo.genetic.Population;
import aokbo.simulation.Building;
import aokbo.simulation.GameRules;
import aokbo.simulation.GraphData;
import aokbo.simulation.LineChart;
import aokbo.simulation.LookUp;
import aokbo.simulation.Resource;
import aokbo.simulation.SimulationMaintained;
import aokbo.simulation.Tasker;
import aokbo.simulation.TechTree;
import aokbo.simulation.Unit;

/**
 *
 * @author Oytun
 */
public class AOKBO { // Mostly for testing

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //tryCaseSimulateWithResources();
        // Genetic algorithm test case
         tryCaseRunWithGA();
        // These are test cases for some functionalities.
        // tryCaseRun();
        // tryCaseFood();
        // tryCaseDeer();
        // tryCaseWheelbarrow();
        // tryCaseTechtree();
        // tryCaseResources();
        // tryCaseResourcesAddVilsByTime();
    }

    public static ArrayList<Resource> createNewResources() {

        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(new Resource(2, "Deer", 0.408f, 4, 500, 35, 9));
        resources.add(new Resource(2, "Berry", 0.310f, 6, 600, 10, 8));
        resources.add(new Resource(2, "Sheep", 0.330f, 8, 800, 10, 0));
        resources.add(new Resource(1, "Wood", 0.310f, 30, 5000, 10, 8));
        resources.add(new Resource(3, "Gold", 0.330f, 30, 3000, 10, 10));
        resources.add(new Resource(4, "Stone", 0.330f, 30, 3000, 10, 10));
        return resources;
    }

    public static void tryCaseSimulateWithResources() {
        int maxEstimatedTime = 4000;
        Integer[] thirteen = { 21, 21, 21, 11, 21, 21, 11, 21, 21, 21, 21, 21, 31, 23, 22, 22, 22, 31, 22, 22, 22, 22,
                31, 21, 31, 31, 21, 21, 21, 22, 22, 21, 22, 22, 22, 22 };
        Integer[] thirteen2 = { 21, 11, 21, 31, 21, 21, 11, 21, 21, 21, 21, 21, 23, 22, 31, 22, 22, 22, 22, 22, 31, 22,
                22, 22, 22, 22, 22, 31, 31, 22 };
        Integer[] thirteen3 = { 21, 11, 21, 21, 21, 31, 11, 21, 21, 21, 21, 21, 23, 22, 21, 22, 22, 31, 22, 22, 22, 22,
                22, 22, 31, 21, 21, 21, 31, 21, 21, 31, 22, 21, 22, 22, 22 };
        Integer[] fastFedual = { 21, 21, 21, 11, 21, 21, 21, 21, 21, 21, 33, 21, 21, 21, 21, 11, 11, 11, 43, 44 };
        Integer[] fastFeudal2 = { 11, 21, 21, 21, 21, 21, 21, 33, 11, 43, 44 };
        Integer[] fastFeudal3 = { 21, 21, 21, 21, 11, 33, 11, 21, 21, 43, 44 };
        Integer[] fastCastle = { 21, 11, 21, 21, 11, 21, 21, 21, 33, 21, 21, 21, 21, 21, 43, 11, 53, 21, 21, 11, 11, 21,
                11, 21, 21, 21, 44, 31, 31, 63, 73, 21, 54 };
        Integer[] fastCastle2 = { 21, 33, 21, 21, 21, 21, 21, 21, 21, 11, 31, 11, 11, 21, 11, 43, 53, 11, 21, 21, 21,
                44, 21, 21, 21, 63, 21, 21, 21, 73, 21, 31, 54 }; // 867
        Integer[] fastCastle3 = { 11, 21, 33, 21, 21, 11, 21, 21, 21, 11, 21, 21, 31, 21, 21, 43, 21, 53, 44, 11, 21,
                11, 21, 21, 21, 21, 21, 21, 21, 21, 63, 73, 31, 54 };
        Integer[] fastCastle4 = { 11, 21, 21, 11, 21, 21, 21, 21, 11, 21, 21, 11, 21, 33, 21, 21, 43, 53, 44, 21, 31,
                21, 21, 21, 21, 31, 21, 21, 21, 21, 63, 73, 21, 21, 54 };// 826
        Integer[] fastCastle5 = { 11, 21, 21, 11, 21, 21, 21, 21, 11, 21, 21, 11, 21, 33, 21, 21, 43, 53, 44, 31, 21,
                21, 21, 31, 21, 21, 21, 21, 21, 63, 11, 11, 73, 54 }; // 956
        Integer[] fastCastle6 = { 21, 21, 21, 21, 21, 11, 11, 21, 21, 21, 21, 33, 21, 21, 21, 21, 43, 11, 21, 21, 21,
                21, 11, 21, 11, 21, 21, 21, 21, 53, 21, 21, 21, 31, 21, 21, 21, 31, 21, 44, 21, 21, 21, 11, 63, 73, 21,
                54 }; // 815
        Integer[] fastCastle7 = { 21, 21, 21, 21, 11, 21, 11, 11, 21, 21, 33, 11, 31, 21, 21, 21, 21, 21, 21, 21, 21,
                43, 21, 21, 21, 53, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 44, 21, 63, 21, 21, 11, 21, 21, 73,
                54 }; // 678
        Integer[] asd1 = { 11, 21, 33, 21, 21, 21, 21, 11, 21, 21, 11, 11, 21, 21, 43, 21, 31, 21, 21, 21, 21, 21, 21,
                53, 21, 11, 21, 44, 16, 21, 63, 21, 73, 31, 31, 54 };
        Integer[] fcfarms = { 21, 11, 33, 21, 21, 21, 11, 11, 21, 21, 21, 21, 31, 21, 11, 21, 43, 21, 21, 53, 21, 21,
                21, 16, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 44, 63, 73, 21, 21, 21, 21, 21, 21, 54 }; // 676
        Integer[] fi = { 11, 21, 21, 21, 21, 21, 21, 11, 21, 33, 11, 11, 21, 21, 21, 21, 21, 43, 21, 11, 21, 21, 21, 21,
                53, 11, 11, 11, 16, 21, 31, 21, 21, 21, 21, 31, 21, 21, 31, 21, 31, 31, 11, 21, 31, 21, 44, 31, 21, 21,
                63, 31, 31, 21, 73, 54, 73, 21, 41, 73, 64 }; // 745
        Integer[] fi2 = { 11, 21, 11, 21, 21, 33, 21, 21, 11, 11, 11, 11, 21, 21, 31, 11, 31, 21, 21, 31, 21, 21, 21,
                43, 21, 21, 31, 53, 16, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 31, 11, 44, 21, 31, 31, 63, 21,
                73, 54, 11, 73, 11, 73, 41, 11, 64 };
        ArrayList<Integer> bo = new ArrayList<>();
        Collections.addAll(bo, fi2);
        LookUp lookUpTable = new LookUp();
        TechTree techTree = new TechTree();
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(techTree.TownCenter);
        GameRules gameRules = new GameRules(100, 200, 100, 100, 200, 0, 3, buildings); // wood, food, gold, stone,
                                                                                        // maxpop, startingvils
        SimulationMaintained simulation = new SimulationMaintained(gameRules, techTree, createNewResources());
        SimulationMaintained simulationWithGraphdata = new SimulationMaintained(gameRules, techTree,
                createNewResources());
        GraphData data = simulationWithGraphdata.RunGD(bo, maxEstimatedTime);

        LineChart.run(data);
        System.out.println(data.getIngameSeconds());

    }

    public static void tryCaseRunWithGA() {
        LookUp lookUpTable = new LookUp();
        TechTree techTree = new TechTree();
        ArrayList<Building> buildings = new ArrayList<>();
        ArrayList<Resource> resources = new ArrayList<>();
        buildings.add(techTree.TownCenter);
        GameRules gameRules = new GameRules(100, 200, 100, 100, 200, 0, 3, buildings); // wood, food, gold, stone,
                                                                                       // maxpop, startingvils
        ArrayList<Integer> fastCastleBO = new ArrayList();
        ArrayList<Integer> threeMilitia = new ArrayList();
        ArrayList<Integer> feudalRush = new ArrayList();
        ArrayList<Integer> fastImperial = new ArrayList();
        ArrayList<Integer> sevenMilitia = new ArrayList();
        ArrayList<Integer> farming = new ArrayList();

        // target is barracks+7militia
        sevenMilitia.add(23); // build barracks
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia
        sevenMilitia.add(22); // create militia

        // Standard fast castle order
        fastCastleBO.add(33); // build lumber camp
        fastCastleBO.add(43); // Build mill
        fastCastleBO.add(53); // Build mining Camp
        fastCastleBO.add(44); // Feudal age research
        fastCastleBO.add(63); // Build blacksmith
        fastCastleBO.add(73); // Build Market
        fastCastleBO.add(54); // Castle Age research

        // target is barracks+3militia
        threeMilitia.add(23); // build barracks
        threeMilitia.add(22); // create militia
        threeMilitia.add(22); // create militia
        threeMilitia.add(22); // create militia

        feudalRush.add(33); // build lumber camp
        feudalRush.add(43); // build mill
        feudalRush.add(44); // feudal age research

        fastImperial.add(33); // build lumber camp
        fastImperial.add(43); // Build mill
        fastImperial.add(53); // Build mining Camp
        fastImperial.add(16); // enable farming
        fastImperial.add(44); // Feudal age research
        fastImperial.add(63); // Build blacksmith
        fastImperial.add(73); // Build Market
        fastImperial.add(54); // Castle Age research
        fastImperial.add(73); // Build Market
        fastImperial.add(73); // Build Market
        fastImperial.add(64); // imp

        farming.add(33); // build lumber camp
        farming.add(43); // Build mill
        farming.add(53); // Build mining Camp
        farming.add(44); // Feudal age research
        farming.add(16); // enable farming
        farming.add(63); // Build blacksmith
        farming.add(73); // Build Market
        farming.add(54); // Castle Age research

        // initialize GeneticAlgorithm here

        int numberOfGenerations = 1000;
        int populationSize = 100;
        int targetsize = 120;
        int numberOfGenes = 90;
        double mutationRate = 0.05;
        double mutationGrowRate = 0.05;
        int numberOfEliteChromosomes = 2;
        int numberOfSemiEliteChromosomes = 15;
        int tournamentSelectionSize = 2;
        int maxEstimatedTime = 3000;
        Chromosome.targetPrerequisites = fastImperial;
        int generationNumber = 0;

        //initialing options
        Options geneticOptions = Options.getOptions();
        geneticOptions.setOptions(numberOfGenerations, populationSize, targetsize,
        numberOfGenes, mutationRate, mutationGrowRate, numberOfEliteChromosomes, numberOfSemiEliteChromosomes,
        tournamentSelectionSize, maxEstimatedTime, gameRules, techTree);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        ArrayList<Integer> bestChromosomes = new ArrayList<>();
        ArrayList<Chromosome> bestChromosomesList = new ArrayList<>();

        // here the genetic algorithm side starts.
        Population population = new Population(populationSize).initializePopulation(targetsize, maxEstimatedTime, numberOfEliteChromosomes, numberOfSemiEliteChromosomes);

        System.out.println("-----------------------------------------------");
        System.out.println(
                "Generation #0 " + " | Fittest chromosome fitness: " + (population.getChromosomes()[0].getFitness()));
        System.out.println("" + population.getChromosomes()[0].toString());

        bestChromosomes.add(population.getChromosomes()[0].getFitness());
        bestChromosomesList.add(population.getChromosomes()[0]);

        while (geneticAlgorithm.getCurrentGeneration() < numberOfGenerations) {
            generationNumber++;
            geneticAlgorithm.setCurrentGeneration(generationNumber);
            // GeneticAlgorithm.MUTATION_RATE = GeneticAlgorithm.currentGeneration *
            // GeneticAlgorithm.MUTATION_GROW_RATE;
            System.out.println("\n------------------------------------------------");
            population = geneticAlgorithm.evolve(population);
            // population.sortChromosomeByFitness();
            System.out.println("-----------------------------------------------");
            // numberOfEliteChromosomes +
            // GeneticAlgorithm.NUMB_OF_SEMIELITE_CHROMOSOMES
            for (int i = 0; i < numberOfEliteChromosomes; i++) {
                System.out.println("Generation # " + generationNumber + " | Fittest chromosome fitness: "
                        + (population.getChromosomes()[i].getFitness()));
                System.out.println("" + population.getChromosomes()[i].toString());
                // System.out.println("" +
                // lookUpTable.convert(population.getChromosomes()[i].getGenes()));
            }
            // if (generationNumber % 5 == 0) {
            bestChromosomes.add(population.getChromosomes()[0].getFitness());
            bestChromosomesList.add(population.getChromosomes()[0]);
            // }

        }

        for (int i = 0; i < numberOfEliteChromosomes; i++) {
            System.out.println("Generation # " + generationNumber + " | Fittest chromosome fitness: "
                    + (population.getChromosomes()[i].getFitness()));
            System.out.println("" + population.getChromosomes()[i].toString());
            System.out.println("" + lookUpTable.convert(population.getChromosomes()[i].getGenes()));
        }

        int temp = 10000;
        int anotherCounter = 0;

        for (int i : bestChromosomes) {
            if (temp < i) {
                System.out.println("----------------error---------------- : " + i + " : " + anotherCounter);
                System.out.println(bestChromosomesList.get(anotherCounter - 1).toString());
            }
            temp = i;
            anotherCounter++;
        }
        LineChart.run(bestChromosomes);
    }

    public static void tryCaseRun() {
        TechTree techTree = new TechTree();
        ArrayList<Building> buildings = new ArrayList<>();
        ArrayList<Resource> resources = new ArrayList<>();
        buildings.add(techTree.TownCenter);
        GameRules gameRules1 = new GameRules(100, 200, 100, 100, 200, 0, 3, buildings);
        resources.add(new Resource(2, "Hunt without mill1", 0.408f, 30, 1600, 35, 0));
        resources.add(new Resource(2, "Berry", 0.310f, 30, 800, 10, 8));
        resources.add(new Resource(2, "Sheep", 0.330f, 30, 1600, 10, 0));
        resources.add(new Resource(1, "Wood", 0.310f, 30, 5000, 10, 8));
        resources.add(new Resource(3, "Gold", 0.330f, 30, 3000, 10, 10));
        resources.add(new Resource(4, "Stone", 0.330f, 30, 3000, 10, 10));
        SimulationMaintained firstSimulation = new SimulationMaintained(gameRules1, techTree, resources);
        ArrayList<Integer> buildOrder = new ArrayList();
        ArrayList<Integer> feudalRush = new ArrayList<>();
        buildOrder.add(21);
        buildOrder.add(21);
        buildOrder.add(11);
        buildOrder.add(23);
        buildOrder.add(22);
        buildOrder.add(21);
        buildOrder.add(31);
        buildOrder.add(22);
        buildOrder.add(22);

        feudalRush.add(21);
        feudalRush.add(21);
        feudalRush.add(21);
        feudalRush.add(21);
        feudalRush.add(21);
        feudalRush.add(21);
        feudalRush.add(33);

        feudalRush.add(11);
        feudalRush.add(11);
        feudalRush.add(11);
        feudalRush.add(11);

        feudalRush.add(43);
        feudalRush.add(21);
        feudalRush.add(21);
        feudalRush.add(21);
        feudalRush.add(21);
        feudalRush.add(21);

        feudalRush.add(11);
        feudalRush.add(11);
        feudalRush.add(11);
        feudalRush.add(11);

        feudalRush.add(53);
        feudalRush.add(31);
        feudalRush.add(31);
        feudalRush.add(31);
        feudalRush.add(44);
        firstSimulation.Run(feudalRush, 1000);
    }

    public static void tryCaseFood() {
        float totalFood = 0;
        int inGameSecond = 0;
        TechTree firstTree = new TechTree();
        Building mill = firstTree.Mill;
        Resource deer1 = new Resource(2, "Hunt without mill1", 0.408f, 30, 800, 35, 16);
        Resource berry = new Resource(2, "Berry", 0.310f, 30, 800, 10, 8);
        Resource sheep = new Resource(2, "Sheep", 0.330f, 30, 800, 10, 0);
        berry.addBuilding(mill);
        boolean flagDeer1 = true;
        boolean flagBerry = true;

        boolean flagSheep = true;

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
        }

        Tasker tasker = new Tasker();

        tasker.addResource(deer1);
        tasker.addResource(berry);
        tasker.addResource(sheep);

        tasker.addVilTask(vilList.remove(0), 2, true);
        tasker.addVilTask(vilList.remove(0), 2, true);
        tasker.addVilTask(vilList.remove(0), 2, true);
        float tempFood = -1;

        while (tempFood < totalFood) {
            tempFood = totalFood;
            inGameSecond++;
            if (inGameSecond == 451) {
                deer1.addBuilding(mill);
            }
            if (inGameSecond % 25 == 0 && !vilList.isEmpty()) {
                tasker.addVilTask(vilList.remove(0), 2, true);
                System.out.println("Vil added: " + inGameSecond);
            }
            if (flagDeer1) {
                if (deer1.currentState()) {
                    totalFood += deer1.clockWork();
                } else {
                    tasker.addCollectionofVils(deer1.removeAllWorker(), 2, true);
                    flagDeer1 = false;
                }
            }
            if (flagBerry) {
                if (berry.currentState()) {
                    totalFood += berry.clockWork();
                } else {
                    tasker.addCollectionofVils(berry.removeAllWorker(), 2, true);
                    flagBerry = false;
                }
            }
            if (flagSheep) {
                if (sheep.currentState()) {
                    totalFood += sheep.clockWork();
                } else {
                    tasker.addCollectionofVils(sheep.removeAllWorker(), 2, true);
                    flagSheep = false;
                }
            }

        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Food Collected ->" + totalFood);
    }

    public static void tryCaseDeer() {
        float totalFood = 0;
        int inGameSecond = 0;
        TechTree firstTree = new TechTree();
        Building mill = firstTree.Mill;
        Resource deer = new Resource(2, "Hunt", 0.408f, 7, 124, 35, 16);
        Resource deer2 = new Resource(2, "Hunt without mill", 0.408f, 7, 124, 35, 16);
        Resource berry = new Resource(2, "Berry", 0.310f, 16, 800, 10, 8);
        deer.addBuilding(mill);
        berry.addBuilding(mill);

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
        }

        Tasker tasker = new Tasker();

        tasker.addResource(deer);
        tasker.addResource(deer2);
        tasker.addResource(berry);
        tasker.addVilTask(vilList.remove(0), 2, true);
        tasker.addVilTask(vilList.remove(0), 2, true);
        tasker.addVilTask(vilList.remove(0), 2, true);

        while (deer.currentState() || deer2.currentState() || berry.currentState()) {
            inGameSecond++;
            if (inGameSecond % 25 == 0 && !vilList.isEmpty()) {
                tasker.addVilTask(vilList.remove(0), 2, true);
                System.out.println("Vil added: " + inGameSecond);
            }
            totalFood += deer.clockWork();
            totalFood += deer2.clockWork();
            totalFood += berry.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Food Collected ->" + totalFood);
    }

    public static void tryCaseWheelbarrow() {
        float totalWood = 0;
        int inGameSecond = 0;
        TechTree firstTree = new TechTree();
        Building lumberCamp = firstTree.LumberCamp;
        Resource Wood1 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
        }

        for (int i = 0; i < vilList.size(); i++) {
            Wood1.addWorker(vilList.get(i));
        }

        Resource WoodWheel = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        for (int i = 0; i < vilList.size(); i++) {
            WoodWheel.addWorker(vilList.get(i));
        }

        firstTree.WheelbarrowResearch.applyResearch(WoodWheel);

        inGameSecond = 0;
        totalWood = 0;

        System.out.println("Before Wheelbarrow upgrade");

        while (Wood1.currentState()) {
            inGameSecond++;
            totalWood += Wood1.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

        inGameSecond = 0;
        totalWood = 0;

        System.out.println("After Wheelbarrow upgrade");

        while (WoodWheel.currentState()) {
            inGameSecond++;
            totalWood += WoodWheel.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

    }

    public static void tryCaseLumbercampUpgrades() {
        float totalWood = 0;

        int inGameSecond = 0;

        TechTree firstTree = new TechTree();
        Building lumberCamp = firstTree.LumberCamp;
        Resource Wood1 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        Wood1.addBuilding(lumberCamp);
        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 13));
        }
        for (int i = 0; i < vilList.size(); i++) {
            Wood1.addWorker(vilList.get(i));
        }
        Resource Wood4 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        for (int i = 0; i < vilList.size(); i++) {
            Wood4.addWorker(vilList.get(i));
        }

        Resource Wood2 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        Wood2.addBuilding(lumberCamp);
        for (int i = 0; i < vilList.size(); i++) {
            Wood2.addWorker(vilList.get(i));
        }

        Resource Wood3 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        Wood3.addBuilding(lumberCamp);
        for (int i = 0; i < vilList.size(); i++) {
            Wood3.addWorker(vilList.get(i));
        }

        firstTree.DoubleBitAxeResearch.applyResearch(Wood2);
        firstTree.DoubleBitAxeResearch.applyResearch(Wood3);
        firstTree.BowSawResearch.applyResearch(Wood3);

        System.out.println("Before assigning lumbercamp");
        while (Wood4.currentState()) {
            inGameSecond++;
            totalWood += Wood4.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

        inGameSecond = 0;
        totalWood = 0;

        System.out.println("After LumberCamp but Before 1st wood upgrade");

        while (Wood1.currentState()) {
            inGameSecond++;
            totalWood += Wood1.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

        inGameSecond = 0;
        totalWood = 0;

        System.out.println("after 1st wood upgrade");

        while (Wood2.currentState()) {
            inGameSecond++;
            totalWood += Wood2.clockWork();

        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

        System.out.println("after 2nd wood upgrade");
        inGameSecond = 0;
        totalWood = 0;

        while (Wood3.currentState()) {
            inGameSecond++;
            totalWood += Wood3.clockWork();

        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);
    }

    public static void tryCaseTechtree() {
        TechTree firstTree = new TechTree();
        firstTree.clearAllReq();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        firstTree.listingAllReqbyName("Bow Saw");
    }

    public static void tryCaseResources() {
        float totalFood = 0;

        Resource Berry1 = new Resource(1, "Berry", 0.310f, 15, 1000, 10, 8);
        Resource Berry2 = new Resource(1, "Berry", 0.310f, 15, 1000, 10, 8);
        Building Mill = new Building("Mill", 0, 0, 0, 0, 0, 10);

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 13));
        }

        Berry2.addBuilding(Mill);
        for (int i = 0; i < vilList.size(); i++) {
            Berry1.addWorker(vilList.get(i));
        }

        int inGameSecond = 0;

        while (Berry1.currentState()) {
            inGameSecond++;
            totalFood += Berry1.clockWork();
            System.out.println(inGameSecond + ":ingameSecond -> " + totalFood);
        }
        System.out.println("Total resource left on berry1 = " + Berry1.getTotalResourceLeft());
        totalFood = 0;
        for (int i = 0; i < vilList.size(); i++) {
            Berry2.addWorker(vilList.get(i));
        }

        inGameSecond = 0;

        while (Berry2.currentState()) {
            inGameSecond++;
            totalFood += Berry2.clockWork();
            System.out.println(inGameSecond + ":ingameSecond -> " + totalFood);
        }

        System.out.println("Total resource left on berry2 = " + Berry2.getTotalResourceLeft());
    }

    public static void tryCaseResourcesAddVilsByTime() {
        float totalFood = 0;
        Tasker tasker = new Tasker();

        Resource BerryAllTogether = new Resource(1, "Berry", 0.310f, 15, 1000, 10, 8);
        Resource BerryOneByOne = new Resource(1, "Berry", 0.310f, 15, 1000, 10, 8);
        Building Mill1 = new Building("Mill", 0, 0, 0, 0, 0, 10);
        Building Mill2 = new Building("Mill", 0, 0, 0, 0, 0, 10);

        tasker.addResource(BerryAllTogether);

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 13));
        }

        BerryAllTogether.addBuilding(Mill2);

        tasker.addCollectionofVils(vilList, 2, true);

        int inGameSecond = 0;

        while (BerryAllTogether.currentState()) {
            inGameSecond++;
            totalFood += BerryAllTogether.clockWork();
            System.out.println(inGameSecond + ":ingameSecond -> " + totalFood);
        }
        System.out.println("Total resource left on BerryAllTogether = " + BerryAllTogether.getTotalResourceLeft());

        BerryOneByOne.addBuilding(Mill1);
        totalFood = 0;
        int i = 0;
        inGameSecond = 0;

        while (BerryOneByOne.currentState()) {
            if ((inGameSecond % 25) == 0 && i < 10) {
                BerryOneByOne.addWorker(vilList.get(i));
                i++;
            }
            inGameSecond++;
            totalFood += BerryOneByOne.clockWork();
            System.out.println(inGameSecond + ":ingameSecond -> " + totalFood);
        }

        System.out.println("Total resource left on BerryOneByOne = " + BerryOneByOne.getTotalResourceLeft());
    }

}
