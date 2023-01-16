package ex3;

import static ex3.Expression.$;

public class Application {

    public static void main(String[] args) {
        Expression e1 = $(4).plus($(5));
        System.out.println(e1.eval());

        Expression e2 = ($(4).plus($(5))).times($(2));
        System.out.println(e2.eval());
    }

}
