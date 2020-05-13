package NPC;

import Player.*;
import Thread.*;

import java.util.Scanner;

public class N_Shop_Weapon extends N_Shop {

    public N_Shop_Weapon(int npcPlaceY, int npcPlaceX, MainThread mainThread) {
        super(npcPlaceY, npcPlaceX);
        this.mainThread = mainThread;
        this.mainThread.villageMap.mapArray[npcPlaceY][npcPlaceX] = GameMap.WEAPON;
    }

    private MainThread mainThread;

    @Override
    public void interact_N(){

        // 대장간 소리 쓰레드 실행
        String WEAPON_SMITH = "c:/a_project_sound/weapon_smith.wav";
        SoundThread soundThread = new SoundThread(WEAPON_SMITH);
        soundThread.start();

        System.out.println("대장간에 어서오세요~~");
        System.out.println("대장간에서는 무기를 강화해서 공격력을 높이거나");
        System.out.println("스킬 포인트를 사서 전투에서 소모한 포인트를 회복할 수 있습니다\n\n");
        Scanner scanner = new Scanner(System.in);

        loop:
        while (true){
            System.out.println("\n\n1. 무기강화 공격력20상승 - 100원");
            System.out.println("2. 스킬 포인트 5개 구매 - 100원");
            System.out.println("z. 대장간 나가기");
            String select = scanner.next();
            switch (select){
                case "1": // 무기강화
                    if (mainThread.player.playerMoney >= 100){ //돈이 충분할때
                        mainThread.player.playerATK += 20;
                        mainThread.player.playerMoney -= 100;
                        System.out.println("\n\n플레이어의 무기를 강화합니다");
                        System.out.println("공격력+20");
                        System.out.println("플레이어 공격력:"+mainThread.player.playerATK);
                        System.out.println("돈 -100");
                        System.out.println("현재 돈:"+mainThread.player.playerMoney);

                        continue loop;
                    }else { // 돈이 부족할때
                        System.out.println("\n돈이 부족합니다");
                        System.out.println("현재 잔고:"+mainThread.player.playerMoney+"\n\n");
                        continue loop;
                    }
                case "2": // 스킬 포인트 사기
                    if (mainThread.player.playerMoney >= 100){ //돈이 충분할때
                        mainThread.player.playerSkillPoint += 5;
                        mainThread.player.playerMoney -= 100;
                        System.out.println("\n\n스킬 포인트 5개를 구매했습니다");
                        System.out.println("현재 스킬포인트:"+mainThread.player.playerSkillPoint);
                        System.out.println("돈 -100");
                        System.out.println("현재 돈:"+mainThread.player.playerMoney);

                        continue loop;
                    }else { // 돈이 부족할때
                        System.out.println("\n돈이 부족합니다");
                        System.out.println("현재 잔고:"+mainThread.player.playerMoney+"\n\n");
                        continue loop;
                    }
                case "z":
                    System.out.println("\n\n\n\n\n\n\n대장간을 나갑니다");
                    synchronized (soundThread){
                        soundThread.stop();
                    }
                    break loop;
                default:
                    System.out.println("다시 선택해주세요");
            }
        }

    }
}
