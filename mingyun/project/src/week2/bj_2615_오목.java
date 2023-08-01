package week2;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class bj_2615_오목 {

    public static boolean inRange(int x, int y) {
        return x<19 && y<19 && 0<=x && 0<=y;
    }
    public static void main(String[] args) throws Exception {
        //////////////////////////////////////////////////////////////
        // 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
        //////////////////////////////////////////////////////////////
        //System.setIn(new FileInputStream("Test5.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	//버퍼드 리더를 통해 한번에 읽어올 준비를 함.

        int[][] map = new int[19][19];	//바둑판 배열을 만듬
        for (int i=0;i<19;i++) {	//바둑판을 19줄이므로 19번 반복
            StringTokenizer st = new StringTokenizer(br.readLine());	//한줄씩 읽어옴 그걸 스트링토크나이저를 이용해 띄어쓰기 기준으로 나눔
            for (int j=0;j<19;j++) {	//한줄 씩 읽어온 것을 바둑판에 입력
                map[i][j] = Integer.parseInt(st.nextToken());	//토큰은 스트링으로 반환하기 때문에 integer.parse를 이용해 받아옴
            }
        }

        int[] dx = {0,1,1,1};	//우,하,대각만 보고 가장 왼쪽 기준의 좌표로 출력하기 때문에 이동경로를 저장
        int[] dy = {1,0,1,-1};

        int winner = 0, x=0, y=0;	//출력을 위한 변수 초기화

        work:for (int i=0;i<19;i++) {	//바둑판 만큼 반복
            for (int j=0;j<19;j++) {
                if (map[i][j]!=0) {	//만약 바둑판의 숫자가 0이 아니라면 바둑알이 놓여있는것이기 때문에 탐색 시작
                    for (int k=0;k<4;k++) {	//dx, dy의 배열의 숫자만큼 탐색할것
                        if (inRange(i-dy[k], j-dx[k])&& map[i][j] != map[i-dy[k]][j-dx[k]]||inRange(i-dy[k], j-dx[k]) == false) {
                            //만약에 탐색하기 전의 값이 동일하다면 이미 탐색한 범위이기 때문에 탐색하지 않고, 이때 arrayRangeOutOfBounds가 날 수 있기 때문에 먼저 범위가 유효한지 체크
                            //만약 이전 범위의 탐색이 불가능한 범위이면 현재의 위치가 바둑판에 끝에 있다는것이므로 탐색 시작.
                            int tx = j;	//x값에 j를 입력
                            int ty = i;	//y값에 i를 입력
                            int check;	//몇개의 바둑알이 연속으로 있는지 체크
                            for (check=0;check<4;check++) {
                                tx += dx[k];	//디렉션 배열에 지정한 순서대로 이동함.
                                ty += dy[k];
                                if (inRange(tx,ty)==false) break;	//만약에 바둑판 범위를 벗어나면 5알을 연속할 수 없기 때문에 탈출
                                if (map[i][j] != map[ty][tx]) break;	//탐색한 위치에 다른 바둑알이 존재하면 탈출
                                if (check==3) {		//check가 3까지 반복했을 경우 현재위치 + 4번의 탐색을 했기 때문에 5알이 연속으로 이어져 있는것
                                    tx += dx[k];	//하지만 다음 바둑알까지 같은돌이면 승리조건이 아니므로 다음 바둑알을 체크함.
                                    ty += dy[k];
                                    if (inRange(tx,ty)&&map[i][j] == map[ty][tx]) break;	//체크결과 동일한 바둑알이면 6개알이 이어져있기 때문에 승리가 아님.
                                    winner = map[i][j];	//승리한 결과를 저장함
                                    x = j;
                                    y = i;
                                    break work; //승리자를 탐색했기 때문에 더 이상 탐색할 이유가 없으므로 반복문을 완전히 빠져나감.
                                }
                            }
                        }
                    }
                }
            }
        }
        if (winner ==0) System.out.println(0);	//만약 승리자가 없다면 0을 출력하고
        else System.out.printf("%d\n%d %d\n", winner, y+1, x+1);	//승리자가 있다면 x,y좌표를 출력함 이때 배열의 시작점이 0,0이고 문제에서는 1,1에서 시작하기 때문에 +1씩해서 출력하고
        //가로줄 번호가우선이기 때문에 y,x순으로 출력한다.
    }
}