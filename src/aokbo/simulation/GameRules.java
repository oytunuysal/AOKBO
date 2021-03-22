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
public class GameRules {

    int food, wood, gold, stone, maxPop, startingAge, startingVilCount;
    ArrayList<Building> startingBuildings;

    public GameRules(int startingWood, int startingFood, int startingGold, int startingStone, int maxPop, int startingAge, int startingVilCount, ArrayList<Building> startingBuildings) {
        this.food = startingFood;
        this.wood = startingWood;
        this.gold = startingGold;
        this.stone = startingStone;
        this.maxPop = maxPop;
        this.startingAge = startingAge;
        this.startingVilCount = startingVilCount;
        this.startingBuildings = startingBuildings;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setMaxPop(int maxPop) {
        this.maxPop = maxPop;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getMaxPop() {
        return maxPop;
    }

    public int getStone() {
        return stone;
    }

    public int getWood() {
        return wood;
    }

}
