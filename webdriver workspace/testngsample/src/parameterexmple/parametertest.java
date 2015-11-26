package parameterexmple;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class parametertest {
  @Test
  @Parameters("myname")
  public void parameterTest(String myname)
  {
	  System.out.println("Parameter value" + myname);
  }
}
