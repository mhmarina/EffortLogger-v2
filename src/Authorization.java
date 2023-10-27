
public class Authorization {
	
	enum permission
	{
		READ,
		WRITE,
		DELETE,
		UPDATE
	}
	
	enum role
	{
        SUPERVISOR(new permission[]{permission.READ, permission.WRITE, permission.DELETE, permission.UPDATE}),
        EMPLOYEE(new permission[]{permission.READ,permission.WRITE});
		
		private final permission[] permissions;
		
		role(permission[] permissions)
		{
			this.permissions = permissions;
		}
		
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
		
	public Authorization(String userid, role userRole)
		{
		this.userid = userid;
		this.userRole = userRole;
		}
	public boolean havePermission(permission perm)
		{
			return userRole.havePermission(perm);
		}
}
