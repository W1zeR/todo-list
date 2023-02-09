if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TODO') and o.name = 'FK_APPUSERID_TODO')
alter table TODO
   drop constraint FK_APPUSERID_TODO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TODO')
            and   type = 'U')
   drop table TODO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('AppUser')
            and   type = 'U')
   drop table AppUser
go

create table AppUser (
   id bigint identity(1,1) not null,
   login nvarchar(30) not null,
   password nvarchar(30) not null
)
go

alter table AppUser
   add constraint PK_ID_APPUSER primary key (id)
go

create table TODO (
   id bigint identity(1,1) not null,
   comment nvarchar(100) not null,
   created date not null,
   shouldBeDoneBefore date not null,
   userId bigint not null,
   status tinyint not null
)
go

alter table TODO
   add constraint PK_ID_TODO primary key (id)
go

alter table TODO
	add constraint CHECK_STATUS_TODO check (status BETWEEN 0 AND 2)
go

alter table TODO
   add constraint FK_USERID_TODO foreign key (userID) references AppUser (id)
go