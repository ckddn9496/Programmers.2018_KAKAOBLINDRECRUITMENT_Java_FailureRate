import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		int N = 5;
		int[] stages = {2,1,2,6,2,4,3,3};
		
//		int N = 4;
//		int[] stages = {4,4,4,4};
		
		System.out.println(Arrays.toString(new Solution().solution(N, stages)));
	}
}

class Solution {
	class Stage implements Comparable<Stage>{
		int id;
		float failureRate;
		@Override
		public int compareTo(Stage o) {
			if (this.failureRate == o.failureRate)
				return this.id - o.id;
			else if (o.failureRate > this.failureRate)
				return 1;
			else
				return -1;
		}
		@Override
		public String toString() {
			return id + ": " + failureRate;
		}
	}
	
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int playerNum = stages.length;
        ArrayList<Stage> stageList = new ArrayList<>(N);
        Arrays.sort(stages);
        int stageIdx = 0;
        for (int i = 1; i <= N; i++) {
        	int count = 0;
        	for (int j = stageIdx; j < stages.length; j++) {
//        		System.out.print(" j : " + j);
        		if (i != stages[j]) {
//        			System.out.println(" break");
        			stageIdx = j;
        			break;
        		} else {
        			count++;        			
        		}
        	}
//        	System.out.println("count : " + count + " playerNum : " + playerNum);
        	Stage curStage = new Stage();
        	curStage.id = i;
        	if (playerNum == 0)
        		curStage.failureRate = 0.f;
        	else 
        		curStage.failureRate = ((float)count/playerNum);
        	stageList.add(curStage);
        	playerNum -= count;
        }
        Collections.sort(stageList);
        for (int i = 0; i < N; i++) {
        	answer[i] = stageList.get(i).id;
        }
        return answer;
    }
}