package programming_assign4;

import java.util.Scanner;

public class SequenceAlignment2 {

	public static void main(String[] args) {
	    int gap = -1;
		
	    Scanner in = new Scanner(System.in);
        
	    /*
            String str1 = in.nextLine();
            String str2 = in.nextLine();
        
 
	    char dnaSeq1[] = str1.toCharArray();
	    char dnaSeq2[] = str2.toCharArray();
		*/
	    char dnaSeq1[] = {'A', 'C', 'G', 'C', 'C', 'G'};
		char dnaSeq2[] = {'A', 'C', 'C', 'A', 'C', 'G'};
        
		int n = dnaSeq1.length;
		int m = dnaSeq2.length;
	
		//------------------------Scoring Matrix----------------------
		int score[][] = new int[n + 1][m + 1];
		int traceback[][] = new int[n + 1][m + 1];

		for (int i = 0; i < n + 1; i++) {
			score[i][0] = i * gap;
		}
		for (int j = 0; j < m; j++) {
			score[0][j] = j * gap;
		}

		int diag, left, top;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				diag = score[i][j] + cost(dnaSeq1[i], dnaSeq2[j]);
				top = score[i][j+1] + gap;
				left = score[i+1][j] + gap;
				
				score[i + 1][j + 1] = max(diag, left, top);
				if (score[i+1][j+1] == diag) {traceback[i+1][j+1] = 1;}
				if (score[i+1][j+1] == top) {traceback[i+1][j+1] = 2;}
				if (score[i+1][j+1] == left){traceback[i+1][j+1] = 3;}
				
			}
		}
		System.out.println(score[n][m]);

		
		//------------------Best Alignment---------------------------------
		String firstFinal = "";
		String secondFinal = "";
		n = n -1;
		m = m - 1;
		
		while (n >= 0 || m >= 0) {
			if (traceback[n+1][m+1] == 1) {
				firstFinal = dnaSeq1[n] + firstFinal;
				secondFinal = dnaSeq2[m] + secondFinal;
				n -= 1;
				m -= 1;
			}
			else if (n >= 0 && traceback[n+1][m+1] == 2) {
				firstFinal = dnaSeq1[n] + firstFinal;
				secondFinal = "_" + secondFinal;
				n -= 1;
			}
			else {
				firstFinal = "_" + firstFinal;
				secondFinal = dnaSeq2[m] + secondFinal;
				m -= 1;
			}
			
		}
		
		System.out.println(firstFinal);
		System.out.println(secondFinal);
		
    }
    
    public static int cost(char first, char second) {
		if (first == second) {
			return 2;
		}
		else {
			return -1;
		}
	}
    
    public static int max(int a, int b, int c) {
		int max = a;
		if (max <= b) {
			if (c <= b) {
				max = b;
			}
		}
		if (max <= c) {
			if (b <= c) {
				max = c;
			}
		}
		return max;
	}
}
