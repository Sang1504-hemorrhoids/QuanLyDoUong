CREATE DATABASE QLDO;
GO
USE QLDO;
GO

CREATE TABLE Users
(
  UserId VARCHAR(10) NOT NULL,
  Username NVARCHAR(50) NOT NULL,
  Password VARCHAR(15) NOT NULL,
  Role NVARCHAR(15) NOT NULL,
  Status BIT DEFAULT 1,
  PRIMARY KEY (UserId)
);

CREATE TABLE Customers
(
  CustomerId VARCHAR(10) NOT NULL,
  CustomerName NVARCHAR(50) NOT NULL,
  PhoneNumber INT NOT NULL,
  Address NVARCHAR(50) NOT NULL,
  Email VARCHAR(50) NOT NULL,
  PRIMARY KEY (CustomerId)
);

CREATE TABLE Categories
(
  CategoryId VARCHAR(10) NOT NULL,
  Name NVARCHAR(50) NOT NULL,
  Desciption NVARCHAR(50) NOT NULL,
  PRIMARY KEY (CategoryId)
);

CREATE TABLE Promotion
(
  PromotionId VARCHAR(10) NOT NULL,
  PromoName NVARCHAR(50) NOT NULL,
  Discount FLOAT NOT NULL,
  StartDate DATE NOT NULL,
  EndDate DATE NOT NULL,
  Status BIT,
  PRIMARY KEY (PromotionId)
);

CREATE TABLE UnitConversion
(
  Id VARCHAR(10) NOT NULL,
  UnitName NVARCHAR(10) NOT NULL,
  Factor INT NOT NULL,
  IsBaseunit BIT,
  PRIMARY KEY (Id)
);

CREATE TABLE Bills
(
  BillId VARCHAR(10) NOT NULL,
  Total FLOAT NOT NULL,
  StartDate DATE NOT NULL,
  Status BIT,
  UserId VARCHAR(10) NOT NULL,
  PromotionId VARCHAR(10) NOT NULL,
  CustomerId VARCHAR(10) NOT NULL,
  PRIMARY KEY (BillId),
  FOREIGN KEY (UserId) REFERENCES Users(UserId),
  FOREIGN KEY (PromotionId) REFERENCES Promotion(PromotionId),
  FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId)
);
GO

CREATE TABLE Products
(
  ProductId VARCHAR(10) NOT NULL,
  Name NVARCHAR(50) NOT NULL,
  Quantity INT NOT NULL,
  SalePrice FLOAT NOT NULL,
  CostPrice FLOAT NOT NULL,
  Status BIT,
  CategoryId VARCHAR(10) NOT NULL,
  Id VARCHAR(10) NOT NULL,
  PRIMARY KEY (ProductId),
  FOREIGN KEY (CategoryId) REFERENCES Categories(CategoryId),
  FOREIGN KEY (Id) REFERENCES UnitConversion(Id)
);

CREATE TABLE Bill_Details
(
  BillDetail_Id VARCHAR(10) NOT NULL,
  Quantity INT NOT NULL,
  UnitPrice FLOAT NOT NULL,
  BillId VARCHAR(10) NOT NULL,
  ProductId VARCHAR(10) NOT NULL,
  Id VARCHAR(10) NOT NULL,
  PRIMARY KEY (BillDetail_Id),
  FOREIGN KEY (BillId) REFERENCES Bills(BillId),
  FOREIGN KEY (ProductId) REFERENCES Products(ProductId),
  FOREIGN KEY (Id) REFERENCES UnitConversion(Id)
);