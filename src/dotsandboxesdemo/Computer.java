package dotsandboxesdemo;
import java.util.*;

//���Խ����1.2:1=14:6
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
		
		//����ʣ��ձߵĸ���������ʱ�ɽ��ܵ��������
		int moveCount = currentBoard.getValidMoves().size(); //ʣ��ձߵĸ���
		int nextLevel = moveCount;
		int moveDepth = 0;
		
		ArrayList<Line>safeEdge=getSafeEdge(currentBoard.getHedge(),currentBoard.getVedge(),currentBoard.getBox());
		//safe edge����7��ʱ����Ϊ��ʱ����safe�����������
		if (safeEdge.size()>7){
			int i,j;
			//����ڰ�ȫ����³���3�ߣ�ȡ֮
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
					
		//next level��ʾ�׳�������������max����������ͨ��������Ƽ����������9��������
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

		//�Կձ��е�ÿһ������飺�����������һ�ߣ��Ƿ��ռ��һ��box������ǵĻ�������Ƿ����˳��ռ�����
			for (Line nextLine : validMoves) {
				Move checkedMove = new Move();
				Board nextBoard=bd.clone();
				checkedMove.played = nextLine;
				checkedMove.score = nextBoard.assumeLines(nextLine);

				//���ռ����һ��������box������Ƿ����˳��ռ�����
				if (checkedMove.score > 0){
					checkedMove.score += takeMore(nextBoard.clone(),nextLine);
				}
				
				if (checkedMove.score>3){
					return checkedMove;
				}
				//����δ��������δ�ﵽ��ȣ�������һ��
				if ((nextBoard.getValidMoves().size() > 0) && (depthLeft > 0)) {
					//���ֻ��ƣ����ռ����box��������һ�ݹ����playerNow����������һ�ݹ������һ��ҡ���һ�ݹ��ʣ����ȵݼ�
					double nextScore = findBest(nextBoard, depthLeft -1,(checkedMove.score > 0) ? playerNow : 3-playerNow).score;
					checkedMove.score = checkedMove.score+ ((checkedMove.score > 0) ? nextScore : -nextScore);
				}
				if ((bestMove == null) || (checkedMove.score > bestMove.score)) bestMove = checkedMove;
			}
		return bestMove;
	}
	
	//��ĳһ����Ϊ��׼������Ƿ����˳��ռ����һ�����ӣ�������ԣ�ͨ���ݹ������ĸ��ӣ����շ�������
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

	//����һ��3��box���ҳ���ʣ��һ��
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
	
	//����һ���ߣ��ҳ����������ߵ�һ��������box
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
	
	//���ص�ǰ��ֵ�safe edge
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