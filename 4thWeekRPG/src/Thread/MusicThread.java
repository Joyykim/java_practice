package Thread;

public class MusicThread implements Runnable{

    @Override
    public void run() {
        MakeMusic makeSound = new MakeMusic();
        while (true){
            makeSound.playSound("c:/a_project_sound/forest_night.wav");
        }
    }
}