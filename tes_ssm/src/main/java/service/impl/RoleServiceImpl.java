package service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.RoleDao;
import dto.Execution;
import dto.Page;
import entity.Role;
import enums.StateEnum;
import service.RoleService;
import util.CommonValue;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private CommonValue commonValue;

	@Override
	public Execution<Page<Role>> getRoleByPage(Map<String, Object> keyWords, int currentPage) {
		// TODO Auto-generated method stub
		Execution<Page<Role>> re = null;
		try {
			Page<Role> page = new Page<Role>(currentPage, keyWords, commonValue.getPageSize());
			int start = (page.getCurrentPage() - 1) * page.getPageSize();
			int totalCount = roleDao.getTotalCount(page.getKeyWords());
			page.setTotalCount(totalCount);
			List<Role> roleList = roleDao.queryRoleList(start, page.getPageSize(), page.getKeyWords());
			page.setData(roleList);
			re = new Execution<Page<Role>>(StateEnum.SUCCESS, page);
		} catch (Exception e) {
			// TODO: handle exception
			re = new Execution<Page<Role>>(StateEnum.INNER_ERROR, e.getMessage());
		}

		return re;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Execution<Role> addRole(String roleName) {
		// TODO Auto-generated method stub
		Execution<Role> re = null;
		try {
			Role role = new Role();
			role.setName(roleName);
			int rowEffect = roleDao.addRole(role);
			if (rowEffect == 1) {
				re = new Execution<Role>(StateEnum.SUCCESS);
			} else {
				re = new Execution<>(StateEnum.EXECUTE_FAIL, "新增角色失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			re = new Execution<Role>(StateEnum.INNER_ERROR, e.getMessage());
			//补抛出异常的话不会回滚
			throw new RuntimeException(e.getMessage());
		}
		return re;
	}

	@Override
	public Execution<Role> deleteRole(String roleId) {
		// TODO Auto-generated method stub
		Execution<Role> re = null;
		try {
			int rowEffect = roleDao.deleteRole(roleId);
			if (rowEffect == 1) {
				re = new Execution<>(StateEnum.SUCCESS);
			} else {
				re = new Execution<Role>(StateEnum.EXECUTE_FAIL, "删除角色失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			re = new Execution<>(StateEnum.INNER_ERROR, e.getMessage());
		}
		return re;
	}

	@Override
	public Execution<Role> updateRole(String roleId, String roleName) {
		// TODO Auto-generated method stub
		Execution<Role> re = null;

		Role role = new Role();
		role.setId(roleId);
		role.setName(roleName);
		int rowEffect = roleDao.updateRole(role);
		if (rowEffect == 1) {
			re = new Execution<>(StateEnum.SUCCESS);
		} else {
			re = new Execution<>(StateEnum.EXECUTE_FAIL, "更新角色失败");
		}

		return re;
	}

	@Override
	public Execution<List<Role>> getAllUser() {
		// TODO Auto-generated method stub
		Execution<List<Role>> re = null;
		try {
			List<Role> roleList = roleDao.queryRoleList(0, 1000, null);
			re = new Execution<>(StateEnum.SUCCESS, roleList);
		} catch (Exception e) {
			// TODO: handle exception
			re = new Execution<List<Role>>(StateEnum.INNER_ERROR, e.getMessage());
			e.printStackTrace();
		}
		return re;
	}

}
