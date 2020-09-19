import java.lang.Math;

class Vector{

    float x, y, z;

    public Vector(float x, float y, float z)    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Vector v) {
        return x == v.x && y == v.y && z == v.z;
    }

    /*public int hashCode()   {
        return (int) (x + y + z);
    }*/

    public String toString()    {
        return "[x = " + x + ", y = " + y + ", z = " + z + "]";
    }

    public float mag()  {
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

    public void normalize() {
        if (mag() != 0) {
            into(1/mag());
        }
    }

    public void plus(Vector v)  {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    public static Vector add(Vector v1, Vector v2)  {
        return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public void minus(Vector v)  {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }

    public static Vector sub(Vector v1, Vector v2)  {
        return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public void into(float f) {
        x *= f;
        y *= f;
        z *= f;
    }

    public void into(int f) {
        x *= f;
        y *= f;
        z *= f;
    }

    public static Vector mult(Vector v, float f)  {
        return new Vector(v.x*f, v.y*f, v.z*f);
    }

    public static Vector mult(Vector v, int f)  {
        return new Vector(v.x*f, v.y*f, v.z*f);
    }

    public float dot(Vector v)  {
        return dot(this, v);
    }

    public static float dot(Vector v1, Vector v2)  {
        return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
    }

    public Vector cross(Vector v)   {
        return cross(this, v);
    }

    public static Vector cross(Vector v1, Vector v2) {
        return new Vector(v1.y*v2.z - v1.z*v2.y, v1.z*v2.x - v1.x*v2.z, v1.x*v2.y - v1.y*v2.x);
    }

    public Vector copy()    {
        return new Vector(x, y, z);
    }

}