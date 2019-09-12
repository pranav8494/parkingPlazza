/**
 * 
 */
package com.pp.bom;

/**
 * 
 * Class for Address.
 * @author ppandey
 *
 */
public class Address {

	private final String address;
	private final String city;
	private final String zipCode;
	private final String country;

	public Address(String address, String city, String zipCode, String country){
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
}
