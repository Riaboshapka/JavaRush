package com.javarush.test.level01.lesson06.task02_test;

/* ��������
������� �� ����� ����� ������ ���������:
���� ����� �����.

� �������� �� �������� $800/����� � ������ ���.
� �������� �� �������� $1500/����� �� ������ ���.
� �������� �� �������� $2200/����� � ������ ���.
� �������� �� �������� $2700/����� � ��������� ���.
� �������� �� �������� $3200/����� � ����� ���.

������� ��� ��������� ������������� ���!
*/
public class Solution
{
    public static void main(String[] args)
    {
        String a = "� �������� �� �������� $";
        String b = "/�����";
        int c = 800;
        int d = 700;
        int e = 500;
        System.out.println("���� ����� �����.");
        System.out.println(" ");
        System.out.println(a + c + b + " � ������ ���.");
        c=c+d;
        System.out.println(a + c + b +" �� ������ ���.");
        c=c+d;
        System.out.println(a + c + b +" � ������ ���.");
        c=c+e;
        System.out.println(a + c + b +" � ��������� ���.");
        c=c+e;
        System.out.println(a + c + b +" � ����� ���.");
        System.out.println(" ");
        System.out.println("������� ��� ��������� ������������ ���!");//�������� ��� ��� ���
    }
}