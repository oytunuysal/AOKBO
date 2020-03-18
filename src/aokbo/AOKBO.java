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
public class AOKBO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        tryCaseLumbercampUpgrades();

    }

    public static void tryCaseLumbercampUpgrades() {
        float totalWood = 0;

        int inGameSecond = 0;

        TechTree firstTree = new TechTree();
        Building lumberCamp = firstTree.LumberCamp;
        Resource Wood1 = new Resource("Wood", 0.38f, 15, 5000);
        Wood1.addBuilding(lumberCamp);
        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 13));
        }
        for (int i = 0; i < vilList.size(); i++) {
            Wood1.addWorker(vilList.get(i));
        }
        Resource Wood4 = new Resource("Wood", 0.38f, 15, 5000);
        for (int i = 0; i < vilList.size(); i++) {
            Wood4.addWorker(vilList.get(i));
        }

        Resource Wood2 = new Resource("Wood", 0.38f, 15, 5000);
        Wood2.addBuilding(lumberCamp);
        for (int i = 0; i < vilList.size(); i++) {
            Wood2.addWorker(vilList.get(i));
        }

        Resource Wood3 = new Resource("Wood", 0.38f, 15, 5000);
        Wood3.addBuilding(lumberCamp);
        for (int i = 0; i < vilList.size(); i++) {
            Wood3.addWorker(vilList.get(i));
        }

        firstTree.DoubleBitAxeResearch.applyResearch(Wood2);
        firstTree.DoubleBitAxeResearch.applyResearch(Wood3);
        firstTree.BowSawResearch.applyResearch(Wood3);

        System.out.println("Before assigning lumbercamp");
        while (Wood4.currentState()) {
            inGameSecond++;
            totalWood += Wood4.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

        inGameSecond = 0;
        totalWood = 0;

        System.out.println("After LumberCamp but Before 1st wood upgrade");

        while (Wood1.currentState()) {
            inGameSecond++;
            totalWood += Wood1.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

        inGameSecond = 0;
        totalWood = 0;

        System.out.println("after 1st wood upgrade");

        while (Wood2.currentState()) {
            inGameSecond++;
            totalWood += Wood2.clockWork();

        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

        System.out.println("after 2nd wood upgrade");
        inGameSecond = 0;
        totalWood = 0;

        while (Wood3.currentState()) {
            inGameSecond++;
            totalWood += Wood3.clockWork();

        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);
    }

    public static void tryCaseTechtree() {
        TechTree firstTree = new TechTree();
        firstTree.listingAllReqbyName("Fletching");
        firstTree.clearAllReq();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        firstTree.listingAllReqbyName("SiegeWorkshop");
    }

    public static void tryCaseResources() {
        float totalFood = 0;

        Resource Berry1 = new Resource("Berry", 0.38f, 15, 1000);
        Resource Berry2 = new Resource("Berry", 0.38f, 15, 1000);
        Building Mill = new Building("Mill", 0, 0, 0, 0, 0, 10);

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 13));
        }

        Berry2.addBuilding(Mill);
        for (int i = 0; i < vilList.size(); i++) {
            Berry1.addWorker(vilList.get(i));
        }

        int inGameSecond = 0;

        while (Berry1.currentState()) {
            inGameSecond++;
            totalFood += Berry1.clockWork();
            System.out.println(inGameSecond + ":ingameSecond -> " + totalFood);
        }
        System.out.println("Total resource left on berry1 = " + Berry1.totalResourceLeft);
        totalFood = 0;
        for (int i = 0; i < vilList.size(); i++) {
            Berry2.addWorker(vilList.get(i));
        }

        inGameSecond = 0;

        while (Berry2.currentState()) {
            inGameSecond++;
            totalFood += Berry2.clockWork();
            System.out.println(inGameSecond + ":ingameSecond -> " + totalFood);
        }

        //  for (int i = 0; i < 600; i++) {
        //    totalFood += Berry2.clockWork();
        //  System.out.println(totalFood);
        //}
        System.out.println("Total resource left on berry2 = " + Berry2.totalResourceLeft);
    }

}
