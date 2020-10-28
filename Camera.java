class CameraOrthographic	{

	Vector u, v, w;

	public CameraOrthographic(Vector u, Vector v, Vector w)	{
		this.u = u;
		this.v = v;
		this.w = w;
	}

	public float[][] getTransformationalMatrix()	{
		return Matrix.getTransformationalMatrixXYZ(u, v, w);
	}

	public void move(Vector input)	{
		if (input.x != 0 || input.y != 0) {

			float[][] M = Matrix.inverse33(getTransformationalMatrix());
			input = Matrix.vXm33(M, input);

			Vector i = Vector.normalized(input);
			float[][] M_reg = Matrix.getTransformationalMatrixXYZ(w, i, Vector.cross(w, i));
			float[][] M_inv = Matrix.inverse33(M_reg);

			u = Matrix.vXm33(M_reg, u);
			v = Matrix.vXm33(M_reg, v);
			w = Matrix.vXm33(M_reg, w);

			float[][] M_rot = Matrix.getRotationalMatrixAboutZ(input.mag());

			u = Matrix.vXm33(M_rot, u);
			v = Matrix.vXm33(M_rot, v);
			w = Matrix.vXm33(M_rot, w);

			u = Matrix.vXm33(M_inv, u);
			v = Matrix.vXm33(M_inv, v);
			w = Matrix.vXm33(M_inv, w);

		}
	}

}