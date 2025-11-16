import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class DSKH
{
    private int n;
    private KhachHang[] dskh;
    private boolean isReadingFile=false;

    public DSKH()
    {
        n=0;
        dskh=new KhachHang[0];
    }
    public DSKH(int n)
    {
        this.n=n;
        dskh=new KhachHang[n];
    }
    public void docFile()
    {
        this.isReadingFile=true;
        try(BufferedReader br = new BufferedReader(new FileReader("filekhachhangin.txt")))
        {
            String line;
            n=0;
            dskh = new KhachHang[0];
            while((line = br.readLine()) != null)
            {
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if(parts.length==5)
                {
                    KhachHang kh = new KhachHang(); 
                    kh.setMakhachhang(parts[0].trim());
                    kh.setHokh(parts[1].trim());
                    kh.setTenkh(parts[2].trim());
                    kh.setSdt(parts[3].trim());
                    kh.setDiachi(parts[4].trim());
                    themkh(kh);
                }
                else {
                    System.out.println("Dong du lieu khong hop le" + line);
                }
            }
            System.out.println("=> Doc file thanh cong, co "+n+" khach hang. ");
        } catch(FileNotFoundException e)
        {
            System.out.println("Chua tim thay file, tao danh sach moi");
        } catch(IOException e) 
    {
            System.out.println("Loi doc file " + e.getMessage());
        }
        finally
        {
            this.isReadingFile=false;
        }
    }
    public void nhapdskh()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong hoa don: ");
        n=sc.nextInt();
        sc.nextLine();
        dskh = new KhachHang[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Nhap thong tin hoa don thu "+(i+1)+": ");
            dskh[i]=new KhachHang();
            dskh[i].nhapkh();
        }
    }
    public void xuatds()
    {
        System.out.println("=====DANH SACH KHACH HANG=====");
        System.out.printf( "%-10s %-15s %-10s %-15s\n",
                    "Ma khach hang","ho ten","so dien thoai","dia chi");
        System.out.println("--------------------------------");
        for(int i=0;i<n;i++)
        dskh[i].xuatkh();
    }
    public void themkh(KhachHang x)
    {
        System.out.println("\n=== Them thong tin cho khach hang " + x + " ===");
        dskh=Arrays.copyOf(dskh, n + 1);
        dskh[n] = x;
        n++;
        if(!this.isReadingFile)
        {
            try {
                dskh[n-1].ghiFIle();
            } catch (Exception e) {
                System.out.println("=>LOI: Khong the ghi thong tin khach hang vao file!" + e.getMessage());
            }
        }
    }
    public void suamakh(String maKH)
    {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getMakhachhang().equalsIgnoreCase(maKH))
            {
                int k;
                do
                {
                    System.out.println("Chon thong tin can sua: ");
                    System.out.println("1. Ho khach hang.");
                    System.out.println("2. Ten khach hang.");
                    System.out.println("3. So dien thoai khach hang.");
                    System.out.println("4. Dia chi khach hang.");
                    System.out.println("Nhap lua chon: ");
                    k=sc.nextInt();
                    sc.nextLine();
                    switch (k) {
                        case 1:
                        {
                            System.out.print("Nhap ho moi khach hang: ");
                            String hokh=sc.nextLine();
                            dskh[i].setHokh(hokh);
                            break;
                        }
                        
                        case 2:
                        {
                            System.out.print("Nhap ten moi khach hang: ");
                            String tenkh=sc.nextLine();
                            dskh[i].setTenkh(tenkh);
                            break;
                        }

                        case 3:
                        {
                            System.out.print("Nhap so dien thoai moi khach hang: ");
                            String sdt=sc.nextLine();
                            dskh[i].setSdt(sdt);
                            break;
                        }

                        case 4:
                        {
                            System.out.print("Nhap dia chi moi khach hang: ");
                            String diachi=sc.nextLine();
                            dskh[i].setDiachi(diachi);
                            break;
                        }

                        default:
                        {
                            System.out.println("Lua chon khong hop le!");
                        }
                            break;
                    }
                }
                while (k != 0);
                return;
            }
        }
        System.out.println("Khong tim thay khach hang can sua!");
    }
    public void xoakh(String maKH)
    {
        int vitricanxoa=-1;
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getMakhachhang().equals(maKH))
            {
                vitricanxoa=i;
                break;
            }
        }
        if(vitricanxoa==-1)
        {
            System.out.println("Khong tim thay khach hang co ma: "+maKH);
            return;
        }
        for(int j=vitricanxoa;j<n-1;j++)
        {
            dskh[j]=dskh[j+1];
        }
        dskh=Arrays.copyOf(dskh, n-1);
        n--;
        System.out.println("Xoa khach hang thanh cong!");
    }
    public KhachHang timkiemma(String maKH)
    {
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getMakhachhang().equalsIgnoreCase(maKH))
            {
                System.out.println("Thong tin khach hang can tim: ");
                dskh[i].xuatkh();
                return dskh[i];
            }
        }
        return null;
    }
    
    public KhachHang[] timkiemtheotenkh(String tenkh)
    {
        int dem=0;
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getTenkh().equalsIgnoreCase(tenkh))
            dem++;
        }
        KhachHang[] kq = new KhachHang[dem];
        int dem1=0;
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getTenkh().equalsIgnoreCase(tenkh));
            {kq[dem1] = dskh[i];
            dem1++;
            }
        }
        return kq;
    }

    public void thongketheoho()
    {
        int honguyen=0;
        int hotran=0;
        int hoho=0;
        int hole=0;
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getHokh().equalsIgnoreCase("Nguyen"))
            {
                honguyen++;
            }
            else if(dskh[i].getHokh().equalsIgnoreCase("Tran"))
            {
                hotran++;
            }
            else if(dskh[i].getHokh().equalsIgnoreCase("Ho"))
            {
                hoho++;
            }
            else if(dskh[i].getHokh().equalsIgnoreCase("Le"))
            {
                hole++;
            }
        }
        System.out.println("=== Thong ke theo ho khach hang ===");
        System.out.println("Ho Nguyen: " + honguyen);
        System.out.println("Ho Tran: " + hotran);
        System.out.println("Ho Ho: " + hoho);
        System.out.println("Ho Le: " + hole);

    }

    public void ghiFile() throws IOException
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("filekhachhang.txt",true))) 
        {
            for(int i=0;i<n;i++)
            {
                KhachHang kh = dskh[i];
                bw.write(kh.getMakhachhang() + ", " + kh.getHokh() + ", " + kh.getTenkh() +
                        ", " + kh.getSdt() + ", " + kh.getDiachi());
                bw.newLine();
            }
            System.out.println("Ghi file thanh cong!");
        } catch (Exception e) {
            System.out.println("LOI: Khong the ghi file!" + e.getMessage());
        }
    }
    public KhachHang[] getDskh() {      
        return dskh;
    }

    public void setDskh(KhachHang[] dskh) {
        this.dskh = dskh;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}