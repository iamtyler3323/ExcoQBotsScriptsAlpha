package com.qbots.painter;

/**
 * Created by Tyler on 5/11/14.
 */
public enum Types {
    AGILITY, CONSTRUCTION, COOKING, CRAFTING, FARMING, FIREMAKING, FISHING, FLETCHING, HERBLORE, HUNTER, MAGIC, MINING, PRAYER, RANGED, RUNECRAFTING, SLAYER, SMITHING, THIEVING, WOODCUTTING, MONEYMAKING, MISC;

    public String toString() {
        return this.name().toLowerCase();
    }
}
