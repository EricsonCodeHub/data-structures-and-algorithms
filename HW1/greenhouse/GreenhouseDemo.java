
public class GreenhouseDemo
{
	public static void main(String[] args)
	{
		Greenhouse ghouse = new Greenhouse();
		
		do
		{
			if (ghouse.getRunState() == ghouse.restart)
			{
				//restart the green house thread
				ghouse = new Greenhouse();
			}
			else if (ghouse.getRunState() == ghouse.terminate)
			{
				// terminate the greenhouse
			}
		} while ((ghouse.getRunState() == ghouse.running) ||
				 (ghouse.getRunState() == ghouse.restart));
				 
	}
}