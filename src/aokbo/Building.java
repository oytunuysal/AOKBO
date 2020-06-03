/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.LinkedList;

/**
 *
 * @author Oytun
 */
public class Building extends baseGameItem {

    //maybe put a flag for economic buildings.
    //public baseGameItem baseUnit;
    private boolean available;
    private int queueTimeLeft;
    private baseGameItem currentQueueItem;
    LinkedList<baseGameItem> inQueueItems;

    public Building(String name, int food, int wood, int gold, int stone, int age, int time) {
        super(name, food, wood, gold, stone, age, time);
        this.available = true;
        this.queueTimeLeft = 0;
        this.currentQueueItem = null;
        this.inQueueItems = new LinkedList<>();
    }
    
    public Building getNew(){
        return new Building(super.name, super.requiredFood, super.requiredWood, super.requiredGold, super.requiredStone, super.requiredAge, super.creationTime);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setUnavailable() {
        this.available = false;
    }

    public void setAvailable() {
        this.available = true;
    }

    public void setQueueTime(int time) {
        this.queueTimeLeft = time;
        setUnavailable();
    }

    public baseGameItem returnFromQueue() {
        baseGameItem temp = currentQueueItem;
        if (inQueueItems.isEmpty()) {

            currentQueueItem = null;
        } else {
            currentQueueItem = inQueueItems.pop();
        }
        return temp;
    }

    public String getCurrentQIName() {
        return this.currentQueueItem.name;
    }

    public int getQueueTimeLeft() {
        return queueTimeLeft;
    }

    public int getInQueueCount() {
        return inQueueItems.size() + 1;
    }

    public void addQueue(baseGameItem queueItem) {
        if (currentQueueItem == null) {
            this.currentQueueItem = queueItem;
            setQueueTime(queueItem.creationTime);
            setUnavailable();
        } else {
            inQueueItems.add(queueItem);
        }

    }

    public boolean runQueue() {
        if (!isAvailable()) {
            queueTimeLeft--;
            if (queueTimeLeft == 0) {
                if (inQueueItems.isEmpty()) {
                    setAvailable();
                } else {
                    setQueueTime(currentQueueItem.creationTime);
                }
                return true;
            }
        }
        return false;
    }

}
