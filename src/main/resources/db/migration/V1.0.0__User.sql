create table user (
  `id` bigint auto_increment primary key,
  `email` varchar(255) unique not null,
  `name` varchar(20) not null,
  `type` char(1) not null,
  `version` bigint not null
)