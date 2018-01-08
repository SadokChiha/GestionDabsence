package tn.iit.ws.controle_enseignant.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.iit.ws.controle_enseignant.entities.Enseignement;
import tn.iit.ws.controle_enseignant.entities.Pointage;
import tn.iit.ws.controle_enseignant.service.CrudService;
import tn.iit.ws.controle_enseignant.service.PointageService;

@RestController
@RequestMapping("pointage")
public class PointageController extends CrudController<Pointage, Integer> {
	@Autowired
	private PointageService pointageService;

	@Override
	protected CrudService<Pointage, Integer> getService() {
		return pointageService;
	}

	@RequestMapping(value = "/enseignant/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Pointage> findPointageEns(@PathVariable(name="id") int id) {
		return pointageService.findPointageEns(id);
}
	
	@RequestMapping(value = "/dateP/{date}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Pointage> findPointageDate(@PathVariable(name = "date") String date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Pointage> l= new ArrayList<Pointage>();
		String DatePointage="" ;
		try {
			Date datep = dateFormat.parse(date);
			DatePointage=""+dateFormat.format(datep);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Pointage> all = pointageService.findAll();
		for(int i =0;i<all.size();i++)
		{
			try {
				Date datep = dateFormat.parse(all.get(i).getDate().toString());
				
				if(dateFormat.format(datep).equals(DatePointage))
				{
					l.add(all.get(i));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return l;
}
}
