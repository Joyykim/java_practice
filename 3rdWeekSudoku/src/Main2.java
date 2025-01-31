import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main2 {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("스도쿠의 크기를 입력하세요.");
        int scNum = scanner.nextInt();
        scanner.close();

        // 2차원 배열 생성
        int[][] sudoku = new int[scNum][scNum];

        int xIndex;
        int yIndex;
        int tryNum = 0;
        int[] tempArray = new int[scNum];

        //2차원 배열 끝까지 랜덤수로 채워넣기 (입력수*입력수 반복)
        //yLoop:
        for (yIndex = 0; yIndex < scNum; yIndex++) {
            xLoop:
            for (xIndex = 0; xIndex < scNum; xIndex++) {
                sudoku[yIndex][xIndex] = 0;

                //원소 하나가 담길 때 까지 무한반복으로 랜덤수 뽑기
                //elementLoop:
                while (true){

                    //랜덤수 생성
                    int ranNum = random.nextInt(scNum)+1;

                    //x배열 중복 없을땐 타 차원의 인덱스들을 확인
                    //만약 랜덤수가 x 배열에 존재시 다른 랜덤수 뽑기
                    if (IntStream.of(sudoku[yIndex]).noneMatch(l -> l == ranNum)){

                        //타차원의 동일 인덱스 원소들을 리스트에 묶어서 contains 로 확인
                        //ArrayList<Integer> tempList = new ArrayList<>();

                        //현재보다 위의 차원수 만큼 반복해서 윗 차원 원소들을 리스트에 추가
                        for (int dimNum = 0;dimNum < yIndex; dimNum++){
                            //tempList.add(sudoku[dimNum][xIndex]);
                            //@@
                            tempArray[dimNum] = sudoku[dimNum][xIndex];
                        }

                        // 타차원(Y축) 중복 확인
                        //중복X  =>  원소 담기
                        if (/*!tempList.contains(ranNum)*/  IntStream.of(tempArray).noneMatch(k -> k == ranNum)){
                            sudoku[yIndex][xIndex] = ranNum;
                            Arrays.fill(tempArray, 0);
                            break;
                        }

                        //중복O  =>  루프 감지 문
                        else {
                            tryNum++;
                            //루프O
                            if(tryNum > scNum*scNum){
                                xIndex = 0;

                                //sudoku[yIndex] = new int[scNum];

                                //xIndex = xIndex - 1;
                                //sudoku[yIndex][xIndex] = 0;

                                // 루프 감지 초기화
                                tryNum = 0;
                                continue xLoop;
                            }
                        }
                    }
                }
            }
            System.out.println((yIndex+1)+"번째 배열 연산 완료.");
        }

        for(int yPrint = 0; yPrint < scNum; yPrint++)
        {
            for(int xPrint = 0; xPrint < scNum; xPrint++)
            {
                int element = sudoku[yPrint][xPrint];
                System.out.printf("%-3d", element);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(scNum+"크기의 스도쿠 생성 완료.");
    }
}