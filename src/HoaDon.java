import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HoaDon
{
    private String mahoadon;
    private String makhachhang;
    private String ngaylap;
    private double tongtien;

    public HoaDon()
    {
        mahoadon="";
        makhachhang="";
        ngaylap="";
        tongtien=0;
    }
    public HoaDon(String mahoadon,String makhachhang,String ngaylap,double tongtien)
    {
        this.mahoadon=mahoadon;
        this.makhachhang=makhachhang;
        this.ngaylap=ngaylap;
        this.tongtien=tongtien;
    }
    public HoaDon(HoaDon hd2)
    {
        this.mahoadon=hd2.mahoadon;
        this.makhachhang=hd2.makhachhang;
        this.ngaylap=hd2.ngaylap;
        this.tongtien=hd2.tongtien;
    }

    public String getMaHoaDon() { return mahoadon; }
    public void setMaHoaDon(String maHoaDon) { this.mahoadon = maHoaDon; }
    public String getmakhachhangachHang() { return makhachhang; }
    public void setmakhachhangachHang(String makhachhangachHang) { this.makhachhang = makhachhangachHang; }
    public String getNgayLap() { return ngaylap; }
    public void setNgayLap(String ngayLap) { this.ngaylap = ngayLap; }
    public double gettongtien() { return tongtien; }
    public void settongtien(double tongtien) { this.tongtien = tongtien; }

    public void nhaphd()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma hoa don: ");
        mahoadon=sc.nextLine();
        System.out.print("Nhap vao ma khach hang: ");
        makhachhang=sc.nextLine();
        System.out.print("Nhap vao ngay lap hoa don: ");
        ngaylap=sc.nextLine();
        System.out.print("Nhap vao tong tien: ");
        tongtien=sc.nextLong();
    }
    public void xuathd()
    {
         System.out.printf("%-10s %-15s %-10s %-15s\n",
            mahoadon, makhachhang, ngaylap, tongtien);
    }

    public void ghiFile() throws IOException
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("filehoadon.txt",true))) 
        {
            bw.write(mahoadon + ", " + makhachhang + ", " + ngaylap + ", " + tongtien);
            bw.newLine();
        }
        catch (Exception e) {
            System.out.println("Loi khi ghi file!" + e.getMessage());
        }
    }
}
























