-- База данных СКЛАД, созданная для работы с JDBC

use master


CREATE DATABASE WarehouseDB
CONTAINMENT = NONE
ON  PRIMARY 
( NAME = N'WarehouseDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.DEV\MSSQL\DATA\WarehouseDB.mdf' , SIZE = 8192KB , FILEGROWTH = 65536KB )
LOG ON 
( NAME = N'WarehouseDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.DEV\MSSQL\DATA\WarehouseDB_log.ldf' , SIZE = 8192KB , FILEGROWTH = 65536KB )

COLLATE Cyrillic_General_CS_AS


 GO 
 use WarehouseDB;
 go

 CREATE SCHEMA ProductSchema
 GO

 CREATE SCHEMA AccountingSchema
 GO

 
---Создание таблицы КАТЕГОРИЯ - типы товаров, категории
CREATE TABLE ProductSchema.category
(
		category_id          BIGINT PRIMARY KEY IDENTITY(1,1),
		name_category        NVARCHAR(30),
		description_category NVARCHAR(255),
);

-- Создание таблицы сотрудники (люди)
CREATE TABLE AccountingSchema.staff
(
		staff_id             BIGINT PRIMARY KEY IDENTITY(1,1),
		staff_name           NVARCHAR(30),
		staff_surname        NVARCHAR(30),
		position	      		 NVARCHAR(30),
		telephone            NVARCHAR(12)
);


---Создание таблицы Товары (продукты на англ)
CREATE TABLE ProductSchema.product
(
		product_id           BIGINT PRIMARY KEY IDENTITY(1,1),
		category_id          BIGINT NOT NULL,
		name_product         NVARCHAR(30),
		price				 INT

		CONSTRAINT "FK_product_category"
			FOREIGN KEY (category_id) REFERENCES ProductSchema.category(category_id) 
					ON DELETE CASCADE,
);

-- drop table ProductSchema.product
-- Создание таблицы склад
CREATE TABLE AccountingSchema.warehouse
(
		warehouse_id         BIGINT PRIMARY KEY IDENTITY(1,1),
		city_name            NVARCHAR(30),
);
-- drop table AccountingSchema.warehouse


CREATE TABLE AccountingSchema.storage
(

		warehouse_id         BIGINT,
		product_id           BIGINT,
		amount               INT

		CONSTRAINT "FK_STORAGE_warehouse_ID"
			FOREIGN KEY (warehouse_id) REFERENCES AccountingSchema.warehouse(warehouse_id) 
					ON DELETE CASCADE,
		CONSTRAINT "FK_STORAGE_product_id"
			FOREIGN KEY (product_id) REFERENCES ProductSchema.product(product_id) 
					ON DELETE CASCADE,

		CONSTRAINT "PK_STORAGE" PRIMARY KEY (warehouse_id, product_id)
);

-- drop table AccountingSchema.storage


-- Создание таблицы Приходы (новые продукты; товары)
CREATE TABLE AccountingSchema.new_product
(
		arriving_id          BIGINT IDENTITY(1,1),
		product_id           BIGINT,
		warehouse_id         BIGINT,
		staff_id             BIGINT,
		amount	      		 INT,
		price                INT,
		arrived_date	         DATE DEFAULT getdate()
		
		CONSTRAINT "FK_new_product"
			FOREIGN KEY (warehouse_id, product_id) REFERENCES AccountingSchema.storage,

		CONSTRAINT "FK_new_product_STAFF_ID"
			FOREIGN KEY (staff_id) REFERENCES AccountingSchema.staff(staff_id) 
					ON DELETE CASCADE,

		CONSTRAINT "PK_new_product" PRIMARY KEY (arriving_id, warehouse_id, product_id, staff_id)
);

-- drop table AccountingSchema.new_product

-- Создание таблицы Приходы (новые продукты; товары)
CREATE TABLE AccountingSchema.send_product
(
		arriving_id          BIGINT IDENTITY(1,1),
		product_id           BIGINT,
		warehouse_id         BIGINT,
		staff_id             BIGINT,
		amount	      		 INT,
		price                INT,
		send_date	         DATE DEFAULT getdate()
		
		CONSTRAINT "FK_send_product"
			FOREIGN KEY (warehouse_id, product_id) REFERENCES AccountingSchema.storage,

		CONSTRAINT "FK_send_product_STAFF_ID"
			FOREIGN KEY (staff_id) REFERENCES AccountingSchema.staff(staff_id) 
					ON DELETE CASCADE,

		CONSTRAINT "PK_send_product" PRIMARY KEY (arriving_id, warehouse_id, product_id, staff_id)
);

