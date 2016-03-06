package dotsandboxesdemo;

//此类用于定义一个横/竖，（x，y）代表左/上端点，horizon=true/false代表横还是竖
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
