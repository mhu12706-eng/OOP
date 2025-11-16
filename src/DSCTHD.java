import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
public class DSCTHD
{
    private int n;
    private CTHD[] dscthd;
    private boolean isReadingFile=false;

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
    public DSCTHD(DSCTHD dscthdcp)
    {
        this.n=dscthdcp.n;
        this.dscthd=new CTHD[n];
        for(int i=0;i<n;i++)
        {
            this.dscthd[i]=new CTHD(dscthdcp.dscthd[i]);
        }
    }
    public void docFile()
    {
        this.isReadingFile=true;
        try (BufferedReader br = new BufferedReader(new FileReader("filecthd.txt"))) 
        {
            String line;
            n=0;
            dscthd=new CTHD[0];
            while((line = br.readLine()) != null)
            {
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split(", ");
                if(parts.length == 5)
                {
                    CTHD ct = new CTHD();
                    ct.setMaHoaDon(parts[0].trim());
                    ct.setMaSanPham(parts[1].trim());
                    ct.setSoLuong(Integer.parseInt(parts[2].trim()));
                    ct.setDonGia(Double.parseDouble(parts[3].trim()));
                    ct.setThanhTien(Double.parseDouble(parts[4].trim()));
                    themcthd(ct);
                }
                else
                {
                    System.out.println("Dong du lieu khong hop le " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Chua tim thay file, vui long tao danh sach moi.");
        } 
        catch (IOException e)
        {
            System.out.println("Loi doc file! " + e.getMessage());
        }
        finally
        {
            this.isReadingFile=false;
        }
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
    public void themcthd(CTHD c)
    {
        System.out.println("\n=== Nhap thong tin cho chi tiet hoa don " + c + " ===");
        dscthd=Arrays.copyOf(dscthd, n+1);
        dscthd[n]=c;
        n++;
        if(!this.isReadingFile)
        {
            try
            {
                dscthd[n-1].ghiFile;
            }
            catch (IOException e)
            {
                System.out,println("LOI: ")
            }
        }
    }
}