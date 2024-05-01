import java.lang.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;


class WaterJug implements Serializable, Cloneable{
	/*	
	The main idea behind singleton pattern is creation of single object only.
		1. We generally create an object with the help of contructor that can be either default(on its own) or parameterized
		2. So, we will somehow restrict the user to make use of contructor by making it private
		3. Now in order to get an object of this class we will create a method named getWaterJug 
		of static type as we will not be creating object for this class and this method will then 
		can be called directly by class name
		4. It is important to check wheather the object is already created or not, so lets introduce 
		one instance variable named waterJug of static type so that it can be accessed by the static method
		5. Just check waterJug using if condition in getWaterJug method before creating the object 

	Now comes the main point that how it can be violated and what are the resolutions to it
		1. Using Java Reflection : reflections can help in getting access to private methods/constructor of a class during runtime.
		In order to prevent this, throw a RuntimeException from the constructor if someone tries to access it. Or can make use of ENUM.
		2. By different threads : So if it is executed by multiple threads, then there might occur a condition where
		multiple threads are able to create new object of this class. To prevent this, make use of synchronized .
		3. In process of serialization and deserialization. In this just implement the readResolve method and return the same object.
		4. By Cloning : in this just implement the Cloneable interface and override the clone method and return same object.
		Remember it throws, CloneNotSupportedException.

	*/
	private static WaterJug waterJug;
	private static Integer water = 100;

	private WaterJug(){
		if (waterJug!=null) {
			throw new RuntimeException("Not allowed as it violates singleton pattern!");
		}
	}

	public static WaterJug getWaterJug(){
		if (waterJug==null) {
			//synchronized act like a barrier, maintaing one thread execution at a time
			synchronized(WaterJug.class){
				waterJug = new WaterJug();
			}
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

	public Object readResolve(){
		return waterJug;
	}

	@Override
	public Object clone() throws CloneNotSupportedException{
		return waterJug;
	}


}

class SingletonPattern {
	public static void main(String args[]) throws Exception, CloneNotSupportedException{
		System.out.println("This is about SingletonPattern in Java");

		//Single threaded environment
		WaterJug waterJug1 = WaterJug.getWaterJug();
		waterJug1.getWater();
		WaterJug waterJug2 = WaterJug.getWaterJug();
		waterJug2.getWater();
		System.out.println((waterJug1.hashCode()==waterJug2.hashCode())?"Both are same object":"Different objects");


		try{
		//Using reflection
		Constructor<WaterJug> constructor = WaterJug.class.getDeclaredConstructor();
		constructor.setAccessible(Boolean.TRUE);
		WaterJug waterJug3 = constructor.newInstance();
		waterJug3.getWater();
		System.out.println((waterJug1.hashCode()==waterJug3.hashCode())?"Both are same object":"Different objects");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}


		try{
		//Using Serialization and Deserialization
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("abc.txt"));
		objectOutputStream.writeObject(waterJug1);

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("abc.txt"));
		WaterJug waterJug4 = (WaterJug) objectInputStream.readObject();
		waterJug4.getWater();
		System.out.println((waterJug1.hashCode()==waterJug4.hashCode())?"Both are same object":"Different objects");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}


		try{
		//By Cloning
		WaterJug waterJug5 = (WaterJug)waterJug1.clone();
		waterJug5.getWater();
		System.out.println((waterJug1.hashCode()==waterJug5.hashCode())?"Both are same object":"Different objects");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}

		//Multi-Threaded Environment

	}
}