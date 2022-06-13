create table chat_messages
(
    id             varchar(50) primary key,
    chat_id        varchar(100) not null,
    sender_id      varchar(50)  not null,
    recipient_id   varchar(50)  not null,
    sender_name    varchar(100),
    recipient_name varchar(100),
    content        varchar(500),
    datetime       timestamp,
    status         varchar(50)
);

create table chat_rooms
(
    id           varchar(50) primary key,
    chat_id      varchar(100) not null,
    sender_id    varchar(50)  not null,
    recipient_id varchar(50)  not null
);