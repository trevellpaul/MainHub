ALTER TABLE CD_MUSICIAN DROP CONSTRAINT CD_MUSICIAN_CD_ID
ALTER TABLE CD_MUSICIAN DROP CONSTRAINT CDMSICIANmsciansID
DROP TABLE CD
DROP TABLE MUSICIAN
DROP TABLE ITEM
DROP TABLE CD_MUSICIAN
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'