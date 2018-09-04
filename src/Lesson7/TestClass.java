import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;


public class TestClass {
    static void start(Class c) throws RuntimeException, InvocationTargetException, IllegalAccessException {
        Method[] methods = c.getDeclaredMethods();
        //Создаем листы для записи методов ,т.к. могут быть несколько методов с одинаковым приоритетом,
        // а простая сортировка массива по вложенному циклу будет иметь сложность О(n^2),
        //c использованием массива ArrayList сложность уменьшится до О(2n)
        ArrayList<Method>[] mList = new ArrayList[12];  //0 - before, 1-10 соответствуют приоритетам , 11 - after, итого 12 элементов
        for (int i = 0; i < 12; i++) {      //создаем ArrayList для каждого возможного значения приоритета
            mList[i] = new ArrayList<Method>();
        }

        for (Method m: methods) {
            //счетчики для определения двукратного использования
            int countBeforeSuite = 0;
            int countAfterSuite = 0;

            if (m.isAnnotationPresent(BeforeSuite.class)) {
                countBeforeSuite++;
                //выбрасываем если >1
                if (countBeforeSuite == 2) {throw new RuntimeException("Annotation @BeforeSuite is present more than 1x times");}
                mList[0].add(m);
//                hashTableForMethods.put(-1,mList[0]);
            }

            if (m.isAnnotationPresent(AfterSuite.class)) {
                countAfterSuite++;
                //выбрасываем если >1
                if (countAfterSuite == 2) {throw new RuntimeException("Annotation @AfterSuite is present more than 1x times");}
                mList[11].add(m);
//                hashTableForMethods.put(11,mList[10]);
            }

            if (m.isAnnotationPresent(Test.class)) {
                int priority = m.getDeclaredAnnotation(Test.class).priority();
                if (priority < 1 && priority > 10) {throw new RuntimeException("Annotation @Test priority must be 1..10");}
                mList[priority].add(m);
            }
        }

        for (int i = 0; i < 12; i++) {
            while (mList[i].size() != 0) {
                Method m = mList[i].remove(0);
                m.invoke(c);
            }
        }
    }
}
