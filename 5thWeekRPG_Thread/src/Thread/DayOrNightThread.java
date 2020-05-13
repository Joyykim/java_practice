package Thread;

import Player.GameMap;

import java.util.Random;

public class DayOrNightThread extends Thread {

    // 게임 최초 실행에만 생성
    DayOrNightThread(MainThread mainThread,boolean isDay){
        this.mainThread = mainThread;

        // 쓰레드 객체 생성시 낮밤 결정
        this.isDay = isDay;
    }

    // 게임 중간엔 모두 중간실행으로 생성
    DayOrNightThread(MainThread mainThread,boolean isDay, int sleepTime_left){
        this.mainThread = mainThread;

        // 쓰레드 객체 생성시 낮밤 결정, 중간실행여부 전달(is_battle_paused), 남은 시간 전달
        this.isDay = isDay;
        this.is_paused = true;
        this.mid_sleepTime = sleepTime_left;
    }

    // 게임 첫 실행, 포탈 이동시 변화
    // 쓰레드 첫 실행용 밤낮 변수

    boolean isDay; //시작시 낮 브금, 루프시 바로 밤으로 - MainThread 에서 참조
    boolean printOn_interact = true;

    private MainThread mainThread;

    private final String DAY_MUSIC_FILE = "c:/a_project_sound/day.wav";
    private final String NIGHT_MUSIC_FILE = "c:/a_project_sound/night.wav";


    private MusicThread musicThread_day_music = new MusicThread(DAY_MUSIC_FILE);
    private MusicThread musicThread_night_music = new MusicThread(NIGHT_MUSIC_FILE);


    // 기본 시간
    private int default_sleepTime = 10; //1분 슬립후 낮밤 교체
    // 시작 시간
    private int start_time;
    // 남은 시간 = 기본 시간 - (정지 시간 - 시작 시간) (int 로 계산)
    private int mid_sleepTime;
    // 전투 발생 일시정지시 - true (다시 재생할때 남은 시간(sleepTime_left)만큼 sleep 실행)
    private boolean is_paused;
    // 기본 - false, 두번 이상 일시정지시 - false (is_paused 슬립코드안에 한번 들어가면 true)
    private boolean multiple_paused = false;

