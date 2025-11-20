import java.io.*;
import java.util.*;
import java.text.ParseException;      
import java.text.SimpleDateFormat;
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
            this.dshd[i] = new HoaDon(dshdcp.dshd[i]);
        }
    }

    public void docFile()
    {
        this.isReadingFile=true;
        try (BufferedReader br = new BufferedReader(new FileReader("filehoadon.txt")))
        {
            String line;
            n=0;
            dshd = new HoaDon[0];
            while((line = br.readLine()) != null)
            {
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split(", ");
                if(parts.length == 4)
                {
                    HoaDon hd = new HoaDon();
                    hd.setMaHoaDon(parts[0].trim());
                    hd.setMakhachhang(parts[1].trim());
                    hd.setNgayLap(parts[2].trim());
                    hd.setTongtien(Double.parseDouble(parts[3].trim()));
                    ThemHD(hd);
                }
                else
                {
                    System.out.println("Dong du lieu khong hop le " + line);
                }
            }
            System.out.println(">>Doc file thanh cong co " + n + "hoa don.");
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file, vui long tao danh sach moi.");
        }
        catch (IOException e){
            System.out.println("Loi doc file!" + e.getMessage());
        }
        finally{
            this.isReadingFile = false;
        }
        
    }
    public void nhapds()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong hoa don: ");
        n = sc.nextInt();
        sc.nextLine();
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
    public void ThemHD(HoaDon h)
    {
        System.out.println("\n=== Nhap thong tin cho hoa don " + h + " ===");
        dshd = Arrays.copyOf(dshd, n+1);
        dshd[n]=h;
        n++;
        if(!this.isReadingFile)
        {
            try 
            {
                dshd[n-1].ghiFile();    
            } catch (IOException e) {
                System.out.println("LOI: Khong the ghi thong tin hoa don vao file!" + e.getMessage());
            }
        }
    }
   public void SuaHD() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ma hoa don can sua: ");
        String ma =sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dshd[i].getMaHoaDon().equalsIgnoreCase(ma)) {
                int k;
                do {
                System.out.println("Chon thong tin can sua:");
                System.out.println("1. Ma hoa don");
                System.out.println("2. Ma khach hang");
                System.out.println("3. Ngay lap hoa don");
                System.out.println("4. Tong tien");
                System.out.print("Nhap lua chon: ");
                k = sc.nextInt();
                sc.nextLine();

                switch (k) {
                    case 1:
                        System.out.print("Nhap ma hoa don moi: ");
                        dshd[i].setMaHoaDon(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Nhap ma khach hang moi: ");
                        dshd[i].setMakhachhang(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Nhap ngay lap hoa don moi (dd/MM/yyyy): ");
                        dshd[i].setNgayLap(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Nhap tong tien moi: ");
                        dshd[i].setTongtien(sc.nextLong());
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
            System.out.println("Khong tim thay ma hoa don: " + ma);
    }
    public void XoaHD()
    {
        Scanner sc = new Scanner(System.in);
        boolean tim = false;
        System.out.println("Nhap vao ma hoa don can xoa: ");
        String ma =sc.nextLine();
        for(int i=0;i<n;i++)
        {
            if(dshd[i].getMaHoaDon().equalsIgnoreCase(ma))
            {
                for(int j=i;j<n-1;j++)
                {
                    dshd[j]=dshd[j+1];
                }
                dshd=Arrays.copyOf(dshd, n-1);
                n--;
                tim=true;
                System.out.println("Da xoa hoa don co ma: " + ma);
                break;
            }
        }
        if(!tim)
        {
            System.out.println("Khong tim thay hoa don can xoa!");
        }
    }
    public void timkiemma()
    {
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        System.out.println("Nhap vao ma hoa don can tim: ");
        String ma = sc.nextLine();
        for(int i=0;i<n;i++)
        {
            if(dshd[i].getMaHoaDon().equalsIgnoreCase(ma))
            {
                System.out.println("Thong tin hoa don can tim: ");
                dshd[i].xuathd();
                found=true;
                break;
            }
        }
        if(!found) System.out.println("Khong tim thay hoa don co ma: "+ma );
    }
    public HoaDon[] timtheomakhachhang(String maKH)
    {
        int d=0;
        for(int i=0;i<n;i++)
        {
            if(dshd[i].getMakhachhang().equalsIgnoreCase(maKH))
            d++;
        }
        HoaDon[] kq = new HoaDon[d];
        int d1 = 0;
        for(int i=0;i<n;i++)
        {
            if(dshd[i].getMakhachhang().equalsIgnoreCase(maKH))
            {
                kq[d1]=dshd[i];
                d1++;
            }
        }
        return kq;
    }
    public void thongketongtientungayatoingayb()
    {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        System.out.println("=== THONG KE TONG TIEN THEO NGAY ===");

        try {
            System.out.print("Nhap ngay bat dau (A) theo dinh dang yyyy-MM-dd: ");
            String strNgayA = sc.nextLine();
            Date ngayA = dateFormat.parse(strNgayA);

            System.out.print("Nhap ngay ket thuc (B) theo dinh dang yyyy-MM-dd: ");
            String strNgayB = sc.nextLine();
            Date ngayB = dateFormat.parse(strNgayB);

            if (ngayA.after(ngayB)) {
                System.out.println("LOI: Ngay bat dau phai truoc hoac bang ngay ket thuc.");
                return;
            }

            double tongTienThongKe = 0.0;
            int soHoaDon = 0;

            System.out.println("\n--- Cac hoa don tim thay trong khoang tu " + strNgayA + " den " + strNgayB + " ---");
            System.out.printf( "%-10s %-15s %-10s %-15s\n",
                     "Ma hoa don","Ma khach hang","Ngay lap hoa don","Tong tien");
            System.out.println("-------------------------------------------------------");

            for (int i = 0; i < n; i++) 
            {
                String ngayLapHD_str = dshd[i].getNgayLap();
                Date ngayLapHD;

                try {
                    ngayLapHD = dateFormat.parse(ngayLapHD_str);
                } catch (ParseException e) {
                    System.out.println("Canh bao: Bo qua hoa don " + dshd[i].getMaHoaDon() + " do sai dinh dang ngay: " + ngayLapHD_str);
                    continue;
                }
                if (!ngayLapHD.before(ngayA) && !ngayLapHD.after(ngayB)) {
                    dshd[i].xuathd();
                    tongTienThongKe += dshd[i].getTongtien();
                    soHoaDon++;
                }
            }

            System.out.println("-------------------------------------------------------");
            System.out.println("Tim thay " + soHoaDon + " hoa don hop le.");
            System.out.printf("TONG TIEN: %.2f\n", tongTienThongKe);

        } catch (ParseException e) {
            System.out.println("LOI: Dinh dang ngay nhap vao khong hop le.");
            System.out.println("Vui long nhap dung dinh dang dd/MM/yyyy (vi du: 31/12/2025).");
        }
    }

    private boolean kiemTraTonTai(String[] arr, int soLuongHienTai, String value) {
        for (int i = 0; i < soLuongHienTai; i++) {
            if (arr[i] != null && arr[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    private int timIndex(String[] arr, String value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;  
    }

    public void thongKeTheoKhachHangVaNam()
    {
        SimpleDateFormat fullFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        String[] khachHangTam = new String[n]; 
        String[] namTam = new String[n];        
        int soLuongKH = 0;
        int soLuongNam = 0;

        for (int i = 0; i < n; i++) {
            HoaDon hd = dshd[i];
            String maKH = hd.getMakhachhang();
            String nam;

            if (!kiemTraTonTai(khachHangTam, soLuongKH, maKH)) {
                khachHangTam[soLuongKH] = maKH;
                soLuongKH++;
            }
            try {
                Date ngayLap = fullFormat.parse(hd.getNgayLap());
                nam = yearFormat.format(ngayLap);
                if (!kiemTraTonTai(namTam, soLuongNam, nam)) {
                    namTam[soLuongNam] = nam;
                    soLuongNam++;
                }
            } catch (ParseException e) {
                System.out.println("Canh bao: Sai dinh dang ngay tai hoa don " + hd.getMaHoaDon());
            }
        }

        if (soLuongKH == 0 || soLuongNam == 0) {
            System.out.println("Khong co du lieu de thong ke.");
            return;
        }

        String[] dsKH = Arrays.copyOf(khachHangTam, soLuongKH);
        String[] dsNam = Arrays.copyOf(namTam, soLuongNam);

        Arrays.sort(dsKH);
        Arrays.sort(dsNam);

        double[][] bangThongKe = new double[soLuongKH][soLuongNam];

        for (int i = 0; i < n; i++) {
            HoaDon hd = dshd[i];
            String maKH = hd.getMakhachhang();
            String nam;

            try {
                nam = yearFormat.format(fullFormat.parse(hd.getNgayLap()));
            } catch (ParseException e) {
                continue; 
            }

            int indexKH = timIndex(dsKH, maKH);
            int indexNam = timIndex(dsNam, nam);

            if (indexKH != -1 && indexNam != -1) {
                bangThongKe[indexKH][indexNam] += hd.getTongtien();
            }
        }

        System.out.println("\n===== BANG THONG KE DOANH THU THEO KHACH HANG VA NAM =====");

        System.out.printf("%-15s", "KH/NAM");
        for (String nam : dsNam) {
            System.out.printf(" | %15s", nam); 
        }
        System.out.printf(" | %15s\n", "TONG (THEO KH)"); 
        for(int i=0; i < dsNam.length + 2; i++) System.out.print("-----------------");
        System.out.println();
        double[] tongCongTheoNam = new double[soLuongNam]; 
        double tongCongChung = 0.0;

        for (int i = 0; i < soLuongKH; i++) {
            System.out.printf("%-15s", dsKH[i]); 
            
            double tongCongTheoKH = 0.0; 
            for (int j = 0; j < soLuongNam; j++) {
                double giaTri = bangThongKe[i][j];
                System.out.printf(" | %15.2f", giaTri);
                tongCongTheoKH += giaTri;
                tongCongTheoNam[j] += giaTri;
            }
            
            System.out.printf(" | %15.2f\n", tongCongTheoKH);
            tongCongChung += tongCongTheoKH;
        }

        for(int i=0; i < dsNam.length + 2; i++) System.out.print("-----------------");
        System.out.println();
        System.out.printf("%-15s", "TONG (THEO NAM)");
        for (int j = 0; j < soLuongNam; j++) {
            System.out.printf(" | %15.2f", tongCongTheoNam[j]);
        }
        System.out.printf(" | %15.2f\n", tongCongChung);
    }

    public void ghiFile()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("filehoadon.txt",true))) 
        {
            for(int i=0;i<n;i++)
            {
                HoaDon hd = dshd[i];
                bw.write(hd.getMaHoaDon() + ", " + hd.getMakhachhang() + ", " + hd.getNgayLap() + ", " + hd.getTongtien());
                bw.newLine();
            }
            System.out.println("Ghi file thanh cong!");
        } catch (IOException e) {
            System.out.println("LOI: Khong the ghi file! " + e.getMessage());
        }
    }
    public HoaDon[] getDshd()
    {
        return dshd;
    }
    public void setDshd(HoaDon[] dshd)
    {
        this.dshd=dshd;
    }
    public int getN()
    {
        return n;
    }
    public void setN(int N)
    {
        this.n=N;
    }
}