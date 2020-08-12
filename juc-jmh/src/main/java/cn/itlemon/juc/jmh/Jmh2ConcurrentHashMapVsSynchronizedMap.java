package cn.itlemon.juc.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 使用JMH对比ConcurrentHashMap Vs SynchronizedMap
 *
 * @author jiangpingping
 * @date 2020/8/12 16:36
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(5)
@State(Scope.Benchmark)
public class Jmh2ConcurrentHashMapVsSynchronizedMap {

    private Map<Long, Long> concurrentMap;
    private Map<Long, Long> synchronizedMap;

    @Setup
    public void setUp() {
        concurrentMap = new ConcurrentHashMap<>();
        synchronizedMap = Collections.synchronizedMap(new HashMap<>());
    }

    @Benchmark
    public void testConcurrentHashMap() {
        concurrentMap.put(System.nanoTime(), System.nanoTime());
    }

    @Benchmark
    public void testSynchronizedMap() {
        synchronizedMap.put(System.nanoTime(), System.nanoTime());
    }

    public static void main(String[] args) throws RunnerException {
        final Options build = new OptionsBuilder()
                .include(Jmh2ConcurrentHashMapVsSynchronizedMap.class.getSimpleName())
                .build();
        new Runner(build).run();
    }

}
