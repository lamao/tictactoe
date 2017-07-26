insert into state (id, code, title) values
(1, 'IN_PROGRESS', 'In progress'),
(2, 'X_WON', 'X won'),
(3, 'O_WON', 'O won'),
(4, 'DRAW', 'Draw');

insert into game (id, state_id, title, snapshot) values
(1, 1, 'First game',     '         '),
(2, 1, 'The second one', ' o x o x '),
(3, 1, 'The third',      'o   x    ');