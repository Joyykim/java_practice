package Thread;

public class SoundThread extends Thread{
    public SoundThread(String file){
        this.file = file;
    }
    private String file;

    @Override
    public void run() {
        MakeMusic makeSound = new MakeMusic();
        makeSound.playSound(file);
    }
}
