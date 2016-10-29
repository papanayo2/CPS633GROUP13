package lab2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class lab21 {
		public static ArrayList<Integer> first_key_code;
		public static ArrayList<Integer> second_key_code;
		public static ArrayList<Integer> fly_time;
		public static ArrayList<Integer> first_key_dwell;
		public static ArrayList<Integer> second_key_dwell;
		
		public static void main(String[]args) throws IOException{
					
			        first_key_code = new ArrayList<Integer>();
			        second_key_code = new ArrayList<Integer>();
			        fly_time = new ArrayList<Integer>();
			        first_key_dwell = new ArrayList<Integer>();
			        second_key_dwell = new ArrayList<Integer>();
			        
			        Scanner userInput = new Scanner(System.in);
			        System.out.println("Enter UserID: ");
			        String userID = userInput.next();
			        userInput.close();
					String filename = userID + ".txt";
					File file = new File(filename);
					Scanner fileOutput = new Scanner(file);
					
					fileOutput.nextLine();//skipping first line
					while(fileOutput.hasNext()){
						    String input = fileOutput.nextLine();
						    String[] list = input.split("\\s+");
						    
							first_key_code.add(Integer.parseInt(list[0]));
							second_key_code.add(Integer.parseInt(list[1]));
							fly_time.add(Integer.parseInt(list[2]));
							first_key_dwell.add(Integer.parseInt(list[3]));
							second_key_dwell.add(Integer.parseInt(list[4]));
					}
					
					double[] deviation_values = new double[5];
					
					int k = 500;
					for(int i = 0; i < deviation_values.length; i++){
							deviation_values[i] = calculate_deviation(k);
							k += 500;
					}
					int[] FR = calculate_FR(deviation_values, 70);
					//for (double x : deviation_values)System.out.println(x);
					//for (int x : FR)System.out.println(x);
					
					fileOutput.close();
		}
		
		static double calculate_deviation(int k){
				
				int n = 500;
				
				double sum_of_digraphs = 0;
				for(int i = 0; i < n; i++){
						if(fly_time.get(i) != 0){
								sum_of_digraphs += Math.abs(fly_time.get(k+i) - fly_time.get(i))/(double)fly_time.get(i);
						}
				}
			
				double sum_of_monographs = 0;
				for(int i = 0; i < n; i++){
						if(first_key_dwell.get(i) != 0){
								sum_of_monographs += Math.abs(first_key_dwell.get(k+i) - first_key_dwell.get(i))/(double)first_key_dwell.get(i);
						}
				}
				double d = ((sum_of_digraphs/(double)(n-1)) + (sum_of_monographs/(double)(n)))*50;
				
				return d;
		}
		
		static int[] calculate_FR(double[] devs, double threshold){
				int[] FR = new int[5];
				int i =0;
				for(double dev : devs){
						if(dev >= threshold){
								FR[i] = 1;
						}else{
								FR[i] = 0;
						}
						i++;
				}
				return FR;
		}
}

