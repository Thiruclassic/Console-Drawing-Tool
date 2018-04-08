public class Canvas {
	
	char[][] drawingMatrix;
	int width;
	int height;
	
	/*
	 * Initialize the canvas with width & height
	 */
	public Canvas(int width,int height)
	{
		this.width = width;
		this.height = height;
	    drawingMatrix = new char[height+2][width+2];
	}
	
	/*
	 * This method is used to validate the co-ordinates
	 * to check whether they are in valid bounds of the canvas
	 */
	public void validateCoordinates(int x, int y) throws Exception
	{
		if(x<0 || x>width || y<0 || y >height)
		{
			throw new Exception("Please enter valid coordinates. The coordinates are out of bounds on the canvas");
		}
	}
	
	/*
	 * This method is used to create the canvas
	 */
	public void createCanvas() throws Exception
	{		
		fillHorizontalLine(0,0,drawingMatrix[0].length-1,'-');
		fillHorizontalLine(drawingMatrix.length-1,0, drawingMatrix[0].length-1,'-');
		fillVerticalLine(0,1,drawingMatrix.length-2, '|');
		fillVerticalLine(drawingMatrix[0].length-1,1, drawingMatrix.length-2, '|');	
	}
	
	
	/*
	 * This method is actual method used to draw the filled
	 * canvas and other components
	 */
	public void draw()
	{
		for(int i=0; i<drawingMatrix.length; i++)
		{
			for(int j=0; j<drawingMatrix[i].length; j++)
			{
				System.out.print(drawingMatrix[i][j]);
			}
			nextLine();
		}
	}
	
	/*
	 * This method is used to fill the rectangle from the given co-ordinates
	 */
    public void fillRectangle(int x1,int y1,int x2,int y2, char drawChar) throws Exception
    {
    	validateCoordinates(x1, y1);
		validateCoordinates(x2, y2);
    	fillHorizontalLine(y1, x1, x2,'x');
		fillHorizontalLine(y2, x1, x2,'x');
		fillVerticalLine(x1, y1, y2, 'x');
		fillVerticalLine(x2, y1, y2, 'x');
    }
    
    
    
    /*
     *    This method is used to paint the area connected
     *    to (x,y) with colour drawChar.
     *   
     *           
     */
    public void fillBucket(int y, int x, char drawChar) throws Exception
    {
    	if(y<=width && y>0 && x<=height && x>0 && drawingMatrix[x][y] == '\u0000')
    	{
    		drawingMatrix[x][y] = drawChar;
    		fillBucket(y,x-1,drawChar);
    		fillBucket(y,x+1,drawChar);
    		fillBucket(y+1,x,drawChar);
    		fillBucket(y-1,x,drawChar);
    		fillBucket(y-1,x-1,drawChar);
    		fillBucket(y-1,x+1,drawChar);
    		fillBucket(y+1,x+1,drawChar);
    		fillBucket(y+1,x-1,drawChar);
    	}    	
    }
	
    /*
     * This method is used to draw the vertical or horizontal lines
     * with respect to the co-ordinates given
     */
	public void fillLine(int x1,int y1,int x2, int y2, char drawChar) throws Exception
	{
		validateCoordinates(x1, y1);
		validateCoordinates(x2, y2);
		if(x1 == x2)
		{
			fillVerticalLine(x1, y1, y2, drawChar);
		}
		else if(y1 == y2)
		{
			fillHorizontalLine(y1, x1, x2, drawChar);
		}
		else
		{
			throw new Exception("Please enter valid coordinates. The coordinates are not valid to form a line");
		}
	}
	
	
	/*
	 * This method is used to draw horizontal line on the specified y axis 
	 */
	public void fillHorizontalLine(int y, int startVertex, int endVertex, char drawChar) throws Exception
	{
		for(int i=startVertex;i<=endVertex;i++)
		{
			drawingMatrix[y][i] = drawChar;
		}
	}
	
	
	/*
	 * This method is used to draw vertical line on the specified x axis
	 */
	public void fillVerticalLine(int x, int startVertex, int endVertex, char drawChar) throws Exception
	{
		for(int i=startVertex ; i<=endVertex; i++)
		{
			drawingMatrix[i][x] = drawChar;
		}
	}
	
	/*
	 * This method is used to move to the next line
	 */
	public void nextLine()
	{
		System.out.println();
	}

	

}
