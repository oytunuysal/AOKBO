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

        tryCaseFood();
        //tryCaseDeer();
        //tryCaseWheelbarrow();
        //tryCaseTechtree();
        //tryCaseResources();
        //tryCaseResourcesAddVilsByTime();

    }

    public static void tryCaseFood() {
        float totalFood = 0;
        int inGameSecond = 0;
        TechTree firstTree = new TechTree();
        Building mill = firstTree.Mill;
        Resource deer1 = new Resource(2, "Hunt without mill1", 0.408f, 30, 800, 35, 16);
        //Resource deer2 = new Resource(2, "Hunt without mill2", 0.408f, 7, 124, 35, 16);
        //Resource deer3 = new Resource(2, "Hunt without mill3", 0.408f, 7, 124, 35, 16);
        Resource berry = new Resource(2, "Berry", 0.310f, 30, 800, 10, 8);
        Resource sheep = new Resource(2, "Sheep", 0.330f, 30, 800, 10, 0);
        berry.addBuilding(mill);
        boolean flagDeer1 = true;
        boolean flagBerry = true;

        boolean flagSheep = true;

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
        }

        Tasker tasker = new Tasker();

        tasker.addResource(deer1);
        //tasker.addResource(deer2);
        //tasker.addResource(deer3);
        tasker.addResource(berry);
        tasker.addResource(sheep);

        tasker.addVilTask(vilList.remove(0), 2);
        tasker.addVilTask(vilList.remove(0), 2);
        tasker.addVilTask(vilList.remove(0), 2);
        float tempFood = -1;

        while (tempFood < totalFood) {
            tempFood = totalFood;
            inGameSecond++;
            if (inGameSecond == 451) {
                deer1.addBuilding(mill);
            }
            if (inGameSecond % 25 == 0 && !vilList.isEmpty()) {
                tasker.addVilTask(vilList.remove(0), 2);
                System.out.println("Vil added: " + inGameSecond);
            }
            if (flagDeer1) {
                if (deer1.currentState()) {
                    totalFood += deer1.clockWork();
                } else {
                    tasker.addCollectionofVils(deer1.removeAllWorker(), 2);
                    flagDeer1 = false;
                }
            }
            if (flagBerry) {
                if (berry.currentState()) {
                    totalFood += berry.clockWork();
                } else {
                    tasker.addCollectionofVils(berry.removeAllWorker(), 2);
                    flagBerry = false;
                }
            }
            if (flagSheep) {
                if (sheep.currentState()) {
                    totalFood += sheep.clockWork();
                } else {
                    tasker.addCollectionofVils(sheep.removeAllWorker(), 2);
                    flagSheep = false;
                }
            }

            // totalFood += deer2.clockWork();
            // totalFood += deer3.clockWork();
            //totalFood += berry.clockWork();
            //totalFood += sheep.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Food Collected ->" + totalFood);
    }

    public static void tryCaseDeer() {
        float totalFood = 0;
        int inGameSecond = 0;
        TechTree firstTree = new TechTree();
        Building mill = firstTree.Mill;
        Resource deer = new Resource(2, "Hunt", 0.408f, 7, 124, 35, 16);
        Resource deer2 = new Resource(2, "Hunt without mill", 0.408f, 7, 124, 35, 16);
        Resource berry = new Resource(2, "Berry", 0.310f, 16, 800, 10, 8);
        deer.addBuilding(mill);
        berry.addBuilding(mill);

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
        }

        Tasker tasker = new Tasker();

        tasker.addResource(deer);
        tasker.addResource(deer2);
        tasker.addResource(berry);
        tasker.addVilTask(vilList.remove(0), 2);
        tasker.addVilTask(vilList.remove(0), 2);
        tasker.addVilTask(vilList.remove(0), 2);

        while (deer.currentState() || deer2.currentState() || berry.currentState()) {
            inGameSecond++;
            if (inGameSecond % 25 == 0 && !vilList.isEmpty()) {
                tasker.addVilTask(vilList.remove(0), 2);
                System.out.println("Vil added: " + inGameSecond);
            }
            totalFood += deer.clockWork();
            totalFood += deer2.clockWork();
            totalFood += berry.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Food Collected ->" + totalFood);
    }

    public static void tryCaseWheelbarrow() {
        float totalWood = 0;
        int inGameSecond = 0;
        TechTree firstTree = new TechTree();
        Building lumberCamp = firstTree.LumberCamp;
        Resource Wood1 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
        }

        for (int i = 0; i < vilList.size(); i++) {
            Wood1.addWorker(vilList.get(i));
        }

        Resource WoodWheel = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        for (int i = 0; i < vilList.size(); i++) {
            WoodWheel.addWorker(vilList.get(i));
        }

        firstTree.WheelbarrowResearch.applyResearch(WoodWheel);

        inGameSecond = 0;
        totalWood = 0;

        System.out.println("Before Wheelbarrow upgrade");

        while (Wood1.currentState()) {
            inGameSecond++;
            totalWood += Wood1.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

        inGameSecond = 0;
        totalWood = 0;

        System.out.println("After Wheelbarrow upgrade");

        while (WoodWheel.currentState()) {
            inGameSecond++;
            totalWood += WoodWheel.clockWork();
        }
        System.out.println("ingameSecond -> " + inGameSecond + " \nTotal Wood Collected ->" + totalWood);

    }

    public static void tryCaseLumbercampUpgrades() {
        float totalWood = 0;

        int inGameSecond = 0;

        TechTree firstTree = new TechTree();
        Building lumberCamp = firstTree.LumberCamp;
        Resource Wood1 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        Wood1.addBuilding(lumberCamp);
        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 13));
        }
        for (int i = 0; i < vilList.size(); i++) {
            Wood1.addWorker(vilList.get(i));
        }
        Resource Wood4 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        for (int i = 0; i < vilList.size(); i++) {
            Wood4.addWorker(vilList.get(i));
        }

        Resource Wood2 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
        Wood2.addBuilding(lumberCamp);
        for (int i = 0; i < vilList.size(); i++) {
            Wood2.addWorker(vilList.get(i));
        }

        Resource Wood3 = new Resource(1, "Wood", 0.388f, 15, 5000, 10, 8);
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
        //firstTree.listingAllReqbyName("Fletching");
        firstTree.clearAllReq();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        //    firstTree.listingAllReqbyName("SiegeWorkshop");
        firstTree.listingAllReqbyName("Bow Saw");
    }

    public static void tryCaseResources() {
        float totalFood = 0;

        Resource Berry1 = new Resource(1, "Berry", 0.310f, 15, 1000, 10, 8);
        Resource Berry2 = new Resource(1, "Berry", 0.310f, 15, 1000, 10, 8);
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

    public static void tryCaseResourcesAddVilsByTime() {
        float totalFood = 0;
        Tasker tasker = new Tasker();

        Resource BerryAllTogether = new Resource(1, "Berry", 0.310f, 15, 1000, 10, 8);
        Resource BerryOneByOne = new Resource(1, "Berry", 0.310f, 15, 1000, 10, 8);
        Building Mill1 = new Building("Mill", 0, 0, 0, 0, 0, 10);
        Building Mill2 = new Building("Mill", 0, 0, 0, 0, 0, 10);

        tasker.addResource(BerryAllTogether);

        ArrayList<Unit> vilList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            vilList.add(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 13));
        }

        BerryAllTogether.addBuilding(Mill2);

        //for (int i = 0; i < vilList.size(); i++) {
        //    BerryAllTogether.addWorker(vilList.get(i));
        //}
        tasker.addCollectionofVils(vilList, 2);

        int inGameSecond = 0;

        while (BerryAllTogether.currentState()) {
            inGameSecond++;
            totalFood += BerryAllTogether.clockWork();
            System.out.println(inGameSecond + ":ingameSecond -> " + totalFood);
        }
        System.out.println("Total resource left on BerryAllTogether = " + BerryAllTogether.totalResourceLeft);

        BerryOneByOne.addBuilding(Mill1);
        totalFood = 0;
        int i = 0;
        inGameSecond = 0;

        while (BerryOneByOne.currentState()) {
            if ((inGameSecond % 25) == 0 && i < 10) {
                BerryOneByOne.addWorker(vilList.get(i));
                i++;
            }
            inGameSecond++;
            totalFood += BerryOneByOne.clockWork();
            System.out.println(inGameSecond + ":ingameSecond -> " + totalFood);
        }

        //  for (int i = 0; i < 600; i++) {
        //    totalFood += Berry2.clockWork();
        //  System.out.println(totalFood);
        //}
        System.out.println("Total resource left on BerryOneByOne = " + BerryOneByOne.totalResourceLeft);
    }

}
