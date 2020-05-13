package Player;

import Monster.Monster;
import NPC.NPC;

public class GameMap {
    // 맵은 2차원 배열
    // 맵 출력문 필요
    // 지형지물, NPC, 몬스터, 플레이어
    // 일단 하나의 맵만으로 다른 클래스 구현, 후에 맵 확장 예정
    //여러 채널의 2차배열을 만든후 하나씩 확인???? 너무 복잡해 그럴 필요가 있나? XXX 폐기
    public String[][] mapArray;

    public GameMap(int y, int x) {
        mapArray = new String[y][x];
    }

    public String mapName;
    //맵 변수 지정


    //String 배열 안에 각각 스트링으로 저장(키,밸류 처럼)
    // player = P, NPC = N, monster = M,
    public void printMap(String[][] map) {
        for (int i = 0; i < map[0].length; i++){
            System.out.print("--");
        }System.out.print("--");
        System.out.println();
        for (String[] strings : map) {
            System.out.print("|");
            for (String string : strings) {
                switch (string) {
                    case "Player": //플레이어
                        System.out.print("ME");
                        break;

                    case "Monster1": // 일반몬스터1
                        System.out.print("M1");
                        break;
                    case "Monster2": // 일반몬스터2
                        System.out.print("M2");
                        break;
                    case "Boss1": // 보스1
                        System.out.print("11");
                        break;
                    case "Boss2": // 보스2
                        System.out.print("22");
                        break;
                    case "Boss3": // 최종 보스
                        System.out.print("XX");
                        break;
                    case "NPC_Person1": //npc - 사람1
                        System.out.print("P1");
                        break;
                    case "NPC_Person2": //npc - 사람2
                        System.out.print("P2");
                        break;
                    case "EndWay": //막힌길
                        System.out.print("00");
                        break;
                    case "ShopWeapon": //npc - 대장간
                        System.out.print("WP");
                        break;
                    case "ShopPotion": //npc - 상점
                        System.out.print("SP");
                        break;
                    case "Portal":
                        System.out.print("GO");
                        break;
                    case "Road":
                    default:
                        System.out.print("  ");
                        break;
                }
            }
            //줄바꿈
            System.out.print("|");
            System.out.println();
        }
        for (int i = 0; i < map[0].length; i++){
            System.out.print("--");
        }System.out.println("--");
    }

    // 현재 플레이어 위치 메소드 (Y,X 좌표 수정)
    // 플레이어 이동시 호출됨
    public void currentPlayerPlace(Player player, String[][] map) {
        loop:
        for (int yIndex = 0; yIndex < map.length; yIndex++) {
            for (int xIndex = 0; xIndex < map[yIndex].length; xIndex++) {
                //index 를 찾았다면 플레이어 xy 좌표 수정
                if (map[yIndex][xIndex].equals("Player")) {
                    player.xPlace = xIndex;
                    player.yPlace = yIndex;
                    break loop;
                }
            }
        }
    }

    //이동하려는 인덱스가 길이 아니면 true 를 리턴하는 메소드(플레이어 무브 메소드에 들어감)
    public boolean thisIsRoad(String string){
        return string.equals("Road");
    }

    //전투 발생 조건 메소드 (몬스터와 플레이어 x or y 좌표가 1이하일때)
    public boolean metMonster(Player player, Monster monster, GameMap map){
        int xDistance;
        int yDistance;

        if (map.mapName.equals("villageMap")){
            return false;
        }

        // 플레이어, 몬스터의 좌표값 차이
        xDistance = player.xPlace - monster.monsterPlaceX;
        yDistance = player.yPlace - monster.monsterPlaceY;

        //비교 (xy 좌표값의 차이가 -1 ~ 1 사이라면 true 리턴)
        if (xDistance > -2 && xDistance < 2){
            if (yDistance == 0){
                return true;
            }
        }
        if(yDistance > -2 && yDistance < 2){
            return xDistance == 0;
        }
        return false;
    }

    public boolean metNPC(Player player, NPC npc, GameMap map){
        int xDistance;
        int yDistance;

        if (map.mapName.equals("forestMap")){
            return false;
        }

        // 플레이어, 몬스터의 좌표값 차이
        xDistance = player.xPlace - npc.npcPlaceX;
        yDistance = player.yPlace - npc.npcPlaceY;

        //비교 (xy 좌표값의 차이가 -1 ~ 1 사이라면 true 리턴)
        if (xDistance > -2 && xDistance < 2){
            if (yDistance == 0){
                return true;
            }
        }
        if(yDistance > -2 && yDistance < 2){
            return xDistance == 0;
        }
        return false;
    }

    public boolean metPortal(Player player, Portal portal){
        int xDistance;
        int yDistance;

        // 플레이어, 몬스터의 좌표값 차이
        xDistance = player.xPlace - portal.portalPlaceX;
        yDistance = player.yPlace - portal.portalPlaceY;

        //비교 (xy 좌표값의 차이가 -1 ~ 1 사이라면 true 리턴)
        if (xDistance > -2 && xDistance < 2){
            if (yDistance == 0){
                return true;
            }
        }
        if(yDistance > -2 && yDistance < 2){
            return xDistance == 0;
        }
        return false;
    }


}
