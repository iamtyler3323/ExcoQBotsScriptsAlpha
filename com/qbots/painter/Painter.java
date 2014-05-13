package com.qbots.painter;

import org.excobot.Application;
import org.excobot.script.ScriptContext;
import org.excobot.script.event.listeners.PaintListener;
import org.excobot.script.methods.Skills;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Tyler on 5/11/14.
 */
public class Painter {
    private String status, type, name;
    ScriptContext s;
    private int start_xp;
    private int start_lvl;
    private long startTime;
    private double version;
    private Skills.Skill skill;

    public Painter(String SName, Types type, ScriptContext s, double version) {
        this.name = SName;
        this.version = version;
        this.startTime = System.currentTimeMillis();
        this.s = s;
        this.type = type.toString();
        this.skill = getSkill(SName);
        try {
            this.start_lvl = s.skills.getRealLevel(Skills.Skill.COOKING);
            this.start_xp = s.skills.getExperience(Skills.Skill.COOKING);
        } catch (Exception e) {
            Application.println("error!",Color.RED);
        }

    }

    private Skills.Skill getSkill(String name) {
        Skills.Skill[] skills = Skills.Skill.values();
        for(Skills.Skill s : skills) {
            if(s.name().equals(name)) return s;
        }
        return Skills.Skill.COOKING;
    }

    public void update(String status) {
        this.status = status;
    }

    private final Color color1 = new Color(0, 0, 0, 178);
    private final Color color2 = new Color(0, 0, 0);
    private final Color color3 = new Color(255, 255, 255);
    private final Color color4 = new Color(255, 255, 255, 178);
    private final Color color5 = new Color(0, 0, 0, 204);
    private final Color color6 = new Color(255, 0, 0, 229);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 0, 9);
    private final Font font2 = new Font("Batang", 0, 17);
    private final Font font3 = new Font("Batang", 0, 12);
    private final Font font4 = new Font("Batang", 0, 10);

    public void render(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRect(6, 345, 489, 112);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(6, 345, 489, 112);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString("", 678, 206);
        g.setFont(font2);
        g.setColor(color3);
        g.drawString("Excobot "+name, 183, 363);
        g.setFont(font3);
        g.drawString("Exp Gained: "+formatNumber(s.skills.getExperience(skill)-this.start_xp), 14, 385);
        g.drawString("Exp To Level: "+formatNumber(experienceForLevel(s.skills.getRealLevel(skill)+1)-s.skills.getExperience(skill)), 14, 418);
        g.drawString("Exp Per Hour: "+perHour(s.skills.getExperience(skill)-this.start_xp), 14, 401);
        g.drawString("Levels Gained: "+(s.skills.getRealLevel(skill)-this.start_lvl), 294, 385);
        g.drawString("Levels Per Hour: "+perHour(s.skills.getRealLevel(skill)-this.start_lvl), 293, 400);
        g.drawString("Version: "+this.version, 105, 453);
        g.setColor(color4);
        g.fillRect(444, 437, 49, 19);
        g.setColor(color5);
        g.drawRect(444, 437, 49, 19);
        g.setFont(font4);
        g.setColor(color6);
        g.drawString("Hide", 458, 452);
        g.setFont(font3);
        g.setColor(color3);
        g.drawString("Task: "+this.status, 259, 455);
    }

    public String perHour(int gained) {
        return formatNumber((int) ((gained) * 3600000D / (System.currentTimeMillis() - startTime)));
    }

    public String formatNumber(int start) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = start;
        if(i >= 1000000) {
            return nf.format((i / 1000000)) + "m";
        }
        if(i >=  1000) {
            return nf.format((i / 1000)) + "k";
        }
        return ""+start;
    }

    public int experienceForLevel(int level)
    {
        double total = 0.0D;
        for (int i = 1; i < level; i++) {
            total += Math.floor(i + 300.0D * Math.pow(2.0D, i / 7.0D));
        }

        return (int)Math.floor(total / 4.0D);
    }
}
