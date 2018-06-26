package mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * @ClassName WordcountRunner
 * @Description TODO
 * 用来描述一个特定的作业
 * 可以指定那个类作为逻辑处理的map，reduce
 * 指定要处理的数据所在的路径
 * 指定改作业输出的结果什么路径下
 * @Author liang
 * @Date 2018/6/26 11:14
 * @Version 1.0
 **/
public class WordcountRunner {
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置整个job所用的类在哪个jar包下
        job.setJarByClass(WordcountRunner.class);
        //指定map和reduce的类
        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReducer.class);
        //指定reduce输出数据的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        //指定mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        //指定原始数据存放的位置,输入数据
        FileInputFormat.setInputPaths(job,new Path("/wc/srcdata"));
        //处理结果，输出数据
        FileOutputFormat.setOutputPath(job,new Path("/wc/output"));
        //提交到集群运行
        job.waitForCompletion(true);
    }
}
