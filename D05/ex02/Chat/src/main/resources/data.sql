INSERT INTO chat.users (login, password) VALUES('BOB', '1234');
INSERT INTO chat.users (login, password) VALUES('BOB1', '1234');
INSERT INTO chat.users (login, password) VALUES('BOB2', '1234');
INSERT INTO chat.users (login, password) VALUES('BOB3', '1234');
INSERT INTO chat.users (login, password) VALUES('BOB4', '1234');

INSERT INTO chat.rooms (name, owner) VALUES('ROOM', 1);
INSERT INTO chat.rooms (name, owner) VALUES('ROOM', 2);
INSERT INTO chat.rooms (name, owner) VALUES('ROOM2', 3);
INSERT INTO chat.rooms (name, owner) VALUES('ROOM2', 4);
INSERT INTO chat.rooms (name, owner) VALUES('ROOM3', 4);

INSERT INTO chat.messages (author, room, text, time) VALUES(1, 1, 'room1', '2016-06-22 19:10:25-07');
INSERT INTO chat.messages (author, room, text, time) VALUES(1, 2, 'room2', '2016-06-22 19:10:25-07');
INSERT INTO chat.messages (author, room, text, time) VALUES(1, 2, 'room2', '2016-06-22 19:10:25-07');
INSERT INTO chat.messages (author, room, text, time) VALUES(1, 3, 'room3', '2016-06-22 19:10:25-07');
INSERT INTO chat.messages (author, room, text, time) VALUES(2, 3, 'room3', '2016-06-22 19:10:25-07');
