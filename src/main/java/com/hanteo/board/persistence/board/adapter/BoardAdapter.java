package com.hanteo.board.persistence.board.adapter;

import com.hanteo.board.domain.board.Board;
import com.hanteo.board.domain.board.repository.BoardRepository;
import com.hanteo.board.persistence.board.entity.BoardEntity;
import com.hanteo.board.persistence.board.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardAdapter implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    @Override
    public Optional<Board> findById(Long id) {
        return boardJpaRepository.findById(id)
                .map(BoardEntity::toDomain);
    }

    @Override
    public List<Board> findByName(String name) {
        return boardJpaRepository.findByName(name).stream()
                .map(BoardEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Board> findByBoardNumber(int boardNumber) {
        return boardJpaRepository.findByBoardNumber(boardNumber)
                .map(BoardEntity::toDomain);
    }
}
