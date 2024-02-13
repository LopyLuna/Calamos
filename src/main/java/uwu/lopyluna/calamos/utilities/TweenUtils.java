package uwu.lopyluna.calamos.utilities;

public class TweenUtils {
    private static final float PI = (float) Math.PI;

    //Linear//
    public static float linear(float x) {
        return x;
    }
    //Quadratic//
    public static float quadraticIn(float x) {
        return x * x;
    }
    public static float quadraticOut(float x) {
        return -x*(x-2);
    }
    public static float quadraticInOut(float x) {
        if ((x *= 2) < 1) return (0.5f * x * x);
        return -0.5f * ((--x) * (x-2)-1);
    }
    //Cubic//
    public static float cubicIn(float x) {
        return x * x * x;
    }
    public static float cubicOut(float x) {
        return (x-=1)*x*x + 1;
    }
    public static float cubicInOut(float x) {
        if ((x*=2) < 1) return 0.5f*x*x*x;
        return 0.5f * ((x-=2)*x*x + 2);
    }
    //Quart//
    public static float quartIn(float x) {
        return x * x * x * x;
    }
    public static float quartOut(float x) {
        return -((x-=1)*x*x*x - 1);
    }
    public static float quartInOut(float x) {
        if ((x*=2) < 1) return 0.5f*x*x*x*x;
        return -0.5f * ((x-=2)*x*x*x - 2);
    }
    //Quint//
    public static float quintIn(float x) {
        return x * x * x * x * x;
    }
    public static float quintOut(float x) {
        return (x-=1)*x*x*x*x + 1;
    }
    public static float quintInOut(float x) {
        if ((x*=2) < 1) return 0.5f*x*x*x*x*x;
        return-0.5f * ((x-=2)*x*x*x*x + 2);
    }
    //Sine//
    public static float sineIn(float x) {
        return (float) -Math.cos(x * (PI/2)) + 1;
    }
    public static float sineOut(float x) {
        return (float) Math.sin(x * (PI/2));
    }
    public static float sineInOut(float x) {
        return -0.5f * ((float) Math.cos(PI*x)-1);
    }
    //Exponential//
    public static float expoIn(float x) {
        return (x==0) ? 0 : (float) Math.pow(2, 10 * (x - 1));
    }
    public static float expoOut(float x) {
        return (x==1) ? 1 : -(float) Math.pow(2, 10 * x) + 1;
    }
    public static float expoInOut(float x) {
        if (x==0) return 0;
        if (x==1) return 1;
        if ((x*=2) < 1) return 0.5f * (float) Math.pow(2, 10 * (x - 1));
        return 0.5f * (-(float)Math.pow(2, -10 * --x) + 2);
    }
    //Circular//
    public static float circIn(float x) {
        return (float) -Math.sqrt(1 - x*x) - 1;
    }
    public static float circOut(float x) {
        return (float) Math.sqrt(1 - (x-=1)*x);
    }
    public static float circInOut(float x) {
        if ((x*=2) < 1) return -0.5f * ((float)Math.sqrt(1 - x*x) - 1);
        return 0.5f * ((float)Math.sqrt(1 - (x-=2)*x) + 1);
    }
    //Elastic//
    public static float elasticIn(float x) {
        float c4 = (2*PI) / 3;

        return x == 0
                ? 0
                : (float) (x == 1
                ? 1
                : -Math.pow(2, 10 * x - 10) * Math.sin((x * 10 - 10.75) * c4));
    }
    public static float elasticOut(float x) {
        float c4 = (2*PI) / 3;

        return x == 0
                ? 0
                : (float) (x == 1
                ? 1
                : Math.pow(2, -10 * x) * Math.sin((x * 10 - 0.75) * c4) + 1);
    }
    public static float elasticInOut(float x) {
        float c5 = ((2*PI) / 4.5f);

        return x == 0
                ? 0
                : (float) (x == 1
                ? 1
                : x < 0.5
                ? -(Math.pow(2, 20 * x - 10) * Math.sin((20 * x - 11.125) * c5)) / 2
                : (Math.pow(2, -20 * x + 10) * Math.sin((20 * x - 11.125) * c5)) / 2 + 1);
    }
    //Back//
    public static float backIn(float  x) {
        float c1 = 1.70158f;
        float c3 = c1 + 1;

        return c3 * x * x * x - c1 * x * x;
    }
    public static float backOut(float  x) {
        float c1 = 1.70158f;
        float c3 = c1 + 1;

        return (float) (1 + c3 * Math.pow(x - 1, 3) + c1 * Math.pow(x - 1, 2));
    }
    public static float backInOut(float  x) {
        float c1 = 1.70158f;
        float c2 = c1 * 1.525f;

        return (float) (x < 0.5
                        ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2
                        : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2);
    }
    //Bounce//
    public static float bounceIn(float x) {
        return 1 - bounceOut(1-x);
    }
    public static float bounceOut(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;

        if (x < 1 / d1) {
            return n1 * x * x;
        } else if (x < 2 / d1) {
            return n1 * (x -= 1.5 / d1) * x + 0.75f;
        } else if (x < 2.5 / d1) {
            return n1 * (x -= 2.25 / d1) * x + 0.9375f;
        } else {
            return n1 * (x -= 2.625 / d1) * x + 0.984375f;
        }
    }
    public static float bounceInOut(float x) {
        return x < 0.5
                ? (1 - bounceOut(1 - 2 * x)) / 2
                : (1 + bounceOut(2 * x - 1)) / 2;
    }
}
