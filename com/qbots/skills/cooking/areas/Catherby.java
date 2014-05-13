package com.qbots.skills.cooking.areas;

import com.qbots.skills.cooking.Area;
import org.excobot.script.ScriptContext;
import org.excobot.script.wrappers.Path;
import org.excobot.script.wrappers.Tile;

/**
 * Created by Tyler on 5/13/14.
 */
public class Catherby extends Area {

    ScriptContext s;
    private Tile walk_path,reversed;

    public Catherby(ScriptContext s) {
        this.s = s;
        walk_path = new Tile(2816,3442,0);
        reversed = new Tile(2810,3440);
    }



    @Override
    public boolean hasPath() {
        return true;
    }

    @Override
    public boolean walkPath() {
        return s.movement.walkTileOnMap(walk_path);
    }

    @Override
    public boolean walkReversed() {
        return s.movement.walkTileOnMap(reversed);
    }
}
