package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Execution;
import dto.Page;
import entity.Role;
import enums.StateEnum;
import service.RoleService;
import util.ParamVerify;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/{keyWord}/{currentPage}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoleByPage(@PathVariable("keyWord") String keyWord,
			@PathVariable("currentPage") String currentPageStr) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			keyWord = "undefined".equals(keyWord) ? null : keyWord;
			int currentPage = "undefined".equals(currentPageStr) || Integer.parseInt(currentPageStr) < 1 ? 0
					: Integer.parseInt(currentPageStr);
			Map<String, Object> keyWords = new HashMap<String, Object>();
			keyWords.put("roleName", "undefined".equals(keyWord) ? null : keyWord);
			Execution<Page<Role>> re = roleService.getRoleByPage(keyWords, currentPage);
			if (re.getState() == StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("page", re.getData());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", re.getStateInfo());
			}
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}

		return modelMap;
	}

	@RequestMapping(value = "/{roleName}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addRole(@PathVariable("roleName") String roleName) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!ParamVerify.isNULL(roleName)) {
			Execution<Role> re = roleService.addRole(roleName);
			if (re.getState() == StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", re.getStateInfo());
			}
		} else {
			modelMap.put("errMsg", "角色信息为空");
			modelMap.put("success", false);
		}
		return modelMap;
	}

	@RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteRole(@PathVariable("roleId") String roleId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!ParamVerify.isNULL(roleId)) {
			Execution<Role> re = null;
			re = roleService.deleteRole(roleId);
			if (re.getState() == StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", re.getStateInfo());
			}
		} else {
			modelMap.put("errMsg", "角色ID为空");
			modelMap.put("success", false);
		}
		return modelMap;
	}

	@RequestMapping(value = "/{roleId}/{roleName}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateRole(@PathVariable("roleId") String roleId,
			@PathVariable("roleName") String roleName) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!ParamVerify.isNULL(roleId, roleName)) {
			Execution<Role> re = null;
			re = roleService.updateRole(roleId, roleName);
			if (re.getState() == StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", re.getStateInfo());
			}
		} else {
			modelMap.put("errMsg", "角色信息为空");
			modelMap.put("success", false);
		}

		return modelMap;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> gertAllUser() {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		Execution<List<Role>> re = roleService.getAllUser();
		if (re.getState() == StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("roleList", re.getData());
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", re.getStateInfo());
		}

		return modelMap;
	}
}
