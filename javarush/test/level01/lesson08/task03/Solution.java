package com.javarush.test.level01.lesson08.task03;

/* ������ ������
������ ���������, ������� ������� �� ����� �������: ������� - ����� ������, �� ������ - ����� ������� 16 ���.
*/
public class Solution
{
    public static void main(String[] args)
    {
        //�������� ��� ��� ���
        String text = "������ - ����� ������, �� ������ - ����� ������";
        print4Times(text);
        print4Times(text);
        print4Times(text);
        print4Times(text);
    }

    public static void print4Times(String s)
    {
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
        System.out.println(s);
    }
}
