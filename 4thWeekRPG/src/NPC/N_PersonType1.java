package NPC;

import Player.GameMap;
import Player.Player;

public class N_PersonType1 extends N_Person {

    public N_PersonType1(int npcPlaceY, int npcPlaceX, GameMap map) {
        super(npcPlaceY, npcPlaceX, map);
        map.mapArray[npcPlaceY][npcPlaceX] = "NPC_Person1";
        super.npcQuest_Yet.add("퀘스트1-1");
        super.npcQuest_Yet.add("퀘스트1-2");
        super.npcQuest_Yet.add("퀘스트1-3");
    }

    @Override
    public void interact_N(Player player, NPC npc){

        // 대화시 자동으로 퀘스트 달성 메소드
        if (!player.playerQuest_done.isEmpty()){
            for (String quest : player.playerQuest_done){
                switch (quest){
                    case "퀘스트1-1":
                        System.out.println("\n\n퀘스트1-1 보상을 획득합니다");
                        //리스트 정리
                        player.playerQuest_done.remove("퀘스트1-1");
                        super.npcQuest_Yet.remove("퀘스트1-1");
                        super.npcQuest_Done.add("퀘스트1-1");

                        //보상 획득
                        player.playerATK += 50;
                        player.playerMaxHP += 100;
                        player.playerHP = player.playerMaxHP;
                        player.playerMoney += 300;

                        System.out.println("\n공격력 50증가");
                        System.out.println("최대HP 100증가");
                        System.out.println("잔고 300증가\n");

                        System.out.println("플레이어 공격력:"+player.playerATK);
                        System.out.println("플레이어 최대HP:"+player.playerMaxHP);
                        System.out.println("플레이어 잔고:"+player.playerMoney+"\n");
                        break;

                    case "퀘스트1-2":
                        System.out.println("\n\n퀘스트1-2 보상을 획득합니다");
                        //리스트 정리
                        player.playerQuest_done.remove("퀘스트1-2");
                        super.npcQuest_Yet.remove("퀘스트1-2");
                        super.npcQuest_Done.add("퀘스트1-2");

                        //보상 획득
                        player.playerATK += 50;
                        player.playerMaxHP += 100;
                        player.playerHP = player.playerMaxHP;
                        player.playerMoney += 300;

                        System.out.println("\n공격력 50증가");
                        System.out.println("최대HP 100증가");
                        System.out.println("잔고 300증가\n");

                        System.out.println("플레이어 공격력:"+player.playerATK);
                        System.out.println("플레이어 최대HP:"+player.playerMaxHP);
                        System.out.println("플레이어 잔고:"+player.playerMoney+"\n");
                        break;

                    case "퀘스트1-3":
                        System.out.println("\n\n퀘스트1-3 보상을 획득합니다");
                        //리스트 정리
                        player.playerQuest_done.remove("퀘스트1-3");
                        super.npcQuest_Yet.remove("퀘스트1-3");
                        super.npcQuest_Done.add("퀘스트1-3");

                        //보상 획득
                        player.playerATK += 50;
                        player.playerMaxHP += 100;
                        player.playerHP = player.playerMaxHP;
                        player.playerMoney += 300;

                        System.out.println("\n공격력 50증가");
                        System.out.println("최대HP 100증가");
                        System.out.println("잔고 300증가\n");

                        System.out.println("플레이어 공격력:"+player.playerATK);
                        System.out.println("플레이어 최대HP:"+player.playerMaxHP);
                        System.out.println("플레이어 잔고:"+player.playerMoney+"\n");
                        break;

                    default:
                }
            }
        }


        System.out.println("\n\n퀘스트를 수락한 상태에서 몬스터를 사냥하면 확률적으로 퀘스트 아이템을 획득할 수 있습니다");
        System.out.println("몬스터 당 하나의 퀘스트 아이템을 드랍합니다");
        System.out.println("퀘스트를 완료하고 NPC와 대화를 하면 자동으로 완료가 되고 보상을 획득합니다");
        System.out.println("보상은 공격력, 최대HP 증가 그리고 보상금입니다");
        loop:
        while (true){
            // 미수락 퀘스트 출력, 안내
            System.out.println("\n수락할 퀘스트를 선택하세요\n");
            System.out.println("z. 대화 끝내기");
            for (String quest: npcQuest_Yet){
                System.out.println(quest);
            }
            System.out.println("\nq를 입력하면 이미 완료한 퀘스트 목록을 볼수 있습니다");
            // 숫자 입력으로 퀘스트 수락
            String questSelect = scanner.next();
            switch (questSelect){
                case "1":
                    if (npcQuest_Yet.contains("퀘스트1-1")){ //리스트에 퀘스트 있음
                        System.out.println("퀘스트1-1를 수락했습니다");
                        player.playerQuest_yet.add("퀘스트1-1"); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove("퀘스트1-1"); //NPC 미수락 리스트에서 삭제
                        break loop;
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "2":
                    if (npcQuest_Yet.contains("퀘스트1-2")){ //리스트에 퀘스트 있음
                        System.out.println("퀘스트1-2를 수락했습니다");
                        player.playerQuest_yet.add("퀘스트1-2"); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove("퀘스트1-2"); //NPC 미수락 리스트에서 삭제
                        break loop;
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "3":
                    if (npcQuest_Yet.contains("퀘스트1-3")){ //리스트에 퀘스트 있음
                        System.out.println("퀘스트1-3를 수락했습니다");
                        player.playerQuest_yet.add("퀘스트1-3"); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove("퀘스트1-3"); //NPC 미수락 리스트에서 삭제
                        break loop;
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "q":
                case "Q":
                    // NCP 완료한 퀘스트 목록 출력
                    for (String questDone:npcQuest_Done){
                        System.out.println(questDone);
                    }
                    continue loop;
                case "z":
                case "Z":
                    System.out.println("\n\n\n\n\n\n\n대화를 마칩니다");
                    break loop;
                default:
                    System.out.println("잘못 선택하셨습니다. ");
            }

        }


    }
}
