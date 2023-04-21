create database pronosticos;
 
 use pronosticos;
 
 create table pronostico(  Participante varchar(100)  not null,Fase varchar(10)  not null , Ronda int , EquipoL varchar(100)  not null , 
  GanaL varchar(10)  not null , Empata varchar(10)  not null ,GanaV varchar(100)  not null ,EquipoV varchar(100)  not null );
 

   
insert into pronostico values('Mariana','A','1','Argentina','X','','','Arabia Saudita' ),
('Mariana','A','1','Polonia','','X','','México'),
('Mariana','A','1','Argentina','X','','','México'),
('Mariana','A','1','Arabia Saudita','','','X','Polonia'   ),
('Mariana','A','1','Polonia','','','X','Argentina'  ),
('Mariana','A','1','Arabia Saudita','','','X','México'),
('Pedro','A','1','Argentina','X','','','Arabia Saudita'   ),
('Pedro','A','1','Polonia','','','X','México'  ),
('Pedro','A','1','Argentina','X','','','México'),
('Pedro','A','1','Arabia Saudita','','X','','Polonia' ),
('Pedro','A','1','Polonia','','','X','Argentina' ),
('Pedro','A','1','Arabia Saudita','','','X','México'),
('Pedro','A','2','Dinamarca','','','X','Tunez' ),
('Pedro','A','2','Francia','X','','','Dinamarca' ),
('Pedro','A','2','Tunez','','','X','Australia' ),
('Pedro','A','2','Tunez','X','','','Francia'   ),
('Pedro','A','2','Australia','','X','','Dinamarca'  ),
('Pedro','A','2','Francia','X','','','Australia' ),
('Ricardo','A','1','Argentina','','','X','Arabia Saudita' ),
('Ricardo','A','1','Polonia','','X','','México'),
('Ricardo','A','1','Argentina','X','','','México'),
('Ricardo','A','1','Arabia Saudita','','','X','Polonia'   ),
('Ricardo','A','1','Polonia','','','X','Argentina'  ),
('Ricardo','A','1','Arabia Saudita','','','X','México'),
('Ricardo','A','2','Dinamarca','','X','','Tunez' ),
('Ricardo','A','2','Francia','X','','','Dinamarca'  ),
('Ricardo','A','2','Tunez','','','X','Australia' ),
('Ricardo','A','2','Tunez','X','','','Francia' ),
('Ricardo','A','2','Australia','X','','','Dinamarca'),
('Ricardo','A','2','Francia','X','','','Australia'  ),
('Ricardo','B','3','Marruecos','','X','','Croacia' ),
('Ricardo','B','3','Bélgica','X','','','Canadá'),
('Ricardo','B','3','Bélgica','','','X','Marruecos' ),
('Ricardo','B','3','Croacia','X','','','Canadá'),
('Ricardo','B','3','Croacia','','X','','Bélgica'   ),
('Ricardo','B','3','Canadá','','','X','Marruecos' );

 

 select Ronda ,  count(*)
  from pronostico
  group by  Ronda;

 select* from pronostico  where Ronda='1';
 
  select* from pronostico  where Ronda='2';
  
   select* from pronostico  where Ronda='3';
 




   DROP table pronostico;
  