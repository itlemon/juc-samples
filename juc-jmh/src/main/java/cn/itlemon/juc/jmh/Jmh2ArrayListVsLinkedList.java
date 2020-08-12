package cn.itlemon.juc.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用JMH工具对比ArrayList和LinkedList性能
 *
 * @author jiangpingping
 * @date 2020/8/12 13:45
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class Jmh2ArrayListVsLinkedList {

    private final static String DATA = "DUMMY DATA";

    private List<String> arrayList;

    private List<String> linkedList;

    @Setup(Level.Invocation)
    public void setUp() {
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
    }

    /**
     * 基准测试方法，必须使用注解 @Benchmark 进行标注
     *
     * @return 测试数据
     */
    @Benchmark
    public List<String> arrayListAdd() {
        arrayList.add(DATA);
        return arrayList;
    }

    @Benchmark
    public List<String> linkedListAdd() {
        linkedList.add(DATA);
        return linkedList;
    }

    public static void main(String[] args) throws RunnerException {
        final Options build = new OptionsBuilder().include(Jmh2ArrayListVsLinkedList.class.getSimpleName())
                .forks(1)
                // 度量执行的批次为5，也就是说在这10个批次中，对基准方法的执行与调用将会纳入统计，可以在类和方法上使用注解@Measurement(iterations = 10)
                .measurementIterations(10)
                // 在真正的度量之前，首先会对代码进行10个批次的热身，使代码的运行达到JVM已经优化的状态，可以在类和方法上使用注解@Warmup(iterations = 10)
                .warmupIterations(10)
                .build();
        new Runner(build).run();
    }

}
