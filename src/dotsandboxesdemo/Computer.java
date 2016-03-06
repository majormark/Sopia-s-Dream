package dotsandboxesdemo;
import java.util.*;

//测试结果：1.2:1=14:6
public class Computer {
	private  long MAX_TESTS=200000; 
	
	public Computer(int difficulty){
		if (difficulty==1){
			this.MAX_TESTS=5000;
		}
		else if (difficulty==2){
			this.MAX_TESTS=50000;
		}
		else{
			this.MAX_TESTS=200000;
		}
	}
	
	public Line AImove(Board currentBoard,int player) {
		Calendar c1=Calendar.getInstance();
		long d1=c1.getTimeInMillis();
		Line bestLine=null;
		
		//依据剩余空边的个数决定用时可接受的搜索深度
		int moveCount = currentBoard.getValidMoves().size(); //剩余空边的个数
		int nextLevel = moveCount;
		int moveDepth = 0;
		
		ArrayList<Line>safeEdge=getSafeEdge(currentBoard.getHedge(),currentBoard.getVedge(),currentBoard.getBox());
		//safe edge多于7条时，认为此时局面safe，可以随机下
		if (safeEdge.size()>7){
			int i,j;
			//如果在安全情况下出现3边，取之
			boolean surprise=false;
			for (i=0;i<5;i++){
				for (j=0;j<5;j++){
					if (currentBoard.getBox()[i][j]==3){
						bestLine=getEmptyLine(currentBoard.getHedge(),currentBoard.getVedge(),i,j);
						surprise=true;
					}
				}
			}
			if (!surprise) bestLine=(safeEdge.get((int)(Math.random()*safeEdge.size())));
			}
					
		//next level表示阶乘数量，不超过max常量，并且通过这个限制计算出不超过9的最大深度
		else{
			while ((nextLevel < MAX_TESTS) && (moveCount > 1)) {
				moveDepth++;
				moveCount--;
				nextLevel = nextLevel *moveCount;
				}
			Move bestMove = findBest(currentBoard,moveDepth,player);
			bestLine=bestMove.played;
		}
			
		Calendar c2=Calendar.getInstance();
		long d2=c2.getTimeInMillis();
		long interval=d2-d1;
		if (interval<200)
			try {
				Thread.sleep(200-interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return bestLine; 
	}
	
private Move findBest(Board bd,int depthLeft,int playerNow) {
		
		Move bestMove = null;
		List<Line> validMoves = bd.getValidMoves();

		//对空边中的每一条，检查：如果我下了这一边，是否会占领一个box，如果是的话，检查是否可以顺势占领更多
			for (Line nextLine : validMoves) {
				Move checkedMove = new Move();
				Board nextBoard=bd.clone();
				checkedMove.played = nextLine;
				checkedMove.score = nextBoard.assumeLines(nextLine);

				//如果占领了一个或两个box，检查是否可以顺势占领更多
				if (checkedMove.score > 0){
					checkedMove.score += takeMore(nextBoard.clone(),nextLine);
				}
				
				if (checkedMove.score>3){
					return checkedMove;
				}
				//若还未结束，且未达到深度，再搜索一步
				if ((nextBoard.getValidMoves().size() > 0) && (depthLeft > 0)) {
					//换手机制：如果占领了box，则传入下一递归的是playerNow，否则传入下一递归的是下一玩家。下一递归的剩余深度递减
					double nextScore = findBest(nextBoard, depthLeft -1,(checkedMove.score > 0) ? playerNow : 3-playerNow).score;
					checkedMove.score = checkedMove.score+ ((checkedMove.score > 0) ? nextScore : -nextScore);
				}
				if ((bestMove == null) || (checkedMove.score > bestMove.score)) bestMove = checkedMove;
			}
		return bestMove;
	}
	
	//以某一条边为基准，检查是否可以顺势占领下一个格子，如果可以，通过递归检查更多的格子，最终返回总数
	private int takeMore(Board bd,Line startingLine) {
		ArrayList<Box> startingBox = getBoxes(startingLine);
		int numMore = 0;
		for (Box box : startingBox) {
			if (bd.getBox()[box.getX()][box.getY()]!=4) {
				Line nextLineTaken = getEmptyLine(bd.getHedge(),bd.getVedge(),box.getX(),box.getY());
				if (nextLineTaken != null) {
					numMore += bd.assumeLines(nextLineTaken);
					numMore += takeMore(bd,nextLineTaken);
				}
			}
		}
		return numMore;
	}

	//对于一个3边box，找出其剩余一边
	private Line getEmptyLine(int hedge[][], int vedge[][],int x,int y) {
		int linesLeft = 4;
		if (hedge[x][y]!=0) linesLeft--;
		if (hedge[x+1][y]!=0) linesLeft--;
		if (vedge[x][y]!=0) linesLeft--;
		if (vedge[x][y+1]!=0) linesLeft--;

		if (linesLeft!= 1) return null;
		if (hedge[x][y]==0) return new Line(x,y,true);
		else if (hedge[x+1][y]==0) return  new Line(x+1,y,true);
		else if (vedge[x][y]==0) return new Line(x,y,false);
		else return new Line(x,y+1,false);
	}
	
	//根据一条边，找出包含这条边的一个或两个box
	public ArrayList<Box> getBoxes(Line pt){
		ArrayList<Box> setOfBoxes=new ArrayList<Box>();
		if (pt.getDirection()){
			if (pt.getX()>0) setOfBoxes.add(new Box(pt.getX()-1,pt.getY()));
			if (pt.getX()<5) setOfBoxes.add(new Box(pt.getX(),pt.getY()));
		}
		else{
			if (pt.getY()>0) setOfBoxes.add(new Box(pt.getX(),pt.getY()-1));
			if (pt.getY()<5) setOfBoxes.add(new Box(pt.getX(),pt.getY()));
		}
		return setOfBoxes;
	}
	
	//返回当前棋局的safe edge
	 public ArrayList<Line> getSafeEdge(int [][]hedge,int [][]vedge,int[][]box){
		 ArrayList<Line> safeEdge=new ArrayList<Line>();
		 int i,j;
		 Boolean safe;
		 for (i=0;i<6;i++){
			 for (j=0;j<5;j++){
				 safe=true;
				 if (i>0){
					 if (hedge[i][j]!=0||box[i-1][j]>1) safe=false;
					 }
				 if (i<5){
					 if (hedge[i][j]!=0||box[i][j]>1) safe=false;
					 }
				 if (safe) safeEdge.add(new Line(i,j,true));
				 }
			 }
		 for (i=0;i<5;i++){
			 for (j=0;j<6;j++){
				 safe=true;
				 if (j>0){
					 if (vedge[i][j]!=0||box[i][j-1]>1) safe=false;
					 }
				 if (j<5){
					 if (vedge[i][j]!=0||box[i][j]>1) safe=false;
					 }
				 if (safe) safeEdge.add(new Line(i,j,false));
				}
			}
		 return safeEdge;
	 }
	
	class Move{
		 public Line played;
		public double score;
	}
	 
	 class Box {
			private int x,y;
			public Box(int _x,int _y){
				x=_x;
				y=_y;
			}
			public int getX(){
				return x;
			}
			public int getY(){
				return y;
			}
	}
}