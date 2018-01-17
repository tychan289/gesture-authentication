package cityu.csfyp.tychan289.gestureauthentication;

/**
 * Created by Moonviler on 17/1/18.
 */

public class AccelerometerData {
    private double acceleration_x = 0;
    private double acceleration_y = 0;
    private double acceleration_z = 0;

    public AccelerometerData(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }

    public void setX(double a){
        acceleration_x = a;
    }

    public void setY(double a){
        acceleration_y = a;
    }

    public void setZ(double a){
        acceleration_z = a;
    }

    public double getX(){
        return acceleration_x;
    }

    public double getY(){
        return acceleration_y;
    }

    public double getZ(){
        return acceleration_z;
    }

}
