import java.lang.*;
import java.util.*;
import java.io.*;

class WaterJug{
	/*	the main idea behind singleton pattern is creation of single object only.
		1. We generally create an object with the help of contructor that can be either default(on its own) or parameterized
		2. So, we will somehow restrict the user to make use of contructor by making it private
		3. Now in order to get an object of this class we will create a method named getWaterJug 
		of static type as we will not be creating object for this class and this method will then 
		can be called directly by class name
		4. It is important to check wheather the object is already created or not, so lets introduce 
		one instance variable named waterJug of static type so that it can be accessed by the static method
		5. Just check waterJug using if condition in getWaterJug method before creating the object 

	*/
	private static WaterJug waterJug;
	private static Integer water = 100;

	private WaterJug(){

	}

	public static WaterJug getWaterJug(){
		if (waterJug==null) {
			waterJug = new WaterJug();
		}
		return waterJug;
	}

	public void getWater(){
		if (water==0) {
			System.out.println("Sorry! Jug is empty. Please refill!");
			return;
		}
		System.out.println("One glass water is here! remaining "+water);
		water=water-10;
	}


}

class SingletonPattern {
	public static void main(String args[]){
		System.out.println("This is about SingletonPattern in Java");

		WaterJug waterJug1 = WaterJug.getWaterJug();
		waterJug1.getWater();
		WaterJug waterJug2 = WaterJug.getWaterJug();
		waterJug2.getWater();
		System.out.println((waterJug1.hashCode()==waterJug2.hashCode())?"Both are same object":"Different objects");
	}
}