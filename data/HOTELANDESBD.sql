CREATE TABLE CADENAHOTELERA
    (
        "ID" NUMBER,
        "NOMBRE" VARCHAR(60),
        "PRINCIPIOSGENERALES" VARCHAR(500),
        CONSTRAINT CADENAHOTELERA_PK PRIMARY KEY (ID)
    ); 
  CREATE TABLE USUARIOS
   (	"ID" NUMBER,
        "NOMBRE" VARCHAR(40),
        "APELLIDO" VARCHAR(40),
        "IDTIPOUSUARIOS" NUMBER,
        "IDHOTEL" NUMBER,
        
        CONSTRAINT USUARIOS_PK PRIMARY KEY (ID)
    );
     CREATE TABLE TIPOUSUARIOS
   (	"ID" NUMBER,
        "NOMBRE" VARCHAR(40),
        CONSTRAINT TIPOUSUARIOS_PK PRIMARY KEY (ID)
    );
    CREATE TABLE HOTEL
    (
        "ID" NUMBER,
        "IDCADENAHOTELERA" NUMBER,
        "NOMBRE" VARCHAR(60),
        CONSTRAINT HOTEL_PK PRIMARY KEY (ID)
    );
    CREATE TABLE PLANCONSUMO
    (
         "ID" NUMBER,
         "NOMBRE" VARCHAR(80),
         "MINIMODIAS" NUMBER,
         --TIPO DE DESCUENTO; SI ES PAGO A EL COSTO DE LA HABITACION SE LE ADICIONA EL PRECIO, SI ES DESCUENTO APLICA EL PORCENTAJE A EL COSTO DEL ALOJAMIENTO
         "TIPODESCUENTO" VARCHAR(10),
         "DESCRIPCION" VARCHAR(500),
         "DESCUENTO" NUMBER,
         "PRECIO" NUMBER,
         "IDHOTEL" NUMBER,
         CONSTRAINT PLANCONSUMO_PK PRIMARY KEY (ID)
    );
     CREATE TABLE SERVICIOSPLAN
    (
         "ID" NUMBER,
         "IDPRODUCTOSERVICIO" NUMBER,
         "IDPLANCONSUMO" NUMBER,
         "DESCUENTO" NUMBER,
         "CANTIDAD" NUMBER,
         CONSTRAINT SERVICIOSPLAN_PK PRIMARY KEY (ID)
    );
    CREATE TABLE SERVICIOADICIONAL
    (
        "ID" NUMBER,
        "NOMBRE" VARCHAR(80),
        "HORAAPERTURA" VARCHAR(4),
        "HORACIERRE" VARCHAR(4),
        "CAPACIDAD" NUMBER,
        "IDHOTEL" NUMBER,
        CONSTRAINT SERVICIOADICIONAL_PK PRIMARY KEY (ID)
    );
   CREATE TABLE PRODUCTOSERVICIO
    (
        "ID" NUMBER,
        "COSTO" NUMBER,
        "NOMBRE" VARCHAR (80),
        "DESCRIPCION" VARCHAR(500),
        "IDSERVICIOADICIONAL" NUMBER,
        "RESERVA" VARCHAR(2),
        CONSTRAINT PRODUCTOSERVICIO_PK PRIMARY KEY (ID)
    );
    CREATE TABLE RESERVAHABITACION 
    (
        "ID" NUMBER,
        "FECHACREACION" DATE,
        "FECHAINGRESO" DATE,
        "FECHASALIDA" DATE,
        "IDHABITACION" NUMBER,
        "NUMEROPERSONAS" NUMBER,
        "IDPLANCONSUMO" NUMBER,
        "IDUSUARIO" NUMBER,
        CONSTRAINT RESERVAHABITACION_PK PRIMARY KEY (ID)
    );
    CREATE TABLE RESERVASERVICIO
    (
        "ID" NUMBER,
        "IDPRODUCTOSERVICIO" NUMBER,
        "FECHAINICIO" TIMESTAMP ,
        "FECHAFIN" TIMESTAMP ,
        "IDUSUARIO" NUMBER,
        
        CONSTRAINT RESERVASERVICIO_PK PRIMARY KEY (ID)
    );
    CREATE TABLE CUENTA
    (
        "ID" NUMBER,
        "IDRESERVA" NUMBER,
        "PAGADA" VARCHAR(2),
        "TOTAL"  NUMBER,
        "ACOMPAŅANTES" VARCHAR(60),
        CONSTRAINT CUENTA_PK PRIMARY KEY (ID)
    );
    CREATE TABLE CUENTAHABITACION
    (
        "ID" NUMBER,
        "IDCUENTA" NUMBER,
        "IDPRODUCTOSERVICIO" NUMBER,
        "ESPAGO" VARCHAR(2),
        CONSTRAINT CUENTAHABITACION_PK PRIMARY KEY (ID)
    );
    CREATE TABLE TIPOHABITACION
    (
      "ID" NUMBER,
      "TIPO" VARCHAR(60),
      "CAPACIDAD" NUMBER,
      "COSTONOCHE" NUMBER,
      "DESCRIPCION" VARCHAR(600),
      CONSTRAINT TIPOHABITACION_PK PRIMARY KEY (ID)
    );
    CREATE TABLE HABITACION
    (
     "ID" NUMBER,
     "NHABITACION" NUMBER,
     "IDTIPOHABITACION" NUMBER,
     "IDHOTEL" NUMBER,
     CONSTRAINT HABITTACION_PK PRIMARY KEY (ID)
    );
   
   ALTER TABLE HOTEL
