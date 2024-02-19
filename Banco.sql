create schema Banco;
use Banco;

Create table Tipos_Usuarios
(
	Tipo_Usuario int PRIMARY KEY auto_increment,
    Descripcion VARCHAR(50) NOT NULL unique
);


create table Usuarios
(
	Usuario VARCHAR(25) PRIMARY KEY,
    Password VARCHAR(25) NOT NULL,
    Tipo_Usuario int NOT NULL,
    Estado BOOL NOT NULL DEFAULT 1,
    foreign key (Tipo_usuario) references Tipos_Usuarios(Tipo_Usuario)
);


create table Pais
(
codPais int not null primary key auto_increment,
NombrePais varchar(50) not null unique
);

create table Provincia
(
codPais INT NOT NULL,
codProvincia INT NOT NULL auto_increment,
NombreProvincia VARCHAR(50) NOT NULL,
PRIMARY KEY (codPais, codProvincia),
FOREIGN KEY (codPais) REFERENCES Pais(codPais),
INDEX idx_codPais (codPais),
index idx_codProvincia (codProvincia)
);


CREATE table Localidades(
codPais int not null,
codProvincia int not null,
codLocalidad int not null auto_increment,
NombreLocalidad varchar(50) not null unique,
primary key(codPais,codProvincia, codLocalidad),
FOREIGN KEY (codPais) REFERENCES Pais(codPais),
FOREIGN KEY (codProvincia) REFERENCES Provincia(codProvincia),
INDEX idx_codPais (codPais),
INDEX idx_codProvincia (codProvincia),
INDEX idx_codLoc(codLocalidad)
);

create table clientes
(
DNI VARCHAR(8) NOT NULL PRIMARY KEY,
Cuil VARCHAR(13) NOT NULL UNIQUE,
Apellido VARCHAR(50) NOT NULL,
Nombre VARCHAR(50) NOT NULL,
Sexo CHAR(1) check(Sexo='F' or Sexo='M' or Sexo='X'),
FechaNac date NOT NULL,
Localidad int NOT NULL,
Direccion VARCHAR(100) NOT NULL,
Email VARCHAR(100) NOT NULL,
Telefono VARCHAR(20) NOT NULL,
TelefonoSecundario VARCHAR(20) NULL,
Usuario VARCHAR(25) not null unique,
Estado CHAR(1) NOT NULL default 'P' check(Estado='P' or Estado='A' or Estado='I'),
foreign key (Usuario) references Usuarios(Usuario),
foreign key (Localidad) references Localidades(codLocalidad)
);


create table Tipos_Cuentas
(
	Tipo_de_cuenta int primary key auto_increment,
    Descripcion VARCHAR(30) NOT NULL unique
);


CREATE TABLE Cuentas (
    Cuenta INT AUTO_INCREMENT PRIMARY KEY,
    DNI VARCHAR(8) NOT NULL,
    Fecha_creacion DATE NOT NULL,
    Tipo_De_Cuenta INT NOT NULL,
    CBU VARCHAR(24) NOT NULL,
    Saldo DECIMAL(10,2) NOT NULL CHECK (Saldo >= 0),
    Estado CHAR(1) NOT NULL DEFAULT 'P' CHECK (Estado='P' OR Estado='A' OR Estado='I'),
    FOREIGN KEY (DNI) REFERENCES Clientes(DNI),
    FOREIGN KEY (Tipo_De_Cuenta) REFERENCES Tipos_Cuentas(Tipo_De_Cuenta)
) AUTO_INCREMENT = 100000;

create table Prestamos
(
    ID_Prestamo int not NULL PRIMARY KEY auto_increment,
    DNI VARCHAR(8),
    Cuenta int NOT NULL,
    Fecha date not null,
    Importe_solicitado DECIMAL(10,2) NOT NULL,
    Importe_total DECIMAL(10,2) NOT NULL,
    Importe_mensual_a_pagar DECIMAL(10,2) NOT NULL,
    cuotas_pendientes INT not null,
    Estado CHAR(1) NOT NULL default 'P' check(Estado='P' or Estado='A' or Estado='E' or Estado='R'),
    foreign key (Cuenta) references Cuentas(cuenta),
    foreign key (DNI) references Clientes(DNI)
) AUTO_INCREMENT = 100000;

ALTER TABLE Prestamos
ADD CantidadCuotas INT NOT NULL;

CREATE table PagoPrestamos(
    ID_Pago int not NULL primary key auto_increment,
    ID_Prestamo int not NULL,
    Cuenta int,
    NumeroCuota INT not NULL,
    Importe_cuota DECIMAL NOT NULL,
    Fecha date not null,
    foreign key (Cuenta) references Cuentas(Cuenta),
    foreign key (ID_Prestamo) references Prestamos(ID_Prestamo)
);

ALTER TABLE PagoPrestamos
MODIFY COLUMN Importe_cuota DECIMAL(10,2) NOT NULL;

create Table Tipos_Movimientos
(
	Tipo_movimiento int not null primary key auto_increment,
    Descripcion VARCHAR(100) NOT NULL unique
);

create table Movimientos
(
    ID int not null PRIMARY KEY auto_increment,
	Cuenta int NOT NULL,
    Fecha date not null,
    IMPORTE DECIMAL(10,2) NOT NULL,
    Tipo_movimiento int not null,
    foreign key (Cuenta) references cuentas(cuenta),
    foreign key (Tipo_movimiento) references Tipos_Movimientos(Tipo_movimiento)
);

CREATE DEFINER=`root`@`localhost` TRIGGER `cuentas_BEFORE_INSERT` BEFORE INSERT ON `cuentas` FOR EACH ROW BEGIN
	DECLARE nFecha DATE;
    DECLARE nCBU VARCHAR(24);
    DECLARE nSaldo DECIMAL(10, 2);
    DECLARE nestado CHAR(1);
    DECLARE ncuenta INT;
    Set ncuenta = (select Cuenta from cuentas order by Cuenta desc LIMIT 1)+1;

    SET nFecha = CURDATE();

    SET nCBU = CONCAT(NEW.DNI, ncuenta);

    SET nSaldo = 10000;
    
    SET nestado="P";

    SET NEW.Fecha_creacion = nFecha;
    SET NEW.CBU = nCBU;
    SET NEW.Saldo = nSaldo;
    SET NEW.Estado = nestado;
END

trigger movimientos

CREATE DEFINER=`root`@`localhost` TRIGGER `movimientos_BEFORE_INSERT` BEFORE INSERT ON `movimientos` FOR EACH ROW BEGIN
	DECLARE nfecha date;
    
    Set nfecha = CURDATE();
    
    SET NEW.Fecha = nfecha;
END

