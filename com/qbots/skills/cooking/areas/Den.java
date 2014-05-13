package com.qbots.skills.cooking.areas;

import com.qbots.skills.cooking.Area;

/**
 * Created by Tyler on 5/13/14.
 */
public class Den extends Area {
    @Override
    public boolean hasPath() {
        return false;
    }

    @Override
    public boolean walkPath() {
        return false;
    }

    @Override
    public boolean walkReversed() {
        return false;
    }
}
