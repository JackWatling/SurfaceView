package uk.co.jackwatling.surfaceview;

public abstract class MathHelper {
	
	public static int Clamp(int a, int l, int u){
		if (a < l)
			return a;
		if (a > u)
			return u;
		return a;
	}

}
