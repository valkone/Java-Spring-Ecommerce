--------------------------------------------------------
--  File created - Monday-April-25-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence PRODUCTS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PRODUCTS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 9 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Table PRODUCTS
--------------------------------------------------------

  CREATE TABLE "PRODUCTS" ("ID" NUMBER, "NAME" VARCHAR2(200), "PRICE" NUMBER(10,0), "IS_ACTIVE" NUMBER(1,0), "PICTURE_URL" VARCHAR2(255), "DESCRIPTION" VARCHAR2(255), "USER_ID" NUMBER, "CATEGORY_ID" NUMBER, "QUANTITY" NUMBER(10,0), "DATE_ADDED" DATE)
--------------------------------------------------------
--  DDL for Table PRODUCTS_CATEGORIES
--------------------------------------------------------

  CREATE TABLE "PRODUCTS_CATEGORIES" ("ID" NUMBER, "CATEGORY" VARCHAR2(100))
--------------------------------------------------------
--  DDL for Table ROLES
--------------------------------------------------------

  CREATE TABLE "ROLES" ("ID" NUMBER, "ROLE" VARCHAR2(50))
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "USERS" ("ID" NUMBER, "USERNAME" VARCHAR2(50), "PASSWORD" VARCHAR2(100), "EMAIL" VARCHAR2(50), "ACCOUNTNONEXPIRED" NUMBER(1,0), "ACCOUNTNONLOCKED" NUMBER(1,0), "CREDENTIALSNONEXPIRED" NUMBER(1,0), "ENABLED" NUMBER(1,0))
--------------------------------------------------------
--  DDL for Table USERS_ROLE
--------------------------------------------------------

  CREATE TABLE "USERS_ROLE" ("USER_ID" NUMBER, "ROLE_ID" NUMBER)
