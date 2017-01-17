package com.javarush.test.level01.lesson06.task02_test;

/*  онтракт
¬ывести на экран текст нового контракта:
ћен€ зовут јмиго.

я согласен на зарплату $800/мес€ц в первый год.
я согласен на зарплату $1500/мес€ц во второй год.
я согласен на зарплату $2200/мес€ц в третий год.
я согласен на зарплату $2700/мес€ц в четвертый год.
я согласен на зарплату $3200/мес€ц в п€тый год.

ѕоцелуй мой блест€щий металлический зад!
*/
public class Solution
{
    public static void main(String[] args)
    {
        String a = "я согласен на зарплату $";
        String b = "/мес€ц";
        int c = 800;
        int d = 700;
        int e = 500;
        System.out.println("ћен€ зовут јмиго.");
        System.out.println(" ");
        System.out.println(a + c + b + " в первый год.");
        c=c+d;
        System.out.println(a + c + b +" во второй год.");
        c=c+d;
        System.out.println(a + c + b +" в третий год.");
        c=c+e;
        System.out.println(a + c + b +" в четвертый год.");
        c=c+e;
        System.out.println(a + c + b +" в п€тый год.");
        System.out.println(" ");
        System.out.println("ѕоцелуй мой блест€щий металический зад!");//напишите тут ваш код
    }
}