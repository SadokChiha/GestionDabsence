package tn.iit.ws.controle_enseignant.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.taglibs.standard.tag.rt.core.OutTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.iit.ws.controle_enseignant.entities.Enseignement;
import tn.iit.ws.controle_enseignant.service.CrudService;
import tn.iit.ws.controle_enseignant.service.EnseignementService;

@RestController
@RequestMapping("enseignement")
public class EnseignementController extends CrudController<Enseignement, Integer> {
	@Autowired
	private EnseignementService enseignementService;

	@Override
	protected CrudService<Enseignement, Integer> getService() {
		return enseignementService;
	}

	@RequestMapping(value = "/byDate/", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Enseignement> FindEnsByDate() {

		List<Enseignement> l = new ArrayList<Enseignement>();
		List<Enseignement> all = enseignementService.findEnsByDate();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < all.size(); i++) {
			Date date;
			try {
				date = dateFormat.parse(all.get(i).getDate().toString());
				String output = dateFormat.format(date);
				System.out.println(output);
				if (output.equals("" + localDate))
					l.add(all.get(i));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		return l;

	}

}
