drop database quanlycafe;

create database quanlycafe;

use quanlycafe;

create table nhanvien
(
	manv varchar(5) primary key not null,
    tennv varchar(50) not null,
    ngaysinh date not null,
    diachi varchar(50) not null,
    sodt varchar(12) not null,
	gioitinh varchar(5) not null,
    username varchar(20) not null,
    pass varchar(20) not null, 
    roll tinyint not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
alter table nhanvien 
add constraint d
check (roll in (0, 1));

insert into nhanvien values
('NV001', 'Lee Sin Ma Sứ', '2020/01/01', 'Đi rừng', '0100225366', 'Nam', 'admin', 'admin', 0),
('NV002', 'Master Yi Thợ Săn Chiến Đấu', '2019/01/01', 'Đi rừng', '01002253555', 'Nữ', 'quanlh', '123456', 1),
('NV003', 'Miss Fortune Giả Lập', '2017/01/02', 'AD carry', '01002253555', 'Nam', 'java01', '654321', 1);
select * from nhanvien;
create table khachhang
(
	makh varchar(10) not null primary key default 'KhachLe',
    tenkh varchar(30) not null,
    diachi varchar(50) not null,
    sodt varchar(12) not null,
    diemtichluy float
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into khachhang values
('KH001', 'Lý Thị Tèo', 'Hồ Chí Minh', '0988993047', null),
('KH002', 'Vạn Lý Trường Thành', 'Trung Của', '0988663044', null),
('KH003', 'Tám Vạn Năm', 'Thanh Hóa', '0988912549', null),
('KH004', 'Bánh Rán', 'Nha Trang', '089993044', null),
('KH005', 'Maria Obama', 'Japan', '0988969988', null);
select * from khachhang;
create table ban
(
	maban tinyint not null primary key,
    tenban varchar(10) not null,
    trangthai tinyint not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

alter table ban 
add constraint d
check (trangthai in (0, 1));

insert into ban values
(1, 'Bàn 1', 1),
(2, 'Bàn 2', 1),
(3, 'Bàn 3', 0),
(4, 'Bàn 4', 1),
(5, 'Bàn 5', 0),
(6, 'Bàn 6', 0),
(7, 'Bàn 7', 0),
(8, 'Bàn 8', 1),
(9, 'Bàn 9', 0),
(10, 'Bàn 10', 1),
(11, 'Bàn 11', 1),
(13, 'Bàn 12', 1),
(14, 'Bàn 13', 1),
(15, 'Bàn 14', 0),
(16, 'Bàn 15', 0),
(17, 'Bàn 16', 1),
(18, 'Bàn 17', 0),
(19, 'Bàn 18', 0),
(25, 'Bàn 19', 1),
(31, 'Bàn 20', 0),
(32, 'Bàn 21', 0);
select * from ban;
select count(*) from ban where trangthai = 0;

create table loaimon
(
	maloai varchar(5) not null primary key,	
    tenloai varchar(20) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into loaimon values 
('L001', 'Cà phê'),
('L002', 'Nước đóng chai'),
('L003', 'Sinh tố'),
('L004', 'Đào');
select * from loaimon;

create table mon 
(
	mamon varchar(5) not null primary key,
    tenmon varchar(25) not null,
    maloai varchar(5) not null,
    dvt varchar(10) not null,
    dongia float not null,
    soluongton int not null,
    foreign key (maloai) references loaimon (maloai)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into mon values
('M001', 'Cà phê đen','L001', 'ly', 15000, 100),
('M002', 'Cà phê đen đá','L001', 'ly', 15000, 50),
('M003', 'Cà phê sữa','L001', 'ly', 17000, 60),
('M004', 'Cà phê sữa đá','L001', 'ly', 19000, 25),
('M005', 'Sting','L002', 'lon', 15000, 11),
('M006', 'Red bull','L002', 'lon', 20000, 50),
('M007', 'Coca cola','L002', 'lon', 18000, 200),
('M008', 'Sữa đậu nành','L002', 'chai', 21000, 22),
('M009', 'Cam ép','L003', 'ly', 15000, 33),
('M010', 'Sinh tố bơ','L003', 'ly', 35000, 44),
('M011', 'Sinh tố chanh','L003', 'ly', 145000, 64),
('M012', 'Sữa nguyên bình','L003', 'bát', 500000, 19);
insert into mon values
('M013', 'Đào sinh viên','L004', 'em', 800000, 2),
('M014', 'Đào bình dân','L004', 'em', 400000, 3);

select * from mon;

select * from mon join loaimon using (maloai) where tenloai = "Nước đóng chai";

create table hoadon
(
	mahd varchar(5) not null,
    thoigian datetime not null,
    manv varchar(5) not null,
    makh varchar(5) not null,
    maban tinyint not null,
    giamgia float,
    tongtien float not null,
    primary key (mahd, manv, makh, maban),
    foreign key (manv) references nhanvien (manv),
    foreign key (makh) references khachhang (makh),
    foreign key (maban) references ban (maban)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into hoadon values
('HD005', '2021-01-01 13:35:20', 'NV002', 'KH002', 2, null, 17000),
('HD006', '2021-01-01 18:35:20', 'NV003', 'KH003', 3, null, 250000);
select * from hoadon;
create table chitiethoadon 
(
	mahd varchar(5) not null,
    mamon varchar(5) not null,
    soluong float not null,    
    primary key (mahd, mamon),
    foreign key (mahd) references hoadon (mahd),
    foreign key (mamon) references mon (mamon)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into chitiethoadon values
('HD005', 'M001', 2),
('HD005', 'M002', 1),
('HD006', 'M006', 2),
('HD006', 'M007', 3);
select * from chitiethoadon;

create table phieunhap
(
	maphieunhap varchar(10) not null,
    ngaynhap date not null,
    primary key (maphieunhap)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table chitietphieunhap
(
	maphieunhap varchar(10) not null,
    mamon varchar(5) not null,
    soluong int not null,
    primary key (maphieunhap, mamon),
    foreign key (maphieunhap) references phieunhap (maphieunhap),
    foreign key (mamon) references mon (mamon)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

