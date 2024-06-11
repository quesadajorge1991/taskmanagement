package taskmanagement.taskmanagement.controllers.group;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import taskmanagement.taskmanagement.entity.Groups;
import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.service.Group.GroupService;

@Controller
@RequestMapping(value = "/group")
public class GroupController {

	@Autowired
	GroupService groupService;

	@GetMapping(value = "/groups")
	public String groups(Model model) {
		model.addAttribute("groups", groupService.findAll());
		return "/group/groups";
	}

	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("group", new Groups());
		return "/group/add";
	}

	@PostMapping("/addGroup")
	public @ResponseBody String resetPassword(@RequestBody Groups group) {
		JSONObject jsono = new JSONObject();

		System.out.println(group.getId());
		System.out.println(group.getGroupName());

		try {
			if (group.getId() == null) {
				groupService.save(new Groups(group.getGroupName(), group.getDescription()));
				jsono.put("msgtype", "success");
				jsono.put("msgtitle", "Información");
				jsono.put("msgbody", "Se agrego el grupo " + group.getGroupName());
			} else { /*si el id del objeto group esta en null inserta de lo contrario actualiza*/
				groupService.save(new Groups(group.getId(), group.getGroupName(), group.getDescription()));
				jsono.put("msgtype", "success");
				jsono.put("msgtitle", "Información");
				jsono.put("msgbody", "Se agrego el grupo " + group.getGroupName());
			}

		} catch (Exception e) {
			// TODO: handle exception
			jsono.put("msgtype", "error");
			jsono.put("msgtitle", "Error");
			jsono.put("msgbody", "Error al agregar el grupo" + group.getGroupName());
		}

		return jsono.toString();
	}

	@GetMapping(value = "/deletegroup/{id}")
	public String deletegroup(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {
		try {
			groupService.delete(groupService.findById(id));
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Grupo eleminado correctamente ");
			return "redirect:/group/groups";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eleminar el Grupo " + e.getLocalizedMessage());
		}
		return "redirect:/group/groups";
	}

	@GetMapping(value = "/update/{id}")
	public String update(Model model, @PathVariable(value = "id") int id) {
		Groups group = groupService.findById(id);
		model.addAttribute("group", group);
		return "/group/update";
	}

}
