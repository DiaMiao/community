create table USER
(
    ID           INTEGER  auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    TOKEN        CHAR(36),
    NAME         VARCHAR(50),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);