ADD CONSTRAINT fk_cadenahotelera_hotel
    FOREIGN KEY (idcadenahotelera)
    REFERENCES CADENAHOTELERA(id)
ENABLE;

ALTER TABLE HABITACION
ADD CONSTRAINT fk_tipohabitacion_habitacion
    FOREIGN KEY (idtipohabitacion)
    REFERENCES TIPOHABITACION(id)
ENABLE;
ALTER TABLE HABITACION
ADD CONSTRAINT fk_hotel_habitacion
    FOREIGN KEY (idhotel)
    REFERENCES HOTEL(id)
ENABLE;

ALTER TABLE USUARIOS
ADD CONSTRAINT fk_tipousuarios_usuarios
    FOREIGN KEY (idtipousuarios)
    REFERENCES TIPOUSUARIOS(id)
ENABLE;
ALTER TABLE USUARIOS
ADD CONSTRAINT fk_hotel_usuarios
    FOREIGN KEY (idhotel)
    REFERENCES HOTEL(id)
ENABLE;

ALTER TABLE SERVICIOADICIONAL
ADD CONSTRAINT fk_hotel_servicioadicional
    FOREIGN KEY (idhotel)
    REFERENCES HOTEL(id)
ENABLE;

ALTER TABLE PRODUCTOSERVICIO
ADD CONSTRAINT fk_servicioa_prodservicio
    FOREIGN KEY (idservicioadicional)
    REFERENCES SERVICIOADICIONAL(id)
ENABLE;


ALTER TABLE PLANCONSUMO
ADD CONSTRAINT fk_hotel_planconsumo
    FOREIGN KEY (idhotel)
    REFERENCES HOTEL(id)
ENABLE;

ALTER TABLE SERVICIOSPLAN
ADD CONSTRAINT fk_pconsumo_splan
    FOREIGN KEY (idplanconsumo)
    REFERENCES PLANCONSUMO(id)
ENABLE;
ALTER TABLE SERVICIOSPLAN
ADD CONSTRAINT fk_pservicio_splan
    FOREIGN KEY (idproductoservicio)
    REFERENCES PRODUCTOSERVICIO(id)
ENABLE;

ALTER TABLE RESERVAHABITACION
ADD CONSTRAINT fk_habitacion_rhabitacion
    FOREIGN KEY (idhabitacion)
    REFERENCES HABITACION(id)
ENABLE;
ALTER TABLE RESERVAHABITACION
ADD CONSTRAINT fk_usuario_rhabitacion
    FOREIGN KEY (idusuario)
    REFERENCES USUARIOS(id)
ENABLE;
ALTER TABLE RESERVAHABITACION
ADD CONSTRAINT fk_planconsumo_rhabitacion
    FOREIGN KEY (idplanconsumo)
    REFERENCES PLANCONSUMO(id)
ENABLE;

ALTER TABLE RESERVASERVICIO
ADD CONSTRAINT fk_usuario_reservaservicio
    FOREIGN KEY (idusuario)
    REFERENCES USUARIOS(id)
ENABLE;
ALTER TABLE RESERVASERVICIO
ADD CONSTRAINT fk_prodservicio_rservicio
    FOREIGN KEY (idproductoservicio)
    REFERENCES PRODUCTOSERVICIO(id)
ENABLE;

ALTER TABLE CUENTA
ADD CONSTRAINT fk_reserva_cuenta
    FOREIGN KEY (idreserva)
    REFERENCES RESERVAHABITACION(id)
ENABLE;

ALTER TABLE CUENTAHABITACION
ADD CONSTRAINT fk_cuenta_chabitacion
    FOREIGN KEY (idcuenta)
    REFERENCES CUENTA(id)
ENABLE;
ALTER TABLE CUENTAHABITACION
ADD CONSTRAINT fk_prodservicio_chabitacion
    FOREIGN KEY (idproductoservicio)
    REFERENCES PRODUCTOSERVICIO(id)
ENABLE;
COMMIT;