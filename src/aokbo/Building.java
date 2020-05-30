/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

/**
 *
 * @author Oytun
 */
public class Building extends baseGameItem {

    //maybe put a flag for economic buildings.
    //public baseGameItem baseUnit;
    private boolean available;

    public Building(String name, int food, int wood, int gold, int stone, int age, int time) {
        super(name, food, wood, gold, stone, age, time);
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }
    
    public void setUnavailable(){
        this.available = false;
    }
    
    public void setAvailable(){
        this.available = true;
    }
}
