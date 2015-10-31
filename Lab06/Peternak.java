package Lab06;

/**
 * Class peternak
 * @author AsdosA
 *
 */
public class Peternak {

	/**
	 * Instance variable dari class Peternak, tambahkan yang diperlukan
	 */
	private String nama;
	private int duit;
	private KandangAyam kandang;
	public static final int HARGA_BELI_AYAM = 1500;
	public static final int HARGA_JUAL_AYAM_BIASA = 1500;
	public static final int HARGA_JUAL_AYAM_EMAS = 3000;

	/**
	 * Constructor peternak
	 * @param nama nama peternak
	 * @param duit jumlah uang yang dimiliki peternak
	 */
	public Peternak(String nama, int duit) {
		this.nama = nama;
		this.duit = duit;
		kandang = new KandangAyam();
	}
	
	/**
	 * Setter dan Getter
	 * @return
	 */
	public KandangAyam getKandang() {
		return kandang;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	private void setDuit(int duit) {
		this.duit = duit;
	}

	public void setKandang(KandangAyam kandang) {
		this.kandang = kandang;
	}

	public String getNama() {
		return nama;
	}

	public int getDuit() {
		return duit;
	}

	public int getHARGA_AYAM() {
		return HARGA_BELI_AYAM;
	}

	/**
	 * Method ini berfungsi untuk membeli ayam baru, 
	 * jika kekurangan uang, akan mengembalikan nilai -1
	 * jika kandang penuh akan mengembalikan 0
	 * 
	 * @param namaAyam nama ayam yang baru dibeli
	 * @return uang sekarang
	 */
	public int buyAyam(String namaAyam) {
		if(getDuit() < HARGA_BELI_AYAM)
			return -1;
		if(kandang.getayams().size() == kandang.size())
			return 0;
		
		setDuit(getDuit() - HARGA_BELI_AYAM);
		kandang.addAyam(namaAyam);
		
		return getDuit();
	}

	/**
	 * Method ini berfungsi untuk menjual ayam, harga tergantung jenis ayam
	 * Jika tidak ada ayam yang tersedia, return -1
	 * @param namaAyam nama ayam yang ingin dijual.
	 * @return uang sekarang
	 */
	public int sellAyam(String namaAyam) {
		if(kandang.getayams().size() == 0) 
			return -1;
		if (kandang.removeAyam(namaAyam))
			setDuit(getDuit() + HARGA_JUAL_AYAM_EMAS);

		else
			setDuit(getDuit() + HARGA_JUAL_AYAM_BIASA);

		return getDuit();
	}

	/**
	 * Method ini membuat peternak mengangkat ayam
	 * @param namaAyam nama ayam yang diangkat
	 */
	public void pickUpAyam(String namaAyam) {
		Ayam ayam = kandang.findAyam(namaAyam);
		ayam.pickUp();
	}
	
	/**
	 * Method ini membuat peternak menendang ayam
	 * @param namaAyam nama ayam yang ditendang
	 */
	public void kickAyam(String namaAyam)	{
		Ayam ayam = kandang.findAyam(namaAyam);
		ayam.kick();
	}
	
	/**
	 * Method ini berfungsi untuk mengupgrade kandang ayam, menmbahkan ukuran kandang 2x lipat ukuran sebelumnya
	 */
	public void upgradeKandang() {
		int upgradeSize = kandang.size() * 2;
		if(upgradeSize > 0) {
			kandang.setSize(upgradeSize);
		}
	}
	
	/**
	 * Method ini berfungsi untuk mejual telur, ada 2 jenis telur dengan harga yang berbeda
	 */
	public void jualTelor() {
		int sum = 0;
		for(int i = 0; i < kandang.size(); i++) {
			Ayam current = kandang.getAyam(i);
			sum = current.hargaTelor();
		}
		setDuit(sum + getDuit());
	}
}