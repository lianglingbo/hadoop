package hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @ClassName HdfsUtils
 * @Description TODO
 * @Author liang
 * @Date 2018/6/22 17:26
 * @Version 1.0
 **/
public class HdfsUtils {
    FileSystem fs = null;
    @Before
    public void intit() throws Exception {
        //读取classpath下的xx-sit.xml配置文件，并解析其内容。封装到conf对象中
        Configuration conf = new Configuration();
        //也可以在代码中对conf进行手动配置
        //conf.set("fs.defaultFS", "hdfs://192.168.31.168:9000/");
        //根据配置信息，获取一个具体文件系统的客户端操作实例对象，指定用户root
          fs = FileSystem.get(new URI("hdfs://192.168.31.168:9000/"),conf,"root");
    }

    public static void main(String[] args) throws IOException {

    }


    //上传，底层写法
    @Test
    public void upload() throws IOException {

        Path dst = new Path("hdfs://192.168.31.168:9000/aa/ceshi.txt");
        FSDataOutputStream os = fs.create(dst);
        FileInputStream is = new FileInputStream("d:/ceshi.txt");
        IOUtils.copy(is,os);
    }
    //封装好的方法
    @Test
    public void upload2() throws IOException {
        fs.copyFromLocalFile(new Path("d:/ceshi.txt"),new Path("hdfs://192.168.31.168:9000/aa/ceshi2.txt"));
    }


    //下载
    @Test
    public void download() throws IOException {
        fs.copyToLocalFile(false,new Path("/aa/ceshi2.txt"),new Path("D:/download.txt"),true);
    }
    //查看
    @Test
    public void listFiles() throws IOException {
        //查看所有文件，包括文件夹内的文件,提供递归遍历
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/"), true);
        while (files.hasNext()){
            LocatedFileStatus file = files.next();
            Path path = file.getPath();
            String name = path.getName();
            System.out.println("filename:"+name);

        }
        //查看当前目录下的文件和文件夹，可以判断是否为文件
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus status:listStatus) {
            String name = status.getPath().getName();
            System.out.println(name+(status.isDirectory()? "is dir ":"is file "));

        }


    }
    //创建目录
    @Test
    public void mkdir() throws IOException {
        fs.mkdirs(new Path("/a/b/c"));
    }
    //删除
    @Test
    public void deleted() throws IOException {
        fs.delete(new Path("/aa"),true);

    }
}
