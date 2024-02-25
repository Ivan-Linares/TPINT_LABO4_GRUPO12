use banco;

DELIMITER //

CREATE PROCEDURE sp_resumenTotal()
BEGIN
	create temporary table movimientos_cuentas
	SELECT  l.codpais,
				ifnull(sum(case when m.Tipo_movimiento = 1 then m.importe end),0) as movimientos_altas_cuenta,
				ifnull(sum(case when m.Tipo_movimiento = 2 then m.importe end),0) as movimientos_altas_prestamo,
				ifnull(sum(case when m.Tipo_movimiento = 3 then m.importe end),0) as movimientos_pago_prestamo,
				ifnull(sum(case when m.Tipo_movimiento = 4 then m.importe end),0) as movimientos_extracciones,
				ifnull(sum(case when m.Tipo_movimiento = 5 then m.importe end),0) as movimientos_deposito
		FROM movimientos m
		left join cuentas c on c.Cuenta=m.Cuenta
		left join clientes cl on cl.dni=c.dni
		left join localidades l on l.codlocalidad=cl.localidad
		group by l.codpais;

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
		   ifnull(sum(cu.saldo),0) AS Saldo_total,mc.movimientos_altas_cuenta, mc.movimientos_altas_prestamo, mc.movimientos_pago_prestamo, 
			mc.movimientos_extracciones, mc.movimientos_deposito
	FROM pais p
	left JOIN localidades l ON l.codPais = p.codPais
	left JOIN clientes c ON c.localidad = l.codlocalidad
	left JOIN cuentas cu ON cu.dni = c.dni
    left join movimientos_cuentas mc on mc.codPais=p.codPais
	group by p.codPais, p.NombrePais, mc.movimientos_altas_cuenta, mc.movimientos_altas_prestamo, mc.movimientos_pago_prestamo, 
    mc.movimientos_extracciones, mc.movimientos_deposito;
    DROP TEMPORARY TABLE IF EXISTS movimientos_cuentas;
END //

delimiter ;

DELIMITER //

CREATE PROCEDURE sp_resumenPais(IN paisf int)
BEGIN
	create temporary table movimientos_cuentas
	SELECT  l.codpais, l.codprovincia,
				ifnull(sum(case when m.Tipo_movimiento = 1 then m.importe end),0) as movimientos_altas_cuenta,
				ifnull(sum(case when m.Tipo_movimiento = 2 then m.importe end),0) as movimientos_altas_prestamo,
				ifnull(sum(case when m.Tipo_movimiento = 3 then m.importe end),0) as movimientos_pago_prestamo,
				ifnull(sum(case when m.Tipo_movimiento = 4 then m.importe end),0) as movimientos_extracciones,
				ifnull(sum(case when m.Tipo_movimiento = 5 then m.importe end),0) as movimientos_deposito
		FROM movimientos m
		left join cuentas c on c.Cuenta=m.Cuenta
		left join clientes cl on cl.dni=c.dni
		left join localidades l on l.codlocalidad=cl.localidad
		group by l.codpais, l.codProvincia;

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
	sum(cu.saldo) AS Saldo_total, mc.movimientos_altas_cuenta, mc.movimientos_altas_prestamo, mc.movimientos_pago_prestamo, 
    mc.movimientos_extracciones, mc.movimientos_deposito
	FROM provincia pr
	left JOIN localidades l ON l.codProvincia= pr.codProvincia
	LEFT JOIN clientes c ON c.localidad = l.codlocalidad
	left JOIN cuentas cu ON cu.dni = c.dni
    left join movimientos_cuentas mc on mc.codProvincia=pr.codProvincia
    where pr.codPais=paisf
	group by pr.codProvincia, pr.NombreProvincia, mc.movimientos_altas_cuenta, mc.movimientos_altas_prestamo, mc.movimientos_pago_prestamo, 
    mc.movimientos_extracciones, mc.movimientos_deposito;
    
     DROP TEMPORARY TABLE IF EXISTS movimientos_cuentas;
END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_resumenProvincia(IN provin int, paisf int)
BEGIN
	create temporary table movimientos_cuentas
	SELECT  l.codlocalidad,
				ifnull(sum(case when m.Tipo_movimiento = 1 then m.importe end),0) as movimientos_altas_cuenta,
				ifnull(sum(case when m.Tipo_movimiento = 2 then m.importe end),0) as movimientos_altas_prestamo,
				ifnull(sum(case when m.Tipo_movimiento = 3 then m.importe end),0) as movimientos_pago_prestamo,
				ifnull(sum(case when m.Tipo_movimiento = 4 then m.importe end),0) as movimientos_extracciones,
				ifnull(sum(case when m.Tipo_movimiento = 5 then m.importe end),0) as movimientos_deposito
		FROM movimientos m
		left join cuentas c on c.Cuenta=m.Cuenta
		left join clientes cl on cl.dni=c.dni
		left join localidades l on l.codlocalidad=cl.localidad
		group by l.codlocalidad;
	
	
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
		   ifnull(sum(cu.saldo),0) AS Saldo_total, mc.movimientos_altas_cuenta, mc.movimientos_altas_prestamo, mc.movimientos_pago_prestamo, 
			mc.movimientos_extracciones, mc.movimientos_deposito
	FROM localidades l
	LEFT JOIN clientes c ON c.localidad = l.codlocalidad
	left JOIN cuentas cu ON cu.dni = c.dni
    left join movimientos_cuentas mc on mc.codlocalidad=l.codlocalidad
    where l.codPais=paisf and l.codProvincia=provin
	group by l.codlocalidad, l.Nombrelocalidad, mc.movimientos_altas_cuenta, mc.movimientos_altas_prestamo, mc.movimientos_pago_prestamo, 
    mc.movimientos_extracciones, mc.movimientos_deposito;
    DROP TEMPORARY TABLE IF EXISTS movimientos_cuentas;
END//

DELIMITER ;
