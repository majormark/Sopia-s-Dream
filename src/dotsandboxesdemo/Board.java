package dotsandboxesdemo;

//对于整个局势的抽象， 通过addLines方法对每一步走棋进行处理，
//监控整个局势
//此类不承担任何绘图功能
import java.util.*;

public class Board {
	private int[][] hedge;
	private int[][] vedge;
	private int[][] box;
	private int[][] whoseBox;
	private int[] score;
	
	
	// 该构造方法用于AI搜索中克隆之用
	public Board(int[][] new_hedge, int[][] new_vedge, int[][] new_box) {
		hedge = new_hedge;
		vedge = new_vedge;
		box = new_box;
	}

	public Board() {

	}

	public Board clone() {
		int[][] newHedge = new int[6][5];
		int[][] newVedge = new int[5][6];
		int[][] newBox = new int[5][5];
		int i;
		for (i = 0; i < 6; i++) {
			newHedge[i] = hedge[i].clone();
		}
		for (i = 0; i < 5; i++) {
			newVedge[i] = vedge[i].clone();
		}
		for (i = 0; i < 5; i++) {
			newBox[i] = box[i].clone();
		}
		Board newBoard = new Board(newHedge, newVedge, newBox);
		return newBoard;
	}

	public void reset() { // 初始化
		int i, j;
		hedge = new int[6][5];
		vedge = new int[5][6];
		box = new int[5][5];
		score = new int[3];
		whoseBox = new int[5][5];
		
		for (i = 0; i < 6; i++) {
			for (j = 0; j < 5; j++) {
				hedge[i][j] = 0;
			}
		}
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 6; j++) {
				vedge[i][j] = 0;
			}
		}
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				box[i][j] = 0;
			}
		}
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				whoseBox[i][j] = 0;
			}
		}
		score[1] = 0;
		score[2] = 0;
	}
	
	public boolean isValid(Line pt){
		if (pt.getDirection()){
			if (hedge[pt.getX()][pt.getY()]==0) return true;
		}
		else{
			if (vedge[pt.getX()][pt.getY()]==0) return true;
		}
		return false;
	}

	public int addLines(Line pt, int player) {
		int getBox;
		int row = pt.getX();
		int column = pt.getY();
		if (pt.getDirection()) {
			if (hedge[row][column] != 0)
				getBox = -1;
			else {
				hedge[row][column] = player;
				addToBoxes(row, column, true);
				getBox = checkHedge(row, column, player);
			}
		} else {
			if (vedge[row][column] != 0)
				getBox = -1;
			else {
				vedge[row][column] = player;
				addToBoxes(row, column, false);
				getBox = checkVedge(row, column, player);
			}
		}
		
		return getBox;
	}

	// 专门用于AI中搜索之用，因为AI与玩家不同，不可能试图走已经走过的边，所以省去两次判断
	public int assumeLines(Line pt) {
		int row = pt.getX();
		int column = pt.getY();
		if (pt.getDirection()) {
			hedge[row][column] =3;
			addToBoxes(row, column, true);
			return AIcheckHedge(row, column);
		} else {
			vedge[row][column] = 3;
			addToBoxes(row, column, false);
			return AIcheckVedge(row, column);
		}
	}

	// 检查新走的一步是否占领了某个格子,更新得分
	public int checkHedge(int x, int y, int player) {
		int takeBox = 0;
		if (x > 0) {
			if (box[x - 1][y] == 4) {
				whoseBox[x - 1][y] = player;
				score[player]++;
				takeBox++;
			}
		}
		if (x < 5) {
			if (box[x][y] == 4) {
				whoseBox[x][y] = player;
				score[player]++;
				takeBox++;
			}
		}
		return takeBox;
	}

	// 检查新走的一步是否占领了某个格子,更新得分
	public int checkVedge(int x, int y, int player) {
		int takeBox = 0;
		if (y > 0) {
			if (box[x][y - 1] == 4) {
				whoseBox[x][y - 1] = player;
				score[player]++;
				takeBox++;
			}
		}
		if (y < 5) {
			if (box[x][y] == 4) {
				whoseBox[x][y] = player;
				score[player]++;
				takeBox++;
			}
		}
		return takeBox;
	}

	// AI专用的check，因为在AI的运行中并不需要box和whosebox，为了提高效率
	public int AIcheckHedge(int x, int y) {
		int takeBox = 0;
		if (x > 0) {
			if (box[x - 1][y] == 4) {
				takeBox++;
			}
		}
		if (x < 5) {
			if (box[x][y] == 4) {
				takeBox++;
			}
		}
		return takeBox;
	}

	public int AIcheckVedge(int x, int y) {
		int takeBox = 0;
		if (y > 0) {
			if (box[x][y - 1] == 4) {
				takeBox++;
			}
		}
		if (y < 5) {
			if (box[x][y] == 4) {
				takeBox++;
			}
		}
		return takeBox;
	}

	// 将画的线添加到boxes中去
	private void addToBoxes(int x, int y, Boolean horizon) {
		if (horizon) {
			if (x < 5)
				box[x][y]++;
			if (x > 0)
				box[x - 1][y]++;
		} else {
			if (y < 5)
				box[x][y]++;
			if (y > 0)
				box[x][y - 1]++;
		}
	}

	// 获取所有未下的边
	public ArrayList<Line> getValidMoves() {
		int i, j;
		ArrayList<Line> validMoves = new ArrayList<Line>();
		for (i = 0; i < 6; i++) {
			for (j = 0; j < 5; j++) {
				if (hedge[i][j] == 0)
					validMoves.add(new Line(i, j, true));
			}
		}
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 6; j++) {
				if (vedge[i][j] == 0)
					validMoves.add(new Line(i, j, false));
			}
		}
		return validMoves;
	}

	// 判断游戏是否结束,未结束返回0，已结束的话A胜返回1，B胜返回2
	public int isGameOver() {
		if (score[1] + score[2] == 25) {
			if (score[1] > score[2])
				return 1;
			else
				return 2;
		} else
			return 0;
	}
	
	public int[][] getHedge() {
		return hedge;
	}
	
	public int[][] getVedge() {
		return vedge;
	}

	public int[][] getBox() {
		return box;
	}

	public int[][] getWhoseBox() {
		return whoseBox;
	}

	public int[] getScore() {
		return score;
	}

}