-- drop table AccountingSchema.send_product

INSERT INTO AccountingSchema.staff
	(staff_name, staff_surname, position, telephone)
VALUES
	('Иван', 'Иванович', 'Отдел продаж', '87776665656'),
	('Андрей', 'Никитин', 'Склад', '87776665633'),
	('Николай', 'Андреев', 'Склад', '87476665656'),
	('Татьяна', 'Ивановна', 'Бухгалгер', '87723423656')


INSERT INTO ProductSchema.category
	(name_category, description_category)
VALUES
	('Книги', 'Книги, журналы, газеты все тоары для чтения'),
	('Игрушки', 'Игрушки, рисование, товары для творчества'),
	('Канцтовары', 'Товары для школы и учебы'),
	('Другое', 'Другие тоары не принадлежащие определенной категории')

-- Cклад в городе только один =)
INSERT INTO AccountingSchema.warehouse
VALUES
	('Алматы'),
	('Астана')


INSERT INTO ProductSchema.product
	(category_id, name_product, price)
VALUES
	(1, 'Отцы и дети', 1200),
	(1, 'Сборник сочинений А.С. Пушкина', 6000),
	(1, 'Энциклопедия животных', 7200),
	(1, 'Алхимик', 750),
	(1, 'Чистый код', 7800),  --5
	(2, 'Раскраска морской мир', 350),
	(2, 'Попит', 1200),
	(2, 'Кукла Барби', 17200),
	(3, 'Ластик', 150),
	(3, 'Тетрадь 12 листов', 50),
	(3, 'Ручка шариковая', 250),
	(3, 'Карандаш простой', 150)



INSERT INTO AccountingSchema.storage
	(warehouse_id, product_id, amount)
VALUES
	(1, 1, 20),
	(1, 6, 120),
	(1, 3, 5),
	(1, 4, 60),
	(1, 5, 15),
	(1, 8, 150),
	(1, 9, 250),
	(1, 10, 1500),
	(1, 12, 560),
	(2, 1, 120),
	(2, 2, 220),
	(2, 3, 90),
	(2, 7, 80),
	(2, 6, 75),
	(2, 8, 650),
	(2, 11, 120),
	(2, 10, 2500),
	(2, 12, 80)


	-- delete from  AccountingSchema.new_product

select * from AccountingSchema.new_product
INSERT INTO AccountingSchema.new_product
	(product_id, warehouse_id, staff_id, amount)
VALUES
	(1,  1,  1, 10),
	(10, 1,  2, 120),
	(9,  1,  1, 90),
	(4, 1,  1, 80),
	(1,  1,  1, 70),
	(8,  1,  1, 50),
	(2,  2,  1, 40),
	(2, 2,  2, 30),
	(6, 2,  1, 70),
	(7,  2,  1, 50)



INSERT INTO AccountingSchema.send_product
	(product_id, warehouse_id, staff_id, amount)
VALUES
	(1,  1,  1, 1),
	(3,  1,  1, 9),
	(4, 1,  1, 8),
	(5, 1,  1, 7),
	(6,  1,  1, 5),
	(7,  2,  1, 4),
	(8, 2,  2, 3),
	(10, 2,  1, 8),
	(11,  2,  1, 7),
	(11,  2,  1, 5)

GO

select * from ProductSchema.product
GO

-- Представление всех товаров по категориям и количеству
create view ProductSchema.show_products
as
	select c.name_category, p.name_product, p.price, sum(s.amount) as amount
	from ProductSchema.product as p
	FULL JOIN ProductSchema.category as c    		
		on c.category_id = p.category_id
	INNER JOIn AccountingSchema.storage as s
		on s.product_id = p.product_id
	group by c.name_category, p.name_product, p.price

GO

SELECT * FROM ProductSchema.show_products

GO
-- Представление всех товаров по категориям и количеству, а также номеру склада
create view ProductSchema.show_products_by_warehouse
as
	select TOP 100 PERCENT s.warehouse_id, c.name_category, p.name_product, p.price, sum(s.amount) as amount
	from ProductSchema.product as p
	INNER JOIN ProductSchema.category as c    		
		on c.category_id = p.category_id
	INNER JOIn AccountingSchema.storage as s
		on s.product_id = p.product_id
	group by c.name_category, p.name_product, p.price, s.warehouse_id
	order by s.warehouse_id

GO
SELECT * FROM ProductSchema.show_products_by_warehouse where warehouse_id = 1