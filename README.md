<p align="center">
  <img width="96" align="center" src="app/src/main/res/drawable/icon_app.png" alt="logo">
</p>
  <h1 align="center">
  SaiGon Coffee Chilling
</h1>
<p align="center">
  The beverage ordering application is developed based on the Android platform.
</p>

<p align="center">

  <a style="text-decoration:none">
    <img src="https://img.shields.io/badge/JavaJDK-17-blue.svg?color=00B16A" alt="JavaJDK 17"/>
  </a>

  <a style="text-decoration:none">
    <img src="https://img.shields.io/badge/Platform-Win64%20|%20macOS-blue?color=00B16A" alt="Platform Win64 | macOS"/>
  </a>
</p>

## Quick start
### Windows OS
1. If Java is not in your system
   - [Install Java JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)

2. Create a new Folder and git clone the Project:
   ```shell
    git clone https://github.com/Canon-D2/SGUCoffeeApp
    ```
3. Fix build.gradle (Module :app)
  - Modify the directory path in Gradle Scripts -> build.gradle (Module: app) -> the line of code "implementation fileTree(dir:'....', include: ['.aar', '.jar'], exclude: [])" to the location of the current zalopay folder on your machine.

4. Fix IPAddress in RetrofitService
   - Open Terminal and press the following command line:
   ```shell
    ipconfig
    ```
   - Search the line "IPv4 Address"
   - Add the new Ip Address in retrofit
5. Run APP
   
--------------------------

## PROJECT ANDROID 2 - APP CLIENT - TEAM 11 - APP ĐẶT COFFEE TRỰC TUYẾN

## GIAO DIỆN CLIENT XML - RETROFIT API

## TEAM MEMBER

+ 3120410046 - Đặng Chí Bảo (Leader)
+ 3120410337 - Trần Quốc Nam
+ 3120410412 - Nguyễn Thiên Phúc
+ 3120410030 - Nguyễn Đức Anh
+ 3120410034 - Nguyễn Tuấn Anh

## DANH SÁCH CHỨC NĂNG

Thiết kế db, file data sql, vẽ usecase chức năng admin và client (Bảo)
Thiết kế giao diện Figma cho client (Nam) 

## ADMIN SERVER WEB SPRING BOOT

0. Hibernate JPA + MySQL (Bảo + Phúc)
1. Model: get, set thuộc tính + Đưa dữ liệu lên API Swagger (đ anh)
2. Viết các hàm chức năng (controller)
	+ Đăng nhập, đăng xuất admin (Nam)
	+ Quản lý hóa đơn (xem ds, xem chi tiết, xóa, gửi mail) (phúc) 
	+ Thống kê doanh thu (nam)
	+ CRUD tài khoản (đ anh) 
	+ CRUD sản phẩm (t anh) 
	+ Chatbox hỗ trợ GPT (Bảo)
	+ Chatbox nhóm socket (bảo)

-----------------------------------------------------------------------------------------------

## CLIENT APP - ANDROID 

## BACKEND
1. Retrofit: lấy dữ liệu từ API + Model: get, set các đối tượng (đ anh)
2. Đăng nhập, đăng xuất (nam)
3. Trang chủ:

	+ Trang chủ: load sản phẩm, tìm kiếm sản phẩm, phân sản phẩm theo loại (bảo)
	+ Chi tiết sản phẩm: xem ảnh, sl còn lại, mô tả, giá,... (phúc)
	+ Giỏ hàng: dạng danh sách, tăng giảm số lượng, xóa sp (nam)
	+ Hóa đơn: dạng danh sách, xem chi tiết (đ anh)
	+ Thanh toán: nhập địa chỉ (chưa có), tiền mặt (hoặc tích hợp zalopay) (bảo)
	+ Tài khoản: xem thông tin chi tiết, sửa thông tin, (t anh)
	+ Hỗ trợ: chatbox gpt (bảo)
	+ Chèn quảng cáo (gg ads) (nam)
	+ Cache lưu tạm sản phẩm khi disconnect (phúc)


## FRONDEND (bao gồm các items nhỏ trong trang chính) 

4. Giao diện XML các trang (bao gồm trang con chồng nhau)
	+ Trang chủ (ds sản phẩm) (bảo)
	+ Trang chi tiết sản phẩm (nam)
  	+ Trang điều khoản và dịch vụ (nam)
	+ Trang giỏ hàng (phúc)
	+ Trang thanh toán (đ anh)
	+ Trang xem hóa đơn (t anh)
	+ Trang tài khoản (phúc)
	+ Trang hỗ trợ gpt (bảo)


