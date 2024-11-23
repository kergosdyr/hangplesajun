
DELETE FROM post WHERE id IN (3, 4);


INSERT INTO post (id, user_id, title, content, created_at, modified_at) VALUES
(3, 1, 'First Post', 'This is the content of the first post', '2023-01-01 00:00:00', '2023-01-01 00:00:00'),
(4, 2, 'Second Post', 'This is the content of the second post', '2023-01-02 00:00:00', '2023-01-02 00:00:00');