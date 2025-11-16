import java.util.Scanner;
import java.util.Arrays;
public class DSCTHD
{
    private int n;
    private CTHD[] dscthd;


    public DSCTHD()
    {
        n=0;
        dscthd=new CTHD[0];
    }
    public DSCTHD(int n)
    {
       this.n=n;
       dscthd=new CTHD[n]; 
    }
    public void nhapdscthd()
    {
        System.out.print("Nhap vao so luong danh sach: ");
        dscthd=new CTHD[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Nhap vao thong tin chi tiet hoa don thu "+(i+1)+": ");
            dscthd[i]=new CTHD();
            dscthd[i].nhapcthd();
        }
    }
    public void xuatdscthd()
    {
        System.out.println("=====DANH SACH CHI TIET HOA DON=====");
        System.out.printf("%-10s %-10s %-10s %-15s %-15s\n",
                "Ma Hoa Don","Ma San Pham","So Luong","Don Gia","Thanh Tien");
    }
}