package by.it.kuzmichalex.jd01_05;

import static java.lang.Math.*;

/**
 * Класс для тестового задания C
 */
public class TaskC {
    public static void main(String[] args) {
        double[] arr = createArray(27);
        printArray(arr, "A", 5);
        double geomMean = getGeometricMean(arr);

        System.out.println("Среднее геометрическое " + geomMean);
        runTask7();
    }

    /**
     * Вывод элементов массива с применением printf c указанием имени элемента массива
     * Вывод производится по строкам с указанным количеством колонок
     * @param inArr       массив, который надо вывести
     * @param name        имя, которе будет выводиться перед каждым элементом массива
     * @param columnCount Количество элементов массива, которые будут выведены в одну строку. т.е количество колонок.
     */
    private static void printArray(double[] inArr, String name, int columnCount) {
        for (int i = 0; i < inArr.length; i++) {
            System.out.printf("%s[%d]=%f ", name, i, inArr[i]);
            if ((i + 1) % columnCount == 0) {
                System.out.println();
            }
        }
        if ((inArr.length % columnCount != 0) && (columnCount > 1)) {
            System.out.println();
        }
    }

    /**
     * Задание 7, с псевдографикой
     **/
    private static void runTask7() {
        double[] arrayA;
        double[] arrayB;
        arrayA = new double[31];
        //Формируем массив A
        for (int i = 0; i < arrayA.length; i++) {
            arrayA[i] = round(103.00 + (random() * (450.0 - 103.0 + 1.0)));
        }
        //Формируем массив B. Для начала подсчитаем, сколько элементов массива A больше своих индексов
        int lengthOfArrayB = 0;
        for (int i = 0; i < arrayA.length; i++) {
            if ((arrayA[i] / 10) > (double) i) lengthOfArrayB++;
        }
        //А потом заведём массив B и засунем в него подмножестово массива A.
        arrayB = new double[lengthOfArrayB];
        int indexForArrayB = 0;
        for (int i = 0; i < arrayA.length; i++) {
            if ((arrayA[i] / 10) > (double) i){arrayB[indexForArrayB++]=arrayA[i];}
        }
        //Ну наконец-то! Нконец-то! Ах, как я ждал этой функции!
        printArrayBeautifully(arrayA,5,true);

        printArrayBeautifully(arrayB,2,true);

    }
    /**
     * печать массива в прекрасном псевдографическом виде, с блекджеком и мадемуазелями!
     * надеюсь, я не наделал ошибок в слове beautifully?
     * @param inArray  массив, который надо напечатать
     * @param countOfColumns - во сколько колонок печатать
     * @param horizontal true для печати в горизонталь. false для китайского вертикального массива
     * */
    private static void printArrayBeautifully(double[] inArray, int countOfColumns, boolean horizontal) {
        final int lengthOfField=10;
        int arrayCounter=0; //Счётчик 0 - длина массива. Но это не индекс!
        int arrayIndex=0;   //Индекс. Вычисляется из счётчика.
        String strStart;    //псевдографическая строка, начинающая массив
        String strMiddle;   //псевдографическая строка, разделяющая строки
        String strFinish;   //псевдографическая строка, завершающая строки
        strStart= formPeriodicString("╔","╦","╗",countOfColumns,lengthOfField);
        strMiddle= formPeriodicString("╠","╬","╣",countOfColumns,lengthOfField);
        strFinish= formPeriodicString("╚","╩","╝",countOfColumns,lengthOfField);

        System.out.println(strStart);       //Стартовая строка
        do {
            System.out.print("║");          //Начинаем выводить строоку значений
            for (int i = 0; i < countOfColumns; i++) {
                if (arrayCounter >= inArray.length) {
                    for (int j = 0; j < lengthOfField; j++) System.out.print(" ");
                } else {
                    if(horizontal==true)arrayIndex=arrayCounter;    //Для горизонтальных индекс массива=счётчику
                    else{

                    }
                    System.out.printf("%10.2f", inArray[arrayIndex]);
                    arrayCounter++;
                }

                System.out.print("║");
            }
            System.out.println();
            if (arrayCounter >= inArray.length) System.out.println(strFinish);
            else System.out.println(strMiddle);
            //Закончили с выводом строки
        }while(arrayCounter<inArray.length);
    }

    /**
     * Формиривание строки A====B====C
     * @param startSequence A
     * @param middleSequence B
     * @param finishSequence С
     * @param countOfElements количество элементов ====
     * @param lengthOfElement длина элемента =
     * @return Строка A====B====C. или A=====C для countOfElements==1
     * */
    private static String formPeriodicString(String startSequence, String middleSequence, String finishSequence, int countOfElements, int lengthOfElement) {
        String retString=new String();

        retString = startSequence;

        for (int i = 0; i <countOfElements ; i++) {
            for (int j = 0; j <lengthOfElement ; j++) {
                retString +="═";
            }
            if(i==(countOfElements-1))retString +=finishSequence;
            else{retString +=middleSequence;}
        }
        return retString;
    }

    /**
     * Вычисление среднего геометрического
     * @param arr массив чисел, по которым рассчитываем среднее геометрическое
     * @return среднее геометрическое
     */
    private static double getGeometricMean(double[] arr) {
        double retValue = 0;
        for (int i = 0; i < arr.length; i++) {
            retValue += log10(arr[i]);
        }
        return retValue;
    }

    /**
     * Формирование массива  double c заданнным количеством элементов
     * Элементы массива - значения функции при диапазоне аргументов функции [5.33, 9]
     * @param countOfElements количество элементов массива,который надо построить
     * @return массив
     */
    private static double[] createArray(int countOfElements) {
        double x;
        double deltaX;
        if ((countOfElements < 20) || (countOfElements > 40)) {
            System.out.println("размерность массива не предусмотрена заданием! Вы чо!?");
            return null;
        }
        x = 5.33;
        deltaX = (9.0 - 5.33) / (double) (countOfElements - 1);
        double[] returnArr = new double[countOfElements];
        for (int i = 0; i < countOfElements; i++) {
            //Из-за ошибок втискитвания вещественного числа в double точно на 9 можем и не выйти.
            if (i == (countOfElements - 1)) x = 9;
            returnArr[i] = cbrt(pow(x, 2) + 4.5);
            //System.out.println("i:" + i + " X:" + x + " z:" + returnArr[i]);
            x += deltaX;
        }
        return returnArr;
    }
}
