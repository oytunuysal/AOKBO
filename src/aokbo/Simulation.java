/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Oytun
 */
public class Simulation {

    private float totalFood;
    private float totalWood;
    private float totalGold;
    private float totalStone;
    private TechTree techTree;
    ArrayList<Resource> resourceList;
    Tasker tasker;
    LinkedList<Unit> villagerList;
    ArrayList<Building> buildings; // instead of this, I can parse building into Barracks, TCS ect in constuctor.
    ArrayList<Building> allTCs;
    int inQ = 0;
    boolean enableFarms;

    public Simulation(gameRules rules, TechTree techTree, ArrayList<Resource> resources) {
        this.totalWood = rules.getWood();
        this.totalFood = rules.getFood();
        this.totalGold = rules.getGold();
        this.totalStone = rules.getStone();
        this.techTree = techTree;
        this.resourceList = resources;
        this.tasker = new Tasker();
        this.tasker.addResourceList(resources);
        this.villagerList = new LinkedList<>();
        this.allTCs = new ArrayList<>();
        this.inQ = 0;
        this.enableFarms = true;
        for (int i = 0; i < rules.startingVilCount; i++) {
            this.villagerList.push(new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
        }
        this.buildings = (ArrayList<Building>) rules.startingBuildings.clone();

        for (Building tc : buildings) {
            if (tc.name.contains("TownCenter")) {
                this.allTCs.add(tc);

            }
        }

    }

    public int Run(ArrayList buildOrder, int maxEstimatedTime) {
        int token = 1;
        boolean waitForResource = false;
        int ingameTime = 0;
        int temp;
        int nextInput1 = 0;
        int nextInput2 = 0;
        Iterator boIterator = buildOrder.iterator();
        while (ingameTime < maxEstimatedTime) {
            //System.out.println("IngameSeconds = " + ingameTime);
            if (token == 1 || token == 5) { //I may remove this if statement, it has almost no use now
                if (waitForResource == false && token != 5) {
                    if (boIterator.hasNext()) {
                        temp = (int) boIterator.next();
                        //System.out.println("temp = " + temp);
                        nextInput1 = temp % 10; // last index of a number
                        //System.out.println("input1 = " + nextInput1);
                        nextInput2 = (temp - nextInput1) / 10; //cut out the last index of the number
                        //System.out.println("nextinput2 = " + nextInput2);
                    } else {
                        //System.out.println("Build order finished!");
                        if (inQ == 0) {
                            //System.out.println("Fitness " + ingameTime);
                            return ingameTime;
                        } else {
                            token = 4;
                        }

                    }
                }
                if (token != 4) { //token = 4 only once bo finishes and something still in q
                    switch (nextInput1) {
                        case 1:
                            //System.out.println("                                                     1");
                            token = villagerTaskCommand(nextInput2);
                            if (token == 2) {
                                waitForResource = true;
                                token = 1;
                            } else if (token == 5) {
                                //System.out.println("waiting for a villager to give the task!");
                            } else if (token == 1) {
                                waitForResource = false;
                            }
                            break;
                        case 3:
                            //System.out.println("                                                     3");
                            //build
                            //no building time for now
                            token = buildCommand(nextInput2);
                            if (token == 2) { //token == 2 in front because of performance.
                                waitForResource = true;
                                token = 1;
                            } else if (token == 1) {
                                waitForResource = false;
                            }
                            break;
                        case 4:
                            //System.out.println("                                                     4");
                            //research
                            token = researchCommand(nextInput2);
                            if (token == 2) { //token == 2 in front because of performance.
                                waitForResource = true;
                                token = 1;
                            } else if (token == 1) {
                                waitForResource = false;
                            }
                            //no research time and occupying building for now either.
                            break;
                        case 2:
                            //System.out.println("                                                     2");
                            //produceUnit
                            token = produceUnit(nextInput2);
                            if (token == 2) { //token == 2 in front because of performance.
                                waitForResource = true;
                                token = 1;
                            } else if (token == 1) {
                                waitForResource = false;
                            }
                            break;
                        case 6:
                            //special commands
                            specialCommand(nextInput2);
                            break;

                        case 0:
                            //System.out.println("ERROR!!!!!!!!");
                            //System.out.println("Fitness                                " + ingameTime + " " + totalWood + " " + totalFood + " " + totalGold + " " + totalStone);
                            return ingameTime;

                    }
                }

            } else if (token == 3) {
                //System.out.println("invalid index ERROR!!!!!!!!");
                return maxEstimatedTime;
            } else if (token == 4 && inQ == 0) {
                //System.out.println("Fitness                                       " + ingameTime + " " + totalWood + " " + totalFood + " " + totalGold + " " + totalStone);
                return ingameTime;
            }
            //call resource.clockwork
            resourceHandler();
            //other time dependent stuff like researchs ect
            timeDependent();
            ingameTime++;
            //System.out.println("W: " + this.totalWood + " " + "F: " + this.totalFood + " " + "G: " + this.totalGold + " " + "S: " + this.totalStone);
            //System.out.println("inQ =                            " + inQ);
        }
        //System.out.println("Fitness " + ingameTime);
        return maxEstimatedTime - ingameTime;

    }

    private void assignEcoBuilding(Building building) { //Maybe put this part inside tasker?
        float bestGR = 0, temp = 0;
        Resource tempResource = null;
        if (building.name.contains("Mill")) {
            for (Resource aResource : resourceList) {
                if (aResource.sourceType == 2) {
                    temp = aResource.differenceAfterBuilding();
                    if (temp > bestGR) {
                        bestGR = temp;
                        tempResource = aResource;
                    }
                }
            }
        } else if (building.name.contains("Lumber Camp")) {
            for (Resource aResource : resourceList) {
                if (aResource.sourceType == 1) {
                    temp = aResource.differenceAfterBuilding();
                    if (temp > bestGR) {
                        bestGR = temp;
                        tempResource = aResource;
                    }
                }
            }
        } else if (building.name.contains("Mining Camp")) {
            for (Resource aResource : resourceList) {
                if (aResource.sourceType == 3 || aResource.sourceType == 4) {
                    temp = aResource.differenceAfterBuilding();
                    if (temp > bestGR) {
                        bestGR = temp;
                        tempResource = aResource;
                    }
                }
            }
        }

        if (tempResource != null) {
            tempResource.addBuilding(building);
        }
    }

    private boolean hasEnoughResource(baseGameItem anItem) {
        return (anItem.getRequiredFood() <= totalFood && anItem.getRequiredWood() <= totalWood && anItem.getRequiredGold() <= totalGold && anItem.getRequiredStone() <= totalStone);
    }

    private void executeCost(baseGameItem anItem) {
        totalFood = totalFood - anItem.getRequiredFood();
        totalWood = totalWood - anItem.getRequiredWood();
        totalGold = totalGold - anItem.getRequiredGold();
        totalStone = totalStone - anItem.getRequiredStone();
    }

    private void timeDependent() { //executes run on each building with queue. then it applies the affect aswell
        for (Building aBuilding : buildings) {
            if (aBuilding.runQueue()) { // runqueue returns true only if the queue completes and reutrns only once.
                inQ--;
                baseGameItem unitOrResearch = aBuilding.returnFromQueue();
                if (unitOrResearch instanceof Research) {
                    timeDependentResearch((Research) unitOrResearch); //applies the research on resources
                } else if (unitOrResearch instanceof Unit) {
                    timeDependentProduction((Unit) unitOrResearch);
                    //if it is a villager, adds on villagerList.
                }
            }
        }
    }

    private void timeDependentProduction(Unit vilOrMil) { //if its a vil, adds on vil-list.
        if (vilOrMil.getName().equalsIgnoreCase("Vil")) {
            villagerList.add(vilOrMil);
        }
        //System.out.println(vilOrMil.getName() + " created!");

    }

    private void timeDependentResearch(Research aResearch) { // if its a research,
        for (Resource aResource : resourceList) {
            for (Research.UpgradeAffect anAffect : ((Research) aResearch).upgradeAffects) {
                if (anAffect.getResourceType() == 0) {
                    ((Research) aResearch).applyResearch(aResource);
                } else if (anAffect.getResourceType() == aResource.getSourceType()) {
                    aResearch.applyResearch(aResource);
                }
            }

        }
    }

    private int tryResearch(Research research, String building) {
        if (hasEnoughResource(research)) {
            boolean hasFound = false;
            executeCost(research);
            //maybe apply method here
            hasFound = putIntoQueue(building, research);
            if (hasFound) {
                return 1;
            } else {
                //System.out.println("No space in queue");
                return 1;
            }
        } else {
            return 2;
        }
    }

    private int researchCommand(int index) {
        switch (index) {
            case 1:
                return tryResearch(techTree.ManAtArms, "Barracks");

//                if (hasEnoughResource(techTree.ManAtArms)) {
//                    boolean hasFound = false;
//                    executeCost(techTree.ManAtArms);
//                    //maybe apply method here
//                    hasFound = putIntoQueue("Barracks", techTree.ManAtArms);
//                    if (hasFound) {
//                        return 1;
//                    } else {
//                        //System.out.println("No space in queue");
//                        return 1;
//                    }
//                } else {
//                    return 2;
//                }
            case 2:
                return tryResearch(techTree.DoubleBitAxeResearch, "Lumber Camp");
//                if (hasEnoughResource(techTree.DoubleBitAxeResearch)) {
//                    boolean hasFound = false;
//                    executeCost(techTree.DoubleBitAxeResearch);
//                    //maybe apply method here
//                    hasFound = putIntoQueue("Lumber Camp", techTree.DoubleBitAxeResearch);
//                    if (hasFound) {
//                        return 1;
//                    } else {
//                        //System.out.println("No space in queue");
//                        return 1;
//                    }
//                } else {
//                    return 2;
//                }
            case 3:
                return tryResearch(techTree.WheelbarrowResearch, "TownCenter");
//                if (hasEnoughResource(techTree.WheelbarrowResearch)) {
//                    boolean hasFound = false;
//                    executeCost(techTree.WheelbarrowResearch);
//                    //maybe apply method here
//                    hasFound = putIntoQueue("TownCenter", techTree.WheelbarrowResearch);
//                    if (hasFound) {
//                        return 1;
//                    } else {
//                        //System.out.println("No space in queue");
//                        return 1;
//                    }
//                } else {
//                    return 2;
//                }
            case 4:
                return tryResearch(techTree.FeudalAgeResearch, "TownCenter");
//                if (hasEnoughResource(techTree.FeudalAgeResearch)) {
//                    boolean hasFound = false;
//                    executeCost(techTree.FeudalAgeResearch);
//                    //maybe apply method here
//                    hasFound = putIntoQueue("TownCenter", techTree.FeudalAgeResearch);
//                    if (hasFound) {
//                        return 1;
//                    } else {
//                        //System.out.println("No space in queue");
//                        return 1;
//                    }
//                } else {
//                    return 2;
//                }
            case 5:
                return tryResearch(techTree.CastleAgeResearch, "TownCenter");
//                if (hasEnoughResource(techTree.CastleAgeResearch)) {
//                    boolean hasFound = false;
//                    executeCost(techTree.CastleAgeResearch);
//                    //maybe apply method here
//                    hasFound = putIntoQueue("TownCenter", techTree.CastleAgeResearch);
//                    if (hasFound) {
//                        return 1;
//                    } else {
//                        //System.out.println("No space in queue");
//                        return 1;
//                    }
//                } else {
//                    return 2;
//                }
            case 6:
                return tryResearch(techTree.ImperialAgeResearch, "TownCenter");
//                if (hasEnoughResource(techTree.ImperialAgeResearch)) {
//                    boolean hasFound = false;
//                    executeCost(techTree.ImperialAgeResearch);
//                    //maybe apply method here
//                    hasFound = putIntoQueue("TownCenter", techTree.ImperialAgeResearch);
//                    if (hasFound) {
//                        return 1;
//                    } else {
//                        //System.out.println("No space in queue");
//                        return 1;
//                    }
//                } else {
//                    return 2;
//                }
        }
        return 3;
    }

    private int buildCommand(int index) {
        switch (index) {
            case 2:
                if (hasEnoughResource(techTree.Barracks)) {
                    executeCost(techTree.Barracks);
                    buildings.add(techTree.Barracks.getNew());
                    return 1;
                } else {
                    return 2;
                }
            case 3:
                if (hasEnoughResource(techTree.LumberCamp)) {
                    executeCost(techTree.LumberCamp);
                    buildings.add(techTree.LumberCamp.getNew());
                    if (buildings != null && !buildings.isEmpty()) {
                        assignEcoBuilding(buildings.get(buildings.size() - 1));
                    }
                    return 1;
                } else {
                    return 2;
                }
            case 4:
                if (hasEnoughResource(techTree.Mill)) {
                    executeCost(techTree.Mill);
                    buildings.add(techTree.Mill.getNew());
                    if (buildings != null && !buildings.isEmpty()) {
                        assignEcoBuilding(buildings.get(buildings.size() - 1));
                    }
                    return 1;
                } else {
                    return 2;
                }
            case 5:
                if (hasEnoughResource(techTree.MiningCamp)) {
                    executeCost(techTree.MiningCamp);
                    buildings.add(techTree.MiningCamp.getNew());
                    if (buildings != null && !buildings.isEmpty()) {
                        assignEcoBuilding(buildings.get(buildings.size() - 1));
                    }
                    return 1;
                } else {
                    return 2;
                }
            case 6:
                if (hasEnoughResource(techTree.Blacksmith)) {
                    executeCost(techTree.Blacksmith);
                    buildings.add(techTree.Blacksmith.getNew());
                    return 1;
                } else {
                    return 2;
                }
            case 7:
                if (hasEnoughResource(techTree.Market)) {
                    executeCost(techTree.Market);
                    buildings.add(techTree.Market.getNew());
                    return 1;
                } else {
                    return 2;
                }

        }
        return 3;
    }

    private boolean putIntoQueue(String name, baseGameItem aUnit) {
        boolean hasFound = false;
        Building temp = null;
        int maxq = 100;
        for (Building building : buildings) {
            if (building.name.contains(name)) {
                if (building.getInQueueCount() < maxq) {
                    temp = building;
                    maxq = building.getInQueueCount();
                }
            }

        }
        if (temp != null) {
            temp.addQueue(aUnit);
            hasFound = true;
            inQ++;
        }
        return hasFound;
    }

    private void resourceHandler() {//I will turn this into a class later

        /*
         Inputs for decider*
         1-Wood
         2-Food
         3-Gold
         4-Stone
         */
        for (Iterator<Resource> iterator = resourceList.iterator(); iterator.hasNext();) {
            Resource next = iterator.next();
            if (!next.hasDepleted()) {
                switch (next.getSourceType()) { // put wood food gold stone into 4 size of array.
                    case 1:
                        if (next.currentState()) {
                            totalWood += next.clockWork();
                        } else {
                            villagerList.addAll(next.removeAllWorker());
                            next.hasDepleted = true;
                        }
                        break;
                    case 2:
                        if (next.currentState()) {
                            totalFood += next.clockWork();
                        } else {
                            villagerList.addAll(next.removeAllWorker());
                            next.hasDepleted = true;
                        }
                        break;
                    case 3:
                        if (next.currentState()) {
                            totalGold += next.clockWork();
                        } else {
                            villagerList.addAll(next.removeAllWorker());
                            next.hasDepleted = true;
                        }
                        break;
                    case 4:
                        if (next.currentState()) {
                            totalStone += next.clockWork();
                        } else {
                            villagerList.addAll(next.removeAllWorker());
                            next.hasDepleted = true;
                        }
                        break;
                }
            }

        }
    }

    private int produceUnit(int target) {
        /*
        1-militia
         */

 /* return values:
            1-success
            2- need to wait for resource
         */
        switch (target) {
            case 1:
                if (true) {
                    if (hasEnoughResource(techTree.Villager)) {
                        executeCost(techTree.Villager);
                        boolean hasFound = false;
                        hasFound = putIntoQueue("TownCenter", new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10));
                        if (hasFound) {
                            return 1;
                        } //else put into
                        else {
                            //System.out.println("No space in queue");
                            return 1;
                        }
                    } else {
                        return 2;
                    }
                }
            case 2: //militia line
                if (true) { //if building exist and available then we check the rest
                    if (hasEnoughResource(techTree.MilitiaLine)) {
                        boolean hasFound = false;
                        executeCost(techTree.MilitiaLine);
                        hasFound = putIntoQueue("Barracks", techTree.MilitiaLine);
                        if (hasFound) {
                            return 1;
                        } //else put into
                        else {
                            //System.out.println("No space in queue");
                            return 1;
                        }

                    } else {
                        return 2;
                    }
                }

        }
        return 3; //cant find the unit

    }

