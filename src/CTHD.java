import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CTHD
{
    private String mahoadon;
    private String masanpham;
    private int soluong;
    private double dongia;
    private double thanhtien;

    public CTHD()
    {
        mahoadon="";
        masanpham="";
        soluong=0;
        dongia=0;
        thanhtien=0;
    }

    public CTHD(String mahoadon, String masanpham, int soluong, double dongia, double thanhtien)
    {
        this.mahoadon=mahoadon;
        this.masanpham=masanpham;
        this.soluong=soluong;
        this.dongia=dongia;
        this.thanhtien=thanhtien;
    }

    public CTHD(CTHD cthd)
    {
        this.mahoadon=cthd.mahoadon;
        this.masanpham=cthd.masanpham;
        this.soluong=cthd.soluong;
        this.dongia=cthd.dongia;
        this.thanhtien=cthd.thanhtien;
    }

    public String getMaHoaDon() { return mahoadon; }
    public void setMaHoaDon(String maHoaDon) { this.mahoadon = maHoaDon; }
    public String getMaSanPham() { return masanpham; }
    public void setMaSanPham(String maSanPham) { this.masanpham = maSanPham; }
    public int getSoLuong() { return soluong; }
    public void setSoLuong(int soLuong) { this.soluong = soLuong; }
    public double getDonGia() { return dongia; }
    public void setDonGia(double donGia) { this.dongia = donGia; }
    public double getThanhTien() { return thanhtien; }
    public void setThanhTien(double thanhTien) { this.thanhtien = thanhTien; }

    public void nhapcthd()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma hoa don: ");
        mahoadon=sc.nextLine();
        System.out.print("Nhap vao ma san pham: ");
        masanpham=sc.nextLine();
        System.out.print("Nhap vao so luong san pham: ");
        soluong=sc.nextInt();
        System.out.print("Nhap vao don gia san pham: ");
        dongia=sc.nextDouble();
        System.out.print("Nhap vao thanh tien san pham: ");
        thanhtien=sc.nextDouble();
    }
    
    public void xuatcthd()
    {
        System.out.printf("%-10s %-10s %-10s %-15s %-15s\n",
                mahoadon, masanpham, soluong, dongia, thanhtien);
    }

    public void ghiFile() throws IOException
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("fileCTHD.txt", true)))
        {
            bw.write(mahoadon + ", " + masanpham + ", " + soluong + ", " + dongia + ", " + thanhtien);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Loi khi ghi file!" + e.getMessage());
        }
    }
}