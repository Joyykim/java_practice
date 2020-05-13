package Player;

public class Portal {
    public Portal(int portalPlaceY, int portalPlaceX, GameMap map){ //생성시 xy 좌표, 맵을 받음
        this.portalPlaceY = portalPlaceY;
        this.portalPlaceX = portalPlaceX;
        map.mapArray[portalPlaceY][portalPlaceX] = GameMap.PORTAL; //맵배열에 스트링 입력
    }

    public int portalPlaceY;
    public int portalPlaceX;
}