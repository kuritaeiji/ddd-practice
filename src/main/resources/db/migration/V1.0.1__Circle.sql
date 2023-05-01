create table circle (
  `id` bigint auto_increment primary key,
  `name` varchar(20) not null,
  `owner_id` bigint not null,
  `version` bigint not null,
  index idx_user_id(owner_id),
  index idx_id_version(`id`, `version`)
)