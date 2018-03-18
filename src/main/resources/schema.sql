create schema IF NOT EXISTS spring_data_jpa_example;

USE spring_data_jpa_example;

DROP TABLE IF EXISTS empleado;
CREATE TABLE empleado (
    id bigint auto_increment primary key,
    email varchar(255) NOT NULL,
    estado varchar(255) NOT NULL,
    cargo varchar(255) NOT NULL
);

DROP TABLE IF EXISTS llamada;
CREATE TABLE llamada (
    id bigint auto_increment primary key,
    duracion bigint(20) NOT NULL,
    estado varchar(255) NOT NULL,
    empleado_id bigint(20) unsigned DEFAULT NULL
);

ALTER TABLE llamada ADD FOREIGN KEY (empleado_id) REFERENCES empleado(id);

insert into empleado (email,estado,cargo) values ('davidarce@gmail.com','DISPONIBLE','OPERADOR');
insert into llamada (duracion,estado) values (6,'EN_PROGRESO');