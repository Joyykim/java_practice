import java.util.Scanner;

public class Dia {
    public static void dia() {
        Scanner sc = new Scanner(System.in);
        System.out.println("마름모의 크기 입력");
        int scNum = sc.nextInt();

        //73까지 출력 가능! 그 이상부터 콘솔창 짤림@@@@@@@@@@@@@@@@@


        // 윗 꼭지점 출력
        for (int i = 0;i < scNum;i++){
            System.out.print("        "); //space 9
        }
        System.out.print(" ");
        System.out.println("*");

        //상단-중앙 출력
        int 상단_중앙 = 1;

        for (int i = 8*scNum;i > 0;i--){
            for (int j = i;j > 0;j--){ //앞 공간 그리기
                System.out.print(" ");
            }
            System.out.print("*"); //앞 별표 그리기

            for (int k = 0;k < 상단_중앙;k++){ //가운데 공간 그리기
                System.out.print(" ");
            }
            System.out.print("*"); //뒤 별표 그리기
            상단_중앙 += 2;

            System.out.println();//줄바꿈
        }



        //중앙 꼭지점 출력
        System.out.print("*");
        for (int i = 0;i < scNum; i++){//중앙 공간 17
            System.out.print("                ");
        }
        System.out.print(" ");
        System.out.print("*"); //별표
        System.out.println(); //줄바꿈



        //중앙-하단 출력
        int downMid = 상단_중앙;
        int z = 1;
        for (int i = 0;i < 8*scNum;i++){

            for (int j = 0;j < z;j++){ //앞 공간 그리기
                System.out.print(" ");
            }
            z += 1;

            System.out.print("*"); //앞 별표 그리기

            for (int k = 2;k < downMid;k++){ //가운데 공간 그리기
                System.out.print(" ");
            }

            System.out.print("*"); //뒤 별표 그리기
            downMid -= 2;
            System.out.println();//줄바꿈
        }

        //아래 꼭지점 출력
        for (int i = 0;i < scNum;i++){
            System.out.print("        "); //space 9
        }
        System.out.print(" ");
        System.out.println("*");
    }


}
