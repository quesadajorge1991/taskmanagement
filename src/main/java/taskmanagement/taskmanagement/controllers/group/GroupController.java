package taskmanagement.taskmanagement.controllers.group;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import taskmanagement.taskmanagement.controllers.util.Permisos;
import taskmanagement.taskmanagement.entity.Authority;
import taskmanagement.taskmanagement.entity.Groups;
import taskmanagement.taskmanagement.service.Group.GroupServiceImp;

@Controller
@RequestMapping(value = "/group")
public class GroupController {

	@Autowired
	GroupServiceImp groupService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/groups")
	public String groups(Model model) {
		model.addAttribute("title", messageSource.getMessage("N.group.groups", null, Locale.getDefault()));
		model.addAttribute("groups", groupService.findAll());
		return "/group/groups";
	}

	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("title", messageSource.getMessage("N.group.addgroup", null, Locale.getDefault()));
		model.addAttribute("group", new Groups());
		model.addAttribute("autorities", Permisos.getListauthorities());
		return "/group/add";
	}

	@PostMapping("/updateGroup")
	public String updateGroup(@ModelAttribute("group") @Valid Groups group, BindingResult result, Model model,
			@RequestParam(value = "selectedauthorities", required = false) String selectedauthorities[],
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {

			Groups groupp = groupService.findById(group.getId());
			model.addAttribute("group", groupp);
			model.addAttribute("autorities", Permisos.getListauthorities());
			model.addAttribute("autoritiesKeys", groupService.getKeys(Permisos.getListauthorities()));
			model.addAttribute("autoritiesByGroup", groupService.findGroupAuthoritiesByGroup(group.getGroupName()));
			return "/group/update";
		}

		/* validar que grupos no este null */
		System.out.println(group.getId());

		try {
			groupService.save(new Groups(group.getId(), group.getGroupName(), group.getDescription()));

			groupService.removeGroupAuthority(group.getGroupName());/* elimina todos los permisos de un grupo */

			for (int i = 0; i < selectedauthorities.length; i++) {
				groupService.addGroupAuthority(group.getGroupName(), new Authority(selectedauthorities[i]));
			}

			redirectAttributes.addFlashAttribute("msgtype", "success");
			redirectAttributes.addFlashAttribute("msgtitle", "Información");
			redirectAttributes.addFlashAttribute("msgbody",
					"Se actualizaron los permisos al grupo " + group.getGroupName());

		} catch (Exception e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el grupo" + group.getGroupName());
		}

		return "redirect:/group/groups";
	}

	@PostMapping("/addGroup")
	public String resetPassword(@ModelAttribute("group") @Valid Groups group, BindingResult result, Model model,
			@RequestParam(value = "selectedauthorities", required = false) String selectedauthorities[],
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("group", new Groups());
			model.addAttribute("autorities", Permisos.getListauthorities());
			return "/group/add";
		}
		try {
			groupService.save(new Groups(group.getGroupName(), group.getDescription())); /* save el grupo en la bd */
			groupService.removeGroupAuthority(group.getGroupName());/* elimina todos los permisos de un grupo */
			groupService.addGroupAuthority(group.getGroupName(), selectedauthorities); /* agregar permisos al grupo */

			redirectAttributes.addFlashAttribute("msgtype", "success");
			redirectAttributes.addFlashAttribute("msgtitle", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Se agrego el grupo " + group.getGroupName());
			return "redirect:/group/groups";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el grupo" + group.getGroupName());
		}
		return "redirect:/group/groups";
	}

	@GetMapping(value = "/deletegroup/{id}")
	public String deletegroup(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {
		try {
			groupService.delete(groupService.findById(id));
			redirectAttributes.addFlashAttribute("msgtype", "success");
			redirectAttributes.addFlashAttribute("msgtitle", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Grupo eleminado correctamente ");
			return "redirect:/group/groups";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eleminar el Grupo " + e.getLocalizedMessage());
		}
		return "redirect:/group/groups";
	}

	@GetMapping(value = "/update/{id}")
	public String update(Model model, @PathVariable(value = "id") int id) {
		model.addAttribute("title", messageSource.getMessage("N.group.updategroup", null, Locale.getDefault()));
		Groups group = groupService.findById(id);
		model.addAttribute("group", group);
		model.addAttribute("autorities", Permisos.getListauthorities());
		model.addAttribute("autoritiesKeys", groupService.getKeys(Permisos.getListauthorities()));
		model.addAttribute("autoritiesByGroup", groupService.findGroupAuthoritiesByGroup(group.getGroupName()));
		return "/group/update";
	}

}
