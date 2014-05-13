package com.qbots.skills.cooking;

import com.qbots.task.Task;
import org.excobot.Application;
import org.excobot.bot.game.script.GameScript;
import org.excobot.script.ScriptContext;
import org.excobot.script.methods.Bank;
import org.excobot.script.methods.Inventory;
import org.excobot.script.wrappers.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tyler on 5/11/14.
 */
public class Tasks {
    private GameScript s;
    private ScriptContext c;
    public ArrayList<Task> task_list;
    private Utils utils;
    private Area area;

    public Tasks(GameScript s, Utils utils, Area area) {
        this.s = s;
        c = s.context();
        this.utils = utils;
        this.area = area;
        task_list = new ArrayList<Task>();
        task_list.add(interact_fire());
        task_list.add(interface_click());
        task_list.add(walk_path_bank());
        task_list.add(bank_open());
        task_list.add(deposit_all());
        task_list.add(withdraw_raws());
        task_list.add(close_bank());
        task_list.add(to_cook());
    }

    public Task interact_fire() {
        return new Task() {
            @Override
            public boolean bool_check() {
                return c.players.getLocal().isIdle() && c.inventory.contains(utils.food_name) && !interface_click().bool_check() && !c.bank.isOpen() && c.objects.getNearest("Range") != null && c.objects.getNearest("Range").isOnGameScreen();
            }

            @Override
            public int execute() {
                GameObject Fire = c.objects.getNearest("Range");
                Inventory inv = c.inventory;

                if(inv.getSelectedItem() == null)
                    inv.getItem(utils.food_name).interact("Use");
                else {
                    Fire.interact("Use");
                }

                return 1000;
            }

            @Override
            public String get_name() {
                return "Interacting";
            }
        };
    }

    private Task interface_click() {
        return new Task() {
            @Override
            public boolean bool_check() {
                return c.widgets.get(307) != null && c.widgets.get(307).getChild(6) != null;
            }

            @Override
            public int execute() {
                c.widgets.get(307).getChild(6).interact("Cook All");
                return 1000;
            }

            @Override
            public String get_name() {
                return "Widget...";
            }
        };
    }

    private Task walk_path_bank() {
        return new Task() {
            @Override
            public boolean bool_check() {
                return area.hasPath() && !c.inventory.contains(utils.food_name) && (c.objects.getNearest("Bank booth") != null && c.objects.getNearest("Bank booth").isOnGameScreen()) == false;
            }

            @Override
            public int execute() {
                area.walkReversed();
                return 1000;
            }

            @Override
            public String get_name() {
                return "Walking to bank";
            }
        };
    }

    private Task bank_open() {
        return new Task() {
            @Override
            public boolean bool_check() {
                return ((c.objects.getNearest("Bank booth") != null && c.objects.getNearest("Bank booth").isOnGameScreen()) || (c.npcs.getNearest("Emerald Benedict") != null && c.npcs.getNearest("Emerald Benedict").isOnGameScreen())) && !c.inventory.contains(utils.food_name) && !c.bank.isOpen() && !c.players.getLocal().isMoving();
            }

            @Override
            public int execute() {
                if(c.objects.getNearest("Bank booth") != null)
                    c.bank.open();
                else
                    c.npcs.getNearest("Emerald Benedict").interact("Bank");
                return 1000;
            }

            @Override
            public String get_name() {
                return "Opening bank";
            }
        };
    }

    private Task deposit_all() {
        return new Task() {
            @Override
            public boolean bool_check() {
                return c.bank.isOpen() && !c.inventory.isEmpty() && !c.inventory.contains(utils.food_name);
            }

            @Override
            public int execute() {
                c.bank.depositAll();
                return 1000;
            }

            @Override
            public String get_name() {
                return "Depositing";
            }
        };
    }

    private Task withdraw_raws() {
        return new Task() {
            @Override
            public boolean bool_check() {
                return c.bank.isOpen() && c.inventory.isEmpty() && !c.inventory.contains(utils.food_name);
            }

            @Override
            public int execute() {
                c.bank.withdraw(utils.food_name, Bank.Amount.ALL);
                return 1000;
            }

            @Override
            public String get_name() {
                return "Withdrawing";
            }
        };
    }

    private Task close_bank() {
        return new Task() {
            @Override
            public boolean bool_check() {
                return c.bank.isOpen() && !c.inventory.isEmpty() && c.inventory.contains(utils.food_name);
            }

            @Override
            public int execute() {
                c.bank.close();
                return 1000;
            }

            @Override
            public String get_name() {
                return "Closing";
            }
        };
    }

    private Task to_cook() {
        return new Task() {
            @Override
            public boolean bool_check() {
                return area.hasPath() && !c.players.getLocal().isMoving() && c.players.getLocal().isIdle() && c.widgets.get(159) == null;
            }

            @Override
            public int execute() {
                area.walkPath();
                return 1000;
            }

            @Override
            public String get_name() {
                return "Walking back";
            }
        };
    }

}