    @Override
    public void run(){

        musicThread_day_music = new MusicThread(DAY_MUSIC_FILE);
        musicThread_night_music = new MusicThread(NIGHT_MUSIC_FILE);

        //최종보스 처치시 밤낮 실행X
        if (!mainThread.player.ending){


            //생성후 첫실행
            synchronized (this){
                if(isDay){
                    //BGM 실행
                    musicThread_day_music = new MusicThread(DAY_MUSIC_FILE);
                    musicThread_day_music.start();
                    //System.out.println("\n최초 낮 브금 재생");


                }else {
                    //BGM 실행
                    musicThread_night_music = new MusicThread(NIGHT_MUSIC_FILE);
                    musicThread_night_music.start();
                    //System.out.println("\n최초 밤 브금 재생");
                }
            }

            //첫실행 후부터는 루프안에서만 실행
            while (true){

                ////////////////////////음악 쓰레드 상태 확인 - 중복 음악 버그
/*                System.out.println("musicThread_night_music 상태: "+musicThread_night_music.getState());
                System.out.println("musicThread_day_music 상태: "+musicThread_day_music.getState());*/


                ///////////////////////////////////////////////슬립 코드

                //sleep 시작시간
                start_time = (int)(System.currentTimeMillis() / 1000);


                // 전투발생으로 일시정지 됐다면 남은 시간만큼만 sleep 실행
                // 두번이상 일시정지 됐다면 (남은시간 - 지난시간)으로 계산??
                if (is_paused){
                    is_paused = false;

                    // multiple_pause false => true 로 교체
                    if (!multiple_paused){
                        multiple_paused = true;
                    }

                    if (mid_sleepTime > default_sleepTime || mid_sleepTime < 0){
                        //sleep 시간 측정 오류 시 : 0 ~ 10 을 벗어남
                        try {
                            //System.out.println("※ sleep_time 오류 - sleepTime_left: "+ mid_sleepTime);
                            sleep(default_sleepTime *1000);
                        } catch (InterruptedException ignored) {}

                    }else {
                        //중간 실행
/*                        System.out.println("중간 실행");
                        System.out.println("sleepTime_left 만큼 sleep 실행: "+ mid_sleepTime);*/
                        try {
                            sleep(mid_sleepTime *1000);
                        } catch (InterruptedException ignored) {
                            mid_sleepTime = 0;
                        }

                    }
                }else {
                    // default 실행
                    multiple_paused = false;
                    try {
                        //System.out.println("default 실행");
                        sleep(default_sleepTime *1000);
                    } catch (InterruptedException ignored) {}
                }
                ////////////////////////////////////////////슬립코드

                //디버깅 - 맵이름 확인 - 낮밤 맵교체 버그
/*                System.out.println("mapName: "+mainThread.currentMap.mapName);
                System.out.println("isDay: "+isDay);*/


                if (isDay){ //낮에서 밤으로

                    isDay = false;

                    //맵 저장,교체
                    //현재맵이 숲이면 낮밤맵 교체
                    //////////////////////////////////////////////////////
                    if (mainThread.currentMap.mapName.equals(GameMap.FOREST)) {
                        //System.out.println("맵이름이 숲입니다. 낮숲 -> 밤숲");
                        //main 현재맵 플레이어 위치 ROAD 로
                        mainThread.currentMap.mapArray[mainThread.player.yPlace][mainThread.player.xPlace] = GameMap.ROAD;
                        //main 현재맵을 낮숲맵에 저장
                        mainThread.forestMap = mainThread.currentMap;
                        //main 밤숲맵 불러오기
                        mainThread.currentMap = mainThread.forestMap_night;
                        //맵이름 다시 지정
                        mainThread.currentMap.mapName = GameMap.FOREST;

                        //boss, player 좌표가 같다면 player 치우기
                        boolean player_move_aside = false;
                        if (mainThread.player.yPlace == 1 && mainThread.player.xPlace == 27){
                            player_move_aside = true;
                        }
                        if (mainThread.player.yPlace == 7 && mainThread.player.xPlace == 27){
                            player_move_aside = true;
                        }
                        if (mainThread.player.yPlace == 13 && mainThread.player.xPlace == 27){
                            player_move_aside = true;
                        }

                        //랜덤 하게 대각선으로 player 좌표 바꾸기
                        if (player_move_aside) {
                            Random random = new Random();
                            int ranNum1 = random.nextInt(2);
                            if (ranNum1 == 0){
                                mainThread.player.yPlace--;
                            }else {
                                mainThread.player.yPlace++;
                            }
                            int ranNum2 = random.nextInt(2);
                            if (ranNum2 == 0){
                                mainThread.player.xPlace++;
                            }else {
                                mainThread.player.xPlace--;
                            }
                        }
                        //main 현재맵에 플레이어 위치시키기
                        mainThread.currentMap.mapArray[mainThread.player.yPlace][mainThread.player.xPlace] = GameMap.PLAYER;
                    }
                    //////////////////////////////////////////////////////


                    //맵 출력 - 상호작용시 off
                    if (printOn_interact){
                        mainThread.currentMap.printMap();
                        System.out.println("\n밤이 되었습니다");
                        System.out.println("\n입력으로 행동을 정하세요: ");
                    }

                    //BGM 삭제, 재생성, 실행 - 밤
                    delete_restart_bgm_night();

/*                    //이전 BGM 삭제
                    if (musicThread_night_music.isAlive()){
                        synchronized (this){
                            musicThread_night_music.stop();
                        }
                    }
                    if (musicThread_day_music.isAlive()){
                        synchronized (this){
                            musicThread_day_music.stop();
                        }
                    }

                    //BGM 실행
                    musicThread_night_music = new MusicThread(NIGHT_MUSIC_FILE);
                    musicThread_night_music.start();*/


                }else { //밤에서 낮으로

                    isDay = true;

                    //맵 저장,교체
                    //현재맵이 숲이면 낮밤맵 교체
                    if (mainThread.currentMap.mapName.equals(GameMap.FOREST)){
                        //System.out.println("맵이름이 숲입니다. 밤숲 -> 낮숲");
                        //main 현재맵 플레이어 위치 ROAD 로
                        mainThread.currentMap.mapArray[mainThread.player.yPlace][mainThread.player.xPlace] = GameMap.ROAD;
                        //main 현재맵을 밤숲맵에 저장
                        mainThread.forestMap_night = mainThread.currentMap;
                        //main 낮숲맵 불러오기
                        mainThread.currentMap = mainThread.forestMap;
                        //맵이름 다시 지정
                        mainThread.currentMap.mapName = GameMap.FOREST;
                        //main 현재맵에 플레이어 위치시키기
                        mainThread.currentMap.mapArray[mainThread.player.yPlace][mainThread.player.xPlace] = GameMap.PLAYER;

                    }

                    //숲일땐 몬스터 재생성
                    if (mainThread.currentMap.mapName.equals(GameMap.FOREST)){
                        //몬스터 재생성
                        mainThread.monster_regenerate(mainThread);
                    }

                    //맵 출력 - 상호작용시 off
                    if (printOn_interact){
                        mainThread.currentMap.printMap();
                        System.out.println("\n낮이 되었습니다");
                        System.out.println("\n입력으로 행동을 정하세요: ");
                    }

                    //BGM 삭제, 재생성, 실행 - 낮
                    delete_restart_bgm_day();

/*                    //이전 BGM 삭제
                    if (musicThread_night_music.isAlive()){
                        synchronized (this){
                            musicThread_night_music.stop();
                        }
                    }
                    if (musicThread_day_music.isAlive()){
                        synchronized (this){
                            musicThread_day_music.stop();
                        }
                    }
                    //BGM 실행
                    musicThread_day_music = new MusicThread(DAY_MUSIC_FILE);
                    musicThread_day_music.start();*/
                }
                ///////////////////////////////
                //System.out.println("낮 브금 쓰레드 생존?"+musicThread_day_music.isAlive());
                //System.out.println("밤 브금 쓰레드 생존?"+musicThread_night_music.isAlive());

                ////////////////////////////////
            }
        }

    }

