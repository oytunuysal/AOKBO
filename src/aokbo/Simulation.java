/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Oytun
 */
public class Simulation {

    private float totalFood;
    private float totalWood;
    private float totalGold;
    private float totalStone;
    private TechTree techTree;
    ArrayList<Resource> resourceList;
    Tasker tasker;
    ArrayList<Unit> villagerList;
    ArrayList<Building> buildings;

    public Simulation(gameRules rules, TechTree techTree, ArrayList<Resource> resources) {
        this.totalWood = rules.getWood();
        this.totalFood = rules.getFood();
        this.totalGold = rules.getGold();
        this.totalStone = rules.getStone();
        this.techTree = techTree;
        this.resourceList = resources;
        this.tasker = new Tasker();
        this.villagerList = new ArrayList<>();
        for (int i = 0; i < rules.startingVilCount; i++) {
            this.villagerList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
        }
        this.buildings = (ArrayList<Building>) rules.startingBuildings.clone();

    }

    public void Run(ArrayList buildOrder, int maxEstimatedTime) {
        int token = 1;
        boolean lock = false;
        int ingameTime = 0;
        int temp;
        int nextInput1 = 0;
        int nextInput2 = 0;
        Iterator boIterator = buildOrder.iterator();
        while (ingameTime < maxEstimatedTime) {
            if (token == 1) { //I may remove this if statement, it has almost no use now
                if (lock == false) {
                    if (boIterator.hasNext()) {
                        temp = (int) boIterator.next();
                        nextInput1 = temp % 10; // last index of a number
                        nextInput2 = (temp - nextInput1) / 10; //cut out the last index of the number
                    } else {
                        return;
                    }
                }
                switch (nextInput1) {
                    case 1:
                        //build
                        //no building time for now
                        token = buildCommand(nextInput2);
                        if (token == 2) { //token == 2 in front because of performance.
                            lock = true;
                            token = 1;
                        } else if (token == 1) {
                            lock = false;
                        }
                        break;
                    case 2:
                        //research
                        //no research time and occupying building for now either.
                        break;
                    case 3:
                        //produceUnit
                        token = produceUnit(nextInput2);
                        if (token == 2) { //token == 2 in front because of performance.
                            lock = true;
                            token = 1;
                        } else if (token == 1) {
                            lock = false;
                        }
                        break;
                    case 0:
                        System.out.println("ERROR!!!!!!!!");
                        return;

                }
            } else if (token == 3) {
                System.out.println("not found ERROR!!!!!!!!");
                return;
            }
            //call resource.clockwork
            resourceHandler();

            ingameTime++;
        }

    }

    private int researchCommand(int index) {
        switch (index) {
            case 1:
                if (hasEnoughResource(techTree.ManAtArms)) {
                    totalFood -= techTree.ManAtArms.getRequiredFood();
                    totalGold -= techTree.ManAtArms.getRequiredGold();
                    //maybe apply method here
                    return 1;
                } else {
                    return 2;
                }
        }
        return 3;
    }

    private int buildCommand(int index) {
        switch (index) {
            case 1:
                if (hasEnoughResource(techTree.Barracks)) {
                    totalWood -= techTree.Barracks.getRequiredWood();
                    return 1;
                } else {
                    return 2;
                }
        }
        return 3;
    }

    private int produceUnit(int target) {
        /* 
        1-militia
         */

 /* return values:
            1-success
            2- need to wait for resource
         */
        switch (target) {
            case 1: //militia line
                if (true) { //if building exist and available then we check the rest
                    if (hasEnoughResource(techTree.MilitiaLine)) {
                        totalFood -= techTree.MilitiaLine.getRequiredFood();
                        totalWood -= techTree.MilitiaLine.getRequiredWood();
                        totalGold -= techTree.MilitiaLine.getRequiredGold();
                        totalStone -= techTree.MilitiaLine.getRequiredStone();
                        return 1;
                    } else {
                        return 2;
                    }
                }

        }
        return 3; //cant find the unit

    }

    private boolean hasEnoughResource(baseGameItem anItem) {
        return anItem.getRequiredFood() <= totalFood && anItem.getRequiredWood() <= totalWood && anItem.getRequiredGold() <= totalGold && anItem.getRequiredStone() <= totalStone;
    }

    private void resourceHandler() {//I will turn this into a class later

        /*
         Inputs for decider*
         1-Wood
         2-Food
         3-Gold
         4-Stone
         */
        for (Iterator<Resource> iterator = resourceList.iterator(); iterator.hasNext();) {
            Resource next = iterator.next();
            if (!next.hasDepleted()) {
                switch (next.getSourceType()) { // put wood food gold stone into 4 size of array.
                    case 1:
                        if (next.currentState()) {
                            totalWood += next.clockWork();
                        } else {
                            villagerList.addAll(next.removeAllWorker());
                            next.hasDepleted = true;
                        }
                        break;
                    case 2:
                        if (next.currentState()) {
                            totalFood += next.clockWork();
                        } else {
                            villagerList.addAll(next.removeAllWorker());
                            next.hasDepleted = true;
                        }
                        break;
                    case 3:
                        if (next.currentState()) {
                            totalGold += next.clockWork();
                        } else {
                            villagerList.addAll(next.removeAllWorker());
                            next.hasDepleted = true;
                        }
                        break;
                    case 4:
                        if (next.currentState()) {
                            totalStone += next.clockWork();
                        } else {
                            villagerList.addAll(next.removeAllWorker());
                            next.hasDepleted = true;
                        }
                        break;
                }
            }

        }
    }
}
