/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2011/10/12 10:08:16                          */
/*==============================================================*/


alter table ASSESS
   drop constraint FK_ASSESS_TEMEPLATE;

alter table ASSESS
   drop constraint FK_ASSESS_REFERENCE_ASSESS_O;

alter table ASSESS_PROP
   drop constraint FK_ASSESS_P_REFERENCE_ASSESS_P;

alter table ASSESS_PROP
   drop constraint FK_ASSESS_P_REFERENCE_ASSESS_T;

alter table ASSESS_RESULT
   drop constraint FK_ASSESS_RECORD;

alter table ASSESS_RESULT
   drop constraint FK_ASSESS_R_REFERENCE_ASSESS_P;

drop table ASSESS cascade constraints;

drop table ASSESS_OBJECT cascade constraints;

drop table ASSESS_PROP cascade constraints;

drop table ASSESS_RESULT cascade constraints;

drop table ASSESS_TEMPLATE cascade constraints;

drop table KPI_VALUE_STORE cascade constraints;

drop table USER_LOG cascade constraints;


/*==============================================================*/
/* Table: ASSESS                                                */
/*==============================================================*/
create table ASSESS 
(
   ID                   NUMBER(10)           not null,
   TEMEPLATE_ID         NUMBER(10)           not null,
   OBJECT_ID            NUMBER(10),
   CREATE_TIME          DATE,
   BEGIN_TIME           DATE,
   END_TIME             DATE,
   PERIOD               NUMBER(1),
   constraint PK_ASSESS primary key (ID)
);

comment on column ASSESS.PERIOD is
'1.��
2.��
3.��
4.��
5.��
6.�Զ���';

/*==============================================================*/
/* Table: ASSESS_OBJECT                                         */
/*==============================================================*/
create table ASSESS_OBJECT 
(
   ID                   NUMBER(10)           not null,
   FOREIGN_ID           VARCHAR2(200),
   OBJECT_CODE          VARCHAR2(100),
   OBJECT_NAME          VARCHAR2(300),
   OBJECT_TYPE          NUMBER(1),
   ACCOUNT              VARCHAR2(200),
   STATUS               NUMBER(1),
   constraint PK_ASSESS_OBJECT primary key (ID)
);

comment on column ASSESS_OBJECT.FOREIGN_ID is
'���˶���Ϊ��֯��ʱ��д��֯��id
';

comment on column ASSESS_OBJECT.OBJECT_TYPE is
'1 ��֯�� 2.��Ա';

comment on column ASSESS_OBJECT.STATUS is
'1.��
2.��ͣ��';

/*==============================================================*/
/* Table: ASSESS_PROP                                           */
/*==============================================================*/
create table ASSESS_PROP 
(
   ID                   NUMBER(10)           not null,
   TEMPLATE_ID          NUMBER(10),
   PARENT_ID            NUMBER(10),
   KPI_ID               VARCHAR2(50),
   PROP_NAME            VARCHAR2(200),
   PROP_TYPE            NUMBER(1),
   PROP_EXPRESSION      varchar2(500),
   PROP_UNIT            varchar2(20),
   SCORE_EXPRESSION     VARCHAR2(500),
   PERIOD               varchar2(20),
   STATUS               NUMBER(1),
   UPDATE_ACCOUNT       VARCHAR2(200),
   UPDATE_TIME          DATE,
   SORT                 NUMBER(5),
   REMARKS              VARCHAR2(2000),
   constraint PK_ASSESS_PROP primary key (ID)
);

comment on column ASSESS_PROP.PROP_TYPE is
'1.�ֹ���д
2.ָ��ֵ
3.�������';

comment on column ASSESS_PROP.STATUS is
'1.����
2.ͣ��';

/*==============================================================*/
/* Table: ASSESS_RESULT                                         */
/*==============================================================*/
create table ASSESS_RESULT 
(
   ID                   NUMBER(10)           not null,
   ASSESS_ID            NUMBER(10)           not null,
   PROP_ID              NUMBER(10),
   KPI_VALUE            VARCHAR2(100),
   SCORE                NUMBER(10,2),
   constraint PK_ASSESS_RESULT primary key (ID)
);

/*==============================================================*/
/* Table: ASSESS_TEMPLATE                                       */
/*==============================================================*/
create table ASSESS_TEMPLATE 
(
   ID                   NUMBER(10)           not null,
   TEMPLATE_NAME        VARCHAR2(100),
   STATUS               NUMBER(1),
   UPDATE_ACCOUNT       VARCHAR2(200),
   UPDATE_TIME          DATE,
   REMARKS              VARCHAR2(500),
   constraint PK_ASSESS_TEMPLATE primary key (ID)
);

comment on column ASSESS_TEMPLATE.STATUS is
'1 �ݸ� 2 �ѷ��� 3 ��ͣ��';

/*==============================================================*/
/* Table: KPI_VALUE_STORE                                       */
/*==============================================================*/
create table KPI_VALUE_STORE 
(
   ID                   NUMBER(10),
   CI_ID                VARCHAR2(100),
   KPI_ID               VARCHAR2(100)        not null,
   DATA_BEGIN_TIME      DATE                 not null,
   KPI_VALUE            VARCHAR2(100),
   WRITE_TIME           DATE
);

/*==============================================================*/
/* Table: USER_LOG                                              */
/*==============================================================*/
create table USER_LOG 
(
   ID                   NUMBER(10),
   ACCOUNT              VARCHAR2(200),
   SUCCESSD             NUMBER(1),
   LOG_MSG              VARCHAR2(2000),
   MODULE_ID            NUMBER(10),
   TIME                 DATE
);

alter table ASSESS
   add constraint FK_ASSESS_TEMEPLATE foreign key (TEMEPLATE_ID)
      references ASSESS_TEMPLATE (ID);

alter table ASSESS
   add constraint FK_ASSESS_REFERENCE_ASSESS_O foreign key (OBJECT_ID)
      references ASSESS_OBJECT (ID);

alter table ASSESS_PROP
   add constraint FK_ASSESS_P_REFERENCE_ASSESS_P foreign key (PARENT_ID)
      references ASSESS_PROP (ID);

alter table ASSESS_PROP
   add constraint FK_ASSESS_P_REFERENCE_ASSESS_T foreign key (TEMPLATE_ID)
      references ASSESS_TEMPLATE (ID);

alter table ASSESS_RESULT
   add constraint FK_ASSESS_RECORD foreign key (ASSESS_ID)
      references ASSESS (ID);

alter table ASSESS_RESULT
   add constraint FK_ASSESS_R_REFERENCE_ASSESS_P foreign key (PROP_ID)
      references ASSESS_PROP (ID);

