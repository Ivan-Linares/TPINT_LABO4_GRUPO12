use banco;

DELIMITER //

CREATE PROCEDURE sp_resumenTotal()
BEGIN
	SELECT p.NombrePais as region,
		   (select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codpais=p.codpais and c.estado="A") AS clientes_activos,
		   (select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codpais=p.codpais and c.estado="I") AS clientes_inactivos,
			(select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codpais=p.codpais and c.estado="P") AS clientes_pendientes,
		   ifnull(sum(cu.saldo),0) AS Saldo_total,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 1 THEN m.importe END),0) AS movimientos_altas_cuenta,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 2 THEN m.importe END),0) AS movimientos_altas_prestamo,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 3 THEN m.importe END),0) AS movimientos_pago_prestamo,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 4 THEN m.importe END),0) AS movimientos_extracciones,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 5 THEN m.importe END),0) AS movimientos_deposito
	FROM pais p
	left JOIN localidades l ON l.codPais = p.codPais
	LEFT JOIN clientes c ON c.localidad = l.codlocalidad
	left JOIN cuentas cu ON cu.dni = c.dni
	left join movimientos m on m.cuenta=cu.cuenta
	group by p.codPais, p.NombrePais;
END //

delimiter ;


DELIMITER //

CREATE PROCEDURE sp_resumenPais(IN paisf int)
BEGIN
	SELECT pr.NombreProvincia as region,
		   (select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codProvincia=pr.codprovincia and c.estado="A") AS clientes_activos,
		   (select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codProvincia=pr.codprovincia and c.estado="I") AS clientes_inactivos,
			(select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codProvincia=pr.codprovincia and c.estado="P") AS clientes_pendientes,
		   ifnull(sum(cu.saldo),0) AS Saldo_total,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 1 THEN m.importe END),0) AS movimientos_altas_cuenta,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 2 THEN m.importe END),0) AS movimientos_altas_prestamo,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 3 THEN m.importe END),0) AS movimientos_pago_prestamo,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 4 THEN m.importe END),0) AS movimientos_extracciones,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 5 THEN m.importe END),0) AS movimientos_deposito
	FROM provincia pr
	left JOIN localidades l ON l.codProvincia= pr.codProvincia
	LEFT JOIN clientes c ON c.localidad = l.codlocalidad
	left JOIN cuentas cu ON cu.dni = c.dni
	left join movimientos m on m.cuenta=cu.cuenta
    where pr.codPais=paisf
	group by pr.codProvincia, pr.NombreProvincia;
END//

DELIMITER ;


DELIMITER //

CREATE PROCEDURE sp_resumenProvincia(IN provin int, paisf int)
BEGIN
	SELECT l.Nombrelocalidad as region,
		   (select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codProvincia=provin and lo.codLocalidad=l.codLocalidad and lo.codPais=paisf and c.estado="A") AS clientes_activos,
		   (select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codProvincia=provin and lo.codLocalidad=l.codLocalidad and lo.codPais=paisf and c.estado="I") AS clientes_inactivos,
			(select count(c.DNI) from clientes c
			right join localidades lo on lo.codlocalidad= c.localidad
			where lo.codProvincia=provin and lo.codLocalidad=l.codLocalidad and lo.codPais=paisf and c.estado="P") AS clientes_pendientes,
		   ifnull(sum(cu.saldo),0) AS Saldo_total,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 1 THEN m.importe END),0) AS movimientos_altas_cuenta,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 2 THEN m.importe END),0) AS movimientos_altas_prestamo,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 3 THEN m.importe END),0) AS movimientos_pago_prestamo,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 4 THEN m.importe END),0) AS movimientos_extracciones,
			ifnull(sum(CASE WHEN m.Tipo_movimiento = 5 THEN m.importe END),0) AS movimientos_deposito
	FROM localidades l
	LEFT JOIN clientes c ON c.localidad = l.codlocalidad
	left JOIN cuentas cu ON cu.dni = c.dni
	left join movimientos m on m.cuenta=cu.cuenta
    where l.codPais=paisf and l.codProvincia=provin
	group by l.codlocalidad, l.Nombrelocalidad;
END//

DELIMITER ;

