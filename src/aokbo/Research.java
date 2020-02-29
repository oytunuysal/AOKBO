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
public class Research {

    private baseGameItem baseUnit;

    Research preResearch;
    int availableFlag = 1;

    public Research(int food, int wood, int gold, int stone, int age, int time) {
        baseUnit = new baseGameItem(food, wood, gold, stone, age, time);
    }

    public void addPreResearch(Research preResearch) {
        this.preResearch = preResearch;
        availableFlag = 0;
    }

}
