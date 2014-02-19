package xelitez.frostcraft.command;

import net.minecraft.command.SyntaxErrorException;

public class NoTestException extends SyntaxErrorException
{
	private static final long serialVersionUID = 2397292912612476684L;

	public NoTestException(Object ... par2ArrayOfObj)
    {
        super("This command is only available to testers of Frostcraft", par2ArrayOfObj);
    }
}
