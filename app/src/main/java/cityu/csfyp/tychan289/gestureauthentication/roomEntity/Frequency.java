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

    private static final double constant = 1;

    private String username;
    private int class_0;
    private int class_1;
    private int class_2;
    private int class_3;
    private int class_4;
    private int class_5;
    private int class_6;
    private int class_7;
    private int class_8;
    private int class_9;
    private int class_10;
    private int class_11;
    private int class_12;
    private int class_13;
    private int class_14;
    private int class_15;
    private int class_16;
    private int class_17;
    private int class_18;
    private int class_19;

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

    public static Frequency compare(Frequency f1, Frequency f2) {
        Frequency f = new Frequency();
        f.class_0 = Math.abs(f1.class_0 - f2.class_0);
        f.class_1 = Math.abs(f1.class_1 - f2.class_1);
        f.class_2 = Math.abs(f1.class_2 - f2.class_2);
        f.class_3 = Math.abs(f1.class_3 - f2.class_3);
        f.class_4 = Math.abs(f1.class_4 - f2.class_4);
        f.class_5 = Math.abs(f1.class_5 - f2.class_5);
        f.class_6 = Math.abs(f1.class_6 - f2.class_6);
        f.class_7 = Math.abs(f1.class_7 - f2.class_7);
        f.class_8 = Math.abs(f1.class_8 - f2.class_8);
        f.class_9 = Math.abs(f1.class_9 - f2.class_9);
        f.class_10 = Math.abs(f1.class_10 - f2.class_10);
        f.class_11 = Math.abs(f1.class_11 - f2.class_11);
        f.class_12 = Math.abs(f1.class_12 - f2.class_12);
        f.class_13 = Math.abs(f1.class_13 - f2.class_13);
        f.class_14 = Math.abs(f1.class_14 - f2.class_14);
        f.class_15 = Math.abs(f1.class_15 - f2.class_15);
        f.class_16 = Math.abs(f1.class_16 - f2.class_16);
        f.class_17 = Math.abs(f1.class_17 - f2.class_17);
        f.class_18 = Math.abs(f1.class_18 - f2.class_18);
        f.class_19 = Math.abs(f1.class_19 - f2.class_19);
        return f;
    }

    public static int chi_square_test(Frequency template, Frequency test) {
        int x = 0;
        int temp;

        if (template.class_0 != 0) {
            temp = Math.abs(template.class_0 - test.class_0) * Math.abs(template.class_0 - test.class_0) / template.class_0;
        } else {
            temp = (int)(Math.abs(template.class_0 - test.class_0) * Math.abs(template.class_0 - test.class_0) / constant);
        }
        if (temp != 0) {
            x = x + temp;
        }

        temp = Math.abs(template.class_1 - test.class_1) * Math.abs(template.class_1 - test.class_1) / template.class_1;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_2 - test.class_2) * Math.abs(template.class_2 - test.class_2) / template.class_2;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_3 - test.class_3) * Math.abs(template.class_3 - test.class_3) / template.class_3;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_4 - test.class_4) * Math.abs(template.class_4 - test.class_4) / template.class_4;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_5 - test.class_5) * Math.abs(template.class_5 - test.class_5) / template.class_5;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_6 - test.class_6) * Math.abs(template.class_6 - test.class_6) / template.class_6;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_7 - test.class_7) * Math.abs(template.class_7 - test.class_7) / template.class_7;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_8 - test.class_8) * Math.abs(template.class_8 - test.class_8) / template.class_8;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_9 - test.class_9) * Math.abs(template.class_9 - test.class_9) / template.class_9;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_10 - test.class_10) * Math.abs(template.class_10 - test.class_10) / template.class_10;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_11 - test.class_11) * Math.abs(template.class_11 - test.class_11) / template.class_11;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_12 - test.class_12) * Math.abs(template.class_12 - test.class_12) / template.class_12;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_13 - test.class_13) * Math.abs(template.class_13 - test.class_13) / template.class_13;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_14 - test.class_14) * Math.abs(template.class_14 - test.class_14) / template.class_14;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_15 - test.class_15) * Math.abs(template.class_15 - test.class_15) / template.class_15;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_16 - test.class_16) * Math.abs(template.class_16 - test.class_16) / template.class_16;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_17 - test.class_17) * Math.abs(template.class_17 - test.class_17) / template.class_17;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_18 - test.class_18) * Math.abs(template.class_18 - test.class_18) / template.class_18;
        if (temp != 0) {
            x = x + temp;
        }
        temp = Math.abs(template.class_19 - test.class_19) * Math.abs(template.class_19 - test.class_19) / template.class_19;
        if (temp != 0) {
            x = x + temp;
        }
        return x;
    }

    /* Getter and Setter Methods */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
