package Thread;

public class MusicThread extends Thread{
    MusicThread(String file){ this.file = file; }
    private String file;

    @Override
    public void run() {
        MakeMusic makeSound = new MakeMusic();
        while (true){
            makeSound.playSound(file);
        }
    }
}
