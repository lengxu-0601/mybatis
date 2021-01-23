package cn.tedu;

import cn.tedu.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    @Test
    public void testInsert() throws IOException {
        //1.定义mybatis主配置文件名称,从类路径的根开始(target/classes)
        String config="mybatis.xml";
        //2.读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        SqlSessionFactory factory = builder.build(in);

        SqlSession sqlSession = factory.openSession();

        String sqlId = "cn.tedu.dao.StudentDao.insertStudent";
        Student student = new Student();
        student.setId(3);
        student.setName("关羽");
        student.setEmail("guanyu@168.com");
        student.setAge(25);

        int nums =  sqlSession.insert(sqlId,student);

        sqlSession.commit();

        System.out.println("执行insert的结果是="+nums);

        sqlSession.close();
    }
}
