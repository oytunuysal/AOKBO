/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Oytun
 */
public class Tasker { //This is a sort of adapter class.

    ArrayList<Resource> allResources; //holds all available resources.
    //maybe a lookup table for here

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

    public void addResource(Resource eResource) {
        allResources.add(eResource);
    }

    public void addResourceList(Collection resource) {
        allResources.addAll(resource);
    }

    public void addVilTask(Unit gatherer, int position) {
        //int indicates the resource
        Resource temp;
        switch (position) {
            case 1:
                temp = findResource("Wood");
                if (temp != null) {
                    temp.addWorker(gatherer);
                }
                break;
            case 2:
                temp = findResource("Gold");
                if (temp != null) {
                    temp.addWorker(gatherer);
                }
                break;
            case 3:
                temp = findResource("Stone");
                if (temp != null) {
                    temp.addWorker(gatherer);
                }
                break;
            case 4:
                temp = findResource("Berry");
                if (temp != null) {
                    temp.addWorker(gatherer);
                }
                break;
            case 5:
                temp = findResource("Sheep");
                if (temp != null) {
                    temp.addWorker(gatherer);
                }
                break;
            case 6:
                temp = findResource("Hunt");
                if (temp != null) {
                    temp.addWorker(gatherer);
                }
                break;
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
