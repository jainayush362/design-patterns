import java.lang.*;
import java.util.*;
import java.io.*;

interface Vehicle{
	void vehicleType();
}

class TwoWheeler implements Vehicle{
	@Override
	public void vehicleType(){
		System.out.println("This is a two wheeler");
	}
}

class FourWheeler implements Vehicle{
	@Override
	public void vehicleType(){
		System.out.println("This is a four wheeler");
	}
}

class VehicleFactory{
	public static Vehicle getVehicle(String type){
		switch(type){
		case "TWO" -> {return new TwoWheeler();}
		case "FOUR" -> {return new FourWheeler();}
		default -> {return null;}
		}
	} 
}


class FactoryPattern{
	public static void main(String args[]){
		System.out.println("This is about factory design pattern in java");
		Vehicle vehicle1 = VehicleFactory.getVehicle("FOUR");
		Vehicle vehicle2 = VehicleFactory.getVehicle("TWO");
		vehicle1.vehicleType();
		vehicle2.vehicleType();
	}
}