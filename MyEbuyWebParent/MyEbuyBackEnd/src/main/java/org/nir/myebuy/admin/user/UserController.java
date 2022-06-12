package org.nir.myebuy.admin.user;

import java.io.IOException;
import java.util.List;

import org.nir.myebuy.admin.FileUploadUtil;
import org.nir.myebuy.common.entity.Role;
import org.nir.myebuy.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	// Default URL
	
	@GetMapping("/users")
	public String listFirstPage(Model model) {
		return listByPage(1, model, "firstName", "asc", null);
	}
	
	//WHY MY PREVIOUS @RequestParam does not workd!!!!?????????
	//Check the @Param VS RequestParam!!!!(he set to @Param!) 
	@GetMapping("/users/page/{pageNum}")
	public String listByPage(
			@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir,
			@Param("keyword") String keyword
			) {
		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);
		
		Page<User> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		
		List<User> listUsers = page.getContent();
		
		long startCount = (pageNum - 1) * UserService.USERS_PER_PAGE + 1;
		long endCount = startCount + UserService.USERS_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		
		return "users";		
	}
	
	/*
	 * @GetMapping("/users") public String listFirstPage(Model model) { //firstName
	 * is the name of the Entity property - not in the DB return this.listByPage(1,
	 * model, "firstName", "asc", null); }
	 * 
	 * 
	 *//**
		 * NOTE: DOES THE QUERY PARAMS ARE REQUIRED TO PASS IN THE URL?? I can not pass
		 * URLS othen than the Pattern:
		 * http://localhost:8080/MyEbuyBackEnd/users/page/1?sortField=email&sortDir=desc&keyword=aaxxx
		 * 
		 * users/page/2 => BAD REQUEST!!
		 * 
		 * @param pageNum
		 * @param model
		 * @param sortField
		 * @param sortDir
		 * @param keyword
		 * @return
		 *//*
			 * // //PAGINATION//WORKS
			 * 
			 * @GetMapping("/users/page/{pageNum}") public String
			 * listByPage(@PathVariable(name = "pageNum") Integer pageNum ,Model model,
			 * 
			 * @RequestParam("sortField") String sortField,
			 * 
			 * @RequestParam("sortDir") String sortDir,
			 * 
			 * @RequestParam("keyword") String keyword ) { System.err.println("sortField: "
			 * + sortField + " ,sortDir: " + sortDir + "keyword: " + keyword); Page<User>
			 * page = this.service.listByPage(pageNum, sortField, sortDir, keyword);
			 * 
			 * List<User> listUsers = page.getContent();
			 * 
			 * 
			 * 
			 * //Caluclate the start count(first item in the requested page long startCount
			 * = (pageNum - 1) * UserService.USERS_PER_PAGE + 1; //Caluclate the last
			 * count(last item in the requested page long endCount = startCount +
			 * UserService.USERS_PER_PAGE - 1;
			 * 
			 * if(endCount > page.getTotalElements()) endCount = page.getTotalElements();
			 * 
			 * 
			 * 
			 * //UPDATE THE MODEL //For testing the case when the list is empty and total
			 * items = 0 - should display the "No users found in the pagingaiton section" //
			 * model.addAttribute("listUsers", new ArrayList<>()); //
			 * model.addAttribute("totalItems",0);
			 * 
			 * String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
			 * //System.err.println(reverseSortDir);
			 * 
			 * 
			 * model.addAttribute("currentPage", pageNum); model.addAttribute("totalPages",
			 * page.getTotalPages()); model.addAttribute("listUsers", listUsers);
			 * model.addAttribute("totalItems",page.getTotalElements());
			 * model.addAttribute("startCount", startCount); model.addAttribute("endCount",
			 * endCount); model.addAttribute("sortField", sortField);
			 * model.addAttribute("sortDir", sortDir); model.addAttribute("reverseSortDir",
			 * reverseSortDir); model.addAttribute("keyword", keyword);
			 * 
			 * 
			 * return "users";
			 * 
			 * 
			 * }
			 */
	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> listRoles = service.listRoles();
		// Fetch all Roles from the DB
		// System.out.println(">>UserController.newUser(): List of all roles in DB:");
		listRoles.stream().forEach(System.out::println);

		// Create an EMPTY object to map to the Form inputs - and pass the list of roles
		// and enbaled
		User user = new User();
		user.setEnabled(true);

		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("pageTitle", "Create New User");

		return "user_form";
	}

	/**
	 * This method will redirect to the to the user list page To obtain the file
	 * Object - I need to specify the argument as RequestParam(comming from the form
	 * - after adding the enctype="multipart/"
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @param name:              comming from the form input of type ="file" with
	 *                           the name="image"
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		// For testing - read the file name - OK!!
		// System.err.println(multipartFile.getOriginalFilename());

		// Check if the user submitted the image file input - check the multipart value
		if (!multipartFile.isEmpty()) {
			// Clean the path string
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

			// Set the photos property of the User Entity
			user.setPhotos(fileName);

			// Persist the user and get the saved user to get the is and use it store the
			// file in the appropriate folder path
			User savedUser = this.service.save(user);

			String uploadDir = "user-photos/" + savedUser.getId();

			// Clean the folder before upload the photos - otherwise - it will add the photo
			// - instead of replacing - I want one image for each user!
			FileUploadUtil.cleanDir(uploadDir);

			// Upload the file
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		}
		// The form does not have any file upload
		else {
			// If the user has no photos - string is empty - set his photos property to null
			// NOTE: the photos property is bound to the form hidden input element :
			// th:field="*{photos}"
			if (user.getPhotos().isEmpty())
				user.setPhotos(null);
			this.service.save(user);
		}

		// To add a message in the view to redirect
		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");

		return "redirect:/users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		// Get the User object from the DB with the id
		// this method might throw an exception - catch it and handle it by setting an
		// apprpriate message in the UI
		try {
			User user = this.service.getUser(id);
			List<Role> listRoles = this.service.listRoles();

			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
			model.addAttribute("listRoles", listRoles);
			return "user_form";
		} catch (UserNotFoundException ex) {
			// display the error message in the user list page (after redirecting)
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			return "redirect:/users";
		}

	}

	// WHY GetMapping and not @Delete?? Maybe because it is not from a form?????
	@GetMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable(name = "userId") Integer userId, RedirectAttributes redirectAttributes) {
		try {
			// User user = this.service.getUser(userId);
			this.service.deleteUser(userId);
			redirectAttributes.addFlashAttribute("message", "The user ID " + userId + " has been deleted successfully");
		} catch (UserNotFoundException exc) {
			redirectAttributes.addFlashAttribute("message", exc.getMessage());
		}

		return "redirect:/users";

	}

	// ADD reference to redirect attribute for redirecting to user list after
	// updating
	// Disable/Enable user status : th:href="@{'/users/' + ${user.id} +
	// '/enabled/false'}"></a>
	@GetMapping("/users/{id}/enabled/{status}")
	public String updateUserEnableStatus(@PathVariable(name = "id") Integer id,
			@PathVariable(name = "status") boolean enabled, RedirectAttributes redirectAttributes) {
		System.out.println("UserController: updateUserEnabledStatus before updating: " + enabled);
		this.service.updateUserEnableStatus(id, enabled);

		String status = enabled ? "enabled" : "disabled";
		String message = "The user ID " + id + " has been " + status;

		// Add the message to the model
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/users";
	}

}