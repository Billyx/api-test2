INSERT INTO technicaltest.persona (nombre,genero,edad,identificacion,direccion,telefono) VALUES
	 ('Jose Lema','M',34,'42099127','Otavalo sn y principal','098254785'),
	 ('Marianela Montalvo','F',26,'41551320','Amazonas y  NNUU','097548965'),
	 ('Juan Osorio','M',37,'01492039','13 junio y Equinoccial','098874587');
	 
 INSERT INTO technicaltest.cliente (idPersona,contrasena,estado) VALUES
 (1,'12345678',1),
 (2,'56789012',1),
 (3,'12456789',1);
 
 INSERT INTO technicaltest.tipocuenta (descripcion,estado) VALUES
	 ('Ahorros',1),
	 ('Corriente',1);
 
 INSERT INTO technicaltest.cuenta (idCliente,numeroCuenta,tipoCuenta,saldoInicial,estado) VALUES
	 (1,'478758870193213323',1,2000.0,1),
	 (2,'225487887019321332',2,100.0,1),
	 (3,'495878887019321332',1,0.0,1),
	 (2,'496825887019321332',1,540.0,1),
	 (1,'585545887019321332',2,1000.0,1);
	 
INSERT INTO technicaltest.movimiento (fecha,valor,saldo,idCuenta) VALUES
	 ('2023-09-01',-575.0,1425.0,1),
	 ('2023-09-01',600.0,700.0,2),
	 ('2023-09-03',150.0,150.0,3),
	 ('2023-09-04',-540.0,0.0,4),
	 ('2023-09-03',-100.0,1325.0,1),
	 ('2023-09-04',-325.0,1000.0,1),
	 ('2023-09-05',-80.0,920.0,1),
	 ('2023-09-03',-150.0,850.0,5),
	 ('2023-09-06',-50.0,870.0,1),
	 ('2023-09-03',-20.0,830.0,5);
	 