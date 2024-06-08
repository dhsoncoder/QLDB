package com.example.quanlydanhba;

public class Contact {
    public String ten; // Tên đơn vị hoặc tên nhân viên
    public String tenDonVi; // Tên đơn vị (chỉ dùng cho nhân viên)

    // Constructor cho đơn vị
    public Contact(String tenDonVi) {
        this.ten = tenDonVi;
        this.tenDonVi = null; // Không cần tên đơn vị cho đơn vị
    }

    // Constructor cho nhân viên
    public Contact(String tenNhanVien, String tenDonVi) {
        this.ten = tenNhanVien;
        this.tenDonVi = tenDonVi;
    }

    // Override phương thức toString() để hiển thị đúng thông tin trên ListView
    @Override
    public String toString() {
        if (tenDonVi == null) {
            return ten; // Đơn vị: chỉ hiển thị tên đơn vị
        } else {
            return ten + " - " + tenDonVi; // Nhân viên: hiển thị tên nhân viên - tên đơn vị
        }
    }
}