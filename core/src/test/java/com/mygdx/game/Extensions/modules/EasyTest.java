package com.mygdx.game.Extensions.modules;

import static org.junit.Assert.*;

public class EasyTest {
    @org.junit.Test
    public void run() throws Exception {

        Easy instance = new Easy();



        boolean result = true;

        double old_x =instance.player.sprite().getOldX();
        double old_y =instance.player.sprite().getOldY();

        instance.run();
        if(old_x == instance.player.sprite().getOldX() && old_y == instance.player.sprite().getOldY()){

            result = false;

        }

        assertEquals(true, result);

// TODO review the generated test code and remove the default call to fail.

        fail("The test case is a prototype.");
        fail("The test case is a prototype.");
    }

}