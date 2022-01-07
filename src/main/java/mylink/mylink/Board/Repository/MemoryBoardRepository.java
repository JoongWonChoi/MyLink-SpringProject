package mylink.mylink.Board.Repository;

import mylink.mylink.Board.domain.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MemoryBoardRepository implements BoardRepository {
    HashMap<Long, Board> store = new HashMap<>();
    private static long index = 0L; // key값 생성

    @Override
    //게시물 정보 단순 저장
    public Board save(Board board) {
        board.setIndex(++index);
        store.put(index, board);
        return board;
    }

    @Override
    public Board read(Board board) {
        return null;
    }

    @Override
    public Board delete(Board board) {
        return null;
    }

    @Override
    public Board update(Board board) {
        return null;
    }

    @Override
    //전달받은 keyword를 포함하는 title을 가진 게시물 반환
    public Optional<Board> findByTitle(String keyword) {
        return store.values().stream().filter(board -> board.getTitle().equals(keyword)) //board의 getName이 파라미터로 넘어온 name과 같으면 반환
                .findAny(); //Map의 get메서드의 인자는 key이어야함.
        // ==> key,value 설정 시 key는 중복되지 않는 primary key여야함.
        //     따라서 고유index를 설정하고, 이를 통해 검색하는 방식을 사용
        //     하지만 title로 검색을 시도하려면 key값으로 String을 넣어 줬어야 함.
    }
    //해쉬에 저장되어있는 게시물 정보들(k,v)를 리스트 형태로 반환
    public List<Board> findAllBoards(){
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }
}
