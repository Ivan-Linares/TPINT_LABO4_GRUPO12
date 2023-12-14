use Banco;

INSERT INTO Tipos_Usuarios  VALUES ("1","Administrador");
insert into Tipos_Usuarios  values("2","Cliente");

INSERT INTO Usuarios (Usuario, Password, Tipo_Usuario, Estado) VALUES
('adm', 'pas', 1, 1),
('admin2', 'pass2', 1, 1),
('user1', 'pass1', 2, 1),
('user2', 'pass2', 2, 1),
('user3', 'pass3', 2, 1),
('user4', 'pass4', 2, 1),
('user5', 'pass5', 2, 1),
('user6', 'pass6', 2, 1),
('user7', 'pass7', 2, 1),
('user8', 'pass8', 2, 1),
('user9', 'pass9', 2, 1),
('user10', 'pass10', 2, 1),
('user11', 'pass11', 2, 1),
('user12', 'pass12', 2, 1),
('user14', 'pass14', 2, 1),
('user15', 'pass15', 2, 1),
('user13', 'pass13', 2, 1);



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

INSERT INTO clientes (DNI, Cuil, Apellido, Nombre, Sexo, FechaNac, Direccion, Localidad, Email, Telefono, Usuario, Estado)
VALUES

('12345678', '20345678901', 'Gomez', 'Juan', 'M', '1990-01-15', 'Calle 123', 1, 'juan@gmail.com',"1155885588", 'user1', 'A'),
('23456789', '30345678901', 'Lopez', 'Ana', 'F', '1995-05-20', 'Calle 456', 3, 'ana@gmail.com',"1146468899" , 'user2', 'A'),
('34567890', '40345678901', 'Martinez', 'Pedro', 'M', '1988-08-10', 'Calle 789', 3, 'pedro@gmail.com',"1188889999" , 'user3', 'A'),
('45678901', '50345678901', 'Fernandez', 'Luis', 'M', '1992-07-08', 'Avenida A 587', 1, 'luis@gmail.com',"1138696933" ,'user4', 'P'),
('44444444', '50444444401', 'Fernandez', 'Morena', 'F', '1996-09-08', 'Avenida A 12', 1, 'morenaf@gmail.com',"1138696933" ,'user5', 'P'),
('55555555', '50555555551', 'Fernandez', 'Carolina', 'F', '1992-04-08', 'Avenida T 567', 1, 'carolinaf@gmail.com',"1138696933" ,'user6', 'P'),
('66666666', '50666666661', 'Martinez', 'Carlos', 'M', '1990-07-08', 'Avenida A L567', 15, 'carlosm@gmail.com',"1138696933" ,'user7', 'P'),
('77777777', '50777777771', 'Gomez', 'Selena', 'F', '1990-07-05', 'Avenida A 1234', 20, 'selenag@gmail.com',"1138696933" ,'user8', 'P'),
('88888888', '50888888881', 'Fernandez', 'Alberto', 'M', '1989-01-01', 'Avenida A 4', 7, 'albertog@gmail.com',"1138696933" ,'user9', 'A'),
('99999999', '50999999991', 'Rivadavia', 'Comodoro', 'M', '1992-05-08', 'Avenida L 567', 8, 'comodoror@gmail.com',"1138696933" ,'user10', 'P'),
('98765432', '50987654321', 'Cruz', 'Debora', 'F', '1998-07-08', 'Avenida F 5678', 17, 'deborac@gmail.com',"1138696933" ,'user11', 'P'),
('12345676', '50123456781', 'Sanchez', 'Camila', 'X', '1992-01-08', 'Avenida D 76', 5, 'camilas@gmail.com',"1138696933" ,'user12', 'A'),
('87654321', '50876543211', 'Fernandez', 'Milagros', 'F', '1976-07-06', 'Avenida b 678', 10, 'milagrosf@gmail.com',"1138696933" ,'user13', 'A'),
('34567891', '50345678911', 'Cruz', 'Tomas', 'M', '1985-02-03', 'Avenida A 56', 11, 'tomasm@gmail.com',"1138696933" ,'user14', 'A'),
('43579087', '50435790871', 'Fernandez', 'Ana', 'F', '1992-07-08', 'Avenida zz 134', 21, 'anaf@gmail.com',"1138696933" ,'user15', 'P');




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
('88888888', '2023-11-18', 1, '101010101', "15000.00", 'A'),
('88888888', '2023-11-18', 1, '111111111', "12500.00", 'A'),
('12345678', '2023-11-18', 1, '123456789', "3500.00", 'A'),
('12345678', '2023-11-18', 1, '987654321', "22500.00", 'A'),
('87654321', '2023-11-18', 1, '707070707', "122500.00", 'A'),
('87654321', '2023-11-18', 1, '606060606', "90000.00", 'A'),
('34567891', '2023-11-18', 1, '232323232', "3500.00", 'A');

use Banco;

INSERT INTO Prestamos (Cuenta, dni, Fecha, Importe_mensual_a_pagar, Importe_total, Importe_solicitado,Estado, cuotas_pendientes) VALUES
(100000, '12345678', '2023-11-18', 200.00, 1200.00,1000.00, 'P', '12'),
(100001, '23456789', '2023-11-18', 150.00, 900.00,1000.00, 'P', '6'),
(100002, '34567890', '2023-11-18', 300.00, 1800.00,1000.00, 'P', '12'),
(100003, '45678901', '2023-11-18', 500.00, 2500.00,1000.00, 'P', '5');

INSERT INTO Tipos_Movimientos (Descripcion) VALUES
('Alta de cuenta'),
('Alta de prestamo'),
('Pago de prestamo'),
('TransferenciaExtraccion'),
('TransferenciaDeposito');



INSERT INTO Movimientos (Cuenta, Fecha, IMPORTE, Tipo_movimiento) VALUES
(100000, '2023-11-18', 1500.00, '1'),
(100001, '2023-11-18', 2000.00, '1'),
(100002, '2023-11-18', 3000.00, '1'),
(100003, '2023-11-18', 5000.00, '1'),
(100000, '2023-11-18', 200.00, '2'),
(100001, '2023-11-18', 150.00, '2'),

(100002, '2023-11-18', 300.00, '2'),
(100003, '2023-11-18', 500.00, '2'),

(100004, '2023-11-18', 500.00, '4'),
(100005, '2023-11-18', 500.00, '4'),
(100006, '2023-11-18', 500.00, '4'),

(100000, '2023-11-18', 200.00, '3'),
(100001, '2023-11-18', 150.00, '3'),
(100002, '2023-11-18', 300.00, '3'),
(100003, '2023-11-18',  500.00, '3');