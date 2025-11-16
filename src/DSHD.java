import java.io.*;
import java.util.*;
public class DSHD
{
    private int n;
    private HoaDon[] dshd;
    private boolean isReadingFile=false;

    public DSHD()
    {
        n=0;
        dshd=new HoaDon[0];
    }

    public DSHD(int n)
    {
        this.n=n;
        this.dshd=new HoaDon[n];
    }

    public DSHD(DSHD dshdcp)
    {
        this.n=dshdcp.n;
        this.dshd=new HoaDon[n];
        for(int i=0;i<n;i++)
        {
            this.dshd[i]= new HoaDon(dshdcp.dshd[i]);
        }
    }

    public void docFile()
    {
        this.isReadingFile=true;
        try (BufferedReader br = new BufferedReader(new FileReader("filehoadon.txt")))
        {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
    public void nhapds()
    {
        System.out.print("Nhap so luong hoa don: ");
        dshd=new HoaDon[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Nhap thong tin hoa don thu "+(i+1)+": ");
            dshd[i]=new HoaDon();
            dshd[i].nhaphd();
        }     
    }
    public void xuatds()
    {
        System.out.println("=====DANH SACH HOA DON=====");
        System.out.printf( "%-10s %-15s %-10s %-15s\n",
                    "Ma hoa don","Ma khach hang","Ngay lap hoa don","Tong tien");
        System.out.println("--------------------------------");
        for(int i=0;i<n;i++)
        dshd[i].xuathd();
    }
    public void ThemHD(int n)
    {
    for (int i = 0; i < n; i++) {
        System.out.println("\n=== Nhap thong tin hoa don moi thu " + (i + 1) + " ===");
        HoaDon hd3 = new HoaDon();
        hd3.nhaphd();
        dshd[n]=hd3;
        n++;
    }
    }
    public void XoaHD(String maHD)
    {
        int vitricanxoa=-1;
        for(int i=0;i<n;i++)
        {
            if(dshd[i].getMaHoaDon().equals(maHD))
            {
                vitricanxoa=i;
                break;
            }
        }
        if(vitricanxoa==-1)
        {
            System.out.println("Khong tim thay hoa don co ma: "+maHD);
            return;
        }
        for(int j=vitricanxoa;j<n-1;j++)
        {
            dshd[j]=dshd[j+1];
        }
        dshd=Arrays.copyOf(dshd, n-1);
        n--;
    }
    public void timkiemma(String maHD)
    {
        boolean found = false;
        for(int i=0;i<n;i++)
        {
            if(dshd[i].getMaHoaDon().equalsIgnoreCase(maHD))
            {
                System.out.println("Thong tin hoa don can tim: ");
                dshd[i].xuathd();
                found=true;
                break;
            }
        }
        if(!found) System.out.println("Khong tim thay hoa don co ma: "+maHD );
    }
    
}