package com.javarush.test.level01.lesson04.task02;

import java.lang.String;

/* JavaRush. Learn once - use anywhere
������ ���������, ������� ������� �� ����� �������: �JavaRush. Learn once - use anywhere� 10 ���.
*/
public class Solution
{
    public static void main(String[] args)
    {
        String message = "JavaRush. Learn once - use anywhere";
        print5Times(message);
        print5Times(message);
    }


    public static void print5Times(String s)
    {
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
    }
}
