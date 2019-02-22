package com.vsm.devcase.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TesteMain {

	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String date = "19/02/2019";
		
		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);
		System.out.println(localDate);
		//System.out.println(DataFormat.getLocalDate("18/02/2019"));
	}
	
}
