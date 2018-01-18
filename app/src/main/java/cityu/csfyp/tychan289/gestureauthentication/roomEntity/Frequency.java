package cityu.csfyp.tychan289.gestureauthentication.roomEntity;

/**
 * Created by Moonviler on 18/1/18.
 */

public class Frequency {
    public Frequency(){
        class_0 = 0;
        class_1 = 0;
        class_2 = 0;
        class_3 = 0;
        class_4 = 0;
        class_5 = 0;
        class_6 = 0;
        class_7 = 0;
        class_8 = 0;
        class_9 = 0;
    }

    public String username;
    public int class_0;
    public int class_1;
    public int class_2;
    public int class_3;
    public int class_4;
    public int class_5;
    public int class_6;
    public int class_7;
    public int class_8;
    public int class_9;

    public void add(int segment){
        switch (segment){
            case 0: class_0 = class_0 + 1;
            case 1: class_1 = class_1 + 1;
            case 2: class_2 = class_2 + 1;
            case 3: class_3 = class_3 + 1;
            case 4: class_4 = class_4 + 1;
            case 5: class_5 = class_5 + 1;
            case 6: class_6 = class_6 + 1;
            case 7: class_7 = class_7 + 1;
            case 8: class_8 = class_8 + 1;
            case 9: class_9 = class_9 + 1;
        }
    }

    public static Frequency average(Frequency f1, Frequency f2){
        f1.class_0 = (f1.class_0 + f2.class_0) / 2;
        f1.class_1 = (f1.class_1 + f2.class_1) / 2;
        f1.class_2 = (f1.class_2 + f2.class_2) / 2;
        f1.class_3 = (f1.class_3 + f2.class_3) / 2;
        f1.class_4 = (f1.class_4 + f2.class_4) / 2;
        f1.class_5 = (f1.class_5 + f2.class_5) / 2;
        f1.class_6 = (f1.class_6 + f2.class_6) / 2;
        f1.class_7 = (f1.class_7 + f2.class_7) / 2;
        f1.class_8 = (f1.class_8 + f2.class_8) / 2;
        f1.class_9 = (f1.class_9 + f2.class_9) / 2;
        return f1;
    }
}
