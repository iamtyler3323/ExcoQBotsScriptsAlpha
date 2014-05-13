package com.qbots.skills.cooking;

import com.qbots.painter.Painter;
import com.qbots.painter.Types;
import com.qbots.skills.cooking.areas.Catherby;
import com.qbots.task.Task;
import org.excobot.Application;
import org.excobot.bot.game.script.GameScript;
import org.excobot.bot.game.script.ScriptManifest;
import org.excobot.script.event.listeners.PaintListener;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tyler on 5/11/14.
 */
@ScriptManifest(authors = "QBots", name = "ExcoCooker", description = "Cooks anything, anywhere", version = 0.01)
public class Main extends GameScript implements PaintListener{

    public ArrayList<Task> task_list;
    public Utils utils;
    Painter painter;
    Area area;
    CFrame gui;

    @Override
    public boolean start() {
        utils = new Utils(this,"Raw shrimps",1000);
        area = new Catherby(this.context());
        task_list = new Tasks(this,utils, area).task_list;
        try {
            painter = new Painter("Cooking", Types.COOKING,this.context(),0.1);
        } catch (Exception e) {
            Application.println(e.toString(),Color.RED);
        }
        gui = new CFrame(this);
        gui.setVisible(true);
        return true;
    }

    @Override
    public int execute() {
        if(gui.isVisible()) {
            return 100;
        }
        for(Task task : task_list) {
            if(task.bool_check()) {
                painter.update(task.get_name());
                return task.execute();
            }
        }
        painter.update("Idle");
        return 100;
    }

    @Override
    public void render(Graphics graphics) {
        if(painter != null)
            painter.render(graphics);
    }
}
