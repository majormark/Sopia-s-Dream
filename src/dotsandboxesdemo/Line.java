package dotsandboxesdemo;

//�������ڶ���һ����/������x��y��������/�϶˵㣬horizon=true/false����ỹ����
public class Line {
	private int x, y;
	private Boolean horizon;

	public Line(int x, int y, Boolean horizon) {
		this.x = x;
		this.y = y;
		this.horizon = horizon;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Boolean getDirection() {
		return horizon;
	}

}
