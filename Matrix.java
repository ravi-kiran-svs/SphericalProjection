class Matrix{

	public static float det33(float[][] M)	{
		float det = 0;
		for(int i=0; i<3; i++)	{
			det +=M[0][i]*(M[1][(i+1)%3]*M[2][(i+2)%3] - M[1][(i+2)%3]*M[2][(i+1)%3]);
		}
		return det;
	}

	public static float[][] inverse33(float[][] M)	{
		float[][] invM = new float[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		float det = det33(M);
		for(int i=0; i<3; i++)	{
			for(int j=0; j<3; j++)	{
				invM[j][i] = (M[(i+1)%3][(j+1)%3]*M[(i+2)%3][(j+2)%3] - 
					M[(i+1)%3][(j+2)%3]*M[(i+2)%3][(j+1)%3])/det;
			}
		}
		return invM;
	}

	public static Vector vXm33(Vector p, float[][] M)	{
		Vector v = new Vector(0, 0, 0);
	    v.x = p.x*M[0][0] + p.y*M[1][0] + p.z*M[2][0];
	    v.y = p.x*M[0][1] + p.y*M[1][1] + p.z*M[2][1];
	    v.z = p.x*M[0][2] + p.y*M[1][2] + p.z*M[2][2];
	    return v;
	}

	public static float[][] getTransformationalMatrixXYZ(Vector x, Vector y, Vector z)	{
		float[][] M = new float[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	    M[0][0] = x.x;
	    M[1][0] = x.y;
	    M[2][0] = x.z;
	    M[0][1] = y.x;
	    M[1][1] = y.y;
	    M[2][1] = y.z;
	    M[0][2] = z.x;
	    M[1][2] = z.y;
	    M[2][2] = z.z;
	    return M;
	}

}