    public void specialCommand(int command) {
        switch (command) {
            case 1:
                this.enableFarms = true;
                break;
        }
    }

    public int villagerTaskCommand(int task) {
        Building temp = null;
        if (!villagerList.isEmpty()) {
            if (!(enableFarms && task == 2)) {
                tasker.addVilTask(villagerList.pop(), task, false);
                return 1;
            } else {
                //first decide on farm or others
                if (tasker.isFarmOk()) {
                    Resource tempFarm = new Resource(2, "Farm", 0.310f, 1, 175, 10, 1);
                    this.resourceList.add(tempFarm);
                    tasker.addResource(tempFarm);
                    //System.out.println("Farm added!");
                    tasker.addVilTask(villagerList.pop(), task, false);
                    return 1;
                } else {
                    tasker.addVilTask(villagerList.pop(), task, false);
                    return 1;
                }

            }

        } else {
            for (Building aTC : allTCs) {
                if (!aTC.isAvailable()) {
                    if (aTC.getCurrentQIName().contains("Vil")) {

                        return 5; //maybe put something else here for multiq on vils
                    }
                } else {
                    temp = aTC;
                }
            }
            if (temp != null) {
                int a = produceUnit(1);
                if (a == 1) {
                    return 5;
                } else if (a == 2) {
                    return 2;
                }
            }
        }
        return 5;
    }
}
