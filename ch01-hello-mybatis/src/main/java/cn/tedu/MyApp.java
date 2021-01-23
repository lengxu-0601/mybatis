package cn.tedu;

import cn.tedu.entity.Student;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws IOException {
        //访问mybatis读取student数据
        //1.定义mybatis主配置文件名称,从类路径的根开始(target/classes)
        String config="mybatis.xml";
        //2.读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        SqlSessionFactory factory = builder.build(in);

        SqlSession sqlSession = factory.openSession();

        //dao类的全限定名.xml标签里的sql语句标签的id
        String sqlId = "cn.tedu.dao.StudentDao"+"."+"selectStudents";

        List<Student> studentList = sqlSession.selectList(sqlId);
        for (Student stu : studentList){
            System.out.println("查询结果:"+stu);
        }
        sqlSession.close();
    }
}
