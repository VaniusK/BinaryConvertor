import java.util.Scanner;
import java.lang.Math;
import java.math.*;
class Main {
    public static String IntToBin(long n, int dp) {
        String s = "";
        int a = 0;
        long n2  = n;
        n = Math.abs(n);
        long d = (long)Math.pow(2, dp - 1) - 1;
        while (d >= 1) {
            if (a == 0) {
                s += "0";
            }
            else {
                if (n >= d) {
                n -= d;
                s = s + "1";
            } else {
                s = s + "0";
            }
            }
            d /= 2;
            if (a == 0) d += 1;
            a += 1;
        }
        if (n2 < 0) {
            s = s.replace('0', '@');
            s = s.replace('1', '0');
            s = s.replace('@', '1');
            int b = s.length() - 1;
            while (true) {
                if (s.substring(b, b + 1).equals("0")) {
                    s = s.substring(0, b) + "1" + s.substring(b + 1);
                    break;
                } else {
                    s = s.substring(0, b) + "0" + s.substring(b + 1);
                    b -= 1;
                }
            }
        }
        return s;
    }
    
    
    public static String Beautify(String s) {
        StringBuffer sb = new StringBuffer(s);
        int n = s.length() / 8;
        for (int i = 0; i < n; i++) {
            sb.insert(i + (i + 1) * 8, " ");
        }
        return sb.toString();
    }
    
    
    public static int getDp(int n) {
        int d = 1;
        int a = 1;
        while (d <= n) {
            a += 1;
            d *= 2;
        }
        return a;
    }
    
    
    
    public static String FloatToBin(float n) {
        String s;
        if (n > 0) s = "0 ";
        else s = "1 ";
        n = Math.abs(n);
        String order = IntToBin((int)(n), getDp((int)(n)));
        int offset = getDp((int)(n)) - 2;
        Float dec = n % 1;
        String orderDec = "";
        while (orderDec.length() < 1025 && dec != 0) {
            dec *= 2;
            orderDec += String.valueOf((int)(Math.floor(dec)));
            dec = dec % 1;
        }
        String ans_order = IntToBin(offset + 127, 9);
        s += ans_order.substring(1);
        s += " ";
        s += (order + orderDec).substring(2);
        while (34 > s.length()) {
            s += "0";
        }
        if (s.length() <= 34) {
            return s;
        } else {
            return "Это число слишком большое для float!";
        }
    }
    public static String DoubleToBin(double n) {
        String s;
        if (n > 0) s = "0 ";
        else s = "1 ";
        n = Math.abs(n);
        String order = IntToBin((int)(n), getDp((int)(n)));
        int offset = getDp((int)(n)) - 2;
        double dec = n % 1;
        String orderDec = "";
        while (orderDec.length() < 1025 && dec != 0) {
            dec *= 2;
            orderDec += String.valueOf((int)(Math.floor(dec)));
            dec = dec % 1;
        }
        String ans_order = IntToBin(offset + 1023, 12);
        s += ans_order.substring(1);
        s += " ";
        s += (order + orderDec).substring(1);
        while (66 > s.length()) {
            s += "0";
        }
        return s;
    }
    
    
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String start2;
        while (true) {
            start2  = sc.next();
            start2 = start2.replace(",", ".");
            if (start2.substring(0, 1) == ".") start2 = "0" + start2;
            if (start2.indexOf(".") == -1) {
                long start = Long.parseLong(start2);
                if (Math.abs(start) <= 127) {
                    System.out.println("byte:  " + Beautify(IntToBin(start, 8)));
                }
                if (Math.abs(start) <= 32767) {
                    System.out.println("short:  " + Beautify(IntToBin(start, 16)));
                }
                if (Math.abs(start) <= 2147483647) {
                    System.out.println("int:  " + Beautify(IntToBin(start, 32)));
                }
                float f = Float.parseFloat(start2);
                double d = Double.parseDouble(start2);
                if (Math.abs(start) <= 9223372036854775807L) {
                    System.out.println("long:  " + Beautify(IntToBin(start, 64)));
                }
                System.out.println("float:  " + FloatToBin(f));
                System.out.println("double:  " + DoubleToBin(d));
                System.out.printf("Также советуем вам попробовать следующие числа: %f, %f, %f\n", Math.random() * Math.random() * 100, Math.random() * Math.random() * 100, Math.random() * Math.random() * 100);
            } else {
                try {
                    float f = Float.parseFloat(start2);
                    double d = Double.parseDouble(start2);
                    System.out.println("float:  " + FloatToBin(f));
                    System.out.println("double:  " + DoubleToBin(d));
                    System.out.printf("Также советуем вам попробовать следующие числа: %f, %f, %f\n", Math.random() * Math.random() * 100, Math.random() * Math.random() * 100, Math.random() * Math.random() * 100);
                } catch (Exception e) {
                    System.out.println("Попробуйте ещё раз!");
                }
            }
        }
    }
}