import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KhachHang
{
    private String makhachhang;
    private String hokh;
    private String tenkh;
    private String sdt;
    private String diachi;

    public KhachHang()
    {
        makhachhang="";
        hokh="";
        tenkh="";
        sdt="";
        diachi="";
    }
    public KhachHang(String makhachhang,String hokh, String tenkh,String sdt,String diachi)
    {
        this.makhachhang=makhachhang;
        this.hokh=hokh;
        this.tenkh=tenkh;
        this.sdt=sdt;
        this.diachi=diachi;
    }
    public KhachHang(KhachHang kh2)
    {
        this.makhachhang=kh2.makhachhang;
        this.hokh=kh2.hokh;
        this.tenkh=kh2.tenkh;
        this.sdt=kh2.sdt;
        this.diachi=kh2.diachi;
    }
    public String getMakhachhang(){return makhachhang;}
    public String getHokh(){return hokh;}
    public String getTenkh(){return tenkh;}
    public String getSdt(){return sdt;}
    public String getDiachi(){return diachi;}
    public void setMakhachhang(String makhachhang){this.makhachhang=makhachhang;}
    public void setHokh(String hokh){this.hokh=hokh;}
    public void setTenkh(String tenkh){this.tenkh=tenkh;}
    public void setSdt(String sdt){this.sdt=sdt;}
    public void setDiachi(String diachi){this.diachi=diachi;}

    public void nhapkh()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma khach hang: ");
        makhachhang=sc.nextLine();
        System.out.print("Nhap vao ho khach hang: ");
        hokh=sc.nextLine();
        System.out.print("Nhap vao ten khach hang: ");
        tenkh=sc.nextLine();
        System.out.print("Nhap vao so dien thoai: ");
        sdt=sc.nextLine();
        System.out.print("Nhap vao dia chi: ");
        diachi=sc.nextLine();
    }

    public void xuatkh()
    {
        System.out.printf("%-10s %-15s %-15s %-10s %-15s\n",
            makhachhang, hokh, tenkh, sdt, diachi);
    }

    public void ghiFIle() throws IOException
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("filekhachhang.txt", true)))
        {
            bw.write(makhachhang + ", " + hokh + ", " + tenkh + ", " + sdt + ", " + diachi);
            bw.newLine();
        }
        catch (Exception e) {
           System.out.println("Loi khi ghi file!" + e.getMessage());
        }
    }
}
