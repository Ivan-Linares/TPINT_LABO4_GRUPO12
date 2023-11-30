create schema Banco;
use Banco;

Create table Tipos_Usuarios
(
	Tipo_Usuario int PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL unique
);

insert into Tipos_Usuarios values(1, "Administrador");
insert into Tipos_Usuarios values(2,"Cliente");

create table Usuarios
(
	Usuario VARCHAR(10) PRIMARY KEY,
    Password VARCHAR(10) NOT NULL,
    Tipo_Usuario int NOT NULL,
    Estado BOOL NOT NULL DEFAULT 1,
    foreign key (Tipo_usuario) references Tipos_Usuarios(Tipo_Usuario)
);

INSERT INTO Usuarios (Usuario, Password, Tipo_Usuario, Estado) VALUES
('admin1', 'pass1', 1, 1),
('admin2', 'pass2', 1, 1),
('user1', 'pass1', 2, 1),
('user2', 'pass2', 2, 1),
('user3', 'pass3', 2, 1),
('user4', 'pass4', 2, 1);

create table Pais
(
codPais int not null primary key,
NombrePais varchar(50) not null unique
);

create table Provincia
(
codPais int not null,
codProvincia int not null,
NombreProvincia varchar(50) not null,
foreign key (codPais) references Pais(codPais),
primary key(codPais,codProvincia)
);

ALTER TABLE Provincia
ADD INDEX idx_codPais (codPais),
ADD INDEX idx_codProvincia (codProvincia);

CREATE table Localidades(
codLocalidad int not null primary key,
codProvincia int not null,
NombreLocalidad varchar(50) not null unique,
foreign key (codProvincia) references Provincia(codProvincia)
);

create table clientes
(
DNI VARCHAR(10) NOT NULL PRIMARY KEY,
Cuil VARCHAR(13) NOT NULL UNIQUE,
Apellido VARCHAR(50) NOT NULL,
Nombre VARCHAR(50) NOT NULL,
Sexo CHAR(1) check(Sexo='F' or Sexo='M' or Sexo='X'),
FechaNac date NOT NULL,
Localidad int NOT NULL,
Direccion VARCHAR(100) NOT NULL,
Email VARCHAR(100) NOT NULL,
Usuario VARCHAR(10) not null unique,
Estado CHAR(1) NOT NULL default 'P' check(Estado='P' or Estado='A' or Estado='I'),
foreign key (Usuario) references Usuarios(Usuario),
foreign key (Localidad) references Localidades(codLocalidad)
);

create table Telefonos
(
	DNI VARCHAR(10) UNIQUE,
    Telefono VARCHAR(20) NOT NULL,
    PRIMARY KEY (DNI, Telefono),
    foreign key (DNI) references clientes(DNI)
);

create table Tipos_Cuentas
(
	Tipo_de_cuenta int primary key,
    Descripcion VARCHAR(20) NOT NULL unique
    
);

create table Cuentas
(
	Cuenta varchar(100) primary key,
    DNI VARCHAR(10) not NULL,
    Fecha_creacion date not null,
    Tipo_De_Cuenta int not null,
    CBU varchar(12) not null,
    Saldo DECIMAL(10,2) NOT NULL check (Saldo>=0),
    Estado CHAR(1) NOT NULL default 'P' check(Estado='P' or Estado='A' or Estado='I'),
    foreign key (DNI) references Clientes(DNI)
);

create table Prestamos
(
    ID_Prestamo int not NULL PRIMARY KEY auto_increment,
	Cuenta VARCHAR(100) NOT NULL,
    Fecha date not null,
    Importe_mensual_a_pagar DECIMAL(6,2) NOT NULL,
    Importe_total DECIMAL(7,2) NOT NULL,
    Plazo_de_pago char(3) not null,
    Estado CHAR(1) NOT NULL default 'P' check(Estado='P' or Estado='A' or Estado='I'),
    cuotas_pendientes char(3) not null,
    foreign key (Cuenta) references Cuentas(cuenta)
);

