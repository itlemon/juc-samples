package cn.itlemon.juc.jmh;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ArrayList和LinkedList性能对比
 *
 * @author jiangpingping
 * @date 2020/8/12 10:21
 */
public class ArrayListVsLinkedList {

    private final static String DATA = "DUMMY DATA";

    private final static int MAX_CAPACITY = 1000000;

    private final static int MAX_ITERATIONS = 10;

    private static void test(List<String> list) {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            list.add(DATA);
        }
    }

    private static void arrayListPerfTest(int iterations) {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            final List<String> list = new ArrayList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private static void linkedListPerfTest(int iterations) {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            final List<String> list = new LinkedList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

    public static void main(String[] args) {
        arrayListPerfTest(MAX_ITERATIONS);
        System.out.println(Strings.repeat("#", 20));
        linkedListPerfTest(MAX_ITERATIONS);
    }

}
