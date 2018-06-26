package mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @ClassName WordcountReducer
 * @Description TODO
 * @Author liang
 * @Date 2018/6/26 10:19
 * @Version 1.0
 **/
public class WordcountReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    //框架在map处理完成后，将所有key，values对缓存起来，进行分组，然后传递一个组，调用一次reduce方法
    //<hello.{1,1,1,1,1,1,1}>
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0;
        //遍历累加求和
        for(LongWritable value:values){
            count+=value.get();
        }
        //输出这一个单词的统计结果
        context.write(key,new LongWritable(count));

    }
}
