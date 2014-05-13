package com.qbots.skills.smithing;

import com.qbots.skills.smithing.smithing.SmithStuff;
import org.excobot.script.ScriptContext;
import org.excobot.script.wrappers.Component;
import org.excobot.script.wrappers.Widget;

/**
 * Created by Tyler on 5/13/14.
 */
public class Utils {
    ScriptContext s;
    SmithStuff type = SmithStuff.BAXE;
    public Utils(ScriptContext s) {
        this.s = s;
    }



    public Component getComp() {
        Widget parent = s.widgets.get(312);
        if(parent != null) {
            Component child = parent.getChild(type.getChild(this.type)+2);
            return child;
        }
        return null;
    }

    public int getAmountNeeded() {
        return Integer.parseInt(getComp().getChild(1).getText().split(" ")[0]);
    }
}
