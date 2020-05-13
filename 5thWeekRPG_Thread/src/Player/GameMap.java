package Player;

import Monster.Monster;
import NPC.NPC;

public class GameMap {
    // 맵은 2차원 배열
    public String[][] mapArray;

    public GameMap(int y, int x,String mapName) {
        mapArray = new String[y][x];
        this.mapName = mapName;
    }

    //맵 변수 지정
    public String mapName;


    // 상수 지정
    public static final String PLAYER = "Player";
    public static final String MONSTER1 = "Monster1";
    public static final String MONSTER2 = "Monster2";
    public static final String BOSS1 = "Boss1";
    public static final String BOSS2 = "Boss2";
    public static final String BOSS3 = "Boss3";
    public static final String NPC1 = "NPC_Person1";
    public static final String NPC2 = "NPC_Person2";
    public static final String END_WAY = "EndWay";
    public static final String WEAPON = "ShopWeapon";
    public static final String POTION = "ShopPotion";
    static final String PORTAL = "Portal";
    public static final String ROAD = "Road";
    public static final String VILLAGE = "villageMap";
    public static final String FOREST = "forestMap";


    //String 배열 안에 각각 스트링으로 저장(키,밸류 처럼)
    // player = P, NPC = N, monster = M,
    public synchronized void printMap() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println();
        for (int i = 0; i < this.mapArray[0].length; i++){
            System.out.print("###");
        }System.out.print("###");
        System.out.println();
        for (int y = 0; y < this.mapArray.length; y++) {
            System.out.print("# ");
            for (int x = 0;x < this.mapArray[y].length; x++) {
                String string = this.mapArray[y][x];
                try {
                    switch (string) {
                        case PLAYER: //플레이어
                            System.out.print("ME ");
                            break;
                        case MONSTER1: // 일반몬스터1
                            System.out.print("M1 ");
                            break;
                        case MONSTER2: // 일반몬스터2
                            System.out.print("M2 ");
                            break;
                        case BOSS1: // 보스1
                            System.out.print("11 ");
                            break;
                        case BOSS2: // 보스2
                            System.out.print("22 ");
                            break;
                        case BOSS3: // 최종 보스
                            System.out.print("XX ");
                            break;
                        case NPC1: //npc - 사람1
                            System.out.print("P1 ");
                            break;
                        case NPC2: //npc - 사람2
                            System.out.print("P2 ");
                            break;
                        case END_WAY: //막힌길
                            System.out.print("00 ");
                            break;
                        case WEAPON: //npc - 대장간
                            System.out.print("WP ");
                            break;
                        case POTION: //npc - 상점
                            System.out.print("SP ");
                            break;
                        case PORTAL:
                            System.out.print("GO ");
                            break;
                        case ROAD:
                        default:
                            System.out.print("   ");
                            break;
                    }
                }catch (NullPointerException e){

                }
            }
            //줄바꿈
            System.out.print("#");

            if (!this.mapName.equals(VILLAGE)){
                if (y == 1){
                    System.out.print("       <= 늪지대");
                }
                if (y == 7){
                    System.out.print("       <= 깊은 숲");
                }
                if (y == 13){
                    System.out.print("       <= 동굴");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < this.mapArray[0].length; i++){
            System.out.print("###");
        }System.out.println("###");
    }

    // 현재 플레이어 위치 메소드 (Y,X 좌표 수정)
    // 플레이어 이동시 호출됨
    public void currentPlayerPlace(Player player) {
        loop:
        for (int yIndex = 0; yIndex < this.mapArray.length; yIndex++) {
            for (int xIndex = 0; xIndex < this.mapArray[yIndex].length; xIndex++) {
                //index 를 찾았다면 플레이어 xy 좌표 수정
                if (this.mapArray[yIndex][xIndex].equals(GameMap.PLAYER)) {
                    player.xPlace = xIndex;
                    player.yPlace = yIndex;
                    break loop;
                }
            }
        }
    }

    //이동하려는 인덱스가 길이 아니면 true 를 리턴하는 메소드(플레이어 무브 메소드에 들어감)
    public boolean thisIsRoad(String string){
        return string.equals(GameMap.ROAD);
    }

    //전투 발생 조건 메소드 (몬스터와 플레이어 x or y 좌표가 1이하일때)
    public boolean metMonster(Player player, Monster monster, GameMap map){
        int xDistance;
        int yDistance;

        if (map.mapName.equals(VILLAGE)){
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

        if (map.mapName.equals(FOREST)){
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
