/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

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

    public Resource calculateOptimalResource(int type) {
        Resource temp = null;
        float max = 0;
        float time;
        float resourcePerVil;
        float newGR; //new gather rate
        for (Resource next : allResources) {
            if (next.sourceType == type && next.currentWorkerNumber < next.maxWorkerSlot) {
                resourcePerVil = next.totalResourceLeft / (next.currentWorkerNumber + 1);
                time = resourcePerVil / next.finalGatherRate;
                //time += 18; //deploy time
                time += next.walkingTime();
                newGR = resourcePerVil / time;
                if (newGR > max) {
                    max = newGR;
                    temp = next;
                }
            }
        }
        if (temp != null) {
//            System.out.println("newGR = " + max + " Name = " + temp.name);
//            System.out.println("Walking time = " + temp.walkingTime());
        }

        return temp;
    }

    public void addVilTask(Unit gatherer, int position) {
        //int indicates the resource
        Resource temp;

        temp = calculateOptimalResource(position);
        if (temp != null) {
            temp.addWorker(gatherer);
        } else {
            //System.out.println("No more space for that job!");
        }
    }

    public Resource findResource(String name) {
        for (Resource next : allResources) {
            if (next.name.equalsIgnoreCase(name)) {
                return next;
            }
        }
        return null;
    }

    public void addCollectionofVils(Collection<Unit> vils, int position) {
        for (Unit next : vils) {
            addVilTask(next, position);
        }
    }
}
