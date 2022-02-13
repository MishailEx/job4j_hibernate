import integration.Order;
import integration.OrdersStore;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void del() throws SQLException {
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement(
                     "drop table orders")) {
            pr.execute();
        }
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenUpdateOrderAndFindOrderName() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));
        Order order = store.findByName("name1");
        Order order1 = Order.of("name2", "desc2");
        store.update(order1, order.getId());
        Order order2  = store.findByName("name2");

        assertThat(order.getName(), is("name1"));
        assertThat(order2.getId(), is(1));
    }

    @Test
    public void whenFindById() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));
        Order order = store.findById(1);

        assertThat(order.getId(), is(1));
    }
}