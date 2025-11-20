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
                dscthd[n-1].ghiFile();
            }
            catch (IOException e)
            {
                System.out.println("LOI: Khong the ghi thong tin chi tiet hoa don vao file! " + e.getMessage());
            }
        }
    }
    public void suacthd(String mahd)
    {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<n;i++)
        {
            if(dscthd[i].getMaHoaDon().equalsIgnoreCase(mahd))
            {
            int k;
            do{
                System.out.println("Chon thong tin can sua: ");
                System.out.println("1. Ma hoa don");
                System.out.println("2. Ma san pham");
                System.out.println("3. So Luong");
                System.out.println("4. Don gia");
                System.out.println("5. Thanh tien");
                k=sc.nextInt();
                sc.nextLine();

                switch (k) {
                    case 1:
                        System.out.println("Nhap vao ma hoa don moi: ");
                        dscthd[i].setMaHoaDon(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhap vao ma san pham moi: ");
                        dscthd[i].setMaSanPham(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhap vao so luong moi: ");
                        dscthd[i].setSoLuong(sc.nextInt());
                        break;
                    case 4:
                        System.out.println("Nhap vao don gia moi: ");
                        dscthd[i].setDonGia(sc.nextDouble());
                        break;
                    case 5:
                        System.out.println("Nhap vao thanh tien moi: ");
                        dscthd[i].setThanhTien(sc.nextDouble());
                        break;
                    case 0:
                        System.out.println("Thoat chon");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                        break;
                }
            }
            while(k != 0);
            return;
        }
        }
        System.out.println("Khong tim thay ma hoa don " + mahd);
    }
    public void xoacthd()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ma hoa don can xoa: ");
        String ma = sc.nextLine();
        boolean tim = false;
        for(int i=0;i<n;i++)
        {
            if(dscthd[i].getMaHoaDon().equalsIgnoreCase(ma));
            {
                for(int j=i;j<n-1;j++)
                {
                    dscthd[j] = dscthd[j+1];
                }
                dscthd=Arrays.copyOf(dscthd, n-1);
                n--;
                tim=true;
                System.out.println("Da xoa thanh cong hoa don co ma " + ma);
                break;
            }
        }
        if(!tim)
        {
            System.out.println("Khong tim thay hoa don can xoa!");
        }
    }
    public void timkiemtheomahd()
    {
        Scanner sc = new Scanner(System.in);
        boolean tim=false;
        System.out.println("Nhap vao ma hoa don can tim: ");
        String ma = sc.nextLine();
        for(int i=0;i<n;i++)
        {
            if(dscthd[i].getMaHoaDon().equalsIgnoreCase(ma));
            {
                System.out.println("Thong tin chi tiet hoa don can tim: ");
                dscthd[i].xuatcthd();
                tim=true;
                break;  
            }
        }
        if(!tim)
        {
            System.out.println("Khong tim thay chi tiet hoa don co ma hoa don " + ma);
        }
    }

    public void thongke()
    {
        double tongtien=0;
        for(int i=0;i<n;i++)
        {
            tongtien += dscthd[i].getThanhTien();
        }
        System.out.println("\t===== Thong ke =====");
        System.out.println("So dong chi tiet hoa don: " + n);
        System.out.printf("Tong tien: %.2f%n", tongtien);
    }

    public void ghiFile() 
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("filecthd.txt",true)))
        {
            for(int i=0;i<n;i++)
            {
                CTHD ct=dscthd[i];
                bw.write(ct.getMaHoaDon() + ", " + ct.getMaSanPham() + ", " + ct.getSoLuong() + ", " + ct.getDonGia() + ", " + ct.getThanhTien());
                bw.newLine();
            } 
            System.out.println("Ghi file thanh cong!");
        }
        catch (IOException e)
        {
            System.out.println("Loi khong the ghi thong tin chi tiet hoa don vao file!" + e.getMessage());
        }
    }
    public CTHD[] getDscthd()
    {
        return dscthd;
    }
    public void setDscthd(CTHD[] dscthd)
    {
        this.dscthd=dscthd;
    }
    public int getN()
    {
        return n;
    }
    public void setN(int n)
    {
        this.n=n;
    }
}