/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo.simulation;

import java.util.ArrayList;

/**
 *
 * @author Oytun
 */
public class Resource {

    private boolean state = true;
    private boolean hasDepleted = false;
    private String name;
    private int sourceType;
    private float finalGatherRate;
    private float gatherRate; // gatherrate/second
    private float baseGatherRate;  //never changes
    private float distanceGatherPoint;
    private float walkingDistanceFactor; // a number between 0-1.
    private boolean dropOffBuilding;
    private Building assignedBuilding; // maybe an arraylist for here
    private ArrayList<Unit> workerArrayList;
    private int currentWorkerNumber;
    private int maxWorkerSlot;
    private float totalResourceLeft;
    private float resourcesProduced;
    private int baseCarryCapacity;
    private int carryCapacity;
    // havent implemented deploy time for new vils yet
    //farms missing
    //ingame reveal/scout time(for example it will take some time to find boars ingame)

    public Resource(int sourceType, String name, float baseGatherRate, int maxWorkerSlot, float totalResourceLeft, int baseCarryCapacity, int distance) {
        this.sourceType = sourceType;
        this.name = name;
        this.gatherRate = baseGatherRate;
        this.baseGatherRate = baseGatherRate;
        this.workerArrayList = new ArrayList();
        this.maxWorkerSlot = maxWorkerSlot;
        this.totalResourceLeft = totalResourceLeft;
        dropOffBuilding = false;
        currentWorkerNumber = 0;
        distanceGatherPoint = distance; //for now
        this.baseCarryCapacity = baseCarryCapacity;
        this.carryCapacity = baseCarryCapacity;
        this.walkingDistanceFactor = (float) 0.8;
        calculateFinalGatherRate();

    }

    private void calculateFinalGatherRate() {
        // 0.8 is the walking speed of a vil
        //gather rate = CarryCap/ (time to collect + time to walk)
        // carry cap = 13 for starting point
        //finalGatherRate = 13 / (walkingTime() + (13 / gatherRate));
        finalGatherRate = carryCapacity / (walkingTime() + (carryCapacity / gatherRate));
        //System.out.println("Final gather rate = " + finalGatherRate);
    }

    public float walkingTime() {
        float temp = (distanceGatherPoint * 2 - 1) / (walkingDistanceFactor);
        if (temp < 0) {
            return 0;
        } else {
            return temp;
        }
    }

    public void changeCarryCap(float factor) {
        //System.out.println("before affect : " + carryCapacity);
        carryCapacity += (factor / 100) * carryCapacity;
        //System.out.println("after affect : " + carryCapacity);
        calculateFinalGatherRate();
    }

    public String getName(){
        return name;
    }

    public int getCurrentWorkerNumber() {
        return currentWorkerNumber;
    }
    public int getMaxWorkerSlot() {
        return maxWorkerSlot;
    }

    public float getTotalResourceLeft() {
        return totalResourceLeft;
    }

    public boolean currentState() {
        return state;
    }

    public boolean hasDepleted() {
        return hasDepleted;
    }

    public float getFinalGatherRate() {
        return finalGatherRate;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void depletedResource(){
        this.hasDepleted = true;
    }

    public void changeWalkingDistanceFactor(float factor) {
        //System.out.println("before affect : " + walkingDistanceFactor);
        walkingDistanceFactor += (factor / 100) * walkingDistanceFactor; //not sure about this
        //System.out.println("After : " + walkingDistanceFactor);
        calculateFinalGatherRate();
    }

    public void changeGatherRate(float factor) {
        //System.out.println("Factor/100 = " + factor / 100);
        gatherRate += (factor / 100) * baseGatherRate;
        calculateFinalGatherRate();
    }

    //maybe add some workers at the same time? so they could build it at the same time
    public void addBuilding(Building assign) {
        distanceGatherPoint = 3; //just for now
        assignedBuilding = assign;
        dropOffBuilding = true;
        calculateFinalGatherRate();
    }

    public void addWorker(Unit gatherer) {
        workerArrayList.add(gatherer);
        currentWorkerNumber++;//need to put an error check here.
    }

    public Unit removeWorker() {
        Unit tempUnit = workerArrayList.get(currentWorkerNumber - 1);
        workerArrayList.remove(currentWorkerNumber - 1);
        currentWorkerNumber--; //need to put an error check here.
        return tempUnit;
    }

    public ArrayList<Unit> removeAllWorker() {
        ArrayList<Unit> tempList = new ArrayList<>();
        while (!workerArrayList.isEmpty()) {
            tempList.add(removeWorker());
        }
        return tempList;
    }

    //Returns gather rate difference between with and without Eco Building
    public float differenceAfterBuilding() {
        float resourcePerVil, time, newGRwithoutECO, newGRwithECO;
        int currentWorkerNumber;
        if (this.currentWorkerNumber == 0) {
            currentWorkerNumber = 1;
        } else {
            currentWorkerNumber = this.currentWorkerNumber;
        }
//        resourcePerVil = totalResourceLeft / (currentWorkerNumber);
//        time = resourcePerVil / finalGatherRate;
//        time += distanceGatherPoint / walkingDistanceFactor; //deploy time
//        newGRwithoutECO = resourcePerVil / time;
//
//        resourcePerVil = totalResourceLeft / (currentWorkerNumber);
//        time = resourcePerVil / finalGatherRate;
//        time += 1 / walkingDistanceFactor; //deploy time
//        newGRwithECO = resourcePerVil / time;
        newGRwithoutECO = carryCapacity / (walkingTime() + (carryCapacity / gatherRate));
        newGRwithECO = carryCapacity / (1 + (carryCapacity / gatherRate));
//        System.out.println(newGRwithECO);
//        System.out.println(newGRwithoutECO);
//        System.out.println((newGRwithECO - newGRwithoutECO));
//        System.out.println("");
        return newGRwithECO - newGRwithoutECO;
    }

    public float clockWork() {
        if (currentState() == true && currentWorkerNumber > 0) {
            resourcesProduced = finalGatherRate * currentWorkerNumber;
            totalResourceLeft -= resourcesProduced;
            if (totalResourceLeft <= 0) {
                //means it is depleted, if reseeadable -> reseed. else just moveout the vils.
                resourcesProduced += totalResourceLeft;
                state = false;
                totalResourceLeft = 0;
                //System.out.println(name + " depleted!");
            }
            //if total resources left becomes 0 or negative, call a function the inform.
            return resourcesProduced;
        } else {
            return 0;
        }

    }
}