    private void delete_restart_bgm_night(){
        //이전 BGM 삭제
        if (musicThread_night_music.isAlive()){
            synchronized (this){
                musicThread_night_music.stop();
            }
        }
        if (musicThread_day_music.isAlive()){
            synchronized (this){
                musicThread_day_music.stop();
            }
        }

        //BGM 실행
        musicThread_night_music = new MusicThread(NIGHT_MUSIC_FILE);
        musicThread_night_music.start();
    }

    private void delete_restart_bgm_day(){
        //이전 BGM 삭제
        if (musicThread_night_music.isAlive()){
            synchronized (this){
                musicThread_night_music.stop();
            }
        }
        if (musicThread_day_music.isAlive()){
            synchronized (this){
                musicThread_day_music.stop();
            }
        }

        //BGM 실행
        musicThread_day_music = new MusicThread(DAY_MUSIC_FILE);
        musicThread_day_music.start();
    }

    //음악 정지, 남은 시간 리턴 = 포탈,전투
    int stopMusic_getLeftTime(){

        //음악 정지 메소드
/*        System.out.println("musicThread_night_music 상태: "+musicThread_night_music.getState());
        System.out.println("musicThread_day_music 상태: "+musicThread_day_music.getState());
        System.out.println("음악 정지 메소드 실행");*/
        if (musicThread_night_music.isAlive()){
            synchronized (this){
                //System.out.println("밤음악 긴급정지");
                musicThread_night_music.stop();
                //System.out.println("musicThread_night_music 상태: "+musicThread_night_music.getState());
            }
        }
        if (musicThread_day_music.isAlive()){
            synchronized (this){
                //System.out.println("낮음악 긴급정지");
                musicThread_day_music.stop();
                //System.out.println("musicThread_day_music 상태: "+musicThread_day_music.getState());
            }
        }

        // 남은 시간 = 기본 시간 - (시간시간 - 일시정지 시간) (int 로 계산)
        int pause_time = (int)(System.currentTimeMillis() / 1000);

        //첫 일시정지 => default_sleepTime 으로 계산
        //여러번 일시정지 => mid_sleepTime 으로 계산

        // multiple_pause true => (mid_sleepTime - 지난시간)으로 계산
        if (multiple_paused){
            mid_sleepTime = (mid_sleepTime - (pause_time - start_time)); //두번이상 정지
            //System.out.println("여러번 정지");
        }else {
            mid_sleepTime = (default_sleepTime - (pause_time - start_time)); //첫 정지
            //System.out.println("첫 정지");
        }
/*        System.out.println("pause_time - start_time: "+(pause_time - start_time));
        System.out.println("pause_time: "+pause_time);
        System.out.println("start_time: "+start_time);
        System.out.println("sleepTime_left: "+ mid_sleepTime);*/
        return mid_sleepTime;

    }

}
