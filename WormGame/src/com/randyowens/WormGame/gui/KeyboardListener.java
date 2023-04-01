
package com.randyowens.WormGame.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.randyowens.WormGame.Direction;
import com.randyowens.WormGame.domain.Worm;

public class KeyboardListener implements KeyListener {

    // reference to Worm
    private Worm worm;

    // default constructor
    public KeyboardListener(Worm worm) {
        this.worm = worm;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // switch statement to determine which arrow key was pressed and assign it to worm Direction
        int keyCode = e.getKeyCode();

        switch(keyCode) {
            case KeyEvent.VK_UP:
                worm.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                worm.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                worm.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                worm.setDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
