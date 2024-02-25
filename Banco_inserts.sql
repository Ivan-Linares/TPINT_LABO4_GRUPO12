use Banco;

INSERT INTO Tipos_Usuarios  VALUES ("1","Administrador");
INSERT INTO Tipos_Usuarios  VALUES ("2","Cliente");

INSERT INTO Usuarios (Usuario, Password, Tipo_Usuario, Estado) VALUES
('adm', 'pas', 1, 1),
('admin2', 'pass2', 1, 1);

insert into Pais (NombrePais) values 
("Argentina"),
("Chile"),
("Brasil");

INSERT INTO Provincia (codPais, NombreProvincia) VALUES
(1, "Buenos Aires"),
(1, "Cordoba"),
(1, "Mendoza"),
(2, "Santiago"),
(2, "Concepcion"),
(2, "Valdivia"),
(3, "San pablo"),
(3, "Rio de janeiro"),
(3, "Bahia");

INSERT INTO Localidades (codPais, codProvincia, NombreLocalidad) VALUES
(1, 1, 'La Plata'),
(1, 1, 'Mar del Plata'),
(1, 1, 'Quilmes'),
(1, 2, 'Córdoba'),
(1, 2, 'Villa María'),
(1, 2, 'Río Cuarto'),
(1, 3, 'Mendoza'),
(1, 3, 'San Rafael'),
(1, 3, 'Tunuyán'),
(2, 4, 'Santiago'),
(2, 4, 'Rancagua'),
(2, 4, 'Curicó'),
(2, 4, 'Concepción'),
(2, 6, 'Talcahuano'),
(2, 6, 'Chillán'),
(2, 5, 'Valdivia'),
(2, 5, 'Osorno'),
(2, 5, 'Puerto Montt'),
(3, 7, 'Sao Paulo'),
(3, 7, 'Campinas'),
(3, 7, 'Guarulhos'),
(3, 8, 'Río de Janeiro'),
(3, 8, 'Niterói'),
(3, 8, 'São Gonçalo'),
(3, 9, 'Salvador'),
(3, 9, 'Feira de Santana'),
(3, 9, 'Vitória da Conquista');

INSERT INTO Tipos_Cuentas (Descripcion) VALUES
('Caja de ahorro'),
('Cuenta Corriente');

INSERT INTO Tipos_Movimientos (Descripcion) VALUES
('Alta de cuenta'),
('Alta de prestamo'),
('Pago de prestamo'),
('TransferenciaExtraccion'),
('TransferenciaDeposito');
