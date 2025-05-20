package com.hanteo.board.domain.board.repository;

import com.hanteo.board.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Optional<Board> findById(Long id);
    List<Board> findByName(String name);
    Optional<Board> findByBoardNumber(int boardNumber);
}
