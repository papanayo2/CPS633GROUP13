package lab2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
 
public class lab21 {

		public static ArrayList<Integer> fly_time;
		public static ArrayList<Integer> first_key_dwell;
		public static ArrayList<Integer> fly_time2;
		public static ArrayList<Integer> first_key_dwell2;
		public static String access_filename;
		public static String type_of_access;
		
		public static void main(String[]args) throws IOException{
					

			        fly_time = new ArrayList<Integer>();
			        first_key_dwell = new ArrayList<Integer>();

			        ArrayList<Double> threshold_FR = new ArrayList<Double>();
			        threshold_FR.add(75.0);
			        threshold_FR.add(85.0);
			        threshold_FR.add(85.0);
			        threshold_FR.add(75.0);
			        threshold_FR.add(70.0);
			        ArrayList<Double> threshold_FA = new ArrayList<Double>();
			        threshold_FA.add(78.0);
			        threshold_FA.add(75.0);
			        threshold_FA.add(78.0);
			        threshold_FA.add(74.0);
			        threshold_FA.add(79.0);
			        
			        ArrayList<String> user_ids = new ArrayList<String>(); 
			        user_ids.add("User1");
			        user_ids.add("User2");
			        user_ids.add("User3");
			        user_ids.add("User4");
			        user_ids.add("User5");
			        
			        Scanner userInput = new Scanner(System.in);
			        
			        System.out.println("Enter UserID: ");
			        String userID = userInput.next();
			        System.out.println("Enter Filename: ");
			        access_filename = userInput.next();
			        System.out.println("Type of Access: ");
			        type_of_access = userInput.next();
			        
			        userInput.close();
			        int index = -1;
			        index = user_ids.indexOf(userID);
			       user_ids.remove(userID);
			       
					
					read_data(userID, fly_time, first_key_dwell);
					
					
					double[] deviation_values = new double[5];
					
					int k = 500;

					for(int i = 0; i < deviation_values.length; i++){
							deviation_values[i] = calculate_deviation(fly_time, first_key_dwell, new ArrayList<Integer>(fly_time.subList(k,k+500)), new ArrayList<Integer>(first_key_dwell.subList(k, k+500)));
							k += 500;

					}
					int[] FR = calculate_FR(deviation_values, threshold_FR.get(index));
					int sum_of_FR = 0;
					for(int i = 0; i < 5; i++){
							sum_of_FR += FR[i];
					}
					double FRR = sum_of_FR/5.0;
					
					
					double[][] dev_values = new double[4][5];
					//for(double x : deviation_values)System.out.println(x);
					for(int i = 0; i < 4; i++){
							fly_time2 = new ArrayList<Integer>();
							first_key_dwell2 = new ArrayList<Integer>();

							read_data(user_ids.get(i), fly_time2, first_key_dwell2);
							k = 500;

							for(int j = 0; j < 5; j++){
									dev_values[i][j] = calculate_deviation(fly_time, first_key_dwell, new ArrayList<Integer>(fly_time2.subList(k, k+500)), new ArrayList<Integer>(first_key_dwell2.subList(k, k+500)));
									k += 500;
							}
					}
					int[][] FA = calculate_FA(dev_values, threshold_FA.get(index));
					int sum_of_FA = 0;
					for(int i = 0; i < 4; i++){
							for(int j = 0; j < 5; j++){
									sum_of_FA += FA[i][j];
							}
					}
					double FAR = sum_of_FA / 20.0;
					
					System.out.println("FAR = " + FAR);
					System.out.println("FRR = " + FRR);
					if(FAR == FRR){
							//i think code for authorization should go here
					}else{
							System.out.println("Access Failed!");
					}
		}
		
		static double calculate_deviation(ArrayList<Integer> flyTimeEnr, ArrayList<Integer> firstDwellEnr, ArrayList<Integer> flyTimeVer, ArrayList<Integer> firstDwellVer){
				
				int n = 500;
				
				double sum_of_digraphs = 0;
				for(int i = 0; i < n; i++){
						if(flyTimeEnr.get(i) != 0){
								sum_of_digraphs += Math.abs(flyTimeVer.get(i) - flyTimeEnr.get(i))/(double)flyTimeEnr.get(i);
						}
				}
			
				double sum_of_monographs = 0;
				for(int i = 0; i < n; i++){
						if(firstDwellEnr.get(i) != 0){
								sum_of_monographs += Math.abs(firstDwellVer.get(i) - firstDwellEnr.get(i))/(double)firstDwellEnr.get(i);
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
		static int[][] calculate_FA(double[][]devs, double threshold){
				int[][] FA = new int[4][5];
				for(int i = 0; i < 4; i++){
						for(int j = 0; j < 5; j++){
								if(devs[i][j] <= threshold){
										FA[i][j] = 1;
								}else{
										FA[i][j] = 0;
								}
						}
				}
				return FA;
		}
		
		static void read_data(String user_id, ArrayList<Integer> flyTime, ArrayList<Integer> firstKeyDwell) throws IOException{
				String filename = user_id + ".txt";
				File file = new File(filename);
				Scanner fileOutput = new Scanner(file);
				String line = "";
				
				fileOutput.nextLine();//skipping first line
				while(fileOutput.hasNext()){
				    	line = fileOutput.nextLine();
				    	String[] list = line.split("\\s+");
				    
				    	flyTime.add(Integer.parseInt(list[2]));
				    	firstKeyDwell.add(Integer.parseInt(list[3]));
				    	
				}
				fileOutput.close();
		}
					
		
}

