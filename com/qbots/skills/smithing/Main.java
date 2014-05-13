package com.qbots.skills.smithing;

import org.excobot.Application;
import org.excobot.bot.game.script.GameScript;
import org.excobot.bot.game.script.ScriptManifest;
import org.excobot.script.ScriptContext;
import org.excobot.script.methods.Bank;
import org.excobot.script.methods.Inventory;
import org.excobot.script.util.Time;

import java.awt.*;

/**
 * Created by Tyler on 5/11/14.
 */
@ScriptManifest(authors = "QBots", name = "ExcoSmither", description = "Smiths anything in Lumbridge", version = 0.01)
public class Main extends GameScript {
    private Inventory inv;
    private ScriptContext c;
    Utils utils;
    @Override
    public boolean start() {
        inv = context().inventory;
        c = context();
        utils = new Utils(c);
        return true;
    }

    @Override
    public int execute() {
        if(utils.getComp() != null) {
            Application.println("Needed: "+utils.getAmountNeeded(), Color.RED);
        }
        return 1000;
    }
}
