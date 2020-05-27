package com.xz.vehicle_license;

import java.text.NumberFormat;
import java.util.Scanner;

import kotlin.jvm.internal.PropertyReference0Impl;

public class TestJava {
    public static void main(String[] args) {
//        print99();
//        print2();
        resInt(1090);
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    private static void print(Object o) {
        System.out.print(o);
    }



    private static void  print1(){
        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                print("*");
            }
            println("");
        }
    }

    private static void resInt(int num){
        if (num < 10) print(num);
        int p = num;
        while (p>0){
            print(p%10);
            p = p/10;
        }
        println("");
    }
    private static void print2(){
        int f = 14;
        for (int i = f; i > 0; i = i -4) {
            for (int j = 0; j < (14-i)/2; j++) {
                print(" ");
            }
            for (int j = 0; j < i / 2; j++) {
                print("* ");
            }
            for (int j = 0; j < (14-i)/2; j++) {
                print(" ");
            }
            println("");
        }
        for (int i = 0; i < f; i = i +4) {
            for (int j = 0; j < (14-i)/2; j++) {
                print(" ");
            }
            for (int j = 0; j < i / 2; j++) {
                print("* ");
            }
            for (int j = 0; j < (14-i)/2; j++) {
                print(" ");
            }
            println("");
        }
    }
    /**
     * 99 乘法表
     */
    private static void print99(){
        for (int j = 1; j < 10; j++) {
            for (int k = 1; k <= j; k++) {
                System.out.print(k+"*"+j+"="+j*k+" ");
            }
            println("");
        }
    }
}

