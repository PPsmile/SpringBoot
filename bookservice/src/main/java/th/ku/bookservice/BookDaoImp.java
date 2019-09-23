package th.ku.bookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class BookDaoImp implements BookDao{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        String query = "INSERT INTO bookstore (id, name, price) VALUES (?, ?, ?);";
        Object[] data = new Object[]
                { book.getId(), book.getName(), book.getPrice() };
        jdbcTemplate.update(query, data);
    }

    public void update(int id, Book book) {
        // TODO: add code to update book
    }

    public void deleteById(int id) {
        // TODO: add code to delete book
    }

    public Book findById(int id) {
        String query = "SELECT * FROM bookstore WHERE id = " + id;
        Book book = jdbcTemplate.queryForObject(query, new BookRowMapper());
        return book;
    }

    public List<Book> findAll() {
        String query = "SELECT * FROM bookstore";
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;
    }
    class BookRowMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Book book = new Book(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"));
            return book;
        }
    }
}
