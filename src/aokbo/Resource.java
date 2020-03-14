/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.ArrayList;

/**
 *
 * @author Oytun
 */
public class Resource {

    String name;
    float gatherRate; // gatherrate/second
    float baseGatherRate;
    boolean dropOffBuilding;
    Building assignedBuilding; // maybe an arraylist for here
    ArrayList<Unit> workerArrayList;
    int currentWorkerNumber;
    int maxWorkerSlot;
    float totalResourceLeft;
    float resourcesProduced;
    //deploy time for new vil?
    //farms missing
    //ingame reveal/scout time(for example it will take some time to find boars ingame)

    public Resource(String name, float gatherRate, float baseGatherRate, int maxWorkerSlot, float totalResourceLeft) {
        this.name = name;
        this.gatherRate = gatherRate;
        this.baseGatherRate = baseGatherRate;
        this.workerArrayList = new ArrayList();
        this.maxWorkerSlot = maxWorkerSlot;
        this.totalResourceLeft = totalResourceLeft;
        dropOffBuilding = false;
        currentWorkerNumber = 0;
    }

    //maybe add some workers at the same time? so they could build it at the same time
    public void addBuilding(Building assign) {
        assignedBuilding = assign;
        dropOffBuilding = true;
    }

    public void addWorker(Unit gatherer) {
        workerArrayList.add(gatherer);
        currentWorkerNumber++;//need to put an error check here.
    }

    public Unit removeWorker() {
        Unit tempUnit = workerArrayList.get(currentWorkerNumber);
        workerArrayList.remove(currentWorkerNumber);
        currentWorkerNumber--; //need to put an error check here.
        return tempUnit;
    }

    public float clockWork() {
        resourcesProduced = gatherRate * currentWorkerNumber;
        totalResourceLeft -= resourcesProduced;
        //if total resources left becomes 0 or negative, call a function the inform.
        return resourcesProduced;
    }

}
