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
public class GraphData {

    private ArrayList<Integer> food;
    private ArrayList<Integer> wood;
    private ArrayList<Integer> gold;
    private ArrayList<Integer> stone;
    private ArrayList<Integer> population;
    private int ingameSeconds;
    private ArrayList<Integer> villagerCount;
    private ArrayList<Integer> idleVillagerCount;

    public GraphData() {
        this.food = new ArrayList<Integer>();
        this.wood = new ArrayList<Integer>();
        this.gold = new ArrayList<Integer>();
        this.stone = new ArrayList<Integer>();
        this.population = new ArrayList<Integer>();
        this.ingameSeconds = 0;
        this.idleVillagerCount = new ArrayList<>();
        this.villagerCount = new ArrayList<>();
    }

    public ArrayList<Integer> getVillagerCount() {
        return villagerCount;
    }

    public ArrayList<Integer> getIdleVillagerCount() {
        return idleVillagerCount;
    }

    public int getIngameSeconds() {
        return ingameSeconds;
    }

    public ArrayList<Integer> getFood() {
        return food;
    }

    public ArrayList<Integer> getWood() {
        return wood;
    }

    public ArrayList<Integer> getGold() {
        return gold;
    }

    public ArrayList<Integer> getStone() {
        return stone;
    }

    public ArrayList<Integer> getPopulation() {
        return population;
    }

    public void setIngameSeconds(int ingameSeconds) {
        this.ingameSeconds = ingameSeconds;
    }

    public void addFood(int amount) {
        this.food.add(amount);
    }

    public void addWood(int amount) {
        this.wood.add(amount);
    }

    public void addGold(int amount) {
        this.gold.add(amount);
    }

    public void addStone(int amount) {
        this.stone.add(amount);
    }

    public void addPop(int amount) {
        this.population.add(amount);
    }
    public void addVillagerCount(int amount) {
        this.villagerCount.add(amount);
    }
    public void addIdleVillagerCount(int amount) {
        this.idleVillagerCount.add(amount);
    }

}
