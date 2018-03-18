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
    estado varchar(255) NOT NULL
);

DROP TABLE IF EXISTS `llamada_empleado`;
CREATE TABLE `llamada_empleado` (
  `id` bigint auto_increment,
  `empleado_id` int(10) unsigned NOT NULL,
  `llamada_id` int(10) unsigned NOT NULL,
  `duracion` bigint unsigned NOT NULL,
  `fecha`date not null,
  PRIMARY KEY (`empleado_id`,`llamada_id`),
  FOREIGN KEY (`empleado_id`) references `empleado` (`id`),
  FOREIGN KEY (`llamada_id`) references `llamada` (`id`)
);