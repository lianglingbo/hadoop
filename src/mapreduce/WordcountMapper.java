package mapreduce;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;

/**
 * @ClassName wordcount
 * @Description TODO
 * @Author liang
 * @Date 2018/6/26 10:18
 * @Version 1.0
 **/
public class WordcountMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
    //每读一次数据掉一次此方法
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //具体业务逻辑写在这，要处理的数据已经被框架传输进来
        //key 是这样一行数据的起始偏移量
        //value是这一行的文本内容
        String line = value.toString();
        if(line.indexOf("{") != -1 && line.indexOf("}") != -1 ){
            String json  = line.substring(line.indexOf("{"));

            context.write(new Text(json),new LongWritable(1));

        }
//        String[] words = StringUtils.split(line, " ");
//        for (String word:words) {
//            //拿到一个单词发送出去
//            //context.write(new Text(word),new LongWritable(1));
//        }
    }
}
