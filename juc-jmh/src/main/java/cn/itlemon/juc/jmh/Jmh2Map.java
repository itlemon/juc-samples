package cn.itlemon.juc.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

/**
 * 使用注解@Param来优化Jmh2ConcurrentHashMapVsSynchronizedMap
 *
 * @author jiangpingping
 * @date 2020/8/12 17:18
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(5)
@State(Scope.Benchmark)
public class Jmh2Map {

    @Param({"1", "2", "3", "4"})
    private int type;

    private Map<Long, Long> map;

    @Setup
    public void setUp() {
        switch (type) {
            case 1:
                map = new ConcurrentHashMap<>();
                break;
            case 2:
                map = new ConcurrentSkipListMap<>();
                break;
            case 3:
                map = new Hashtable<>();
                break;
            case 4:
                map = Collections.synchronizedMap(new HashMap<>());
                break;
            default:
                throw new IllegalArgumentException("Illegal map type.");
        }
    }

    @Benchmark
    public void test() {
        map.put(System.nanoTime(), System.nanoTime());
    }

    public static void main(String[] args) throws RunnerException {
        final Options build = new OptionsBuilder()
                .include(Jmh2Map.class.getSimpleName())
                .build();
        new Runner(build).run();
    }

}
