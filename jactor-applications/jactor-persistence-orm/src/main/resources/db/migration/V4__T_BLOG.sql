CREATE TABLE T_BLOG (
  ID            BIGINT PRIMARY KEY,
  CREATION_TIME TIMESTAMP   NOT NULL,
  CREATED_BY    VARCHAR(50) NOT NULL,
  UPDATED_TIME  TIMESTAMP   NOT NULL,
  UPDATED_BY    VARCHAR(50) NOT NULL,
  CREATED       DATE        NOT NULL,
  TITLE         VARCHAR(50) NOT NULL,
  USER_ID       BIGINT      NOT NULL
);

ALTER TABLE T_BLOG
  ADD CONSTRAINT fk_b_user_id FOREIGN KEY (USER_ID)
REFERENCES T_USER (ID);
