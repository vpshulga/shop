import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.impl.RoleServiceImpl;

public class Test1 {
    public static void main(String[] args) {
        RoleService roleService = new RoleServiceImpl();
        RoleDto roleDto1 = new RoleDto();
        roleDto1.setName("r1");

        RoleDto roleDto2 = new RoleDto();
        roleDto2.setName("r2");

        PermissionDto p1 = new PermissionDto();
        p1.setName("p1");
        PermissionDto p2 = new PermissionDto();
        p2.setName("p2");
        PermissionDto p3 = new PermissionDto();
        p3.setName("p3");
        PermissionDto p4 = new PermissionDto();
        p4.setName("p4");

        roleDto1.getPermissions().add(p1);
        roleDto1.getPermissions().add(p2);
        roleDto1.getPermissions().add(p4);

        roleDto2.getPermissions().add(p1);
        roleDto2.getPermissions().add(p3);

        roleService.create(roleDto1);
        roleService.create(roleDto2);
    }


}
