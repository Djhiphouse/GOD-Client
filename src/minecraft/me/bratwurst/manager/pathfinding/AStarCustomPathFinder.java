package me.bratwurst.manager.pathfinding;

import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class AStarCustomPathFinder {
    private static final Minecraft MC = Minecraft.getMinecraft();
    private static final CustomVec3[] flatCardinalDirections = new CustomVec3[]{new CustomVec3(1.0D, 0.0D, 0.0D), new CustomVec3(-1.0D, 0.0D, 0.0D), new CustomVec3(0.0D, 0.0D, 1.0D), new CustomVec3(0.0D, 0.0D, -1.0D)};
    private final CustomVec3 startCustomVec3;
    private final CustomVec3 endCustomVec3;
    private final ArrayList hubs = new ArrayList();
    private final ArrayList hubsToWork = new ArrayList();
    private ArrayList path = new ArrayList();

    public AStarCustomPathFinder(CustomVec3 startCustomVec3, CustomVec3 endCustomVec3) {
        this.startCustomVec3 = startCustomVec3.addVector(0.0D, 0.0D, 0.0D).floor();
        this.endCustomVec3 = endCustomVec3.addVector(0.0D, 0.0D, 0.0D).floor();
    }

    public static boolean checkPositionValidity(CustomVec3 vec3, boolean checkGround) {
        return checkPositionValidity((int)vec3.getX(), (int)vec3.getY(), (int)vec3.getZ(), checkGround);
    }

    public static boolean checkPositionValidity(int x, int y, int z, boolean checkGround) {
        BlockPos blockPos1 = new BlockPos(x, y, z);
        BlockPos blockPos2 = new BlockPos(x, y + 1, z);
        BlockPos blockPos3 = new BlockPos(x, y - 1, z);
        return !isBlockSolid(blockPos1) && !isBlockSolid(blockPos2) && (isBlockSolid(blockPos3) || !checkGround) && isSafeToWalkOn(blockPos3);
    }

    private static boolean isBlockSolid(BlockPos pos) {
        Block block = MC.theWorld.getBlockState(pos).getBlock();
        return block.isSolidFullCube() || block instanceof BlockSlab || block instanceof BlockStairs || block instanceof BlockCactus || block instanceof BlockChest || block instanceof BlockEnderChest || block instanceof BlockSkull || block instanceof BlockPane || block instanceof BlockFence || block instanceof BlockWall || block instanceof BlockGlass || block instanceof BlockPistonBase || block instanceof BlockPistonExtension || block instanceof BlockPistonMoving || block instanceof BlockStainedGlass || block instanceof BlockTrapDoor;
    }

    private static boolean isSafeToWalkOn(BlockPos pos) {
        Block block = MC.theWorld.getBlockState(pos).getBlock();
        return !(block instanceof BlockFence) && !(block instanceof BlockWall);
    }

    public ArrayList getPath() {
        return this.path;
    }

    public void compute() {
        this.compute(1000, 4);
    }

    public void compute(int loops, int depth) {
        this.path.clear();
        this.hubsToWork.clear();
        ArrayList initPath = new ArrayList();
        CustomVec3 startCustomVec3 = this.startCustomVec3;
        initPath.add(startCustomVec3);
        CustomVec3[] flatCardinalDirections = AStarCustomPathFinder.flatCardinalDirections;
        this.hubsToWork.add(new AStarCustomPathFinder.Hub(startCustomVec3, (AStarCustomPathFinder.Hub) null, initPath, startCustomVec3.squareDistanceTo(this.endCustomVec3), 0.0D, 0.0D));

        label55:
        for (int i = 0; i < loops; ++i) {
            ArrayList hubsToWork = this.hubsToWork;
            int hubsToWorkSize = hubsToWork.size();
            hubsToWork.sort(new AStarCustomPathFinder.CompareHub());
            int j = 0;
            if (hubsToWorkSize == 0) {
                break;
            }

            for (int i1 = 0; i1 < hubsToWorkSize; ++i1) {
                AStarCustomPathFinder.Hub hub = (AStarCustomPathFinder.Hub) hubsToWork.get(i1);
                ++j;
                if (j > depth) {
                    break;
                }

                hubsToWork.remove(hub);
                this.hubs.add(hub);
                CustomVec3 hLoc = hub.getLoc();
                int i2 = 0;

                for (int flatCardinalDirectionsLength = flatCardinalDirections.length; i2 < flatCardinalDirectionsLength; ++i2) {
                    CustomVec3 loc = hLoc.add(flatCardinalDirections[i2]).floor();
                    if (checkPositionValidity(loc, false) && this.addHub(hub, loc, 0.0D)) {
                        break label55;
                    }
                }

                CustomVec3 loc1 = hLoc.addVector(0.0D, 1.0D, 0.0D).floor();
                if (checkPositionValidity(loc1, false) && this.addHub(hub, loc1, 0.0D)) {
                    break label55;
                }

                CustomVec3 loc2 = hLoc.addVector(0.0D, -1.0D, 0.0D).floor();
                if (checkPositionValidity(loc2, false) && this.addHub(hub, loc2, 0.0D)) {
                    break label55;
                }
            }
        }

        this.hubs.sort(new AStarCustomPathFinder.CompareHub());
        this.path = ((AStarCustomPathFinder.Hub) this.hubs.get(0)).getPath();
    }

    public AStarCustomPathFinder.Hub isHubExisting(CustomVec3 loc) {
        List hubs = this.hubs;
        int i = 0;

        for (i = hubs.size(); i < i; ++i) {
            AStarCustomPathFinder.Hub hub = (AStarCustomPathFinder.Hub) hubs.get(i);
            CustomVec3 hubLoc = hub.getLoc();
            if (hubLoc.getX() == loc.getX() && hubLoc.getY() == loc.getY() && hubLoc.getZ() == loc.getZ()) {
                return hub;
            }
        }

        List hubsToWork = this.hubsToWork;
        i = 0;

        for (int hubsToWorkSize = hubsToWork.size(); i < hubsToWorkSize; ++i) {
            AStarCustomPathFinder.Hub hub = (AStarCustomPathFinder.Hub) hubsToWork.get(i);
            CustomVec3 hubLoc = hub.getLoc();
            if (hubLoc.getX() == loc.getX() && hubLoc.getY() == loc.getY() && hubLoc.getZ() == loc.getZ()) {
                return hub;
            }
        }

        return null;
    }

    public boolean addHub(AStarCustomPathFinder.Hub parent, CustomVec3 loc, double cost) {
        AStarCustomPathFinder.Hub existingHub = this.isHubExisting(loc);
        double totalCost = cost;
        if (parent != null) {
            totalCost = cost + parent.getTotalCost();
        }

        CustomVec3 endCustomVec3 = this.endCustomVec3;
        ArrayList parentPath = parent.getPath();
        if (existingHub == null) {
            if (loc.getX() == endCustomVec3.getX() && loc.getY() == endCustomVec3.getY() && loc.getZ() == endCustomVec3.getZ()) {
                this.path.clear();
                this.path = parentPath;
                this.path.add(loc);
                return true;
            }

            parentPath.add(loc);
            this.hubsToWork.add(new AStarCustomPathFinder.Hub(loc, parent, parentPath, loc.squareDistanceTo(endCustomVec3), cost, totalCost));
        } else if (existingHub.getCost() > cost) {
            parentPath.add(loc);
            existingHub.setLoc(loc);
            existingHub.setParent(parent);
            existingHub.setPath(parentPath);
            existingHub.setSquareDistanceToFromTarget(loc.squareDistanceTo(endCustomVec3));
            existingHub.setCost(cost);
            existingHub.setTotalCost(totalCost);
        }

        return false;
    }

    public class CompareHub implements Comparator<Hub> {
        public int compare(AStarCustomPathFinder.Hub o1, AStarCustomPathFinder.Hub o2) {
            return (int) (o1.getSquareDistanceToFromTarget() + o1.getTotalCost() - (o2.getSquareDistanceToFromTarget() + o2.getTotalCost()));
        }
    }

    private static class Hub {
        private CustomVec3 loc;
        private AStarCustomPathFinder.Hub parent;
        private ArrayList path;
        private double squareDistanceToFromTarget;
        private double cost;
        private double totalCost;

        public Hub(CustomVec3 loc, AStarCustomPathFinder.Hub parent, ArrayList path, double squareDistanceToFromTarget, double cost, double totalCost) {
            this.loc = loc;
            this.parent = parent;
            this.path = path;
            this.squareDistanceToFromTarget = squareDistanceToFromTarget;
            this.cost = cost;
            this.totalCost = totalCost;
        }

        public CustomVec3 getLoc() {
            return this.loc;
        }

        public void setLoc(CustomVec3 loc) {
            this.loc = loc;
        }

        public AStarCustomPathFinder.Hub getParent() {
            return this.parent;
        }

        public void setParent(AStarCustomPathFinder.Hub parent) {
            this.parent = parent;
        }

        public ArrayList getPath() {
            return this.path;
        }

        public void setPath(ArrayList path) {
            this.path = path;
        }

        public double getSquareDistanceToFromTarget() {
            return this.squareDistanceToFromTarget;
        }

        public void setSquareDistanceToFromTarget(double squareDistanceToFromTarget) {
            this.squareDistanceToFromTarget = squareDistanceToFromTarget;
        }

        public double getCost() {
            return this.cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public double getTotalCost() {
            return this.totalCost;
        }

        public void setTotalCost(double totalCost) {
            this.totalCost = totalCost;
        }
    }
}
