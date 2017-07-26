create table state (
  st_id bigint primary key,
  st_code varchar(50) unique not null,
  st_title varchar(255) not null
);

create table location (
  lc_id bigint primary key,
  lc_x bigint not null,
  lc_y bigint not null
);

create table game (
  gm_id bigint primary key,
  gm_state_id bigint not null,
  gm_title varchar(255) not null,
  gm_last_turn_id bigint,
  gm_snapshot varchar(9) not null,
  foreign key (gm_state_id) references state (st_id),
  foreign key (gm_last_turn_id) references location (lc_id)

);
