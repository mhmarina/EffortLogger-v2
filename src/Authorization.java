
public class Authorization {
	
	enum permission
	{
		READ, // reading logs
		WRITE, // creating logs
		DELETE, // deleting logs
		UPDATE // modify logs
	}
	
	enum role
	{
		// setting basic roles
        SUPERVISOR(new permission[]{permission.READ, permission.WRITE, permission.DELETE, permission.UPDATE}),
        EMPLOYEE(new permission[]{permission.READ,permission.WRITE});
		
		private final permission[] permissions;
		
		// setting current instance to permission
		role(permission[] permissions)
		{
			this.permissions = permissions;
		}
		
		// checks to see if person has a given permission
		public boolean havePermission(permission permission)
		{
			for (permission perm : permissions)
			{
				if (perm == permission)
				{
					return true;
				}
			}
			return false;
		}
	}
	
	
	private
		String userid;
		private role userRole;
		
		// setting userid and their role
	public Authorization(String userid, role userRole)
		{
		this.userid = userid;
		this.userRole = userRole;
		}
	
		// check for permission
	public boolean havePermission(permission perm)
		{
			return userRole.havePermission(perm);
		}
}
