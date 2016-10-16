package csula.cs4660.graphs.utils;

public class PointXY {
	final int x;
    final int y;
    

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public PointXY(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof PointXY)) {
            return false;
        }

        PointXY coord = (PointXY) o;

        return  coord.x == x && coord.y == y;
    }

    @Override
    public int hashCode() {
    	int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
