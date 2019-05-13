package aims;


//HirakiTest

import aims.user.domain.UserAwareService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HirakiTest {
    @Autowired
    UserAwareService userAwareService;

    @Autowired
    DataSource dataSource;

//    @Test
//    public void exampleTest() {
//        long startTime = System.currentTimeMillis();
//
//        for (int i = 0; i < 10000; i++) {
//            List<User> users = userAwareService.findAll();
//        }
//
//        long endTime = System.currentTimeMillis();
//
//        long lTime = endTime - startTime;
//        System.out.println("TIME : " + lTime + "(ms)");
//    }

    @Test
    public void helloworld() throws SQLException, InterruptedException {
        Thread[] thread = new Thread[1000];
        Runnable[] run = new Runnable[1000];

        for (int j = 0; j < 1000; j++) {
            run[j] = new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 100; i++) {
                            Connection con = dataSource.getConnection();
                            PreparedStatement psmt = con.prepareStatement("SELECT * FROM TB_USER");
                            ResultSet rs = psmt.executeQuery();
                            //psmt.close();
                            con.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            };
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            thread[i] = new Thread(run[i]);
            thread[i].start();
            thread[i].join();
            System.out.println(i);
        }

        long end = System.currentTimeMillis();
        System.out.println(String.format("dataSource NAME::::: %s   ", dataSource));
        System.out.println(String.format("TIME %d", end - start));
    }
}