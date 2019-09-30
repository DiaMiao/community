alter table QUESTION alter column ID bigint default NOT NULL;
alter table user alter column ID bigint default NOT NULL;
alter table QUESTION alter column creator bigint default NOT NULL;
alter table comment alter column commentator bigint default NOT NULL;