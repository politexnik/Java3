import java.util.Arrays;

public class Tusk {


    public static void main(String[] args) {
        Tusk tusk = new Tusk();
        int[] arr = {1 , 2 , 3, 1, 5, 6};
        System.out.println(Arrays.toString(tusk.arrOutAfter4(arr)));
    }

    //1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный
    // массив, метод должен вернуть новый массив, который получен путем вытаскивания элементов из
    // исходного массива, идущих после последней четверки. Входной массив должен содержать хотя бы
    // одну четверку, в противном случае в методе необходимо выбросить RuntimeException.
    public int[] arrOutAfter4 (int[] arrIn) {
        int[] arrReturn = new int[0];   //иначе ругается
        Mark: for (int i = arrIn.length-2, k = 1; i >=0 ; i--, k++) {
            if (arrIn[i] == 4) {
                arrReturn = new int[k];
                for (int j = 0; j < k; j++, i++) {
                    arrReturn[j] = arrIn[i+1];
                }
                break Mark;
            }
            if (i==0) {throw new RuntimeException();}; //при проходе до конца массива без обнаружения 4
        }
    return arrReturn;
    }
}