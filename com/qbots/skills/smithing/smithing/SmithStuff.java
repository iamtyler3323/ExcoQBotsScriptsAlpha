package com.qbots.skills.smithing.smithing;

/**
 * Created by Tyler on 5/13/14.
 */
public enum SmithStuff {
    DAGGER,SWORD,SCIMITAR,LONGSWORD,TWOHSWORD,AXE,MACE,WARHAMMER,BAXE,no,CHAINBODY,PLEGS,PSKIRT,PBODY,NAILS,MHELM,FHELM,SQSHIELD,KSHIELD,noo,DTIPS,ATIPS,KNIVES,WIRE,BOLTS,LIMBS;

    public int getChild(SmithStuff type) {
        for(int i = 0; i < values().length; i++) {
            if(type.name().equals(values()[i].name())) {
                return i;
            }
        }
        return -1;
    }
}
