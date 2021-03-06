USE [master]
GO
/****** Object:  Database [ResourceSharing]    Script Date: 7/18/2020 11:33:50 PM ******/
CREATE DATABASE [ResourceSharing]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ResourceSharing', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\ResourceSharing.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ResourceSharing_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\ResourceSharing_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [ResourceSharing] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ResourceSharing].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ResourceSharing] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ResourceSharing] SET ARITHABORT OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ResourceSharing] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ResourceSharing] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ResourceSharing] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ResourceSharing] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ResourceSharing] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ResourceSharing] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ResourceSharing] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ResourceSharing] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ResourceSharing] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ResourceSharing] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ResourceSharing] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ResourceSharing] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ResourceSharing] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ResourceSharing] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ResourceSharing] SET  MULTI_USER 
GO
ALTER DATABASE [ResourceSharing] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ResourceSharing] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ResourceSharing] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ResourceSharing] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ResourceSharing] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ResourceSharing] SET QUERY_STORE = OFF
GO
USE [ResourceSharing]
GO
/****** Object:  Table [dbo].[tbl_Booking]    Script Date: 7/18/2020 11:33:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Booking](
	[bookingID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](250) NULL,
	[createDate] [date] NULL,
 CONSTRAINT [PK_tbl_Booking] PRIMARY KEY CLUSTERED 
(
	[bookingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Category]    Script Date: 7/18/2020 11:33:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Category](
	[CategoryID] [int] NOT NULL,
	[CategoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Detail]    Script Date: 7/18/2020 11:33:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Detail](
	[detailID] [int] IDENTITY(1,1) NOT NULL,
	[resourceName] [nvarchar](50) NULL,
	[bookingID] [int] NULL,
	[resourceID] [int] NULL,
	[quantity] [int] NULL,
	[RentalDate] [date] NULL,
	[ReturnDate] [date] NULL,
	[statusID] [int] NULL,
 CONSTRAINT [PK_tbl_Detail] PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Resource]    Script Date: 7/18/2020 11:33:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Resource](
	[resourceID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[color] [nvarchar](50) NULL,
	[quantity] [int] NULL,
	[categoryID] [int] NULL,
	[roleID] [int] NULL,
	[statusID] [int] NULL,
 CONSTRAINT [PK_tbl_Resource\] PRIMARY KEY CLUSTERED 
(
	[resourceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 7/18/2020 11:33:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[roleID] [int] NOT NULL,
	[roleName] [varchar](50) NULL,
 CONSTRAINT [PK_tbl_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Status]    Script Date: 7/18/2020 11:33:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Status](
	[statusID] [int] NOT NULL,
	[statusName] [varchar](50) NULL,
 CONSTRAINT [PK_tbl_Status] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 7/18/2020 11:33:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
	[userID] [varchar](250) NOT NULL,
	[password] [varchar](50) NULL,
	[phone] [varchar](50) NULL,
	[name] [nvarchar](250) NULL,
	[address] [nvarchar](20) NULL,
	[createDate] [date] NULL,
	[statusID] [int] NULL,
	[roleID] [int] NULL,
 CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tbl_Booking] ON 

INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (1, N'employee@gmail.com', CAST(N'2020-07-16' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (2, N'employee@gmail.com', CAST(N'2020-07-16' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (3, N'employee@gmail.com', CAST(N'2020-07-16' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (4, N'leader@gmail.com', CAST(N'2020-07-16' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (5, N'leader@gmail.com', CAST(N'2020-07-16' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (6, N'leader@gmail.com', CAST(N'2020-07-17' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (7, N'employee@gmail.com', CAST(N'2020-07-17' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (8, N'employee@gmail.com', CAST(N'2020-07-17' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (11, N'employee@gmail.com', CAST(N'2020-07-17' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (12, N'employee@gmail.com', CAST(N'2020-07-17' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (13, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (14, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (15, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (16, N'leader@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (17, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (18, N'leader@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (19, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (20, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (21, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (22, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (23, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (24, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
INSERT [dbo].[tbl_Booking] ([bookingID], [userID], [createDate]) VALUES (25, N'employee@gmail.com', CAST(N'2020-07-18' AS Date))
SET IDENTITY_INSERT [dbo].[tbl_Booking] OFF
INSERT [dbo].[tbl_Category] ([CategoryID], [CategoryName]) VALUES (1, N'Bàn')
INSERT [dbo].[tbl_Category] ([CategoryID], [CategoryName]) VALUES (2, N'Ghế')
INSERT [dbo].[tbl_Category] ([CategoryID], [CategoryName]) VALUES (3, N'Bảng')
INSERT [dbo].[tbl_Category] ([CategoryID], [CategoryName]) VALUES (4, N'Máy chiếu')
INSERT [dbo].[tbl_Category] ([CategoryID], [CategoryName]) VALUES (5, N'Tivi')
INSERT [dbo].[tbl_Category] ([CategoryID], [CategoryName]) VALUES (6, N'Máy tính')
SET IDENTITY_INSERT [dbo].[tbl_Detail] ON 

INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (1, N'Ghe 2', 1, 8, 1, CAST(N'2020-07-16' AS Date), CAST(N'2020-07-29' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (2, N'Ghe 3', 2, 14, 4, CAST(N'2020-07-16' AS Date), CAST(N'2020-07-29' AS Date), 2)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (3, N'Ghe 3', 3, 14, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (4, N'Ghe 4', 3, 20, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (5, N'Ghe 1', 4, 2, 8, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (6, N'Bang 1', 5, 3, 12, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (7, N'Ghe 1', 6, 2, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (8, N'May Chieu 1', 6, 4, 1, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (9, N'Bang 1', 7, 3, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (10, N'May Chieu 1', 7, 4, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 2)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (11, N'Ban 1', 7, 1, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (12, N'Ghe 1', 7, 2, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 2)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (13, N'Bang 1', 7, 3, 1, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (14, N'May Chieu 1', 7, 4, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (15, N'Tivi 1', 7, 5, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (16, N'May Tinh 1', 7, 6, 1, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 2)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (17, N'Ban 2', 7, 7, 1, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (18, N'Ghe 2', 7, 8, 1, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (19, N'Bang 2', 7, 9, 1, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (20, N'May Chieu 2', 7, 10, 1, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (21, N'Tivi 2', 7, 11, 1, CAST(N'2020-07-15' AS Date), CAST(N'2020-07-16' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (22, N'Ghe 1', 8, 2, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 2)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (23, N'Bang 1', 11, 3, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (24, N'Ghe 1', 11, 2, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (25, N'Bang 1', 12, 3, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 2)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (26, N'Bang 1', 12, 3, 1, CAST(N'2020-07-16' AS Date), CAST(N'2020-07-29' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (27, N'Bang 1', 12, 3, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-29' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (28, N'Bang 1', 13, 3, 3, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (29, N'May Chieu 1', 13, 4, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (30, N'Bang 2', 14, 9, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (31, N'Tivi 1', 14, 5, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (32, N'May Tinh 2', 15, 12, 4, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 2)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (33, N'Bang 1', 16, 3, 4, CAST(N'2020-07-25' AS Date), CAST(N'2020-07-30' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (34, N'Tivi 1', 16, 5, 1, CAST(N'2020-07-25' AS Date), CAST(N'2020-07-30' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (35, N'Ghe 4', 17, 20, 5, CAST(N'2020-07-24' AS Date), CAST(N'2020-07-29' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (36, N'Ghe 2', 18, 8, 5, CAST(N'2020-07-25' AS Date), CAST(N'2020-07-30' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (37, N'May Tinh 2', 19, 12, 10, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-22' AS Date), 2)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (38, N'May Tinh 2', 20, 12, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-23' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (39, N'May Tinh 2', 21, 12, 2, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (40, N'May Tinh 2', 22, 12, 11, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 3)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (41, N'May Tinh 2', 23, 12, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (42, N'May Tinh 2', 24, 12, 1, CAST(N'2020-07-18' AS Date), CAST(N'2020-07-19' AS Date), 1)
INSERT [dbo].[tbl_Detail] ([detailID], [resourceName], [bookingID], [resourceID], [quantity], [RentalDate], [ReturnDate], [statusID]) VALUES (43, N'May Tinh 2', 25, 12, 5, CAST(N'2020-07-21' AS Date), CAST(N'2020-07-30' AS Date), 1)
SET IDENTITY_INSERT [dbo].[tbl_Detail] OFF
SET IDENTITY_INSERT [dbo].[tbl_Resource] ON 

INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (1, N'Ban 1', N'Pink', 12, 1, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (2, N'Ghe 1', N'Black', 12, 2, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (3, N'Bang 1', N'Blue', 12, 3, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (4, N'May Chieu 1', N'Blue', 12, 4, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (5, N'Tivi 1', N'Red', 12, 5, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (6, N'May Tinh 1', N'Pink', 12, 6, 2, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (7, N'Ban 2', N'Red', 12, 1, 2, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (8, N'Ghe 2', N'Blue', 12, 2, 2, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (9, N'Bang 2', N'Blue', 13, 3, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (10, N'May Chieu 2', N'Red', 13, 4, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (11, N'Tivi 2', N'Pink', 14, 5, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (12, N'May Tinh 2', N'Blue', 15, 6, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (13, N'Ban 3', N'Green', 15, 1, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (14, N'Ghe 3', N'Blue', 15, 2, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (15, N'Bang 3', N'Red', 151, 3, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (16, N'May Chieu 3', N'Pink', 13, 4, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (17, N'Tivi 3', N'Green', 13, 5, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (18, N'May Tinh 3', N'Green', 113, 6, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (19, N'Ban 4', N'Red', 13, 1, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (20, N'Ghe 4', N'Pink', 131, 2, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (21, N'Bang 4', N'Red', 31, 3, 3, 2)
INSERT [dbo].[tbl_Resource] ([resourceID], [name], [color], [quantity], [categoryID], [roleID], [statusID]) VALUES (22, N'May Chieu 4', N'Pink', 31, 4, 3, 2)
SET IDENTITY_INSERT [dbo].[tbl_Resource] OFF
INSERT [dbo].[tbl_Role] ([roleID], [roleName]) VALUES (1, N'manager')
INSERT [dbo].[tbl_Role] ([roleID], [roleName]) VALUES (2, N'leader')
INSERT [dbo].[tbl_Role] ([roleID], [roleName]) VALUES (3, N'employee')
INSERT [dbo].[tbl_Status] ([statusID], [statusName]) VALUES (1, N'New')
INSERT [dbo].[tbl_Status] ([statusID], [statusName]) VALUES (2, N'Active')
INSERT [dbo].[tbl_Status] ([statusID], [statusName]) VALUES (3, N'Delete')
INSERT [dbo].[tbl_User] ([userID], [password], [phone], [name], [address], [createDate], [statusID], [roleID]) VALUES (N'employee@gmail.com', N'1', NULL, N'EMPLOYEE', NULL, NULL, 2, 3)
INSERT [dbo].[tbl_User] ([userID], [password], [phone], [name], [address], [createDate], [statusID], [roleID]) VALUES (N'leader@gmail.com', N'1', NULL, N'LEADER', NULL, NULL, 2, 2)
INSERT [dbo].[tbl_User] ([userID], [password], [phone], [name], [address], [createDate], [statusID], [roleID]) VALUES (N'manager@gmail.com', N'1', NULL, N'MANAGER', NULL, NULL, 2, 1)
INSERT [dbo].[tbl_User] ([userID], [password], [phone], [name], [address], [createDate], [statusID], [roleID]) VALUES (N'nguyennstse130431@fpt.edu.vn', N'12345', N'1234567899', N'NguyenNST', N'123', CAST(N'2020-07-18' AS Date), 2, 3)
INSERT [dbo].[tbl_User] ([userID], [password], [phone], [name], [address], [createDate], [statusID], [roleID]) VALUES (N'nguyensitrieunguyen0805@gmail.com', N'12345', N'1234567899', N'NguyenNST', N'123', CAST(N'2020-07-18' AS Date), 2, 3)
ALTER TABLE [dbo].[tbl_Booking]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Booking_tbl_User] FOREIGN KEY([userID])
REFERENCES [dbo].[tbl_User] ([userID])
GO
ALTER TABLE [dbo].[tbl_Booking] CHECK CONSTRAINT [FK_tbl_Booking_tbl_User]
GO
ALTER TABLE [dbo].[tbl_Detail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Detail_tbl_Booking1] FOREIGN KEY([bookingID])
REFERENCES [dbo].[tbl_Booking] ([bookingID])
GO
ALTER TABLE [dbo].[tbl_Detail] CHECK CONSTRAINT [FK_tbl_Detail_tbl_Booking1]
GO
ALTER TABLE [dbo].[tbl_Detail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Detail_tbl_Resource] FOREIGN KEY([resourceID])
REFERENCES [dbo].[tbl_Resource] ([resourceID])
GO
ALTER TABLE [dbo].[tbl_Detail] CHECK CONSTRAINT [FK_tbl_Detail_tbl_Resource]
GO
ALTER TABLE [dbo].[tbl_Detail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Detail_tbl_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[tbl_Status] ([statusID])
GO
ALTER TABLE [dbo].[tbl_Detail] CHECK CONSTRAINT [FK_tbl_Detail_tbl_Status]
GO
ALTER TABLE [dbo].[tbl_Resource]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Resource_tbl_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tbl_Category] ([CategoryID])
GO
ALTER TABLE [dbo].[tbl_Resource] CHECK CONSTRAINT [FK_tbl_Resource_tbl_Category]
GO
ALTER TABLE [dbo].[tbl_Resource]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Resource_tbl_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[tbl_Role] ([roleID])
GO
ALTER TABLE [dbo].[tbl_Resource] CHECK CONSTRAINT [FK_tbl_Resource_tbl_Role]
GO
ALTER TABLE [dbo].[tbl_Resource]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Resource_tbl_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[tbl_Status] ([statusID])
GO
ALTER TABLE [dbo].[tbl_Resource] CHECK CONSTRAINT [FK_tbl_Resource_tbl_Status]
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD  CONSTRAINT [FK_tbl_User_tbl_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[tbl_Role] ([roleID])
GO
ALTER TABLE [dbo].[tbl_User] CHECK CONSTRAINT [FK_tbl_User_tbl_Role]
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD  CONSTRAINT [FK_tbl_User_tbl_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[tbl_Status] ([statusID])
GO
ALTER TABLE [dbo].[tbl_User] CHECK CONSTRAINT [FK_tbl_User_tbl_Status]
GO
USE [master]
GO
ALTER DATABASE [ResourceSharing] SET  READ_WRITE 
GO
