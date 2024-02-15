package uwu.lopyluna.calamos.utilities;

public class JsonMathUtils {
    
    public static double calc(String operation, float input) {
        return switch (operation) {
            case "sin" -> Math.sin(input);
            case "cos" -> Math.cos(input);
            case "tan" -> Math.tan(input);
            case "abs" -> Math.abs(input);
            case "asin" -> Math.asin(input);
            case "acos" -> Math.acos(input);
            case "atan" -> Math.atan(input);
            case "toRadians" -> Math.toRadians(input);
            case "toDegrees" -> Math.toDegrees(input);
            case "signum" -> Math.signum(input);
            case "ulp" -> Math.ulp(input);
            case "sinh" -> Math.sinh(input);
            case "cosh" -> Math.cosh(input);
            case "tanh" -> Math.tanh(input);
            case "exp" -> Math.exp(input);
            case "log" -> Math.log(input);
            case "log10" -> Math.log10(input);
            case "sqrt" -> Math.sqrt(input);
            case "cbrt" -> Math.cbrt(input);
            case "ceil" -> Math.ceil(input);
            case "floor" -> Math.floor(input);
            case "rint" -> Math.rint(input);
            case "round" -> Math.round(input);
            default -> input;
        };
    }
    public static double calc(String operation, float input1, float input2) {
        return switch (operation) {
            case "atan2" -> Math.atan2(input1, input2);
            case "pow" -> Math.pow(input1, input2);
            case "hypot" -> Math.hypot(input1, input2);
            case "max" -> Math.max(input1, input2);
            default -> input1;
        };
    }
    public static double calc(String operation) {
        return switch (operation) {
            case "E" -> Math.E;
            case "PI" -> Math.PI;
            case "random" -> Math.random();
            default -> 0;
        };
    }
    
}
