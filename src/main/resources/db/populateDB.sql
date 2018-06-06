DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 10000;


INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 10000),
  ('ROLE_ADMIN', 10001),
  ('ROLE_USER', 10001);

INSERT INTO restaurants (name, voteCount) VALUES
  ('Шашлычная №1', 0),
  ('Шаурма ВАХ-какой вкусный!', 0);

INSERT INTO meals (date_time, name, price, restaurant_id) VALUES
  (timestamp(now), 'Шаурма в лаваше', 150, 10003),
  (timestamp(now), 'Шаурма в батоне', 160, 10003),
  (timestamp(now), 'Шаурма в пите', 140, 10003),
  ('2018-05-30', 'Шаурма в лаваше', 150, 10003),
  ('2018-05-30', 'Шаурма в батоне', 160, 10003),
  ('2018-05-30', 'Шаурма в пите', 140, 10003),

  (timestamp(now), 'Шашлык-машлык', 200, 10002),
  (timestamp(now), 'Шашлык из Пумбы', 300, 10002),
  (timestamp(now), 'Корейский народный шашлык', 250, 10002),
  ('2018-05-30', 'Шашлык-машлык', 200, 10002),
  ('2018-05-30', 'Шашлык из Пумбы', 300, 10002),
  ('2018-05-30', 'Корейский народный шашлык', 250, 10002);
