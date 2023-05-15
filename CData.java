public class CData{
	private int[] liczbaDni = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
	private int[] liczbaDniLeap = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
	private int days, daysNew;
	private int[] date = {0,0,0}, dateNew = {0, 0 ,0};
	private static final String[] Dni = {"Nd", "Pon", "Wt", "Sr", "Czw", "Pt", "Sb"};
	
	public boolean leapYear(int year){ //oblicz rok przestepny
		if(year % 4 == 0 && year % 100 != 0)
			return true;
		else if(year % 100 == 0 && year % 400 == 0)
			return true;
		else
			return false;
	}
	
	public int getYear(int year){ //przelicza rok na dni
		int day = 0;
	    for (int i = 1970; i < year; i++){
		    if (leapYear(i))
		    	day += 366;
		    else if (leapYear(i) == false)
		    	day += 365;
	    }
	    return day;
	}
	
	public int setYear(int days){ //ustawia rok w kalendarzu gregorianskim
		int i;
	    for (i = 1970; days > 364; i++){
		    if(leapYear(i) == true && days == 365)
		    	return i;
		    else if(leapYear(i))
		    	days -= 366;
		    else if(leapYear(i) != true)
		    	days -= 365;
	    }
	    this.dateNew[2] = i;
	    return i;
	}
	
	public int getMonth(int month, int year){ //przlicza miesiace na dni
		if(leapYear(year) == true)
			return liczbaDniLeap[month];
		else
			return liczbaDni[month];	
	}
	
	public int getDay(int day){ //przelicza dni na dni
		return day;	
	}
	
	private void ustawDate(int day, int month, int year){
		this.date[0] = day;
		this.date[1] = month;
		this.date[2] = year;
		DateToInt();
	}
	
	public void DateToInt(){
		this.days = (getDay(date[0]) + getMonth(date[1] - 1, date[2]) + getYear(date[2]) - 1);
	}
	
	public int index(int[] daysNew){
			int indeX = 0;
			int max = 0;
			for(int i = 0; i <= 11; i++)
				max = Math.max(max, daysNew[i]);
				
			int min = max;
			for(int i = 0; i <= 11; i++){
				if(daysNew[i] >= 0){
					min = Math.min(min, daysNew[i]);
					}
			}
			
			for(int i = 0; i <= 11; i++){
				if(min == daysNew[i])
					indeX = i;
			}
			return indeX;
	}
	public int setMonth(int days, int year){
		days = days - getYear(setYear(days));
		int[] daysNew = new int[12];
		
		if(leapYear(year) == true){
			for(int i = 0; i <= 11; i++)
				daysNew[i] = days - liczbaDniLeap[i];
			this.dateNew[1] = index(daysNew);
			return index(daysNew);
		}
		else{
			for(int i = 0; i <= 11; i++)
				daysNew[i] = days - liczbaDni[i];
			this.dateNew[1] = index(daysNew);
			return index(daysNew);
		}
	}
	
	public int setDay(int days){
		this.dateNew[0] = (days - getYear(setYear(days)) - getMonth(setMonth(days, setYear(days)), setYear(days)));
		return (days - getYear(setYear(days)) - getMonth(setMonth(days, setYear(days)), setYear(days)));
	}
	
	public CData(){
		ustawDate(1,1,1970);
	}
	
	public CData(int day, int month, int year){
		ustawDate(day, month, year);
	}

	public CData(int year){
		int day = 1;
		int month = 1;
		ustawDate(day, month, year);
	}
	
	public CData(String s_data){
		String[] st = {"", "", ""};
        st = s_data.split("-");
		if(st.length == 3){
			int day = Integer.parseInt(st[0]);
			int month = Integer.parseInt(st[1]);
			int year = Integer.parseInt(st[2]);
			
			ustawDate(day, month, year);
		}
		else
			ustawDate(1,1,1970);
	}
	
	public String toString(int days){
		if(leapYear(setYear(days))){
			String day = Integer.toString(setDay(days) + 1);
			String month = Integer.toString(setMonth(days, setYear(days)) + 1);
			String year = Integer.toString(setYear(days));
			return (day + "." + month + "." + year);
		}
		else{
			String day = Integer.toString(setDay(days) + 1);
			String month = Integer.toString(setMonth(days, setYear(days)) + 1);
			String year = Integer.toString(setYear(days));
			return (day + "." + month + "." + year);
		}
	}

    public String pokazDate(){
            int day = this.date[0];
            int month = this.date[1];
            int year = this.date[2];
            return (day + "." + month + "." + year);
    }

	public String pokazRok(){
		int year = this.date[2];
		return String.valueOf(year);
	}
	
	public void doPrzyszlosci(int przesuniecie){
		this.daysNew = this.days + przesuniecie;
		System.out.println(toString(this.daysNew));	
	}
	
	public void wPrzeszlosci(int przesuniecie){
		this.daysNew = this.days - przesuniecie;
		System.out.println(toString(this.daysNew));
	}
	
	public void Jutro(){
		doPrzyszlosci(1);
	}
	
	public void Wczoraj(){
		wPrzeszlosci(1);	
	}
	
	public void zaTydzien(){
		doPrzyszlosci(7);
	}
	
	public void tydzienTemu(){
		wPrzeszlosci(7);
	}
	
	public boolean porownajDaty(CData x){
		int x1 = this.days;
		int x2 = x.days;
		if(x1 == x2)
			return true;
		else
			return false;
	}
	
	public int roznicaDat(CData x){
		int x1 = this.days;
		int x2 = x.days;
		return Math.abs(x1 - x2);
	}
	
	public static String dzienTygodnia(CData x){
        int day = x.date[0];
        int month = x.date[1];
        int year = x.date[2];
		int k, C, Y;
		double m = 0;
		if(day > 0 && day < 32 && month > 0 && month < 13){
			k = day;
			C = year/100;
			Y = year%100;
			if(month == 1 || month == 2)
				m = ((double) month) + 10.0;
			else
				m = ((double) month) - 2.0;
		}
		else
			return "Blad, podaj poprawna date";
		int W =(int) (k + Math.floor(2.6 * m - 0.2) - (2*C) + Y + Math.floor(Y/4) + Math.floor(C/4)) % 7;
        if(W < 0)
            W = W + 7;
		return Dni[W];
	}
	
    public static boolean porownajDaty(CData x, CData y){
		int x1 = x.days;
		int x2 = y.days;
		if(x1 == x2)
			return true;
		else
			return false;
    }

    public static int roznicaDat(CData x, CData y){
        int x1 = x.days;
        int x2 = y.days;
        return Math.abs(x1 - x2);
    }

	public static int[] dataWielkanocy(int year){ //https://i.stack.imgur.com/WNhEk.jpg
		int y = year,
		p = y/100,
		q = y - 19*(y/19),
		r = (p-17)/25,
		s = p - (p/4) - ((p-r)/3) + 19*q + 15;
		s = s - (30*(s/30));
		s = s - ((s / 28) * (1 - ((s / 28) * (29 / (2 + 1)) * ((21 - q) / 11))));
		int t = y + (y/4) + s + 2 - p + (p/4);
		t = t - (7*(t/7));
		int u = s - t,
		m = 3 + ((u + 40)/44),
		d = u + 28 - 31*(m/4);
		
		int[] datex = {d, m, year};
		
		return datex;
	}
}