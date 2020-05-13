package NPC;

import Player.*;
import Thread.*;

import java.util.Scanner;

public class N_Shop_Potion extends N_Shop {

    public N_Shop_Potion(int npcPlaceY, int npcPlaceX, MainThread mainThread) {
        super(npcPlaceY, npcPlaceX);
        this.mainThread = mainThread;
        this.mainThread.villageMap.mapArray[npcPlaceY][npcPlaceX] = GameMap.POTION;
    }

    private MainThread mainThread;

    @Override
    public void interact_N(){

        System.out.println("상점에 어서오세요~~");
        System.out.println("상점에서는 회복 물약을 사서 전투에서 소모한 물약을 회복할 수 있습니다");

        Scanner scanner = new Scanner(System.in);
        loop:
        while (true){
            //입력값으로 물품 선택
            System.out.println("\n\n1. 체력50 회복 물약 - 50원");
            System.out.println("2. 체력200 회복 물약 - 200원");
            System.out.println("z. 상점 나가기");
            String scString = scanner.next();
            switch (scString){
                case "1": // 포션1 50원
                    if (mainThread.player.playerMoney >= 50){ //돈이 충분할때
                        mainThread.player.playerMoney -= 50; // 돈 차감
                        mainThread.player.playerPotion1 += 1; // 포션 증가
                        System.out.println("\n\n50회복 물약을 구매하였습니다");
                        System.out.println("50회복물약 개수:"+mainThread.player.playerPotion1);
                        System.out.println("돈 -50");
                        System.out.println("현재 돈:"+mainThread.player.playerMoney);
                        continue loop;
                    }else { //돈이 부족할때
                        System.out.println("돈이 부족합니다");
                        System.out.println("현재 잔고:"+mainThread.player.playerMoney+"\n\n");
                        continue loop;
                    }
                case "2":
                    if (mainThread.player.playerMoney >= 200){ //돈이 충분할때
                        mainThread.player.playerMoney -= 200; // 돈 차감
                        mainThread.player.playerPotion2 += 1; // 포션 증가
                        System.out.println("\n\n200회복 물약을 구매하였습니다");
                        System.out.println("200회복물약 개수:"+mainThread.player.playerPotion2);
                        System.out.println("돈 -200");
                        System.out.println("현재 돈:"+mainThread.player.playerMoney);
                        continue loop;
                    }else { //돈이 부족할때
                        System.out.println("\n돈이 부족합니다");
                        System.out.println("현재 잔고:"+mainThread.player.playerMoney+"\n\n");
                        continue loop;
                    }
                case "z":
                    System.out.println("\n\n\n\n\n\n\n포션상점을 나갑니다");
                    break loop;
                default:
                    System.out.println("다시 입력해주세요");
            }
        }
    }
}
