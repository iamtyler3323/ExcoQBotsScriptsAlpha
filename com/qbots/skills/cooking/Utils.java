package com.qbots.skills.cooking;

import org.excobot.bot.game.script.GameScript;
import org.excobot.script.methods.Inventory;

/**
 * Created by Tyler on 5/11/14.
 */
public class Utils {
    public String food_name;
    public int wait_time;
    private GameScript s;
    public String fire_name = "Fire";

    public Utils(GameScript script, String FName, int WTime) {
        this.food_name = FName;
        this.wait_time = WTime;
        this.s = script;
    }

    public boolean check_inv() {
        Inventory inv = s.context().inventory;
        return inv.contains(this.food_name);
    }

}