CREATE table PagoPrestamos(
    ID_Prestamo int not NULL primary key,
    Cuenta varchar(100),
    NumeroCuota SMALLINT not NULL,
    Importe_total DECIMAL(7,2) NOT NULL,
    Fecha date not null,
    foreign key (Cuenta) references Cuentas(Cuenta)
);

create Table Tipos_Movimientos
(
	Tipo_movimiento char(1) not null primary key,
    Descripcion VARCHAR(100) NOT NULL unique
);

create table Movimientos
(
    ID int not null PRIMARY KEY auto_increment,
	Cuenta VARCHAR(100) NOT NULL,
    Fecha date not null,
    Concepto VARCHAR(100) NOT NULL,
    IMPORTE DECIMAL(7,2) NOT NULL,
    Tipo_movimiento char(1) not null,
    foreign key (Cuenta) references cuentas(cuenta),
    foreign key (Tipo_movimiento) references Tipos_Movimientos(Tipo_movimiento)
);



DELIMITER $$
CREATE TRIGGER tr_autoinc
BEFORE INSERT ON Cuentas FOR EACH ROW
BEGIN
    SET NEW.CBU = LPAD(
        (
            CAST(
                IFNULL(
                    (SELECT MAX(cbu) FROM cuentas),
                    '100000000001'
                ) AS SIGNED
            ) + 1
        ),
        12,
        '0'
    );
END;
$$
DELIMITER ;


INSERT INTO Usuarios (Usuario, Password, Tipo_Usuario, Estado) VALUES
('admin1', 'pass1', 1, 1),
('admin2', 'pass2', 1, 1),
('user1', 'pass1', 2, 1),
('user2', 'pass2', 2, 1),
('user3', 'pass3', 2, 1),
('user4', 'pass4', 2, 1);

insert into Pais values (1, "Argentina");
insert into Pais values (2, "Chile");
insert into Pais values (3, "Brasil");

insert into Provincia values(1,1,"Buenos Aires");
insert into Provincia values(1,2,"Cordoba");
insert into Provincia values(1,3,"Mendoza");
insert into Provincia values(2,4,"Santiago");
insert into Provincia values(2,5,"Concepcion");
insert into Provincia values(2,6,"Valdivia");
insert into Provincia values(3,7,"San pablo");
insert into Provincia values(3,8,"Rio de janeiro");
insert into Provincia values(3,9,"Bahia");

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(1, 1, 'La Plata'),
(1, 2, 'Mar del Plata'),
(1, 3, 'Quilmes');

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(2, 4, 'Córdoba'),
(2, 5, 'Villa María'),
(2, 6, 'Río Cuarto');

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(3, 7, 'Mendoza'),
(3, 8, 'San Rafael'),
(3, 9, 'Tunuyán');

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(4, 10, 'Santiago'),
(4, 11, 'Rancagua'),
(4, 12, 'Curicó');

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(5, 13, 'Concepción'),
(5, 14, 'Talcahuano'),
(5, 15, 'Chillán');

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(6, 16, 'Valdivia'),
(6, 17, 'Osorno'),
(6, 18, 'Puerto Montt');

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(7, 19, 'Sao Paulo'),
(7, 20, 'Campinas'),
(7, 21, 'Guarulhos');

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(8, 22, 'Río de Janeiro'),
(8, 23, 'Niterói'),
(8, 24, 'São Gonçalo');

INSERT INTO Localidades (codProvincia, codLocalidad, NombreLocalidad) VALUES
(9, 25, 'Salvador'),
(9, 26, 'Feira de Santana'),
(9, 27, 'Vitória da Conquista');

INSERT INTO clientes (DNI, Cuil, Apellido, Nombre, Sexo, FechaNac, Direccion, Localidad, Email, Usuario, Estado)
VALUES

('12345678', '20345678901', 'Gomez', 'Juan', 'M', '1990-01-15', 'Calle 123',1, 'juan@gmail.com', 'user1', 'P'),
('23456789', '30345678901', 'Lopez', 'Ana', 'F', '1995-05-20', 'Calle 456', 25, 'ana@gmail.com', 'user2', 'P'),
('34567890', '40345678901', 'Martinez', 'Pedro', 'M', '1988-08-10', 'Calle 789', 5, 'pedro@gmail.com', 'user3', 'P'),
('45678901', '50345678901', 'Fernandez', 'Luis', 'M', '1992-07-08', 'Avenida A', 10, 'luis@gmail.com', 'user4', 'P');


