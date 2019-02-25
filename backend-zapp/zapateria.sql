DROP TABLE IF EXISTS producto_idioma;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS producto_categoria;
DROP TABLE IF EXISTS cliente_direccion;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS cod_pos;
--
-- TABLE: cod_pos
--
--
CREATE TABLE cod_pos (
  cp INT PRIMARY KEY,
  localidad VARCHAR(120) NOT NULL
);
INSERT INTO `cod_pos` (`cp`, `localidad`) VALUES
(23005, 'Jaén'),
(23008, 'Jaén'),
(29000, 'Málaga');
--
-- TABLE: clientes
--
--
CREATE TABLE cliente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(40) NOT NULL ,
  apellidos VARCHAR(100) NOT NULL ,
  dni INT NOT NULL
);
INSERT INTO `cliente` (`id`, `nombre`, `apellidos`, `dni`) VALUES
(1, 'Pepe', 'Perez Martinez', 12345678),
(2, 'Miguel Angel', 'Gutierrez Garcia', 87654321),
(3, 'Juan', 'Sin Miedo', 11223344);
--
-- TABLE: cliente_direccion
--
--
CREATE TABLE cliente_direccion (
  id_direccion INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  id_cliente int REFERENCES cliente(id) ON UPDATE CASCADE ON DELETE CASCADE,
  nombre_via VARCHAR(150) NOT NULL ,
  cp int REFERENCES cod_pos(cp) ON UPDATE CASCADE ON DELETE SET NULL
);
INSERT INTO `cliente_direccion` (`nombre`, `id_direccion`, `id_cliente`, `nombre_via`, `cp`) VALUES
('Mi casa', 1, 1, 'Paseo de la Estación 44', 23008),
('Casa', 2, 2, 'Rue del Percebe 13', 23005),
('Playa', 3, 2, 'Rue del Percebe 13', 29000),
('Oficina', 4, 3, 'Avenida de Andalucia S/N', 29000);
CREATE TABLE producto_categoria (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(40) NOT NULL
);
INSERT INTO `producto_categoria` (`id`, `nombre`) VALUES
(1, "Botas caballero"),
(2, "Botas mujer");
CREATE TABLE `producto`(
	id INT AUTO_INCREMENT PRIMARY KEY,
	ruta_imagen VARCHAR(200),
	categoria INT REFERENCES producto_categoria(id) ON UPDATE CASCADE
);
INSERT INTO producto(`id`,`ruta_imagen`,`categoria`) VALUES 
(1,NULL,1),
(2,NULL,1),
(3,NULL,1),
(4,NULL,2),
(5,NULL,2);
CREATE TABLE `producto_idioma` (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cod_idioma VARCHAR(2) NOT NULL,
  nombre VARCHAR(40) NOT NULL,
  descripcion VARCHAR(80) NOT NULL,
  id_producto INT REFERENCES producto(id) ON UPDATE CASCADE
);
INSERT INTO producto_idioma(`cod_idioma`,`nombre`,`descripcion`,`id_producto`) VALUES
('es','Botas trekking Merrel','botas con goretex, resistentes al agua', 1),
('en','Trekking boots Merrel','with goretex, waterproof', 1),
('es','Botas trekking Boreal','botas con goretex, resistentes al agua', 2),
('en','Trekking boots Boreal','with goretex, waterproof', 2),
('es','Botas trekking Norhtface','botas con goretex, resistentes al agua', 3),
('en','Trekking boots Northface','with goretex, waterproof', 3),
('es','Botas trekking Merrel','botas con goretex, resistentes al agua', 4),
('en','Trekking boots Merrel','with goretex, waterproof', 4),
('es','Botas trekking Boreal','botas con goretex, resistentes al agua', 5),
('en','Trekking boots Boreal','with goretex, waterproof', 5);















