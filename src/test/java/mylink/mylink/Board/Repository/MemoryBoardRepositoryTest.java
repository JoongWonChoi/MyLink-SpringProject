package mylink.mylink.Board.Repository;

import mylink.mylink.Board.domain.Board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;


class MemoryBoardRepositoryTest {
    MemoryBoardRepository mbr = new MemoryBoardRepository();
    @AfterEach
    public void clearRepo() {
        mbr.clear();
    }
    @Test

    void save() {
        //given
        //board(게시물) 정보 생성
        Board board = new Board();
        board.setName("jwc");
        board.setAge(25);
        board.setSex("M");
        board.setDepartment("SW");
        board.setTitle("Test");
        board.setBody("Hello this is test case");

        //when
        //게시물 정보 저장
        mbr.save(board);

        //then
        Optional<Board> test = mbr.findByTitle("Test");
        assertThat(test).isEqualTo("jwc");
    }

    @Test
    void findByTitle() {
    }
}