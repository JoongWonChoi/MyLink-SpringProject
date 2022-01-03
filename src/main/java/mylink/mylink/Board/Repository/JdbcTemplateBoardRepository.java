package mylink.mylink.Board.Repository;

import mylink.mylink.Board.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateBoardRepository implements BoardRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateBoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Board save(Board board) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("index"); //board 테이블에 primary key ('index')

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", board.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        board.setIndex(key.longValue());

        return board;
    }

    @Override
    public Optional<Board> findByTitle(String keyword) {
        List<Board> result = jdbcTemplate.query("select * from board where title = ?", memberRowMapper(), keyword);
        return result.stream().findAny();
    }

    @Override
    public List<Board> findAllBoards() {
        List<Board> result = jdbcTemplate.query("select * from board", memberRowMapper());
        return result;
    }

    private RowMapper<Board> memberRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setIndex(rs.getLong("index"));
            board.setName(rs.getString("name"));
            board.setTitle(rs.getString("title"));
            board.setAge(rs.getInt("age"));
            board.setSex(rs.getString("sex"));
            board.setBody(rs.getString("body"));
            board.setDepartment(rs.getString("department"));
            return board;
        };
    }
}
