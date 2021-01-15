import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RCal {
        public static void main(String[] args) {
            String mexp;
                /* Строковая переменная mexp (math expression) для хранения
                данных которые ввел пользователь (математическое выражение) */
            String[] numsArr;
                /* Массив, служит для хранения чисел которые ввел пользователь
                в строковом формате*/
            String ars = "";
                /* Строковая перменная ars (ariphmetic sign) для храние арифметического
                оператора */
            int[] ArNum = new int[2];
                /* Массив, служит для хранения чисел которые ввел пользователь*/
            int res = 0;
              /* Переменная для хранения результата выполнения арифметической
               операции */
            List<RomeNum> RN = RomeNum.getReverseSortedValues();
            /* Список отсортированных элементов ENUMa */

            boolean type = false;
            /* Булева переменная для определения типа числа (арабка или римка */


            // НАЧИНАЕТСЯ ТЕЛО ПРОГРАММЫ!!!

            Scanner input = new Scanner(System.in);
                // Создаем объект класса для ввода данных
            System.out.print("Введите выражение: ");
                // Приветствие
            mexp = input.nextLine().replace(" ", "");
                // Удаляем лишние пробелы, где-бы они не были
            numsArr = num(mexp);
                /* Метод num() выдает строковый массив чисел которые были
                введены пользователем */
            ars = ars(mexp);
                /* Метод ars() выдает строку символа арифмет. оператора */

            int sum = 0;
            int j = 0;
            for (int i = 0; i < numsArr.length; i++) {
                char[] sym = numsArr[i].toCharArray();
                type = Character.isDigit(sym[0]);
                while ((numsArr[i].length() > 0) && (j < RN.size())) {
                    RomeNum symbl = RN.get(j);
                    if (numsArr[i].startsWith(symbl.name())) {
                        ArNum[i] += symbl.getValue();
                        numsArr[i] = numsArr[i].substring(symbl.name().length());
                    } else {
                        j++;
                    }
                }
                if (type) {
                    ArNum[i] = Integer.parseInt(numsArr[i]);
                }
            }


            res = mOper(ars, ArNum);
             /* Передача в метод mOper() (math operation) чисел и
              значения арифметического оператора, вычисление */
            int i =0;
            StringBuilder buf = new StringBuilder();
            if (type) {
                      buf.append(Integer.toString(res));
            }
            else {
                while (res > 0 && (i < RN.size())){
                    RomeNum oSym = RN.get(i);
                    if(res >= oSym.getValue()){
                        buf.append(oSym.name());
                        res = res - oSym.getValue();
                    }
                    else { i++ ;}
              }
            }

            System.out.println(buf);
        }


            // Блок используемых методов
            public static String ars(String x) {
                String[] ars = {"+", "-", "*", "/"};
                String s = "";

                for (int i = 0; i <= ars.length - 1; i++) {
                    if (x.indexOf(ars[i]) > 0) {
                        s = ars[i];
                    }
                }
                return s;
            }
            //Метод ars() извлекает из строки арифметический оператор


            public static String[] num(String x) {
                return x.split("[+*/-]");
            }
            //Метод num() извлекает из строки числа арабские или римские


            public static int mOper(String ars, int[] num) {
                int res =12;

                switch (ars) {
                    case "+":
                        res = num[0] + num[1];
                        break;
                    case "-":
                        res = num[0] - num[1];
                        break;
                    case "*":
                        res = num[0] * num[1];
                        break;
                    case "/":
                        res = num[0] / num[1];
                        break;
                }

                return res;
            }
            //Метод mOper() выполняет арифметические операции над числами



}
