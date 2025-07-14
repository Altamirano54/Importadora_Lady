/*Modificacion de la base de datos*/

-- Agregar la tabla tipo_documento
CREATE TABLE IF NOT EXISTS tipo_documento (
  id int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(100) NOT NULL,
  estado tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Insertar registros base en tipo_documento
INSERT INTO tipo_documento (id, nombre, estado) VALUES
(1, 'OTROS TIPOS DE DOCUMENTOS', 1),
(2, 'DNI', 1),
(3, 'CARNET DE EXTRANJERIA', 1),
(4, 'REGISTRO ÚNICO DE CONTRIBUYENTES', 1),
(5, 'PASAPORTE', 1);

-- Agregar campos a tabla cliente
ALTER TABLE cliente
  ADD COLUMN id_tipoDocumento INT(11) NOT NULL AFTER id,
  ADD COLUMN nro_documento VARCHAR(16) NOT NULL AFTER id_tipoDocumento;

-- Agregar relación con tipo_documento en cliente
ALTER TABLE cliente
  ADD CONSTRAINT FK_tipoDocumento_cliente FOREIGN KEY (id_tipoDocumento) REFERENCES tipo_documento (id);

-- Agregar campos a tabla empleado
ALTER TABLE empleado
  ADD COLUMN id_tipo_document INT(11) DEFAULT NULL AFTER nombre,
  ADD COLUMN nro_documento VARCHAR(16) DEFAULT NULL AFTER id_tipo_document,
  ADD COLUMN url VARCHAR(255) DEFAULT NULL AFTER estado;

-- Agregar clave foránea en empleado
ALTER TABLE empleado
  ADD CONSTRAINT FK_tipo_documento_empleado FOREIGN KEY (id_tipo_document) REFERENCES tipo_documento (id);

-- Agregar campo a tabla producto
ALTER TABLE producto
  ADD COLUMN url VARCHAR(255) DEFAULT NULL AFTER estado;

-- Agregar campo a tabla proveedor
ALTER TABLE proveedor
  ADD COLUMN ruc VARCHAR(11) NOT NULL AFTER id;







/*esdatos de solicitud*/
INSERT INTO `estadosolicitud` (`id`, `nombre`, `fecha_creacion`) VALUES 
(NULL, 'Cancelado', current_timestamp()), 
(NULL, 'En proceso', current_timestamp()), 
(NULL, 'En espera', current_timestamp()), 
(NULL, 'Completado', current_timestamp()), 
(NULL, 'Recibido', current_timestamp());



/*Cambios a la tabla empleado*/
ALTER TABLE `empleado` ADD `usuario` VARCHAR(100) NOT NULL AFTER `nombre`;
ALTER TABLE `empleado` ADD `fecha_nacimiento` DATE NULL AFTER `nro_documento`;
ALTER TABLE `empleado` ADD `genero` CHAR(1) NULL AFTER `fecha_nacimiento`;
ALTER TABLE `empleado` ADD `direccion` VARCHAR(100) NULL AFTER `id_cargo`;
ALTER TABLE `empleado` ADD `telefono` VARCHAR(9) NULL AFTER `direccion`;
ALTER TABLE `empleado` ADD `correo` VARCHAR(255) NULL AFTER `telefono`;
ALTER TABLE `empleado` CHANGE `fecha_creacion` `fecha_contratacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE `empleado` ADD `fecha_terminacion` TIMESTAMP NULL AFTER `estado`;
ALTER TABLE `empleado` ADD `fecha_ultima_actualizacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `fecha_contratacion`;



/*Cambios a la tabla proveedor*/
ALTER TABLE `proveedor` CHANGE `contacto` `telefono_contacto` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL;

/*cambios a cliente*/
ALTER TABLE `cliente` ADD `direccion` VARCHAR(200) NULL AFTER `telefono`;

/*Cambios a producto*/
descripcionALTER TABLE `producto` ADD `descripcion` VARCHAR(260) NULL AFTER `id_proveedor`;