INSERT INTO Telefonos (DNI, Telefono) VALUES
('12345678', '1111111111'),
('23456789', '2222222222'),
('34567890', '3333333333'),
('45678901', '4444444444');

INSERT INTO Tipos_Cuentas (Tipo_de_cuenta, Descripcion) VALUES
(1, 'Caja de ahorro'),
(2, 'Cuenta Corriente');

INSERT INTO Cuentas (DNI,Cuenta, Fecha_creacion, Tipo_De_Cuenta, CBU, Saldo, Estado) VALUES
('12345678','111111111111', '2023-11-18', 1, 'CBU111111111', 1500.00, 'A'),
('23456789','222222222222', '2023-11-18', 1, 'CBU222222222', 2000.00, 'A'),
('34567890', '333333333333', '2023-11-18', 1, 'CBU333333333', 3000.00, 'A'),
('45678901','444444444444', '2023-11-18', 2, 'CBU444444444', 5000.00, 'A'),
('12345678','555555555555', '2023-11-18', 2, 'CBU555555555', 8000.00, 'A'),
('34567890','666666666666', '2023-11-18', 2, 'CBU666666666', 10000.00, 'A'),
('45678901','777777777777', '2023-11-18', 1, 'CBU777777777', 1200.00, 'A'),
('45678901','888888888888', '2023-11-18', 1, 'CBU888888888', 1800.00, 'A'),
('34567890','999999999999', '2023-11-18', 1, 'CBU999999999', 2500.00, 'A');

INSERT INTO Prestamos (Cuenta, Fecha, Importe_mensual_a_pagar, Importe_total, Plazo_de_pago, Estado, cuotas_pendientes) VALUES
('111111111111', '2023-11-18', 200.00, 1200.00, '12', 'P', '12'),
('222222222222', '2023-11-18', 150.00, 900.00, '6', 'P', '6'),
('333333333333', '2023-11-18', 300.00, 1800.00, '12', 'P', '12'),
('444444444444', '2023-11-18', 500.00, 2500.00, '5', 'P', '5');

INSERT INTO Tipos_Movimientos (Tipo_Movimiento, Descripcion) VALUES
(1, 'Alta de cuenta'),
(2, 'Alta de prestamo'),
(3, 'Pago de prestamo'),
(4, 'Transferencia');

INSERT INTO Movimientos (Cuenta, Fecha, Concepto, IMPORTE, Tipo_movimiento) VALUES
('111111111111', '2023-11-18', 'Alta de cuenta', 1500.00, '1'),
('222222222222', '2023-11-18', 'Alta de cuenta', 2000.00, '1'),
('333333333333', '2023-11-18', 'Alta de cuenta', 3000.00, '1'),
('444444444444', '2023-11-18', 'Alta de cuenta', 5000.00, '1'),

('111111111111', '2023-11-18', 'Alta de prestamo', 200.00, '2'),
('222222222222', '2023-11-18', 'Alta de prestamo', 150.00, '2'),
('333333333333', '2023-11-18', 'Alta de prestamo', 300.00, '2'),
('444444444444', '2023-11-18', 'Alta de prestamo', 500.00, '2'),

('111111111111', '2023-11-18', 'Pago de prestamo', 200.00, '3'),
('222222222222', '2023-11-18', 'Pago de prestamo', 150.00, '3'),
('333333333333', '2023-11-18', 'Pago de prestamo', 300.00, '3'),
('444444444444', '2023-11-18', 'Pago de prestamo', 500.00, '3'),

('111111111111', '2023-11-18', 'Transferencia', 100.00, '4'),
('222222222222', '2023-11-18', 'Transferencia', 50.00, '4'),
('333333333333', '2023-11-18', 'Transferencia', 200.00, '4'),
('444444444444', '2023-11-18', 'Transferencia', 300.00, '4');
