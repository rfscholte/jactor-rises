INSERT INTO T_ADDRESS (ID, CREATED_BY, CREATION_TIME, UPDATED_BY, UPDATED_TIME, ADDRESS_LINE_1, ZIP_CODE, CITY)
VALUES (999999, 'jactor', CURRENT_TIMESTAMP, 'jactor', CURRENT_TIMESTAMP, 'Haganjordet 1', 1351, 'RUD');

INSERT INTO T_PERSON (ID, CREATED_BY, CREATION_TIME, UPDATED_BY, UPDATED_TIME, FIRST_NAME, SURNAME, ADDRESS_ID)
VALUES (999998, 'jactor', CURRENT_TIMESTAMP, 'jactor', CURRENT_TIMESTAMP, 'Tor Egil', 'Jacobsen', 999999);

INSERT INTO T_PERSON (ID, CREATED_BY, CREATION_TIME, UPDATED_BY, UPDATED_TIME, FIRST_NAME, SURNAME, ADDRESS_ID)
VALUES (999999, 'jactor', CURRENT_TIMESTAMP, 'jactor', CURRENT_TIMESTAMP, 'Suthatip', 'Jacobsen', 999999);

INSERT INTO T_USER (ID, CREATED_BY, CREATION_TIME, UPDATED_BY, UPDATED_TIME, USER_NAME, EMAIL, PERSON_ID, INACTIVE)
VALUES (
  999998, 'jactor', CURRENT_TIMESTAMP, 'jactor', CURRENT_TIMESTAMP, 'jactor', 'tor.egil.jacobsen@gmail.com', 999998,
  false
);

INSERT INTO T_USER (ID, CREATED_BY, CREATION_TIME, UPDATED_BY, UPDATED_TIME, USER_NAME, EMAIL, PERSON_ID, INACTIVE)
VALUES (
  999999, 'jactor', CURRENT_TIMESTAMP, 'jactor', CURRENT_TIMESTAMP, 'tip', 'suthatip.jacobsen@gmail.com', 999999,
  false
);
