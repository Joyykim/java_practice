package NPC;

import Player.GameMap;
import Player.Player;

public class N_PersonType2 extends N_Person {
    public N_PersonType2(int npcPlaceY, int npcPlaceX, GameMap map) {
        super(npcPlaceY, npcPlaceX, map);
        map.mapArray[npcPlaceY][npcPlaceX] = "NPC_Person2";
        super.npcQuest_Yet.add("퀘스트2-1");
        super.npcQuest_Yet.add("퀘스트2-2");
        super.npcQuest_Yet.add("퀘스트2-3");

    }

    @Override
    public void interact_N(Player player, NPC npc){


        // 대화시 자동으로 퀘스트 달성 메소드
        if (!player.playerQuest_done.isEmpty()){
            for (String quest : player.playerQuest_done){
                switch (quest){
                    case "퀘스트2-1":
                        System.out.println("\n\n퀘스트2-1 보상을 획득합니다");
                        //리스트 정리
                        player.playerQuest_done.remove("퀘스트2-1");
                        super.npcQuest_Yet.remove("퀘스트2-1");
                        super.npcQuest_Done.add("퀘스트2-1");

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

                    case "퀘스트2-2":
                        System.out.println("\n\n퀘스트2-2 보상을 획득합니다");
                        //리스트 정리
                        player.playerQuest_done.remove("퀘스트2-2");
                        super.npcQuest_Yet.remove("퀘스트2-2");
                        super.npcQuest_Done.add("퀘스트2-2");

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

                    case "퀘스트2-3":
                        System.out.println("\n\n퀘스트2-3 보상을 획득합니다");
                        //리스트 정리
                        player.playerQuest_done.remove("퀘스트2-3");
                        super.npcQuest_Yet.remove("퀘스트2-3");
                        super.npcQuest_Done.add("퀘스트2-3");

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
                    if (npcQuest_Yet.contains("퀘스트2-1")){ //리스트에 퀘스트 있음
                        System.out.println("퀘스트2-1를 수락했습니다");
                        player.playerQuest_yet.add("퀘스트2-1"); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove("퀘스트2-1"); //NPC 미수락 리스트에서 삭제
                        break loop;
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "2":
                    if (npcQuest_Yet.contains("퀘스트2-2")){ //리스트에 퀘스트 있음
                        System.out.println("퀘스트2-2를 수락했습니다");
                        player.playerQuest_yet.add("퀘스트2-2"); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove("퀘스트2-2"); //NPC 미수락 리스트에서 삭제
                        break loop;
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "3":
                    if (npcQuest_Yet.contains("퀘스트2-3")){ //리스트에 퀘스트 있음
                        System.out.println("퀘스트2-3를 수락했습니다");
                        player.playerQuest_yet.add("퀘스트2-3"); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove("퀘스트2-3"); //NPC 미수락 리스트에서 삭제
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
