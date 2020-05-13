import java.util.Scanner;

public class Star {
    public static void star() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("별의 크기 입력");
        int scNum = scanner.nextInt();

        // 윗 꼭지점 출력
        for (int i1 = 0; i1 < scNum*17; i1++){
            System.out.print(" ");
        }
        System.out.println(" *");

        //22영역 출력 (상단 세모꼴)
        //줄바꿈 포문 4번반복
        int i4 = 4*scNum; //앞공백 변수 (줄바꿈시 1감소)
        int i7 = 3; //별표 변수 (줄바꿈시 2증가)
        for (int line = scNum*4;line > 0;line--){

            //13공백*입력수
            for (int space1 = 0;space1 < scNum;space1++){
                System.out.print("             ");
            }

            //줄어드는 공백 출력
            for (int space2 = i4;space2 > 0;space2--)  {
                System.out.print(" ");
            }
            i4--;

            // 늘어나는 별표 출력
            for (int star = 0;star < i7;star++){
                System.out.print("*");
            }
            i7+=2;

            //줄바꿈
            System.out.println();
        }

        //33영역 출력(중단 사다리꼴)
        int i10 = 1; //앞공백 변수 (줄바꿈시 3증가)
        int i12 = 35*scNum; //별표 변수
        int nexti1 = 0;
        int nexti2 = 0;
        for (int line = 0;line < 4*scNum - (scNum/2);line++){ //
            nexti1 = 0;
            nexti2 = 0;
            //앞공백 출력 (3씩 늘어남)
            System.out.print(" ");
            for (int i11=1; i11 < i10;i11++){
                System.out.print(" ");
                nexti1++;
            }
            i10+=3;

            //별표 출력 (6씩 줄어듬)         35*scNum 줄바꿈 (35-6)*scNum 줄바꿈 (35-6-6)*scNum
            for (int i13=i12; i13 > 0; i13--){
                System.out.print("*");
                nexti2++;
            }
            i12-=6;

            //줄바꿈
            System.out.println();
        }

        //44영역
        int nexti3 = 0;
        int nexti4 = 0;
        for (int line = 0; line < scNum*2; line++){
            System.out.print(" ");
            nexti3 = 0;
            nexti4 = 0;
            //앞공백 출력 (33영역 변수-1번 출력)
            for (int space1=nexti1-1;space1 > 0;space1--){
                System.out.print(" ");
                nexti3++;
            }
            nexti1--;

            //별표 출력 (33영역 변수 +2번 출력)
            for (int star = nexti2+2; star > 0; star--){
                System.out.print("*");
                nexti4++;
            }
            nexti2+=2;

            //줄바꿈
            System.out.println();
        }

        //55영역 출력 (
        // 44영역에서 앞공,별표 변수를 받아서    //앞공변수-1
        nexti4 = (int)(float)(nexti4/2+1);
        int midCount = 1;
        for (int line = 0; line < 6*scNum; line++){
            for (int space = 0;space < nexti3; space++){
                System.out.print(" ");
            }
            nexti3--;

            //(int)(별표변수/2+1) 줄바꿈시-2
            for (int star = 0; star < nexti4; star++){
                System.out.print("*");
            }

            //중공 => 1부터 시작, 6씩 증가
            for (int midSpace = 0; midSpace < midCount; midSpace++){
                System.out.print(" ");
            }
            midCount+=6;

            //별표 출력 반복
            for (int star = 0; star < nexti4; star++){
                System.out.print("*");
            }
            if (nexti4 < 0){
                break;
            }
            nexti4-=2;


            //줄바꿈
            System.out.println();
        }
    }
}
