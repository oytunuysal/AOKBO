/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo.simulation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Oytun
 */
public class LookUp { //maybe do it as static

    HashMap<Integer, String> lookUpTable = new HashMap<>();

    public LookUp() {
        lookUpTable.put(11, "Villager to Wood");
        lookUpTable.put(21, "Villager to Food");
        lookUpTable.put(31, "Villager to Gold");
        lookUpTable.put(41, "Villager to Stone");
        lookUpTable.put(22, "Create MilitaLine");
        lookUpTable.put(23, "Build Barracks");
        lookUpTable.put(14, "Research M@A");
        lookUpTable.put(43, "Build Mill");
        lookUpTable.put(33, "Build LC");
        lookUpTable.put(53, "Build MC");
        lookUpTable.put(73, "Build Market");
        lookUpTable.put(63, "Build Blacksmith");
        lookUpTable.put(44, "Research Feudal");
        lookUpTable.put(54, "Research Castle");
        lookUpTable.put(64, "Research Imperial");
        lookUpTable.put(16, "Enable Farms");
    }

    public ArrayList convert(ArrayList<Integer> chromosome) {
        ArrayList<String> temp = new ArrayList<>();
        for (int index : chromosome) {
            temp.add(lookUpTable.get(index));
        }
        return temp;
    }
}
