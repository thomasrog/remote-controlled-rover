package sample.jsp;

//@Controller
public class RoverController {
/*
	@Autowired
	private List<Steckdose> steckdosen;

	static Logger logger = Logger.getLogger(RoverController.class);

	@RequestMapping(value = "/dosen", method = RequestMethod.GET)
	public @ResponseBody
	List<Steckdose> showDosen() {
		return steckdosen;
	}

	@RequestMapping(value = { "/", "/dosen-view" }, method = RequestMethod.GET)
	public String displayDosen(Model model) {
		model.addAttribute("dosenList", steckdosen);
		return "/dosen";
	}

	@RequestMapping(value = "/dosen/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDose(@PathVariable String id,
			@RequestBody Steckdose changeTo) {

		Steckdose found = this.getDoseById(id);
		Assert.notNull(found);

		if (!found.switchDoseTo(changeTo.isOn())) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private Steckdose getDoseById(String id) {
		for (Steckdose dose : this.steckdosen) {
			if (dose.getId().compareTo(id) == 0) {
				return dose;
			}
		}
		return null;
	}
*/
}
