use Banco;

INSERT INTO Tipos_Usuarios (Descripcion) VALUES ("Administrador");
insert into Tipos_Usuarios (Descripcion)  values("Cliente");

INSERT INTO Usuarios (Usuario, Password, Tipo_Usuario, Estado) VALUES
('admin1', 'pass1', 1, 1),
('admin2', 'pass2', 1, 1),
('user1', 'pass1', 2, 1),
('user2', 'pass2', 2, 1),
('user3', 'pass3', 2, 1),
('user4', 'pass4', 2, 1);


insert into Pais (NombrePais) values 
("Argentina"),
("Chile"),
("Brasil");

INSERT INTO Provincia (codPais, codProvincia, NombreProvincia) VALUES
(1, 1,"Buenos Aires"),
(1, 2,"Cordoba"),
(1, 3,"Mendoza"),
(2, 1,"Santiago"),
(2, 2,"Concepcion"),
(2, 3,"Valdivia"),
(3, 1,"San pablo"),
(3, 2,"Rio de janeiro"),
(3, 3,"Bahia");

INSERT INTO Localidades (codPais, codProvincia, codLocalidad, NombreLocalidad) VALUES
(1, 1, 1, 'La Plata'),
(1, 1, 2, 'Mar del Plata'),
(1, 1, 3, 'Quilmes'),
(1, 2, 1, 'Córdoba'),
(1, 2, 2, 'Villa María'),
(1, 2, 3, 'Río Cuarto'),
(1, 3, 1, 'Mendoza'),
(1, 3, 2, 'San Rafael'),
(1, 3, 3, 'Tunuyán'),
(2, 1, 1, 'Santiago'),
(2, 1, 2, 'Rancagua'),
(2, 1, 3, 'Curicó'),
(2, 2, 1, 'Concepción'),
(2, 2, 2,'Talcahuano'),
(2, 2, 3,'Chillán'),
(2, 3, 1,'Valdivia'),
(2, 3, 2,'Osorno'),
(2, 3, 3,'Puerto Montt'),
(3, 1, 1,'Sao Paulo'),
(3, 1, 2,'Campinas'),
(3, 1, 3,'Guarulhos'),
(3, 2, 1,'Río de Janeiro'),
(3, 2, 2,'Niterói'),
(3, 2, 3,'São Gonçalo'),
(3, 3, 1,'Salvador'),
(3, 3, 2,'Feira de Santana'),
(3, 3, 3,'Vitória da Conquista');

INSERT INTO clientes (DNI, Cuil, Apellido, Nombre, Sexo, FechaNac, Direccion, pais, provincia, Localidad, Email, Usuario, Estado)
VALUES

('12345678', '20345678901', 'Gomez', 'Juan', 'M', '1990-01-15', 'Calle 123',1, 1, 1, 'juan@gmail.com', 'user1', 'P'),
('23456789', '30345678901', 'Lopez', 'Ana', 'F', '1995-05-20', 'Calle 456', 1, 3, 3, 'ana@gmail.com', 'user2', 'P'),
('34567890', '40345678901', 'Martinez', 'Pedro', 'M', '1988-08-10', 'Calle 789', 1, 2, 3, 'pedro@gmail.com', 'user3', 'P'),
('45678901', '50345678901', 'Fernandez', 'Luis', 'M', '1992-07-08', 'Avenida A', 1, 1, 1, 'luis@gmail.com', 'user4', 'P');


INSERT INTO Telefonos (DNI, Telefono) VALUES
('12345678', '1111111111'),
('23456789', '2222222222'),
('34567890', '3333333333'),
('45678901', '4444444444');

INSERT INTO Tipos_Cuentas (Descripcion) VALUES
('Caja de ahorro'),
('Cuenta Corriente');

INSERT INTO Cuentas (DNI, Fecha_creacion, Tipo_De_Cuenta, CBU, Saldo, Estado) VALUES
('12345678', '2023-11-18', 1, 'CBU111111111', 1500.00, 'A'),
('23456789', '2023-11-18', 1, 'CBU222222222', 2000.00, 'A'),
('34567890', '2023-11-18', 1, 'CBU333333333', 3000.00, 'A'),
('45678901', '2023-11-18', 2, 'CBU444444444', 5000.00, 'A'),
('12345678', '2023-11-18', 2, 'CBU555555555', 8000.00, 'A'),
('34567890', '2023-11-18', 2, 'CBU666666666', 10000.00, 'A'),
('45678901', '2023-11-18', 1, 'CBU777777777', 1200.00, 'A'),
('45678901', '2023-11-18', 1, 'CBU888888888', 1800.00, 'A'),
('34567890', '2023-11-18', 1, 'CBU999999999', 2500.00, 'A');

INSERT INTO Prestamos (Cuenta, dni, Fecha, Importe_mensual_a_pagar, Importe_total, Estado, cuotas_pendientes) VALUES
(100000, '12345678', '2023-11-18', 200.00, 1200.00, 'P', '12'),
(100001, '23456789', '2023-11-18', 150.00, 900.00, 'P', '6'),
(100002, '34567890', '2023-11-18', 300.00, 1800.00, 'P', '12'),
(100003, '45678901', '2023-11-18', 500.00, 2500.00, 'P', '5');

INSERT INTO Tipos_Movimientos (Descripcion) VALUES
('Alta de cuenta'),
('Alta de prestamo'),
('Pago de prestamo'),
('TransferenciaExtraccion'),
('TransferenciaDeposito');

INSERT INTO Movimientos (Cuenta, Fecha, Concepto, IMPORTE, Tipo_movimiento) VALUES
(100000, '2023-11-18', 'Alta de cuenta', 1500.00, '1'),
(100001, '2023-11-18', 'Alta de cuenta', 2000.00, '1'),
(100002, '2023-11-18', 'Alta de cuenta', 3000.00, '1'),
(100003, '2023-11-18', 'Alta de cuenta', 5000.00, '1'),

(100000, '2023-11-18', 'Alta de prestamo', 200.00, '2'),
(100001, '2023-11-18', 'Alta de prestamo', 150.00, '2'),
(100002, '2023-11-18', 'Alta de prestamo', 300.00, '2'),
(100003, '2023-11-18', 'Alta de prestamo', 500.00, '2'),

(100000, '2023-11-18', 'Pago de prestamo', 200.00, '3'),
(100001, '2023-11-18', 'Pago de prestamo', 150.00, '3'),
(100002, '2023-11-18', 'Pago de prestamo', 300.00, '3'),
(100003, '2023-11-18', 'Pago de prestamo', 500.00, '3');

