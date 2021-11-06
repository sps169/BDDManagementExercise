use postgres;

CREATE TABLE public.departamentos (
	id_departamento int8 NOT NULL,
	nombre_departamento varchar(25) NULL,
	presupuesto numeric NULL,
	id_jefe int8 NULL,
	CONSTRAINT departamentos_pkey PRIMARY KEY (id_departamento)
);

CREATE TABLE public.programadores (
	id_programador int8 NOT NULL,
	nombre_programador varchar(25) NULL,
	experiencia varchar(25) NULL,
	salario numeric NULL,
	id_departamento int8 NULL,
	lenguajes varchar(200) NULL,
	CONSTRAINT programadores_pkey PRIMARY KEY (id_programador)
);

INSERT INTO public.departamentos (id_departamento,nombre_departamento,presupuesto,id_jefe) VALUES
	 (0,'navidad',1000,0),
	 (1,'caramelos',1600,1);

INSERT INTO public.programadores (id_programador,nombre_programador,experiencia,salario,id_departamento,lenguajes) VALUES
	 (0,'jefejuan','20',3,0,'Java;Kotlin;Python'),
	 (1,'jefejose','8',5,1,'C'),
	 (2,'patricio estrella','-1',-20,0,'JavaScript'),
	 (3,'bob esponja','10',1,0,'JavaScript;Java'),
	 (4,'arenita','5',1000,0,'JavaScript;Kotlin'),
	 (5,'cangrejo','200',10000,0,'JavaScript;C'),
	 (6,'calamardo','3',15,1,'JavaScript;C;Java'),
	 (7,'gary','100000',1000000,1,'JavaScript;Python'),
	 (8,'pirata','5',900000,1,'JavaScript;C'),
	 (9,'perla','2',50000,1,'JavaScript;Kotlin');