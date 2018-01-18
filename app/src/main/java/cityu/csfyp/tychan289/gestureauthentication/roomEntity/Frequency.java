package cityu.csfyp.tychan289.gestureauthentication.roomEntity;

/**
 * Created by Moonviler on 18/1/18.
 */

public class Frequency {
    public Frequency() {
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
    public int class_10;
    public int class_11;
    public int class_12;
    public int class_13;
    public int class_14;
    public int class_15;
    public int class_16;
    public int class_17;
    public int class_18;
    public int class_19;

    public void add(int segment) {
        switch (segment) {
            case 0:
                class_0 = class_0 + 1;
                break;
            case 1:
                class_1 = class_1 + 1;
                break;
            case 2:
                class_2 = class_2 + 1;
                break;
            case 3:
                class_3 = class_3 + 1;
                break;
            case 4:
                class_4 = class_4 + 1;
                break;
            case 5:
                class_5 = class_5 + 1;
                break;
            case 6:
                class_6 = class_6 + 1;
                break;
            case 7:
                class_7 = class_7 + 1;
                break;
            case 8:
                class_8 = class_8 + 1;
                break;
            case 9:
                class_9 = class_9 + 1;
                break;
            case 10:
                class_10 = class_10 + 1;
                break;
            case 11:
                class_11 = class_11 + 1;
                break;
            case 12:
                class_12 = class_12 + 1;
                break;
            case 13:
                class_13 = class_13 + 1;
                break;
            case 14:
                class_14 = class_14 + 1;
                break;
            case 15:
                class_15 = class_15 + 1;
                break;
            case 16:
                class_16 = class_16 + 1;
                break;
            case 17:
                class_17 = class_17 + 1;
                break;
            case 18:
                class_18 = class_18 + 1;
                break;
            case 19:
                class_19 = class_19 + 1;
                break;
        }
    }

    public static Frequency average(Frequency f1, Frequency f2) {
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
        f1.class_10 = (f1.class_10 + f2.class_10) / 2;
        f1.class_11 = (f1.class_11 + f2.class_11) / 2;
        f1.class_12 = (f1.class_12 + f2.class_12) / 2;
        f1.class_13 = (f1.class_13 + f2.class_13) / 2;
        f1.class_14 = (f1.class_14 + f2.class_14) / 2;
        f1.class_15 = (f1.class_15 + f2.class_15) / 2;
        f1.class_16 = (f1.class_16 + f2.class_16) / 2;
        f1.class_17 = (f1.class_17 + f2.class_17) / 2;
        f1.class_18 = (f1.class_18 + f2.class_18) / 2;
        f1.class_19 = (f1.class_19 + f2.class_19) / 2;
        return f1;
    }

    public static Frequency compare(Frequency f1, Frequency f2){
        f1.class_0 = Math.abs(f1.class_0 - f2.class_0);
        f1.class_1 = Math.abs(f1.class_1 - f2.class_1);
        f1.class_2 = Math.abs(f1.class_2 - f2.class_2);
        f1.class_3 = Math.abs(f1.class_3 - f2.class_3);
        f1.class_4 = Math.abs(f1.class_4 - f2.class_4);
        f1.class_5 = Math.abs(f1.class_5 - f2.class_5);
        f1.class_6 = Math.abs(f1.class_6 - f2.class_6);
        f1.class_7 = Math.abs(f1.class_7 - f2.class_7);
        f1.class_8 = Math.abs(f1.class_8 - f2.class_8);
        f1.class_9 = Math.abs(f1.class_9 - f2.class_9);
        f1.class_10 = Math.abs(f1.class_10 - f2.class_10);
        f1.class_11 = Math.abs(f1.class_11 - f2.class_11);
        f1.class_12 = Math.abs(f1.class_12 - f2.class_12);
        f1.class_13 = Math.abs(f1.class_13 - f2.class_13);
        f1.class_14 = Math.abs(f1.class_14 - f2.class_14);
        f1.class_15 = Math.abs(f1.class_15 - f2.class_15);
        f1.class_16 = Math.abs(f1.class_16 - f2.class_16);
        f1.class_17 = Math.abs(f1.class_17 - f2.class_17);
        f1.class_18 = Math.abs(f1.class_18 - f2.class_18);
        f1.class_19 = Math.abs(f1.class_19 - f2.class_19);
        return f1;
    }
}
