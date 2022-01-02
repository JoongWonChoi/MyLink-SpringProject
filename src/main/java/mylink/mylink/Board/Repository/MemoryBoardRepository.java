package mylink.mylink.Board.Repository;

import mylink.mylink.Board.domain.Board;

import java.util.HashMap;
import java.util.Optional;

public class MemoryBoardRepository implements BoardRepository {
    HashMap<Long, Board> store = new HashMap<>();
    private static long index = 0L; // key값 생성

    @Override
    public Board save(Board board) {
        board.setIndex(++index);
        store.put(index, board);
        return board;
    }

    @Override
    //전달받은 keyword를 포함하는 title을 가진 게시물 반환
    public Optional<Board> findByTitle(String keyword) {
        return store.values().stream().filter(board -> board.getTitle().equals(keyword)) //board의 getName이 파라미터로 넘어온 name과 같으면 반환
                .findAny(); //Map의 get메서드의 인자는 key이어야함.
        // ==> key,value 설정 시 key는 중복되지 않는 primary key여야함.
        //     따라서 고유index를 설정하고, 이를 통해 검색하는 방식을 사용
        //     하지만 title로 검색을 시도하려면 key값으로 String을 넣어줬어야 함.
    }

    public void clear() {
        store.clear();
    }
}
