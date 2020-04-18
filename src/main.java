import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    static final Scanner in = new Scanner(System.in);
    static Matkul[] arrayInput;

    public static void main(String[]args) {
        boolean run = true;

        while (run) {
            int menu = showMenu();
            if (menu == 1) {
                pendataan();
            } else if (menu == 2) {
                perhitungan();
            } else if (menu == 3) {
                updateNilai();
            } else if (menu == 4) {
                run = false;
            }
            System.out.println("\n-------------------------Terima kasih-------------------------");
        }
    }

    private static int showMenu() {
        System.out.println("Pendataan dan Perhitungan IPS (Indeks Prestasi Semester)\n" +
                "1. Pendataan Mata Kuliah\n" +
                "2. Perhitungan IPS\n" +
                "3. Update Nilai\n" +
                "4. Keluar");
        int Menu = 0;

        while (Menu < 1 || Menu > 4) {
            try{
                System.out.print("Masukkan menu yang anda pilih: ");
                Menu = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Mohon maaf anda salah memasukkan menu");
            }
            in.nextLine();
        }

        return Menu;
    }

    private static void pendataan() {
        if (arrayInput != null) {
            arrayInput = null;
        }

        System.out.println("-------------------------------------");

        int jumlah, sks;
        String kode, nama, nilai;
        boolean validasi =false;
        jumlah =0;
        sks=0;
        nilai ="";

        while (!validasi) {
            try {
                System.out.print("Masukkan jumlah matkul \t: ");
                jumlah = in.nextInt();
                validasi = true;
            } catch (InputMismatchException e) {
                System.out.println("Mohon maaf anda salah memasukkan jumlah matkul");
            }
            in.nextLine();
        }

        arrayInput = new Matkul[jumlah];

        for (int i = 0; i < jumlah; i++) {
            System.out.print("Masukkan kode matkul \t: ");
            kode = in.next();

            System.out.print("Masukkan nama matkul \t: ");
            nama = in.next();

            validasi =false;
            while (!validasi) {
                try {
                    System.out.print("Masukkan jumlah sks \t: ");
                    sks = in.nextInt();
                    validasi = true;
                } catch (InputMismatchException e) {
                    System.out.println("Mohon maaf anda salah memasukkan jumlah sks");
                }
                in.nextLine();
            }

            validasi =false;
            while (!validasi) {
                System.out.print("Masukkan jumlah nilai \t: ");
                nilai = in.next();
                if("ABCDE".contains(nilai)){
                    validasi = true;
                }else{
                    System.out.println("Mohon maaf anda salah memasukkan nilai, masukkan nilai A,B,C,D, atau E ");
                }
                in.nextLine();
            }

            System.out.println("-------------------------------------");

            Matkul matkul= new Matkul();
            matkul.setNilai(nilai);
            matkul.setSks(sks);
            matkul.setKode(kode);
            matkul.setNama(nama);

            arrayInput[i] = matkul;
        }

        System.out.println("Sukses melakukan pendaataan mata kuliah");
    }

    private static void updateNilai() {
        if (arrayInput == null) {
            System.out.println("Mohon maaf data kosong, silahkan melakukan pendataan mata kuliah terlebih dahulu \n");
            return;
        }

        String kode, nama, inputKode;
        String nilai = "";
        Boolean validasi=false;
        int sks;

        System.out.print("Masukkan kode mata kuliah: ");
        inputKode = in.next();

        for (int i = 0; i < arrayInput.length; i++) {
            kode = arrayInput[i].getKode();
            nama = arrayInput[i].getNama();
            nilai = arrayInput[i].getNilai().toUpperCase();
            sks = arrayInput[i].getSks();

            if (kode.equals(inputKode)) {
                while (!validasi) {
                    System.out.print("Masukkan nilai baru: ");
                    nilai = in.next();
                    if("ABCDE".contains(nilai)){
                        validasi = true;
                    }else{
                        System.out.println("Mohon maaf anda salah memasukkan nilai, masukkan nilai A,B,C,D, atau E ");
                    }
                    in.nextLine();
                }

                arrayInput[i].setNilai(nilai);
            }
            System.out.println(kode + "\t" + nama + "\t" + nilai + "\t" + sks);
        }

    }

    private static void perhitungan() {
        if (arrayInput == null) {
            System.out.println("Mohon maaf data kosong, silahkan melakukan pendataan mata kuliah terlebih dahulu \n");
            return;
        }

        int totalSks = 0;
        float ipsMatkul = 0;
        float jumlahIPS;
        int sks, nilaiInt;
        String kode, nama, nilai;

        System.out.println("Mata kuliah yang anda ambil adalah: ");
        for (int i = 0; i < arrayInput.length; i++) {
            nama = arrayInput[i].getNama();
            kode = arrayInput[i].getKode();
            nilai = arrayInput[i].getNilai().toUpperCase();
            sks = arrayInput[i].getSks();

            System.out.println(kode + "\t" + nama + "\t" + nilai + "\t" + sks);

            if (nilai.equals("A")) {
                nilaiInt = 4;
            } else if (nilai.equals("B")) {
                nilaiInt = 3;
            } else if (nilai.equals("C")) {
                nilaiInt = 2;
            } else if (nilai.equals("D")) {
                nilaiInt = 1;
            } else if (nilai.equals("E")) {
                nilaiInt = 0;
            } else {
                nilaiInt = 0;
            }

            totalSks += sks;

            ipsMatkul += (sks * nilaiInt);
        }

        jumlahIPS = ipsMatkul / totalSks;

        System.out.println("-------------------------------------");
        System.out.println("Nilai IPS Anda adalah: " + jumlahIPS);

    }

    private static class Matkul {
        int sks;
        String nilai, nama, kode;

        public int getSks() {
            return sks;
        }

        public void setSks(int sks) {
            this.sks = sks;
        }

        public String getNilai() {
            return nilai;
        }

        public void setNilai(String nilai) {
            this.nilai = nilai;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }
    }
}






