USE [master]
GO
/****** Object:  Database [CarRental]    Script Date: 3/18/2020 1:23:29 AM ******/
CREATE DATABASE [CarRental]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CarRental', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CarRental.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'CarRental_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CarRental_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [CarRental] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CarRental].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CarRental] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CarRental] SET ARITHABORT OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CarRental] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CarRental] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CarRental] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CarRental] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CarRental] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CarRental] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CarRental] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CarRental] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CarRental] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CarRental] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CarRental] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CarRental] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CarRental] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CarRental] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CarRental] SET RECOVERY FULL 
GO
ALTER DATABASE [CarRental] SET  MULTI_USER 
GO
ALTER DATABASE [CarRental] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CarRental] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CarRental] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CarRental] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [CarRental] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'CarRental', N'ON'
GO
USE [CarRental]
GO
/****** Object:  Table [dbo].[BillDetails]    Script Date: 3/18/2020 1:23:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BillDetails](
	[Email] [varchar](50) NOT NULL,
	[Price] [int] NOT NULL,
	[carName] [varchar](50) NULL,
	[color] [varchar](50) NULL,
	[quantity] [int] NULL,
	[rentalDate] [date] NULL,
	[returnDate] [date] NULL,
	[total] [int] NULL,
	[idBill] [int] NOT NULL,
 CONSTRAINT [PK_BillDetails] PRIMARY KEY CLUSTERED 
(
	[Email] ASC,
	[Price] ASC,
	[idBill] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Cars]    Script Date: 3/18/2020 1:23:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cars](
	[carName] [varchar](50) NOT NULL,
	[color] [varchar](50) NOT NULL,
	[price] [int] NOT NULL,
	[quantity] [int] NULL,
	[categoryID] [int] NULL,
	[status] [varchar](50) NULL,
 CONSTRAINT [PK_Cars] PRIMARY KEY CLUSTERED 
(
	[price] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Category]    Script Date: 3/18/2020 1:23:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[categoryID] [int] NOT NULL,
	[nameCategory] [varchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DiscountCodes]    Script Date: 3/18/2020 1:23:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DiscountCodes](
	[DiscountCode] [varchar](50) NOT NULL,
	[value] [int] NULL,
	[dateFrom] [date] NULL,
	[dateTo] [date] NULL,
 CONSTRAINT [PK_DiscountCodes] PRIMARY KEY CLUSTERED 
(
	[DiscountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[FeedBacks]    Script Date: 3/18/2020 1:23:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[FeedBacks](
	[idFeedBack] [int] NOT NULL,
	[email] [varchar](50) NULL,
	[carName] [varchar](50) NULL,
	[color] [varchar](50) NULL,
	[price] [int] NULL,
	[contentFB] [varchar](255) NULL,
	[rate] [int] NULL,
 CONSTRAINT [PK_FeedBacks] PRIMARY KEY CLUSTERED 
(
	[idFeedBack] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderBills]    Script Date: 3/18/2020 1:23:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderBills](
	[idBill] [int] NOT NULL,
	[Email] [varchar](50) NULL,
	[orderDate] [datetime] NULL,
	[numOfCars] [int] NULL,
	[total] [float] NULL,
	[DiscountCode] [varchar](50) NULL,
	[Status] [varchar](50) NULL,
 CONSTRAINT [PK_OrderBills] PRIMARY KEY CLUSTERED 
(
	[idBill] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Registrations]    Script Date: 3/18/2020 1:23:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registrations](
	[Email] [varchar](50) NOT NULL,
	[Password] [varchar](50) NULL,
	[Fullname] [varchar](50) NULL,
	[Phone] [varchar](50) NULL,
	[Address] [varchar](50) NULL,
	[createDate] [datetime] NULL,
	[Role] [varchar](50) NULL,
	[Status] [varchar](50) NULL,
 CONSTRAINT [PK_Registrations] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 1500, N'Mercedes C', N'Black', 1, CAST(N'2020-03-21' AS Date), CAST(N'2020-03-23' AS Date), 1500, 4)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 1500, N'Mercedes C', N'Black', 5, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-19' AS Date), 7500, 7)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 1700, N'Mercedes C', N'Silver', 1, CAST(N'2020-03-16' AS Date), CAST(N'2020-03-18' AS Date), 1700, 3)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 1700, N'Mercedes C', N'Silver', 1, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-17' AS Date), 1700, 5)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 1700, N'Mercedes C', N'Silver', 1, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-17' AS Date), 1700, 6)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 1700, N'Mercedes C', N'Silver', 3, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-17' AS Date), 5100, 8)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 2000, N'Audi A4', N'White', 1, CAST(N'2020-03-10' AS Date), CAST(N'2020-03-14' AS Date), 2000, 3)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 2000, N'Audi A4', N'White', 4, CAST(N'2020-03-13' AS Date), CAST(N'2020-03-17' AS Date), 8000, 4)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 2000, N'Audi A4', N'White', 1, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-17' AS Date), 2000, 5)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 2000, N'Audi A4', N'White', 1, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-17' AS Date), 2000, 6)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 2000, N'Audi A4', N'White', 2, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-17' AS Date), 4000, 9)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 3500, N'BMW i8', N'Blue', 1, CAST(N'2020-03-15' AS Date), CAST(N'2020-03-16' AS Date), 3500, 4)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 3500, N'BMW i8', N'Blue', 3, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-17' AS Date), 10500, 10)
INSERT [dbo].[BillDetails] ([Email], [Price], [carName], [color], [quantity], [rentalDate], [returnDate], [total], [idBill]) VALUES (N'success@gmail.com', 3500, N'BMW i8', N'Blue', 1, CAST(N'2020-03-17' AS Date), CAST(N'2020-03-17' AS Date), 3500, 11)
INSERT [dbo].[Cars] ([carName], [color], [price], [quantity], [categoryID], [status]) VALUES (N'Mercedes C', N'Black', 1500, 20, 1, N'Active')
INSERT [dbo].[Cars] ([carName], [color], [price], [quantity], [categoryID], [status]) VALUES (N'Mercedes C', N'Silver', 1700, 10, 1, N'Active')
INSERT [dbo].[Cars] ([carName], [color], [price], [quantity], [categoryID], [status]) VALUES (N'Audi A4', N'White', 2000, 15, 6, N'Active')
INSERT [dbo].[Cars] ([carName], [color], [price], [quantity], [categoryID], [status]) VALUES (N'BMW i8', N'Blue', 3500, 15, 2, N'Active')
INSERT [dbo].[Category] ([categoryID], [nameCategory]) VALUES (1, N'Mercedes')
INSERT [dbo].[Category] ([categoryID], [nameCategory]) VALUES (2, N'BMW')
INSERT [dbo].[Category] ([categoryID], [nameCategory]) VALUES (3, N'Vinfast')
INSERT [dbo].[Category] ([categoryID], [nameCategory]) VALUES (4, N'Laxus')
INSERT [dbo].[Category] ([categoryID], [nameCategory]) VALUES (5, N'Toyota')
INSERT [dbo].[Category] ([categoryID], [nameCategory]) VALUES (6, N'Audi')
INSERT [dbo].[DiscountCodes] ([DiscountCode], [value], [dateFrom], [dateTo]) VALUES (N'code1', 3, CAST(N'2020-03-15' AS Date), CAST(N'2020-03-20' AS Date))
INSERT [dbo].[DiscountCodes] ([DiscountCode], [value], [dateFrom], [dateTo]) VALUES (N'code2', 10, CAST(N'2020-03-20' AS Date), CAST(N'2020-03-25' AS Date))
INSERT [dbo].[FeedBacks] ([idFeedBack], [email], [carName], [color], [price], [contentFB], [rate]) VALUES (1, N'success@gmail.com', N'Audi A4', N'White', 2000, N'good', 8)
INSERT [dbo].[FeedBacks] ([idFeedBack], [email], [carName], [color], [price], [contentFB], [rate]) VALUES (2, N'success@gmail.com', N'Audi A4', N'White', 2000, N'do', 5)
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (3, N'success@gmail.com', CAST(N'2020-03-15 00:21:15.533' AS DateTime), 2, 3700, NULL, N'Active')
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (4, N'success@gmail.com', CAST(N'2020-03-17 00:23:17.207' AS DateTime), 3, 13000, NULL, N'Active')
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (5, N'success@gmail.com', CAST(N'2020-03-10 01:35:55.027' AS DateTime), 2, 3589, N'code1', N'Active')
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (6, N'success@gmail.com', CAST(N'2020-03-17 01:40:08.230' AS DateTime), 2, 3700, NULL, N'Active')
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (7, N'success@gmail.com', CAST(N'2020-03-17 01:40:27.637' AS DateTime), 1, 7500, NULL, N'Inactive')
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (8, N'success@gmail.com', CAST(N'2020-03-17 01:42:32.087' AS DateTime), 1, 5100, NULL, N'Active')
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (9, N'success@gmail.com', CAST(N'2020-03-17 08:27:01.363' AS DateTime), 1, 4000, NULL, N'Inactive')
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (10, N'success@gmail.com', CAST(N'2020-03-17 08:27:18.277' AS DateTime), 1, 10500, NULL, N'Active')
INSERT [dbo].[OrderBills] ([idBill], [Email], [orderDate], [numOfCars], [total], [DiscountCode], [Status]) VALUES (11, N'success@gmail.com', CAST(N'2020-03-17 08:27:32.227' AS DateTime), 1, 3395, N'code1', N'Active')
INSERT [dbo].[Registrations] ([Email], [Password], [Fullname], [Phone], [Address], [createDate], [Role], [Status]) VALUES (N'hihi@gmail.com', N'123456', N'Hi Hi', N'1234567891', N'Binh Duong', CAST(N'2020-03-08 22:25:50.593' AS DateTime), N'member', N'New')
INSERT [dbo].[Registrations] ([Email], [Password], [Fullname], [Phone], [Address], [createDate], [Role], [Status]) VALUES (N'success@gmail.com', N'123456', N'Success', N'1234567899', N'BD', CAST(N'2020-03-08 12:50:11.157' AS DateTime), N'member', N'Active')
ALTER TABLE [dbo].[BillDetails]  WITH CHECK ADD  CONSTRAINT [FK_BillDetails_Cars] FOREIGN KEY([Price])
REFERENCES [dbo].[Cars] ([price])
GO
ALTER TABLE [dbo].[BillDetails] CHECK CONSTRAINT [FK_BillDetails_Cars]
GO
ALTER TABLE [dbo].[BillDetails]  WITH CHECK ADD  CONSTRAINT [FK_BillDetails_OrderBills] FOREIGN KEY([idBill])
REFERENCES [dbo].[OrderBills] ([idBill])
GO
ALTER TABLE [dbo].[BillDetails] CHECK CONSTRAINT [FK_BillDetails_OrderBills]
GO
ALTER TABLE [dbo].[BillDetails]  WITH CHECK ADD  CONSTRAINT [FK_BillDetails_Registrations] FOREIGN KEY([Email])
REFERENCES [dbo].[Registrations] ([Email])
GO
ALTER TABLE [dbo].[BillDetails] CHECK CONSTRAINT [FK_BillDetails_Registrations]
GO
ALTER TABLE [dbo].[Cars]  WITH CHECK ADD  CONSTRAINT [FK_Cars_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO
ALTER TABLE [dbo].[Cars] CHECK CONSTRAINT [FK_Cars_Category]
GO
ALTER TABLE [dbo].[FeedBacks]  WITH CHECK ADD  CONSTRAINT [FK_FeedBacks_Cars] FOREIGN KEY([price])
REFERENCES [dbo].[Cars] ([price])
GO
ALTER TABLE [dbo].[FeedBacks] CHECK CONSTRAINT [FK_FeedBacks_Cars]
GO
ALTER TABLE [dbo].[FeedBacks]  WITH CHECK ADD  CONSTRAINT [FK_FeedBacks_Registrations] FOREIGN KEY([email])
REFERENCES [dbo].[Registrations] ([Email])
GO
ALTER TABLE [dbo].[FeedBacks] CHECK CONSTRAINT [FK_FeedBacks_Registrations]
GO
ALTER TABLE [dbo].[OrderBills]  WITH CHECK ADD  CONSTRAINT [FK_OrderBills_DiscountCodes] FOREIGN KEY([DiscountCode])
REFERENCES [dbo].[DiscountCodes] ([DiscountCode])
GO
ALTER TABLE [dbo].[OrderBills] CHECK CONSTRAINT [FK_OrderBills_DiscountCodes]
GO
ALTER TABLE [dbo].[OrderBills]  WITH CHECK ADD  CONSTRAINT [FK_OrderBills_Registrations] FOREIGN KEY([Email])
REFERENCES [dbo].[Registrations] ([Email])
GO
ALTER TABLE [dbo].[OrderBills] CHECK CONSTRAINT [FK_OrderBills_Registrations]
GO
USE [master]
GO
ALTER DATABASE [CarRental] SET  READ_WRITE 
GO
