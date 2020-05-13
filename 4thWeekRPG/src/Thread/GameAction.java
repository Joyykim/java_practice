package Thread;

import Player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameAction implements KeyListener {
    //이동
    public boolean KeyUp;
    public boolean KeyDown;
    public boolean KeyLeft;
    public boolean KeyRight;
    //스킬발동 - 꾹 누르고 타이밍을 맞춰서 때면 스킬강화
    public boolean KeySpace;

    public void keyMove(Player player, GameAction gameAction){

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                KeyUp = true;
                break;
            case KeyEvent.VK_DOWN :
                KeyDown = true;
                break;
            case KeyEvent.VK_LEFT :
                KeyLeft = true;
                break;
            case KeyEvent.VK_RIGHT :
                KeyRight = true;
                break;
            case KeyEvent.VK_SPACE :
                KeySpace = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                KeyUp = true;
                break;
            case KeyEvent.VK_DOWN :
                KeyDown = true;
                break;
            case KeyEvent.VK_LEFT :
                KeyLeft = true;
                break;
            case KeyEvent.VK_RIGHT :
                KeyRight = true;
                break;
            case KeyEvent.VK_SPACE :
                KeySpace = true;
                break;
        }
    }
}
