

USE WINDOWS

GO

CREATE TABLE IF NOT EXISTS CATEGORY(
	ID INT,
	MANUFACTURER NVARCHAR(100),
	PRIMARY KEY(ID)
)

GO

CREATE TABLE IF NOT EXISTS DISCOUNT(
	ID INT,
	PERCENT_DISCOUNT INT,
	TIME_BEGIN DATETIME,
	TIME_END DATETIME,
	PRIMARY KEY(ID)
)

GO

CREATE TABLE IF NOT EXISTS PHONE(
	ID INT, 
	NAME NVARCHAR(100),
	SCREEN_SIZE CHAR(20),
	CPU CHAR(20),
	RAM INT,
	MEMORY INT,
	PIN INT,
	FRONT_CAM INT,
	BACK_CAM INT,
	PRICE INT,
	ID_CATEGORY INT,
	PRIMARY KEY(ID)
)

GO 

CREATE TABLE IF NOT EXISTS INVENTORY(
	STT INT,
	COUNT INT,
	DATE_IMPORT DATE,
	ID_PHONE INT,
	PRIMARY KEY(STT)
)

GO

CREATE TABLE IF NOT EXISTS BILL(
	ID CHAR(15),
	DATE_SELL DATE,
	COUNT INT,
	STATUS NVARCHAR(100),
	DISCRIPTION TEXT,
	ID_PHONE INT,
	ID_DISCOUNT INT,
	ID_PAYMENT INT,
	PRIMARY KEY(ID)
)

GO

CREATE TABLE IF NOT EXISTS PAYMENT(
	ID INT,
	METHOD NVARCHAR(100),
	PRIMARY KEY(ID)
)

GO
	
CREATE TABLE IF NOT EXISTS ACCOUNT(
	USERNAME CHAR(20),
	PASSWORD CHAR(100),
	PRIMARY KEY (USERNAME)
)

GO

ALTER TABLE PHONE ADD CONSTRAINT FK_PHONE_CATEGORY FOREIGN KEY(ID_CATEGORY) REFERENCES CATEGORY(ID)
GO

ALTER TABLE INVENTORY ADD CONSTRAINT FK_INVENTORY_PHONE FOREIGN KEY(ID_PHONE) REFERENCES PHONE(ID)
GO

ALTER TABLE BILL ADD CONSTRAINT FK_BILL_PHONE FOREIGN KEY(ID_PHONE) REFERENCES PHONE(ID)
GO

ALTER TABLE BILL ADD CONSTRAINT FK_BILL_DISCOUNT FOREIGN KEY(ID_DISCOUNT) REFERENCES DISCOUNT(ID)
GO

ALTER TABLE BILL ADD CONSTRAINT FK_BILL_PAYMENT FOREIGN KEY(ID_PAYMENT) REFERENCES PAYMENT(ID)
GO
