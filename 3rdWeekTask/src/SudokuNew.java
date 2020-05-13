import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SudokuNew {
    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());

        Scanner scanner = new Scanner(System.in);
        System.out.println("스도쿠의 크기 입력");
        int scNum = scanner.nextInt();
        scanner.close();
        int[][] sudoku;

        // 2차원 배열 생성
        sudoku = new int[scNum][scNum];

        int xIndex = 0;
        int yIndex = 0;
        int tryNum = 0;
        boolean reTry = false;
        int checkNum = 100;

        boolean restart = false;
        int endInt = 0;

        //2차원 배열 끝까지 랜덤수로 채워넣기 (입력수*입력수 반복)
        while (true/*int i = 0;i < scNum*scNum;i++*/){

            //원소 하나가 담길 때 까지 무한반복으로 랜덤수 뽑기
            outerLoop2:while (true){

                //식이 막힐때 돌아와서 x배열 초기화 후 0번부터 채우기////////////////////////////////////
                if (reTry){
                    //System.out.println(tryNum);
                    sudoku[yIndex] = new int[scNum];
                    xIndex = 0;
                    reTry = false;
                    tryNum = 0;
                }

                //랜덤수 생성
                int ranNum = (int)(Math.random()*scNum+1);

                //만약 랜덤수가 x 배열에 존재시 다른 랜덤수 뽑기
                if (IntStream.of(sudoku[yIndex]).anyMatch(l -> l == ranNum)){
                    continue;
                }

                //x배열 중복 없을땐 타 차원의 인덱스들을 확인
                else {

                    //타차원의 동일 인덱스 원소들을 리스트에 묶어서 contains 로 확인
                    ArrayList<Integer> tempList = new ArrayList<>();

                    //차원수 만큼 반복해서 타차원 원소들을 리스트에 추가
                    for (int dimNum = 0;dimNum < scNum; dimNum++){
                        tempList.add(sudoku[dimNum][xIndex]);
                    }

                    // 타차원(Y축) 중복 확인
                    //중복X
                    if (!tempList.contains(ranNum)){
                        sudoku[yIndex][xIndex] = ranNum;
                        //System.out.println(ranNum);
                        checkNum = ranNum;
                        break outerLoop2;
                    }
                    //중복O
                    else {
                        tryNum++;
                        //식이 막힐 때 반복탈출 & 새로 시도////////////////////////////////////////////////////////////////
                        if(tryNum > scNum){
                            reTry = true;
                            continue outerLoop2;
                        }
                        continue outerLoop2;
                    }

                }

            }

            //xy 증감식
            if (xIndex < scNum-1){
                xIndex++;
            }else {
                xIndex = 0;
                yIndex++;
            }

            //배열 full 확인 후 탈출
            restart = false;
            endInt = 0;
            for (int[] arr: sudoku){
                restart = IntStream.of(arr).anyMatch(p -> p == 0);
                //0없을시 endInt 1+
                if (!restart){
                    endInt++;
                }
            }
            if (endInt == scNum){
                break;
            }
        }


        //배열 출력 확인
        for (int[] c1 : sudoku) {
            for (int c2 : c1){
                if (c2 < 10){
                    System.out.print(c2+"   ");
                }else if (c2 < 100){
                    System.out.print(c2+"  ");
                }else if (c2 < 1000){
                    System.out.print(c2+" ");
                }
            }
            System.out.println();
        }


    }
}