class CameraOrthographic	{

	Vector u, v, w;
	float v_max, friction;

	Vector vel;
	float ease = 20;

	public CameraOrthographic(Vector u, Vector v, Vector w, float v_max)	{
		this.u = u;
		this.v = v;
		this.w = w;
		this.v_max = v_max;
		this.friction = v_max/ease;

		vel = new Vector(0, 0, 0);
	}

	public float[][] getTransformationalMatrix()	{
		return Matrix.getTransformationalMatrixXYZ(u, v, w);
	}

	public void move(Vector input)	{
		if (input.x != 0 || input.y != 0) {
			input.into(v_max/ease);
			vel = Vector.add(vel, input);

			if (vel.mag() >= v_max) {
				vel.normalize();
				vel.into(v_max);
			}

			//System.out.println("force applied.");

		}else {
			if (vel.mag() != 0) {
				Vector velocity = vel.copy();
				vel = Vector.sub(vel, Vector.mult(Vector.normalized(vel), friction));

				if (Vector.dot(vel, velocity) <= 0) {
					vel.x = 0;
					vel.y = 0;
					vel.z = 0;
				}

				//System.out.println("friction applied.");

			}else {
				//System.out.println("at rest.");
			}
		}

		if (vel.mag() != 0) {
			float[][] M = Matrix.inverse33(getTransformationalMatrix());
			Vector velocity = Matrix.vXm33(M, vel);

			Vector i = Vector.normalized(velocity);
			float[][] M_reg = Matrix.getTransformationalMatrixXYZ(w, i, Vector.cross(w, i));
			float[][] M_inv = Matrix.inverse33(M_reg);

			u = Matrix.vXm33(M_reg, u);
			v = Matrix.vXm33(M_reg, v);
			w = Matrix.vXm33(M_reg, w);

			float[][] M_rot = Matrix.getRotationalMatrixAboutZ(velocity.mag());

			u = Matrix.vXm33(M_rot, u);
			v = Matrix.vXm33(M_rot, v);
			w = Matrix.vXm33(M_rot, w);

			u = Matrix.vXm33(M_inv, u);
			v = Matrix.vXm33(M_inv, v);
			w = Matrix.vXm33(M_inv, w);
		}
	}

}