REM INSERTING into PRODUCTS
SET DEFINE OFF;
Insert into PRODUCTS (ID,NAME,PRICE,IS_ACTIVE,PICTURE_URL,DESCRIPTION,USER_ID,CATEGORY_ID,QUANTITY,DATE_ADDED) values (4,'&lt;h1&gt;dqdq&lt;/h1&gt;q',232,1,null,null,2,1,2323,to_date('16-APR-16','DD-MON-RR'));
Insert into PRODUCTS (ID,NAME,PRICE,IS_ACTIVE,PICTURE_URL,DESCRIPTION,USER_ID,CATEGORY_ID,QUANTITY,DATE_ADDED) values (5,'&lt;h1&gt;dqdq&lt;/h1&gt;',312,1,'&lt;h1&gt;qdwd&lt;/h1&gt;','&lt;h2&gt;dqwdq&lt;/h2&gt;',2,1,0,to_date('16-APR-16','DD-MON-RR'));
Insert into PRODUCTS (ID,NAME,PRICE,IS_ACTIVE,PICTURE_URL,DESCRIPTION,USER_ID,CATEGORY_ID,QUANTITY,DATE_ADDED) values (1,'aprodukt',100,1,'http://store.hp.com/wcsstore/hpusstore/Treatment/dsnew-laptop-drawer-6-2.jpg','Basi qkiqt laptop, chovek',3,1,998,to_date('15-APR-16','DD-MON-RR'));
Insert into PRODUCTS (ID,NAME,PRICE,IS_ACTIVE,PICTURE_URL,DESCRIPTION,USER_ID,CATEGORY_ID,QUANTITY,DATE_ADDED) values (2,'xaxaxa',23,0,null,null,3,2,11,null);
REM INSERTING into PRODUCTS_CATEGORIES
SET DEFINE OFF;
Insert into PRODUCTS_CATEGORIES (ID,CATEGORY) values (1,'Laptops');
Insert into PRODUCTS_CATEGORIES (ID,CATEGORY) values (2,'Keyboards');
Insert into PRODUCTS_CATEGORIES (ID,CATEGORY) values (3,'Computers');
Insert into PRODUCTS_CATEGORIES (ID,CATEGORY) values (4,'Other');
REM INSERTING into ROLES
SET DEFINE OFF;
Insert into ROLES (ID,ROLE) values (1,'ROLE_ADMIN');
Insert into ROLES (ID,ROLE) values (2,'ROLE_USER');
REM INSERTING into USERS
SET DEFINE OFF;
Insert into USERS (ID,USERNAME,PASSWORD,EMAIL,ACCOUNTNONEXPIRED,ACCOUNTNONLOCKED,CREDENTIALSNONEXPIRED,ENABLED) values (1,'dqwdq','qdwdqwd','dqw',0,0,0,0);
Insert into USERS (ID,USERNAME,PASSWORD,EMAIL,ACCOUNTNONEXPIRED,ACCOUNTNONLOCKED,CREDENTIALSNONEXPIRED,ENABLED) values (3,'testasdasd','a4c79931401939092f8fbd2b36e0623d','testasdasd@dqw.dqw',1,1,1,1);
Insert into USERS (ID,USERNAME,PASSWORD,EMAIL,ACCOUNTNONEXPIRED,ACCOUNTNONLOCKED,CREDENTIALSNONEXPIRED,ENABLED) values (8,'popopoop','415d7bb624ffaff5d5806aac1347c5c7','popopoop@dqw.w',1,1,1,1);
Insert into USERS (ID,USERNAME,PASSWORD,EMAIL,ACCOUNTNONEXPIRED,ACCOUNTNONLOCKED,CREDENTIALSNONEXPIRED,ENABLED) values (2,'admin','21232f297a57a5a743894a0e4a801fc3','admin@abv.bg',1,1,1,1);
Insert into USERS (ID,USERNAME,PASSWORD,EMAIL,ACCOUNTNONEXPIRED,ACCOUNTNONLOCKED,CREDENTIALSNONEXPIRED,ENABLED) values (4,'fwefewf','8f086a03a76f0fe546804804a142b0c0','fwefewf@ad.qwdq',1,1,1,1);
Insert into USERS (ID,USERNAME,PASSWORD,EMAIL,ACCOUNTNONEXPIRED,ACCOUNTNONLOCKED,CREDENTIALSNONEXPIRED,ENABLED) values (7,'1212e21e','b2759a947a2ad16284e8487fc36ae6e0','1212e21e@adw.d',1,1,1,1);
REM INSERTING into USERS_ROLE
SET DEFINE OFF;
Insert into USERS_ROLE (USER_ID,ROLE_ID) values (3,2);
Insert into USERS_ROLE (USER_ID,ROLE_ID) values (8,2);
Insert into USERS_ROLE (USER_ID,ROLE_ID) values (4,2);
--------------------------------------------------------
--  DDL for Index SYS_C007088
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007088" ON "PRODUCTS" ("ID")
--------------------------------------------------------
--  DDL for Index SYS_C007091
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007091" ON "PRODUCTS_CATEGORIES" ("ID")
--------------------------------------------------------
--  DDL for Index SYS_C007093
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007093" ON "ROLES" ("ID")
--------------------------------------------------------
--  DDL for Index SYS_C007098
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007098" ON "USERS" ("ID")
--------------------------------------------------------
--  Constraints for Table PRODUCTS
--------------------------------------------------------

  ALTER TABLE "PRODUCTS" ADD PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "PRODUCTS" MODIFY ("IS_ACTIVE" NOT NULL ENABLE)
  ALTER TABLE "PRODUCTS" MODIFY ("PRICE" NOT NULL ENABLE)
  ALTER TABLE "PRODUCTS" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "PRODUCTS" MODIFY ("ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table PRODUCTS_CATEGORIES
--------------------------------------------------------

  ALTER TABLE "PRODUCTS_CATEGORIES" ADD PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "PRODUCTS_CATEGORIES" MODIFY ("CATEGORY" NOT NULL ENABLE)
  ALTER TABLE "PRODUCTS_CATEGORIES" MODIFY ("ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table ROLES
--------------------------------------------------------

  ALTER TABLE "ROLES" ADD PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "ROLES" MODIFY ("ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "USERS" ADD PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "USERS" MODIFY ("EMAIL" NOT NULL ENABLE)
  ALTER TABLE "USERS" MODIFY ("PASSWORD" NOT NULL ENABLE)
  ALTER TABLE "USERS" MODIFY ("USERNAME" NOT NULL ENABLE)
  ALTER TABLE "USERS" MODIFY ("ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table USERS_ROLE
--------------------------------------------------------

  ALTER TABLE "USERS_ROLE" MODIFY ("ROLE_ID" NOT NULL ENABLE)
  ALTER TABLE "USERS_ROLE" MODIFY ("USER_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table PRODUCTS
--------------------------------------------------------

  ALTER TABLE "PRODUCTS" ADD CONSTRAINT "FK_PRODUCTS__CATEGORY_ID" FOREIGN KEY ("CATEGORY_ID") REFERENCES "PRODUCTS_CATEGORIES" ("ID") ENABLE
  ALTER TABLE "PRODUCTS" ADD CONSTRAINT "FK_PRODUCTS__USER_ID" FOREIGN KEY ("USER_ID") REFERENCES "USERS" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table USERS_ROLE
--------------------------------------------------------

  ALTER TABLE "USERS_ROLE" ADD CONSTRAINT "FK_USERS_ROLE__ROLE_ID" FOREIGN KEY ("ROLE_ID") REFERENCES "ROLES" ("ID") ENABLE
  ALTER TABLE "USERS_ROLE" ADD CONSTRAINT "FK_USERS_ROLE__USER_ID" FOREIGN KEY ("USER_ID") REFERENCES "USERS" ("ID") ENABLE
--------------------------------------------------------
--  DDL for Trigger PRODUCTS_AUTOINC
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PRODUCTS_AUTOINC" 
BEFORE INSERT ON PRODUCTS 
FOR EACH ROW

BEGIN
  SELECT products_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
ALTER TRIGGER "PRODUCTS_AUTOINC" ENABLE
--------------------------------------------------------
--  DDL for Trigger USERS_AUTOINC
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "USERS_AUTOINC" 
BEFORE INSERT ON users 
FOR EACH ROW

BEGIN
  SELECT users_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
ALTER TRIGGER "USERS_AUTOINC" ENABLE
