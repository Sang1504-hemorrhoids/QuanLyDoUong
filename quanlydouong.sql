CREATE DATABASE quanlydouong;
GO
USE quanlydouong;
GO

CREATE TABLE ThanhVien
(
  MaThanhvien VARCHAR(10) NOT NULL,
  TenThanhvien NVARCHAR(50) NOT NULL,
  Matkhau VARCHAR(15) NOT NULL,
  Chucvu NVARCHAR(15) NOT NULL,
  Trangthai BIT DEFAULT 1,
  PRIMARY KEY (MaThanhvien)
);

CREATE TABLE Khach
(
  MaKhachhang VARCHAR(10) NOT NULL,
  TenKhachhang NVARCHAR(50) NOT NULL,
  Sodienthoai INT NOT NULL,
  Diachi NVARCHAR(50) NOT NULL,
  Email VARCHAR(50) NOT NULL,
  PRIMARY KEY (MaKhachhang)
);

CREATE TABLE Loaidouong
(
  Maloai VARCHAR(10) NOT NULL,
  Tenloai NVARCHAR(50) NOT NULL,
  Mota NVARCHAR(50) NOT NULL,
  PRIMARY KEY (Maloai)
);

CREATE TABLE Khuyenmai
(
  MaKhuyenmai VARCHAR(10) NOT NULL,
  TenKhuyenmai NVARCHAR(50) NOT NULL,
  Giamgia FLOAT NOT NULL,
  Ngaybatdau DATE NOT NULL,
  Ngayketthuc DATE NOT NULL,
  Trangthai BIT,
  PRIMARY KEY (MaKhuyenmai)
);

CREATE TABLE Donvitinh
(
  MaDonvitinh VARCHAR(10) NOT NULL,
  MaSanpham VARCHAR(10) NOT NULL,
  TenDonvitinh NVARCHAR(10) NOT NULL,
  Hequydoi INT NOT NULL,
  IsDonvi BIT,
  PRIMARY KEY (MaDonvitinh)
);

CREATE TABLE Hoadon
(
  MaHoadon VARCHAR(10) NOT NULL,
  Trangthai BIT,
  Tongtien FLOAT NOT NULL,
  Ngaykhoitao DATE NOT NULL,
  MaThanhvien VARCHAR(10) NOT NULL,
  MaKhuyenmai VARCHAR(10) NOT NULL,
  MaKhachhang VARCHAR(10) NOT NULL,
  PRIMARY KEY (MaHoadon),
  FOREIGN KEY (MaThanhvien) REFERENCES ThanhVien(MaThanhvien),
  FOREIGN KEY (MaKhuyenmai) REFERENCES Khuyenmai(MaKhuyenmai),
  FOREIGN KEY (MaKhachhang) REFERENCES Khach(MaKhachhang)
);
GO

CREATE TABLE Sanpham
(
  MaSanpham VARCHAR(10) NOT NULL,
  TenSanpham NVARCHAR(50) NOT NULL,
  Soluong INT NOT NULL,
  Dongia FLOAT NOT NULL,
  Giaban FLOAT NOT NULL,
  Trangthai BIT,
  Maloai VARCHAR(10) NOT NULL,
  MaDonvitinh VARCHAR(10) NOT NULL,
  PRIMARY KEY (MaSanpham),
  FOREIGN KEY (Maloai) REFERENCES Loaidouong(Maloai),
  FOREIGN KEY (MaDonvitinh) REFERENCES Donvitinh(MaDonvitinh)
);

CREATE TABLE Hoadon_chitiet
(
  MaHoadonChitiet VARCHAR(10) NOT NULL,
  Soluong INT NOT NULL,
  Giaban FLOAT NOT NULL,
  MaHoadon VARCHAR(10) NOT NULL,
  MaSanpham VARCHAR(10) NOT NULL,
  MaDonvitinh VARCHAR(10) NOT NULL,
  PRIMARY KEY (MaHoadonChitiet),
  FOREIGN KEY (MaHoadon) REFERENCES Hoadon(MaHoadon),
  FOREIGN KEY (MaSanpham) REFERENCES Sanpham(MaSanpham),
  FOREIGN KEY (MaDonvitinh) REFERENCES Donvitinh(MaDonvitinh)
);