create table circle_member (
  id bigint auto_increment primary key,
  user_id bigint not null,
  circle_id bigint not null,
  index idx_user_id_circle_id(user_id, circle_id)
)