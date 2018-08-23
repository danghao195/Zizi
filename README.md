# Zizi
di dỉ dì di cái gì cũng có


DB2:
DB Host Name: 10.254.12.8
Database Name: v4avgmp
Port Number: 50000
User Name: dbread
Password: dbread
--
10.254.12.8
50000
table: avgrp
u: arp
p: crm.com
jdbc:db2://10/254/12/8:50000/zizitest	
-----------------------------------------------------------------
CREATE TABLE "ARP"."M_MK" (

  MK_CD smallint NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
  PULL_DOWN_DSP_SEQ bigint,
  MK_NM varchar(100) NOT NULL,
  CREATE_TIME TIMESTAMP NOT NULL,
  CREATE_USER varchar(32) NOT NULL,
  CREATE_CLASS varchar(128) NOT NULL,
  UPDATE_TIME TIMESTAMP NOT NULL,
  UPDATE_USER varchar(32) NOT NULL,
  UPDATE_CLASS varchar(128) NOT NULL,
  DELETE_FLG varchar(1) not null,
  DELETE_TIME TIMESTAMP DEFAULT NULL
);
ALTER TABLE "ARP"."M_MK" ADD CONSTRAINT "PK_M_MK" PRIMARY KEY ("MK_CD");
