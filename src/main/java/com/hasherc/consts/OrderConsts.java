package com.hasherc.consts;

public class OrderConsts {

    public static final int UNPAID = 0;
    public static final int PAID = 1;
    public static final int UN_PRINT = 2;
    public static final int DONE = 3;
    // 黑白单面打印单价
    private static final double SINGLE_BLACK_PRINT_ONE_PAGE_PRICE = 0.1;
    // 彩色单面打印单价
    private static final double SINGLE_COLOR_PRINT_ONE_PAGE_PRICE = 1;
    //黑白双面打印单价
    private static final double DUPLEX_BLACK_PRINT_ONE_PAGE_PRICE = 0.2;

    public static double calculatePrice(int page, boolean color, boolean duplex) {

        double cost;

        if (color) {
            //彩色单面
            cost = page * OrderConsts.SINGLE_COLOR_PRINT_ONE_PAGE_PRICE;
        } else {

            if (duplex) {
                //黑白双面
                cost = (page - page % 2) * OrderConsts.DUPLEX_BLACK_PRINT_ONE_PAGE_PRICE + OrderConsts
                        .SINGLE_BLACK_PRINT_ONE_PAGE_PRICE;
            } else {
                //黑白单面
                cost = page * OrderConsts.SINGLE_BLACK_PRINT_ONE_PAGE_PRICE;
            }
        }
        return cost;
    }
}
