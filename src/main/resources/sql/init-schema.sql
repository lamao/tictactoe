create table state (
  id bigint primary key,
  code varchar(50) unique not null,
  title varchar(255) not null
);

create table game (
  id bigint primary key,
  state_id bigint not null,
  title varchar(255) not null,
  snapshot varchar(9) not null,
  foreign key (state_id) references state (id)
);
