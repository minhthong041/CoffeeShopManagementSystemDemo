-- Tạo CSDL
create database CoffeeShopManagement;
go
use CoffeeShopManagement;
go

-- Role table
create table Role (
	roleId varchar(10) primary key,
	roleName varchar(20) not null,
	typeOfWork varchar(20) not null
);

-- PaymentMethod table
create table PaymentMethod (
	paymentId int identity(1,1) primary key,
	methodName varchar(10) not null
);

-- Staff table
create table Staff (
	staffId int identity(1,1) primary key,
	firstName nvarchar(50) not null,
	middleName nvarchar(50),
	lastName nvarchar(50) not null,
	fullName as concat(lastName, ' ', middleName, ' ', firstName),
	sex varchar(10),
	dateOfBirth date not null,
	phoneNumber varchar(10) not null unique,
	email varchar(100) unique,
	address nvarchar(255),
	dateOfHiring date not null,
	password varchar(255) not null,
	status varchar(10) not null,
	roleId varchar(10) not null,
	constraint FK_Apply foreign key (roleId) references Role(roleId)
);

-- Customer table
create table Customer (
	customerId int identity(1,1) primary key,
	firstName nvarchar(50) not null,
	middleName nvarchar(50),
	lastName nvarchar(50) not null,
	fullName as concat(lastName, ' ', middleName, ' ', firstName),
	sex varchar(10),
	phoneNumber varchar(10) not null unique,
	email varchar(100) unique,
	address nvarchar(255),
	loyaltyPoint int default 0
);

-- Category table
create table Category (
	categoryId int identity(1, 1) primary key,
	categoryName varchar(100) not null
);

-- Product table
create table Product (
	productId int identity(1, 1) primary key,
	productName varchar(100) not null,
	price decimal(10, 2) not null,
	categoryId int,
	status varchar(10) not null,
	constraint FK_Include foreign key (categoryId) references Category(categoryId)
);

-- Order table
create table Orders (
	orderId int identity(1, 1) primary key,
	orderTime datetime default getdate(),
	description nvarchar(255),
	totalAmount decimal(10, 2),
	paymentId int,
	staffId int,
	customerId int,
	constraint FK_Paidby foreign key (paymentId) references PaymentMethod(paymentId),
	constraint FK_Receive foreign key (staffId) references Staff(staffId),
	constraint FK_Require foreign key (customerId) references Customer(customerId)
);

-- OrderDetail table
create table OrderDetail (
	orderId int,
	productId int,
	quantity int not null default 1,
	priceAtOrder decimal(10, 2) not null,
	primary key (orderId, productId),
	constraint FK_Have foreign key (orderId) references Orders(orderId),
	constraint FK_Isreferencedin foreign key (productId) references Product(productId)
);

go

-- Thêm dữ liệu
insert into Role (roleId, roleName, typeOfWork) values
('mgr', 'Manager', 'Fulltime'),
('baris_ft', 'Barista', 'Full time'),
('baris_pt', 'Barista', 'Part time'),
('wait_ft', 'Waiter/Waitress', 'Full time'),
('wait_pt', 'Waiter/Waitress', 'Part time');

insert into PaymentMethod (methodName) values
('Card'),
('Cash'),
('Transfer');

insert into Staff (firstName, middleName, lastName, sex, dateOfBirth, phoneNumber, email, address, dateOfHiring, password, status, roleId)
VALUES 
(N'Thông', N'Minh', N'Cao', 'Male', '2005-01-04', '0362420105', 'n23dccn195@student.ptithcm.edu.vn', N'Quận 9, TPHCM', '2025-11-30', '1234', 'Active', 'mgr'),
(N'Cường', N'Mạnh', N'Lê', 'Male', '2003-12-12', '0903456789', 'cuong.le@example.com', N'789 Đường 3/2, Quận 10, TP.HCM', '2024-01-10', '1234', 'Active', 'wait_pt'),
(N'Dũng', NULL, N'Phạm', 'Male', '1998-02-28', '0904567890', 'dung.pham@example.com', N'Số 10, Đường Hùng Vương, TP. HCM', '2024-02-01', '1234', 'Inactive', 'wait_ft');