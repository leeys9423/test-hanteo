-- 카테고리 데이터 추가
INSERT INTO categories (id, name, level, parent_id) VALUES (1, '남자', 1, NULL);
INSERT INTO categories (id, name, level, parent_id) VALUES (2, '여자', 1, NULL);
INSERT INTO categories (id, name, level, parent_id) VALUES (3, '엑소', 2, 1);
INSERT INTO categories (id, name, level, parent_id) VALUES (4, '방탄소년단', 2, 1);
INSERT INTO categories (id, name, level, parent_id) VALUES (5, '블랙핑크', 2, 2);
INSERT INTO categories (id, name, level, parent_id) VALUES (6, '공지사항', 3, 3);
INSERT INTO categories (id, name, level, parent_id) VALUES (7, '첸', 3, 3);
INSERT INTO categories (id, name, level, parent_id) VALUES (8, '백현', 3, 3);
INSERT INTO categories (id, name, level, parent_id) VALUES (9, '시우민', 3, 3);
INSERT INTO categories (id, name, level, parent_id) VALUES (10, '공지사항', 3, 4);
INSERT INTO categories (id, name, level, parent_id) VALUES (11, '익명게시판', 3, 4);
INSERT INTO categories (id, name, level, parent_id) VALUES (12, '뷔', 3, 4);
INSERT INTO categories (id, name, level, parent_id) VALUES (13, '공지사항', 3, 5);
INSERT INTO categories (id, name, level, parent_id) VALUES (14, '익명게시판', 3, 5);
INSERT INTO categories (id, name, level, parent_id) VALUES (15, '로제', 3, 5);

-- 게시판 데이터 추가
INSERT INTO boards (id, name, board_number) VALUES (1, '공지사항', 1);
INSERT INTO boards (id, name, board_number) VALUES (2, '첸', 2);
INSERT INTO boards (id, name, board_number) VALUES (3, '백현', 3);
INSERT INTO boards (id, name, board_number) VALUES (4, '시우민', 4);
INSERT INTO boards (id, name, board_number) VALUES (5, '공지사항', 5);
INSERT INTO boards (id, name, board_number) VALUES (6, '익명게시판', 6);
INSERT INTO boards (id, name, board_number) VALUES (7, '뷔', 7);
INSERT INTO boards (id, name, board_number) VALUES (8, '공지사항', 8);
INSERT INTO boards (id, name, board_number) VALUES (9, '로제', 9);

-- 카테고리-게시판 매핑 데이터 추가
INSERT INTO category_board (category_id, board_id) VALUES (6, 1);
INSERT INTO category_board (category_id, board_id) VALUES (7, 2);
INSERT INTO category_board (category_id, board_id) VALUES (8, 3);
INSERT INTO category_board (category_id, board_id) VALUES (9, 4);
INSERT INTO category_board (category_id, board_id) VALUES (10, 5);
INSERT INTO category_board (category_id, board_id) VALUES (11, 6);
INSERT INTO category_board (category_id, board_id) VALUES (12, 7);
INSERT INTO category_board (category_id, board_id) VALUES (13, 8);
INSERT INTO category_board (category_id, board_id) VALUES (14, 6);
INSERT INTO category_board (category_id, board_id) VALUES (15, 9);