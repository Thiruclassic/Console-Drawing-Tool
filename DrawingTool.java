import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DrawingTool {
	
	static Canvas canvas;
	static boolean isProgramNOtExited;
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		isProgramNOtExited = true;
		System.out.println(readInstructions());
		while(isProgramNOtExited)
		{
			read(reader);
		}
		System.out.println("Program Exited!!! Thank you");
	}
	
	/*
	 * used to read the commands from the user
	 */
	public static void read(BufferedReader reader) 
	{
		try
		{
		String command;
		command = reader.readLine();
		
		String[] arguments = command.split(Constant.SPACE);
		
		int index = 0;
		switch(arguments[0])
		{
		case Command.CANVAS:
				int[] commandArgs = readCommandArgs(arguments);
				Canvas c = new Canvas(commandArgs[index++],commandArgs[index++]);
				canvas = c;
				canvas.createCanvas();
				canvas.draw();
			break;
			
		case Command.LINE:
				commandArgs = readCommandArgs(arguments);
				canvas.fillLine(commandArgs[index++], commandArgs[index++], commandArgs[index++], commandArgs[index++], 'x');
				canvas.draw();
			break;
		case Command.RECTANGLE:
				commandArgs = readCommandArgs(arguments);
				canvas.fillRectangle(commandArgs[index++], commandArgs[index++], commandArgs[index++], commandArgs[index++], 'x');
				canvas.draw();
			break;
		case Command.BUCKET:
			if(arguments.length != 4 || arguments[arguments.length-1].length()!= 1)
				throw new Exception("Please enter valid coordinates. The coordinates are out of bounds on the canvas");
				String[] tempCommandArgs = new String[3];
				System.arraycopy(arguments, 0, tempCommandArgs, 0, 3);
				commandArgs = readCommandArgs(tempCommandArgs);
				canvas.fillBucket(commandArgs[index++], commandArgs[index++], (char)arguments[arguments.length-1].charAt(0));
				canvas.draw();
			break;
		case Command.HELP:
			System.out.println(readInstructions());
			break;
		case Command.QUIT:
			isProgramNOtExited = false;
			reader.close();
			break;
		default:
			System.out.println("Command Not Recognized. Please enter a valid Command!!!");
			break;
		}
		
		}
		catch(NullPointerException e)
		{
			if(canvas == null)
			System.out.println("Please create the canvas first to draw on the canvas!!!");
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid Arguments. Please enter valid arguments");
		}
		catch(Exception e)
		{
			System.out.println("Invalid Command. Please Try Again");
			System.out.println(e.getMessage());
		}
		
	}
		
	
	
	/*
	 * used to convert the arguments to integer array 
	 * of co-ordinates for drawing
	 */
	public static int[] readCommandArgs(String[] arguments)
	{
		int[] commandArgs = new int[arguments.length-1];
		for(int i=1; i<arguments.length;i++)
		{
				commandArgs[i-1] = Integer.parseInt(arguments[i]);
		}
		return commandArgs;
		
	}
	
	/*
	 * Instructions to run the command for the drawing tool
	 */
	public static String readInstructions()
	{
		return "Command 		Description                                                                     \n"+
				"C w h           Should create a new canvas of width w and height h.                            \n"+
				"L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only               \n"+
				"                horizontal or vertical lines are supported. Horizontal and vertical lines      \n"+
				"                will be drawn using the 'x' character.                                         \n"+
				"R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and          \n"+
				"                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn     \n"+
				"                using the 'x' character.                                                       \n"+
				"B x y c         Should fill the entire area connected to (x,y) with colour c. The            \n"+
				"                behaviour of this is the same as that of the bucket fill tool in paint       \n"+
				"                programs.                                                                      \n"+
				"Q               Should quit the program.                                                       \n"+
				"H               To know the list of commands                                                       \n"+
				"Enter your command:";
	}

}
