INSERT INTO tag (name,priority) VALUES ('校园卡',1);
INSERT INTO tag (name,priority) VALUES ('失物招领',0);
INSERT INTO tag (name,priority) VALUES ('寻物启事',0);
INSERT INTO role VALUES (1,'developer');

INSERT INTO role VALUES (2,'admin');

INSERT INTO role VALUES (3,'user');
/* INSERT INTO user (njuid,role_id) VALUES ('181860055',1); */

ALTER TABLE item MODIFY COLUMN created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;
INSERT INTO item (created_by, description, method, state) VALUES (1,'315丢了钥匙','去找宿管要','finish');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','finish');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','finish');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','finish');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','finish');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','finish');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','finish');
INSERT INTO item (created_by, description, method, state) VALUES (1,'316丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'317丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'318丢了钥匙','去找宿管要','wait');
INSERT INTO item (created_by, description, method, state) VALUES (1,'319丢了钥匙','去找宿管要','wait');
INSERT INTO items_tags (item_id, tag_id) VALUES (1, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (1, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (2, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (2, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (3, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (3, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (4, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (4, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (5, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (6, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (7, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (8, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (9, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (10, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (11, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (12, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (13, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (14, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (15, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (16, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (17, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (18, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (19, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (20, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (21, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (22, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (23, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (24, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (25, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (26, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (27, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (28, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (29, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (30, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (31, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (32, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (33, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (34, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (35, 2);
INSERT INTO items_tags (item_id, tag_id) VALUES (36, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (37, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (38, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (39, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (40, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (41, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (42, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (43, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (44, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (45, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (46, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (47, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (48, 3);
INSERT INTO items_tags (item_id, tag_id) VALUES (49, 3);



INSERT INTO image (id, url, item_id) VALUES (1, "https://cbu01.alicdn.com/img/ibank/2016/597/960/3694069795_1624996386.jpg", 1);
INSERT INTO image (id, url, item_id) VALUES (2, "https://cbu01.alicdn.com/img/ibank/2016/597/960/3694069795_1624996386.jpg", 2);
INSERT INTO image (id, url, item_id) VALUES (3, "https://cbu01.alicdn.com/img/ibank/2016/597/960/3694069795_1624996386.jpg", 3);
INSERT INTO image (id, url, item_id) VALUES (4, "https://cbu01.alicdn.com/img/ibank/2016/597/960/3694069795_1624996386.jpg", 4);
ALTER TABLE post MODIFY COLUMN created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;
INSERT INTO post (id, title, content) VALUES (1, "[公告]小程序使用指南看这里！","问题的关键究竟为何？ 郭沫若说过一句富有哲理的话，形成天才的决定因素应该是勤奋。这似乎解答了我的疑惑. 既然如何， 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 我们不得不面对一个非常尴尬的事实，那就是， 这样看来， 这种事实对本人来说意义重大，相信对这个世界也是有一定意义的。 我们不得不面对一个非常尴尬的事实，那就是， 富兰克林曾经提到过，你热爱生命吗？那么别浪费时间，因为时间是组成生命的材料。我希望诸位也能好好地体会这句话")
INSERT INTO post (id, title, content) VALUES (2, "[公告]FindIt小程序发布啦！","问题的关键究竟为何？ 郭沫若说过一句富有哲理的话，形成天才的决定因素应该是勤奋。这似乎解答了我的疑惑. 既然如何， 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 我们不得不面对一个非常尴尬的事实，那就是， 这样看来， 这种事实对本人来说意义重大，相信对这个世界也是有一定意义的。 我们不得不面对一个非常尴尬的事实，那就是， 富兰克林曾经提到过，你热爱生命吗？那么别浪费时间，因为时间是组成生命的材料。我希望诸位也能好好地体会这句话")
INSERT INTO post (id, title, content) VALUES (3, "[公告]团队招募！速来！","问题的关键究竟为何？ 郭沫若说过一句富有哲理的话，形成天才的决定因素应该是勤奋。这似乎解答了我的疑惑. 既然如何， 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 我们不得不面对一个非常尴尬的事实，那就是， 这样看来， 这种事实对本人来说意义重大，相信对这个世界也是有一定意义的。 我们不得不面对一个非常尴尬的事实，那就是， 富兰克林曾经提到过，你热爱生命吗？那么别浪费时间，因为时间是组成生命的材料。我希望诸位也能好好地体会这句话")
INSERT INTO post (id, title, content) VALUES (4, "[测试1]小程序使用指南看这里！","问题的关键究竟为何？ 郭沫若说过一句富有哲理的话，形成天才的决定因素应该是勤奋。这似乎解答了我的疑惑. 既然如何， 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 我们不得不面对一个非常尴尬的事实，那就是， 这样看来， 这种事实对本人来说意义重大，相信对这个世界也是有一定意义的。 我们不得不面对一个非常尴尬的事实，那就是， 富兰克林曾经提到过，你热爱生命吗？那么别浪费时间，因为时间是组成生命的材料。我希望诸位也能好好地体会这句话")
INSERT INTO post (id, title, content) VALUES (5, "[测试2]小程序使用指南看这里！","问题的关键究竟为何？ 郭沫若说过一句富有哲理的话，形成天才的决定因素应该是勤奋。这似乎解答了我的疑惑. 既然如何， 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 我们不得不面对一个非常尴尬的事实，那就是， 这样看来， 这种事实对本人来说意义重大，相信对这个世界也是有一定意义的。 我们不得不面对一个非常尴尬的事实，那就是， 富兰克林曾经提到过，你热爱生命吗？那么别浪费时间，因为时间是组成生命的材料。我希望诸位也能好好地体会这句话")
INSERT INTO post (id, title, content) VALUES (6, "[测试3]小程序使用指南看这里！","问题的关键究竟为何？ 郭沫若说过一句富有哲理的话，形成天才的决定因素应该是勤奋。这似乎解答了我的疑惑. 既然如何， 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 可是，即使是这样，失物招领的出现仍然代表了一定的意义。 我们不得不面对一个非常尴尬的事实，那就是， 这样看来， 这种事实对本人来说意义重大，相信对这个世界也是有一定意义的。 我们不得不面对一个非常尴尬的事实，那就是， 富兰克林曾经提到过，你热爱生命吗？那么别浪费时间，因为时间是组成生命的材料。我希望诸位也能好好地体会这句话")