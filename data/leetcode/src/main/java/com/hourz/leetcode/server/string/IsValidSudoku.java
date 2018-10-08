package com.hourz.leetcode.server.string;

import java.util.Arrays;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-10-07
 */
public class IsValidSudoku {

	public boolean isValidSudoku(char[][] board) {
		for(int i = 0; i < 9; i++) {
			int[] row = new int[9];
			int[] col = new int[9];
			int[] cube = new int[9];
			
			for(int j = 0; j < 9; j++) {
				System.out.println("board[i][j] : [ i=" + i + "j = " + j + "] -- 值: " + board[i][j]);
				if(board[i][j]!='.') {
					if(row[board[i][j] - '1'] == 1) {
						/*System.out.println("====");
						System.out.println(row[board[i][j]]);
						System.out.println("====");*/
						return false;
					} else {
						row[board[i][j] - '1'] = 1;
						System.out.println("row[] " + Arrays.toString(row)+"---- board[i][j] =" + board[i][j]);
					}
				}
			
				if(board[j][i] != '.') {
					if(col[board[j][i] - '1'] == 1) {
						return false;
					} else {
						col[board[j][i] - '1'] = 1;
						System.out.println("col[]" + Arrays.toString(col) + "----board[j][i] = " + board[j][i]);
					}
				}
			
				// 每一宫内行列的变化
				int cubeX = 3 * (i/3) + j/3;
				int cubeY = 3 * (i%3) + j%3;
				if(board[cubeX][cubeY] != '.') {
				if(cube[board[cubeX][cubeY] - '1'] == 1) {
					return false;
				} else {
					System.out.println("cubeX= " + cubeX+" cubeY=" + cubeY);
					cube[board[cubeX][cubeY] - '1'] = 1;
					System.out.println("cube[]" + Arrays.toString(cube) +" --- board[cubeX][cubeY] = " + board[cubeX][cubeY]);
				}
			}
		}
		
		System.out.println("row[]"+Arrays.toString(row));
		System.out.println("col[]"+Arrays.toString(col));
		System.out.println("cube[]"+Arrays.toString(cube));
		}
		return true;
	
	}
}
