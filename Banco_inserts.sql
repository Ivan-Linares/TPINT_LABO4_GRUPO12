use Banco;

select *  from Usuarios;
INSERT INTO Tipos_Usuarios  VALUES ("1","Administrador");
insert into Tipos_Usuarios  values("2","Cliente");

INSERT INTO Usuarios (Usuario, Password, Tipo_Usuario, Estado) VALUES
('adm', 'pas', 1, 1),
('admin2', 'pass2', 1, 1),
('user1', 'pass1', 2, 1),
('user2', 'pass2', 2, 1),
('user3', 'pass3', 2, 1),
('user4', 'pass4', 2, 1);


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
(2, 1, 'Santiago'),
(2, 1, 'Rancagua'),
(2, 1, 'Curicó'),
(2, 2, 'Concepción'),
(2, 2, 'Talcahuano'),
(2, 2, 'Chillán'),
(2, 3, 'Valdivia'),
(2, 3, 'Osorno'),
(2, 3, 'Puerto Montt'),
(3, 1, 'Sao Paulo'),
(3, 1, 'Campinas'),
(3, 1, 'Guarulhos'),
(3, 2, 'Río de Janeiro'),
(3, 2, 'Niterói'),
(3, 2, 'São Gonçalo'),
(3, 3, 'Salvador'),
(3, 3, 'Feira de Santana'),
(3, 3, 'Vitória da Conquista');

INSERT INTO clientes (DNI, Cuil, Apellido, Nombre, Sexo, FechaNac, Direccion, Localidad, Email, Telefono, Usuario, Estado)
VALUES

('12345678', '20345678901', 'Gomez', 'Juan', 'M', '1990-01-15', 'Calle 123', 1, 'juan@gmail.com',"1155885588", 'user1', 'A'),
('23456789', '30345678901', 'Lopez', 'Ana', 'F', '1995-05-20', 'Calle 456', 3, 'ana@gmail.com',"1146468899" , 'user2', 'A'),
('34567890', '40345678901', 'Martinez', 'Pedro', 'M', '1988-08-10', 'Calle 789', 3, 'pedro@gmail.com',"1188889999" , 'user3', 'A'),
('45678901', '50345678901', 'Fernandez', 'Luis', 'M', '1992-07-08', 'Avenida A', 1, 'luis@gmail.com',"1138696933" ,'user4', 'P');


INSERT INTO Tipos_Cuentas (Descripcion) VALUES
('Caja de ahorro'),
('Cuenta Corriente');

Select * from Cuentas;

INSERT INTO Cuentas (DNI, Fecha_creacion, Tipo_De_Cuenta, CBU, Saldo, Estado) VALUES
('23456789', '2023-11-18', 1, '222222222', "2000.00", 'A'),
('34567890', '2023-11-18', 1, '333333333', "3000.00", 'A'),
('45678901', '2023-11-18', 2, '444444444', "5000.00", 'A'),
('12345678', '2023-11-18', 2, '555555555', "8000.00", 'A'),
('34567890', '2023-11-18', 2, '666666666', "10000.00", 'A'),
('45678901', '2023-11-18', 1, '777777777', "1200.00", 'A'),
('45678901', '2023-11-18', 1, '888888888', "1800.00", 'A'),
('34567890', '2023-11-18', 1, '999999999', "2500.00", 'A'),
('12345678', '2023-11-18', 1, '111111111', "1500.00", 'A');

INSERT INTO Prestamos (Cuenta, dni, Fecha, Importe_solicitado, Importe_mensual_a_pagar, Importe_total, Estado, cuotas_pendientes) VALUES
(100000, '12345678', '2023-11-18', 1000.00, 200.00, 1200.00, 'P', '12'),
(100001, '23456789', '2023-11-18', 750.00, 150.00, 900.00, 'P', '6'),
(100002, '34567890', '2023-11-18', 1500.00, 300.00, 1800.00, 'P', '12'),
(100003, '45678901', '2023-11-18', 2000.00, 500.00, 2500.00, 'P', '5');

INSERT INTO Tipos_Movimientos (Descripcion) VALUES
('Alta de cuenta'),
('Alta de prestamo'),
('Pago de prestamo'),
('TransferenciaExtraccion'),
('TransferenciaDeposito');


select * from Clientes c inner join Usuarios u on c.Usuario = u.Usuario inner join Localidades l on c.Localidad = l.codLocalidad inner join Provincia p on l.codProvincia = p.codProvincia inner join Pais pa on p.codPais = pa.codPais;


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