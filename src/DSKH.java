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
                    System.out.println("Dong du lieu khong hop le " + line);
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
            } catch (IOException e) {
                System.out.println("=>LOI: Khong the ghi thong tin khach hang vao file!" + e.getMessage());
            }
        }
    }
    public void suakh(String maKH) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMakhachhang().equalsIgnoreCase(maKH)) {
                int k;
                do {
                System.out.println("Chon thong tin can sua:");
                System.out.println("1. Ma khach hang");
                System.out.println("2. Ho khach hang");
                System.out.println("3. Ten khach hang");
                System.out.println("4. So dien thoai khach hang");
                System.out.println("5. Dia chi khach hang");
                System.out.print("Nhap lua chon: ");
                k = sc.nextInt();
                sc.nextLine();

                switch (k) {
                    case 1:
                        System.out.print("Nhap ma khach hang moi: ");
                        dskh[i].setMakhachhang(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Nhap ho khach hang moi: ");
                        dskh[i].setHokh(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Nhap ten khach hang moi: ");
                        dskh[i].setTenkh(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Nhap so dien thoai khach hang moi: ");
                        dskh[i].setSdt(sc.nextLine());
                        break;
                    case 5:
                        System.out.println("Nhap dia chi khach hang moi: ");
                        dskh[i].setDiachi(sc.nextLine());
                        break;
                    case 0 :
                        System.out.println("Thoat chon");
                        break;
                    
                    default:
                        System.out.println("Lua chon khong hop le!");
                        return;
                }
            } while (k != 0);
            return;
            }
         }
            System.out.println("Khong tim thay ma khach hang: " + maKH);
    }
    public void xoakh(String maKH)
    {
        boolean tim = false;
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getMakhachhang().equalsIgnoreCase(maKH));
            {
                for(int j=i;j<n-1;j++)
                {
                    dskh[j]=dskh[j+1];
                }
                dskh=Arrays.copyOf(dskh, n-1);
                n--;
                tim=true;
                System.out.println("Da xoa khach hang co ma: " + maKH);
                break;
            }
        }
        if(!tim)
        {
            System.out.println("Khong tim thay ma khach hang can xoa!");
        }
    }
    public KhachHang timkiemma(String maKH)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ma khach hang can tim: ");
        maKH=sc.nextLine();
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
        } catch (IOException e) {
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


























    //them
    //tim theo ten
    public KhachHang[] timtheoten(String tentim)
    {
        int d=0;
        boolean tim=false;
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getTenkh().equalsIgnoreCase(tentim));
            {
                d++;
                tim=true;
            }
        }
        int d1=0;
        KhachHang[] kq = new KhachHang[d];
        for(int i=0;i<n;i++)
        {
            if(dskh[i].getTenkh().equalsIgnoreCase(tentim));
            {
                kq[d1]=dskh[i];
                d1++;
            }
        }
        if(!tim) System.out.println("Khong tim thay ten khach hang " + tentim);
        return kq;
    }
    public void themkh(KhachHang k)
    {
        dskh=Arrays.copyOf(dskh, n+1);
        dskh[n]=k;
        n++;
    }
}