CREATE DATABASE investigation;

-- El orden de creación presente debería de funcionar

CREATE TABLE IF NOT EXISTS Campo(
    campo_id  INTEGER NOT NULL AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    ramal varchar(50) DEFAULT NULL,
    PRIMARY KEY (campo_id)
);

CREATE TABLE IF NOT EXISTS Entidad(
    entidad_id INTEGER NOT NULL AUTO_INCREMENT,
    nombre varchar(100) NOT NULL,
    ubicacion varchar(100) DEFAULT NULL,
    PRIMARY KEY (entidad_id)
);

CREATE TABLE IF NOT EXISTS Proyecto(
    proyecto_id INTEGER NOT NULL AUTO_INCREMENT,
    campo_id INTEGER,
    nombre varchar(100) NOT NULL,
    fecha_inicio DATE DEFAULT NULL,
    capital DOUBLE(22,2),
    finalizado boolean DEFAULT FALSE,
    coste DOUBLE(22,2),
    fecha_fin DATE DEFAULT NULL,
    PRIMARY KEY (proyecto_id),
    FOREIGN KEY (campo_id) REFERENCES Campo(campo_id) 
);

CREATE TABLE IF NOT EXISTS Investigador(
    investigador_id INTEGER NOT NULL AUTO_INCREMENT,
    proyecto_id INTEGER,
    nombre varchar(50),
    titulo varchar(50),
    salario DOUBLE(9,2),
    PRIMARY KEY (investigador_id),
    FOREIGN KEY (proyecto_id) REFERENCES Proyecto(proyecto_id) 
);

CREATE TABLE IF NOT EXISTS entidad_proyecto(
    entidad_id INTEGER NOT NULL,
    proyecto_id INTEGER NOT NULL,
    PRIMARY KEY (entidad_id, proyecto_id)
);



CREATE TABLE IF NOT EXISTS investigador_campo(
    investigador_id INTEGER NOT NULL,
    campo_id INTEGER NOT NULL,
    PRIMARY KEY (investigador_id, campo_id)
);

--Datos de ejemplo, id´s autogenerados podrían no coincidir:
INSERT INTO Entidad(nombre, ubicacion)
VALUES ("Paco Molla", "Petrer, Alicante"),
("Everis", "Madrid");

INSERT INTO Proyecto(nombre, fecha_inicio, capital, finalizado)
VALUES ("Flujo de hilos", '2021-1-1', 30000, false),
("Algoritmo de hilos", '2021-2-1', 40000, false);

INSERT INTO entidad_proyecto(entidad_id, proyecto_id)
VALUES (1,1), (2,2); 

INSERT INTO Campo(nombre, ramal) 
VALUES("Computacion", "Tecnologia"),
("Algoritmos", "Matematicas");

INSERT INTO Proyecto(nombre, fecha_inicio, capital, finalizado, coste, fecha_fin)
VALUES ("Data Acces Object", '2021-1-1', 10000, true, 9000, '2021-2-24');

INSERT INTO Investigador(nombre, proyecto_id, titulo, salario)
VALUES ("Paco", 1, "Matematico", 1500),
("Arturo", 3, "Ingeniero informatico", 1200);

INSERT INTO Investigador(nombre, proyecto_id, titulo, salario)
VALUES ("DaviJuan", 1, "Fisico", 1400);

INSERT INTO entidad_proyecto(entidad_id, proyecto_id)
VALUES (1, 3); 

INSERT INTO investigador_campo(investigador_id, campo_id)
VALUES (1,2),
(2,1), (3,2)

--Práctica de consultas:
SELECT e.entidad_id, e.nombre, p.nombre
FROM Entidad e
LEFT OUTER JOIN entidad_proyecto e_p
	ON e.entidad_id = e_p.entidad_id
LEFT OUTER JOIN Proyecto AS p
	ON e_p.proyecto_id = p.proyecto_id;
    
SELECT p.proyecto_id
	FROM Entidad e
LEFT OUTER JOIN entidad_proyecto e_p
	ON e.entidad_id = e_p.entidad_id
LEFT OUTER JOIN Proyecto AS p
	ON e_p.proyecto_id = p.proyecto_id
WHERE e_p.entidad_id = 1;

SELECT e.*
	FROM Proyecto p
LEFT OUTER JOIN entidad_proyecto e_p
	ON p.proyecto_id = e_p.proyecto_id
LEFT OUTER JOIN Entidad AS e
	ON e_p.entidad_id = e.entidad_id
WHERE e_p.proyecto_id = 3;

SELECT i.investigador_id
    FROM Proyecto p 
RIGHT JOIN Investigador i 
    ON i.proyecto_id = p.proyecto_id 
WHERE i.proyecto_id = 1;