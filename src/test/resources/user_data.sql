DELETE FROM app_user WHERE username IN ('user1', 'user2');


INSERT INTO app_user (id, username, password, roles, created_at, modified_at) VALUES
(10000,'user1', '{noop}password1', 'USER', NOW(), NOW()),
(10001,'user2', '{noop}password2', 'USER', NOW(), NOW());