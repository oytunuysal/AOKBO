/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo.simulation;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Oytun
 */
public class Tasker { //This is a sort of adapter class.

    ArrayList<Resource> allResources;

    //maybe a lookup table for here
    /*
    Inputs for decider*
    1-Wood
    2-Food
    3-Gold
    4-Stone
     *///holds all available resources.
    //Comparator<Resource> cm1;
    /*
    1-Wood
    2-Gold
    3-Stone
    4-Bush
    5-Shepard
    6-Hunter
     */
    public Tasker() {
        this.allResources = new ArrayList<>();
    }

    public void addResource(Resource aResource) {
        allResources.add(aResource);

    }

    public void addResourceList(Collection resource) {
        allResources.addAll(resource);
    }

    public Resource calculateOptimalResource(int type, boolean print) {
        Resource temp = null;
        float max = 0;
        float newGR; //new gather rate
        for (Resource next : allResources) {
            if (next.getSourceType() == type && next.getCurrentWorkerNumber() < next.getMaxWorkerSlot()) {
                newGR = calculateGR(next);
                if (newGR > max) {
                    max = newGR;
                    temp = next;
                }
            }
        }
        //if maxGR<farmingGR make farm and work on farm. 
        if (temp != null && print == true) {
            System.out.println("newGR = " + max + " Name = " + temp.getName());
//            System.out.println("Walking time = " + temp.walkingTime());
        }

        return temp;
    }

    public float calculateGR(Resource next) {
        float time;
        float resourcePerVil;

        resourcePerVil = next.getTotalResourceLeft() / (next.getCurrentWorkerNumber() + 1);
        time = resourcePerVil / next.getFinalGatherRate();
        //time += 18; //deploy time
        time += next.walkingTime();

        return (resourcePerVil / time);
    }

    public boolean isFarmOk() { //takes the actual farm as argument
        Resource temp = calculateOptimalResource(2, false);
        float newGR;
        float farmGR = 0.30f;
        if (temp != null) {
            newGR = calculateGR(temp);
            if (newGR < farmGR) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void addVilTask(Unit gatherer, int position, boolean showPrints) {
        //int indicates the resource
        Resource temp;

        temp = calculateOptimalResource(position, showPrints);
        if (temp != null) {
            temp.addWorker(gatherer);
        } else {
            //System.out.println("No more space for that job!");
        }
    }

    public Resource findResource(String name) {
        for (Resource next : allResources) {
            if (next.getName().equalsIgnoreCase(name)) {
                return next;
            }
        }
        return null;
    }

    public void addCollectionofVils(Collection<Unit> vils, int position, boolean showPrints) {
        for (Unit next : vils) {
            addVilTask(next, position, showPrints);
        }
    }
}
